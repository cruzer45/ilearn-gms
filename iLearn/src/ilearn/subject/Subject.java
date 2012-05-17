package ilearn.subject;

import ilearn.grades.Grade;
import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.staff.Staff;
import ilearn.term.TimeSlots;
import ilearn.user.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author m.rogers
 */
public class Subject
{

    static final Logger logger = Logger.getLogger(Subject.class.getName());
    private static ArrayList<String> hours = new ArrayList<String>();
    private static ArrayList<String> hourCodes = new ArrayList<String>();
    private static ArrayList<String> assessments = new ArrayList<String>();
    private static ArrayList<Integer> weightings = new ArrayList<Integer>();

    public static void addWeighting(String assesment, int weighting)
    {
        if (!assessments.contains(assesment))
        {
            assessments.add(assesment);
            weightings.add(weighting);
        }
    }

    public static void removeWeighting(String assesment)
    {
        for (int i = 0; i < assessments.size(); i++)
        {
            if (assesment.equals(assessments.get(i)))
            {
                assessments.remove(i);
                weightings.remove(i);
            }
        }
    }

    public static DefaultTableModel getWeightingTable()
    {
        DefaultTableModel model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        model.addColumn("Assessment Type", assessments.toArray());
        model.addColumn("Weight (Percent)", weightings.toArray());
        return model;
    }

    public static int getWeightTotal()
    {
        int weightTotal = 0;
        for (int weight : weightings)
        {
            weightTotal += weight;
        }
        return weightTotal;
    }

    public static void resetWeightings()
    {
        assessments = new ArrayList<String>();
        weightings = new ArrayList<Integer>();
    }

    public static boolean saveWeightings(String subID)
    {
        boolean successful = false;
        try
        {
            ArrayList<String> assmtTypeID = new ArrayList<String>();
            for (String assmtType : assessments)
            {
                assmtTypeID.add(Grade.getAssessmentTypeID(assmtType));
            }
            String sql1 = " DELETE FROM `Subject_Weightings` WHERE `subID` = ?;",
                   sql2 = " INSERT INTO `Subject_Weightings` (`subID`, `assmentTypeID`, `weighting`) VALUES (?, ?, ?); ";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql1);
            prep.setString(1, subID);
            prep.execute();
            prep = Environment.getConnection().prepareStatement(sql2);
            for (int i = 0; i < assmtTypeID.size(); i++)
            {
                prep.setString(1, subID);
                prep.setString(2, assmtTypeID.get(i));
                prep.setInt(3, weightings.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while saving the subject weightings ";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static void addHour(String hour, String code)
    {
        if (!hours.contains(hour))
        {
            hours.add(hour);
            hourCodes.add(code);
        }
    }

    public static void removeHour(String hour)
    {
        for (int i = 0; i < hours.size(); i++)
        {
            if (hour.equals(hours.get(i)))
            {
                hours.remove(i);
                hourCodes.remove(i);
            }
        }
    }

    public static ArrayList<String> getHours()
    {
        return hours;
    }

    public static ArrayList<String> getHourCodes()
    {
        return hourCodes;
    }

    public static void resetHours()
    {
        hours = new ArrayList<String>();
        hourCodes = new ArrayList<String>();
    }

    public static boolean addSubject(String subCode, String subStaffCode, String subName, String subDescription, String subCreditHours)
    {
        boolean successful = false;
        try
        {
            //Save the hours
            String sql = "INSERT INTO `SubjectHours` (`subCode`, `hrsKey`) VALUES (?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            for (int i = 0; i < hourCodes.size(); i++)
            {
                prep.setString(1, subCode);
                prep.setString(2, hourCodes.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
            //Save the subject Info.
            sql = "INSERT INTO `Subject` (`subCode`, `subStaffCode`, `subName`, `subDescription`, `subCredits`) VALUES (?, ?, ?, ?, ?);";
            prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, subCode);
            prep.setString(2, subStaffCode);
            prep.setString(3, subName);
            prep.setString(4, subDescription);
            prep.setString(5, subCreditHours);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a subject";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    /**
     * This method checks if the specified subject has weighting assigned to it.
     * @param subID
     * @return
     */
    public static boolean hasWeighting(String subID)
    {
        boolean hasWeight = false;
        int weighting = 0;
        try
        {
            String sql = " SELECT COUNT(`id`) AS 'Count' "
                         + " FROM `Subject_Weightings` "
                         + " WHERE `subID` =  ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, subID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                weighting = rs.getInt("Count");
            }
            rs.close();
            prep.close();
            if (weighting > 0)
            {
                hasWeight = true;
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while checking if a subject is weighted.";
            logger.log(Level.SEVERE, message, e);
        }
        return hasWeight;
    }

    public static ArrayList<String> getSubjectAssessmentTypes(String subID)
    {
        ArrayList<String> assmtWeightType = new ArrayList<String>();
        try
        {
            //Get the assignment types and weightings.
            String sql = " SELECT `assmtType` "
                         + " FROM `Subject_Weightings`  "
                         + " INNER JOIN `listAssessmentTypes` ON `listAssessmentTypes`.`id` = `Subject_Weightings` .`assmentTypeID`  "
                         + " WHERE `subID` = ? "
                         + " ORDER BY `assmtType` ASC; ";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, subID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                assmtWeightType.add(rs.getString("assmtType"));
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the subject assessment types.";
            logger.log(Level.SEVERE, message, e);
        }
        return assmtWeightType;
    }

    /**
     * This method loads the subject weighting in to the class variables to be manipulated later.
     * @param subID
     */
    public static void loadSubjectWeightings(String subID)
    {
        try
        {
            String sql = " SELECT `assmtType`, `weighting` "
                         + " FROM `Subject_Weightings` "
                         + " INNER JOIN `listAssessmentTypes` ON `listAssessmentTypes`.`id` = `Subject_Weightings` .`assmentTypeID`"
                         + " WHERE `subID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, subID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                addWeighting(rs.getString("assmtType"), rs.getInt("weighting"));
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while loading subject's weightings;";
            logger.log(Level.SEVERE, message, e);
        }
    }

    /**
     * This method updates a subject's details with the ones supplied
     * @param subCode
     * @param subStaffCode
     * @param subName
     * @param subDescription
     * @param subCredits
     * @param subStatus
     * @param subID
     * @return
     */
    public static boolean updateSubject(String subCode, String subStaffCode, String subName, String subDescription, String subCredits, String subStatus, String subID)
    {
        boolean successful = false;
        try
        {
            //Save the hours
            String sql1 = "DELETE FROM `SubjectHours` WHERE `subCode`= ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql1);
            prep.setString(1, subCode);
            prep.executeUpdate();
            prep.close();
            String sql2 = "INSERT INTO `SubjectHours` (`subCode`, `hrsKey`) VALUES (?, ?);";
            prep = Environment.getConnection().prepareStatement(sql2);
            for (int i = 0; i < hourCodes.size(); i++)
            {
                prep.setString(1, subCode);
                prep.setString(2, hourCodes.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
            //Save the subject Info.
            String sql3 = "UPDATE `Subject` SET `subCode`= ?, `subStaffCode`= ?, `subName`= ?, `subDescription`= ?, `subStatus`= ? , `subCredits` = ? WHERE `subID`= ? ;";
            prep = Environment.getConnection().prepareStatement(sql3);
            prep.setString(1, subCode);
            prep.setString(2, subStaffCode);
            prep.setString(3, subName);
            prep.setString(4, subDescription);
            prep.setString(5, subStatus);
            prep.setString(6, subCredits);
            prep.setString(7, subID);
            prep.executeUpdate();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating a subject";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static DefaultTableModel getSubjectTable(String criteria)
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
        ArrayList<String> id = new ArrayList<String>();
        ArrayList<String> code = new ArrayList<String>();
        ArrayList<String> staffCode = new ArrayList<String>();
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<String> description = new ArrayList<String>();
        ArrayList<Integer> credits = new ArrayList<Integer>();
        ArrayList<String> status = new ArrayList<String>();
        try
        {
            String sql = "SELECT `subID`, `subCode`, `subStaffCode`, `subName`, `subDescription`, `subCredits`,`subStatus` FROM `Subject` "
                         + " WHERE (`subID` LIKE ? OR `subCode` LIKE ? OR `subStaffCode` LIKE ? OR `subName` LIKE ? OR `subDescription` LIKE ? ) ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, criteria);
            prep.setString(2, criteria);
            prep.setString(3, criteria);
            prep.setString(4, criteria);
            prep.setString(5, criteria);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                if ((User.getPermittedSubjects().contains(rs.getString("subCode"))) || User.getUserGroup().equals("Administration"))
                {
                    id.add(rs.getString("subID"));
                    code.add(rs.getString("subCode"));
                    staffCode.add(rs.getString("subStaffCode"));
                    name.add(rs.getString("subName"));
                    description.add(rs.getString("subDescription"));
                    status.add(rs.getString("subStatus"));
                    credits.add(rs.getInt("subCredits"));
                }
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while searching for a subject.";
            logger.log(Level.SEVERE, message, e);
        }
        model.addColumn("ID", id.toArray());
        model.addColumn("Code", code.toArray());
        model.addColumn("Name", name.toArray());
        model.addColumn("Teacher", staffCode.toArray());
        model.addColumn("Credits", credits.toArray());
        model.addColumn("Status", status.toArray());
        return model;
    }

    public static ArrayList<HashMap> getSubjectDetailList()
    {
        ArrayList<HashMap> details = new ArrayList<HashMap>();
        try
        {
            String sql = "SELECT `subID`, `subCode`, `subStaffCode`, `subName`, "
                         + "`subDescription`, `subCredits`,`subStatus` "
                         + " FROM `Subject` ";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                HashMap<String, String> subject = new HashMap<String, String>();
                subject.put("subID", rs.getString("subID"));
                subject.put("subCode", rs.getString("subCode"));
                subject.put("subStaffCode", Staff.getStaffName(rs.getString("subStaffCode")));
                subject.put("subName", rs.getString("subName"));
                subject.put("subDescription", rs.getString("subDescription"));
                subject.put("subStatus", rs.getString("subStatus"));
                subject.put("subCredits", rs.getString("subCredits"));
                details.add(subject);
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving subject details.";
            logger.log(Level.SEVERE, message, e);
        }
        return details;
    }

    public static ArrayList<String> getSubjectDetails(String subID)
    {
        ArrayList<String> details = new ArrayList<String>();
        try
        {
            String sql = "SELECT `subID`, `subCode`, `subStaffCode`, `subName`, "
                         + "`subDescription`, `subCredits`,`subStatus` "
                         + " FROM `Subject` WHERE `subID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, subID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                details.add(rs.getString("subID"));//0
                details.add(rs.getString("subCode"));//1
                details.add(Staff.getStaffName(rs.getString("subStaffCode")));//2
                details.add(rs.getString("subName"));//3
                details.add(rs.getString("subDescription"));//4
                details.add(rs.getString("subStatus"));//5
                details.add(rs.getString("subCredits"));//6
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving subject details.";
            logger.log(Level.SEVERE, message, e);
        }
        return details;
    }

    public static String getSubjectID(String subCode)
    {
        String subID = "";
        try
        {
            String sql = "SELECT `subID` FROM `Subject` WHERE `subCode` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, subCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                subID = rs.getString("subID");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving subject details.";
            logger.log(Level.SEVERE, message, e);
        }
        return subID;
    }

    public static String getSubjectCode(String subID)
    {
        String subCode = "";
        try
        {
            String sql = "SELECT `subCode` FROM `Subject` WHERE `subID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, subID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                subCode = rs.getString("subCode");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving subject code.";
            logger.log(Level.SEVERE, message, e);
        }
        return subCode;
    }

    public static void getSubjectHours(String subCode)
    {
        resetHours();
        try
        {
            String sql = "SELECT `subHoursID`, `subCode`, `hrsKey` FROM `SubjectHours` WHERE `subCode` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, subCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                hourCodes.add(rs.getString("hrsKey"));
            }
            rs.close();
            prep.close();
            for (String hourCode : hourCodes)
            {
                hours.add(TimeSlots.getTimeSlot(hourCode));
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving subject hours.";
            logger.log(Level.SEVERE, message, e);
        }
    }

    public static ArrayList<String> getClassesForSubject(String subID)
    {
        ArrayList<String> classesforSubject = new ArrayList<String>();
        try
        {
            String sql = "SELECT `clsCode` FROM `ClassSubjects` WHERE `subCode` = ?";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, subID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                classesforSubject.add(rs.getString("clsCode"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving classes that take a specific subject.";
            logger.log(Level.SEVERE, message, e);
        }
        return classesforSubject;
    }

    public static String getSubjectTeacher(String subCode)
    {
        String teacher = "";
        try
        {
            String sql = "SELECT `subStaffCode` FROM `Subject` WHERE `subCode` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, subCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                teacher = rs.getString("subStaffCode");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving subject details.";
            logger.log(Level.SEVERE, message, e);
        }
        return teacher;
    }
}
