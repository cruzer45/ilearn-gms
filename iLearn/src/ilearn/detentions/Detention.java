/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.detentions;

import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.kernel.logger.iLogger;
import ilearn.student.Student;
import ilearn.term.Term;
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
            //Log the Action
            String message = "A detention was added to the system for " + stuID;
            iLogger.logMessage(message, "Add", "Detention");
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a detention to the system.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static DefaultTableModel searchDetentions(String criteria)
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
        ArrayList<String> IDs = new ArrayList<String>();
        ArrayList<String> dates = new ArrayList<String>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> punishments = new ArrayList<String>();
        try
        {
            String sql = "SELECT `detID`,`detDate`,`detPunishment`, CONCAT_WS(' ',`stuFirstName`, `stuLastName`) AS 'name' "
                         + " FROM `Detention` "
                         + " INNER JOIN `Student` ON `Detention`.`detStuID` = `Student`.`stuID` "
                         + "WHERE `detStatus` = 'Active' AND "
                         + "(`detID` LIKE ? OR `detPunishment` LIKE ? OR CONCAT_WS(' ',`stuFirstName`, `stuLastName`) LIKE ?)";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, criteria);
            prep.setString(2, criteria);
            prep.setString(3, criteria);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                IDs.add(rs.getString("detID"));
                dates.add(Utilities.MDY_Formatter.format(rs.getDate("detDate")));
                names.add(rs.getString("name"));
                punishments.add(rs.getString("detPunishment"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the search results for detentions.";
            logger.log(Level.SEVERE, message, e);
        }
        model.addColumn("ID", IDs.toArray());
        model.addColumn("Name", names.toArray());
        model.addColumn("Date", dates.toArray());
        model.addColumn("Punishment", punishments.toArray());
        return model;
    }

    public static DefaultTableModel getPendingDetentionsTable()
    {
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
                //Only allow the grade column to be editable.
                boolean editable = false;
                if (mColIndex == 4 || mColIndex == 5)
                {
                    editable = true;
                }
                return editable;
            }
        };
        ArrayList<String> IDs = new ArrayList<String>();
        ArrayList<String> dates = new ArrayList<String>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> punishments = new ArrayList<String>();
        ArrayList<Boolean> served = new ArrayList<Boolean>();
        try
        {
            String sql = "SELECT `detID`,`detDate`,`detPunishment`, CONCAT_WS(' ',`stuFirstName`, `stuLastName`) AS 'name' "
                         + " FROM `Detention` "
                         + " INNER JOIN `Student` ON `Detention`.`detStuID` = `Student`.`stuID` "
                         + "WHERE `detStatus` = 'Active' AND `detServed` = 'false'";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                IDs.add(rs.getString("detID"));
                dates.add(Utilities.MDY_Formatter.format(rs.getDate("detDate")));
                names.add(rs.getString("name"));
                punishments.add(rs.getString("detPunishment"));
                served.add(false);
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the search results for detentions.";
            logger.log(Level.SEVERE, message, e);
        }
        model.addColumn("ID", IDs.toArray());
        model.addColumn("Name", names.toArray());
        model.addColumn("Date", dates.toArray());
        model.addColumn("Punishment", punishments.toArray());
        model.addColumn("Served", served.toArray());
        return model;
    }

    public static ArrayList<String> getDetentionTypes()
    {
        ArrayList<String> detionTypes = new ArrayList<String>();
        try
        {
            String sql = "SELECT `detType` FROM `iLearn`.`listDetentionTypes` ORDER BY `detType` ASC;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                detionTypes.add(rs.getString("detType"));
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while loading the list of detention types.";
            logger.log(Level.SEVERE, message, e);
        }
        return detionTypes;
    }

    public static ArrayList<String> getDetentionDetails(String detID)
    {
        ArrayList<String> detDetails = new ArrayList<String>();
        try
        {
            String sql = "SELECT `detID`,`detDate`,`detPunishment`, `detRemark` "
                         + " FROM `Detention` "
                         + " WHERE `detStatus` = 'Active' AND `detID` = ?";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, detID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                detDetails.add(rs.getString("detID"));//0
                detDetails.add(rs.getString("detDate"));//1
                detDetails.add(rs.getString("detPunishment"));//2
                detDetails.add(rs.getString("detRemark"));//3
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while loading the list of detention types.";
            logger.log(Level.SEVERE, message, e);
        }
        return detDetails;
    }

    public static boolean updateDetention(String detID, String detDate, String detPunishment, String detRemark, String detStatus)
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Detention` SET `detDate`= ?, `detPunishment`= ?, `detRemark`= ?, `detStatus`= ? WHERE `detID`= ? ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, detDate);
            prep.setString(2, detPunishment);
            prep.setString(3, detRemark);
            prep.setString(4, detStatus);
            prep.setString(5, detID);
            prep.execute();
            prep.close();
            successful = true;
            //Log the Action
            String message = "A detention was updated: " + detID;
            iLogger.logMessage(message, "Update", "Detention");
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the detention.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean updateServedDetentions(ArrayList<String> IDs, String date)
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Detention` SET `detServed`= '1', `detServedDate`= ? WHERE `detID`= ? ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            String IDSet = "";
            for (String id : IDs)
            {
                IDSet += id + ", ";
                prep.setString(1, date);
                prep.setString(2, id);
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
            successful = true;
            //Log the Action
            String message = "Detentions marked as served: " + IDSet;
            iLogger.logMessage(message, "Update", "Detention");
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the detentions.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean closeDetentions()
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Detention` SET `detStatus` = 'Closed' "
                         + " WHERE `detStatus` = 'Active'";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while closing the detentions.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }
}
