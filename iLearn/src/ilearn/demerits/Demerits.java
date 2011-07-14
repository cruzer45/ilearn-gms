/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.demerits;

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
public class Demerits
{

    static final Logger logger = Logger.getLogger(Demerits.class.getName());

    public static ArrayList<String> getDemeritReasons()
    {
        ArrayList<String> demeritReasons = new ArrayList<String>();
        try
        {
            String sql = "SELECT `reason` "
                    + "FROM `listDemeritReasons` "
                    + "ORDER BY `reason` ASC;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                demeritReasons.add(rs.getString("reason"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating a list of report reasons.";
            logger.log(Level.SEVERE, message, e);
        }
        return demeritReasons;
    }

    public static boolean addDemerit(String demStuID, String demDate, String demStaCode, String demClsCode, String demTermID, String demerits, String demRemarks)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `Demerits` (`demStuID`, `demDate`, `demStaCode`, `demClsCode`, `demTermID`, `demerits`, `demRemarks`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, demStuID);
            prep.setString(2, demDate);
            prep.setString(3, demStaCode);
            prep.setString(4, demClsCode);
            prep.setString(5, demTermID);
            prep.setString(6, demerits);
            prep.setString(7, demRemarks);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while saving a demerit.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }
}
