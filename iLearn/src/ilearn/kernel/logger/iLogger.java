/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.kernel.logger;

import ilearn.kernel.Environment;
import ilearn.user.User;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrogers
 */
public class iLogger
{

    static final Logger logger = Logger.getLogger(iLogger.class.getName());

    public static void logMessage(String message, String type, String module)
    {
        try
        {
            String sql = "INSERT INTO `Log` (`logType`, `logUser`, `logMessage`,`logModule`) VALUES (?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, type);
            prep.setString(2, User.getUserName());
            prep.setString(3, message);
            prep.setString(4, module);
            prep.execute();
            prep.close();
        }
        catch (Exception e)
        {
            String mess = "An error occurred while logging a message to the database.";
            logger.log(Level.SEVERE, mess, e);
        }
    }
}
