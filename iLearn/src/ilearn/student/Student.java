/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.student;

import ilearn.kernel.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m.rogers
 */
public class Student
{

    static final Logger logger = Logger.getLogger(Student.class.getName());

    public static boolean addStudent(String stuFirstName, String stuLastName, String stuOtherNames, String stuDOB, String stuGender,
            String stuEmail, String stuPhone, File stuPhoto, String stuAddress1, String stuAddress2,
            String stuPCName, String stuPCPhone, String stuSCName, String stuPCAddress, String stuSCPhone, String stuSCAddress,
            String stuDoctorName, String stuDoctorContact, String stuHospital, String stuClsCode)
    {
        boolean successful = false;

        try
        {

            FileInputStream fis = new FileInputStream(stuPhoto);

            String sql = "INSERT INTO `Student` "
                    + "(`stuFirstName`, `stuLastName`, `stuOtherNames`, `stuDOB`, `stuGender`, `stuEmail`, "
                    + "`stuPhone`, `stuPhoto`, `stuAddress1`, `stuAddress2`, `stuPCName`, `stuPCPhone`, "
                    + "`stuPCAddress`, `stuSCName`, `stuSCPhone`, `stuSCAddress`, `stuDoctorName`, "
                    + "`stuDoctorContact`, `stuHospital`, `stuClsCode`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuFirstName);
            prep.setString(2, stuLastName);
            prep.setString(3, stuOtherNames);
            prep.setString(4, stuDOB);
            prep.setString(5, stuGender);
            prep.setString(6, stuEmail);
            prep.setString(7, stuPhone);
            prep.setBlob(8, fis, stuPhoto.length());
            prep.setString(9, stuAddress1);
            prep.setString(10, stuAddress2);
            prep.setString(11, stuPCName);
            prep.setString(12, stuPCPhone);
            prep.setString(13, stuPCAddress);
            prep.setString(14, stuSCName);
            prep.setString(15, stuSCPhone);
            prep.setString(16, stuSCAddress);
            prep.setString(17, stuDoctorName);
            prep.setString(18, stuDoctorContact);
            prep.setString(19, stuHospital);
            prep.setString(20, stuClsCode);


            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a student to the system.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }
}
