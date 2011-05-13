/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.register;

import ilearn.kernel.Environment;
import ilearn.term.Term;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author m.rogers
 */
public class Register
{

    static final Logger logger = Logger.getLogger(Register.class.getName());

    public static DefaultTableModel getStudentList(String classCode)
    {
        DefaultTableModel model = new DefaultTableModel()
        {
            @Override
            public Class getColumnClass(int columnIndex)
            {
                try
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
                catch (Exception e)
                {
                    return Object.class;
                }
            }
            @Override
            public boolean isCellEditable(int rowIndex, int ColIndex)
            {
                //Only allow the certain columns to be editable.
                boolean editable = false;
                if (ColIndex == 2 || ColIndex == 3 || ColIndex == 4 || ColIndex == 5)
                {
                    editable = true;
                }
                return editable;
            }
            @Override
            public void setValueAt(Object value, int row, int column)
            {
                Vector rowVector = (Vector) dataVector.elementAt(row);
                rowVector.setElementAt(value, column);
                fireTableCellUpdated(row, column);
                return;
            }
        };
        ArrayList<String> studentID = new ArrayList<String>();
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<Boolean> absent = new ArrayList<Boolean>();
        ArrayList<Integer> demerit = new ArrayList<Integer>();
        ArrayList<Boolean> tardy = new ArrayList<Boolean>();
        ArrayList<String> remark = new ArrayList<String>();
        try
        {
            String sql = "SELECT `stuID`, CONCAT_WS(' ',`stuFirstName`, `stuLastName`) AS `Name` FROM `iLearn`.`Student` WHERE `stuClsCode` = ? AND `stuStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, classCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                studentID.add(rs.getString("stuID"));
                name.add(rs.getString("Name"));
                absent.add(false);
                demerit.add(0);
                tardy.add(false);
                remark.add(" ");
            }
            prep.close();
            rs.close();
            model.addColumn("ID", studentID.toArray());
            model.addColumn("Name", name.toArray());
            model.addColumn("Absent", absent.toArray());
            model.addColumn("Demerit", demerit.toArray());
            model.addColumn("Tardy", tardy.toArray());
            model.addColumn("Remark", remark.toArray());
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the list of students for a class.";
            logger.log(Level.SEVERE, message, e);
        }
        return model;
    }

    public static boolean addRegister(String date, ArrayList<String> stuID, ArrayList<Boolean> absent, ArrayList<Integer> demerit, ArrayList<Boolean> tardy, ArrayList<String> remarks)
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `RollCall` (`rolStuID`, `rolTrmCode`, `rolDate`, `rolAbsent`, `rolTardy`, `rolDemerit`, `rolRemark`) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            String term = Term.getCurrentTerm();
            for (int i = 0; i < stuID.size(); i++)
            {
                prep.setString(1, stuID.get(i));
                prep.setString(2, term);
                prep.setString(3, date);
                prep.setBoolean(4, absent.get(i));
                prep.setBoolean(5, tardy.get(i));
                prep.setInt(6, demerit.get(i));
                prep.setString(7, remarks.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while saving the register.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }
}
