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
        "Absent", "Excused"
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
                if (mColIndex == 3)
                {
                    editable = true;
                }
                return editable;

                //return false;
            }

            @Override
            public void setValueAt(Object value, int row, int column)
            {
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
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the list of students for a class.";
            logger.log(Level.SEVERE, message, e);
        }
        return model;
    }

    public static boolean addAssessment(String assmtTerm, String assmtSubject, String assmtTeacher, String assmtTitle, String assmtType, String assmtTotalPoints)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `Assments` (`assmtTerm`, `assmtSubject`, `assmtTeacher`, `assmtTitle`, `assmtType`, `assmtTotalPoints` ) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, assmtTerm);
            prep.setString(2, assmtSubject);
            prep.setString(3, assmtTeacher);
            prep.setString(4, assmtTitle);
            prep.setString(5, assmtType);
            prep.setString(6, assmtTotalPoints);
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
}
