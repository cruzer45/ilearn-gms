package ilearn.kernel;

import ilearn.classes.Classes;
import ilearn.grades.Grade;
import ilearn.subject.Subject;
import ilearn.term.Term;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author m.rogers
 */
public class RecauculateMidTermForSpecificSubjects
{

    public static void main(String[] args)
    {
        try
        {
            Environment.createConnection();
            String currentTerm = Term.getCurrentTerm();
            ArrayList<String> stuDentList = new ArrayList<String>(); //maintain a list of students that will need to be recalculated
            String sql = "UPDATE `Grade_Average` SET `graAvgMid` = ? WHERE `graAvgTerm` = ? AND `graAvgStuID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            String[] subjectCodes =
            {
                "4A-I.T.", "4A-I.T.", "4B-I.T.", "4G-I.T.", "4S-I.T.", "4T-I.T.", "4V-I.T."
            };
            for (String subCode : subjectCodes)
            {
                System.out.println("Subject: " + subCode);
                String[] split = subCode.split("-");//separate the subject from the class
                String current_class = (split[0]);//add the class to the class list
                ArrayList<String> stuList = Classes.getStudentIDList(current_class);
                for (String stuID : stuList)
                {
                    //If the studen't id isn't in the list already add it in
                    if (!stuDentList.contains(stuID))
                    {
                        stuDentList.add(stuID);
                    }
                    System.out.println("\tStudent: " + stuID);
                    double grade = 0.0;
                    if (Subject.hasWeighting(Subject.getSubjectID(subCode)))
                    {
                        grade = Grade.calculateGradeWithWeighting(stuID, subCode);
                    }
                    else
                    {
                        grade = Grade.calculateGradeWithoutWeighting(stuID, subCode);
                    }
                    prep.setDouble(1, grade);
                    prep.setString(2, currentTerm);
                    prep.setString(3, stuID);
                    prep.addBatch();
                }
            }
            prep.executeBatch();
        }
        catch (Exception e)
        {
            String message = "An error occurred while trying to execute the function.";
            System.out.println(message);
            e.printStackTrace();
        }
        System.exit(0);
    }
}
