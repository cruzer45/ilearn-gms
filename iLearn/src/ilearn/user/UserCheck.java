/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.user;

/**
 *
 * @author mrogers
 */
public class UserCheck
{

    /**
     * Check whether a user can access the Manage Menu.
     * @return
     */
    public static boolean canManage()
    {
        if (User.getUserGroup().equals("Administration"))
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
     * @return
     */
    public static boolean canAddStudent()
    {
        if (User.getUserGroup().equals("Administration")
                && User.getUserLevel().equals("Read Write"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
