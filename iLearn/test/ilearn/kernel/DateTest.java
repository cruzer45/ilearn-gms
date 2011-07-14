/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ilearn.kernel;

/**
 *
 * @author mrogers
 */
public class DateTest
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println(new java.text.SimpleDateFormat("MMM d yyyy  h:mm a").format(new java.util.Date()));
    }

}
