/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmReportViewer.java
 *
 * Created on Feb 17, 2010, 2:47:00 PM
 */
package ilearn.reports;

import ilearn.kernel.Environment;
import java.awt.BorderLayout;
import java.awt.Container;
import java.sql.Connection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author mrogers
 */
public class FrmReportViewer extends javax.swing.JInternalFrame
{

    static final Logger logger = Logger.getLogger(FrmReportViewer.class.getName());

    /** Creates new form FrmReportViewer */
    private FrmReportViewer()
    {
        super("Report Viewer", true, true, true, true);
        initComponents();
        setBounds(10, 10, 600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public FrmReportViewer(String fileName)
    {
        this(fileName, null);
    }

    public FrmReportViewer(String fileName, Map parameter)
    {
        this();
        try
        {
            JasperPrint print = JasperFillManager.fillReport(fileName, parameter, Environment.getConnection());
            JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.setLayout(new BorderLayout());
            c.add(viewer);
        }
        catch (JRException jre)
        {
            String message = "An error occurred while generating a report.";
            logger.log(Level.SEVERE, message, jre);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Report Viewer");
        setFrameIcon(null);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}