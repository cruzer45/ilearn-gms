package ilearn.promotion;

import ilearn.classes.Classes;
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
 * @author mrogers
 */
public class Promote
{

    static final Logger logger = Logger.getLogger(Promote.class.getName());
    static ArrayList<String> validStates = Classes.getClassList();
    static ArrayList<String> ID = new ArrayList<String>();
    static ArrayList<String> NewClass = new ArrayList<String>();

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
        ArrayList<String> stuID = new ArrayList<String>();
        ArrayList<String> stuName = new ArrayList<String>();
        ArrayList<Boolean> stuPromoted = new ArrayList<Boolean>();
        ArrayList<String> stuNewClass = new ArrayList<String>();
        try
        {
            String sql = "SELECT `stuID`, `stuFirstName`, `stuLastName`, `stuOtherNames`, `stuStatus` FROM `iLearn`.`Student` WHERE `stuClsCode` = ? AND `stuStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, classCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                stuID.add(rs.getString("stuID"));
                stuName.add(rs.getString("stuFirstName") + " " + rs.getString("stuLastName"));
                stuPromoted.add(false);
                stuNewClass.add(" ");
            }
            prep.close();
            rs.close();
            model.addColumn("ID", stuID.toArray());
            model.addColumn("Name", stuName.toArray());
            model.addColumn("Promoted", stuPromoted.toArray());
            model.addColumn("New Class", stuNewClass.toArray());
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the list of students for a class.";
            logger.log(Level.SEVERE, message, e);
        }
        return model;
    }

    public static void addPromotion(String stuID, String newClass)
    {
        ID.add(stuID);
        NewClass.add(newClass);
    }

    public static boolean promoteStudents()
    {
        boolean successful = false;
        try
        {
            resetPromotions();
            String sqlSelect = "SELECT `proID`, `proTermID`, `proStudID`, `proNewClsCode` FROM `iLearn`.`Promotion` ORDER BY `proTermID`;";
            String sqlUpdate = "UPDATE `Student` SET `stuClsCode`= ? WHERE `stuID`=? LIMIT 1;";
            String sqlEmptyTable = "TRUNCATE `Promotion`;";
            //load the promotions
            PreparedStatement prep = Environment.getConnection().prepareStatement(sqlSelect);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                ID.add(rs.getString("proStudID"));
                NewClass.add(rs.getString("proNewClsCode"));
            }
            rs.close();
            //promote the students
            prep = Environment.getConnection().prepareStatement(sqlUpdate);
            for (int i = 0; i < ID.size(); i++)
            {
                prep.setString(1, NewClass.get(i));
                prep.setString(2, ID.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            //empty the promotions table
            prep = Environment.getConnection().prepareStatement(sqlEmptyTable);
            prep.execute();
            prep.close();
            resetPromotions();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while promoting the students to their new class.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean savePromotionDetails()
    {
        boolean successful = false;
        try
        {
            String sql = "INSERT INTO `Promotion` (`proTermID`, `proStudID`, `proNewClsCode`) VALUES (?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            String currentTerm = Term.getCurrentTerm();
            for (int i = 0; i < ID.size(); i++)
            {
                prep.setString(1, currentTerm);
                prep.setString(2, ID.get(i));
                prep.setString(3, NewClass.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while saving promotion details.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean recordPromotion()
    {
        boolean successful = false;
        return successful;
    }

    public static void resetPromotions()
    {
        ID = new ArrayList<String>();
        NewClass = new ArrayList<String>();
    }
}
