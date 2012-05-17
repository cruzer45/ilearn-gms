/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.kernel;

import ilearn.grades.Grade;

/**
 *
 * @author mrogers
 */
public class CalculateFirstTermGradeForTCC
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Environment.createConnection();
        Grade.calculateMidAverage();
        
        System.exit(0);
    }
}
