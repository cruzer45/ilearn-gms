package ilearn.student;

import ilearn.classes.Classes;
import ilearn.grades.Grade;
import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.subject.Subject;
import ilearn.term.Term;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author m.rogers
 */
public class Student
{

    static final Logger logger = Logger.getLogger(Student.class.getName());

    public static boolean saveStudent(String stuFirstName, String stuLastName, String stuOtherNames, String stuDOB, String stuGender,
            String stuEmail, String stuPhone, File stuPhoto, String stuAddress1, String stuAddress2,
            String stuPCName, String stuPCPhone, String stuSCName, String stuPCAddress, String stuSCPhone, String stuSCAddress,
            String stuDoctorName, String stuDoctorContact, String stuHospital, String stuClsCode,
            String stuPSEGrade, String stuFeederSchool, boolean stuRepeating, String stuSpecialNeeds, String stuNotes, String stuSSN, boolean stuNonBelizean)
    {
        boolean successful = false;
        try
        {
            FileInputStream fis = new FileInputStream(stuPhoto);
            String sql = "INSERT INTO `Student` "
                    + "(`stuFirstName`, `stuLastName`, `stuOtherNames`, `stuDOB`, `stuGender`, `stuEmail`, "
                    + "`stuPhone`, `stuPhoto`, `stuAddress1`, `stuAddress2`, `stuPCName`, `stuPCPhone`, "
                    + "`stuPCAddress`, `stuSCName`, `stuSCPhone`, `stuSCAddress`, `stuDoctorName`, "
                    + "`stuDoctorContact`, `stuHospital`, `stuClsCode`, `stuPSEGrade`, "
                    + "`stuFeederSchool`, `stuRepeating`, `stuSpecialNeeds`, `stuNotes`, `stuSSN`, `stuNonBelizean`) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,? );";
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
            prep.setString(21, stuPSEGrade);
            prep.setString(22, stuFeederSchool);
            prep.setBoolean(23, stuRepeating);
            prep.setString(24, stuSpecialNeeds);
            prep.setString(25, stuNotes);
            prep.setString(26, stuSSN);//Added Jul 8, 2011
            prep.setBoolean(27, stuNonBelizean);
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

    public static DefaultTableModel searchStudents(String criteria)
    {
        criteria = Utilities.percent(criteria);
        DefaultTableModel model = new DefaultTableModel()
        {

            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }
        };
        ArrayList<String> studentID = new ArrayList<String>();
        ArrayList<String> firstName = new ArrayList<String>();
        ArrayList<String> lastName = new ArrayList<String>();
        ArrayList<String> classCode = new ArrayList<String>();
        try
        {
            String sql = "SELECT `stuID`, `stuFirstName`, `stuLastName`, `stuOtherNames`, `stuClsCode` FROM `Student` "
                    + "WHERE ( `stuID` LIKE ?  OR "
                    + "`stuFirstName` LIKE ?  OR "
                    + "`stuLastName` LIKE ?  OR "
                    + "`stuOtherNames` LIKE ?  OR "
                    + "`stuSSN` LIKE ? OR"
                    + "`stuClsCode` LIKE ? ) AND `stuStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, criteria);
            prep.setString(2, criteria);
            prep.setString(3, criteria);
            prep.setString(4, criteria);
            prep.setString(5, criteria);
            prep.setString(6, criteria);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                studentID.add(rs.getString("stuID"));
                firstName.add(rs.getString("stuFirstName"));
                lastName.add(rs.getString("stuLastName"));
                classCode.add(rs.getString("stuClsCode"));
            }
            prep.close();
            rs.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a student to the system.";
            logger.log(Level.SEVERE, message, e);
        }
        model.addColumn("ID", studentID.toArray());
        model.addColumn("First Name", firstName.toArray());
        model.addColumn("Last Name", lastName.toArray());
        model.addColumn("Class", classCode.toArray());
        return model;
    }

    public static ArrayList<Object> getStudentInfo(String stuID)
    {
        ArrayList<Object> studentInfo = new ArrayList<Object>();
        try
        {
            String sql = "SELECT `stuID`, `stuFirstName`, `stuLastName`, `stuOtherNames`, `stuDOB`, `stuGender`,"
                    + " `stuEthnicity`, `stuPrimaryLanguage`, `stuEmail`, `stuPhone`, `stuPhoto`, `stuAddress1`, `stuAddress2`,"
                    + " `stuPCName`, `stuPCPhone`, `stuPCAddress`, `stuSCName`, `stuSCPhone`, `stuSCAddress`, `stuDoctorName`,"
                    + " `stuDoctorContact`, `stuHospital`, `stuClsCode`,  `stuStatus` ,"
                    + " `stuPSEGrade`, `stuFeederSchool`, `stuRepeating`, `stuSpecialNeeds`, `stuNotes`, `stuSSN`, `stuNonBelizean`"
                    + "FROM `Student` WHERE `stuID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                studentInfo.add(rs.getString("stuID"));//0
                studentInfo.add(rs.getString("stuFirstName"));//1
                studentInfo.add(rs.getString("stuLastName"));//2
                studentInfo.add(rs.getString("stuOtherNames"));//3
                studentInfo.add(rs.getString("stuDOB"));//4
                studentInfo.add(rs.getString("stuGender"));//5
                studentInfo.add(rs.getString("stuEthnicity"));//6
                studentInfo.add(rs.getString("stuPrimaryLanguage"));//7
                studentInfo.add(rs.getString("stuEmail"));//8
                studentInfo.add(rs.getString("stuPhone"));//9
                studentInfo.add(rs.getBlob("stuPhoto"));//10
                studentInfo.add(rs.getString("stuAddress1"));//11
                studentInfo.add(rs.getString("stuAddress2"));//12
                studentInfo.add(rs.getString("stuPCName"));//13
                studentInfo.add(rs.getString("stuPCPhone"));//14
                studentInfo.add(rs.getString("stuPCAddress"));//15
                studentInfo.add(rs.getString("stuSCName"));//16
                studentInfo.add(rs.getString("stuSCPhone"));//17
                studentInfo.add(rs.getString("stuSCAddress"));//18
                studentInfo.add(rs.getString("stuDoctorName"));//19
                studentInfo.add(rs.getString("stuDoctorContact"));//20
                studentInfo.add(rs.getString("stuHospital"));//21
                studentInfo.add(rs.getString("stuClsCode"));//22
                studentInfo.add(rs.getString("stuPSEGrade"));//23
                studentInfo.add(rs.getString("stuFeederSchool"));//24
                studentInfo.add(rs.getBoolean("stuRepeating"));//25
                studentInfo.add(rs.getString("stuSpecialNeeds"));//26
                studentInfo.add(rs.getString("stuNotes"));//27
                studentInfo.add(rs.getString("stuStatus"));//28
                studentInfo.add(rs.getString("stuSSN"));//29   Added Jul 8 2011
                studentInfo.add(rs.getBoolean("stuNonBelizean"));//30
            }
            prep.close();
            rs.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving the student information.";
            logger.log(Level.SEVERE, message, e);
        }
        return studentInfo;
    }

    public static String getStudentClass(String stuID)
    {
        String stuClass = "";
        try
        {
            String sql = "SELECT `stuID` , `stuClsCode` FROM `Student` WHERE `stuID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                stuClass = (rs.getString("stuClsCode"));
            }
            prep.close();
            rs.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving the student information.";
            logger.log(Level.SEVERE, message, e);
        }
        return stuClass;
    }

    public static String getStudentName(String stuID)
    {
        String studentName = "";
        try
        {
            String sql = "SELECT `stuID`, `stuFirstName`, `stuLastName` FROM `Student` WHERE `stuID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                studentName = rs.getString("stuFirstName") + " " + rs.getString("stuLastName");
            }
            prep.close();
            rs.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving the student information.";
            logger.log(Level.SEVERE, message, e);
        }
        return studentName;
    }

    public static boolean updateStudentPhoto(String stuID, String stuFirstName, String stuLastName, String stuOtherNames, String stuDOB, String stuGender,
            String stuEmail, String stuPhone, File stuPhoto, String stuAddress1, String stuAddress2,
            String stuPCName, String stuPCPhone, String stuSCName, String stuPCAddress, String stuSCPhone, String stuSCAddress,
            String stuDoctorName, String stuDoctorContact, String stuHospital, String stuClsCode,
            String stuPSEGrade, String stuFeederSchool, boolean stuRepeating, String stuSpecialNeeds, String stuNotes, String stuSSN, String stuStatus, boolean stuNonBelizean)
    {
        boolean successful = false;
        try
        {
            FileInputStream fis = new FileInputStream(stuPhoto);
            String sql = "UPDATE `Student` SET `stuFirstName`= ?, `stuLastName`= ?, `stuOtherNames`= ?, `stuDOB`= ?, `stuGender`= ?, `stuEmail`= ?, `stuPhone`= ?, `stuPhoto` = ? ,`stuAddress1`= ?, `stuAddress2`= ?, `stuPCName`= ?, `stuPCPhone`= ?, `stuPCAddress`= ?, `stuSCName`= ?, `stuSCPhone`= ?, `stuSCAddress`= ?, `stuDoctorName`= ?, `stuDoctorContact`= ?, `stuHospital`= ?, `stuClsCode`= ?,"
                    + " `stuPSEGrade` = ?, `stuFeederSchool` = ?, `stuRepeating` = ?, `stuSpecialNeeds` = ?, `stuNotes` = ?, `stuSSN` = ?, `stuStatus` = ?, `stuNonBelizean` = ? WHERE `stuID`= ?;";
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
            prep.setString(21, stuPSEGrade);
            prep.setString(22, stuFeederSchool);
            prep.setBoolean(23, stuRepeating);
            prep.setString(24, stuSpecialNeeds);
            prep.setString(25, stuNotes);
            prep.setString(26, stuSSN);
            prep.setString(27, stuStatus);
            prep.setBoolean(28, stuNonBelizean);
            prep.setString(29, stuID);
            prep.executeUpdate();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the student's information.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static boolean updateStudent(String stuID, String stuFirstName, String stuLastName, String stuOtherNames, String stuDOB, String stuGender,
            String stuEmail, String stuPhone, String stuAddress1, String stuAddress2,
            String stuPCName, String stuPCPhone, String stuSCName, String stuPCAddress, String stuSCPhone, String stuSCAddress,
            String stuDoctorName, String stuDoctorContact, String stuHospital, String stuClsCode,
            String stuPSEGrade, String stuFeederSchool, boolean stuRepeating, String stuSpecialNeeds, String stuNotes, String stuSSN, String stuStatus, boolean stuNonBelizean)
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Student` SET `stuFirstName`= ?, `stuLastName`= ?, `stuOtherNames`= ?, `stuDOB`= ?, `stuGender`= ?, `stuEmail`= ?, `stuPhone`= ?, `stuAddress1`= ?, `stuAddress2`= ?, `stuPCName`= ?, `stuPCPhone`= ?, `stuPCAddress`= ?, `stuSCName`= ?, `stuSCPhone`= ?, `stuSCAddress`= ?, `stuDoctorName`= ?, `stuDoctorContact`= ?, `stuHospital`= ?, `stuClsCode`= ?,"
                    + " `stuPSEGrade` = ?, `stuFeederSchool` = ?, `stuRepeating` = ?, `stuSpecialNeeds` = ?, `stuNotes` = ?, `stuSSN` = ? , `stuStatus` = ?, `stuNonBelizean` = ? WHERE `stuID`= ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuFirstName);
            prep.setString(2, stuLastName);
            prep.setString(3, stuOtherNames);
            prep.setString(4, stuDOB);
            prep.setString(5, stuGender);
            prep.setString(6, stuEmail);
            prep.setString(7, stuPhone);
            prep.setString(8, stuAddress1);
            prep.setString(9, stuAddress2);
            prep.setString(10, stuPCName);
            prep.setString(11, stuPCPhone);
            prep.setString(12, stuPCAddress);
            prep.setString(13, stuSCName);
            prep.setString(14, stuSCPhone);
            prep.setString(15, stuSCAddress);
            prep.setString(16, stuDoctorName);
            prep.setString(17, stuDoctorContact);
            prep.setString(18, stuHospital);
            prep.setString(19, stuClsCode);
            prep.setString(20, stuPSEGrade);
            prep.setString(21, stuFeederSchool);
            prep.setBoolean(22, stuRepeating);
            prep.setString(23, stuSpecialNeeds);
            prep.setString(24, stuNotes);
            prep.setString(25, stuSSN);
            prep.setString(26, stuStatus);
            prep.setBoolean(27, stuNonBelizean);
            prep.setString(28, stuID);
            prep.executeUpdate();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the student's information.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static DefaultTableModel getStudentGradeTable(String stuID)
    {
        ArrayList<String> subCodes = new ArrayList<String>();
        ArrayList<String> subjects = new ArrayList<String>();
        ArrayList<Double> grades = new ArrayList<Double>();
        final Class<?>[] columnClasses =
        {
            String.class, String.class, Double.class
        };
        DefaultTableModel model = new DefaultTableModel()
        {

            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClasses[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        String cls = getStudentClass(stuID);
        ArrayList<String> clsSubjects = Classes.getSubjectList(cls);
        for (String sub : clsSubjects) // loop subjects
        {
            double grade = 0.0;
            if (Subject.hasWeighting(Subject.getSubjectID(sub)))
            {
                grade = Grade.calculateGradeWithWeighting(stuID, sub);
            }
            else
            {
                grade = Grade.calculateGradeWithoutWeighting(stuID, sub);
            }
            subCodes.add(sub);
            ArrayList<String> details = Subject.getSubjectDetails(Subject.getSubjectID(sub));
            subjects.add(details.get(3).toString());
            grades.add(Utilities.roundDoubleToDouble(grade));
        }// end loop subjects
        model.addColumn("Subject Code", subCodes.toArray());
        model.addColumn("Subject", subjects.toArray());
        model.addColumn("Grade", grades.toArray());
        return model;
    }

    public static DefaultTableModel getStudentAttendanceTable(String stuID)
    {
        ArrayList<Date> dates = new ArrayList<Date>();
        ArrayList<Integer> absents = new ArrayList<Integer>();
        ArrayList<Integer> lates = new ArrayList<Integer>();
        ArrayList<String> remarks = new ArrayList<String>();
        ArrayList<String> status = new ArrayList<String>();
        final Class<?>[] columnClasses =
        {
            Date.class, Integer.class, Integer.class, String.class, String.class
        };
        DefaultTableModel model = new DefaultTableModel()
        {

            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClasses[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        try
        {
            String sql = " SELECT `rolStuID`,`rolDate`,`rolAbsent`,`rolTardy`,`rolRemark`,`rolStatus`"
                    + " FROM`RollCall` "
                    + " WHERE `rolStuID` = ? AND `rolTrmCode` = ?"
                    + " ORDER BY `rolDate`";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuID);
            prep.setString(2, Term.getCurrentTerm());
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                dates.add(rs.getDate("rolDate"));
                absents.add(rs.getInt("rolAbsent"));
                lates.add(rs.getInt("rolTardy"));
                remarks.add(rs.getString("rolRemark"));
                status.add(rs.getString("rolStatus"));
            }
        }
        catch (Exception e)
        {
        }
        model.addColumn("Date", dates.toArray());
        model.addColumn("Absent", absents.toArray());
        model.addColumn("Late", lates.toArray());
        model.addColumn("Remark", remarks.toArray());
        model.addColumn("Status", status.toArray());
        return model;
    }

    public static DefaultTableModel getStudentMeritTable(String stuID)
    {

        ArrayList<Date> dates = new ArrayList<Date>();
        ArrayList<Integer> merits = new ArrayList<Integer>();
        ArrayList<String> remarks = new ArrayList<String>();
        ArrayList<String> staff = new ArrayList<String>();
        final Class<?>[] columnClasses =
        {
            Date.class, Integer.class, Integer.class, String.class, String.class
        };
        DefaultTableModel model = new DefaultTableModel()
        {

            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClasses[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        try
        {
            String sql = "  SELECT `merDate`,`merits`, `merRemarks`, CONCAT_WS(' ',`staFirstName`, `staLastName`) as 'Staff'"
                    + " FROM `Merits`"
                    + "INNER JOIN `Staff` ON `Staff`.`staID` = `Merits`.`merStaID`"
                    + "WHERE `merStatus` = 'Active' AND `merStuID` = ? AND `merTermID` = ?";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuID);
            prep.setString(2, Term.getCurrentTerm());
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                dates.add(rs.getDate("merDate"));
                merits.add(rs.getInt("merits"));
                remarks.add(rs.getString("merRemarks"));
                staff.add(rs.getString("Staff"));
            }
        }
        catch (Exception e)
        {
        }
        model.addColumn("Date", dates.toArray());
        model.addColumn("Merits", merits.toArray());
        model.addColumn("Remark", remarks.toArray());
        model.addColumn("Staff", staff.toArray());
        return model;
    }
    public static DefaultTableModel getStudentDemeritTable(String stuID)
    {

        ArrayList<Date> dates = new ArrayList<Date>();
        ArrayList<Integer> merits = new ArrayList<Integer>();
        ArrayList<String> remarks = new ArrayList<String>();
        ArrayList<String> actionTaken = new ArrayList<String>();
        ArrayList<String> staff = new ArrayList<String>();
        final Class<?>[] columnClasses =
        {
            Date.class, Integer.class, Integer.class, String.class, String.class
        };
        DefaultTableModel model = new DefaultTableModel()
        {

            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClasses[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        try
        {
            String sql = "  SELECT `demDate`, `demerits`, `demRemarks`, `demActionTaken`, CONCAT_WS(' ',`staFirstName`, `staLastName`) AS 'staff'"
                    + " FROM `Demerits`"
                    + " INNER JOIN `Staff` ON `Staff`.`staCode` = `Demerits`.`demStaCode`"
                    + " WHERE `demStatus` = 'Active' AND `demStuID` = ? AND `demTermID` = ? ";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, stuID);
            prep.setString(2, Term.getCurrentTerm());
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                dates.add(rs.getDate("demDate"));
                merits.add(rs.getInt("demerits"));
                remarks.add(rs.getString("demRemarks"));
                actionTaken.add(rs.getString("demActionTaken"));
                staff.add(rs.getString("staff"));
            }
        }
        catch (Exception e)
        {
        }
        model.addColumn("Date", dates.toArray());
        model.addColumn("Demerits", merits.toArray());
        model.addColumn("Remark", remarks.toArray());
        model.addColumn("Action", actionTaken.toArray());
        model.addColumn("Staff", staff.toArray());
        return model;
    }
}