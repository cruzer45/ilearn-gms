/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.grades;

import ilearn.classes.Classes;
import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.kernel.logger.iLogger;
import ilearn.term.Term;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mrogers
 */
public class Grade
{

    static final Logger logger = Logger.getLogger(Grade.class.getName());
    protected static final String[] validStates =
    {
        "Absent", "Excused", "Incomplete"
    };

    public static String[] getValidStates()
    {
        return validStates;
    }

    public static ArrayList<String> getAssessmentTypes()
    {
        ArrayList<String> list = new ArrayList<String>();
        try
        {
            String sql = "SELECT `assmtType` FROM `iLearn`.`listAssessmentTypes` ORDER BY `assmtType` DESC; ";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                list.add(rs.getString("assmtType"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
        }
        return list;
    }

    public static ArrayList<Integer> getAssessmentList(String clsCode, String subjectID)
    {
        ArrayList<Integer> assmtIDs = new ArrayList<Integer>();
        try
        {
            String sql1 = "SELECT `assmtID`, `assmtType`, `assmtTitle`, `assmtDate`, `assmtTotalPoints`, `assmtClassID`, `assmtSubject`, `assmtTerm`, `assmtTeacher`, `assmtStatus` FROM `iLearn`.`Assments` WHERE `assmtClassID` = ? AND `assmtTerm` = ? AND `assmtSubject` = ? AND `assmtStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql1);
            prep.setString(1, clsCode);
            prep.setString(2, Term.getCurrentTerm());
            prep.setString(3, subjectID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                assmtIDs.add(rs.getInt("assmtID"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the list of assessments for a class and subject.";
            logger.log(Level.SEVERE, message, e);
        }
        return assmtIDs;
    }

    public static DefaultTableModel getStudentList(String classCode)
    {
        DefaultTableModel model = new DefaultTableModel()
        {

            @Override
            public Class getColumnClass(int columnIndex)
            {
                Object o = getValueAt(0, columnIndex);
                if (o == null)
                {
                    return Object.class;
                }
                else
                {
                    return o.getClass();
                }
            }

            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                //Only allow the grade column to be editable.
                boolean editable = false;
                if (mColIndex == 3 || mColIndex == 4)
                {
                    editable = true;
                }
                return editable;
                //return false;
            }

            @Override
            public void setValueAt(Object value, int row, int column)
            {
                //If they are changing the remarks column just accept the changes.
                if (column == 4)
                {
                    Vector rowVector = (Vector) dataVector.elementAt(row);
                    rowVector.setElementAt(value, column);
                    fireTableCellUpdated(row, column);
                    return;
                }
                //If it is the grades column only accept specific values.
                if (isValidValue(value))
                {
                    Vector rowVector = (Vector) dataVector.elementAt(row);
                    rowVector.setElementAt(value, column);
                    fireTableCellUpdated(row, column);
                }
            }
            // Protected methods

            protected boolean isValidValue(Object value)
            {
                String sValue = (String) value;
                sValue = sValue.trim();
                if (value instanceof String)
                {
                    for (int i = 0; i < validStates.length; i++)
                    {
                        if (sValue.equals(validStates[i]))
                        {
                            return true;
                        }
                    }
                }
                if (isInteger(sValue))
                {
                    return true;
                }
                if (isDouble(sValue))
                {
                    return true;
                }
                return false;
            }

            protected boolean isInteger(String input)
            {
                try
                {
                    Integer.parseInt(input);
                    return true;
                }
                catch (Exception e)
                {
                    return false;
                }
            }

            protected boolean isDouble(String input)
            {
                try
                {
                    Double.parseDouble(input);
                    return true;
                }
                catch (Exception e)
                {
                    return false;
                }
            }
        };
        ArrayList<String> studentID = new ArrayList<String>();
        ArrayList<String> firstName = new ArrayList<String>();
        ArrayList<String> lastName = new ArrayList<String>();
        ArrayList<String> grade = new ArrayList<String>();
        ArrayList<String> remarks = new ArrayList<String>();
        try
        {
            String sql = "SELECT `stuID`, `stuFirstName`, `stuLastName`, `stuOtherNames`, `stuStatus` FROM `iLearn`.`Student` WHERE `stuClsCode` = ? AND `stuStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, classCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                studentID.add(rs.getString("stuID"));
                firstName.add(rs.getString("stuFirstName"));
                lastName.add(rs.getString("stuLastName"));
                grade.add(" ");
                remarks.add(" ");
            }
            prep.close();
            rs.close();
            model.addColumn("ID", studentID.toArray());
            model.addColumn("First Name", firstName.toArray());
            model.addColumn("Last Name", lastName.toArray());
            model.addColumn("Grade", grade.toArray());
            model.addColumn("Remarks", remarks.toArray());
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the list of students for a class.";
            logger.log(Level.SEVERE, message, e);
        }
        return model;
    }

    public static boolean addAssessment(String assmtTerm, String assmtSubject, String assmtTeacher, String assmtTitle, String assmtDate, String assmtType, String assmtTotalPoints, String assmtClassID)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `Assments` (`assmtTerm`, `assmtSubject`, `assmtTeacher`, `assmtTitle`,`assmtDate`, `assmtType`, `assmtTotalPoints` ,  `assmtClassID`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, assmtTerm);
            prep.setString(2, assmtSubject);
            prep.setString(3, assmtTeacher);
            prep.setString(4, assmtTitle);
            prep.setString(5, assmtDate);
            prep.setString(6, assmtType);
            prep.setString(7, assmtTotalPoints);
            prep.setString(8, assmtClassID);
            prep.execute();
            prep.close();
            successful = true;
            //Log the Action
            String message = "The assessment, \"" + assmtTitle + "\" was added to the system.";
            iLogger.logMessage(message, "Add", "Assessment");
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding an assessment.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean updateAssessment(String assmtID, String assmtSubject, String assmtTitle, String assmtDate, String assmtType, String assmtTotalPoints, String assmtClassID)
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Assments` SET `assmtType`=?, `assmtTitle`=?, `assmtDate`=?, `assmtTotalPoints`=?, `assmtClassID`=?, `assmtSubject`=? WHERE `assmtID`=? ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, assmtType);
            prep.setString(2, assmtTitle);
            prep.setString(3, assmtDate);
            prep.setString(4, assmtTotalPoints);
            prep.setString(5, assmtClassID);
            prep.setString(6, assmtSubject);
            prep.setString(7, assmtID);
            prep.executeUpdate();
            prep.close();
            successful = true;
            //Log the Action
            String message = "Assessment No. " + assmtID + "\" was modified.";
            iLogger.logMessage(message, "Update", "Assessment");
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating an assessment.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean addGrades(String assmtID, ArrayList<String> stuID, ArrayList<String> grade, ArrayList<String> remarks)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `TermGrade` (`grdStuID`, `grdAssmtID`, `grdPointsEarned`, `grdRemark`) VALUES (?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            for (int i = 0; i < stuID.size(); i++)
            {
                prep.setString(1, stuID.get(i));
                prep.setString(2, assmtID);
                prep.setString(3, grade.get(i).trim());
                prep.setString(4, remarks.get(i).trim());
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while saving assessment grades.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean updateGrades(String assmtID, ArrayList<String> stuID, ArrayList<String> grade, ArrayList<String> remarks)
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `TermGrade` SET `grdPointsEarned`= ?, `grdRemark`= ? WHERE `grdStuID`= ? and `grdAssmtID` = ? LIMIT 1;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            for (int i = 0; i < stuID.size(); i++)
            {
                prep.setString(1, grade.get(i).trim());
                prep.setString(2, remarks.get(i).trim());
                prep.setString(3, stuID.get(i));
                prep.setString(4, assmtID);
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating assessment grades.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static String getAssmtID(String assmtTerm, String assmtSubject, String assmtTeacher, String assmtTitle, String assmtDate, String assmtType, String assmtTotalPoints, String assmtClassID)
    {
        String assmtID = "";
        try
        {
            String sql = "SELECT `assmtID` FROM `iLearn`.`Assments` "
                    + "WHERE  `assmtType` = ? AND `assmtTitle` = ? AND `assmtDate` = ? AND `assmtTotalPoints` = ? AND "
                    + "`assmtClassID` = ? AND `assmtSubject` = ? AND `assmtTerm` = ? AND `assmtTeacher` = ? ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, assmtType);
            prep.setString(2, assmtTitle);
            prep.setString(3, assmtDate);
            prep.setString(4, assmtTotalPoints);
            prep.setString(5, assmtClassID);
            prep.setString(6, assmtSubject);
            prep.setString(7, assmtTerm);
            prep.setString(8, assmtTeacher);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                assmtID = rs.getString("assmtID");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the classID.";
            logger.log(Level.SEVERE, message, e);
        }
        return assmtID;
    }

    public static DefaultTableModel getAssessmentTable(String criteria)
    {
        String original = criteria;
        criteria = Utilities.percent(criteria);
        DefaultTableModel model = new DefaultTableModel()
        {

            @Override
            public Class getColumnClass(int columnIndex)
            {
                Object o = getValueAt(0, columnIndex);
                if (o == null)
                {
                    return Object.class;
                }
                else
                {
                    return o.getClass();
                }
            }

            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }
        };
        ArrayList<String> ID = new ArrayList<String>();
        ArrayList<String> type = new ArrayList<String>();
        ArrayList<String> title = new ArrayList<String>();
        ArrayList<String> date = new ArrayList<String>();
        ArrayList<String> cls = new ArrayList<String>();
        ArrayList<String> subject = new ArrayList<String>();
        try
        {
            String sql = "SELECT `assmtID`, `assmtType`, `assmtTitle`, `assmtDate`, `assmtTotalPoints`, `assmtClassID`, `assmtSubject`, `assmtTerm`, `assmtTeacher`, `assmtStatus` "
                    + "FROM `iLearn`.`Assments` "
                    + "WHERE (`assmtID` LIKE ? OR `assmtType` LIKE ? OR `assmtTitle` LIKE ? OR `assmtDate` LIKE ? OR `assmtTotalPoints` LIKE ? OR `assmtClassID` LIKE ? OR `assmtSubject` LIKE ?  OR `assmtTeacher` LIKE ?) AND `assmtStatus` = 'Active' AND `assmtTerm` = ? "
                    + "LIMIT 0, 1000;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, criteria);
            prep.setString(2, criteria);
            prep.setString(3, criteria);
            prep.setString(4, criteria);
            prep.setString(5, criteria);
            prep.setString(6, Classes.getClassID(original));
            prep.setString(7, criteria);
            prep.setString(8, criteria);
            prep.setString(9, Term.getCurrentTerm());
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                ID.add(rs.getString("assmtID"));
                type.add(rs.getString("assmtType"));
                title.add(rs.getString("assmtTitle"));
                date.add(Utilities.MDY_Formatter.format(rs.getDate("assmtDate")));
                cls.add(Classes.getClassCode(rs.getString("assmtClassID")));
                subject.add(rs.getString("assmtSubject"));
            }
            rs.close();
            prep.close();
            model.addColumn("ID", ID.toArray());
            model.addColumn("Type", type.toArray());
            model.addColumn("Title", title.toArray());
            model.addColumn("Date", date.toArray());
            model.addColumn("Class", cls.toArray());
            model.addColumn("Subject", subject.toArray());
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the Assessment table model.";
            logger.log(Level.SEVERE, message, e);
        }
        return model;
    }

    public static ArrayList<Object> getAssessmentInfo(String assmtID)
    {
        ArrayList<Object> assmt = new ArrayList<Object>();
        try
        {
            String sql = "SELECT `assmtID`, `assmtType`, `assmtTitle`, `assmtDate`, `assmtTotalPoints`, `assmtClassID`, `assmtSubject`, `assmtTerm`, `assmtTeacher`, `assmtStatus` "
                    + "FROM `iLearn`.`Assments` "
                    + "WHERE `assmtID` = ? AND `assmtStatus` = 'Active' ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, assmtID);
            ResultSet rs = prep.executeQuery();
            rs.first();
            assmt.add(rs.getString("assmtID"));//0
            assmt.add(rs.getString("assmtType"));//1
            assmt.add(rs.getString("assmtTitle"));//2
            assmt.add(Utilities.MDY_Formatter.format(rs.getDate("assmtDate")));//3
            assmt.add(rs.getString("assmtTotalPoints"));//4
            assmt.add(Classes.getClassCode(rs.getString("assmtClassID")));//5
            assmt.add(rs.getString("assmtSubject"));//5
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the Assessment table model.";
            logger.log(Level.SEVERE, message, e);
        }
        return assmt;
    }

    public static ArrayList<String> getStudentGrade(String assmtID, String stuID)
    {
        ArrayList<String> stuGrade = new ArrayList<String>();
        try
        {
            String sql = "SELECT `grdID`, `grdStuID`, `grdAssmtID`, `grdPointsEarned`, `grdRemark`, `grdStatus` FROM `iLearn`.`TermGrade` "
                    + "WHERE `grdAssmtID` = ? AND `grdStuID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, assmtID);
            prep.setString(2, stuID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                stuGrade.add(rs.getString("grdPointsEarned"));
                stuGrade.add(rs.getString("grdRemark"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the student's grade.";
            logger.log(Level.SEVERE, message, e);
        }
        return stuGrade;
    }

    public static boolean addBlankGrades(String assmtID, String stuID)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `TermGrade` (`grdStuID`, `grdAssmtID`, `grdPointsEarned` , `grdRemark`) VALUES (?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuID);
            prep.setString(2, assmtID);
            prep.setString(3, "Excused");
            prep.setString(4, " ");
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while saving blank assessment grades.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static ArrayList<Object> checkAllGrades()
    {
        ArrayList<Object> results = new ArrayList<Object>();
        try
        {
            //Get list of classes
            ArrayList<String> classes = Classes.getClassList();
            for (String cls : classes)
            {
                System.out.println("Now doing class: " + cls);
                //Get all students in a class.
                ArrayList<String> studentList = Classes.getStudentIDList(cls);
                //Get list of all subjects for class
                ArrayList<String> subjects = Classes.getSubjectList(cls);
                for (String sub : subjects)
                {
                    String clsID = Classes.getClassID(cls);
                    System.out.println("\tNow doing subject: " + sub);
                    //Get all assessments for a subject
                    ArrayList<Integer> assmtList = getAssessmentList(clsID, sub);
                    for (Integer assmt : assmtList)
                    {
                        System.out.println("\t\tNow doing assmt: " + assmt);
                        for (String stuID : studentList)
                        {
                            System.out.println("\t\t\tNow checking: " + stuID);
                            ArrayList<String> stuGrade = getStudentGrade(String.valueOf(assmt), stuID);
                            try
                            {
                                String grade = stuGrade.get(0);
                                if ((grade.equals("Excused")) || (grade.equals("Incomplete")) || (grade.equals("Absent")))
                                {
                                    String[] missingGrade =
                                    {
                                        stuID, String.valueOf(assmt)
                                    };
                                    results.add(missingGrade);
                                }
                            }
                            catch (Exception e)
                            {
                                String[] missingGrade =
                                {
                                    stuID, String.valueOf(assmt)
                                };
                                results.add(missingGrade);
                            }
                        }
                    }
                    //get list of students and make sure they have a grade for each assessment
                }
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while checking for missing grades.";
            logger.log(Level.SEVERE, message, e);
        }
        return results;
    }
}
