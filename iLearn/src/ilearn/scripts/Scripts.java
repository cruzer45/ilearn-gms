package ilearn.scripts;

import ilearn.kernel.Environment;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrogers
 */
public class Scripts
{

    static final Logger logger = Logger.getLogger(Scripts.class.getName());

    public static boolean addScript(HashMap params)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `Scripts` (`scriptModule`, `scriptTitle`, `scriptQuery`, `scriptPriority`, `scriptRemark`) VALUES (?, ?,?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, params.get("scriptModule").toString());
            prep.setString(2, params.get("scriptTitle").toString());
            prep.setString(3, params.get("scriptQuery").toString());
            prep.setString(4, params.get("scriptPriority").toString());
            prep.setString(5, params.get("scriptRemark").toString());
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while trying to save a script.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }
}
