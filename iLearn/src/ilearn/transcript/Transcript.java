/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.transcript;

import ilearn.school.School;
import ilearn.student.Student;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
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
            cell.setCellValue(1);
            //Insert Logo
            //add picture data to this workbook.
            InputStream is = new FileInputStream("images/school_logo.jpg");
            byte[] bytes = IOUtils.toByteArray(is);
            int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            is.close();
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
            anchor.setRow2(8);
            Picture pict = drawing.createPicture(anchor, pictureIdx);
            //pict.resize();
            //Insert school Info
            topRow.createCell(4).setCellValue(schoolDetails.get("schName").toString());
            String address = schoolDetails.get("schAddress").toString();
            //address.sp
            sheet.createRow(1).createCell(4).setCellValue(address);
            //TODO Finish adding the school details.
            
            
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
