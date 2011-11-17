/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.merits;

import ilearn.kernel.Environment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrogers
 */
public class Merits
{

    static final Logger logger = Logger.getLogger(Merits.class.getName());

    public static ArrayList<String> getMeritReasons()
    {
        ArrayList<String> results = new ArrayList<String>();
        try
        {
            String sql = " SELECT `reason` FROM `listMeritReasons` ORDER BY `reason` ASC; ";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                results.add(rs.getString("reason"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retreiving the list of merit reasons.";
            logger.log(Level.SEVERE, message, e);
        }
        return results;
    }

    public static boolean addMerit(String merStuID, String merDate, String merStaID, String merClsCode, String merTermID, String merits, String merRemarks)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `Merits` (`merStuID`, `merDate`, `merStaID`, `merClsCode`, `merTermID`, `merits`, `merRemarks`) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, merStuID);
            prep.setString(2, merDate);
            prep.setString(3, merStaID);
            prep.setString(4, merClsCode);
            prep.setString(5, merTermID);
            prep.setString(6, merits);
            prep.setString(7, merRemarks);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while trying to save a merit.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }
}
