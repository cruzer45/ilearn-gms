/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.kernel;

/**
 *
 * @author m.rogers
 */

/*
Core SWING Advanced Programming
By Kim Topley
ISBN: 0 13 083292 8
Publisher: Prentice Hall
 */
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;

public class ComboBoxTable
{

    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception evt)
        {
        }

        JFrame f = new JFrame("Combo Box Table");
        JTable tbl = new JTable(new ComboBoxTableModel());

        // Create the combo box editor
        JComboBox comboBox = new JComboBox(ComboBoxTableModel.getValidStates());
        comboBox.setEditable(true);
        DefaultCellEditor editor = new DefaultCellEditor(comboBox);

        // Assign the editor to the second column
        TableColumnModel tcm = tbl.getColumnModel();
        tcm.getColumn(2).setCellEditor(editor);

        // Set column widths
        tcm.getColumn(0).setPreferredWidth(200);
        tcm.getColumn(1).setPreferredWidth(100);
        tcm.getColumn(2).setPreferredWidth(150);

        // Set row heighht
        tbl.setRowHeight(20);

        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl.setPreferredScrollableViewportSize(tbl.getPreferredSize());

        f.getContentPane().add(new JScrollPane(tbl), "Center");
        f.pack();
        f.addWindowListener(new WindowAdapter()
        {

            @Override
            public void windowClosing(WindowEvent evt)
            {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }
}

class ComboBoxTableModel extends AbstractTableModel
{
    // Implementation of TableModel interface

    public int getRowCount()
    {
        return data.length;
    }

    public int getColumnCount()
    {
        return COLUMN_COUNT;
    }

    public Object getValueAt(int row, int column)
    {
        return data[row][column];
    }

    @Override
    public Class getColumnClass(int column)
    {
        return (data[0][column]).getClass();
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
        boolean editable = false;
        if (column == 2)
        {
            editable = true;
        }
        return editable;
    }

    @Override
    public void setValueAt(Object value, int row, int column)
    {
//        if (isValidValue(value))
//        {
        data[row][column] = value;
        fireTableRowsUpdated(row, row);
//        }
    }

    // Extra public methods
    public static String[] getValidStates()
    {
        return validStates;
    }

    // Protected methods
    protected boolean isValidValue(Object value)
    {
        if (value instanceof String)
        {
            String sValue = (String) value;

            for (int i = 0; i < validStates.length; i++)
            {
                if (sValue.equals(validStates[i]))
                {
                    return true;
                }
            }
        }

        return false;
    }
    protected static final int COLUMN_COUNT = 3;
    protected static final String[] validStates =
    {
        "On order", "In stock", "Out of print"
    };
    protected Object[][] data = new Object[][]
    {
        {
            "Core Java Volume 1", "Test", validStates[0]
        },
        {
            "Core Java Volume 2", "Test", validStates[0]
        },
        {
            "Core Web Programming", "Test", validStates[0]
        },
        {
            "Core Visual Basic 5", "Test", validStates[0]
        },
        {
            "Core Java Foundation Classes", "Test", validStates[0]
        }
    };
    protected static final String[] columnNames =
    {
        "Book Name", "Test", "Status"
    };
}
