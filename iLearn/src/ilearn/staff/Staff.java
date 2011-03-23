/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.staff;


import ilearn.kernel.Environment;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m.rogers
 */
public class Staff
{

    static final Logger logger = Logger.getLogger(Staff.class.getName());

    public static boolean addStaff(String code, String firstName , String lastName , String gender , String DOB , String notes , String email)
    {
        boolean successful = false;

        try
        {
            String sql = "INSERT INTO `Staff` (`staCode`, `staFirstName`, `staLastName`, `staGender`, `staDOB`, `staNotes`, `staEmail`) VALUES (?,?, ?, ?,?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, code);
            prep.setString(2, firstName);
            prep.setString(3, lastName);
            prep.setString(4, gender);
            prep.setString(5, DOB);
            prep.setString(6, notes);
            prep.setString(7, email);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding the staff to the system.";
            logger.log(Level.SEVERE, message, e);
        }

        return successful;
    }
}
