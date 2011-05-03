/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.assessments;

import ilearn.kernel.Environment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author mrogers
 */
public class Assessment
{
    static final Logger logger = Logger.getLogger(Assessment.class.getName());

    public static ArrayList<String> getAssessmentTypes()
    {
        ArrayList<String> list = new ArrayList<String>();
        try
        {
            String sql = "SELECT `assmtType` FROM `iLearn`.`listAssessmentTypes` ORDER BY `assmtType` DESC; ";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                list.add(rs.getString("assmtType"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
        }
        return list;
    }
}
