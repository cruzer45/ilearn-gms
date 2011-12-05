/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.merits;

import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

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

    public static boolean upsdateMerit(String merID, String merDate, String merits, String merRemarks, String merStatus)
    {
        boolean successful = false;
        try
        {
            String sql = " UPDATE `Merits` SET `merDate`=?, `merits`=?, `merRemarks`=?, `merStatus`= ? WHERE `merID`= ?; ";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, merDate);
            prep.setString(2, merits);
            prep.setString(3, merRemarks);
            prep.setString(4, merStatus);
            prep.setString(5, merID);
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

    public static DefaultTableModel searchMerits(String criteria)
    {
        criteria = Utilities.percent(criteria);
        DefaultTableModel model = new DefaultTableModel()
        {
            @Override
            public Class getColumnClass(int columnIndex)
            {
                Object o = getValueAt(0, columnIndex);
                if (o == null)
                {
                    return Object.class;
                }
                else
                {
                    return o.getClass();
                }
            }
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }
        };
        try
        {
            ArrayList<String> id = new ArrayList<String>();
            ArrayList<String> name = new ArrayList<String>();
            ArrayList<String> staff = new ArrayList<String>();
            ArrayList<String> date = new ArrayList<String>();
            String sql = " SELECT `merID`, CONCAT_WS(' ',`Student`.`stuFirstName`,`Student`.`stuLastName`) AS 'Name', CONCAT_WS(' ',`Staff`.`staFirstName`,`Staff`.`staLastName`) AS 'Staff',`merDate` "
                         + " FROM `Merits` "
                         + " INNER JOIN `Student` ON `Merits`.`merStuID` = `Student`.`stuID` "
                         + " INNER JOIN `Staff` ON `Merits`.`merStaID`= `Staff`.`staID` "
                         + " WHERE `merStatus` = 'Active'  "
                         + " AND (CONCAT_WS(' ',`Student`.`stuFirstName`,`Student`.`stuLastName`) LIKE ? OR `merStuID` LIKE ? OR `merDate` LIKE ? OR `merClsCode` LIKE ?) ";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, criteria);
            prep.setString(2, criteria);
            prep.setString(3, criteria);
            prep.setString(4, criteria);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                id.add(rs.getString("merID"));
                name.add(rs.getString("Name"));
                staff.add(rs.getString("Staff"));
                date.add(Utilities.MDY_Formatter.format(rs.getDate("merDate")));
            }
            rs.close();
            prep.close();
            model.addColumn("ID", id.toArray());
            model.addColumn("Student", name.toArray());
            model.addColumn("Staff", staff.toArray());
            model.addColumn("Date", date.toArray());
        }
        catch (Exception e)
        {
            String message = "An error occurred while searching for a demerit.";
            logger.log(Level.SEVERE, message, e);
        }
        return model;
    }

    public static ArrayList<String> getMeritDetails(String merID)
    {
        ArrayList<String> details = new ArrayList<String>();
        try
        {
            String sql = " SELECT CONCAT_WS(' ',`Student`.`stuFirstName`,`Student`.`stuLastName`) AS 'Name', CONCAT_WS(' ',`Staff`.`staFirstName`,`Staff`.`staLastName`) AS 'Staff',`merDate`,`merClsCode`,`merits`,`merRemarks`,`merStatus` "
                         + " FROM `Merits` "
                         + " INNER JOIN `Student` ON `Student`.`stuID` = `Merits`.`merStuID` "
                         + " INNER JOIN `Staff` ON `Merits`.`merStaID`= `Staff`.`staID` "
                         + " WHERE `merID` = ? ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, merID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                details.add(rs.getString("Name"));//0
                details.add(rs.getString("Staff"));//1
                details.add(rs.getString("merDate"));//2
                details.add(rs.getString("merClsCode"));//3
                details.add(rs.getString("merits"));//4
                details.add(rs.getString("merRemarks"));//5
                details.add(rs.getString("merStatus"));//6
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retreiving a demerit's details.";
            logger.log(Level.SEVERE, message, e);
        }
        return details;
    }
}
