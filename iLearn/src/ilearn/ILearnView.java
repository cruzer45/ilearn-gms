/*
 * ILearnView.java
 */
package ilearn;

import ilearn.term.FrmAddTerm;
import ilearn.classes.FrmAddNewClass;
import ilearn.kernel.Environment;
import ilearn.user.FrmAddUser;
import ilearn.user.FrmEditUser;
import ilearn.user.FrmLogin;
import ilearn.student.FrmNewStudent;
import ilearn.subject.FrmAddSubject;
import ilearn.term.FrmEditTimeSlots;
import ilearn.term.FrmAddTimeSlot;
import ilearn.term.FrmEditTerm;
import ilearn.user.UserCheck;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 * The application's main frame.
 */
public class ILearnView extends FrameView
{

    /**
     * Declare the form objects.
     */
    FrmNewStudent frmNewStudent = null;
    FrmAddNewClass frmAddNewClass = null;
    FrmAddSubject frmAddSubject = null;
    FrmAddUser frmAddUser = null;
    FrmEditUser frmEditUser = null;
    FrmAddTerm frmAddTerm = null;
    FrmEditTerm frmEditTerm = null;
    FrmAddTimeSlot frmAddTimeSlot = null;
    FrmEditTimeSlots frmEditTimeSlots = null;

    private static final Logger logger = Logger.getLogger(ILearnView.class.getName());

    public ILearnView(SingleFrameApplication app)
    {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++)
        {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {

            public void propertyChange(java.beans.PropertyChangeEvent evt)
            {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName))
                {
                    if (!busyIconTimer.isRunning())
                    {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                }
                else if ("done".equals(propertyName))
                {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                }
                else if ("message".equals(propertyName))
                {
                    String text = (String) (evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                }
                else if ("progress".equals(propertyName))
                {
                    int value = (Integer) (evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });

        Environment.createConnection();
        showLoginScreen();
        checkPrivileges();
        //Environment.startTimer();
    }

    /**
     * This function checks if a specified form is already displayed.
     * It accepts the window title in the form of a string and checks if
     * it is already loaded onto the desktop pane.  It then returns a boolean
     * depending on the result of the test.
     *
     * @param FormTitle
     * @return True if a loaded frame contains the specified string in title.  False if no frames contains the specified string.
     */
    protected boolean isLoaded(String FormTitle)
    {
        JInternalFrame Form[] = desktopPane.getAllFrames();
        for (int i = 0; i < Form.length; i++)
        {
            if (Form[i].getTitle().equalsIgnoreCase(FormTitle))
            {
                Form[i].show();
                try
                {
                    Form[i].setIcon(false);
                    Form[i].setSelected(true);
                }
                catch (Exception e)
                {
                   logger.log(Level.SEVERE, "Error displaying form.", e);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Shows the About window.
     */
    @Action
    public void showAboutBox()
    {
        if (aboutBox == null)
        {
            JFrame mainFrame = ILearnApp.getApplication().getMainFrame();
            aboutBox = new ILearnAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        ILearnApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        manageMenu = new javax.swing.JMenu();
        classMenu = new javax.swing.JMenu();
        addClass = new javax.swing.JMenuItem();
        studentMenu = new javax.swing.JMenu();
        addStudent = new javax.swing.JMenuItem();
        subjectMenu = new javax.swing.JMenu();
        addSubject = new javax.swing.JMenuItem();
        termMenu = new javax.swing.JMenu();
        addTerm = new javax.swing.JMenuItem();
        editTerm = new javax.swing.JMenuItem();
        timeSlotsMenu = new javax.swing.JMenu();
        addTimeSlot = new javax.swing.JMenuItem();
        editTimeSlot = new javax.swing.JMenuItem();
        userMenu = new javax.swing.JMenu();
        addUser = new javax.swing.JMenuItem();
        editUser = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(ILearnView.class);
        desktopPane.setBackground(resourceMap.getColor("desktopPane.background")); // NOI18N
        desktopPane.setName("desktopPane"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(ILearnView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        manageMenu.setText(resourceMap.getString("manageMenu.text")); // NOI18N
        manageMenu.setName("manageMenu"); // NOI18N

        classMenu.setIcon(resourceMap.getIcon("classMenu.icon")); // NOI18N
        classMenu.setText(resourceMap.getString("classMenu.text")); // NOI18N
        classMenu.setName("classMenu"); // NOI18N

        addClass.setAction(actionMap.get("showAddClass")); // NOI18N
        addClass.setIcon(resourceMap.getIcon("addClass.icon")); // NOI18N
        addClass.setText(resourceMap.getString("addClass.text")); // NOI18N
        addClass.setName("addClass"); // NOI18N
        classMenu.add(addClass);

        manageMenu.add(classMenu);

        studentMenu.setIcon(resourceMap.getIcon("studentMenu.icon")); // NOI18N
        studentMenu.setText(resourceMap.getString("studentMenu.text")); // NOI18N
        studentMenu.setName("studentMenu"); // NOI18N

        addStudent.setAction(actionMap.get("showAddStudent")); // NOI18N
        addStudent.setText(resourceMap.getString("addStudent.text")); // NOI18N
        addStudent.setName("addStudent"); // NOI18N
        studentMenu.add(addStudent);

        manageMenu.add(studentMenu);

        subjectMenu.setIcon(resourceMap.getIcon("subjectMenu.icon")); // NOI18N
        subjectMenu.setText(resourceMap.getString("subjectMenu.text")); // NOI18N
        subjectMenu.setName("subjectMenu"); // NOI18N

        addSubject.setAction(actionMap.get("showAddSubject")); // NOI18N
        addSubject.setIcon(resourceMap.getIcon("addSubject.icon")); // NOI18N
        addSubject.setText(resourceMap.getString("addSubject.text")); // NOI18N
        addSubject.setName("addSubject"); // NOI18N
        subjectMenu.add(addSubject);

        manageMenu.add(subjectMenu);

        termMenu.setIcon(resourceMap.getIcon("termMenu.icon")); // NOI18N
        termMenu.setText(resourceMap.getString("termMenu.text")); // NOI18N
        termMenu.setName("termMenu"); // NOI18N

        addTerm.setAction(actionMap.get("showAddTerm")); // NOI18N
        addTerm.setText(resourceMap.getString("addTerm.text")); // NOI18N
        addTerm.setName("addTerm"); // NOI18N
        termMenu.add(addTerm);

        editTerm.setAction(actionMap.get("showEditTerm")); // NOI18N
        editTerm.setText(resourceMap.getString("editTerm.text")); // NOI18N
        editTerm.setName("editTerm"); // NOI18N
        termMenu.add(editTerm);

        manageMenu.add(termMenu);

        timeSlotsMenu.setIcon(resourceMap.getIcon("timeSlotsMenu.icon")); // NOI18N
        timeSlotsMenu.setText(resourceMap.getString("timeSlotsMenu.text")); // NOI18N
        timeSlotsMenu.setName("timeSlotsMenu"); // NOI18N

        addTimeSlot.setAction(actionMap.get("showAddTimeSlot")); // NOI18N
        addTimeSlot.setText(resourceMap.getString("addTimeSlot.text")); // NOI18N
        addTimeSlot.setName("addTimeSlot"); // NOI18N
        timeSlotsMenu.add(addTimeSlot);

        editTimeSlot.setAction(actionMap.get("showEditTimeSlots")); // NOI18N
        editTimeSlot.setText(resourceMap.getString("editTimeSlot.text")); // NOI18N
        editTimeSlot.setName("editTimeSlot"); // NOI18N
        timeSlotsMenu.add(editTimeSlot);

        manageMenu.add(timeSlotsMenu);

        userMenu.setIcon(resourceMap.getIcon("userMenu.icon")); // NOI18N
        userMenu.setText(resourceMap.getString("userMenu.text")); // NOI18N
        userMenu.setName("userMenu"); // NOI18N

        addUser.setAction(actionMap.get("showAddUser")); // NOI18N
        addUser.setIcon(resourceMap.getIcon("addUser.icon")); // NOI18N
        addUser.setText(resourceMap.getString("addUser.text")); // NOI18N
        addUser.setName("addUser"); // NOI18N
        userMenu.add(addUser);

        editUser.setAction(actionMap.get("showEditUser")); // NOI18N
        editUser.setText(resourceMap.getString("editUser.text")); // NOI18N
        editUser.setName("editUser"); // NOI18N
        userMenu.add(editUser);

        manageMenu.add(userMenu);

        menuBar.add(manageMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 216, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Shows the Add Student Window
     */
    @Action
    public void showAddStudent()
    { //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Add Student");
        if (AlreadyLoaded == false)
        {
            frmNewStudent = new FrmNewStudent();
            desktopPane.add(frmNewStudent);

            //Load the Form
            frmNewStudent.setVisible(true);
            frmNewStudent.show();
            try
            {
                frmNewStudent.setIcon(false);
                frmNewStudent.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    /**
     * Shows the Add Class Window.
     */
    @Action
    public void showAddClass()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Add Class");
        if (AlreadyLoaded == false)
        {
            frmAddNewClass = new FrmAddNewClass();
            desktopPane.add(frmAddNewClass);

            //Load the Form
            frmAddNewClass.setVisible(true);
            frmAddNewClass.show();
            try
            {
                frmAddNewClass.setIcon(false);
                frmAddNewClass.setSelected(true);
            }
            catch (Exception e)
            {
               logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    /**
     * Shows the Add Subject Window.
     */
    @Action
    public void showAddSubject()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Add Subject");
        if (AlreadyLoaded == false)
        {
            frmAddSubject = new FrmAddSubject();
            desktopPane.add(frmAddSubject);

            //Load the Form
            frmAddSubject.setVisible(true);
            frmAddSubject.show();
            try
            {
                frmAddSubject.setIcon(false);
                frmAddSubject.setSelected(true);
            }
            catch (Exception e)
            {
               logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    /**
     * Shows the Login Screen.
     */
    private void showLoginScreen()
    {
        FrmLogin frmLogin = new FrmLogin(this.getFrame(), true);
        frmLogin.setLocationRelativeTo(this.getFrame());
        frmLogin.setVisible(true);
    }

    /**
     * Displays the Add User window.
     */
    @Action
    public void showAddUser()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Add User");
        if (AlreadyLoaded == false)
        {
            frmAddUser = new FrmAddUser();
            desktopPane.add(frmAddUser);

            //Load the Form
            frmAddUser.setVisible(true);
            frmAddUser.show();
            try
            {
                frmAddUser.setIcon(false);
                frmAddUser.setSelected(true);
            }
            catch (Exception e)
            {
               logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    /**
     * Displays the Edit User window.
     */
    @Action
    public void showEditUser()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Edit User");
        if (AlreadyLoaded == false)
        {
            frmEditUser = new FrmEditUser();
            desktopPane.add(frmEditUser);

            //Load the Form
            frmEditUser.setVisible(true);
            frmEditUser.show();
            try
            {
                frmEditUser.setIcon(false);
                frmEditUser.setSelected(true);
            }
            catch (Exception e)
            {
               logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    /**
     * This method checks what the user can and cannot access.
     * It then enables or disables the menu items based on those checks.
     */
    private void checkPrivileges()
    {
        //********* Manage Menu ****************
        manageMenu.setEnabled(UserCheck.canManage());
        addStudent.setEnabled(UserCheck.canAddStudent());

    }

    /**
     * Displays the Add Term window
     */
    @Action
    public void showAddTerm()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Add Term");
        if (AlreadyLoaded == false)
        {
            frmAddTerm = new FrmAddTerm();
            desktopPane.add(frmAddTerm);

            //Load the Form
            frmAddTerm.setVisible(true);
            frmAddTerm.show();
            try
            {
                frmAddTerm.setIcon(false);
                frmAddTerm.setSelected(true);
            }
            catch (Exception e)
            {
               logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    /**
     * Displays the Edit Term window.
     */
    @Action
    public void showEditTerm()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Edit Term");
        if (AlreadyLoaded == false)
        {
            frmEditTerm = new FrmEditTerm();
            desktopPane.add(frmEditTerm);

            //Load the Form
            frmEditTerm.setVisible(true);
            frmEditTerm.show();
            try
            {
                frmEditTerm.setIcon(false);
                frmEditTerm.setSelected(true);
            }
            catch (Exception e)
            {
               logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showEditTimeSlots()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Edit Time Slots");
        if (AlreadyLoaded == false)
        {
            frmEditTimeSlots = new FrmEditTimeSlots();
            desktopPane.add(frmEditTimeSlots);

            //Load the Form
            frmEditTimeSlots.setVisible(true);
            frmEditTimeSlots.show();
            try
            {
                frmEditTimeSlots.setIcon(false);
                frmEditTimeSlots.setSelected(true);
            }
            catch (Exception e)
            {
               logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showAddTimeSlot()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Add Time Slot");
        if (AlreadyLoaded == false)
        {
            frmAddTimeSlot = new FrmAddTimeSlot();
            desktopPane.add(frmAddTimeSlot);

            //Load the Form
            frmAddTimeSlot.setVisible(true);
            frmAddTimeSlot.show();
            try
            {
                frmAddTimeSlot.setIcon(false);
                frmAddTimeSlot.setSelected(true);
            }
            catch (Exception e)
            {
               logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addClass;
    private javax.swing.JMenuItem addStudent;
    private javax.swing.JMenuItem addSubject;
    private javax.swing.JMenuItem addTerm;
    private javax.swing.JMenuItem addTimeSlot;
    private javax.swing.JMenuItem addUser;
    private javax.swing.JMenu classMenu;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem editTerm;
    private javax.swing.JMenuItem editTimeSlot;
    private javax.swing.JMenuItem editUser;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenu manageMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JMenu studentMenu;
    private javax.swing.JMenu subjectMenu;
    private javax.swing.JMenu termMenu;
    private javax.swing.JMenu timeSlotsMenu;
    private javax.swing.JMenu userMenu;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
}
