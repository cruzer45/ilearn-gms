/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.kernel;

import ilearn.ILearnApp;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author mrogers
 */
public class Environment
{

    private static String companyName = "";
    private static String telephone1 = "";
    private static String telephone2 = "";
    private static String address = "";
    private static String branchCode = "";
    private static String registrationCode = "";
    private static String timeCode = "";
    private static String dbVersion = "";
    public static Connection dbConnection;
    static Properties properties = new Properties();

    public static void getProperties()
    {
        try
        {
            properties.load(new FileInputStream("conf/iLearn.properties"));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(ILearnApp.getApplication().getMainFrame(),
                    "An error occurred."
                    + "\nCould not read the application's settings."
                    + "\n\nKindly consult the system administrator.",
                    "iLoan", JOptionPane.ERROR_MESSAGE);
            System.err.println("The properties file could not be read.");
            System.exit(1);
        }
    }

    /**
     * This method creates the connection to the database.
     * @return a connection object verifying that the database is live.
     */
    public static void createConnection()
    {
        getProperties();

        String dbDriver = EncryptionHandler.decrypt(properties.getProperty("dbDriver"));
        String dbLocation = EncryptionHandler.decrypt(properties.getProperty("dbLocation"));
        String dbUser = EncryptionHandler.decrypt(properties.getProperty("dbUser"));
        String dbPass = EncryptionHandler.decrypt(properties.getProperty("dbPass"));

        //Try to connect to the database
        try
        {
            //Create database connection objects.
            Class.forName(dbDriver); //set the database driver
            dbConnection = DriverManager.getConnection(dbLocation, dbUser, dbPass);
            String message = "Successfully connected to the database.";
            Logger.getLogger(Environment.class.getName()).log(Level.INFO, message);
        }
        catch (ClassNotFoundException cnfEx)
        {
            String message = "ERROR: The driver specified could not be found.";
            Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, message, cnfEx);
            message = "An error occurred while connecting to the database.\n"
                    + "Kindly check with your system administrator.";
            Utilities.showErrorMessage(null, message);
            ILearnApp.getApplication().exit();
        }
        catch (SQLException sqlEx)
        {
            String message = "ERROR: Could not connect to the database.";
            Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, message, sqlEx);
            message = "An error occurred while connecting to the database.\n"
                    + "Kindly check your connection and consult with your system administrator.";
            Utilities.showErrorMessage(null, message);
            ILearnApp.getApplication().exit();
        }
        catch (Exception e)
        {
            String message = "ERROR: Could not connect to the database.";
            Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, message, e);
            message = "An error occurred while connecting to the database.\n"
                    + "Kindly check with your system administrator.";
            Utilities.showErrorMessage(null, message);
            ILearnApp.getApplication().exit();
        }
    }

    public static void checkAppInfo()
    {
        //Check DB Version
        int minimumDBVersion = Integer.valueOf(properties.getProperty("dbVersion"));
        int foundDBVersion = Integer.valueOf(dbVersion);
        if (foundDBVersion < minimumDBVersion)
        {
            String message = "The database was meant to be used with another version of this application.\n"
                    + "To protect your data, the program will now exit.";
            Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, message);
            Utilities.showErrorMessage(null, message);
            ILearnApp.getApplication().quit(null);
        }

        //Check time code
        try
        {
            Date currentDate = Utilities.YMD_Formatter.parse(Utilities.YMD_Formatter.format(new Date()));
            Date expiryDate = Utilities.YMD_Formatter.parse(EncryptionHandler.decrypt(timeCode));

            //TODO add code to calculate date difference
            if (expiryDate.before(currentDate))
            {
                String message = "The license has expired.\n"
                        + "Kindly contact your provider to get a new license.";
                Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, message);
                Utilities.showErrorMessage(null, message);
                ILearnApp.getApplication().quit(null);
            }
        }
        catch (ParseException ex)
        {
            Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the dbConnection
     */
    public static Connection getConnection()
    {
        return dbConnection;
    }
}
