/*
 * ILearnApp.java
 */
package ilearn;

import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.kernel.logger.iLogger;
import java.util.EventObject;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class ILearnApp extends SingleFrameApplication
{

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup()
    {
        show(new ILearnView(this));
        ILearnApp.getApplication().addExitListener(new ExitListener()
        {
            public boolean canExit(EventObject arg0)
            {
                return exitMethod();
            }
            public void willExit(EventObject arg0)
            {
            }
            private boolean exitMethod()
            {
                String message = "Are you sure you want to exit?";
                int response = Utilities.showConfirmDialog(null, message);
                if (response == JOptionPane.YES_OPTION)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });
    }

    @Override
    protected void shutdown()
    {
        String message = "The user successfully logged Off.";
        iLogger.logMessage(message, "Log Off", "User");
        Environment.closeConnection();
        super.shutdown();
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root)
    {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ILearnApp
     */
    public static ILearnApp getApplication()
    {
        return Application.getInstance(ILearnApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args)
    {
        String[] li =
        {
            "Licensee=Maurice Rogers", "LicenseRegistrationNumber=------", "Product=Synthetica", "LicenseType=Non Commercial", "ExpireDate=--.--.----", "MaxVersion=2.999.999"
        };
        UIManager.put("Synthetica.license.info", li);
        UIManager.put("Synthetica.license.key", "2BCF99E0-3738913D-F30B5EC9-622511CC-4F19572A");
        launch(ILearnApp.class, args);
    }
}
