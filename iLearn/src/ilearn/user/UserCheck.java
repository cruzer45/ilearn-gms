package ilearn.user;

/**
 *
 * @author mrogers
 */
public class UserCheck
{

    public static boolean canSeeStudent()
    {
        if (User.previligeAvailable("Manage"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Check whether a user can access the add student menu item.
     */
    public static boolean canAddStudent()
    {
        if (User.previligeAvailable("Add Student"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canEditStudent()
    {
        if (User.previligeAvailable("Edit Student"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canViewStudent()
    {
        if (User.previligeAvailable("View Student"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeAttendance()
    {
        if (User.previligeAvailable("Attendance"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canEnterAttendance()
    {
        if (User.previligeAvailable("Enter Attendance"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canEditAttendance()
    {
        if (User.previligeAvailable("Edit Attendance"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeGrades()
    {
        if (User.previligeAvailable("Grades"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canCreateAssessment()
    {
        if (User.previligeAvailable("Create Assessment"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canEditAssessment()
    {
        if (User.previligeAvailable("Edit Assessment"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canEditMidTerms()
    {
        if (User.previligeAvailable("Edit Mid Terms"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeDemerits()
    {
        if (User.previligeAvailable("Demerits"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canRecordDemerits()
    {
        if (User.previligeAvailable("Record Demerits"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canEditDemerits()
    {
        if (User.previligeAvailable("Edit Demerits"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeReports()
    {
        if (User.previligeAvailable("Reports"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeStudentReports()
    {
        if (User.previligeAvailable("Student Reports"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeStudentList()
    {
        if (User.previligeAvailable("Student List"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeRepeatingStudents()
    {
        if (User.previligeAvailable("Repeating Students"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeStudentsByClass()
    {
        if (User.previligeAvailable("Students By Class"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeStudentIDCards()
    {
        if (User.previligeAvailable("Student ID Cards"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeClassReports()
    {
        if (User.previligeAvailable("Class Reports"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*
    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Class List Report"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }

    public static boolean canSeeStudent()
    {
    if (User.previligeAvailable("Manage"))
    {
    return true;
    }
    else
    {
    return false;
    }
    }
     *
     */
    /**
     * Check whether a user can access the Manage Menu.
     */
    public static boolean canManage()
    {
        if (User.getUserGroup().equals("Administration"))
        {
            return true;
        }
        else if (User.previligeAvailable("Manage"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
