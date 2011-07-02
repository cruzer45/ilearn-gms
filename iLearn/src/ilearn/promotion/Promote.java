/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.promotion;

import ilearn.classes.Classes;
import ilearn.kernel.Environment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mrogers
 */
public class Promote
{

    static final Logger logger = Logger.getLogger(Promote.class.getName());
    static ArrayList<String> validStates = Classes.getClassList();

    public static DefaultTableModel getStudentList(String classCode)
    {
        DefaultTableModel model = new DefaultTableModel()
        {

            @Override
            public Class getColumnClass(int columnIndex)
            {
                Object o = getValueAt(0, columnIndex);
//                if (o == null)
//                {
//                    return Object.class;
//                }
//                else
//                {
                return o.getClass();
//                }
            }

            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                //Only allow the promoted and new class columns to be editable.
                boolean editable = false;
                if (mColIndex == 2)
                {
                    editable = true;
                }
                if (mColIndex == 3)
                {
                    boolean canEdit = (Boolean) getValueAt(rowIndex, 2);
                    if (!canEdit)
                    {
                        setValueAt(" ", rowIndex, 3);
                    }
                    return canEdit;
                }
                return editable;
                //return false;
            }

            @Override
            public void setValueAt(Object value, int row, int column)
            {
                //If they are changing the promote column just accept the changes.
                if (column == 2)
                {
                    Vector rowVector = (Vector) dataVector.elementAt(row);
                    rowVector.setElementAt(value, column);
                    fireTableCellUpdated(row, column);
                    return;
                }
                //If it is the new class column only accept specific values.
                if (isValidValue(value))
                {
                    Vector rowVector = (Vector) dataVector.elementAt(row);
                    rowVector.setElementAt(value, column);
                    fireTableCellUpdated(row, column);
                }
            }

            protected boolean isValidValue(Object value)
            {
                String sValue = (String) value;
                sValue = sValue.trim();
                if (value instanceof String)
                {
                    for (String cls : validStates)
                    {
                        if (sValue.equals(cls))
                        {
                            return true;
                        }
                    }
                }
                return false;
            }
        };
        ArrayList<String> studentID = new ArrayList<String>();
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<Boolean> promoted = new ArrayList<Boolean>();
        ArrayList<String> newClass = new ArrayList<String>();
        try
        {
            String sql = "SELECT `stuID`, `stuFirstName`, `stuLastName`, `stuOtherNames`, `stuStatus` FROM `iLearn`.`Student` WHERE `stuClsCode` = ? AND `stuStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, classCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                studentID.add(rs.getString("stuID"));
                name.add(rs.getString("stuFirstName") + " " + rs.getString("stuLastName"));
                promoted.add(false);
                newClass.add(" ");
            }
            prep.close();
            rs.close();
            model.addColumn("ID", studentID.toArray());
            model.addColumn("Name", name.toArray());
            model.addColumn("Promoted", promoted.toArray());
            model.addColumn("New Class", newClass.toArray());
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the list of students for a class.";
            logger.log(Level.SEVERE, message, e);
        }
        return model;
    }
}
