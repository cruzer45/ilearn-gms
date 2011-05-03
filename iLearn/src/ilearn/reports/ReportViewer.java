package ilearn.reports;

import ilearn.kernel.Environment;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ReportViewer
{

    static final Logger logger = Logger.getLogger(ReportViewer.class.getName());

    public static void generateReport(String reportLocation, Connection connection)
    {
        try
        {
            System.out.println("Filling report...");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportLocation, new HashMap(), connection); //Fill the report
            System.out.println("Done!");
            JasperViewer.viewReport(jasperPrint, false);
        }
        catch (JRException e)
        {
            String message = "An error occurred while generating a report.";
            logger.log(Level.SEVERE, message, e);
        }
    }

    public static void generateReport(String reportLocation, Map parameters,  String reportTitle)
    {
        try
        {
            FrmReportViewer reportViewer = new FrmReportViewer(reportLocation, parameters);
            reportViewer.setBounds(0, 0, Environment.getDesktopPane().getWidth(), Environment.getDesktopPane().getHeight());
            reportViewer.setTitle(reportTitle);
            reportViewer.setVisible(true);
            Environment.getDesktopPane().add(reportViewer);
            reportViewer.setSelected(true);
        }
        catch (PropertyVetoException pve)
        {
            String message = "An error occurred while generating a report.";
            logger.log(Level.SEVERE, message, pve);
        }
    }
}
