/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.kernel;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;

/**
 *
 * @author mrogers
 */
public class Test
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException
    {
//        String computername = InetAddress.getLocalHost().getHostName();
//        InetAddress[] ip = Inet4Address.getAllByName(computername);
//        String message = "SUCCESS: The user successfully logged on from " + ip[1] + ".";
//        System.out.println(message);
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
        System.exit(0);
    }
}
