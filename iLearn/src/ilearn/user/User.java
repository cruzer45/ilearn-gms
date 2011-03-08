/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.user;

import ilearn.kernel.EncryptionHandler;
import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mrogers
 */
public class User
{

    private static int loginCount = 0;

    public static boolean logIn(String username, String password)
    {
        String storedPassword = "";
        String encryptedPassword = EncryptionHandler.encryptPassword(password);
        boolean successful = false;

        try
        {
            String sql = "SELECT `usrName`, `usrPassword`, `usrGroup`, `usrLevel`, `usrStatus` FROM `iLearn`.`User` WHERE `usrName` = ? AND `usrStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, username);
            ResultSet rs = prep.executeQuery();

            while (rs.next())
            {
                storedPassword = rs.getString("usrPassword");
            }
            rs.close();
            prep.close();

            if (storedPassword.equals(encryptedPassword))
            {
                successful = true;
            }
            else
            {
                loginCount++;
                successful = false;
            }
        }
        catch (Exception e)
        {
            String message = "ERROR: Could not validate user information.";
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, message, e);
            message = "An error occurred while validating the login information.\n"
                    + "Kindly consult your system administrator.";
            Utilities.showErrorMessage(null, message);
        }

        if (loginCount >= 3)
        {
            String message = "You have exceeded the number of failed login attempts.\n"
                    + "The program will now exit.";
            Utilities.showErrorMessage(null, message);
            ilearn.ILearnApp.getApplication().exit();
        }
        return successful;
    }

    public static boolean addUser(String username, String password, String firstName, String lastName, String group, String level)
    {
        boolean successful = false;

        try
        {
            String sql = "INSERT INTO `iLearn`.`User` (`usrFirstName`, `usrLastName`, `usrName`, `usrPassword`, `usrGroup`, `usrLevel`) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, firstName);
            prep.setString(2, lastName);
            prep.setString(3, username);
            prep.setString(4, EncryptionHandler.encryptPassword(password));
            prep.setString(5, group);
            prep.setString(6, level);
            prep.execute();
            prep.close();

            successful = true;
        }
        catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex)
        {
            String message = "An error occurred while adding a user to the database.\n"
                    + "This username already exists.";
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, message, ex);
            successful = false;
            Utilities.showErrorMessage(null, message);
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a user to the database.";
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, message, e);
            successful = false;
        }
        return successful;
    }

    public static DefaultTableModel getUserList()
    {
        ArrayList<String> id = new ArrayList<String>();
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<String> username = new ArrayList<String>();
        ArrayList<String> status = new ArrayList<String>();

        try
        {
            String sql = "SELECT `usrID`, CONCAT(`usrFirstName`, ' ',`usrLastName` ) as name, `usrName`, usrStatus FROM `User` ORDER BY `usrID` ASC;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();

            while (rs.next())
            {
                id.add(rs.getString("usrID"));
                name.add(rs.getString("name"));
                username.add(rs.getString("usrName"));
                status.add(rs.getString("usrStatus"));
            }

            rs.close();
            prep.close();
        }
        catch (SQLException sQLException)
        {
            String message = "An error occurred while getting the user list.";
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, message, sQLException);
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID", id.toArray());
        model.addColumn("Name", name.toArray());
        model.addColumn("User Name", username.toArray());
        model.addColumn("Status", status.toArray());

        return model;
    }

    public static String[] getUserInfo(String ID)
    {
        String sql = "SELECT * FROM `User` WHERE `usrID` = ?;";
        String firstName = "", lastName = "", username = "", password = "", level = "", group = "", status = "";
        try
        {
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, ID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                firstName = rs.getString("usrFirstName");
                lastName = rs.getString("usrLastName");
                username = rs.getString("usrName");
                password = rs.getString("usrPassword");
                level = rs.getString("usrLevel");
                group = rs.getString("usrGroup");
                status = rs.getString("usrStatus");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the user list.";
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, message, e);
        }
        String[] results =
        {
            firstName, lastName, username, password, level, group, status
        };
        return results;
    }

    public static boolean updateUser(String ID, String firstName, String lastName, String userName, String password, String group, String level, String status)
    {
        boolean successful = false;
        String sql = "UPDATE `User` SET `usrFirstName`= ?, `usrLastName`= ?, `usrName`= ?, `usrPassword`= ?, `usrGroup`= ?, `usrLevel`= ? , `usrStatus`= ?  WHERE `usrID`= ? LIMIT 1;";

        String[] currentInfo = getUserInfo(ID);
        if (!currentInfo[3].equals(password))
        {
            password = EncryptionHandler.encryptPassword(password);
        }

        try
        {
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, firstName);
            prep.setString(2, lastName);
            prep.setString(3, userName);
            prep.setString(4, password);
            prep.setString(5, group);
            prep.setString(6, level);
            prep.setString(7, status);
            prep.setString(8, ID);
            prep.executeUpdate();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the user's information.";
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, message, e);
        }

        return successful;
    }
}
