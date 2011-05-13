/*
 * ILearnView.java
 */
package ilearn;

import ilearn.assessments.FrmCreateAssessment;
import ilearn.assessments.FrmEditAssessment;
import ilearn.term.FrmAddTerm;
import ilearn.classes.FrmAddNewClass;
import ilearn.classes.FrmEditClass;
import ilearn.classes.FrmViewClass;
import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.register.FrmRegister;
import ilearn.reports.DialogStudentByClass;
import ilearn.reports.ReportViewer;
import ilearn.school.FrmManageSchool;
import ilearn.staff.FrmAddStaff;
import ilearn.staff.FrmEditStaff;
import ilearn.student.FrmEditStudent;
import ilearn.user.FrmAddUser;
import ilearn.user.FrmEditUser;
import ilearn.user.FrmLogin;
import ilearn.student.FrmNewStudent;
import ilearn.student.FrmViewStudent;
import ilearn.subject.FrmAddSubject;
import ilearn.subject.FrmEditSubject;
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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 * The application's main frame.
 */
public class ILearnView extends FrameView
{

    /**
     * Declare class variables
     */
    private static final Logger logger = Logger.getLogger(ILearnView.class.getName());
    ResourceMap resourceMap = getResourceMap();
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
    FrmAddStaff frmAddStaff = null;
    FrmEditStaff frmEditStaff = null;
    FrmEditClass frmEditClass = null;
    FrmManageSchool frmManageSchool = null;
    FrmViewClass frmViewClass = null;
    FrmEditStudent frmEditStudent = null;
    FrmViewStudent frmViewStudent = null;
    FrmEditSubject frmEditSubject = null;
    FrmCreateAssessment frmCreateAssessment = null;
    FrmEditAssessment frmEditAssessment = null;
    FrmRegister frmRegister = null;

    public ILearnView(SingleFrameApplication app)
    {
        super(app);
        initComponents();
        // status bar initialization - message timeout, idle icon and busy animation, etc
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
        Environment.setDesktopPane(desktopPane);
        showLoginScreen();
        //checkPrivileges();
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
        logOff = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        studentMenu = new javax.swing.JMenu();
        addStudent = new javax.swing.JMenuItem();
        editStudent = new javax.swing.JMenuItem();
        viewStudent = new javax.swing.JMenuItem();
        classMenu = new javax.swing.JMenu();
        addClass = new javax.swing.JMenuItem();
        editClass = new javax.swing.JMenuItem();
        viewClass = new javax.swing.JMenuItem();
        enterRollCall = new javax.swing.JMenuItem();
        gradesMenu = new javax.swing.JMenu();
        createAssessment = new javax.swing.JMenuItem();
        editAssessment = new javax.swing.JMenuItem();
        reportsMenu = new javax.swing.JMenu();
        studentReports = new javax.swing.JMenu();
        studentList = new javax.swing.JMenuItem();
        classReports = new javax.swing.JMenu();
        classListReport = new javax.swing.JMenuItem();
        manageMenu = new javax.swing.JMenu();
        staffMenu = new javax.swing.JMenu();
        addStaff = new javax.swing.JMenuItem();
        editStaff = new javax.swing.JMenuItem();
        subjectMenu = new javax.swing.JMenu();
        addSubject = new javax.swing.JMenuItem();
        editSubject = new javax.swing.JMenuItem();
        termMenu = new javax.swing.JMenu();
        addTerm = new javax.swing.JMenuItem();
        editTerm = new javax.swing.JMenuItem();
        timeSlotsMenu = new javax.swing.JMenu();
        addTimeSlot = new javax.swing.JMenuItem();
        editTimeSlot = new javax.swing.JMenuItem();
        userMenu = new javax.swing.JMenu();
        addUser = new javax.swing.JMenuItem();
        editUser = new javax.swing.JMenuItem();
        schoolInfo = new javax.swing.JMenuItem();
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
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(ILearnView.class, this);
        logOff.setAction(actionMap.get("logOut")); // NOI18N
        logOff.setIcon(resourceMap.getIcon("logOff.icon")); // NOI18N
        logOff.setText(resourceMap.getString("logOff.text")); // NOI18N
        logOff.setName("logOff"); // NOI18N
        fileMenu.add(logOff);

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        studentMenu.setText(resourceMap.getString("studentMenu.text")); // NOI18N
        studentMenu.setName("studentMenu"); // NOI18N

        addStudent.setAction(actionMap.get("showAddStudent")); // NOI18N
        addStudent.setText(resourceMap.getString("addStudent.text")); // NOI18N
        addStudent.setName("addStudent"); // NOI18N
        studentMenu.add(addStudent);

        editStudent.setAction(actionMap.get("showEditStudent")); // NOI18N
        editStudent.setIcon(resourceMap.getIcon("editStudent.icon")); // NOI18N
        editStudent.setText(resourceMap.getString("editStudent.text")); // NOI18N
        editStudent.setName("editStudent"); // NOI18N
        studentMenu.add(editStudent);

        viewStudent.setAction(actionMap.get("showViewStudent")); // NOI18N
        viewStudent.setIcon(resourceMap.getIcon("viewStudent.icon")); // NOI18N
        viewStudent.setText(resourceMap.getString("viewStudent.text")); // NOI18N
        viewStudent.setName("viewStudent"); // NOI18N
        studentMenu.add(viewStudent);

        menuBar.add(studentMenu);

        classMenu.setText(resourceMap.getString("classMenu.text")); // NOI18N
        classMenu.setName("classMenu"); // NOI18N

        addClass.setAction(actionMap.get("showAddClass")); // NOI18N
        addClass.setIcon(resourceMap.getIcon("addClass.icon")); // NOI18N
        addClass.setText(resourceMap.getString("addClass.text")); // NOI18N
        addClass.setName("addClass"); // NOI18N
        classMenu.add(addClass);

        editClass.setAction(actionMap.get("showEditClass")); // NOI18N
        editClass.setIcon(resourceMap.getIcon("editClass.icon")); // NOI18N
        editClass.setText(resourceMap.getString("editClass.text")); // NOI18N
        editClass.setName("editClass"); // NOI18N
        classMenu.add(editClass);

        viewClass.setAction(actionMap.get("showViewClass")); // NOI18N
        viewClass.setIcon(resourceMap.getIcon("viewClass.icon")); // NOI18N
        viewClass.setText(resourceMap.getString("viewClass.text")); // NOI18N
        viewClass.setName("viewClass"); // NOI18N
        classMenu.add(viewClass);

        enterRollCall.setAction(actionMap.get("showRegister")); // NOI18N
        enterRollCall.setIcon(resourceMap.getIcon("enterRollCall.icon")); // NOI18N
        enterRollCall.setText(resourceMap.getString("enterRollCall.text")); // NOI18N
        enterRollCall.setName("enterRollCall"); // NOI18N
        classMenu.add(enterRollCall);

        menuBar.add(classMenu);

        gradesMenu.setText(resourceMap.getString("gradesMenu.text")); // NOI18N
        gradesMenu.setName("gradesMenu"); // NOI18N

        createAssessment.setAction(actionMap.get("showCreateAssessment")); // NOI18N
        createAssessment.setIcon(resourceMap.getIcon("createAssessment.icon")); // NOI18N
        createAssessment.setText(resourceMap.getString("createAssessment.text")); // NOI18N
        createAssessment.setName("createAssessment"); // NOI18N
        gradesMenu.add(createAssessment);

        editAssessment.setAction(actionMap.get("showEditAssessment")); // NOI18N
        editAssessment.setIcon(resourceMap.getIcon("editAssessment.icon")); // NOI18N
        editAssessment.setText(resourceMap.getString("editAssessment.text")); // NOI18N
        editAssessment.setName("editAssessment"); // NOI18N
        gradesMenu.add(editAssessment);

        menuBar.add(gradesMenu);

        reportsMenu.setText(resourceMap.getString("reportsMenu.text")); // NOI18N
        reportsMenu.setName("reportsMenu"); // NOI18N

        studentReports.setIcon(resourceMap.getIcon("studentReports.icon")); // NOI18N
        studentReports.setText(resourceMap.getString("studentReports.text")); // NOI18N
        studentReports.setName("studentReports"); // NOI18N

        studentList.setAction(actionMap.get("showStudentListReport")); // NOI18N
        studentList.setIcon(resourceMap.getIcon("studentList.icon")); // NOI18N
        studentList.setText(resourceMap.getString("studentList.text")); // NOI18N
        studentList.setName("studentList"); // NOI18N
        studentReports.add(studentList);

        reportsMenu.add(studentReports);

        classReports.setIcon(resourceMap.getIcon("classReports.icon")); // NOI18N
        classReports.setText(resourceMap.getString("classReports.text")); // NOI18N
        classReports.setName("classReports"); // NOI18N

        classListReport.setAction(actionMap.get("showClassListingReport")); // NOI18N
        classListReport.setIcon(resourceMap.getIcon("classListReport.icon")); // NOI18N
        classListReport.setText(resourceMap.getString("classListReport.text")); // NOI18N
        classListReport.setName("classListReport"); // NOI18N
        classReports.add(classListReport);

        reportsMenu.add(classReports);

        menuBar.add(reportsMenu);

        manageMenu.setText(resourceMap.getString("manageMenu.text")); // NOI18N

        staffMenu.setIcon(resourceMap.getIcon("staffMenu.icon")); // NOI18N
        staffMenu.setText(resourceMap.getString("staffMenu.text")); // NOI18N
        staffMenu.setName("staffMenu"); // NOI18N

        addStaff.setAction(actionMap.get("showAddStaff")); // NOI18N
        addStaff.setIcon(resourceMap.getIcon("addStaff.icon")); // NOI18N
        addStaff.setText(resourceMap.getString("addStaff.text")); // NOI18N
        addStaff.setName("addStaff"); // NOI18N
        staffMenu.add(addStaff);

        editStaff.setAction(actionMap.get("showEditStaff")); // NOI18N
        editStaff.setIcon(resourceMap.getIcon("editStaff.icon")); // NOI18N
        editStaff.setText(resourceMap.getString("editStaff.text")); // NOI18N
        editStaff.setName("editStaff"); // NOI18N
        staffMenu.add(editStaff);

        manageMenu.add(staffMenu);

        subjectMenu.setIcon(resourceMap.getIcon("subjectMenu.icon")); // NOI18N
        subjectMenu.setText(resourceMap.getString("subjectMenu.text")); // NOI18N
        subjectMenu.setName("subjectMenu"); // NOI18N

        addSubject.setAction(actionMap.get("showAddSubject")); // NOI18N
        addSubject.setIcon(resourceMap.getIcon("addSubject.icon")); // NOI18N
        addSubject.setText(resourceMap.getString("addSubject.text")); // NOI18N
        addSubject.setName("addSubject"); // NOI18N
        subjectMenu.add(addSubject);

        editSubject.setAction(actionMap.get("showEditSubject")); // NOI18N
        editSubject.setIcon(resourceMap.getIcon("editSubject.icon")); // NOI18N
        editSubject.setText(resourceMap.getString("editSubject.text")); // NOI18N
        editSubject.setName("editSubject"); // NOI18N
        subjectMenu.add(editSubject);

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

        schoolInfo.setAction(actionMap.get("showManageSchool")); // NOI18N
        schoolInfo.setIcon(resourceMap.getIcon("schoolInfo.icon")); // NOI18N
        schoolInfo.setText(resourceMap.getString("schoolInfo.text")); // NOI18N
        schoolInfo.setName("schoolInfo"); // NOI18N
        manageMenu.add(schoolInfo);

        menuBar.add(manageMenu);
        manageMenu.setText("Manage");

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
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 630, Short.MAX_VALUE)
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
    {
        //Verify if the form is already loaded
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

    @Action
    public void showAddStaff()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Add Staff");
        if (AlreadyLoaded == false)
        {
            frmAddStaff = new FrmAddStaff();
            desktopPane.add(frmAddStaff);
            //Load the Form
            frmAddStaff.setVisible(true);
            frmAddStaff.show();
            try
            {
                frmAddStaff.setIcon(false);
                frmAddStaff.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showEditStaff()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Edit Staff");
        if (AlreadyLoaded == false)
        {
            frmEditStaff = new FrmEditStaff();
            desktopPane.add(frmEditStaff);
            //Load the Form
            frmEditStaff.setVisible(true);
            frmEditStaff.show();
            try
            {
                frmEditStaff.setIcon(false);
                frmEditStaff.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showEditClass()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Edit Class");
        if (AlreadyLoaded == false)
        {
            frmEditClass = new FrmEditClass();
            desktopPane.add(frmEditClass);
            //Load the Form
            frmEditClass.setVisible(true);
            frmEditClass.show();
            try
            {
                frmEditClass.setIcon(false);
                frmEditClass.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showManageSchool()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("School");
        if (AlreadyLoaded == false)
        {
            frmManageSchool = new FrmManageSchool();
            desktopPane.add(frmManageSchool);
            //Load the Form
            frmManageSchool.setVisible(true);
            frmManageSchool.show();
            try
            {
                frmManageSchool.setIcon(false);
                frmManageSchool.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void logOut()
    {
        String message = "Are you sure you want to logout?";
        int response = Utilities.showConfirmDialog(getFrame(), message);
        if (response == JOptionPane.YES_OPTION)
        {
            for (JInternalFrame frame : desktopPane.getAllFrames())
            {
                frame.dispose();
            }
            showLoginScreen();
            checkPrivileges();
        }
    }

    @Action
    public void showViewClass()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("View Class");
        if (AlreadyLoaded == false)
        {
            frmViewClass = new FrmViewClass();
            desktopPane.add(frmViewClass);
            //Load the Form
            frmViewClass.setVisible(true);
            frmViewClass.show();
            try
            {
                frmViewClass.setIcon(false);
                frmViewClass.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showEditStudent()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Edit Student");
        if (AlreadyLoaded == false)
        {
            frmEditStudent = new FrmEditStudent();
            desktopPane.add(frmEditStudent);
            //Load the Form
            frmEditStudent.setVisible(true);
            frmEditStudent.show();
            try
            {
                frmEditStudent.setIcon(false);
                frmEditStudent.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showViewStudent()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Edit Student");
        if (AlreadyLoaded == false)
        {
            frmViewStudent = new FrmViewStudent();
            desktopPane.add(frmViewStudent);
            //Load the Form
            frmViewStudent.setVisible(true);
            frmViewStudent.show();
            try
            {
                frmViewStudent.setIcon(false);
                frmViewStudent.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showEditSubject()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Edit Subject");
        if (AlreadyLoaded == false)
        {
            frmEditSubject = new FrmEditSubject();
            desktopPane.add(frmEditSubject);
            //Load the Form
            frmEditSubject.setVisible(true);
            frmEditSubject.show();
            try
            {
                frmEditSubject.setIcon(false);
                frmEditSubject.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    public JDesktopPane getDesktopPane()
    {
        return desktopPane;
    }

    @Action
    public void showStudentListReport()
    {
        DialogStudentByClass dialogStudentByClass = new DialogStudentByClass(getFrame(), true);
        dialogStudentByClass.setLocationRelativeTo(getFrame());
        dialogStudentByClass.setVisible(true);
    }

    @Action
    public void showClassListingReport()
    {
        String report = "reports/Class_List.jasper";
        String title = "Report - Class List";
        // Second, create a map of parameters to pass to the report.
        Map parameters = new HashMap();
        parameters.put("SUBREPORT_DIR", "reports/");
        try
        {
            ReportViewer.generateReport(report, parameters, title);
        }
        catch (Exception exception)
        {
            String message = "An error occurred while generating a report.";
            Logger.getLogger(DialogStudentByClass.class.getName()).log(Level.SEVERE, message, exception);
        }
    }

    @Action
    public void showCreateAssessment()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Create Assessment");
        if (AlreadyLoaded == false)
        {
            frmCreateAssessment = new FrmCreateAssessment();
            desktopPane.add(frmCreateAssessment);
            //Load the Form
            frmCreateAssessment.setVisible(true);
            frmCreateAssessment.show();
            try
            {
                frmCreateAssessment.setIcon(false);
                frmCreateAssessment.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showEditAssessment()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Edit Assessment");
        if (AlreadyLoaded == false)
        {
            frmEditAssessment = new FrmEditAssessment();
            desktopPane.add(frmEditAssessment);
            //Load the Form
            frmEditAssessment.setVisible(true);
            frmEditAssessment.show();
            try
            {
                frmEditAssessment.setIcon(false);
                frmEditAssessment.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showRegister()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Class Register");
        if (AlreadyLoaded == false)
        {
            frmRegister = new FrmRegister();
            desktopPane.add(frmRegister);
            //Load the Form
            frmRegister.setVisible(true);
            frmRegister.show();
            try
            {
                frmRegister.setIcon(false);
                frmRegister.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addClass;
    private javax.swing.JMenuItem addStaff;
    private javax.swing.JMenuItem addStudent;
    private javax.swing.JMenuItem addSubject;
    private javax.swing.JMenuItem addTerm;
    private javax.swing.JMenuItem addTimeSlot;
    private javax.swing.JMenuItem addUser;
    private javax.swing.JMenuItem classListReport;
    private javax.swing.JMenu classMenu;
    private javax.swing.JMenu classReports;
    private javax.swing.JMenuItem createAssessment;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem editAssessment;
    private javax.swing.JMenuItem editClass;
    private javax.swing.JMenuItem editStaff;
    private javax.swing.JMenuItem editStudent;
    private javax.swing.JMenuItem editSubject;
    private javax.swing.JMenuItem editTerm;
    private javax.swing.JMenuItem editTimeSlot;
    private javax.swing.JMenuItem editUser;
    private javax.swing.JMenuItem enterRollCall;
    private javax.swing.JMenu gradesMenu;
    private javax.swing.JMenuItem logOff;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenu manageMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JMenu reportsMenu;
    private javax.swing.JMenuItem schoolInfo;
    private javax.swing.JMenu staffMenu;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JMenuItem studentList;
    private javax.swing.JMenu studentMenu;
    private javax.swing.JMenu studentReports;
    private javax.swing.JMenu subjectMenu;
    private javax.swing.JMenu termMenu;
    private javax.swing.JMenu timeSlotsMenu;
    private javax.swing.JMenu userMenu;
    private javax.swing.JMenuItem viewClass;
    private javax.swing.JMenuItem viewStudent;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
}
