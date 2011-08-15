package ilearn.kernel.logger;

import ilearn.kernel.Environment;
import ilearn.term.Term;
import ilearn.user.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrogers
 */
public class iLogger
{

    static final Logger logger = Logger.getLogger(iLogger.class.getName());
    public static final String midTermGrades = "Mid Term Grades Generated";
    public static final String finalGrades = "Final Grades Generated";

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

    public static void logAction(String message)
    {
        try
        {
            String sql = "INSERT INTO `Log_Actions` (`logAction`, `logTrmCode`) VALUES (?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, message);
            prep.setString(2, Term.getCurrentTerm());
            prep.execute();
            prep.close();
        }
        catch (Exception e)
        {
            String mess = "An error occurred while logging a message to the database.";
            logger.log(Level.SEVERE, mess, e);
        }
    }

    public static boolean checkAction(String action)
    {
        boolean actionRun = false;
        try
        {
            String sql = "SELECT `logID` "
                         + " FROM `Log_Actions` "
                         + " WHERE `logAction` = ? AND `logTrmCode` = ? ";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, action);
            prep.setString(2, Term.getCurrentTerm());
            ResultSet rs = prep.executeQuery();
            ArrayList<String> results = new ArrayList<String>();
            while (rs.next())
            {
                results.add(rs.getString("logID"));
            }
            rs.close();
            prep.close();
            if (!results.isEmpty())
            {
                actionRun = true;
            }
        }
        catch (Exception e)
        {
            String mess = "An error occurred while determining of the action has already been run.";
            logger.log(Level.SEVERE, mess, e);
        }
        return actionRun;
    }
}
