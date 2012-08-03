package ilearn.transcript;

import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.student.Student;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author m.rogers
 */
public class Transcript
{
    
    static final Logger logger = Logger.getLogger(Transcript.class.getName());
    
    public static void generateTranscript(String stuID, File selectedFile)
    {
        try
        {
            //Prepare the info
            HashMap studentDetails = Student.getStudentDetails(stuID);
            //CreateWorkbook
            InputStream inp = new FileInputStream("images/transcript.xls");
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);
            int finishRow = 7;
            //Student General Info
            Row studentNameRow = sheet.getRow(finishRow);
            String studentName = studentDetails.get("stuFirstName").toString() + " " + studentDetails.get("stuOtherNames").toString() + " " + studentDetails.get("stuLastName").toString();
            studentNameRow.getCell(1).setCellValue(studentName);
            finishRow++;
            //Parent / Guardian
            Row parentRow = sheet.getRow(finishRow);
            //parentRow.createCell(0).setCellValue("Parent / Guardian:");
            String parent = studentDetails.get("stuPCName").toString();
            if (!studentDetails.get("stuSCName").toString().trim().isEmpty())
            {
                parent = parent + " & " + studentDetails.get("stuSCName").toString();
            }
            parentRow.getCell(1).setCellValue(parent);
            finishRow++;
            //Date Of Birth
            Row dateOfBirth = sheet.getRow(finishRow);
            //dateOfBirth.createCell(0).setCellValue("Date Of Birth:");
            String dob = Utilities.MDY_Formatter.format((Date) studentDetails.get("stuDOB"));
            dateOfBirth.getCell(1).setCellValue(dob);
            finishRow++;
            //Address
            Row studentAddress = sheet.getRow(finishRow);
            ///studentAddress.getCell(0).setCellValue("Address:");
            String stuAddress = studentDetails.get("stuAddress1").toString();
            stuAddress.replaceAll("\n", "");
            studentAddress.getCell(1).setCellValue(stuAddress);
            finishRow++;
            //Entrance Date
            Row entranceDate = sheet.getRow(finishRow);
            //entranceDate.createCell(0).setCellValue("Entrance Date:");
            String stuEntranceDate = Utilities.MonthYear_Formatter.format((Date) studentDetails.get("stuRegistrationDate"));
            stuEntranceDate.replaceAll("\n", "");
            entranceDate.getCell(1).setCellValue(stuEntranceDate);
            finishRow += 4;
            //List School Years Attended
            Row formRow = sheet.getRow(finishRow);
            //formRow.createCell(0).setCellValue("Form");
            finishRow++;
            Row yearRow = sheet.getRow(finishRow);
            //yearRow.createCell(0).setCellValue("Year");
            finishRow++;
            //Get school years attended
            ArrayList<HashMap> schoolYears = getSchoolYearsAttended(stuID);
            //Print them on the sheet
            for (int i = 0; i < schoolYears.size(); i++)
            {
                HashMap<String, String> schoolYear = schoolYears.get(i);
                formRow.getCell(1 + i).setCellValue(schoolYear.get("yrgraClsCode"));
                yearRow.getCell(1 + i).setCellValue(schoolYear.get("syName"));
            }
            finishRow++;
            //Get the subjects taken
            ArrayList<String> subjectsTaken = getSubjectsTaken(stuID);
            //List Subjects Taken
            for (int i = 0; i < subjectsTaken.size(); i++)
            {
                sheet.getRow(finishRow + i).getCell(0).setCellValue(subjectsTaken.get(i));
            }
            //Fill in the grade
            //Navigate the sheet and get the parameters for the grade;
            int lastColumn = schoolYears.size() + 2;
            int lastRow = finishRow + subjectsTaken.size();
            //Loop through and insert the grades
            //start with the columns
            for (int i = 1; i < lastColumn; i++)
            {
                //Then go by rows
                for (int j = finishRow; j < lastRow; j++)
                {
                    Row currentRow = sheet.getRow(j);
                    //Get the info we will need for the query
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("stuID", stuID);
                    params.put("yrgraClsCode", formRow.getCell(i).getStringCellValue());
                    params.put("syName", yearRow.getCell(i).getStringCellValue());
                    params.put("subName", currentRow.getCell(0).getStringCellValue());
                    //get the grade and insert it into the cell
                    String grade = getTranscriptGrade(params);
                    currentRow.getCell(i).setCellValue(grade);
                }
            }
            //Save Workbook
            FileOutputStream fileOut = new FileOutputStream(selectedFile);
            wb.write(fileOut);
            fileOut.close();
            //Open the file.
            if (Desktop.isDesktopSupported())
            {
                Desktop.getDesktop().open(selectedFile);
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while trying to generate a student's transcript.\n\n"
                    + "Kindly ensure that you have the template installed.";
            Utilities.showErrorMessage(null, message);
            logger.log(Level.SEVERE, message, e);
        }
    }
    
    private static ArrayList<HashMap> getSchoolYearsAttended(String stuID)
    {
        ArrayList schoolYearsAttended = new ArrayList();
        try
        {
            String sql = "SELECT DISTINCT syName, yrgraClsCode FROM SchoolYear INNER JOIN Grade_Year_Average ON yrgraSchYear = syID WHERE yrgraStuID = ? ORDER BY syID";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                HashMap<String, String> schoolYear = new HashMap<String, String>();
                schoolYear.put("syName", rs.getString("syName"));
                schoolYear.put("yrgraClsCode", rs.getString("yrgraClsCode"));
                schoolYearsAttended.add(schoolYear);
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while trying to retrieve the years attended for this student.";
            logger.log(Level.SEVERE, message, e);
        }
        return schoolYearsAttended;
    }
    
    private static ArrayList getSubjectsTaken(String stuID)
    {
        ArrayList subjectsTaken = new ArrayList();
        try
        {
            String sql = "SELECT DISTINCT subName FROM Subject INNER JOIN Grade_Year_Average ON yrgraSubCode = subCode WHERE yrgraStuID = ? ORDER BY yrgraSchYear, yrgraSubCode";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                subjectsTaken.add(rs.getString("subName"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while trying to retrieve the subjects this student took.";
            logger.log(Level.SEVERE, message, e);
        }
        return subjectsTaken;
    }
    
    private static String getTranscriptGrade(HashMap params)
    {
        String grade = "";
        try
        {
            String sql = "SELECT ROUND(yrgraYearAverage) as 'grade' "
                    + " FROM Grade_Year_Average "
                    + " INNER JOIN Subject ON subCode = yrgraSubCode "
                    + " INNER JOIN SchoolYear ON syID = yrgraSchYear "
                    + " WHERE yrgraStuID = ? AND yrgraClsCode = ? AND  syName = ? AND subName = ? ";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, params.get("stuID").toString());
            prep.setString(2, params.get("yrgraClsCode").toString());
            prep.setString(3, params.get("syName").toString());
            prep.setString(4, params.get("subName").toString());
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                grade = rs.getString("grade");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while trying to retrieve the studen't transcript grade.";
            logger.log(Level.SEVERE, message, e);
        }
        return grade;
    }
}
