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

    public static boolean canRecordGrades()
    {
        if (User.previligeAvailable("Record Grade"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canEditGrades()
    {
        if (User.previligeAvailable("Edit Grade"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canEditRemarks()
    {
        if (User.previligeAvailable("Edit Report Card Remarks"))
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

    public static boolean canSeeClassListReport()
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

    public static boolean canSeeClassGradeBook()
    {
        if (User.previligeAvailable("Class Grade Book"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeReportCards()
    {
        if (User.previligeAvailable("Report Cards Menu"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeMidTermReports()
    {
        if (User.previligeAvailable("Mid Term Reports"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeMidTermClassRanking()
    {
        if (User.previligeAvailable("Mid Term Class Ranking"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeTermEndReport()
    {
        if (User.previligeAvailable("Term End Report"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeTermEndRanking()
    {
        if (User.previligeAvailable("Term End Ranking"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeDemeritReports()
    {
        if (User.previligeAvailable("Demerit Reports"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeDemeritsByClass()
    {
        if (User.previligeAvailable("Demerits By Class"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeDemeritsByStudent()
    {
        if (User.previligeAvailable("Demerits By Student"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeStatisticalReports()
    {
        if (User.previligeAvailable("Statistical Reports"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeClassSizeDistribution()
    {
        if (User.previligeAvailable("Class Size Distribution"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeGenderDistribution()
    {
        if (User.previligeAvailable("Gender Distribution"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeManage()
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

    public static boolean canSeeDetentionMenu()
    {
        if (User.previligeAvailable("Detention"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canRecordDetention()
    {
        if (User.previligeAvailable("Add Detention"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canEditDetention()
    {
        if (User.previligeAvailable("Edit Detention"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canRecordServedDetention()
    {
        if (User.previligeAvailable("Record Served Detention"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeAttendanceReport()
    {
        if (User.previligeAvailable("Attendance Reports"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeAttendanceSummary()
    {
        if (User.previligeAvailable("Attendance Summary"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeAttendanceDetails()
    {
        if (User.previligeAvailable("Attendance Details"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeDemeritSummaryTeacher()
    {
        if (User.previligeAvailable("Demerit Summary by Teacher"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean canSeeDemeritSummaryStudent()
    {
        if (User.previligeAvailable("Demerit Summary by Student"))
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
