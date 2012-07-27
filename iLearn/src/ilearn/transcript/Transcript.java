/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.transcript;

import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.school.School;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;

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
            HashMap schoolDetails = School.getSchoolDetails();
            School.downloadSignature();
            School.downloadlogo();
            //CreateWorkbook
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("Transcript - " + studentDetails.get("stuFirstName").toString() + " " + studentDetails.get("stuLastName").toString());
            Row topRow = sheet.createRow(0);
            Cell cell = topRow.createCell(0);
            int finishRow = 0;
            //Insert Logo
            //add picture data to this workbook.
            InputStream inputStream = new FileInputStream("images/school_logo.jpg");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            inputStream.close();
            //Add helper
            CreationHelper helper = wb.getCreationHelper();
            // Create the drawing patriarch.  This is the top level container for all shapes.
            Drawing drawing = sheet.createDrawingPatriarch();
            //add a picture shape
            ClientAnchor anchor = helper.createClientAnchor();
            //set top-left corner of the picture,
            //subsequent call of Picture#resize() will operate relative to it
            anchor.setCol1(0);
            anchor.setRow1(0);
            anchor.setCol2(3);
            anchor.setRow2(9);
            Picture pict = drawing.createPicture(anchor, pictureIdx);
            //pict.resize();
            //Insert school Info
            topRow.createCell(4).setCellValue(schoolDetails.get("schName").toString());
            finishRow++;
            String schoolAddress = schoolDetails.get("schAddress").toString();
            String[] addressLines = schoolAddress.split("\n");
            for (int i = 0; i < addressLines.length; i++)
            {
                String addLine = addressLines[i];
                addLine.trim();
                sheet.createRow(finishRow).createCell(4).setCellValue(addLine);
                finishRow++;
            }
            finishRow++;
            //Student Transcript
            sheet.createRow(finishRow).createCell(4).setCellValue("Student Transcript");
            finishRow += 5;
            //Student General Info
            Row studentNameRow = sheet.createRow(finishRow);
            studentNameRow.createCell(0).setCellValue("Student Name:");
            String studentName = studentDetails.get("stuFirstName").toString() + " " + studentDetails.get("stuOtherNames").toString() + " " + studentDetails.get("stuLastName").toString();
            studentNameRow.createCell(2).setCellValue(studentName);
            finishRow++;
            //Parent / Guardian
            Row parentRow = sheet.createRow(finishRow);
            parentRow.createCell(0).setCellValue("Parent / Guardian:");
            String parent = studentDetails.get("stuPCName").toString();
            if (!studentDetails.get("stuSCName").toString().trim().isEmpty())
            {
                parent = parent + " & " + studentDetails.get("stuSCName").toString();
            }
            parentRow.createCell(2).setCellValue(parent);
            finishRow++;
            //Date Of Birth
            Row dateOfBirth = sheet.createRow(finishRow);
            dateOfBirth.createCell(0).setCellValue("Date Of Birth:");
            String dob = Utilities.MDY_Formatter.format((Date) studentDetails.get("stuDOB"));
            dateOfBirth.createCell(2).setCellValue(dob);
            finishRow++;
            //Address
            Row studentAddress = sheet.createRow(finishRow);
            studentAddress.createCell(0).setCellValue("Address:");
            String stuAddress = studentDetails.get("stuAddress1").toString();
            stuAddress.replaceAll("\n", "");
            studentAddress.createCell(2).setCellValue(stuAddress);
            finishRow++;
            //Entrance Date
            Row entranceDate = sheet.createRow(finishRow);
            entranceDate.createCell(0).setCellValue("Entrance Date:");
            String stuEntranceDate = Utilities.MonthYear_Formatter.format((Date) studentDetails.get("stuRegistrationDate"));
            stuEntranceDate.replaceAll("\n", "");
            entranceDate.createCell(2).setCellValue(stuEntranceDate);
            finishRow += 3;
            //List School Years Attended
            Row formRow = sheet.createRow(finishRow);
            formRow.createCell(0).setCellValue("Form");
            finishRow++;
            Row yearRow = sheet.createRow(finishRow);
            yearRow.createCell(0).setCellValue("Year");
            finishRow++;
            //Get school years attended
            ArrayList<HashMap> schoolYears = getSchoolYearsAttended(stuID);
            //Print them on the sheet
            for (int i = 0; i < schoolYears.size(); i++)
            {
                HashMap<String, String> schoolYear = schoolYears.get(i);
                formRow.createCell(2 + i).setCellValue(schoolYear.get("yrgraClsCode"));
                yearRow.createCell(2 + i).setCellValue(schoolYear.get("syName"));
            }
            finishRow++;
            //Get the subjects taken
            ArrayList<String> subjectsTaken = getSubjectsTaken(stuID);
            //List Subjects Taken
            for (int i = 0; i < subjectsTaken.size(); i++)
            {
                sheet.createRow(finishRow + i).createCell(0).setCellValue(subjectsTaken.get(i));
            }
            //Fill in the grade
            //Navigate the sheet and get the parameters for the grade;
            int lastColumn = schoolYears.size() + 2;
            int lastRow = finishRow + subjectsTaken.size();
            //Loop through and insert the grades
            //start with the columns
            for (int i = 2; i < lastColumn; i++)
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
                    currentRow.createCell(i).setCellValue(grade);
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
            String message = "An error occurred while trying to generate a student's transcripts";
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
