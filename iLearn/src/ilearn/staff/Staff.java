package ilearn.staff;

import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author m.rogers
 */
public class Staff
{

    static final Logger logger = Logger.getLogger(Staff.class.getName());

    public static boolean addStaff(String code, String firstName, String lastName, String gender, String DOB, String notes, String email)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `Staff` (`staCode`, `staFirstName`, `staLastName`, `staGender`, `staDOB`, `staNotes`, `staEmail`) VALUES (?,?, ?, ?,?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, code);
            prep.setString(2, firstName);
            prep.setString(3, lastName);
            prep.setString(4, gender);
            prep.setString(5, DOB);
            prep.setString(6, notes);
            prep.setString(7, email);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding the staff to the system.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static DefaultTableModel getStaffListTableModel()
    {
        DefaultTableModel model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }
        };
        ArrayList<String> id = new ArrayList<String>();
        ArrayList<String> code = new ArrayList<String>();
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<String> Status = new ArrayList<String>();
        try
        {
            String sql = "SELECT `staID`, `staCode`, `staFirstName`, `staLastName`,`staDOB`, `staStatus` FROM `Staff`;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                id.add(rs.getString("staID"));
                code.add(rs.getString("staCode"));
                name.add(rs.getString("staFirstName") + " " + rs.getString("staLastName"));
                Status.add(rs.getString("staStatus"));
            }
            rs.close();
            prep.close();
            model.addColumn("ID", id.toArray());
            model.addColumn("Name", name.toArray());
            model.addColumn("Status", Status.toArray());
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the staff list table model.";
            logger.log(Level.SEVERE, message, e);
        }
        return model;
    }

    public static DefaultTableModel searchStaffList(String criteria)
    {
        criteria = Utilities.percent(criteria);
        DefaultTableModel model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }
        };
        ArrayList<String> id = new ArrayList<String>();
        ArrayList<String> code = new ArrayList<String>();
        ArrayList<String> name = new ArrayList<String>();
        try
        {
            String sql = "SELECT `staID`, `staCode`, `staFirstName`, `staLastName`,`staDOB` "
                         + "FROM `Staff` "
                         + "WHERE (`staID` LIKE ? OR `staCode` LIKE ? OR `staFirstName` LIKE ? OR `staLastName` LIKE ?) AND `staStatus` =  'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, criteria);
            prep.setString(2, criteria);
            prep.setString(3, criteria);
            prep.setString(4, criteria);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                id.add(rs.getString("staID"));
                code.add(rs.getString("staCode"));
                name.add(rs.getString("staFirstName") + " " + rs.getString("staLastName"));
            }
            rs.close();
            prep.close();
            model.addColumn("ID", id.toArray());
            model.addColumn("Code", code.toArray());
            model.addColumn("Name", name.toArray());
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the staff list table model.";
            logger.log(Level.SEVERE, message, e);
        }
        return model;
    }

    public static ArrayList<String> getStaffDetails(String id)
    {
        ArrayList<String> details = new ArrayList<String>();
        try
        {
            String sql = "SELECT `staID`, `staCode`, `staFirstName`, `staLastName`, `staGender`, `staDOB`, `staNotes`, `staEmail`, `staStatus` FROM `Staff` WHERE `staID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, id);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                details.add(rs.getString("staID"));//0
                details.add(rs.getString("staCode"));//1
                details.add(rs.getString("staFirstName"));//2
                details.add(rs.getString("staLastName"));//3
                details.add(rs.getString("staGender"));//4
                details.add(rs.getString("staDOB"));//5
                details.add(rs.getString("staNotes"));//6
                details.add(rs.getString("staEmail"));//7
                details.add(rs.getString("staStatus"));//8
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the staff details.";
            logger.log(Level.SEVERE, message, e);
        }
        return details;
    }

    public static boolean updateStaffDetails(String id, String code, String firstName, String lastName, String gender, String DOB, String notes, String email, String status)
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Staff` SET `staCode`= ?, `staFirstName`= ?, `staLastName`= ?, `staGender`= ?, `staDOB`= ?, `staNotes`= ?, `staEmail`= ?, `staStatus`= ? WHERE `staID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, code);
            prep.setString(2, firstName);
            prep.setString(3, lastName);
            prep.setString(4, gender);
            prep.setString(5, DOB);
            prep.setString(6, notes);
            prep.setString(7, email);
            prep.setString(8, status);
            prep.setString(9, id);
            prep.executeUpdate();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the staff details.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    /**
     * Returns a list of the staff members names.
     * @return
     */
    public static ArrayList<String> getStaffList()
    {
        ArrayList<String> staffList = new ArrayList<String>();
        try
        {
            String sql = "SELECT  CONCAT_WS( ' ', `staFirstName`, `staLastName`) AS Name  FROM `Staff` WHERE `staStatus` = 'Active' ORDER BY Name Asc;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                staffList.add(rs.getString("Name"));
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the staff list.";
            logger.log(Level.SEVERE, message, e);
        }
        return staffList;
    }

    public static String getStaffCodeFromName(String teacherName)
    {
        String name = "";
        try
        {
            String sql = "SELECT `staCode` FROM Staff WHERE CONCAT_WS( ' ', `staFirstName`, `staLastName`) = ?";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, teacherName);
            ResultSet rs = prep.executeQuery();
            rs.first();
            name = rs.getString("staCode");
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the staff code.";
            logger.log(Level.SEVERE, message, e);
        }
        return name;
    }

    public static String getStaffName(String staCode)
    {
        String name = "";
        try
        {
            String sql = "SELECT CONCAT_WS( ' ', `staFirstName`, `staLastName`) as Name FROM Staff WHERE `staCode` = ?";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, staCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                name = rs.getString("Name");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the staff code.";
            logger.log(Level.SEVERE, message, e);
        }
        return name;
    }

    public static String getStaffID(String staCode)
    {
        String staID = "";
        try
        {
            String sql = "SELECT `staID` FROM Staff WHERE `staCode` = ?";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, staCode);
            ResultSet rs = prep.executeQuery();
            rs.first();
            staID = rs.getString("staID");
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the staff ID.";
            logger.log(Level.SEVERE, message, e);
        }
        return staID;
    }

    public static String getStaffCodeFromID(String staID)
    {
        String staCode = "";
        try
        {
            String sql = "SELECT `staCode` FROM Staff WHERE `staID` = ?";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, staID);
            ResultSet rs = prep.executeQuery();
            rs.first();
            staCode = rs.getString("staCode");
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the staff ID.";
            logger.log(Level.SEVERE, message, e);
        }
        return staCode;
    }

    public static ArrayList<String> getStaffSubjectsID(String staffCode)
    {
        ArrayList<String> staffSubjects = new ArrayList<String>();
        try
        {
            String sql = "SELECT `subID` "
                         + " FROM `Subject` "
                         + "WHERE `subStaffCode` = ? AND `subStatus` = 'Active'";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, staffCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                staffSubjects.add(rs.getString("subID"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the subjects a staff member teaches.";
            logger.log(Level.SEVERE, message, e);
        }
        return staffSubjects;
    }
    
    public static ArrayList<HashMap> getStaffSubjectList(String staffCode)
    {
        ArrayList<HashMap> subjectList = new ArrayList<HashMap>();

        try
        {
            String sql = "SELECT `clsID`,`Class`.`clsCode` as 'class',`subID`, `Subject`.`subCode` as 'subject' , subStaffCode "
                    + " FROM `Class` "
                    + " INNER JOIN `ClassSubjects` ON `Class`.`clsCode` = `ClassSubjects`.`clsCode` "
                    + " INNER JOIN `Subject` ON `Subject`.subID = `ClassSubjects`.`subCode` "
                    + " WHERE `Subject`.`subStaffCode` = ? ";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, staffCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                HashMap<String, String> classDetails = new HashMap<String, String>();
                classDetails.put("classID", rs.getString("clsID"));
                classDetails.put("classCode", rs.getString("class"));
                classDetails.put("subID", rs.getString("subID"));
                classDetails.put("subCode", rs.getString("subject"));
                classDetails.put("staffCode", rs.getString("subStaffCode"));
                subjectList.add(classDetails);
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
             String message = "An error occurred while retrieving the class a subject is assigned to.";
            logger.log(Level.SEVERE, message, e);
        }
        return subjectList;

    }
    
}
