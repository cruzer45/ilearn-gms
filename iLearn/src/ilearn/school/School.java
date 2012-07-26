package ilearn.school;

import ilearn.kernel.Environment;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author mrogers
 */
public class School
{

    static final Logger logger = Logger.getLogger(School.class.getName());

    public static HashMap<String, Object> getSchoolDetails()
    {
        HashMap<String, Object> details = new HashMap<String, Object>();
        try
        {
            String sql = "SELECT `schlID`, `schName`, `schShortName`, `schPhone1`, "
                         + "`schPhone2`, `schAddress`, `schLogo`, `registrationCode`, "
                         + " `schPassingMark` , `schPrincipal`, `schPrincipalSignature` FROM `School`;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            rs.first();
            details.put("schName", rs.getString("schName"));
            details.put("schShortName", rs.getString("schShortName"));
            details.put("schPhone1", rs.getString("schPhone1"));
            details.put("schPhone2", rs.getString("schPhone2"));
            details.put("schAddress", rs.getString("schAddress"));
            details.put("schLogo", rs.getBlob("schLogo"));
            details.put("schPassingMark", rs.getString("schPassingMark"));
            details.put("schPrincipal", rs.getString("schPrincipal"));
            details.put("schPrincipalSignature", rs.getBlob("schPrincipalSignature"));
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving the school information.";
            logger.log(Level.SEVERE, message, e);
        }
        return details;
    }

    public static ArrayList<Object> getSchoolInfo()
    {
        ArrayList<Object> school = new ArrayList<Object>();
        try
        {
            String sql = "SELECT `schlID`, `schName`, `schShortName`, `schPhone1`, "
                         + "`schPhone2`, `schAddress`, `schLogo`, `registrationCode`, "
                         + " `schPassingMark` , `schPrincipal`, `schPrincipalSignature` FROM `School`;";
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
            school.add(rs.getBlob("schPrincipalSignature"));//8
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

    public static boolean updateSchoolInfo(String schName, String schShortName,
                                           String schPhone1, String schPhone2, String schAddress, File schLogo,
                                           String schPassingMark, String schPrincipal, File schPrincipalSignature)
    {
        boolean successful = false;
        try
        {
            FileInputStream signatureInputStream = new FileInputStream(schPrincipalSignature);
            FileInputStream fis = new FileInputStream(schLogo);
            String sql = "UPDATE `School` SET `schName`= ?, `schShortName`=?, "
                         + "`schPhone1`=?, `schPhone2`= ?, `schAddress`= ? , "
                         + "schLogo = ? , schPassingMark = ? , schPrincipal = ? , `schPrincipalSignature` = ?"
                         + "WHERE `schlID`=1 LIMIT 1;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, schName);
            prep.setString(2, schShortName);
            prep.setString(3, schPhone1);
            prep.setString(4, schPhone2);
            prep.setString(5, schAddress);
            prep.setBlob(6, fis, schLogo.length());
            prep.setString(7, schPassingMark);
            prep.setString(8, schPrincipal);
            prep.setBlob(9, signatureInputStream, schPrincipalSignature.length());
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

    public static void downloadSignature()
    {
        try
        {
            String sql = "SELECT `schlID`, `schName`, `schShortName`, `schPhone1`, "
                         + "`schPhone2`, `schAddress`, `schLogo`, `registrationCode`, "
                         + " `schPassingMark` , `schPrincipal`, `schPrincipalSignature` FROM `School`;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            rs.first();
            Blob blob = rs.getBlob("schPrincipalSignature");
            ImageIcon original = new ImageIcon(blob.getBytes(1, (int) blob.length()));
            //draw original image
            Image img = original.getImage();
            BufferedImage originalImage = new BufferedImage(original.getIconWidth(), original.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = originalImage.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(img, 0, 0, original.getIconWidth(), original.getIconHeight(), null);
            File output = new File("images/report_signature.jpg");
            ImageIO.write(originalImage, "JPG", output);
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while downloading the principal's signature.";
            logger.log(Level.SEVERE, message, e);
        }
    }

    public static void downloadlogo()
    {
        try
        {
            String sql = "SELECT `schLogo` FROM `School`;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            rs.first();
            Blob blob = rs.getBlob("schLogo");
            ImageIcon original = new ImageIcon(blob.getBytes(1, (int) blob.length()));
            //draw original image
            Image img = original.getImage();
            BufferedImage originalImage = new BufferedImage(original.getIconWidth(), original.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = originalImage.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(img, 0, 0, original.getIconWidth(), original.getIconHeight(), null);
            File output = new File("images/school_logo.jpg");
            ImageIO.write(originalImage, "jpg", output);
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while downloading the school's logo.";
            logger.log(Level.SEVERE, message, e);
        }
    }
}
