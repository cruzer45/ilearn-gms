package ilearn.grades;

import ilearn.classes.Classes;
import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.kernel.logger.iLogger;
import ilearn.school.School;
import ilearn.student.Student;
import ilearn.subject.Subject;
import ilearn.term.Term;
import ilearn.user.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
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
    static ArrayList<String> graStuID = new ArrayList<String>();
    static ArrayList<String> graSubCode = new ArrayList<String>();
    static ArrayList<String> graTrmCode = new ArrayList<String>();
    static ArrayList<String> graMid = new ArrayList<String>();
    static ArrayList<String> graFinal = new ArrayList<String>();
    static ArrayList<String> graGPA = new ArrayList<String>();
    static ArrayList<String> graLetterGrade = new ArrayList<String>();
    static ArrayList<String> graRemark = new ArrayList<String>();
    protected static final String[] validStates =
    {
        "Absent", "A", "a", "Excused", "E", "e", "Incomplete", "I", "i"
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
            String sql = "SELECT `assmtType` FROM `listAssessmentTypes` ORDER BY `assmtType` asc; ";
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
            String message = "An error occurred while getting the list of assessment types.";
            logger.log(Level.SEVERE, message, e);
        }
        return list;
    }

    public static String getAssessmentTypeID(String assessmentType)
    {
        String list = "";
        try
        {
            String sql = "SELECT `id` FROM `listAssessmentTypes` where `assmtType` = ?; ";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, assessmentType);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                list = rs.getString("id");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the ID of an assessment type.";
            logger.log(Level.SEVERE, message, e);
        }
        return list;
    }

    public static ArrayList<Integer> getAssessmentList(String clsCode, String subjectID)
    {
        ArrayList<Integer> assmtIDs = new ArrayList<Integer>();
        try
        {
            String sql1 = "SELECT `assmtID` FROM `Assments` WHERE `assmtClassID` = ? AND `assmtTerm` = ? AND `assmtSubject` = ? AND `assmtStatus` = 'Active';";
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
                if (isDouble(sValue))
                {
                    return true;
                }
                return false;
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
            String sql = "SELECT `stuID`, `stuFirstName`, `stuLastName`, `stuOtherNames`, `stuStatus` FROM `Student` WHERE `stuClsCode` = ? AND `stuStatus` = 'Active';";
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

    public static DefaultTableModel getMidTermGrades(String stuID)
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
                //Only allow the remarks column to be editable.
                boolean editable = false;
                if (mColIndex == 4)
                {
                    editable = true;
                }
                return editable;
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
            }
        };
        ArrayList<String> graID = new ArrayList<String>();
        ArrayList<String> subject = new ArrayList<String>();
        ArrayList<String> midGrade = new ArrayList<String>();
        ArrayList<String> finalGrade = new ArrayList<String>();
        ArrayList<String> remarks = new ArrayList<String>();
        try
        {
            String sql = "SELECT `Student`.`stuID`, `Subject`.`subName`,`graMid`,`graFinal`,`graRemark`, `graID` "
                         + "FROM `Grade` "
                         + "INNER JOIN `Student` ON `Grade`.`graStuID` = `Student`.stuID "
                         + "INNER JOIN `Subject` ON `Subject`.`subCode` = `Grade`.graSubCode "
                         + "WHERE `Grade`.graStatus = 'Active' AND `stuID` =  ? "
                         + "ORDER BY `stuClsCode`,`stuID`, `Subject`.subName;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                graID.add(rs.getString("graID"));
                subject.add(rs.getString("subName"));
                midGrade.add(Utilities.roundDouble(rs.getDouble("graMid")));
                finalGrade.add(Utilities.roundDouble(rs.getDouble("graFinal")));
                remarks.add(rs.getString("graRemark"));
            }
            prep.close();
            rs.close();
            model.addColumn("ID", graID.toArray());
            model.addColumn("Subject", subject.toArray());
            model.addColumn("Mid-Term", midGrade.toArray());
            model.addColumn("Final", finalGrade.toArray());
            model.addColumn("Remarks", remarks.toArray());
        }
        catch (Exception e)
        {
            String message = "An error occurred while loading the mid term grades.";
            logger.log(Level.SEVERE, message, e);
        }
        return model;
    }

    public static DefaultTableModel searchGradeList(String criteria)
    {
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
        ArrayList<String> studentID = new ArrayList<String>();
        ArrayList<String> stuName = new ArrayList<String>();
        ArrayList<String> clsCode = new ArrayList<String>();
        try
        {
            String sql = "SELECT DISTINCT `Student`.`stuID`,CONCAT_WS(' ',`Student`.`stuFirstName`,`Student`.`stuLastName`) AS `Name`,`Student`.`stuClsCode`"
                         + "FROM `Grade` "
                         + "INNER JOIN `Student` ON `Grade`.`graStuID` = `Student`.stuID "
                         + "WHERE `Grade`.graStatus = 'Active' AND ((stuID LIKE ?) OR (CONCAT_WS(' ',`Student`.`stuFirstName`,`Student`.`stuLastName`) LIKE ?) OR (stuClsCode LIKE ?)) "
                         + "ORDER BY `stuClsCode`,`stuID`";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, criteria);
            prep.setString(2, criteria);
            prep.setString(3, criteria);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                studentID.add(rs.getString("stuID"));
                stuName.add(rs.getString("Name"));
                clsCode.add(rs.getString("stuClsCode"));
            }
            prep.close();
            rs.close();
            model.addColumn("ID", studentID.toArray());
            model.addColumn("Name", stuName.toArray());
            model.addColumn("Class", clsCode.toArray());
        }
        catch (Exception e)
        {
            String message = "An error occurred while searching the mid term grades.";
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
            String sql = "SELECT `assmtID` FROM `Assments` "
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
        criteria = Utilities.percent(criteria);
        DefaultTableModel model = new DefaultTableModel()
        {
            @Override
            public Class getColumnClass(int columnIndex)
            {
                try
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
                catch (Exception e)
                {
                    return Object.class;
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
            String sql = "SELECT `assmtID`, `assmtType`, `assmtTitle`, `assmtDate`, `assmtTotalPoints`, `assmtClassID`, `assmtSubject`, `assmtTerm`, `assmtTeacher`, `assmtStatus` , `clsCode` "
                         + "FROM `Assments` "
                         + "INNER JOIN `Class` ON `Assments`.`assmtClassID` = `Class`.`clsID` "
                         + "WHERE (`assmtID` LIKE ? OR `assmtType` LIKE ? OR `assmtTitle` LIKE ? OR `assmtDate` LIKE ? OR `assmtTotalPoints` LIKE ? OR `assmtClassID` LIKE ? OR `assmtSubject` LIKE ?  OR `assmtTeacher` LIKE ? OR `clsCode` LIKE ?) AND `assmtStatus` = 'Active' AND `assmtTerm` = ? "
                         + "LIMIT 0, 1000;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, criteria);
            prep.setString(2, criteria);
            prep.setString(3, criteria);
            prep.setString(4, criteria);
            prep.setString(5, criteria);
            prep.setString(6, criteria);
            prep.setString(7, criteria);
            prep.setString(8, criteria);
            prep.setString(9, criteria);
            prep.setString(10, Term.getCurrentTerm());
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                if ((User.getPermittedClasses().contains(rs.getString("clsCode")) && User.getPermittedSubjects().contains(rs.getString("assmtSubject"))) || User.getUserGroup().equals("Administration"))
                {
                    ID.add(rs.getString("assmtID"));
                    type.add(rs.getString("assmtType"));
                    title.add(rs.getString("assmtTitle"));
                    date.add(Utilities.MDY_Formatter.format(rs.getDate("assmtDate")));
                    cls.add(rs.getString("clsCode"));
                    subject.add(rs.getString("assmtSubject"));
                }
            }
            rs.close();
            prep.close();
            model.addColumn("ID", ID.toArray());
            model.addColumn("Class", cls.toArray());
            model.addColumn("Subject", subject.toArray());
            model.addColumn("Type", type.toArray());
            model.addColumn("Title", title.toArray());
            model.addColumn("Date", date.toArray());
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
                         + "FROM `Assments` "
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
            String sql = "SELECT `grdID`, `grdStuID`, `grdAssmtID`, `grdPointsEarned`, `grdRemark`, `grdStatus` FROM `TermGrade` "
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

    public static int getMissingGradeCount()
    {
        int missingGrades = 0;
        try
        {
            String sql = "SELECT count(`grdID`) AS 'Empty_Count'"
                         + " FROM `TermGrade` "
                         + " WHERE (`grdPointsEarned` IS NULL OR `grdPointsEarned` = '' OR `grdPointsEarned` = ' ' OR `grdPointsEarned` = 'Absent' OR `grdPointsEarned` = 'Incomplete') AND `grdStatus` = 'Active'";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                missingGrades = rs.getInt("Empty_Count");
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the number of missing grades.";
            logger.log(Level.SEVERE, message, e);
        }
        return missingGrades;
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
                //System.out.println("Now doing class: " + cls);
                //Get all students in a class.
                ArrayList<String> studentList = Classes.getStudentIDList(cls);
                //Get list of all subjects for class
                ArrayList<String> subjects = Classes.getSubjectList(cls);
                for (String sub : subjects)
                {
                    String clsID = Classes.getClassID(cls);
                    //System.out.println("\tNow doing subject: " + sub);
                    //Get all assessments for a subject
                    ArrayList<Integer> assmtList = getAssessmentList(clsID, sub);
                    for (Integer assmt : assmtList)
                    {
                        //System.out.println("\t\tNow doing assmt: " + assmt);
                        for (String stuID : studentList)
                        {
                            //System.out.println("\t\t\tNow checking student: " + stuID);
                            ArrayList<String> stuGrade = getStudentGrade(String.valueOf(assmt), stuID);
                            try
                            {
                                String grade = stuGrade.get(0);
                                if ((grade.equals("Excused")) || (grade.equals("Incomplete")) || (grade.equals("Absent")) || (grade.equals("")) || (grade.equals(" ")))
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

    public static boolean calculateFinalGrade()
    {
        boolean successful = false;
        try
        {
            long startTime = System.currentTimeMillis();
            //resetGrades
            resetGrades();
            String currentTerm = Term.getCurrentTerm();
            //Get list of classes
            ArrayList<String> classes = Classes.getClassList();
            for (String cls : classes) // loop classes
            {
                System.out.println("Now doing class: " + cls);
                //Get all students in a class.
                ArrayList<String> studentList = Classes.getStudentIDList(cls);
                //Get list of all subjects for class
                ArrayList<String> subjects = Classes.getSubjectList(cls);
                for (String sub : subjects) // loop subjects
                {
                    System.out.println("\tNow doing subject: " + sub);
                    for (String stuID : studentList) // loop students
                    {
                        System.out.println("\t\tNow doing student: " + stuID);
//                        double termGrade = 0.0;
//                        double examGrade = 0.0;
                        //Get all term grade for a subject
//                        termGrade = getTermGrade(stuID, sub);
//                        examGrade = getExamGrade(stuID, sub);
                        //Get Exam grade for a subject
                        // loop assessments
                        double grade = 0.0;
//                        if (termGrade != 0 && examGrade != 0)
//                        {
//                            grade = (termGrade * .65) + (examGrade * .35);
//                        }
//                        else
//                        {
//                            grade = termGrade;
//                        }
                        if (Subject.hasWeighting(Subject.getSubjectID(sub)))
                        {
                            grade = calculateGradeWithWeighting(stuID, sub);
                        }
                        else
                        {
                            grade = calculateGradeWithoutWeighting(stuID, sub);
                        }
                        addFinalGrade(stuID, sub, currentTerm, grade);
                        //System.out.println("Student No " + stuID + " got a " + grade + " for Subject " + sub);
                    }// end loop student
                    //get list of students and make sure they have a grade for each assessment
                }// end loop subjects
            }// end loop classes
            successful = true;
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            String time = String.format("%d min, %d sec",
                                        TimeUnit.MILLISECONDS.toMinutes(duration),
                                        TimeUnit.MILLISECONDS.toSeconds(duration)
                                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
            System.out.println("That took " + time);
        }
        catch (Exception e)
        {
            String message = "An error occurred while calculating final grades.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean calculateMidTerms()
    {
        boolean successful = false;
        try
        {
            long startTime = System.currentTimeMillis();
            //resetGrades
            resetGrades();
            String currentTerm = Term.getCurrentTerm();
            //Get list of classes
            ArrayList<String> classes = Classes.getClassList();
            for (String cls : classes) // loop classes
            {
                System.out.println("Now doing class: " + cls);
                //Get all students in a class.
                ArrayList<String> studentList = Classes.getStudentIDList(cls);
                //Get list of all subjects for class
                ArrayList<String> subjects = Classes.getSubjectList(cls);
                for (String sub : subjects) // loop subjects
                {
                    System.out.println("\tNow doing subject: " + sub);
                    for (String stuID : studentList) // loop students
                    {
                        System.out.println("\t\tNow doing student: " + stuID);
                        double grade = 0.0;
                        if (Subject.hasWeighting(Subject.getSubjectID(sub)))
                        {
                            grade = calculateGradeWithWeighting(stuID, sub);
                        }
                        else
                        {
                            grade = calculateGradeWithoutWeighting(stuID, sub);
                        }
                        addMidTermGrade(stuID, sub, currentTerm, grade);
                    }// end loop student
                    //get list of students and make sure they have a grade for each assessment
                }// end loop subjects
            }// end loop classes
            successful = true;
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            String time = String.format("%d min, %d sec",
                                        TimeUnit.MILLISECONDS.toMinutes(duration),
                                        TimeUnit.MILLISECONDS.toSeconds(duration)
                                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
            System.out.println("That took " + time);
        }
        catch (Exception e)
        {
            String message = "An error occurred while calculating mid-term grades.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean saveMidTerms()
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `Grade` (`graStuID`, `graSubCode`, `graTrmCode`, `graMid`, `graFinal`, `graGPA`, `graLetterGrade`, `graRemark`) "
                         + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            for (int i = 0; i < graStuID.size(); i++)
            {
                prep.setString(1, graStuID.get(i));
                prep.setString(2, graSubCode.get(i));
                prep.setString(3, graTrmCode.get(i));
                prep.setString(4, graMid.get(i));
                prep.setString(5, graFinal.get(i));
                prep.setString(6, graGPA.get(i));
                prep.setString(7, graLetterGrade.get(i));
                prep.setString(8, graRemark.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
            updateStudentClasses();
            updateMidTermGPA();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while saving mid-term grades.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean cleanMidTermGrades()
    {
        boolean succesful = true;
        try
        {
            String sql1 = "DELETE FROM `Grade` WHERE `graSubCode` LIKE '%COUN%';";
            String sql2 = "DELETE FROM `Grade` WHERE `graMid` = 0 AND (`graClsCode` LIKE '3%' OR `graClsCode` LIKE '4%');";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql1);
            prep.execute();
            prep = Environment.getConnection().prepareStatement(sql2);
            prep.execute();
            prep.close();
            succesful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while cleaning mid-term grades.";
            logger.log(Level.SEVERE, message, e);
        }
        return succesful;
    }

    public static boolean cleanFinalGrades()
    {
        boolean succesful = true;
        try
        {
            String sql1 = "DELETE FROM `Grade` WHERE `graSubCode` LIKE '%COUN%';";
            String sql2 = "DELETE FROM `Grade` WHERE `graFinal` = 0;";
            String sql3 = "UPDATE `Grade` SET `graFinal` = 70 WHERE `graFinal` >= 68.5 AND `graFinal` < 70;";
            sql3 = "";
            String sql4 = "UPDATE `Grade` SET `graFinal` = 60 WHERE `graFinal` < 60;";
            sql4 = "";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql1);
            prep.execute();
            prep = Environment.getConnection().prepareStatement(sql2);
            prep.execute();
            prep = Environment.getConnection().prepareStatement(sql3);
            prep.execute();
            prep = Environment.getConnection().prepareStatement(sql4);
            prep.execute();
            prep.close();
            succesful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while cleaning final grades.";
            logger.log(Level.SEVERE, message, e);
        }
        return succesful;
    }

    public static boolean saveFinalGrades()
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Grade` SET `graFinal`= ? , `graRemark` = '' "
                         + " WHERE `graStuID`= ? AND `graSubCode` = ? AND `graTrmCode` = ?; ";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            for (int i = 0; i < graStuID.size(); i++)
            {
                prep.setString(1, graFinal.get(i));
                prep.setString(2, graStuID.get(i));
                prep.setString(3, graSubCode.get(i));
                prep.setString(4, graTrmCode.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
            updateStudentClasses();
            updateFinalGPA();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while saving final grades.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    private static void addFinalGrade(String stuID, String subCode, String TrmCode, double finalGrade)
    {
        graStuID.add(stuID);
        graSubCode.add(subCode);
        graTrmCode.add(TrmCode);
        graFinal.add(String.valueOf(finalGrade));
    }

    private static void addMidTermGrade(String stuID, String subCode, String TrmCode, double midTermGrade)
    {
        graStuID.add(stuID);
        graSubCode.add(subCode);
        graTrmCode.add(TrmCode);
        graMid.add(String.valueOf(midTermGrade));
        graFinal.add("0.0");
        graGPA.add(" ");
        graLetterGrade.add(" ");
        graRemark.add(" ");
    }

    private static void resetGrades()
    {
        graStuID = new ArrayList<String>();
        graSubCode = new ArrayList<String>();
        graTrmCode = new ArrayList<String>();
        graMid = new ArrayList<String>();
        graFinal = new ArrayList<String>();
        graGPA = new ArrayList<String>();
        graLetterGrade = new ArrayList<String>();
        graRemark = new ArrayList<String>();
    }

    private static void updateStudentClasses()
    {
        try
        {
            ArrayList<String> stuIDs = new ArrayList<String>();
            ArrayList<String> stuCls = new ArrayList<String>();
            String sql = "SELECT DISTINCT `graStuID` FROM `Grade` WHERE `graStatus` = 'Active'";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                stuIDs.add(rs.getString("graStuID"));
            }
            rs.close();
            for (String stuID : stuIDs)
            {
                stuCls.add(Student.getStudentClass(stuID));
            }
            sql = "UPDATE `Grade` SET `graClsCode`= ? "
                  + "WHERE `graStuID`= ? AND `graStatus` = 'Active';";
            prep = Environment.getConnection().prepareStatement(sql);
            for (int i = 0; i < stuIDs.size(); i++)
            {
                prep.setString(1, stuCls.get(i));
                prep.setString(2, stuIDs.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating student classes in Grades.";
            logger.log(Level.SEVERE, message, e);
        }
    }

    public static boolean updateRemarks(ArrayList<String> graID, ArrayList<String> remarks)
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Grade` SET `graRemark`= ? WHERE `graID`= ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            for (int i = 0; i < graID.size(); i++)
            {
                prep.setString(1, remarks.get(i));
                prep.setString(2, graID.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating mid term remarks.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean closeAssessments()
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Assments` SET `assmtStatus` = 'Closed' "
                         + " WHERE `assmtStatus` = 'Active'";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while closing the assessments.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean closeGrades()
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Grade` SET `graStatus` = 'Closed' "
                         + " WHERE `graStatus` = 'Active'";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while closing the assessments.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static void updateFinalGPA()
    {
        try
        {
            ArrayList<String> gradeIDs = new ArrayList<String>();
            ArrayList<Double> grades = new ArrayList<Double>();
            ArrayList<String> GPAs = new ArrayList<String>();
            ArrayList<String> letters = new ArrayList<String>();
            ArrayList<String> remarks = new ArrayList<String>();
            String sql = "SELECT `graID`, `graFinal`  FROM `Grade` WHERE `graStatus` = 'Active'";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            System.out.println("Retreiving Results");
            while (rs.next())
            {
                gradeIDs.add(rs.getString("graID"));
                grades.add(rs.getDouble("graFinal"));
            }
            rs.close();
            int counter = 0;
            for (double grade : grades)
            {
                counter++;
                System.out.println("Getting GPAs: " + counter + " of " + grades.size());
                ArrayList<String> gpa = getGPA(grade);
                GPAs.add(gpa.get(0));
                letters.add(gpa.get(1));
                remarks.add(gpa.get(2));
            }
            System.out.println("Creating commit query");
            sql = "UPDATE `Grade` SET `graGPA` = ?, `graLetterGrade` = ?, `graRemark` = ?"
                  + " WHERE `graID`= ?;";
            prep = Environment.getConnection().prepareStatement(sql);
            for (int i = 0; i < gradeIDs.size(); i++)
            {
                System.out.println("Adding " + i + " of " + gradeIDs.size() + "to batch");
                prep.setString(1, GPAs.get(i));
                prep.setString(2, letters.get(i));
                prep.setString(3, remarks.get(i));
                prep.setString(4, gradeIDs.get(i));
                prep.addBatch();
            }
            System.out.println("Executing batch");
            prep.executeBatch();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating GPA and Letter grades.";
            logger.log(Level.SEVERE, message, e);
        }
    }

    public static void updateMidTermGPA()
    {
        try
        {
            ArrayList<String> gradeIDs = new ArrayList<String>();
            ArrayList<Double> grades = new ArrayList<Double>();
            ArrayList<String> GPAs = new ArrayList<String>();
            ArrayList<String> letters = new ArrayList<String>();
            ArrayList<String> remarks = new ArrayList<String>();
            String sql = "SELECT `graID`, `graMid` FROM `Grade` WHERE `graStatus` = 'Active'";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                gradeIDs.add(rs.getString("graID"));
                grades.add(rs.getDouble("graMid"));
            }
            rs.close();
            for (double grade : grades)
            {
                ArrayList<String> gpa = getGPA(grade);
                GPAs.add(gpa.get(0));
                letters.add(gpa.get(1));
                remarks.add(gpa.get(2));
            }
            sql = "UPDATE `Grade` SET `graGPA` = ?, `graLetterGrade` = ?, `graRemark` = ?"
                  + " WHERE `graID`= ?;";
            prep = Environment.getConnection().prepareStatement(sql);
            for (int i = 0; i < gradeIDs.size(); i++)
            {
                prep.setString(1, GPAs.get(i));
                prep.setString(2, letters.get(i));
                prep.setString(3, remarks.get(i));
                prep.setString(4, gradeIDs.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating GPA and Letter grades.";
            logger.log(Level.SEVERE, message, e);
        }
    }

    private static ArrayList<String> getGPA(double grade)
    {
        ArrayList<String> gpa = new ArrayList<String>();
        try
        {
            String sql = "SELECT `gradeLetter`,`gpa`,`remark`  FROM `GPA_Lookup` WHERE `gradeMin` <= ? AND `gradeNext` > ?";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setDouble(1, grade);
            prep.setDouble(2, grade);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                gpa.add(rs.getString("gpa"));//0
                gpa.add(rs.getString("gradeLetter"));//1
                gpa.add(rs.getString("remark"));//2
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while looking up the GPA and Letter grades.";
            logger.log(Level.SEVERE, message, e);
        }
        return gpa;
    }

    public static boolean disableGrade(String assmtID)
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Assments` SET `assmtStatus` = 'Inactive' WHERE `assmtID` = ?;",
                   sql2 = "UPDATE `TermGrade` SET `grdStatus` = 'Inactive' WHERE `grdAssmtID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, assmtID);
            prep.execute();
            prep = Environment.getConnection().prepareStatement(sql2);
            prep.setString(1, assmtID);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while looking up the GPA and Letter grades.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static double calculateGradeWithWeighting(String stuID, String subCode)
    {
        double grade = 0;
        try
        {
            ArrayList<String> assmtWeightType = new ArrayList<String>();
            ArrayList<Integer> assmtWeight = new ArrayList<Integer>();
            //
            //Get the assignment types and weightings.
            String sql = " SELECT `assmtType`, `weighting` FROM `Subject_Weightings`  INNER JOIN `listAssessmentTypes` ON `listAssessmentTypes`.`id` = `Subject_Weightings` .`assmentTypeID` WHERE `subID` = ? ORDER BY `weighting` DESC;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, Subject.getSubjectID(subCode));
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                assmtWeightType.add(rs.getString("assmtType"));
                assmtWeight.add(rs.getInt("weighting"));
            }
            for (int i = 0; i < assmtWeightType.size(); i++)
            {
                double assGrade = getAssessmentTypeAverage(stuID, subCode, assmtWeightType.get(i));
                grade = grade + ((assGrade * assmtWeight.get(i)) / 100);
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while calculating the grades that have weightings.";
            logger.log(Level.SEVERE, message, e);
        }
        return grade;
    }

    public static double calculateGradeWithoutWeighting(String stuID, String subCode)
    {
        double grade = 0;
        try
        {
            String sql = " SELECT `grdStuID`, ((SUM(`grdPointsEarned`)/ SUM(`Assments`.assmtTotalPoints)) * 100) AS 'Grade' "
                         + " FROM `TermGrade` "
                         + " INNER JOIN `Assments` ON `Assments`.`assmtID` = `TermGrade`.`grdAssmtID` "
                         + " WHERE `grdStatus` = 'Active' AND `grdPointsEarned` != 'Excused' "
                         + " AND `grdStuID` = ? AND `assmtSubject` = ? "
                         + " GROUP BY `grdStuID`;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuID);
            prep.setString(2, subCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                grade = rs.getDouble("Grade");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while calculating the grade for a student.";
            logger.log(Level.SEVERE, message, e);
        }
        return grade;
    }

    private static double getTermGrade(String stuID, String subCode)
    {
        double termGrade = 0;
        try
        {
            String sql = " SELECT `stuClsCode`, CONCAT_WS(', ',`stuLastName`,`stuFirstName`) AS 'Student',`subName`, ((SUM(`grdPointsEarned`)/ SUM(`Assments`.assmtTotalPoints)) * 100) AS 'Grade' "
                         + " FROM `TermGrade` "
                         + " INNER JOIN `Assments` ON `Assments`.`assmtID` = `TermGrade`.`grdAssmtID` "
                         + " INNER JOIN `Student` ON `Student`.`stuID` = `TermGrade`.`grdStuID` "
                         + " INNER JOIN `Subject` ON `Subject`.`subCode` = `Assments`.`assmtSubject` "
                         + " WHERE `grdStatus` = 'Active' AND `grdPointsEarned` != 'Excused' AND `assmtType` != 'Exam' AND `stuID` = ? and `subCode` = ? "
                         + " GROUP BY `grdStuID`, `subCode` "
                         + " ORDER BY `stuClsCode`, CONCAT_WS(', ',`stuLastName`,`stuFirstName`), `subName` ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuID);
            prep.setString(2, subCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                termGrade = rs.getDouble("Grade");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while calculating the term grade for a student's subject.";
            logger.log(Level.SEVERE, message, e);
        }
        return termGrade;
    }

    private static double getExamGrade(String stuID, String subCode)
    {
        double examGrade = 0;
        try
        {
            String sql = " SELECT `stuClsCode`, CONCAT_WS(', ',`stuLastName`,`stuFirstName`) AS 'Student',`subName`, ((SUM(`grdPointsEarned`)/ SUM(`Assments`.assmtTotalPoints)) * 100) AS 'Grade' "
                         + " FROM `TermGrade` "
                         + " INNER JOIN `Assments` ON `Assments`.`assmtID` = `TermGrade`.`grdAssmtID` "
                         + " INNER JOIN `Student` ON `Student`.`stuID` = `TermGrade`.`grdStuID` "
                         + " INNER JOIN `Subject` ON `Subject`.`subCode` = `Assments`.`assmtSubject` "
                         + " WHERE `grdStatus` = 'Active' AND `grdPointsEarned` != 'Excused' AND `assmtType` = 'Exam' AND `stuID` = ? and `subCode` = ? "
                         + " GROUP BY `grdStuID`, `subCode` "
                         + " ORDER BY `stuClsCode`, CONCAT_WS(', ',`stuLastName`,`stuFirstName`), `subName` ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuID);
            prep.setString(2, subCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                examGrade = rs.getDouble("Grade");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while calculating the exam grade for a student's subject.";
            logger.log(Level.SEVERE, message, e);
        }
        return examGrade;
    }

    public static int getAssessmentPassCount(String assmtID)
    {
        int passCount = 0;
        try
        {
            double passMark = (School.getPassingMark() / 100);
            //get the general info about the assessment
            String sql = "SELECT COUNT(`grdStuID`) AS 'Pass' "
                         + " FROM `TermGrade` "
                         + " INNER JOIN `Assments` ON `TermGrade`.`grdAssmtID` = `Assments`.`assmtID`"
                         + " WHERE (`grdPointsEarned` >= (`assmtTotalPoints` * ?)) AND `assmtID` = ? ";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setDouble(1, passMark);
            prep.setString(2, assmtID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                passCount = rs.getInt("Pass");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while gathering the pass/fail info for an assessment.";
            logger.log(Level.SEVERE, message, e);
        }
        return passCount;
    }

    public static boolean checkStudentGrade(String stuID, String subID)
    {
        boolean gradeEntered = false;
        try
        {
            int count = 0;
            String sql = "SELECT COUNT(`graID`) as 'Count' FROM `Grade` WHERE `graStuID` = ? AND `graSubCode` = ? AND `graStatus` = 'Active'";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuID);
            prep.setString(2, subID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                count = rs.getInt("Count");
            }
            rs.close();
            prep.close();
            if (count > 0)
            {
                gradeEntered = true;
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while checking if the student has a grade entered.";
            logger.log(Level.SEVERE, message, e);
        }
        return gradeEntered;
    }

    public static double getAssessmentTypeAverage(String stuID, String subCode, String assessmentType)
    {
        double average = 0;
        try
        {
            String sql = " SELECT  ((SUM(`grdPointsEarned`)/ SUM(`Assments`.assmtTotalPoints)) * 100) AS 'Grade' "
                         + " FROM `TermGrade` "
                         + " INNER JOIN `Assments` ON `Assments`.`assmtID` = `TermGrade`.`grdAssmtID` "
                         + " INNER JOIN `Student` ON `Student`.`stuID` = `TermGrade`.`grdStuID` "
                         + " INNER JOIN `Subject` ON `Subject`.`subCode` = `Assments`.`assmtSubject` "
                         + " WHERE `grdStatus` = 'Active' AND `grdPointsEarned` != 'Excused'  AND `stuID` = ? and `subCode` = ? AND `assmtType` = ?"
                         + " GROUP BY `grdStuID`, `subCode` ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuID);
            prep.setString(2, subCode);
            prep.setString(3, assessmentType);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                average = rs.getDouble("Grade");
            }
            //System.out.println("student # "+stuID +" - "+ subCode+" "+average);
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the assessment type average.";
            logger.log(Level.SEVERE, message, e);
        }
        return average;
    }

    public static void calculateFinalAverage()
    {
        try
        {
            ArrayList<String> students = new ArrayList<String>();
            ArrayList<String> classCodes = new ArrayList<String>();
            ArrayList<Double> grades = new ArrayList<Double>();
            ArrayList<Double> gpas = new ArrayList<Double>();
            String sql = "SELECT `graStuID`, `graClsCode`,`graTrmCode`, SUM(`subCredits`) as 'credits', SUM(`graFinal`*`subCredits`) AS 'points_earned' , (SUM(`graFinal`*`subCredits`) / SUM(`subCredits`)) as 'grade'  , (SUM(`graGPA`*`subCredits`) / SUM(`subCredits`)) as 'GPA' "
                         + " FROM `Grade` "
                         + " INNER JOIN `Subject` ON `Subject`.`subCode` = `Grade`.graSubCode "
                         + " WHERE  `graSubCode` NOT LIKE '%ESPART%' "
                         + " GROUP BY `graStuID`, `graClsCode`,`graTrmCode`;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                students.add(rs.getString("graStuID"));
                classCodes.add(rs.getString("graClsCode"));
                grades.add(rs.getDouble("grade"));
                gpas.add(rs.getDouble("GPA"));
            }
            //Save the grades to a table
            sql = "UPDATE `Grade_Average`  "
                  + " SET `graAvgClsCode` = ?, `graAvgFinal` = ? , graAvgGPA = ?"
                  + " WHERE `graAvgStuID` = ?  ";
            prep = Environment.getConnection().prepareStatement(sql);
            for (int i = 0; i < students.size(); i++)
            {
                prep.setString(1, classCodes.get(i));
                prep.setDouble(2, grades.get(i));
                prep.setDouble(3, gpas.get(i));
                prep.setString(4, students.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while calculating final grades.";
            logger.log(Level.SEVERE, message, e);
        }
    }

    public static void calculateMidAverage()
    {
        try
        {
            ArrayList<String> students = new ArrayList<String>();
            ArrayList<String> classCodes = new ArrayList<String>();
            ArrayList<Double> grades = new ArrayList<Double>();
            ArrayList<Double> gpas = new ArrayList<Double>();
            String currentTerm = Term.getCurrentTerm();
            String sql = "SELECT `graStuID`, `graClsCode`,`graTrmCode`, SUM(`subCredits`) as 'credits', SUM(`graMid`*`subCredits`) AS 'points_earned' , (SUM(`graMid`*`subCredits`) / SUM(`subCredits`)) as 'grade' , (SUM(`graGPA`*`subCredits`) / SUM(`subCredits`)) as 'GPA' "
                         + " FROM `Grade` "
                         + " INNER JOIN `Subject` ON `Subject`.`subCode` = `Grade`.graSubCode "
                         + " WHERE  `graSubCode` NOT LIKE '%ESPART%' "
                         + " GROUP BY `graStuID`, `graClsCode`,`graTrmCode`;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                students.add(rs.getString("graStuID"));
                classCodes.add(rs.getString("graClsCode"));
                grades.add(rs.getDouble("grade"));
                gpas.add(rs.getDouble("GPA"));
            }
            //Save the grades to a table
            sql = "INSERT INTO `Grade_Average` (`graAvgStuID`, `graAvgClsCode`, `graAvgMid`, `graAvgTerm`, `graAvgGPA`) VALUES (?, ?, ?, ?, ?);";
            prep = Environment.getConnection().prepareStatement(sql);
            for (int i = 0; i < students.size(); i++)
            {
                prep.setString(1, students.get(i));
                prep.setString(2, classCodes.get(i));
                prep.setDouble(3, grades.get(i));
                prep.setString(4, currentTerm);
                prep.setDouble(5, gpas.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while calculating final grades.";
            logger.log(Level.SEVERE, message, e);
        }
    }
}
