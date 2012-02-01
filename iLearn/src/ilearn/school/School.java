package ilearn.school;

import ilearn.kernel.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrogers
 */
public class School
{

    static final Logger logger = Logger.getLogger(School.class.getName());

    public static ArrayList<Object> getSchoolInfo()
    {
        ArrayList<Object> school = new ArrayList<Object>();
        try
        {
            String sql = "SELECT `schlID`, `schName`, `schShortName`, `schPhone1`, `schPhone2`, `schAddress`, `schLogo`, `registrationCode`, `timeCode`, `dbVersion` , `schPassingMark` , `schPrincipal` FROM `School`;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            rs.first();
            school.add(rs.getString("schName"));//0
            school.add(rs.getString("schShortName"));//1
            school.add(rs.getString("schPhone1"));//2
            school.add(rs.getString("schPhone2"));//3
            school.add(rs.getString("schAddress"));//4
            school.add(rs.getBlob("schLogo"));//5
            school.add(rs.getString("schPassingMark"));//6
            school.add(rs.getString("schPrincipal"));//7
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving the school information.";
            logger.log(Level.SEVERE, message, e);
        }
        return school;
    }

    public static boolean updateSchoolInfo(String schName, String schShortName, String schPhone1, String schPhone2, String schAddress, File schLogo, String schPassingMark, String schPrincipal)
    {
        boolean successful = false;
        try
        {
            FileInputStream fis = new FileInputStream(schLogo);
            String sql = "UPDATE `School` SET `schName`= ?, `schShortName`=?, `schPhone1`=?, `schPhone2`= ?, `schAddress`= ? , schLogo = ? , schPassingMark = ? , schPrincipal = ? WHERE `schlID`=1 LIMIT 1;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, schName);
            prep.setString(2, schShortName);
            prep.setString(3, schPhone1);
            prep.setString(4, schPhone2);
            prep.setString(5, schAddress);
            prep.setBlob(6, fis, schLogo.length());
            prep.setString(7, schPassingMark);
            prep.setString(8, schPrincipal);
            prep.executeUpdate();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the school info.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean updateSchoolInfo(String schName, String schShortName, String schPhone1, String schPhone2, String schAddress, String schPassingMark, String schPrincipal)
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `School` SET `schName`= ?, `schShortName`=?, `schPhone1`=?, `schPhone2`= ?, `schAddress`= ? , schPassingMark = ? , schPrincipal = ? WHERE `schlID`=1 LIMIT 1;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, schName);
            prep.setString(2, schShortName);
            prep.setString(3, schPhone1);
            prep.setString(4, schPhone2);
            prep.setString(5, schAddress);
            prep.setString(6, schPassingMark);
            prep.setString(7, schPrincipal);
            prep.executeUpdate();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the school info.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static int getPassingMark()
    {
        int passingmark = 70; //set a default passing mark of 70%
        try
        {
            String sql = "SELECT `schPassingMark` FROM `School`;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                passingmark = rs.getInt("schPassingMark");
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while retreiving the passing mark.";
            logger.log(Level.SEVERE, message, e);
        }
        return passingmark;
    }
    public static String getPrincipal()
    {
        String principal = "";
        try
        {
            String sql = "SELECT `schPrincipal` FROM `School`;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                principal = rs.getString("schPrincipal");
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while retreiving the passing mark.";
            logger.log(Level.SEVERE, message, e);
        }
        return principal;
    }

}
