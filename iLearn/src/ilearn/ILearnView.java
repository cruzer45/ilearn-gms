/*
 * ILearnView.java
 */
package ilearn;

import ilearn.kernel.session.logoutAction;
import ilearn.grades.FrmRecordGrade;
import ilearn.grades.FrmEditGrades;
import ilearn.term.FrmAddTerm;
import ilearn.classes.FrmAddNewClass;
import ilearn.classes.FrmEditClass;
import ilearn.classes.FrmViewClass;
import ilearn.demerits.FrmEditDemerit;
import ilearn.demerits.FrmRecordDemerit;
import ilearn.detentions.FrmEditDetention;
import ilearn.detentions.FrmRecordDetention;
import ilearn.detentions.FrmRecordServedDetention;
import ilearn.grades.FrmEditMidTerms;
import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.kernel.logger.iLogger;
import ilearn.kernel.session.InactivityListener;
import ilearn.promotion.FrmAssignPromotions;
import ilearn.promotion.FrmPromote;
import ilearn.register.FrmEditRegister;
import ilearn.register.FrmRegister;
import ilearn.reports.DialogStudentByClass;
import ilearn.reports.FrmClassGradebook;
import ilearn.reports.FrmDemeritsByStudent;
import ilearn.reports.FrmPrintIDCards;
import ilearn.reports.ReportLoader;
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
import ilearn.term.FrmCloseTerm;
import ilearn.term.FrmEditTerm;
import ilearn.user.FrmChangePassword;
import ilearn.user.User;
import ilearn.user.UserCheck;
import ilearn.utils.FrmCalculateMidTerms;
import ilearn.utils.FrmExcellClassListImporter;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.Task;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.Icon;
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
    FrmRecordGrade frmRecordGrade = null;
    FrmEditGrades frmEditGrade = null;
    FrmRegister frmRegister = null;
    FrmChangePassword frmChangePassword = null;
    FrmEditRegister frmEditRegister = null;
    FrmCloseTerm frmCloseTerm = null;
    FrmAssignPromotions frmAssignPromotions = null;
    FrmPromote frmPromote = null;
    FrmCalculateMidTerms frmCalculateMidTerms = null;
    FrmEditMidTerms frmEditMidTerms = null;
    FrmRecordDemerit frmRecordDemerit = null;
    FrmEditDemerit frmEditDemerit = null;
    FrmDemeritsByStudent frmDemeritsByStudent = null;
    FrmPrintIDCards frmPrintIDCards = null;
    FrmRecordDetention frmRecordDetention = null;
    FrmEditDetention frmEditDetention = null;
    FrmRecordServedDetention frmRecordServedDetention = null;
    FrmExcellClassListImporter frmExcellClassListImporter = null;

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
        Environment.setMainFrame(getFrame());
        showLoginScreen();
        //Start the timer.
        int timeout = User.getTimeout();
        if (timeout != 0)
        {
            javax.swing.Action logout = (javax.swing.Action) new logoutAction();
            InactivityListener listener = new InactivityListener(logout, timeout);
            listener.start();
        }
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
    private void initComponents()
    {
        mainPanel = new javax.swing.JPanel();
        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        logOff = new javax.swing.JMenuItem();
        changePasswordMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        studentMenu = new javax.swing.JMenu();
        addStudent = new javax.swing.JMenuItem();
        editStudent = new javax.swing.JMenuItem();
        viewStudent = new javax.swing.JMenuItem();
        attendanceMenu = new javax.swing.JMenu();
        enterAttendance = new javax.swing.JMenuItem();
        editAttendance = new javax.swing.JMenuItem();
        gradesMenu = new javax.swing.JMenu();
        createAssessment = new javax.swing.JMenuItem();
        editAssessment = new javax.swing.JMenuItem();
        editMidTerms = new javax.swing.JMenuItem();
        demeritsMenu = new javax.swing.JMenu();
        recordDemerits = new javax.swing.JMenuItem();
        editDemerits = new javax.swing.JMenuItem();
        detentionsMenu = new javax.swing.JMenu();
        recordDetention = new javax.swing.JMenuItem();
        editDetention = new javax.swing.JMenuItem();
        recordServedDetention = new javax.swing.JMenuItem();
        reportsMenu = new javax.swing.JMenu();
        studentReports = new javax.swing.JMenu();
        studentList = new javax.swing.JMenuItem();
        studentsRepeating = new javax.swing.JMenuItem();
        studentListByClass = new javax.swing.JMenuItem();
        studentIDCards = new javax.swing.JMenuItem();
        classReports = new javax.swing.JMenu();
        classListReport = new javax.swing.JMenuItem();
        classGradeBook = new javax.swing.JMenuItem();
        reportCardsMenu = new javax.swing.JMenu();
        midTermReports = new javax.swing.JMenuItem();
        classRankReport = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        termEndReports = new javax.swing.JMenuItem();
        termRanking = new javax.swing.JMenuItem();
        demeritReports = new javax.swing.JMenu();
        demeritsByCass = new javax.swing.JMenuItem();
        demeritsByStudent = new javax.swing.JMenuItem();
        statisticsReports = new javax.swing.JMenu();
        classSizeDistribution = new javax.swing.JMenuItem();
        genderDistribution = new javax.swing.JMenuItem();
        manageMenu = new javax.swing.JMenu();
        classMenu = new javax.swing.JMenu();
        addClass = new javax.swing.JMenuItem();
        editClass = new javax.swing.JMenuItem();
        viewClass = new javax.swing.JMenuItem();
        promoteMenu = new javax.swing.JMenu();
        assignPromotions = new javax.swing.JMenuItem();
        promoteStudents = new javax.swing.JMenuItem();
        manageSchool = new javax.swing.JMenuItem();
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
        utilitiesMenu = new javax.swing.JMenu();
        midTerm = new javax.swing.JMenu();
        calculateMidTerm = new javax.swing.JMenuItem();
        endOfTerm = new javax.swing.JMenu();
        closeTerm = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        mainPanel.setName("mainPanel"); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(ILearnView.class);
        desktopPane.setBackground(resourceMap.getColor("desktopPane.background")); // NOI18N
        desktopPane.setName("desktopPane"); // NOI18N
        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
        );
        menuBar.setName("menuBar"); // NOI18N
        fileMenu.setMnemonic('F');
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(ILearnView.class, this);
        logOff.setAction(actionMap.get("logOut")); // NOI18N
        logOff.setIcon(resourceMap.getIcon("logOff.icon")); // NOI18N
        logOff.setText(resourceMap.getString("logOff.text")); // NOI18N
        logOff.setName("logOff"); // NOI18N
        fileMenu.add(logOff);
        changePasswordMenuItem.setAction(actionMap.get("showChangePassword")); // NOI18N
        changePasswordMenuItem.setIcon(resourceMap.getIcon("changePasswordMenuItem.icon")); // NOI18N
        changePasswordMenuItem.setText(resourceMap.getString("changePasswordMenuItem.text")); // NOI18N
        changePasswordMenuItem.setName("changePasswordMenuItem"); // NOI18N
        fileMenu.add(changePasswordMenuItem);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setIcon(resourceMap.getIcon("exitMenuItem.icon")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        studentMenu.setMnemonic('s');
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
        attendanceMenu.setMnemonic('A');
        attendanceMenu.setText(resourceMap.getString("attendanceMenu.text")); // NOI18N
        attendanceMenu.setName("attendanceMenu"); // NOI18N
        enterAttendance.setAction(actionMap.get("showRegister")); // NOI18N
        enterAttendance.setIcon(resourceMap.getIcon("enterAttendance.icon")); // NOI18N
        enterAttendance.setText(resourceMap.getString("enterAttendance.text")); // NOI18N
        enterAttendance.setName("enterAttendance"); // NOI18N
        attendanceMenu.add(enterAttendance);
        editAttendance.setAction(actionMap.get("showEditAttendance")); // NOI18N
        editAttendance.setIcon(resourceMap.getIcon("editAttendance.icon")); // NOI18N
        editAttendance.setText(resourceMap.getString("editAttendance.text")); // NOI18N
        editAttendance.setName("editAttendance"); // NOI18N
        attendanceMenu.add(editAttendance);
        menuBar.add(attendanceMenu);
        gradesMenu.setMnemonic('g');
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
        editMidTerms.setAction(actionMap.get("showEditMidTerms")); // NOI18N
        editMidTerms.setIcon(resourceMap.getIcon("editMidTerms.icon")); // NOI18N
        editMidTerms.setText(resourceMap.getString("editMidTerms.text")); // NOI18N
        editMidTerms.setName("editMidTerms"); // NOI18N
        gradesMenu.add(editMidTerms);
        menuBar.add(gradesMenu);
        demeritsMenu.setMnemonic('D');
        demeritsMenu.setText(resourceMap.getString("demeritsMenu.text")); // NOI18N
        demeritsMenu.setName("demeritsMenu"); // NOI18N
        recordDemerits.setAction(actionMap.get("showRecordDemerits")); // NOI18N
        recordDemerits.setIcon(resourceMap.getIcon("recordDemerits.icon")); // NOI18N
        recordDemerits.setText(resourceMap.getString("recordDemerits.text")); // NOI18N
        recordDemerits.setName("recordDemerits"); // NOI18N
        demeritsMenu.add(recordDemerits);
        editDemerits.setAction(actionMap.get("showEditDemerits")); // NOI18N
        editDemerits.setIcon(resourceMap.getIcon("editDemerits.icon")); // NOI18N
        editDemerits.setText(resourceMap.getString("editDemerits.text")); // NOI18N
        editDemerits.setName("editDemerits"); // NOI18N
        demeritsMenu.add(editDemerits);
        menuBar.add(demeritsMenu);
        detentionsMenu.setText(resourceMap.getString("detentionsMenu.text")); // NOI18N
        detentionsMenu.setName("detentionsMenu"); // NOI18N
        recordDetention.setAction(actionMap.get("showRecordDetention")); // NOI18N
        recordDetention.setIcon(resourceMap.getIcon("recordDetention.icon")); // NOI18N
        recordDetention.setText(resourceMap.getString("recordDetention.text")); // NOI18N
        recordDetention.setName("recordDetention"); // NOI18N
        detentionsMenu.add(recordDetention);
        editDetention.setAction(actionMap.get("showEditDetention")); // NOI18N
        editDetention.setIcon(resourceMap.getIcon("editDetention.icon")); // NOI18N
        editDetention.setText(resourceMap.getString("editDetention.text")); // NOI18N
        editDetention.setName("editDetention"); // NOI18N
        detentionsMenu.add(editDetention);
        recordServedDetention.setAction(actionMap.get("showRecordServedDetention")); // NOI18N
        recordServedDetention.setIcon(resourceMap.getIcon("recordServedDetention.icon")); // NOI18N
        recordServedDetention.setText(resourceMap.getString("recordServedDetention.text")); // NOI18N
        recordServedDetention.setName("recordServedDetention"); // NOI18N
        detentionsMenu.add(recordServedDetention);
        menuBar.add(detentionsMenu);
        reportsMenu.setMnemonic('r');
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
        studentsRepeating.setAction(actionMap.get("showRepeatingStudents")); // NOI18N
        studentsRepeating.setIcon(resourceMap.getIcon("studentsRepeating.icon")); // NOI18N
        studentsRepeating.setText(resourceMap.getString("studentsRepeating.text")); // NOI18N
        studentsRepeating.setName("studentsRepeating"); // NOI18N
        studentReports.add(studentsRepeating);
        studentListByClass.setAction(actionMap.get("showStudentListByClass")); // NOI18N
        studentListByClass.setIcon(resourceMap.getIcon("studentListByClass.icon")); // NOI18N
        studentListByClass.setText(resourceMap.getString("studentListByClass.text")); // NOI18N
        studentListByClass.setName("studentListByClass"); // NOI18N
        studentReports.add(studentListByClass);
        studentIDCards.setAction(actionMap.get("showStudentIDCards")); // NOI18N
        studentIDCards.setIcon(resourceMap.getIcon("studentIDCards.icon")); // NOI18N
        studentIDCards.setText(resourceMap.getString("studentIDCards.text")); // NOI18N
        studentIDCards.setName("studentIDCards"); // NOI18N
        studentReports.add(studentIDCards);
        reportsMenu.add(studentReports);
        classReports.setIcon(resourceMap.getIcon("classReports.icon")); // NOI18N
        classReports.setText(resourceMap.getString("classReports.text")); // NOI18N
        classReports.setName("classReports"); // NOI18N
        classListReport.setAction(actionMap.get("showClassListingReport")); // NOI18N
        classListReport.setIcon(resourceMap.getIcon("classListReport.icon")); // NOI18N
        classListReport.setText(resourceMap.getString("classListReport.text")); // NOI18N
        classListReport.setName("classListReport"); // NOI18N
        classReports.add(classListReport);
        classGradeBook.setAction(actionMap.get("showGradebookReport")); // NOI18N
        classGradeBook.setIcon(resourceMap.getIcon("classGradeBook.icon")); // NOI18N
        classGradeBook.setText(resourceMap.getString("classGradeBook.text")); // NOI18N
        classGradeBook.setName("classGradeBook"); // NOI18N
        classReports.add(classGradeBook);
        reportsMenu.add(classReports);
        reportCardsMenu.setIcon(resourceMap.getIcon("reportCardsMenu.icon")); // NOI18N
        reportCardsMenu.setText(resourceMap.getString("reportCardsMenu.text")); // NOI18N
        reportCardsMenu.setName("reportCardsMenu"); // NOI18N
        midTermReports.setAction(actionMap.get("showMidTermReports")); // NOI18N
        midTermReports.setIcon(resourceMap.getIcon("midTermReports.icon")); // NOI18N
        midTermReports.setText(resourceMap.getString("midTermReports.text")); // NOI18N
        midTermReports.setName("midTermReports"); // NOI18N
        reportCardsMenu.add(midTermReports);
        classRankReport.setAction(actionMap.get("showMidTermClassRankReport")); // NOI18N
        classRankReport.setIcon(resourceMap.getIcon("classRankReport.icon")); // NOI18N
        classRankReport.setText(resourceMap.getString("classRankReport.text")); // NOI18N
        classRankReport.setName("classRankReport"); // NOI18N
        reportCardsMenu.add(classRankReport);
        jSeparator1.setName("jSeparator1"); // NOI18N
        reportCardsMenu.add(jSeparator1);
        termEndReports.setIcon(resourceMap.getIcon("termEndReports.icon")); // NOI18N
        termEndReports.setText(resourceMap.getString("termEndReports.text")); // NOI18N
        termEndReports.setName("termEndReports"); // NOI18N
        reportCardsMenu.add(termEndReports);
        termRanking.setAction(actionMap.get("showTermEndRanking")); // NOI18N
        termRanking.setIcon(resourceMap.getIcon("termRanking.icon")); // NOI18N
        termRanking.setText(resourceMap.getString("termRanking.text")); // NOI18N
        termRanking.setName("termRanking"); // NOI18N
        reportCardsMenu.add(termRanking);
        reportsMenu.add(reportCardsMenu);
        demeritReports.setIcon(resourceMap.getIcon("demeritReports.icon")); // NOI18N
        demeritReports.setText(resourceMap.getString("demeritReports.text")); // NOI18N
        demeritReports.setName("demeritReports"); // NOI18N
        demeritsByCass.setAction(actionMap.get("showDemeritsByClass")); // NOI18N
        demeritsByCass.setIcon(resourceMap.getIcon("demeritsByCass.icon")); // NOI18N
        demeritsByCass.setText(resourceMap.getString("demeritsByCass.text")); // NOI18N
        demeritsByCass.setName("demeritsByCass"); // NOI18N
        demeritReports.add(demeritsByCass);
        demeritsByStudent.setAction(actionMap.get("showDemeritByStudent")); // NOI18N
        demeritsByStudent.setIcon(resourceMap.getIcon("demeritsByStudent.icon")); // NOI18N
        demeritsByStudent.setText(resourceMap.getString("demeritsByStudent.text")); // NOI18N
        demeritsByStudent.setName("demeritsByStudent"); // NOI18N
        demeritReports.add(demeritsByStudent);
        reportsMenu.add(demeritReports);
        statisticsReports.setIcon(resourceMap.getIcon("statisticsReports.icon")); // NOI18N
        statisticsReports.setText(resourceMap.getString("statisticsReports.text")); // NOI18N
        statisticsReports.setName("statisticsReports"); // NOI18N
        classSizeDistribution.setAction(actionMap.get("showClassSizeDistriburionReport")); // NOI18N
        classSizeDistribution.setIcon(resourceMap.getIcon("classSizeDistribution.icon")); // NOI18N
        classSizeDistribution.setText(resourceMap.getString("classSizeDistribution.text")); // NOI18N
        classSizeDistribution.setName("classSizeDistribution"); // NOI18N
        statisticsReports.add(classSizeDistribution);
        genderDistribution.setAction(actionMap.get("showGenderDistribution")); // NOI18N
        genderDistribution.setIcon(resourceMap.getIcon("genderDistribution.icon")); // NOI18N
        genderDistribution.setText(resourceMap.getString("genderDistribution.text")); // NOI18N
        genderDistribution.setName("genderDistribution"); // NOI18N
        statisticsReports.add(genderDistribution);
        reportsMenu.add(statisticsReports);
        menuBar.add(reportsMenu);
        manageMenu.setMnemonic('m');
        manageMenu.setText(resourceMap.getString("manageMenu.text")); // NOI18N
        classMenu.setIcon(resourceMap.getIcon("classMenu.icon")); // NOI18N
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
        manageMenu.add(classMenu);
        promoteMenu.setIcon(resourceMap.getIcon("promoteMenu.icon")); // NOI18N
        promoteMenu.setText(resourceMap.getString("promoteMenu.text")); // NOI18N
        promoteMenu.setName("promoteMenu"); // NOI18N
        assignPromotions.setAction(actionMap.get("showPromoteStudents")); // NOI18N
        assignPromotions.setIcon(resourceMap.getIcon("assignPromotions.icon")); // NOI18N
        assignPromotions.setText(resourceMap.getString("assignPromotions.text")); // NOI18N
        assignPromotions.setName("assignPromotions"); // NOI18N
        promoteMenu.add(assignPromotions);
        promoteStudents.setAction(actionMap.get("showPromote")); // NOI18N
        promoteStudents.setIcon(resourceMap.getIcon("promoteStudents.icon")); // NOI18N
        promoteStudents.setText(resourceMap.getString("promoteStudents.text")); // NOI18N
        promoteStudents.setName("promoteStudents"); // NOI18N
        promoteMenu.add(promoteStudents);
        manageMenu.add(promoteMenu);
        manageSchool.setAction(actionMap.get("showManageSchool")); // NOI18N
        manageSchool.setIcon(resourceMap.getIcon("manageSchool.icon")); // NOI18N
        manageSchool.setText(resourceMap.getString("manageSchool.text")); // NOI18N
        manageSchool.setName("manageSchool"); // NOI18N
        manageMenu.add(manageSchool);
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
        utilitiesMenu.setIcon(resourceMap.getIcon("utilitiesMenu.icon")); // NOI18N
        utilitiesMenu.setText(resourceMap.getString("utilitiesMenu.text")); // NOI18N
        utilitiesMenu.setName("utilitiesMenu"); // NOI18N
        midTerm.setIcon(resourceMap.getIcon("midTerm.icon")); // NOI18N
        midTerm.setText(resourceMap.getString("midTerm.text")); // NOI18N
        midTerm.setName("midTerm"); // NOI18N
        calculateMidTerm.setAction(actionMap.get("showCalculateMidTerms")); // NOI18N
        calculateMidTerm.setIcon(resourceMap.getIcon("calculateMidTerm.icon")); // NOI18N
        calculateMidTerm.setText(resourceMap.getString("calculateMidTerm.text")); // NOI18N
        calculateMidTerm.setName("calculateMidTerm"); // NOI18N
        midTerm.add(calculateMidTerm);
        utilitiesMenu.add(midTerm);
        endOfTerm.setIcon(resourceMap.getIcon("endOfTerm.icon")); // NOI18N
        endOfTerm.setText(resourceMap.getString("endOfTerm.text")); // NOI18N
        endOfTerm.setName("endOfTerm"); // NOI18N
        closeTerm.setAction(actionMap.get("showCloseTerm")); // NOI18N
        closeTerm.setIcon(resourceMap.getIcon("closeTerm.icon")); // NOI18N
        closeTerm.setText(resourceMap.getString("closeTerm.text")); // NOI18N
        closeTerm.setName("closeTerm"); // NOI18N
        endOfTerm.add(closeTerm);
        utilitiesMenu.add(endOfTerm);
        manageMenu.add(utilitiesMenu);
        menuBar.add(manageMenu);
        manageMenu.setText("Manage");
        helpMenu.setMnemonic('H');
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
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 616, Short.MAX_VALUE)
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
    public static void checkPrivileges()
    {
        //STUDENT MENU
        studentMenu.setEnabled(UserCheck.canSeeStudent());
        addStudent.setEnabled(UserCheck.canAddStudent());
        editStudent.setEnabled(UserCheck.canEditStudent());
        viewStudent.setEnabled(UserCheck.canViewStudent());
        //ATTENDANCE MENU
        attendanceMenu.setEnabled(UserCheck.canSeeAttendance());
        enterAttendance.setEnabled(UserCheck.canEnterAttendance());
        editAttendance.setEnabled(UserCheck.canEditAttendance());
        //GRADES MENU
        gradesMenu.setEnabled(UserCheck.canSeeGrades());
        createAssessment.setEnabled(UserCheck.canCreateAssessment());
        editAssessment.setEnabled(UserCheck.canEditAssessment());
        editMidTerms.setEnabled(UserCheck.canEditMidTerms());
        //DEMERITS MENU
        demeritsMenu.setEnabled(UserCheck.canSeeDemerits());
        recordDemerits.setEnabled(UserCheck.canRecordDemerits());
        editDemerits.setEnabled(UserCheck.canEditDemerits());
        //REPORTS MENU
        reportsMenu.setEnabled(UserCheck.canSeeReports());
        //STUDENT REPORTS
        studentReports.setEnabled(UserCheck.canSeeStudentReports());
        studentList.setEnabled(UserCheck.canSeeStudentList());
        studentsRepeating.setEnabled(UserCheck.canSeeRepeatingStudents());
        studentListByClass.setEnabled(UserCheck.canSeeStudentsByClass());
        studentIDCards.setEnabled(UserCheck.canSeeStudentIDCards());
        //CLASS REPORTS
        classReports.setEnabled(UserCheck.canSeeClassReports());
        classListReport.setEnabled(UserCheck.canSeeClassListReport());
        classGradeBook.setEnabled(UserCheck.canSeeClassListReport());
        //REPORT CARD REPORTS
        reportCardsMenu.setEnabled(UserCheck.canSeeReportCards());
        midTermReports.setEnabled(UserCheck.canSeeMidTermReports());
        classRankReport.setEnabled(UserCheck.canSeeMidTermClassRanking());
        termEndReports.setEnabled(UserCheck.canSeeTermEndReport());
        termRanking.setEnabled(UserCheck.canSeeTermEndRanking());
        //DEMERITS
        demeritReports.setEnabled(UserCheck.canSeeDemeritReports());
        demeritsByCass.setEnabled(UserCheck.canSeeDemeritsByClass());
        demeritsByStudent.setEnabled(UserCheck.canSeeDemeritsByStudent());
        //STATISTICAL REPORTS
        statisticsReports.setEnabled(UserCheck.canSeeStatisticalReports());
        classSizeDistribution.setEnabled(UserCheck.canSeeClassSizeDistribution());
        genderDistribution.setEnabled(UserCheck.canSeeGenderDistribution());
        //MANAGE MENU
        manageMenu.setEnabled(UserCheck.canManage());
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

    /**
     * Shows the edit time slot window.
     */
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

    /**
     * Shows the Add Time Slot Window
     */
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

    /**
     * Shows the add time slot window.
     */
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

    /**
     * Shows the Edit staff window.
     */
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

    /**
     * Shows the edit class window.
     */
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

    /**
     * Shows the Manage School window.
     */
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

    /**
     * Shows the logout window.
     */
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
            message = "The user successfully logged Off.";
            iLogger.logMessage(message, "Log Off", "User");
            showLoginScreen();
            checkPrivileges();
        }
    }

    /**
     * Shows the view class window.
     */
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

    /**
     * Shows the Edit Student window.
     */
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

    /**
     * Shows the view student window.
     */
    @Action
    public void showViewStudent()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("View Student");
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

    /**
     * shows the edit subject window.
     */
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

    /**
     * Shows the student list report.
     */
    @Action
    public void showStudentListReport()
    {
        DialogStudentByClass dialogStudentByClass = new DialogStudentByClass(getFrame(), false);
        dialogStudentByClass.setLocationRelativeTo(getFrame());
        dialogStudentByClass.setVisible(true);
    }

    /**
     * shows the class list report.
     */
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

    /**
     * Shows the create assessment window.
     */
    @Action
    public void showCreateAssessment()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Record Grades");
        if (AlreadyLoaded == false)
        {
            frmRecordGrade = new FrmRecordGrade();
            desktopPane.add(frmRecordGrade);
            //Load the Form
            frmRecordGrade.setVisible(true);
            frmRecordGrade.show();
            try
            {
                frmRecordGrade.setIcon(false);
                frmRecordGrade.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    /**
     * Shows the edit assessment window.
     */
    @Action
    public void showEditAssessment()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Edit Grades");
        if (AlreadyLoaded == false)
        {
            frmEditGrade = new FrmEditGrades();
            desktopPane.add(frmEditGrade);
            //Load the Form
            frmEditGrade.setVisible(true);
            frmEditGrade.show();
            try
            {
                frmEditGrade.setIcon(false);
                frmEditGrade.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    /**
     * shows the attendance register.
     */
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

    @Action
    public void showGradebookReport()
    {
        FrmClassGradebook frmClassGradebook = new FrmClassGradebook(getFrame(), false);
        frmClassGradebook.setLocationRelativeTo(getFrame());
        frmClassGradebook.setVisible(true);
    }

    @Action
    public void showChangePassword()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Change Password");
        if (AlreadyLoaded == false)
        {
            frmChangePassword = new FrmChangePassword();
            desktopPane.add(frmChangePassword);
            //Load the Form
            frmChangePassword.setVisible(true);
            frmChangePassword.show();
            try
            {
                frmChangePassword.setIcon(false);
                frmChangePassword.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showExit()
    {
        String message = "Are you sure you want to exit?";
        int response = Utilities.showConfirmDialog(getFrame(), message);
        if (response == JOptionPane.YES_OPTION)
        {
            for (JInternalFrame frame : desktopPane.getAllFrames())
            {
                frame.dispose();
            }
            message = "The user successfully logged Off.";
            iLogger.logMessage(message, "Log Off", "User");
            ILearnApp.getApplication().exit();
        }
    }

    @Action
    public void showImportExcellClassRegister()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Import Excel Class Register");
        if (AlreadyLoaded == false)
        {
            frmExcellClassListImporter = new FrmExcellClassListImporter();
            desktopPane.add(frmExcellClassListImporter);
            //Load the Form
            frmExcellClassListImporter.setVisible(true);
            frmExcellClassListImporter.show();
            try
            {
                frmExcellClassListImporter.setIcon(false);
                frmExcellClassListImporter.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showRepeatingStudents()
    {
        ReportLoader.showRepeatingStudents();
    }

    @Action
    public void showEditAttendance()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Edit Class Register");
        if (AlreadyLoaded == false)
        {
            frmEditRegister = new FrmEditRegister();
            desktopPane.add(frmEditRegister);
            //Load the Form
            frmEditRegister.setVisible(true);
            frmEditRegister.show();
            try
            {
                frmEditRegister.setIcon(false);
                frmEditRegister.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showCloseTerm()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Close Term");
        if (AlreadyLoaded == false)
        {
            frmCloseTerm = new FrmCloseTerm();
            desktopPane.add(frmCloseTerm);
            //Load the Form
            frmCloseTerm.setVisible(true);
            frmCloseTerm.show();
            try
            {
                frmCloseTerm.setIcon(false);
                frmCloseTerm.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showPromoteStudents()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Promote By Class");
        if (AlreadyLoaded == false)
        {
            frmAssignPromotions = new FrmAssignPromotions();
            desktopPane.add(frmAssignPromotions);
            //Load the Form
            frmAssignPromotions.setVisible(true);
            frmAssignPromotions.show();
            try
            {
                frmAssignPromotions.setIcon(false);
                frmAssignPromotions.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showPromote()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Promote Students");
        if (AlreadyLoaded == false)
        {
            frmPromote = new FrmPromote();
            desktopPane.add(frmPromote);
            //Load the Form
            frmPromote.setVisible(true);
            frmPromote.show();
            try
            {
                frmPromote.setIcon(false);
                frmPromote.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showCalculateMidTerms()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Generate Mid-Terms");
        if (AlreadyLoaded == false)
        {
            frmCalculateMidTerms = new FrmCalculateMidTerms();
            desktopPane.add(frmCalculateMidTerms);
            //Load the Form
            frmCalculateMidTerms.setVisible(true);
            frmCalculateMidTerms.show();
            try
            {
                frmCalculateMidTerms.setIcon(false);
                frmCalculateMidTerms.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public Task showMidTermReports()
    {
        return new ShowMidTermReportsTask(getApplication());
    }

    private class ShowMidTermReportsTask extends org.jdesktop.application.Task<Object, Void>
    {

        ShowMidTermReportsTask(org.jdesktop.application.Application app)
        {
            super(app);
        }

        @Override
        protected Object doInBackground()
        {
            setMessage("Loading reporting engine.");
            ReportLoader.showMidTermReports();
            return null;  // return your result
        }
    }

    @Action
    public void showEditMidTerms()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Edit Mid-Term Grades");
        if (AlreadyLoaded == false)
        {
            frmEditMidTerms = new FrmEditMidTerms();
            desktopPane.add(frmEditMidTerms);
            //Load the Form
            frmEditMidTerms.setVisible(true);
            frmEditMidTerms.show();
            try
            {
                frmEditMidTerms.setIcon(false);
                frmEditMidTerms.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public Task showMidTermClassRankReport()
    {
        return new ShowMidTermClassRankReportTask(getApplication());
    }

    private class ShowMidTermClassRankReportTask extends org.jdesktop.application.Task<Object, Void>
    {

        ShowMidTermClassRankReportTask(org.jdesktop.application.Application app)
        {
            super(app);
        }

        @Override
        protected Object doInBackground()
        {
            setMessage("Loading reporting engine.");
            ReportLoader.showMidTermClassRankingReport();
            return null;  // return your result
        }
    }

    @Action
    public Task showTermEndRanking()
    {
        return new ShowTermEndRankingTask(getApplication());
    }

    private class ShowTermEndRankingTask extends org.jdesktop.application.Task<Object, Void>
    {

        ShowTermEndRankingTask(org.jdesktop.application.Application app)
        {
            super(app);
        }

        @Override
        protected Object doInBackground()
        {
            setMessage("Loading reporting engine.");
            ReportLoader.showTermEndClassRankingReport();
            return null;  // return your result
        }
    }

    @Action
    public Task showStudentListByClass()
    {
        return new ShowStudentListByClassTask(getApplication());
    }

    private class ShowStudentListByClassTask extends org.jdesktop.application.Task<Object, Void>
    {

        ShowStudentListByClassTask(org.jdesktop.application.Application app)
        {
            super(app);
        }

        @Override
        protected Object doInBackground()
        {
            setMessage("Loading reporting engine.");
            ReportLoader.showStudentsByClass();
            return null;  // return your result
        }
    }

    @Action
    public void showRecordDemerits()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Record Demerit");
        if (AlreadyLoaded == false)
        {
            frmRecordDemerit = new FrmRecordDemerit();
            desktopPane.add(frmRecordDemerit);
            //Load the Form
            frmRecordDemerit.setVisible(true);
            frmRecordDemerit.show();
            try
            {
                frmRecordDemerit.setIcon(false);
                frmRecordDemerit.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showEditDemerits()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Edit Demerit");
        if (AlreadyLoaded == false)
        {
            frmEditDemerit = new FrmEditDemerit();
            desktopPane.add(frmEditDemerit);
            //Load the Form
            frmEditDemerit.setVisible(true);
            frmEditDemerit.show();
            try
            {
                frmEditDemerit.setIcon(false);
                frmEditDemerit.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public Task showClassSizeDistriburionReport()
    {
        return new ShowClassSizeDistriburionReportTask(getApplication());
    }

    private class ShowClassSizeDistriburionReportTask extends org.jdesktop.application.Task<Object, Void>
    {

        ShowClassSizeDistriburionReportTask(org.jdesktop.application.Application app)
        {
            super(app);
        }

        @Override
        protected Object doInBackground()
        {
            setMessage("Loading reporting engine.");
            ReportLoader.showClassSizeDistribution();
            return null;  // return your result
        }
    }

    @Action
    public Task showDemeritsByClass()
    {
        return new ShowDemeritsByClassTask(getApplication());
    }

    private class ShowDemeritsByClassTask extends org.jdesktop.application.Task<Object, Void>
    {

        ShowDemeritsByClassTask(org.jdesktop.application.Application app)
        {
            super(app);
        }

        @Override
        protected Object doInBackground()
        {
            setMessage("Loading reporting engine.");
            ReportLoader.showDemeritsByClass();
            return null;  // return your result
        }
    }

    @Action
    public void showDemeritByStudent()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Student Demerits");
        if (AlreadyLoaded == false)
        {
            frmDemeritsByStudent = new FrmDemeritsByStudent();
            desktopPane.add(frmDemeritsByStudent);
            //Load the Form
            frmDemeritsByStudent.setVisible(true);
            frmDemeritsByStudent.show();
            try
            {
                frmDemeritsByStudent.setIcon(false);
                frmDemeritsByStudent.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public Task showGenderDistribution()
    {
        return new ShowGenderDistributionTask(getApplication());
    }

    private class ShowGenderDistributionTask extends org.jdesktop.application.Task<Object, Void>
    {

        ShowGenderDistributionTask(org.jdesktop.application.Application app)
        {
            super(app);
        }

        @Override
        protected Object doInBackground()
        {
            setMessage("Loading reporting engine.");
            ReportLoader.showGenderBreakdown();
            return null;  // return your result
        }
    }

    @Action
    public void showStudentIDCards()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Print ID Cards");
        if (AlreadyLoaded == false)
        {
            frmPrintIDCards = new FrmPrintIDCards();
            desktopPane.add(frmPrintIDCards);
            //Load the Form
            frmPrintIDCards.setVisible(true);
            frmPrintIDCards.show();
            try
            {
                frmPrintIDCards.setIcon(false);
                frmPrintIDCards.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showRecordDetention()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Record Detention");
        if (AlreadyLoaded == false)
        {
            frmRecordDetention = new FrmRecordDetention();
            desktopPane.add(frmRecordDetention);
            //Load the Form
            frmRecordDetention.setVisible(true);
            frmRecordDetention.show();
            try
            {
                frmRecordDetention.setIcon(false);
                frmRecordDetention.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showEditDetention()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Edit Detention");
        if (AlreadyLoaded == false)
        {
            frmEditDetention = new FrmEditDetention();
            desktopPane.add(frmEditDetention);
            //Load the Form
            frmEditDetention.setVisible(true);
            frmEditDetention.show();
            try
            {
                frmEditDetention.setIcon(false);
                frmEditDetention.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }

    @Action
    public void showRecordServedDetention()
    {
        //Verify if the form is already loaded
        boolean AlreadyLoaded = isLoaded("Record Served Detentions");
        if (AlreadyLoaded == false)
        {
            frmRecordServedDetention = new FrmRecordServedDetention();
            desktopPane.add(frmRecordServedDetention);
            //Load the Form
            frmRecordServedDetention.setVisible(true);
            frmRecordServedDetention.show();
            try
            {
                frmRecordServedDetention.setIcon(false);
                frmRecordServedDetention.setSelected(true);
            }
            catch (Exception e)
            {
                logger.log(Level.SEVERE, "Error displaying the form.", e);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JMenuItem addClass;
    private static javax.swing.JMenuItem addStaff;
    private static javax.swing.JMenuItem addStudent;
    private static javax.swing.JMenuItem addSubject;
    private static javax.swing.JMenuItem addTerm;
    private static javax.swing.JMenuItem addTimeSlot;
    private static javax.swing.JMenuItem addUser;
    private static javax.swing.JMenuItem assignPromotions;
    private static javax.swing.JMenu attendanceMenu;
    private static javax.swing.JMenuItem calculateMidTerm;
    private javax.swing.JMenuItem changePasswordMenuItem;
    private static javax.swing.JMenuItem classGradeBook;
    private static javax.swing.JMenuItem classListReport;
    private static javax.swing.JMenu classMenu;
    private static javax.swing.JMenuItem classRankReport;
    private static javax.swing.JMenu classReports;
    private static javax.swing.JMenuItem classSizeDistribution;
    private static javax.swing.JMenuItem closeTerm;
    private static javax.swing.JMenuItem createAssessment;
    private static javax.swing.JMenu demeritReports;
    private static javax.swing.JMenuItem demeritsByCass;
    private static javax.swing.JMenuItem demeritsByStudent;
    private static javax.swing.JMenu demeritsMenu;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu detentionsMenu;
    private static javax.swing.JMenuItem editAssessment;
    private static javax.swing.JMenuItem editAttendance;
    private static javax.swing.JMenuItem editClass;
    private static javax.swing.JMenuItem editDemerits;
    private javax.swing.JMenuItem editDetention;
    private static javax.swing.JMenuItem editMidTerms;
    private static javax.swing.JMenuItem editStaff;
    private static javax.swing.JMenuItem editStudent;
    private static javax.swing.JMenuItem editSubject;
    private static javax.swing.JMenuItem editTerm;
    private static javax.swing.JMenuItem editTimeSlot;
    private static javax.swing.JMenuItem editUser;
    private static javax.swing.JMenu endOfTerm;
    private static javax.swing.JMenuItem enterAttendance;
    private static javax.swing.JMenuItem genderDistribution;
    private static javax.swing.JMenu gradesMenu;
    private static javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem logOff;
    private javax.swing.JPanel mainPanel;
    private static javax.swing.JMenu manageMenu;
    private static javax.swing.JMenuItem manageSchool;
    private javax.swing.JMenuBar menuBar;
    private static javax.swing.JMenu midTerm;
    private static javax.swing.JMenuItem midTermReports;
    private javax.swing.JProgressBar progressBar;
    private static javax.swing.JMenu promoteMenu;
    private static javax.swing.JMenuItem promoteStudents;
    private static javax.swing.JMenuItem recordDemerits;
    private javax.swing.JMenuItem recordDetention;
    private javax.swing.JMenuItem recordServedDetention;
    private static javax.swing.JMenu reportCardsMenu;
    private static javax.swing.JMenu reportsMenu;
    private static javax.swing.JMenu staffMenu;
    private static javax.swing.JMenu statisticsReports;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private static javax.swing.JMenuItem studentIDCards;
    private static javax.swing.JMenuItem studentList;
    private static javax.swing.JMenuItem studentListByClass;
    private static javax.swing.JMenu studentMenu;
    private static javax.swing.JMenu studentReports;
    private static javax.swing.JMenuItem studentsRepeating;
    private static javax.swing.JMenu subjectMenu;
    private static javax.swing.JMenuItem termEndReports;
    private static javax.swing.JMenu termMenu;
    private static javax.swing.JMenuItem termRanking;
    private static javax.swing.JMenu timeSlotsMenu;
    private static javax.swing.JMenu userMenu;
    private static javax.swing.JMenu utilitiesMenu;
    private static javax.swing.JMenuItem viewClass;
    private static javax.swing.JMenuItem viewStudent;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
}
