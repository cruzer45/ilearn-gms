/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.school;

import ilearn.kernel.Environment;
import ilearn.kernel.logger.iLogger;
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
public class DemeritReasons
{

    static final Logger logger = Logger.getLogger(DemeritReasons.class.getName());

    public static DefaultTableModel getDemeritReasons()
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
                return false;
            }
        };
        ArrayList<String> ids = new ArrayList<String>();
        ArrayList<String> types = new ArrayList<String>();
        try
        {
            String sql = "SELECT `id`,`reason` FROM `listDemeritReasons` ORDER BY `reason` ASC";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                ids.add(rs.getString("id"));
                types.add(rs.getString("reason"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retreiving the list of assessment types.";
            logger.log(Level.SEVERE, message, e);
        }
        model.addColumn("ID", ids.toArray());
        model.addColumn("Reason", types.toArray());
        return model;
    }

    public static boolean addDemeritReason(String demReason)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `listDemeritReasons` (`reason`) VALUES (?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, demReason);
            prep.execute();
            prep.close();
            successful = true;
            String message = "Demerit reason was added " + demReason + " was added.";
            iLogger.logMessage(message, "Add", "Demerit Reason");
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a demerit reason.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean deleteDemeritReason(String id)
    {
        boolean successful = false;
        try
        {
            String sql = "DELETE FROM `listDemeritReasons` WHERE `id`= ? LIMIT 1;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, id);
            prep.executeUpdate();
            prep.close();
            successful = true;
            String message = "Demerit reason with id " + id + " was deleted.";
            iLogger.logMessage(message, "Delete", "Demerit Reason");
        }
        catch (Exception e)
        {
            String message = "An error occurred while removing a demerit reason.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }
}
