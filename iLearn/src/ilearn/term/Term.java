package ilearn.term;

import ilearn.kernel.Environment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mrogers
 */
public class Term
{

    static final Logger logger = Logger.getLogger(Term.class.getName());

    /**
     * This function tries to add a term to the system.
     * @param trmCode - The short code for the term.
     * @param trmShortName - The short name for the term.
     * @param trmLongName - The long name for the term.
     * @return A boolean indicating if it was successful or not.
     */
    public static boolean addTerm(String trmCode, String trmShortName, String trmLongName)
    {
        boolean successful = false;
        String sql = "INSERT INTO `Term` (`trmCode`, `trmShortName`, `trmLongName`) VALUES (?, ?, ?);";
        try
        {
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, trmCode);
            prep.setString(2, trmShortName);
            prep.setString(3, trmLongName);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a term to the database.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static DefaultTableModel getTermListTableModel()
    {
        DefaultTableModel model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }
        };
        ArrayList<String> ID = new ArrayList<String>();
        ArrayList<String> trmCodes = new ArrayList<String>();
        ArrayList<String> shortNames = new ArrayList<String>();
        ArrayList<String> longNames = new ArrayList<String>();
        ArrayList<String> status = new ArrayList<String>();
        try
        {
            String sql = "SELECT `trmID`, `trmCode`, `trmShortName`, `trmLongName`, `trmStatus` FROM `Term` ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                ID.add(rs.getString("trmID"));
                trmCodes.add(rs.getString("trmCode"));
                shortNames.add(rs.getString("trmShortName"));
                longNames.add(rs.getString("trmLongName"));
                status.add(rs.getString("trmStatus"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the term list.";
            logger.log(Level.SEVERE, message, e);
        }
        model.addColumn("ID", ID.toArray());
        model.addColumn("Term Code", trmCodes.toArray());
        model.addColumn("Short Name", shortNames.toArray());
        model.addColumn("Long Name", longNames.toArray());
        model.addColumn("Status", status.toArray());
        return model;
    }

    public static boolean updateTerm(String ID, String trmCode, String trmShortName, String trmLongName, String status)
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Term` SET `trmCode`= ?, `trmShortName`= ?, `trmLongName`= ?, `trmStatus`=? WHERE `trmID`= ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, trmCode);
            prep.setString(2, trmShortName);
            prep.setString(3, trmLongName);
            prep.setString(4, status);
            prep.setString(5, ID);
            prep.executeUpdate();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the term info.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    /**
     *
     * @Returns the ID of the current active term.
     */
    public static String getCurrentTerm()
    {
        String currentTerm = "";
        try
        {
            String sql = "SELECT `trmID` FROM `Term`  WHERE `trmStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                currentTerm = rs.getString("trmID");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the current term.";
            logger.log(Level.SEVERE, message, e);
        }
        return currentTerm;
    }

    /**
     *
     * @Returns the ID of the current active term.
     */
    public static String getCurrentTermName()
    {
        String currentTerm = "";
        try
        {
            String sql = "SELECT `trmLongName` FROM `Term`  WHERE `trmStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                currentTerm = rs.getString("trmLongName");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the current term.";
            logger.log(Level.SEVERE, message, e);
        }
        return currentTerm;
    }

    public static boolean closeTerm()
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Term` SET `trmStatus` = 'Closed' WHERE `trmStatus` = 'Active'";
            String sql2 = "UPDATE `TermGrade` SET `grdStatus` = 'Closed' WHERE `grdStatus` = 'Active'";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.execute();
            prep = Environment.getConnection().prepareStatement(sql2);
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

    public static ArrayList<String> getTermList()
    {
        ArrayList<String> list = new ArrayList<String>();
        try
        {
            String sql = "SELECT `trmShortName` FROM `Term`";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                list.add(rs.getString("trmShortName"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving the list of terms.";
            logger.log(Level.SEVERE, message, e);
        }
        return list;
    }

    public static Object[] getClassListForTerm(String classCode, String termID)
    {
        ArrayList<String> studentID = new ArrayList<String>();
        ArrayList<String> stuName = new ArrayList<String>();
        try
        {
            String sql = "SELECT DISTINCT `stuID` as 'id', CONCAT_WS(', ',`stuLastName`,`stuFirstName`) as 'name' "
                         + " FROM `Grade` "
                         + " INNER JOIN `Student` ON `Student`.`stuID` = `Grade`.`graStuID` "
                         + " WHERE `graTrmCode` = ? AND `graClsCode` = ? "
                         + " ORDER BY CONCAT_WS(', ',`stuLastName`,`stuFirstName`);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, termID);
            prep.setString(2, classCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                studentID.add(rs.getString("id"));
                stuName.add(rs.getString("name"));
            }
            prep.close();
            rs.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the list of students for a class.";
            logger.log(Level.SEVERE, message, e);
        }
        Object[] stuList = new Object[]
        {
            studentID, stuName
        };
        return stuList;
    }

    public static Object[] getSubjectsforTerm(String classCode, String termID)
    {
        ArrayList<String> subCodes = new ArrayList<String>();
        ArrayList<String> subNames = new ArrayList<String>();
        try
        {
            String sql = "SELECT DISTINCT `Grade`.`graSubCode`, `subName` "
                         + " FROM `Grade` "
                         + " INNER JOIN `Subject` ON `Subject`.`subCode` = `Grade`.`graSubCode` "
                         + " WHERE `graTrmCode` = ? AND `graClsCode` = ? ";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, termID);
            prep.setString(2, classCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                subCodes.add(rs.getString("graSubCode"));
                subNames.add(rs.getString("subName"));
            }
            prep.close();
            rs.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the list of subjects for a term.";
            logger.log(Level.SEVERE, message, e);
        }
        Object[] subList = new Object[]
        {
            subCodes, subNames
        };
        return subList;
    }

    public static String getTermIDFromShortName(String termName)
    {
        String id = "";
        try
        {
            String sql = "SELECT `trmID` FROM `Term` WHERE `trmShortName` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, termName);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                id = rs.getString("trmID");
            }
            prep.close();
            rs.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the term id for the specified term short name.";
            logger.log(Level.SEVERE, message, e);
        }
        return id;
    }

    public static double getStudentGradeforTerm(String classCode, String termID, String subCode, String stuID)
    {
        double grade = 0.0;
        try
        {
            String sql = "SELECT `graFinal` FROM `Grade` "
                         + " WHERE `graClsCode` = ? AND `graTrmCode` = ?  and `graSubCode` = ? and `graStuID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, classCode);
            prep.setString(2, termID);
            prep.setString(3, subCode);
            prep.setString(4, stuID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                grade = rs.getDouble("graFinal");
            }
            prep.close();
            rs.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting a grade for a specific term.";
            logger.log(Level.SEVERE, message, e);
        }
        return grade;
    }

    public static double getStudentAverageforTerm(String classCode, String termID,  String stuID)
    {
        double grade = 0.0;
        try
        {
            String sql = "SELECT graAvgFinal FROM Grade_Average "
                         + " WHERE `graAvgClsCode` = ? AND `graAvgTerm` = ?  and `graAvgStuID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, classCode);
            prep.setString(2, termID);
            prep.setString(3, stuID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                grade = rs.getDouble("graAvgFinal");
            }
            prep.close();
            rs.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting a grade for a specific term.";
            logger.log(Level.SEVERE, message, e);
        }
        return grade;
    }
}