/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.term;

import ilearn.kernel.Environment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mrogers
 */
public class TimeSlots
{

    private static final Logger logger = Logger.getLogger(TimeSlots.class.getName());
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");

    public static boolean addTimeSlot(String code, String day, String startTime, String endTime)
    {
        boolean successful = false;

        try
        {
            String sql = "INSERT INTO `TimeSlots` (`hrsKey`, `hrsDay`, `hrsBegin`, `hrsEnd`) VALUES (?,?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, code);
            prep.setString(2, day);
            prep.setString(3, startTime);
            prep.setString(4, endTime);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            successful = false;
            String message = "An error occurred while adding the time slot.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static ArrayList<String> getTimeSlotList()
    {
        ArrayList<String> list = new ArrayList<String>();

        try
        {
            String sql = "SELECT `id`, `hrsKey` ,`hrsStatus` FROM `iLearn`.`TimeSlots` WHERE `hrsStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();

            while (rs.next())
            {
                String item = rs.getString("hrsKey");
                list.add(item);
            }
            rs.close();
            prep.close();

        }
        catch (Exception e)
        {
            String message = "An error occurred while fetching the list of time slots.";
            logger.log(Level.SEVERE, message, e);
        }

        return list;
    }

    public static DefaultTableModel getTimeSlotTableModelList()
    {

        DefaultTableModel model = new DefaultTableModel()
        {

            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }
        };

        ArrayList<String> ids = new ArrayList<String>();
        ArrayList<String> codes = new ArrayList<String>();
        ArrayList<String> days = new ArrayList<String>();
        ArrayList<String> begin = new ArrayList<String>();
        ArrayList<String> end = new ArrayList<String>();
        ArrayList<String> status = new ArrayList<String>();


        try
        {
            String sql = "SELECT `id`, `hrsKey`, `hrsDay`, `hrsBegin`, `hrsEnd`, `hrsStatus` FROM `iLearn`.`TimeSlots`;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();

            while (rs.next())
            {
                ids.add(rs.getString("id"));
                codes.add(rs.getString("hrsKey"));
                days.add(rs.getString("hrsDay"));
                begin.add(timeFormat.format(rs.getTime("hrsBegin")));
                end.add(timeFormat.format(rs.getTime("hrsEnd")));
                status.add(rs.getString("hrsStatus"));
            }
            rs.close();
            prep.close();

            model.addColumn("ID", ids.toArray());
            model.addColumn("Code", codes.toArray());
            model.addColumn("Day", days.toArray());
            model.addColumn("Start", begin.toArray());
            model.addColumn("End", end.toArray());
            model.addColumn("Status", status.toArray());
        }
        catch (Exception e)
        {
            String message = "An error occurred while fetching the list of time slots.";
            logger.log(Level.SEVERE, message, e);
        }

        return model;
    }

    public static boolean updateTimeSlot(String id, String code, String day, String startTime, String endTime, String status)
    {
        boolean successful = true;

        try
        {
            String sql = "UPDATE `TimeSlots` SET `hrsKey`= ?, `hrsDay`= ?, `hrsBegin`= ?, `hrsEnd`=?, `hrsStatus`=? WHERE `id`= ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, code);
            prep.setString(2, day);
            prep.setString(3, startTime);
            prep.setString(4, endTime);
            prep.setString(5, status);
            prep.setString(6, id);
            prep.executeUpdate();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the time slots.";
            logger.log(Level.SEVERE, message, e);
        }

        return successful;
    }
}
