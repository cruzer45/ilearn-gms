/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.user;

import ilearn.kernel.EncryptionHandler;
import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.kernel.logger.iLogger;
import ilearn.staff.Staff;
import ilearn.subject.Subject;
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
    private static String permissions = "";
    private static int timeout = 0;
    static final Logger logger = Logger.getLogger(User.class.getName());
    private static ArrayList<String> staIDs = new ArrayList<String>();
    private static ArrayList<String> staCodes = new ArrayList<String>();
    private static ArrayList<String> staNames = new ArrayList<String>();
    private static ArrayList<String> permittedSubjects = new ArrayList<String>();
    private static ArrayList<String> permittedClasses = new ArrayList<String>();

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
            String sql = "SELECT `usrName`, `usrPassword`, `usrGroup`,  `usrStatus`, `usrTimeout`, `usrPermissions` FROM `iLearn`.`User` WHERE `usrName` = ? AND `usrStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, username);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                storedPassword = rs.getString("usrPassword");
                userName = username;
                userGroup = rs.getString("usrGroup");
                timeout = rs.getInt("usrTimeout");
                permissions = rs.getString("usrPermissions");
            }
            rs.close();
            prep.close();
            if (storedPassword.equals(encryptedPassword))
            {
                successful = true;
                //Log the action
                String computername = InetAddress.getLocalHost().getHostName();
                String IP = InetAddress.getLocalHost().getHostAddress();
                String message = "SUCCESS: The user successfully logged on from " + IP + ".";
                iLogger.logMessage(message, "Log On", "User");
            }
            else
            {
                String computername = InetAddress.getLocalHost().getHostName();
                String IP = InetAddress.getLocalHost().getHostAddress();
                String message = "ERROR: Failed to login as " + username + " from " + IP + ".";
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
    public static boolean addUser(String username, String password, String firstName, String lastName, String group, String permission)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `iLearn`.`User` (`usrFirstName`, `usrLastName`, `usrName`, `usrPassword`, `usrGroup`, `usrPermissions`) VALUES ( ?, ?, ?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, firstName);
            prep.setString(2, lastName);
            prep.setString(3, username);
            prep.setString(4, EncryptionHandler.encryptPassword(password));
            prep.setString(5, group);
            prep.setString(6, permission);
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
        String firstName = "", lastName = "", username = "", password = "", group = "", status = "", usrtimeout = "", userPermission = "";
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
                usrtimeout = rs.getString("usrTimeout");
                userPermission = rs.getString("usrPermissions");
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
            firstName, lastName, username, password, group, status, usrtimeout, userPermission
        };
        return results;
    }

    public static String getUserID(String usrName)
    {
        String usrID = "";
        try
        {
            String sql = "SELECT `usrID` FROM `User` WHERE `usrName` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, usrName);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                usrID = rs.getString("usrID");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the user ID.";
            logger.log(Level.SEVERE, message, e);
        }
        return usrID;
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
    public static boolean updateUser(String ID, String firstName, String lastName, String userName, String password, String group, String status, String usrTimeOut)
    {
        boolean successful = false;
        String sql = "UPDATE `User` SET `usrFirstName`= ?, `usrLastName`= ?, `usrName`= ?, `usrPassword`= ?, `usrGroup`= ?, `usrStatus`= ? , `usrTimeout` = ? WHERE `usrID`= ? LIMIT 1;";
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
            prep.setString(7, usrTimeOut);
            prep.setString(8, ID);
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

    /**
     * Changes a specified user's password.
     *
     * @param userName
     * @param password
     * @return True if the change operation was successful.
     */
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

    /**
     * @return the timeout
     */
    public static int getTimeout()
    {
        return timeout;
    }

    public static void getPermittedListItems()
    {
        try
        {
            //get the user
            String usrID = getUserID(userName);
            //get all linked accounts
            getStaffLinks(usrID);
            //get all subjects associated with that account.
            for (String staff : staIDs)
            {
                String staCode = Staff.getStaffCodeFromID(staff);
                ArrayList<String> staffSubjects = Staff.getStaffSubjectsID(staCode);
                for (String subject : staffSubjects)
                {
                    String subCode = Subject.getSubjectCode(subject);
                    if (!permittedSubjects.contains(subCode))
                    {
                        permittedSubjects.add(subCode);
                    }
                    //get all clases that take that subject
                    ArrayList<String> classesforSubject = Subject.getClassesForSubject(subject);
                    for (String clscode : classesforSubject)
                    {
                        if (!permittedClasses.contains(clscode))
                        {
                            permittedClasses.add(clscode);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the list of permitted classes.";
            logger.log(Level.SEVERE, message, e);
        }
    }

    public static void resetStaffLinks()
    {
        staIDs = new ArrayList<String>();
        staCodes = new ArrayList<String>();
        staNames = new ArrayList<String>();
    }

    public static void addStaffLink(String staffID, String staffCode, String staffName)
    {
        if (!staIDs.contains(staffID))
        {
            staIDs.add(staffID);
            staCodes.add(staffCode);
            staNames.add(staffName);
        }
    }

    public static void removeStaffLink(String staffID, String staffCode, String staffName)
    {
        if (staIDs.contains(staffID))
        {
            staIDs.remove(staffID);
            staCodes.remove(staffCode);
            staNames.remove(staffName);
        }
    }

    public static DefaultTableModel loadStaffLinks()
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
                return false;
            }
        };
        model.addColumn("Staff ID", staIDs.toArray());
        model.addColumn("Staff Code", staCodes.toArray());
        model.addColumn("Staff Name", staNames.toArray());
        return model;
    }

    public static boolean saveStaffLinks(String usrID)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `User_Staff` (`userID`, `staID`) VALUES (?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            for (String staff : staIDs)
            {
                prep.setString(1, usrID);
                prep.setString(2, staff);
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while saving the staff links.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    private static void getStaffLinks(String usrID)
    {
        try
        {
            resetStaffLinks();
            String sql = "SELECT `staID` FROM `iLearn`.`User_Staff` WHERE `userID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, usrID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                staIDs.add(rs.getString("staID"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the staff links.";
            logger.log(Level.SEVERE, message, e);
        }
    }

    /**
     * @return the permittedSubjects
     */
    public static ArrayList<String> getPermittedSubjects()
    {
        return permittedSubjects;
    }

    /**
     * @return the permittedClasses
     */
    public static ArrayList<String> getPermittedClasses()
    {
        return permittedClasses;
    }

    /**
     * @return the permissions
     */
    public static String getPermissions()
    {
        return permissions;
    }

    public static boolean previligeAvailable(String currentPath)
    {
        String[] prevList = getPermissions().split("\\|");
        for (String prevItem : prevList)
        {
            String[] split = prevItem.split("-");
            String item = split[0];

            System.out.println(item + " vs " + currentPath);

            if (item.equals(currentPath) && (split[1].equalsIgnoreCase("True"))) //if the prevelige matches the row set check the path.
            {
                return true;
            }
        }
        return false;
    }
}
