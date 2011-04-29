/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.staff;

import ilearn.kernel.Environment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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
        ArrayList<Date> DOB = new ArrayList<Date>();
        ArrayList<String> Status = new ArrayList<String>();
        try
        {
            String sql = "SELECT `staID`, `staCode`, `staFirstName`, `staLastName`,`staDOB`, `staStatus` FROM `iLearn`.`Staff`;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                id.add(rs.getString("staID"));
                code.add(rs.getString("staCode"));
                name.add(rs.getString("staFirstName") + " " + rs.getString("staLastName"));
                DOB.add(rs.getDate("staDOB"));
                Status.add(rs.getString("staStatus"));
            }
            rs.close();
            prep.close();
            model.addColumn("ID", id.toArray());
            //model.addColumn("Code", code.toArray());
            model.addColumn("Name", name.toArray());
            //model.addColumn("DOB", DOB.toArray());
            model.addColumn("Status", Status.toArray());
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
            String sql = "SELECT `staID`, `staCode`, `staFirstName`, `staLastName`, `staGender`, `staDOB`, `staNotes`, `staEmail`, `staStatus` FROM `iLearn`.`Staff` WHERE `staID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, id);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                details.add(rs.getString("staID"));
                details.add(rs.getString("staCode"));
                details.add(rs.getString("staFirstName"));
                details.add(rs.getString("staLastName"));
                details.add(rs.getString("staGender"));
                details.add(rs.getString("staDOB"));
                details.add(rs.getString("staNotes"));
                details.add(rs.getString("staEmail"));
                details.add(rs.getString("staStatus"));
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

    public static ArrayList<String> getStaffList()
    {
        ArrayList<String> staffList = new ArrayList<String>();
        try
        {
            String sql = "SELECT  CONCAT_WS( ' ', `staFirstName`, `staLastName`) AS Name  FROM `iLearn`.`Staff` WHERE `staStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                staffList.add(rs.getString("Name") );
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the staff list.";
            logger.log(Level.SEVERE, message, e);
        }
        return staffList;
    }

    public static String getStaffCode(String teacherName)
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
            rs.first();
            name = rs.getString("Name");
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
}
