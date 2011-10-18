package ilearn.classes;

import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.kernel.logger.iLogger;
import ilearn.subject.Subject;
import ilearn.user.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mrogers
 */
public class Classes
{

    static final Logger logger = Logger.getLogger(Classes.class.getName());
    private static ArrayList<String> subIDs = new ArrayList<String>();
    private static ArrayList<String> subCodes = new ArrayList<String>();
    private static ArrayList<String> subTeacher = new ArrayList<String>();
    private static ArrayList<String> subTitle = new ArrayList<String>();

    /**
     * This resets the list of subjects for a class.
     */
    public static void resetSubjects()
    {
        subIDs = new ArrayList<String>();
        subCodes = new ArrayList<String>();
        subTeacher = new ArrayList<String>();
        subTitle = new ArrayList<String>();
    }

    /**
     * This adds a subject to the class.
     * @param subID
     * @param subCode
     * @param teacher
     * @param title
     */
    public static void addSubject(String subID, String subCode, String teacher, String title)
    {
        if (!subIDs.contains(subID))
        {
            subIDs.add(subID);
            subCodes.add(subCode);
            subTeacher.add(teacher);
            subTitle.add(title);
        }
    }

    /**
     * This removes a subject from the class.
     * @param subCode
     */
    public static void removeSubject(String subCode)
    {
        for (int i = 0; i < subCodes.size(); i++)
        {
            if (subCode.equals(subCodes.get(i)))
            {
                subCodes.remove(i);
                subIDs.remove(i);
                subTeacher.remove(i);
                subTitle.remove(i);
            }
        }
    }

    /**
     * @returns a table model containing the list of subjects for a class.
     */
    public static DefaultTableModel getSubjects()
    {
        DefaultTableModel model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }
        };
        model.addColumn("ID", subIDs.toArray());
        model.addColumn("Code", subCodes.toArray());
        model.addColumn("Title", subTitle.toArray());
        model.addColumn("Teacher", subTeacher.toArray());
        return model;
    }

    /**
     *
     * @param classCode
     * @returns a table model listing the subjects a class takes.
     */
    public static DefaultTableModel getSubjects(String classCode)
    {
        resetSubjects();
        DefaultTableModel model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }
        };
        try
        {
            String sql = "SELECT `id`, `clsCode`, `subCode` FROM `ClassSubjects` "
                         + "WHERE `clsCode` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, classCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                subIDs.add(rs.getString("subCode"));
            }
            rs.close();
            prep.close();
            for (String subID : subIDs)
            {
                ArrayList<String> details = Subject.getSubjectDetails(subID);
                subCodes.add(details.get(1));
                subTeacher.add(details.get(2));
                subTitle.add(details.get(3));
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while loading the class' subjects.";
            logger.log(Level.SEVERE, message, e);
        }
        model.addColumn("ID", subIDs.toArray());
        model.addColumn("Code", subCodes.toArray());
        model.addColumn("Title", subTitle.toArray());
        model.addColumn("Teacher", subTeacher.toArray());
        return model;
    }

    /**
     * @param classCode
     * @returns an list of subjects that a class takes.
     */
    public static ArrayList<String> getSubjectList(String classCode)
    {
        ArrayList<String> subjects = new ArrayList<String>();
        resetSubjects();
        try
        {
            String sql = "SELECT `id`, `clsCode`, `subCode` FROM `ClassSubjects` "
                         + "WHERE `clsCode` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, classCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                subIDs.add(rs.getString("subCode"));
            }
            rs.close();
            prep.close();
            for (String subID : subIDs)
            {
                ArrayList<String> details = Subject.getSubjectDetails(subID);
                subCodes.add(details.get(1));
                subjects.add(details.get(1));
                subTeacher.add(details.get(2));
                subTitle.add(details.get(3));
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while loading the class' subjects.";
            logger.log(Level.SEVERE, message, e);
        }
        return subjects;
    }

    /**
     * This function adds a class to the database.
     * @param code
     * @param level
     * @param name
     * @param description
     * @param homeRoom
     * @return true of the process was successful of false if it wasn't.
     */
    public static boolean saveClass(String code, String level, String name, String description, String homeRoom)
    {
        boolean successful = false;
        if (homeRoom.equals("--- Select One ---")
                || code.isEmpty()
                || name.isEmpty())
        {
            return successful;
        }
        try
        {
            //link the subjects and classes
            String sql1 = "INSERT INTO `ClassSubjects` (`clsCode`, `subCode`) VALUES (?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql1);
            for (int i = 0; i < subIDs.size(); i++)
            {
                prep.setString(1, code);
                prep.setString(2, subIDs.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
            //Add the class data.
            String sql = "INSERT INTO `Class` (`clsCode`, `clsName`, `clsDescription`, `clsLevel`, `clsHomeRoom`) VALUES (?, ?, ?,?, ?);";
            prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, code);
            prep.setString(2, name);
            prep.setString(3, description);
            prep.setString(4, level);
            prep.setString(5, homeRoom);
            prep.execute();
            prep.close();
            successful = true;
            //Log the Action
            String message = "The class, \"" + code + "\" was added to the system.";
            iLogger.logMessage(message, "Add", "Class");
        }
        catch (Exception e)
        {
            String message = "An error occurred while adding a class to the system.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    /**
     * @returns a table model containing a list of classes.
     */
    public static DefaultTableModel getClassTableModel()
    {
        DefaultTableModel model = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }
        };
        ArrayList<String> ID = new ArrayList<String>();
        ArrayList<String> code = new ArrayList<String>();
        ArrayList<String> Name = new ArrayList<String>();
        ArrayList<String> HomeRoom = new ArrayList<String>();
        ArrayList<String> Status = new ArrayList<String>();
        try
        {
            String sql = "SELECT `clsID`, `clsCode`, `clsName`,`clsHomeRoom`, `clsStatus` FROM `Class` ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                ID.add(rs.getString("clsID"));
                code.add(rs.getString("clsCode"));
                Name.add(rs.getString("clsName"));
                HomeRoom.add(rs.getString("clsHomeRoom"));
                Status.add(rs.getString("clsStatus"));
            }
            rs.close();
            prep.close();
            model.addColumn("ID", ID.toArray());
            model.addColumn("Code", code.toArray());
            model.addColumn("Name", Name.toArray());
            model.addColumn("Home Room", HomeRoom.toArray());
            model.addColumn("Status", Status.toArray());
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the class table model.";
            logger.log(Level.SEVERE, message, e);
        }
        return model;
    }

    /**
     *
     * @param criteria
     * @returns a table model containing a filtered list of classes.
     */
    public static DefaultTableModel searchClass(String criteria)
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
        ArrayList<String> ID = new ArrayList<String>();
        ArrayList<String> code = new ArrayList<String>();
        ArrayList<String> Name = new ArrayList<String>();
        ArrayList<String> HomeRoom = new ArrayList<String>();
        ArrayList<String> Status = new ArrayList<String>();
        try
        {
            String sql = "SELECT `clsID`, `clsCode`, `clsName`,`clsHomeRoom`, `clsStatus` FROM `Class` "
                         + "WHERE `clsID` LIKE ? OR `clsCode` LIKE ? OR `clsName` LIKE ? OR `clsHomeRoom` LIKE ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, criteria);
            prep.setString(2, criteria);
            prep.setString(3, criteria);
            prep.setString(4, criteria);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                ID.add(rs.getString("clsID"));
                code.add(rs.getString("clsCode"));
                Name.add(rs.getString("clsName"));
                HomeRoom.add(rs.getString("clsHomeRoom"));
                Status.add(rs.getString("clsStatus"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the class table model.";
            logger.log(Level.SEVERE, message, e);
        }
        model.addColumn("ID", ID.toArray());
        model.addColumn("Code", code.toArray());
        model.addColumn("Name", Name.toArray());
        model.addColumn("Home Room", HomeRoom.toArray());
        model.addColumn("Status", Status.toArray());
        return model;
    }

    /**
     *
     * @param classID
     * @return the information for a class wrapped in a String ArrayList
     */
    public static ArrayList<String> getClassInfo(String classID)
    {
        ArrayList<String> info = new ArrayList<String>();
        try
        {
            String sql = "SELECT `clsID`, `clsCode`, `clsName`, `clsDescription`, `clsLevel`, `clsHomeRoom`, `clsStatus` FROM `Class` WHERE `clsID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, classID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                info.add(rs.getString("clsCode"));
                info.add(rs.getString("clsName"));
                info.add(rs.getString("clsDescription"));
                info.add(rs.getString("clsLevel"));
                info.add(rs.getString("clsHomeRoom"));
                info.add(rs.getString("clsStatus"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while retrieving the class information.";
            logger.log(Level.SEVERE, message, e);
        }
        return info;
    }

    /**
     *
     * @param code
     * @param name
     * @param description
     * @param level
     * @param homeRoom
     * @param status
     * @param id
     * @return a true if the new information for the class was successfully committed.
     */
    public static boolean updateClass(String code, String name, String description, String level, String homeRoom, String status, String id)
    {
        boolean successful = false;
        try
        {
            //Remove Old Subjects
            String sql_purge = "DELETE FROM `ClassSubjects` WHERE `clsCode`= ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql_purge);
            prep.setString(1, code);
            prep.executeUpdate();
            prep.close();
            //link the subjects and classes
            String sql_subject = "INSERT INTO `ClassSubjects` (`clsCode`, `subCode`) VALUES (?, ?);";
            prep = Environment.getConnection().prepareStatement(sql_subject);
            for (int i = 0; i < subIDs.size(); i++)
            {
                prep.setString(1, code);
                prep.setString(2, subIDs.get(i));
                prep.addBatch();
            }
            prep.executeBatch();
            prep.close();
            //Update the class
            String sql_updateClass = "UPDATE `Class` SET `clsCode`= ?, `clsName`= ?, `clsDescription`= ?, `clsLevel`= ?, `clsHomeRoom`= ?, `clsStatus`= ? WHERE `clsID`= ?  LIMIT 1;";
            prep = Environment.getConnection().prepareStatement(sql_updateClass);
            prep.setString(1, code);
            prep.setString(2, name);
            prep.setString(3, description);
            prep.setString(4, level);
            prep.setString(5, homeRoom);
            prep.setString(6, status);
            prep.setString(7, id);
            prep.executeUpdate();
            prep.close();
            successful = true;
            //Log the Action
            String message = "The class, \"" + code + "\" was modifided.";
            iLogger.logMessage(message, "Update", "Class");
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the class information.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static ArrayList<String> getClassList()
    {
        ArrayList<String> classes = new ArrayList<String>();
        try
        {
            String sql = "SELECT `clsCode`,`clsName` FROM `Class` WHERE `clsStatus` = 'Active' ORDER BY `clsName` ASC ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                classes.add(rs.getString("clsCode"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the list of classes.";
            logger.log(Level.SEVERE, message, e);
        }
        return classes;
    }

    public static String getClassID(String clsCode)
    {
        String classID = "";
        try
        {
            String sql = "SELECT `clsID` FROM `Class` WHERE `clsCode` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, clsCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                classID = rs.getString("clsID");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the classID.";
            logger.log(Level.SEVERE, message, e);
        }
        return classID;
    }

    public static String getClassCode(String clsID)
    {
        String classCode = "";
        try
        {
            String sql = "SELECT `clsCode` FROM `Class` WHERE `clsID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, clsID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                classCode = rs.getString("clsCode");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the classCode.";
            logger.log(Level.SEVERE, message, e);
        }
        return classCode;
    }

    public static DefaultTableModel getStudentTableModel(String classCode)
    {
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
        try
        {
            String sql = "SELECT `stuID`, `stuFirstName`, `stuLastName`, `stuOtherNames`, `stuStatus` FROM `Student` WHERE `stuClsCode` = ? AND `stuStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, classCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                studentID.add(rs.getString("stuID"));
                firstName.add(rs.getString("stuFirstName"));
                lastName.add(rs.getString("stuLastName"));
            }
            prep.close();
            rs.close();
            model.addColumn("ID", studentID.toArray());
            model.addColumn("First Name", firstName.toArray());
            model.addColumn("Last Name", lastName.toArray());
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the list of students for a class.";
            logger.log(Level.SEVERE, message, e);
        }
        return model;
    }

    public static ArrayList<String> getStudentIDList(String classCode)
    {
        ArrayList<String> studentID = new ArrayList<String>();
        try
        {
            String sql = "SELECT `stuID` FROM `Student` WHERE `stuClsCode` = ? AND `stuStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, classCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                studentID.add(rs.getString("stuID"));
            }
            prep.close();
            rs.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the list of students for a class.";
            logger.log(Level.SEVERE, message, e);
        }
        return studentID;
    }

    public static Object[] getStudentNameList(String classCode)
    {
        ArrayList<String> studentID = new ArrayList<String>();
        ArrayList<String> stuName = new ArrayList<String>();
        try
        {
            String sql = "SELECT `stuID`, CONCAT_WS(' ',`stuFirstName`, `stuLastName`) AS 'Name', `stuStatus` FROM `Student` WHERE `stuClsCode` = ? AND `stuStatus` = 'Active';";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, classCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                studentID.add(rs.getString("stuID"));
                stuName.add(rs.getString("Name"));
            }
            prep.close();
            rs.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the list of students for a class.";
            logger.log(Level.SEVERE, message, e);
        }
        Object[] stuList = new Object[]
        {
            studentID, stuName
        };
        return stuList;
    }

    public static ArrayList<String> getClassLevelList()
    {
        ArrayList<String> list = new ArrayList<String>();
        try
        {
            String sql = "SELECT `level` FROM `listClassLevels` ORDER BY `id` ASC";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                list.add(rs.getString("level"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while generating the list of class levels.";
            logger.log(Level.SEVERE, message, e);
        }
        return list;
    }

    public static ArrayList<String> getPermittedSubjects(String classCode)
    {
        ArrayList<String> subjects = new ArrayList<String>();
        ArrayList<String> permittedSubjects = User.getPermittedSubjects();
        try
        {
            String sql = "SELECT `subCode` FROM `ClassSubjects` "
                         + "WHERE `clsCode` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, classCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                String subID = rs.getString("subCode"); //This table actually stores the ID and not the code.
                String subCode = Subject.getSubjectCode(subID);
                if (permittedSubjects.contains(subCode))
                {
                    subjects.add(subCode);
                }
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while loading the class' subjects.";
            logger.log(Level.SEVERE, message, e);
        }
        return subjects;
    }

    public static int getClassSize(String clsCode)
    {
        int classSize = 0;
        try
        {
            String sql = "SELECT COUNT(`stuID`) as ClassSize FROM `Student` "
                         + "WHERE `stuStatus` = 'Active' AND `stuClsCode` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, clsCode);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                classSize = rs.getInt("ClassSize");
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the class size.";
            logger.log(Level.SEVERE, message, e);
        }
        return classSize;
    }

    private static boolean setClassSize(String cls, int size)
    {
        boolean successful = false;
        try
        {
            String sql = "UPDATE `Class` SET `clsSize`= ? WHERE `clsCode`= ? LIMIT 1;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setInt(1, size);
            prep.setString(2, cls);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the class size.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }

    public static void recalculateClassSize()
    {
        try
        {
            ArrayList<String> classes = getClassList();
            for (String cls : classes)
            {
                int size = getClassSize(cls);
                setClassSize(cls, size);
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while updating the class sizes.";
            logger.log(Level.SEVERE, message, e);
        }
    }
}
