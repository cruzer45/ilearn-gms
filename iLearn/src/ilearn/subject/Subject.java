/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.subject;

import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.staff.Staff;
import ilearn.term.TimeSlots;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author m.rogers
 */
public class Subject
{

    static final Logger logger = Logger.getLogger(Subject.class.getName());
    private static ArrayList<String> hours = new ArrayList<String>();
    private static ArrayList<String> hourCodes = new ArrayList<String>();

    public static void addHour(String hour, String code)
    {
        if (!hours.contains(hour))
        {
            hours.add(hour);
            hourCodes.add(code);
        }
    }

    public static void removeHour(String hour)
    {
        for (int i = 0; i < hours.size(); i++)
        {
            if (hour.equals(hours.get(i)))
            {
                hours.remove(i);
                hourCodes.remove(i);
            }
        }
    }

    public static ArrayList<String> getHours()
    {
        return hours;
    }

    public static ArrayList<String> getHourCodes()
    {
        return hourCodes;
    }

    public static void resetHours()
    {
        hours = new ArrayList<String>();
        hourCodes = new ArrayList<String>();
    }

    public static boolean addSubject(String subCode, String subStaffCode, String subName, String subDescription)
    {
        boolean successful = false;
        try
        {
            //Save the hours
            String sql = "INSERT INTO `SubjectHours` (`subCode`, `hrsKey`) VALUES (?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            for (int i = 0; i < hourCodes.size(); i++)
            {
                prep.setString(1, subCode);
                prep.setString(2, hourCodes.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
            //Save the subject Info.
            sql = "INSERT INTO `Subject` (`subCode`, `subStaffCode`, `subName`, `subDescription`) VALUES (?, ?, ?, ?);";
            prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, subCode);
            prep.setString(2, subStaffCode);
            prep.setString(3, subName);
            prep.setString(4, subDescription);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a subject";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean updateSubject(String subCode, String subStaffCode, String subName, String subDescription, String subStatus, String subID)
    {
        boolean successful = false;
        try
        {
            //Save the hours
            String sql1 = "DELETE FROM `SubjectHours` WHERE `subCode`= ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql1);
            prep.setString(1, subCode);
            prep.executeUpdate();
            prep.close();
            String sql2 = "INSERT INTO `SubjectHours` (`subCode`, `hrsKey`) VALUES (?, ?);";
            prep = Environment.getConnection().prepareStatement(sql2);
            for (int i = 0; i < hourCodes.size(); i++)
            {
                prep.setString(1, subCode);
                prep.setString(2, hourCodes.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
            //Save the subject Info.
            String sql3 = "UPDATE `Subject` SET `subCode`= ?, `subStaffCode`= ?, `subName`= ?, `subDescription`= ?, `subStatus`= ? WHERE `subID`= ? ;";
            prep = Environment.getConnection().prepareStatement(sql3);
            prep.setString(1, subCode);
            prep.setString(2, subStaffCode);
            prep.setString(3, subName);
            prep.setString(4, subDescription);
            prep.setString(5, subStatus);
            prep.setString(6, subID);
            prep.executeUpdate();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating a subject";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static DefaultTableModel getSubjectList(String criteria)
    {
        criteria = Utilities.percent(criteria);
        DefaultTableModel model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }
        };
        ArrayList<String> id = new ArrayList<String>();
        ArrayList<String> code = new ArrayList<String>();
        ArrayList<String> staffCode = new ArrayList<String>();
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<String> description = new ArrayList<String>();
        ArrayList<String> status = new ArrayList<String>();
        try
        {
            String sql = "SELECT `subID`, `subCode`, `subStaffCode`, `subName`, `subDescription`, `subStatus` FROM `iLearn`.`Subject` "
                         + " WHERE (`subID` LIKE ? OR `subCode` LIKE ? OR `subStaffCode` LIKE ? OR `subName` LIKE ? OR `subDescription` LIKE ? ) ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, criteria);
            prep.setString(2, criteria);
            prep.setString(3, criteria);
            prep.setString(4, criteria);
            prep.setString(5, criteria);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                id.add(rs.getString("subID"));
                code.add(rs.getString("subCode"));
                staffCode.add(rs.getString("subStaffCode"));
                name.add(rs.getString("subName"));
                description.add(rs.getString("subDescription"));
                status.add(rs.getString("subStatus"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while searching for a subject.";
            logger.log(Level.SEVERE, message, e);
        }
        model.addColumn("ID", id.toArray());
        model.addColumn("Code", code.toArray());
        model.addColumn("Name", name.toArray());
        model.addColumn("Teacher", staffCode.toArray());
        model.addColumn("Status", status.toArray());
        return model;
    }

    public static ArrayList<String> getSubjectDetails(String subID)
    {
        ArrayList<String> details = new ArrayList<String>();
        try
        {
            String sql = "SELECT `subID`, `subCode`, `subStaffCode`, `subName`, `subDescription`, `subStatus` FROM `iLearn`.`Subject` WHERE `subID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, subID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                details.add(rs.getString("subID"));
                details.add(rs.getString("subCode"));
                details.add(Staff.getStaffName(rs.getString("subStaffCode")));
                details.add(rs.getString("subName"));
                details.add(rs.getString("subDescription"));
                details.add(rs.getString("subStatus"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving subject details.";
            logger.log(Level.SEVERE, message, e);
        }
        return details;
    }

    public static void getSubjectHours(String subCode)
    {
        resetHours();
        try
        {
            String sql = "SELECT `subHoursID`, `subCode`, `hrsKey` FROM `iLearn`.`SubjectHours` WHERE `subCode` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, subCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                hourCodes.add(rs.getString("hrsKey"));
            }
            rs.close();
            prep.close();
            for (String hourCode : hourCodes)
            {
                hours.add(TimeSlots.getTimeSlot(hourCode));
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving subject hours.";
            logger.log(Level.SEVERE, message, e);
        }
    }
}
