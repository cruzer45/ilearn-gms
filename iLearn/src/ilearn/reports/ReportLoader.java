package ilearn.reports;

import java.util.Date;
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

    static final Logger logger = Logger.getLogger(DialogStudentByClass.class.getName());

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

    public static void showMidTermReports()
    {
        String report = "reports/MidTerm_Report_WIth_GPA.jasper";
        String title = "Report - Mid-Term Report Cards";
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

    public static void showMidTermClassRankingReport()
    {
        String report = "reports/Class_Ranking_MidTerm.jasper";
        String title = "Report - Mid-Term Class Rank";
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

    public static void showTermEndClassRankingReport()
    {
        String report = "reports/Class_Ranking_TermEnd.jasper";
        String title = "Report - Term End Class Rank";
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

    public static void showStudentsByClass()
    {
        String report = "reports/Student_Class_List.jasper";
        String title = "Report - Student List by Class";
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

    public static void showClassSizeDistribution()
    {
        String report = "reports/Student_Class_Count.jasper";
        String title = "Report - Class Size Distribution";
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

    public static void showDemeritsByClass()
    {
        String report = "reports/Demerit_Report.jasper";
        String title = "Report - Demerits By Class";
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

    public static void showDemeritsByStudent(String stuID)
    {
        String report = "reports/Demerit_By_Student.jasper";
        String title = "Report - Demerits By Student";
        // Second, create a map of parameters to pass to the report.
        Map parameters = new HashMap();
        parameters.put("SUBREPORT_DIR", "reports/");
        parameters.put("stuID", stuID);
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

    public static void showGenderBreakdown()
    {
        String report = "reports/StudentGenderDistribution.jasper";
        String title = "Report - Gender Breakdown";
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

    public static void printIDCards(Date date, String background)
    {
        String report = "reports/ID_Cards.jasper";
        String title = "Print Student ID Cards";
        // Second, create a map of parameters to pass to the report.
        Map parameters = new HashMap();
        parameters.put("SUBREPORT_DIR", "reports/");
        parameters.put("CardBG", background);
        parameters.put("ValidToDate", date);
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

    public static void showDetentionReport()
    {
        String report = "reports/Dention_List.jasper";
        String title = "Detention Report";
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

    public static void showTermEndReport()
    {
        String report = "reports/FinalGrade_Report_with_GPA.jasper";
        String title = "TErm End Report Card";
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
