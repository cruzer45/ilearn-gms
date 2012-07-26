package ilearn.scripts;

import ilearn.kernel.Environment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mrogers
 */
public class ScriptModules
{

    static final Logger logger = Logger.getLogger(ScriptModules.class.getName());

    public static boolean addScriptModule(HashMap params)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `listScriptModules` (`smModuleName`, `smSection`) VALUES (?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, params.get("smModuleName").toString());
            prep.setString(2, params.get("smSection").toString());
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a script module to the system.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static DefaultTableModel getScriptModuleTable()
    {
        DefaultTableModel model = new DefaultTableModel()
        {
            final Class<?>[] columnTypes =
            {
                Integer.class, String.class, String.class
            };
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnTypes[columnIndex];
            }
        };
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<String> modules = new ArrayList<String>();
        ArrayList<String> sections = new ArrayList<String>();
        try
        {
            String sql = "SELECT smID, smModuleName, smSection "
                         + " FROM listScriptModules ORDER BY smModuleName, smSection;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                ids.add(rs.getInt("smID"));
                modules.add(rs.getString("smModuleName"));
                sections.add(rs.getString("smSection"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a loading the script module table.";
            logger.log(Level.SEVERE, message, e);
        }
        model.addColumn("ID", ids.toArray());
        model.addColumn("Module", modules.toArray());
        model.addColumn("Section", sections.toArray());
        return model;
    }

    public static boolean deleteScriptModule(int moduleID)
    {
        boolean successful = false;
        try
        {
            String sql = "DELETE FROM `listScriptModules` WHERE  `smID`= ? LIMIT 1;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setInt(1, moduleID);
            prep.executeUpdate();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a removing the script module.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static ArrayList<String> getScriptModuleList()
    {
        ArrayList<String> list = new ArrayList<String>();
        try
        {
            String sql = "SELECT DISTINCT UPPER(smModuleName) as 'smModuleName' FROM listScriptModules ORDER BY smModuleName";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                list.add(rs.getString("smModuleName"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while loading the  script module list.";
            logger.log(Level.SEVERE, message, e);
        }
        return list;
    }
    public static ArrayList<String> getScriptSectionList(String module)
    {
        ArrayList<String> list = new ArrayList<String>();
        try
        {
            String sql = "SELECT DISTINCT UPPER(smSection) AS 'smSection' FROM listScriptModules WHERE UPPER(smModuleName) = ? ORDER BY smModuleName,smSection";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, module);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                list.add(rs.getString("smSection"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while loading the  script module list.";
            logger.log(Level.SEVERE, message, e);
        }
        return list;
    }

    public static HashMap getScriptModuleDetails(int id)
    {
        HashMap<String, String> details = new HashMap<String, String>();
        try
        {
            String sql = "SELECT smID, smModuleName, smSection FROM listScriptModules WHERE smID = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            while(rs.next())
            {
                details.put("smID", rs.getString("smID"));
                details.put("smModuleName", rs.getString("smModuleName"));
                details.put("smSection", rs.getString("smSection"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while loading the  script module details.";
            logger.log(Level.SEVERE, message, e);
        }
        return details;
    }

    public static int findIDByModuleNameAndSection(String module, String section)
    {
        int result = -1;
        try
        {
            String sql = "SELECT smID FROM listScriptModules WHERE smModuleName = ? AND smSection = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, module);
            prep.setString(2, section);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                result = rs.getInt("smID");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while loading the  script module details.";
            logger.log(Level.SEVERE, message, e);
        }
        return result;
    }
}
