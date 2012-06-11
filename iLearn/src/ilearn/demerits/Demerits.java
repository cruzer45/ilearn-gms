package ilearn.demerits;

import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.kernel.logger.iLogger;
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

    public static boolean addDemerit(String demStuID, String demDate, String demStaCode, String demClsCode, String demTermID, String demerits, String demRemarks, String demActionTaken)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `Demerits` (`demStuID`, `demDate`, "
                         + " `demStaCode`, `demClsCode`, `demTermID`, `demerits`, "
                         + " `demRemarks`, demActionTaken) "
                         + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, demStuID);
            prep.setString(2, demDate);
            prep.setString(3, demStaCode);
            prep.setString(4, demClsCode);
            prep.setString(5, demTermID);
            prep.setString(6, demerits);
            prep.setString(7, demRemarks);
            prep.setString(8, demActionTaken);
            prep.execute();
            prep.close();
            successful = true;
            //Log the Action
            String message = "A demerit was added to the system for " + demStuID;
            iLogger.logMessage(message, "Add", "Demerit");
        }
        catch (Exception e)
        {
            String message = "An error occurred while saving a demerit.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean updateDemerit(String demID, String demDate,
                                        String demStaCode, String demerits,
                                        String demRemarks, String demStatus, String demActionTaken)
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Demerits` "
                         + "SET `demDate`=?, `demStaCode`=?, `demerits`= ?, "
                         + "`demRemarks`=?,`demStatus`=? , demActionTaken=? "
                         + "WHERE `demID`=? LIMIT 1;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, demDate);
            prep.setString(2, demStaCode);
            prep.setString(3, demerits);
            prep.setString(4, demRemarks);
            prep.setString(5, demStatus);
            prep.setString(6, demActionTaken);
            prep.setString(6, demID);
            prep.execute();
            prep.close();
            successful = true;
            //Log the Action
            String message = "Demerit " +demID+ " was updated.";
            iLogger.logMessage(message, "Update", "Demerit");
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating a demerit.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static DefaultTableModel searchDemerits(String criteria)
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
            String sql = "SELECT CONCAT_WS(' ',`Student`.`stuFirstName`,`Student`.`stuLastName`) AS 'Name', `Demerits`.*, CONCAT_WS(' ',`Staff`.`staFirstName`,`Staff`.`staLastName`) AS 'Staff' "
                         + "FROM `Demerits`  "
                         + "INNER JOIN `Student` ON `Demerits`.`demStuID` = `Student`.`stuID` "
                         + "INNER JOIN `Staff` ON `Demerits`.`demStaCode` = `Staff`.`staCode` "
                         + "WHERE `Demerits`.`demStatus` = 'Active' AND "
                         + "(CONCAT_WS(' ',`Student`.`stuFirstName`,`Student`.`stuLastName`) LIKE ? OR `demStuID` LIKE ? OR `demDate` LIKE ? OR `demClsCode` LIKE ?)";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, criteria);
            prep.setString(2, criteria);
            prep.setString(3, criteria);
            prep.setString(4, criteria);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                id.add(rs.getString("demID"));
                name.add(rs.getString("Name"));
                staff.add(rs.getString("Staff"));
                date.add(Utilities.MDY_Formatter.format(rs.getDate("demDate")));
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

    public static ArrayList<String> getDemeritInfo(String demID)
    {
        ArrayList<String> demeritInfo = new ArrayList<String>();
        try
        {
            String sql = "SELECT `demID`,`demStuID`,`demDate`,`demStaCode`,"
                         + " `demClsCode`,`demTermID`,`demerits`,`demRemarks`,"
                         + " `demStatus`, demActionTaken "
                         + " FROM `Demerits` "
                         + " WHERE `demID` = ?";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, demID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                demeritInfo.add(rs.getString("demID")); //0
                demeritInfo.add(rs.getString("demStuID")); //1
                demeritInfo.add(Utilities.YMD_Formatter.format(rs.getDate("demDate"))); //2
                demeritInfo.add(rs.getString("demStaCode")); //3
                demeritInfo.add(rs.getString("demClsCode")); //4
                demeritInfo.add(rs.getString("demTermID")); //5
                demeritInfo.add(rs.getString("demerits")); //6
                demeritInfo.add(rs.getString("demRemarks")); //7
                demeritInfo.add(rs.getString("demStatus")); //8
                demeritInfo.add(rs.getString("demActionTaken")); //9
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while loading the demerit info.";
            logger.log(Level.SEVERE, message, e);
        }
        return demeritInfo;
    }

    public static boolean closeDemerits()
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Demerits` SET `demStatus` = 'Closed' "
                         + " WHERE `demStatus` = 'Active'";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while closing the demerits.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }
}
