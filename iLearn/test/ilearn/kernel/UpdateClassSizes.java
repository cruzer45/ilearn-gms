/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.kernel;

import ilearn.classes.Classes;

/**
 *
 * @author mrogers
 */
public class UpdateClassSizes
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Environment.createConnection();
        Classes.recalculateClassSize();
    }
}
