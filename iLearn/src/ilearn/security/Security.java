/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.security;

import ilearn.kernel.EncryptionHandler;
import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrogers
 */
public class Security
{

    private static int loginCount = 0;

    public static boolean logIn(String username, String password)
    {
        String storedPassword = "";
        String encryptedPassword = EncryptionHandler.encryptPassword(password);
        boolean successful = false;

        try
        {
            String sql = "SELECT `usrName`, `usrPassword`, `usrGroup`, `usrLevel`, `usrStatus` FROM `iLearn`.`User` WHERE `usrName` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, username);
            ResultSet rs = prep.executeQuery();

            while (rs.next())
            {
                storedPassword = rs.getString("usrPassword");
            }


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
            Logger.getLogger(Security.class.getName()).log(Level.SEVERE, message,e);
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
}
