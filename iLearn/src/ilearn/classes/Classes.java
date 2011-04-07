/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.classes;

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
public class Classes
{

    static final Logger logger = Logger.getLogger(Classes.class.getName());

    public static boolean addClass(String code, String level, String name, String description, String homeRoom)
    {
        boolean successful = false;

        if (homeRoom.equals("--- Select One ---")
                || code.isEmpty()
                || name.isEmpty())
        {
            return successful;
        }

        try
        {
            String sql = "INSERT INTO `Class` (`clsCode`, `clsName`, `clsDescription`, `clsLevel`, `clsHomeRoom`) VALUES (?, ?, ?,?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, code);
            prep.setString(2, name);
            prep.setString(3, description);
            prep.setString(4, level);
            prep.setString(5, homeRoom);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a class to the system.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static DefaultTableModel getClassTableModel()
    {
        DefaultTableModel model = new DefaultTableModel();
        ArrayList<String> ID = new ArrayList<String>();
        ArrayList<String> code = new ArrayList<String>();
        ArrayList<String> Name = new ArrayList<String>();
        ArrayList<String> HomeRoom = new ArrayList<String>();
        ArrayList<String> Status = new ArrayList<String>();

        try
        {
            String sql = "SELECT `clsID`, `clsCode`, `clsName`,`clsHomeRoom`, `clsStatus` FROM `iLearn`.`Class` ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();

            while (rs.next())
            {
                ID.add(rs.getString("clsID"));
                code.add(rs.getString("clsCode"));
                Name.add(rs.getString("clsName"));
                HomeRoom.add(rs.getString("clsHomeRoom"));
                Status.add(rs.getString("clsStatus"));
            }

            rs.close();
            prep.close();

            model.addColumn("ID", ID.toArray());
            model.addColumn("Code", code.toArray());
            model.addColumn("Name", Name.toArray());
            model.addColumn("Home Room", HomeRoom.toArray());
            model.addColumn("Status", Status.toArray());

        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the class table model.";
            logger.log(Level.SEVERE, message, e);
        }
        return model;
    }

    public static ArrayList<String> getClassInfo(String classID)
    {
        ArrayList<String> info = new ArrayList<String>();

        try
        {
            String sql = "SELECT `clsID`, `clsCode`, `clsName`, `clsDescription`, `clsLevel`, `clsHomeRoom`, `clsStatus` FROM `iLearn`.`Class` WHERE `clsID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, classID);
            ResultSet rs = prep.executeQuery();

            while (rs.next())
            {
                info.add(rs.getString("clsCode"));
                info.add(rs.getString("clsName"));
                info.add(rs.getString("clsDescription"));
                info.add(rs.getString("clsLevel"));
                info.add(rs.getString("clsHomeRoom"));
                info.add(rs.getString("clsStatus"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving the class information.";
            logger.log(Level.SEVERE, message, e);
        }
        return info;
    }

    public static boolean updateClass(String code, String name, String description, String level, String homeRoom, String status, String id)
    {
        boolean successful = false;

        try
        {
            String sql = "UPDATE `Class` SET `clsCode`= ?, `clsName`= ?, `clsDescription`= ?, `clsLevel`= ?, `clsHomeRoom`= ?, `clsStatus`= ? WHERE `clsID`= ?  LIMIT 1;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, code);
            prep.setString(2, name);
            prep.setString(3, description);
            prep.setString(4, level);
            prep.setString(5, homeRoom);
            prep.setString(6, status);
            prep.setString(7, id);
            prep.executeUpdate();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the class information.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static ArrayList<String> getClassList()
    {
        ArrayList<String> classes = new ArrayList<String>();

        try
        {
            String sql = "SELECT `clsName` FROM `iLearn`.`Class` WHERE `clsStatus` = 'Active' ORDER BY `clsName` ASC ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();

            while (rs.next())
            {
                classes.add(rs.getString("clsName"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the list of classes.";
            logger.log(Level.SEVERE, message, e);
        }

        return classes;
    }
}
