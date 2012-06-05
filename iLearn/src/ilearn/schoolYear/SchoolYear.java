package ilearn.schoolYear;

import ilearn.kernel.Environment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mrogers
 */
public class SchoolYear
{

    static final Logger logger = Logger.getLogger(SchoolYear.class.getName());

    public static boolean addSchoolYear(HashMap params)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `SchoolYear` (`syName`, `syStartDate`, `syEndDate`) VALUES (?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, params.get("syName").toString());
            prep.setString(2, params.get("syStartDate").toString());
            prep.setString(3, params.get("syEndDate").toString());
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a school year.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static DefaultTableModel getSchoolYearTable()
    {

        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Date> starts = new ArrayList<Date>();
        ArrayList<Date> ends = new ArrayList<Date>();
        ArrayList<String> status = new ArrayList<String>();

        final Class[] columnClasses =
        {
            Integer.class, String.class, Date.class, Date.class, String.class
        };
        DefaultTableModel model = new DefaultTableModel()
        {

            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClasses[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        try
        {
            String sql = "SELECT syID,syName,syStartDate,syEndDate,syStatus "
                    + " FROM SchoolYear ORDER BY syID";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                ids.add(rs.getInt("syID"));
                names.add(rs.getString("syName"));
                starts.add(rs.getDate("syStartDate"));
                ends.add(rs.getDate("syEndDate"));
                status.add(rs.getString("syStatus"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retreiving the table of school years.";
            logger.log(Level.SEVERE, message, e);
        }

        model.addColumn("ID", ids.toArray());
        model.addColumn("Name", names.toArray());
        model.addColumn("Start", starts.toArray());
        model.addColumn("End", ends.toArray());
        model.addColumn("Status", status.toArray());

        return model;
    }

    public static HashMap getSchoolYearDetail(int id)
    {
        HashMap<String, Object> details = new HashMap<String, Object>();
        try
        {
            String sql = "SELECT syID,syName,syStartDate,syEndDate,syStatus FROM SchoolYear where syID = ?";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                details.put("syID", rs.getInt("syID"));
                details.put("syName", rs.getString("syName"));
                details.put("syStartDate", rs.getDate("syStartDate"));
                details.put("syEndDate", rs.getDate("syEndDate"));
                details.put("syStatus", rs.getString("syStatus"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retreiving the school year details.";
            logger.log(Level.SEVERE, message, e);
        }
        return details;
    }

    public static boolean updateSchoolYear(HashMap params)
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `SchoolYear` "
                    + " SET `syName`= ?, `syStartDate`= ?, `syEndDate`= ?, "
                    + " `syStatus`=? WHERE  `syID`= ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, params.get("syName").toString());
            prep.setString(2, params.get("syStartDate").toString());
            prep.setString(3, params.get("syEndDate").toString());
            prep.setString(4, params.get("syStatus").toString());
            prep.setString(5, params.get("syID").toString());
            prep.executeUpdate();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
        }
        return successful;
    }

    public static int getCurrentSchoolYear()
    {
        int result = -1;

        try
        {
            String sql = " SELECT syID FROM SchoolYear WHERE syStatus = 'Active'";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while(rs.next())
            {
                result = rs.getInt("syID");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the school year details.";
            logger.log(Level.SEVERE, message, e);
        }
        return result;
    }
}
