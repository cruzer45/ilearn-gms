/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.user;

import ilearn.kernel.EncryptionHandler;
import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.kernel.logger.iLogger;
import java.net.Inet4Address;
import java.net.InetAddress;
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
    private static String userName = "";
    private static String userGroup = "";
    static final Logger logger = Logger.getLogger(User.class.getName());

    /**
     * This function checks to see if the given username and password matches
     * one stored in the database.
     * @param username
     * @param password
     * @return True of the username is found and the passwords match,
     *         False if it wasn't found or the passwords don't match.
     */
    public static boolean logIn(String username, String password)
    {
        String storedPassword = "";
        String encryptedPassword = EncryptionHandler.encryptPassword(password);
        boolean successful = false;
        try
        {
            String sql = "SELECT `usrName`, `usrPassword`, `usrGroup`,  `usrStatus`, `usrTimeout` FROM `iLearn`.`User` WHERE `usrName` = ? AND `usrStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, username);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                storedPassword = rs.getString("usrPassword");
                userName = username;
                userGroup = rs.getString("usrGroup");
            }
            rs.close();
            prep.close();
            if (storedPassword.equals(encryptedPassword))
            {
                successful = true;
                //Log the action
                String computername = InetAddress.getLocalHost().getHostName();
                InetAddress[] ip = Inet4Address.getAllByName(computername);
                String message = "SUCCESS: The user successfully logged on from " + ip[1] + ".";
                iLogger.logMessage(message, "Log On", "User");
            }
            else
            {
                String computername = InetAddress.getLocalHost().getHostName();
                InetAddress[] ip = Inet4Address.getAllByName(computername);
                String message = "ERROR: Failed to login as " + username + " from " + ip[1] + ".";
                iLogger.logMessage(message, "Log On", "User");
                loginCount++;
                successful = false;
            }
        }
        catch (Exception e)
        {
            String message = "ERROR: Could not validate user information.";
            logger.log(Level.SEVERE, message, e);
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

    /**
     * This function adds a user to the system.
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param group
     * @returns True if the user was added to the system.
     *          Will return false if the username already exists.
     */
    public static boolean addUser(String username, String password, String firstName, String lastName, String group)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `iLearn`.`User` (`usrFirstName`, `usrLastName`, `usrName`, `usrPassword`, `usrGroup`) VALUES ( ?, ?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, firstName);
            prep.setString(2, lastName);
            prep.setString(3, username);
            prep.setString(4, EncryptionHandler.encryptPassword(password));
            prep.setString(5, group);
            prep.execute();
            prep.close();
            successful = true;
            //Log the action
            String message = "SUCCESS: The user \"" + username + "\" was added to the system.";
            iLogger.logMessage(message, "Add", "User");
        }
        catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex)
        {
            String message = "An error occurred while adding a user to the database.\n"
                             + "This username already exists.";
            logger.log(Level.SEVERE, message, ex);
            successful = false;
            Utilities.showErrorMessage(null, message);
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a user to the database.";
            logger.log(Level.SEVERE, message, e);
            successful = false;
        }
        return successful;
    }

    /**
     * This returns a table model containing a list of all the users.
     * @return
     */
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
            logger.log(Level.SEVERE, message, sQLException);
        }
        DefaultTableModel model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }
        };
        model.addColumn("ID", id.toArray());
        model.addColumn("Name", name.toArray());
        model.addColumn("User Name", username.toArray());
        model.addColumn("Status", status.toArray());
        return model;
    }

    /**
     * This function gets the information for a specific user.
     * @param ID
     * @return
     */
    public static String[] getUserInfo(String ID)
    {
        String sql = "SELECT * FROM `User` WHERE `usrID` = ?;";
        String firstName = "", lastName = "", username = "", password = "", group = "", status = "";
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
                group = rs.getString("usrGroup");
                status = rs.getString("usrStatus");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the user list.";
            logger.log(Level.SEVERE, message, e);
        }
        String[] results =
        {
            firstName, lastName, username, password, group, status
        };
        return results;
    }

    /**
     * This method will update the information for a specified user with the values given.
     * @param ID
     * @param firstName
     * @param lastName
     * @param userName
     * @param password
     * @param group
     * @param status
     * @return True of the action was completed successfully.
     *         False if anything went wrong.
     */
    public static boolean updateUser(String ID, String firstName, String lastName, String userName, String password, String group, String status)
    {
        boolean successful = false;
        String sql = "UPDATE `User` SET `usrFirstName`= ?, `usrLastName`= ?, `usrName`= ?, `usrPassword`= ?, `usrGroup`= ?, `usrStatus`= ?  WHERE `usrID`= ? LIMIT 1;";
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
            prep.setString(6, status);
            prep.setString(7, ID);
            prep.executeUpdate();
            prep.close();
            successful = true;
            //Log the action
            String message = "SUCCESS: " + userName + "'s information was changed.";
            iLogger.logMessage(message, "Update", "User");
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the user's information.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    /**
     * Returns a list of the different user groups.
     * @return
     */
    public static ArrayList<String> getUserGroups()
    {
        ArrayList<String> groups = new ArrayList<String>();
        String sql = "SELECT DISTINCT `groupName` FROM `iLearn`.`listUserGroups` ORDER BY `groupName` ASC;";
        try
        {
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                groups.add(rs.getString("groupName"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the user group listing.";
            logger.log(Level.SEVERE, message, e);
        }
        return groups;
    }

    /**
     * Returns a list of the different levels available to a group.
     * @param group
     * @return
     */
    public static ArrayList<String> getGroupLevels(String group)
    {
        ArrayList<String> levels = new ArrayList<String>();
        String sql = "SELECT `levels` FROM `listUserGroups` WHERE `groupName` = ? ORDER BY `levels` ASC;";
        try
        {
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, group);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                levels.add(rs.getString("levels"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the user group levels listing.";
            logger.log(Level.SEVERE, message, e);
        }
        return levels;
    }

    /**
     * @return the userName
     */
    public static String getUserName()
    {
        return userName;
    }

    /**
     * @return the userGroup
     */
    public static String getUserGroup()
    {
        return userGroup;
    }

    public static boolean changePassword(String userName, String password)
    {
        boolean successful = false;
        String sql = "UPDATE `User` SET `usrPassword`= ?  WHERE `usrName`= ? LIMIT 1;";
        try
        {
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, password);
            prep.setString(2, userName);
            prep.executeUpdate();
            prep.close();
            successful = true;
            //Log the action
            String message = "SUCCESS: " + userName + "'s password was changed.";
            iLogger.logMessage(message, "Update", "User");
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the user's information.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }
}