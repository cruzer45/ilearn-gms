/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.school;

import ilearn.kernel.Environment;
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
public class AssessmentTypes
{

    static final Logger logger = Logger.getLogger(AssessmentTypes.class.getName());

    public static DefaultTableModel getAssessmentTypes()
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
            String sql = "SELECT `id`, `assmtType` FROM `listAssessmentTypes` ORDER BY `assmtType` ASC";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                ids.add(rs.getString("id"));
                types.add(rs.getString("assmtType"));
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
        model.addColumn("Type", types.toArray());
        return model;
    }

    public static boolean addAssmentType(String assmtType)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `listAssessmentTypes` (`assmtType`) VALUES (?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, assmtType);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding an assessment type.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean deleteAssmentType(String id)
    {
        boolean successful = false;
        try
        {
            String sql = "DELETE FROM `listAssessmentTypes` WHERE `id`= ? LIMIT 1;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, id);
            prep.executeUpdate();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while removing an assessment type.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }
}
