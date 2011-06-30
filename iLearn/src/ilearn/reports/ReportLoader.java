/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.reports;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mrogers
 */
public class ReportLoader
{
    static final Logger logger =  Logger.getLogger(DialogStudentByClass.class.getName());

    public static void showRepeatingStudents()
    {
        String report = "reports/List_of_Repeating_Students.jasper";
        String title = "Report - Student Currently Repeating";
        // Second, create a map of parameters to pass to the report.
        Map parameters = new HashMap();
        parameters.put("SUBREPORT_DIR", "reports/");
        try
        {
            ReportViewer.generateReport(report, parameters, title);
        }
        catch (Exception exception)
        {
            String message = "An error occurred while generating a report.";
            logger.log(Level.SEVERE, message, exception);
        }
    }
}
