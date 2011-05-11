/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.assessments;

import ilearn.kernel.Environment;
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
public class Assessment
{

    static final Logger logger = Logger.getLogger(Assessment.class.getName());
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

    public static DefaultTableModel getStudentList(String classCode)
    {
        DefaultTableModel model = new DefaultTableModel()
        {

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
                //grade.add("0");
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
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding an assessment.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean addGrades(String assmtID, ArrayList<String> stuID, ArrayList<String> grade, ArrayList<String> remarks)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `termgrade` (`grdStuID`, `grdAssmtID`, `grdPointsEarned`, `grdRemark`) VALUES (?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);

            for (int i = 0; i < stuID.size(); i++)
            {
                prep.setString(1, stuID.get(i));
                prep.setString(2, assmtID);
                prep.setString(3, grade.get(i));
                prep.setString(4, remarks.get(i));
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

    public static String getAssmtID(String assmtTerm, String assmtSubject, String assmtTeacher, String assmtTitle, String assmtDate, String assmtType, String assmtTotalPoints, String assmtClassID)
    {
        String assmtID = "";
        try
        {
            String sql = "SELECT `assmtID` FROM `ilearn`.`assments` "
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
}
