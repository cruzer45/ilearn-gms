/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.term;

import ilearn.kernel.Environment;
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
public class Term
{

    /**
     * This function tries to add a term to the system.
     * @param trmCode - The short code for the term.
     * @param trmShortName - The short name for the term.
     * @param trmLongName - The long name for the term.
     * @return A boolean indicating if it was successful or not.
     */
    public static boolean addTerm(String trmCode, String trmShortName, String trmLongName)
    {
        boolean successful = false;
        String sql = "INSERT INTO `Term` (`trmCode`, `trmShortName`, `trmLongName`) VALUES (?, ?, ?);";
        try
        {
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, trmCode);
            prep.setString(2, trmShortName);
            prep.setString(3, trmLongName);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a term to the database.";
            Logger.getLogger(Term.class.getName()).log(Level.SEVERE, message, e);
            successful = false;
        }
        return successful;
    }

    public static DefaultTableModel getTermList()
    {
        DefaultTableModel model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }
        };
        ArrayList<String> ID = new ArrayList<String>();
        ArrayList<String> trmCodes = new ArrayList<String>();
        ArrayList<String> shortNames = new ArrayList<String>();
        ArrayList<String> longNames = new ArrayList<String>();
        ArrayList<String> status = new ArrayList<String>();
        try
        {
            String sql = "SELECT `trmID`, `trmCode`, `trmShortName`, `trmLongName`, `trmStatus` FROM `iLearn`.`Term` ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                ID.add(rs.getString("trmID"));
                trmCodes.add(rs.getString("trmCode"));
                shortNames.add(rs.getString("trmShortName"));
                longNames.add(rs.getString("trmLongName"));
                status.add(rs.getString("trmStatus"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the term list.";
            Logger.getLogger(Term.class.getName()).log(Level.SEVERE, message, e);
        }
        model.addColumn("ID", ID.toArray());
        model.addColumn("Term Code", trmCodes.toArray());
        model.addColumn("Short Name", shortNames.toArray());
        model.addColumn("Long Name", longNames.toArray());
        model.addColumn("Status", status.toArray());
        return model;
    }

    public static boolean updateTerm(String ID, String trmCode, String trmShortName, String trmLongName, String status)
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Term` SET `trmCode`= ?, `trmShortName`= ?, `trmLongName`= ?, `trmStatus`=? WHERE `trmID`= ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, trmCode);
            prep.setString(2, trmShortName);
            prep.setString(3, trmLongName);
            prep.setString(4, status);
            prep.setString(5, ID);
            prep.executeUpdate();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the term info.";
            Logger.getLogger(Term.class.getName()).log(Level.SEVERE, message, e);
            successful = false;
        }
        return successful;
    }

    /**
     *
     * @Returns the ID of the current active term.
     */
    public static String getCurrentTerm()
    {
        String currentTerm = "";
        try
        {
            String sql = "SELECT `trmID` FROM `iLearn`.`Term`  WHERE `trmStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                currentTerm = rs.getString("trmID");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the current term.";
            Logger.getLogger(Term.class.getName()).log(Level.SEVERE, message, e);
        }
        return currentTerm;
    }
    /**
    *
    * @Returns the ID of the current active term.
    */
    public static String getCurrentTermName()
    {
        String currentTerm = "";
        try
        {
            String sql = "SELECT `trmLongName` FROM `iLearn`.`Term`  WHERE `trmStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                currentTerm = rs.getString("trmLongName");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the current term.";
            Logger.getLogger(Term.class.getName()).log(Level.SEVERE, message, e);
        }
        return currentTerm;
    }
}
