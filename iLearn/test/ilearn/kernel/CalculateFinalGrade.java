/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.kernel;

import ilearn.grades.Grade;
import ilearn.student.Student;
import java.util.ArrayList;

/**
 *
 * @author m.rogers
 */
public class CalculateFinalGrade
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Environment.createConnection();
        ArrayList<Object> results = Grade.checkAllGrades();
        if (!results.isEmpty())
        {
            for (Object missingGrade : results)
            {
                String[] missing = (String[]) missingGrade;
                String studentName = Student.getStudentName(missing[0]);
                ArrayList<Object> assmt = Grade.getAssessmentInfo(missing[1]);
                String message = studentName + " from " + Student.getStudentClass(missing[0]) + " is a missing grade for the " + assmt.get(1) + " titled \"" + assmt.get(2) + "\" given on " + assmt.get(3);
                System.out.println(message);
            }
        }
        else
        {
            Grade.calculateFinalGrade();
        }
    }
}
