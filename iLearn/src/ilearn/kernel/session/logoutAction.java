/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.kernel.session;

import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.kernel.logger.iLogger;
import ilearn.user.FrmLogin;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JInternalFrame;

/**
 *
 * @author m.rogers
 */
public class logoutAction extends AbstractAction
{

    public void actionPerformed(ActionEvent e)
    {
        String message = "You have been logged out due to inactivity.";
        Utilities.showInfoMessage(null, message);
        showLoginScreen();
    }

    /**
     * Shows the Login Screen.
     */
    private void showLoginScreen()
    {
        for (JInternalFrame frame : Environment.getDesktopPane().getAllFrames())
        {
            frame.dispose();
        }
        String message = "The user successfully logged Off.";
        iLogger.logMessage(message, "Log Off", "User");
        FrmLogin frmLogin = new FrmLogin(Environment.getMainFrame(), true);
        frmLogin.setLocationRelativeTo(Environment.getMainFrame());
        frmLogin.setVisible(true);
    }
}
