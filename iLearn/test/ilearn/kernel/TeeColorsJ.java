/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.kernel;

/**
 *
 * @author mrogers
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class TeeColorsJ extends JFrame
        implements ActionListener,
        WindowListener
{

    Blob blob;
    Connection con = null;
    int iLength;
    ImageIcon ii;
    JButton jbConnect = new JButton("Connect"),
            jbShow = new JButton("Show It!");
    JComboBox jcb = new JComboBox();
    JLabel jlImage = new JLabel(),
            jlUserID = new JLabel("UserID:"),
            jlPassword = new JLabel(
            "Password:");
    JPanel jpCenter = new JPanel(),
            jpNorth = new JPanel(),
            jpSouth = new JPanel(
            new BorderLayout()),
            jpSouthEast = new JPanel(),
            jpSouthWest = new JPanel();
    JPasswordField jpfPassword =
            new JPasswordField(10);
    JTextArea jta = new JTextArea(
            "Errors show here.", 2, 30);
    JTextField jtUserID = new JTextField(10);
    PreparedStatement pstmt = null;
    ResourceBundle rbConnect;
    ResultSet rs;
    ResultSetMetaData rsmd;
    Statement stmt = null;
    String sDriver,
            sDriverKey = "CSDriver",
            sPassword,
            sQueryD =
            "SELECT DISTINCT TColor "
            + "FROM TeeColor",
            sQueryP =
            "SELECT TCBlob "
            + "FROM TeeColor "
            + "WHERE TColor = ?",
            srbName = "ConnectJ",
            sURL,
            sURLKey = "CSURL",
            sUserID;

    public TeeColorsJ()
    {
        super("TeeColorsJ - Available Tees");

        try  // get the PropertyResourceBundle
        {
            rbConnect = ResourceBundle.getBundle(srbName);

            sDriver = rbConnect.getString(sDriverKey);
            sURL = rbConnect.getString(sURLKey);
        }
        catch (MissingResourceException mre)
        {
            System.err.println(
                    "ResourceBundle problem for "
                    + srbName + ", program ends.");
            System.err.println("Specific error: "
                    + mre.getMessage());
            endApp(); // exit on error
        }

        jbConnect.addActionListener(this);
        jbShow.addActionListener(this);

        jpNorth.add(jlUserID);
        jpNorth.add(jtUserID);
        jpNorth.add(jlPassword);
        jpNorth.add(jpfPassword);

        jpCenter.add(jbConnect);
        jpCenter.add(jta);

        jpSouthWest.add(jcb);
        jpSouthWest.add(jbShow);
        jlImage.setPreferredSize(
                new Dimension(100, 75));
        jpSouthEast.add(jlImage);
        jpSouth.add(jpSouthEast, BorderLayout.EAST);
        jpSouth.add(jpSouthWest, BorderLayout.WEST);

        Container cp = getContentPane();
        cp.add(jpNorth, BorderLayout.NORTH);
        cp.add(jpCenter, BorderLayout.CENTER);
        cp.add(jpSouth, BorderLayout.SOUTH);

        addWindowListener(this);
        pack();

        jcb.setVisible(false);
        jbShow.setVisible(false);

        show();

    } // end constructor

    public void doConnect()
    {
        try // Attempt to load the JDBC driver
        {   // with newInstance
            Class.forName(sDriver).newInstance();
        }
        catch (Exception e)  // error
        {
            jta.setText("Failed to load current driver.");
            return;
        } // end catch

        try
        {
            con = DriverManager.getConnection(sURL,
                    sUserID,
                    sPassword);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sQueryD);
            while (rs.next())
            {
                jcb.addItem(rs.getString(1));
            }

            try
            {
                stmt.close();
            }
            catch (Exception e)
            {
            }
            rs = null;

            jcb.setVisible(true);
            jbShow.setVisible(true);

            pstmt = con.prepareStatement(sQueryP);
            jbConnect.setEnabled(false);
        }
        catch (SQLException SQLe)
        {
            reportSQLError(SQLe,
                    "problems in DoConnect():");

            if (con != null)
            {
                try
                {
                    con.close();
                }
                catch (Exception e)
                {
                }
                stmt = null;
            }

            return;
        } // end catch
        finally
        {
            if (stmt != null)
            {
                try
                {
                    stmt.close();
                }
                catch (Exception e)
                {
                }
            }
            rs = null;
        }
    } // end doConnect

    public void doRetrieve()
    {
        try
        {
            pstmt.setString(1,
                    (String) (jcb.getSelectedItem()));
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                blob = rs.getBlob(1);

                iLength = (int) (blob.length());
                ii = new ImageIcon(
                        blob.getBytes(1, iLength));

                jlImage.setIcon(ii);

                pack();

            }

        } // end try
        catch (SQLException SQLe)
        {
            reportSQLError(SQLe,
                    "problems in DoRetrieve():");
            SQLe.printStackTrace();
        }
        finally
        {
            try
            {
                rs.close();
            }
            catch (Exception e)
            {
            }
            rs = null;
        } // end finally clause

    } // end doRetrieve

    public void endApp()
    {
        if (con != null)
        {
            try
            {
                con.close();
            }
            catch (Exception e)
            {
            }
        }

        dispose();
        System.exit(0);
    }

    // ActionListener implementation
    public void actionPerformed(ActionEvent e)
    {
        Object oSource = e.getSource();

        jta.setText("No Errors.");
        if (oSource == jbShow)
        {
            doRetrieve();
            return;
        }

        if (oSource == jbConnect)
        {
            sUserID = jtUserID.getText();
            sPassword = jpfPassword.getText();
            doConnect();
            return;
        }

    } // end actionPerformed

    public void reportSQLError(SQLException SQLe,
            String s)
    {
        jta.setText(s + "\n");
        jta.append(SQLe.getMessage() + "\n");
        jta.append("SQL State: "
                + SQLe.getSQLState() + "\n");
    } // end reportSQLError

// Window Listener Implementation
    public void windowOpened(WindowEvent e)
    {
    }

    public void windowClosing(WindowEvent e)
    {
        endApp();
    }

    public void windowClosed(WindowEvent e)
    {
    }

    public void windowIconified(WindowEvent e)
    {
    }

    public void windowDeiconified(WindowEvent e)
    {
    }

    public void windowActivated(WindowEvent e)
    {
    }

    public void windowDeactivated(WindowEvent e)
    {
    }
// End Window Listener Implementation

    public static void main(String args[])
    {
        new TeeColorsJ();
    } // end main
} // end class TeeColorsJ

