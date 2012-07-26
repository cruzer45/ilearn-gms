/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.transcript;

import ilearn.kernel.Utilities;
import ilearn.school.School;
import ilearn.student.Student;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
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

            sheet.createRow(finishRow).createCell(4).setCellValue("Student Transcript");
            finishRow += 5;
            //Student General Info
            Row studentNameRow = sheet.createRow(finishRow);
            studentNameRow.createCell(0).setCellValue("Student Name:");
            String studentName = studentDetails.get("stuFirstName").toString() + " " + studentDetails.get("stuOtherNames").toString() + " " + studentDetails.get("stuLastName").toString();
            studentNameRow.createCell(2).setCellValue(studentName);
            finishRow++;

            Row parentRow = sheet.createRow(finishRow);
            parentRow.createCell(0).setCellValue("Parent / Guardian:");
            String parent = studentDetails.get("stuPCName").toString();
            if (!studentDetails.get("stuSCName").toString().trim().isEmpty())
            {
                parent = parent + " & " + studentDetails.get("stuSCName").toString();
            }
            parentRow.createCell(2).setCellValue(parent);
            finishRow++;

            Row dateOfBirth = sheet.createRow(finishRow);
            dateOfBirth.createCell(0).setCellValue("Date Of Birth:");
            String dob = Utilities.MDY_Formatter.format((Date) studentDetails.get("stuDOB"));
            dateOfBirth.createCell(2).setCellValue(dob);
            finishRow++;

            Row studentAddress = sheet.createRow(finishRow);
            studentAddress.createCell(0).setCellValue("Address:");
            String stuAddress = studentDetails.get("stuAddress1").toString();
            stuAddress.replaceAll("\n", "");
            studentAddress.createCell(2).setCellValue(stuAddress);
            finishRow++;

            Row entranceDate = sheet.createRow(finishRow);
            entranceDate.createCell(0).setCellValue("Entrance Date:");
            String stuEntranceDate = Utilities.MonthYear_Formatter.format((Date) studentDetails.get("stuRegistrationDate"));
            stuEntranceDate.replaceAll("\n", "");
            entranceDate.createCell(2).setCellValue(stuEntranceDate);
            finishRow++;

            //Save Workbook
            FileOutputStream fileOut = new FileOutputStream(selectedFile);
            wb.write(fileOut);
            fileOut.close();
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
}
