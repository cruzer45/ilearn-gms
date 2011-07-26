/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.detentions;

import ilearn.kernel.Environment;
import ilearn.student.Student;
import ilearn.term.Term;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrogers
 */
public class Detention
{

    static final Logger logger = Logger.getLogger(Detention.class.getName());

    public static boolean saveDetention(String stuID, String detDate, String detPunishment, String detRemark)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `Detention` (`detTermID`, `detStuID`, `detDate`, `detClassCode`, `detPunishment`, `detRemark`) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, Term.getCurrentTerm());
            prep.setString(2, stuID);
            prep.setString(3, detDate);
            prep.setString(4, Student.getStudentClass(stuID));
            prep.setString(5, detPunishment);
            prep.setString(6, detRemark);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a detention to the system.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }
}
