/*
 * FrmEditStudent.java
 *
 * Created on Apr 12, 2011, 7:29:19 PM
 */
package ilearn.student;

import ilearn.kernel.Utilities;
import ilearn.school.FrmManageSchool;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmViewStudent extends javax.swing.JInternalFrame
{

    File selectedFile;
    boolean imageChanged = false;

    /** Creates new form FrmEditStudent */
    public FrmViewStudent()
    {
        initComponents();
    }

    @Action
    public void search()
    {
        String searchCriteria = txtSearch.getText().trim();
        tblStudents.setModel(Student.searchStudents(searchCriteria));
        lblResults2.setText(String.valueOf(tblStudents.getRowCount()));
    }

    @Action
    public void next()
    {
        loadStudentInfo();
        studentTabbedPane.setSelectedIndex(studentTabbedPane.getSelectedIndex() + 1);
    }

    @Action
    public void cancel()
    {
        Utilities.showCancelScreen(this);
    }

    private void loadStudentInfo()
    {
        if (tblStudents.getSelectedRow() != -1)
        {
            String stuID = tblStudents.getValueAt(tblStudents.getSelectedRow(), 0).toString();
            ArrayList<Object> studentInfo = Student.getStudentInfo(stuID);
            txtID.setText(studentInfo.get(0).toString());
            txtFirstName.setText(studentInfo.get(1).toString());
            txtLastName.setText(studentInfo.get(2).toString());
            txtOtherName.setText(studentInfo.get(3).toString());
            txtGender.setText(studentInfo.get(5).toString());
            txtEmail.setText(studentInfo.get(8).toString());
            txtPhone.setText(studentInfo.get(9).toString());
            txtHomeAddress.setText(studentInfo.get(11).toString());
            txtMailingAddress.setText(studentInfo.get(12).toString());
            txtPriConName.setText(studentInfo.get(13).toString());
            txtPriConPhone.setText(studentInfo.get(14).toString());
            txtPriConAddress.setText(studentInfo.get(15).toString());
            txtSecConName.setText(studentInfo.get(16).toString());
            txtSecConPhone.setText(studentInfo.get(17).toString());
            txtSecConAddress.setText(studentInfo.get(18).toString());
            txtPrimaryDoctor.setText(studentInfo.get(19).toString());
            txtDoctorPhone.setText(studentInfo.get(20).toString());
            txtHospital.setText(studentInfo.get(21).toString());
            txtClasss.setText(studentInfo.get(22).toString());
            //These were added on June 17th 2011
            txtPSEGrades.setText(studentInfo.get(23).toString());
            txtFeederSchool.setText(studentInfo.get(24).toString());
            boolean stuRepeating = Boolean.valueOf(studentInfo.get(25).toString());
            if (stuRepeating)
            {
                txtRepeating.setText("Yes");
            }
            else
            {
                txtRepeating.setText("No");
            }
            txtSpecialNeeds.setText(studentInfo.get(26).toString());
            txtNotes.setText(studentInfo.get(27).toString());
            txtSSN.setText(studentInfo.get(29).toString());
            try
            {
                txtDOB.setText(Utilities.MDY_Formatter.format(Utilities.YMD_Formatter.parse(studentInfo.get(4).toString())));
            }
            catch (ParseException ex)
            {
                Logger.getLogger(FrmViewStudent.class.getName()).log(Level.SEVERE, null, ex);
            }
            try
            {
                Blob blob = (Blob) studentInfo.get(10);
                ImageIcon ii = new ImageIcon(blob.getBytes(1, (int) blob.length()));
                if (ii.getIconHeight() > 128 || ii.getIconWidth() > 128)
                {
                    Image img = ii.getImage();
                    Image newimg = img.getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH);
                    ii = new ImageIcon(newimg);
                    File image = new File(System.getProperty("java.io.tmpdir") + "/image.jpg");
                    BufferedImage bi = new BufferedImage(ii.getIconWidth(), ii.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2 = bi.createGraphics();
                    g2.drawImage(newimg, 0, 0, null);
                    g2.dispose();
                    try
                    {
                        ImageIO.write(bi, "jpg", image);
                        selectedFile = image;
                    }
                    catch (IOException ex)
                    {
                        Logger.getLogger(FrmManageSchool.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                lblImage.setIcon(ii);
            }
            catch (SQLException sQLException)
            {
            }
        }
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
        txtPhoto = new javax.swing.JTextField();
        studentTabbedPane = new javax.swing.JTabbedPane();
        searchPanel = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblStudents = new javax.swing.JTable();
        cmdCancel5 = new javax.swing.JButton();
        cmdNext4 = new javax.swing.JButton();
        lblResults = new javax.swing.JLabel();
        lblResults2 = new javax.swing.JLabel();
        generalPanel = new javax.swing.JPanel();
        lblFirstName = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        lblLastName = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        lblOtherName = new javax.swing.JLabel();
        txtOtherName = new javax.swing.JTextField();
        lblGender = new javax.swing.JLabel();
        lblDOB = new javax.swing.JLabel();
        cmdCancel1 = new javax.swing.JButton();
        cmdNext1 = new javax.swing.JButton();
        lblClass = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        lblImage = new javax.swing.JLabel();
        txtGender = new javax.swing.JTextField();
        txtDOB = new javax.swing.JTextField();
        txtClasss = new javax.swing.JTextField();
        contactPanel = new javax.swing.JPanel();
        lblHomeAddress = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHomeAddress = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMailingAddress = new javax.swing.JTextArea();
        lblMailingAddress = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblPhone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        cmdCancel2 = new javax.swing.JButton();
        cmdNext2 = new javax.swing.JButton();
        parentPanel = new javax.swing.JPanel();
        primaryContactPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtPriConAddress = new javax.swing.JTextArea();
        lblPriConAddress = new javax.swing.JLabel();
        txtPriConPhone = new javax.swing.JTextField();
        txtPriConName = new javax.swing.JTextField();
        lblPriConPhone = new javax.swing.JLabel();
        lblPriConName = new javax.swing.JLabel();
        secConPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtSecConAddress = new javax.swing.JTextArea();
        lblSecConAddress = new javax.swing.JLabel();
        txtSecConPhone = new javax.swing.JTextField();
        txtSecConName = new javax.swing.JTextField();
        lblSecConPhone = new javax.swing.JLabel();
        lblSecConName = new javax.swing.JLabel();
        cmdCancel3 = new javax.swing.JButton();
        cmdNext3 = new javax.swing.JButton();
        otherPanel = new javax.swing.JPanel();
        cmdSave1 = new javax.swing.JButton();
        cmdCancel6 = new javax.swing.JButton();
        medicalPanel = new javax.swing.JPanel();
        lblPrimaryDoctor = new javax.swing.JLabel();
        txtDoctorPhone = new javax.swing.JTextField();
        txtPrimaryDoctor = new javax.swing.JTextField();
        lblHospital = new javax.swing.JLabel();
        lblDoctorPhone = new javax.swing.JLabel();
        txtHospital = new javax.swing.JTextField();
        lblFeederSchool = new javax.swing.JLabel();
        lblPSEGrade = new javax.swing.JLabel();
        lblRepeating = new javax.swing.JLabel();
        lblSpecialNeeds = new javax.swing.JLabel();
        lblNotes = new javax.swing.JLabel();
        txtFeederSchool = new javax.swing.JTextField();
        txtPSEGrades = new javax.swing.JTextField();
        txtSpecialNeeds = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();
        lblSSN = new javax.swing.JLabel();
        txtSSN = new javax.swing.JFormattedTextField();
        txtRepeating = new javax.swing.JTextField();
        txtPhoto.setEditable(false);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(FrmViewStudent.class);
        txtPhoto.setToolTipText(resourceMap.getString("txtPhoto.toolTipText")); // NOI18N
        txtPhoto.setName("txtPhoto"); // NOI18N
        setClosable(true);
        setIconifiable(true);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setToolTipText(resourceMap.getString("Form.toolTipText")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        studentTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        studentTabbedPane.setName("studentTabbedPane"); // NOI18N
        searchPanel.setName("searchPanel"); // NOI18N
        lblSearch.setText(resourceMap.getString("lblSearch.text")); // NOI18N
        lblSearch.setName("lblSearch"); // NOI18N
        txtSearch.setText(resourceMap.getString("txtSearch.text")); // NOI18N
        txtSearch.setName("txtSearch"); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtSearchKeyPressed(evt);
            }
        });
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(FrmViewStudent.class, this);
        cmdSearch.setAction(actionMap.get("search")); // NOI18N
        cmdSearch.setText(resourceMap.getString("cmdSearch.text")); // NOI18N
        cmdSearch.setName("cmdSearch"); // NOI18N
        jScrollPane5.setName("jScrollPane5"); // NOI18N
        tblStudents.setAutoCreateRowSorter(true);
        tblStudents.setModel(new javax.swing.table.DefaultTableModel(
                                 new Object [][]
                                 {

                                 },
                                 new String []
                                 {
                                     "ID", "First Name", "Last Name", "Class"
                                 }
                             ));
        tblStudents.setName("tblStudents"); // NOI18N
        tblStudents.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tblStudentsMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblStudents);
        tblStudents.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblStudents.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblStudents.columnModel.title0")); // NOI18N
        tblStudents.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblStudents.columnModel.title1")); // NOI18N
        tblStudents.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblStudents.columnModel.title2")); // NOI18N
        tblStudents.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblStudents.columnModel.title3")); // NOI18N
        cmdCancel5.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel5.setName("cmdCancel5"); // NOI18N
        cmdNext4.setAction(actionMap.get("next")); // NOI18N
        cmdNext4.setName("cmdNext4"); // NOI18N
        lblResults.setText(resourceMap.getString("lblResults.text")); // NOI18N
        lblResults.setName("lblResults"); // NOI18N
        lblResults2.setText(resourceMap.getString("lblResults2.text")); // NOI18N
        lblResults2.setName("lblResults2"); // NOI18N
        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                                .addGroup(searchPanelLayout.createSequentialGroup()
                                          .addComponent(lblSearch)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdSearch))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                                          .addComponent(lblResults)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(lblResults2)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                                          .addComponent(cmdNext4)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel5)))
                      .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSearch)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmdSearch))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                      .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel5)
                                .addComponent(cmdNext4)
                                .addComponent(lblResults)
                                .addComponent(lblResults2))
                      .addContainerGap())
        );
        studentTabbedPane.addTab(resourceMap.getString("searchPanel.TabConstraints.tabTitle"), resourceMap.getIcon("searchPanel.TabConstraints.tabIcon"), searchPanel); // NOI18N
        generalPanel.setName("generalPanel"); // NOI18N
        lblFirstName.setText(resourceMap.getString("lblFirstName.text")); // NOI18N
        lblFirstName.setName("lblFirstName"); // NOI18N
        txtFirstName.setEditable(false);
        txtFirstName.setToolTipText(resourceMap.getString("txtFirstName.toolTipText")); // NOI18N
        txtFirstName.setName("txtFirstName"); // NOI18N
        lblLastName.setText(resourceMap.getString("lblLastName.text")); // NOI18N
        lblLastName.setName("lblLastName"); // NOI18N
        txtLastName.setEditable(false);
        txtLastName.setToolTipText(resourceMap.getString("txtLastName.toolTipText")); // NOI18N
        txtLastName.setName("txtLastName"); // NOI18N
        lblOtherName.setText(resourceMap.getString("lblOtherName.text")); // NOI18N
        lblOtherName.setName("lblOtherName"); // NOI18N
        txtOtherName.setEditable(false);
        txtOtherName.setToolTipText(resourceMap.getString("txtOtherName.toolTipText")); // NOI18N
        txtOtherName.setName("txtOtherName"); // NOI18N
        lblGender.setText(resourceMap.getString("lblGender.text")); // NOI18N
        lblGender.setName("lblGender"); // NOI18N
        lblDOB.setText(resourceMap.getString("lblDOB.text")); // NOI18N
        lblDOB.setToolTipText(resourceMap.getString("lblDOB.toolTipText")); // NOI18N
        lblDOB.setName("lblDOB"); // NOI18N
        cmdCancel1.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel1.setName("cmdCancel1"); // NOI18N
        cmdNext1.setAction(actionMap.get("next")); // NOI18N
        cmdNext1.setName("cmdNext1"); // NOI18N
        lblClass.setText(resourceMap.getString("lblClass.text")); // NOI18N
        lblClass.setName("lblClass"); // NOI18N
        lblID.setText(resourceMap.getString("lblID.text")); // NOI18N
        lblID.setName("lblID"); // NOI18N
        txtID.setEditable(false);
        txtID.setText(resourceMap.getString("txtID.text")); // NOI18N
        txtID.setName("txtID"); // NOI18N
        jScrollPane6.setMaximumSize(new java.awt.Dimension(128, 128));
        jScrollPane6.setMinimumSize(new java.awt.Dimension(128, 128));
        jScrollPane6.setName("jScrollPane6"); // NOI18N
        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setIcon(resourceMap.getIcon("lblImage.icon")); // NOI18N
        lblImage.setText(resourceMap.getString("lblImage.text")); // NOI18N
        lblImage.setName("lblImage"); // NOI18N
        jScrollPane6.setViewportView(lblImage);
        txtGender.setEditable(false);
        txtGender.setText(resourceMap.getString("txtGender.text")); // NOI18N
        txtGender.setName("txtGender"); // NOI18N
        txtDOB.setEditable(false);
        txtDOB.setText(resourceMap.getString("txtDOB.text")); // NOI18N
        txtDOB.setName("txtDOB"); // NOI18N
        txtClasss.setEditable(false);
        txtClasss.setText(resourceMap.getString("txtClasss.text")); // NOI18N
        txtClasss.setName("txtClasss"); // NOI18N
        javax.swing.GroupLayout generalPanelLayout = new javax.swing.GroupLayout(generalPanel);
        generalPanel.setLayout(generalPanelLayout);
        generalPanelLayout.setHorizontalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generalPanelLayout.createSequentialGroup()
                                          .addComponent(cmdNext1)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel1))
                                .addComponent(lblID)
                                .addGroup(generalPanelLayout.createSequentialGroup()
                                          .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(lblFirstName)
                                                  .addComponent(lblLastName)
                                                  .addComponent(lblGender)
                                                  .addComponent(lblDOB)
                                                  .addComponent(lblOtherName)
                                                  .addComponent(lblClass))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addGroup(generalPanelLayout.createSequentialGroup()
                                                          .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                  .addComponent(txtID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                                                  .addComponent(txtFirstName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                                                  .addComponent(txtLastName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                                                  .addComponent(txtOtherName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                                                  .addComponent(txtGender, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                                                  .addComponent(txtDOB, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                          .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                  .addComponent(txtClasss, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))))
                      .addContainerGap())
        );
        generalPanelLayout.setVerticalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generalPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(generalPanelLayout.createSequentialGroup()
                                          .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                  .addComponent(lblID)
                                                  .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                  .addComponent(lblFirstName)
                                                  .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                  .addComponent(lblLastName)
                                                  .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                  .addComponent(lblOtherName, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addComponent(txtOtherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                  .addComponent(lblGender)
                                                  .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                  .addComponent(lblDOB)
                                                  .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblClass)
                                .addComponent(txtClasss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addGap(151, 151, 151)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel1)
                                .addComponent(cmdNext1))
                      .addContainerGap())
        );
        studentTabbedPane.addTab(resourceMap.getString("generalPanel.TabConstraints.tabTitle"), resourceMap.getIcon("generalPanel.TabConstraints.tabIcon"), generalPanel); // NOI18N
        contactPanel.setName("contactPanel"); // NOI18N
        lblHomeAddress.setText(resourceMap.getString("lblHomeAddress.text")); // NOI18N
        lblHomeAddress.setName("lblHomeAddress"); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        txtHomeAddress.setColumns(20);
        txtHomeAddress.setEditable(false);
        txtHomeAddress.setLineWrap(true);
        txtHomeAddress.setRows(5);
        txtHomeAddress.setToolTipText(resourceMap.getString("txtHomeAddress.toolTipText")); // NOI18N
        txtHomeAddress.setWrapStyleWord(true);
        txtHomeAddress.setName("txtHomeAddress"); // NOI18N
        jScrollPane1.setViewportView(txtHomeAddress);
        jScrollPane2.setName("jScrollPane2"); // NOI18N
        txtMailingAddress.setColumns(20);
        txtMailingAddress.setEditable(false);
        txtMailingAddress.setLineWrap(true);
        txtMailingAddress.setRows(5);
        txtMailingAddress.setToolTipText(resourceMap.getString("txtMailingAddress.toolTipText")); // NOI18N
        txtMailingAddress.setWrapStyleWord(true);
        txtMailingAddress.setName("txtMailingAddress"); // NOI18N
        jScrollPane2.setViewportView(txtMailingAddress);
        lblMailingAddress.setText(resourceMap.getString("lblMailingAddress.text")); // NOI18N
        lblMailingAddress.setName("lblMailingAddress"); // NOI18N
        lblEmail.setText(resourceMap.getString("lblEmail.text")); // NOI18N
        lblEmail.setName("lblEmail"); // NOI18N
        txtEmail.setEditable(false);
        txtEmail.setToolTipText(resourceMap.getString("txtEmail.toolTipText")); // NOI18N
        txtEmail.setName("txtEmail"); // NOI18N
        lblPhone.setText(resourceMap.getString("lblPhone.text")); // NOI18N
        lblPhone.setName("lblPhone"); // NOI18N
        txtPhone.setEditable(false);
        txtPhone.setToolTipText(resourceMap.getString("txtPhone.toolTipText")); // NOI18N
        txtPhone.setName("txtPhone"); // NOI18N
        cmdCancel2.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel2.setName("cmdCancel2"); // NOI18N
        cmdNext2.setAction(actionMap.get("next")); // NOI18N
        cmdNext2.setName("cmdNext2"); // NOI18N
        javax.swing.GroupLayout contactPanelLayout = new javax.swing.GroupLayout(contactPanel);
        contactPanel.setLayout(contactPanelLayout);
        contactPanelLayout.setHorizontalGroup(
            contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(contactPanelLayout.createSequentialGroup()
                                          .addGroup(contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(lblEmail)
                                                  .addComponent(lblPhone)
                                                  .addComponent(lblHomeAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                  .addComponent(lblMailingAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                          .addGap(22, 22, 22)
                                          .addGroup(contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                  .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                                  .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                                  .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                                  .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contactPanelLayout.createSequentialGroup()
                                          .addComponent(cmdNext2)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel2)))
                      .addContainerGap())
        );
        contactPanelLayout.setVerticalGroup(
            contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblEmail)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPhone)
                                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblHomeAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblMailingAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                      .addGroup(contactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel2)
                                .addComponent(cmdNext2))
                      .addContainerGap())
        );
        studentTabbedPane.addTab(resourceMap.getString("contactPanel.TabConstraints.tabTitle"), resourceMap.getIcon("contactPanel.TabConstraints.tabIcon"), contactPanel); // NOI18N
        parentPanel.setName("parentPanel"); // NOI18N
        primaryContactPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("primaryContactPanel.border.title"))); // NOI18N
        primaryContactPanel.setName("primaryContactPanel"); // NOI18N
        jScrollPane3.setName("jScrollPane3"); // NOI18N
        txtPriConAddress.setColumns(15);
        txtPriConAddress.setEditable(false);
        txtPriConAddress.setLineWrap(true);
        txtPriConAddress.setRows(3);
        txtPriConAddress.setToolTipText(resourceMap.getString("txtPriConAddress.toolTipText")); // NOI18N
        txtPriConAddress.setWrapStyleWord(true);
        txtPriConAddress.setName("txtPriConAddress"); // NOI18N
        jScrollPane3.setViewportView(txtPriConAddress);
        lblPriConAddress.setText(resourceMap.getString("lblPriConAddress.text")); // NOI18N
        lblPriConAddress.setName("lblPriConAddress"); // NOI18N
        txtPriConPhone.setEditable(false);
        txtPriConPhone.setToolTipText(resourceMap.getString("txtPriConPhone.toolTipText")); // NOI18N
        txtPriConPhone.setName("txtPriConPhone"); // NOI18N
        txtPriConName.setEditable(false);
        txtPriConName.setToolTipText(resourceMap.getString("txtPriConName.toolTipText")); // NOI18N
        txtPriConName.setName("txtPriConName"); // NOI18N
        lblPriConPhone.setText(resourceMap.getString("lblPriConPhone.text")); // NOI18N
        lblPriConPhone.setName("lblPriConPhone"); // NOI18N
        lblPriConName.setText(resourceMap.getString("lblPriConName.text")); // NOI18N
        lblPriConName.setName("lblPriConName"); // NOI18N
        javax.swing.GroupLayout primaryContactPanelLayout = new javax.swing.GroupLayout(primaryContactPanel);
        primaryContactPanel.setLayout(primaryContactPanelLayout);
        primaryContactPanelLayout.setHorizontalGroup(
            primaryContactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(primaryContactPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(primaryContactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblPriConName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPriConPhone)
                                .addComponent(lblPriConAddress))
                      .addGap(13, 13, 13)
                      .addGroup(primaryContactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                                .addComponent(txtPriConPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                                .addComponent(txtPriConName, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
                      .addContainerGap())
        );
        primaryContactPanelLayout.setVerticalGroup(
            primaryContactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(primaryContactPanelLayout.createSequentialGroup()
                      .addGroup(primaryContactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPriConName)
                                .addComponent(txtPriConName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(primaryContactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPriConPhone)
                                .addComponent(txtPriConPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(primaryContactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblPriConAddress)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        secConPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("secConPanel.border.title"))); // NOI18N
        secConPanel.setName("secConPanel"); // NOI18N
        jScrollPane4.setName("jScrollPane4"); // NOI18N
        txtSecConAddress.setColumns(15);
        txtSecConAddress.setEditable(false);
        txtSecConAddress.setLineWrap(true);
        txtSecConAddress.setRows(3);
        txtSecConAddress.setToolTipText(resourceMap.getString("txtSecConAddress.toolTipText")); // NOI18N
        txtSecConAddress.setWrapStyleWord(true);
        txtSecConAddress.setName("txtSecConAddress"); // NOI18N
        jScrollPane4.setViewportView(txtSecConAddress);
        lblSecConAddress.setText(resourceMap.getString("lblSecConAddress.text")); // NOI18N
        lblSecConAddress.setName("lblSecConAddress"); // NOI18N
        txtSecConPhone.setEditable(false);
        txtSecConPhone.setToolTipText(resourceMap.getString("txtSecConPhone.toolTipText")); // NOI18N
        txtSecConPhone.setName("txtSecConPhone"); // NOI18N
        txtSecConName.setEditable(false);
        txtSecConName.setToolTipText(resourceMap.getString("txtSecConName.toolTipText")); // NOI18N
        txtSecConName.setName("txtSecConName"); // NOI18N
        lblSecConPhone.setText(resourceMap.getString("lblSecConPhone.text")); // NOI18N
        lblSecConPhone.setName("lblSecConPhone"); // NOI18N
        lblSecConName.setText(resourceMap.getString("lblSecConName.text")); // NOI18N
        lblSecConName.setName("lblSecConName"); // NOI18N
        javax.swing.GroupLayout secConPanelLayout = new javax.swing.GroupLayout(secConPanel);
        secConPanel.setLayout(secConPanelLayout);
        secConPanelLayout.setHorizontalGroup(
            secConPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(secConPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(secConPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblSecConName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSecConPhone)
                                .addComponent(lblSecConAddress))
                      .addGap(13, 13, 13)
                      .addGroup(secConPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                                .addComponent(txtSecConPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                                .addComponent(txtSecConName, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
                      .addContainerGap())
        );
        secConPanelLayout.setVerticalGroup(
            secConPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(secConPanelLayout.createSequentialGroup()
                      .addGroup(secConPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSecConName)
                                .addComponent(txtSecConName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(secConPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSecConPhone)
                                .addComponent(txtSecConPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(secConPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblSecConAddress)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cmdCancel3.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel3.setName("cmdCancel3"); // NOI18N
        cmdNext3.setAction(actionMap.get("next")); // NOI18N
        cmdNext3.setName("cmdNext3"); // NOI18N
        javax.swing.GroupLayout parentPanelLayout = new javax.swing.GroupLayout(parentPanel);
        parentPanel.setLayout(parentPanelLayout);
        parentPanelLayout.setHorizontalGroup(
            parentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parentPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(parentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, parentPanelLayout.createSequentialGroup()
                                          .addComponent(cmdNext3)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel3))
                                .addComponent(secConPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(primaryContactPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                      .addContainerGap())
        );
        parentPanelLayout.setVerticalGroup(
            parentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parentPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(primaryContactPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(secConPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                      .addGroup(parentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel3)
                                .addComponent(cmdNext3))
                      .addContainerGap())
        );
        studentTabbedPane.addTab(resourceMap.getString("parentPanel.TabConstraints.tabTitle"), resourceMap.getIcon("parentPanel.TabConstraints.tabIcon"), parentPanel); // NOI18N
        otherPanel.setName("otherPanel"); // NOI18N
        cmdSave1.setAction(actionMap.get("save")); // NOI18N
        cmdSave1.setName("cmdSave1"); // NOI18N
        cmdCancel6.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel6.setName("cmdCancel6"); // NOI18N
        medicalPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), resourceMap.getString("medicalPanel.border.title"))); // NOI18N
        medicalPanel.setName("medicalPanel"); // NOI18N
        lblPrimaryDoctor.setText(resourceMap.getString("lblPrimaryDoctor.text")); // NOI18N
        lblPrimaryDoctor.setName("lblPrimaryDoctor"); // NOI18N
        txtDoctorPhone.setEditable(false);
        txtDoctorPhone.setToolTipText(resourceMap.getString("txtDoctorPhone.toolTipText")); // NOI18N
        txtDoctorPhone.setName("txtDoctorPhone"); // NOI18N
        txtPrimaryDoctor.setEditable(false);
        txtPrimaryDoctor.setToolTipText(resourceMap.getString("txtPrimaryDoctor.toolTipText")); // NOI18N
        txtPrimaryDoctor.setName("txtPrimaryDoctor"); // NOI18N
        lblHospital.setText(resourceMap.getString("lblHospital.text")); // NOI18N
        lblHospital.setName("lblHospital"); // NOI18N
        lblDoctorPhone.setText(resourceMap.getString("lblDoctorPhone.text")); // NOI18N
        lblDoctorPhone.setName("lblDoctorPhone"); // NOI18N
        txtHospital.setEditable(false);
        txtHospital.setToolTipText(resourceMap.getString("txtHospital.toolTipText")); // NOI18N
        txtHospital.setName("txtHospital"); // NOI18N
        javax.swing.GroupLayout medicalPanelLayout = new javax.swing.GroupLayout(medicalPanel);
        medicalPanel.setLayout(medicalPanelLayout);
        medicalPanelLayout.setHorizontalGroup(
            medicalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicalPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(medicalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblPrimaryDoctor)
                                .addComponent(lblDoctorPhone)
                                .addComponent(lblHospital))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(medicalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDoctorPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                .addComponent(txtPrimaryDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                .addComponent(txtHospital, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                      .addContainerGap())
        );
        medicalPanelLayout.setVerticalGroup(
            medicalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicalPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(medicalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPrimaryDoctor)
                                .addComponent(txtPrimaryDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(medicalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblDoctorPhone)
                                .addComponent(txtDoctorPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(medicalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtHospital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblHospital))
                      .addContainerGap())
        );
        lblFeederSchool.setText(resourceMap.getString("lblFeederSchool.text")); // NOI18N
        lblFeederSchool.setName("lblFeederSchool"); // NOI18N
        lblPSEGrade.setText(resourceMap.getString("lblPSEGrade.text")); // NOI18N
        lblPSEGrade.setName("lblPSEGrade"); // NOI18N
        lblRepeating.setText(resourceMap.getString("lblRepeating.text")); // NOI18N
        lblRepeating.setName("lblRepeating"); // NOI18N
        lblSpecialNeeds.setText(resourceMap.getString("lblSpecialNeeds.text")); // NOI18N
        lblSpecialNeeds.setName("lblSpecialNeeds"); // NOI18N
        lblNotes.setText(resourceMap.getString("lblNotes.text")); // NOI18N
        lblNotes.setName("lblNotes"); // NOI18N
        txtFeederSchool.setEditable(false);
        txtFeederSchool.setName("txtFeederSchool"); // NOI18N
        txtPSEGrades.setEditable(false);
        txtPSEGrades.setName("txtPSEGrades"); // NOI18N
        txtSpecialNeeds.setEditable(false);
        txtSpecialNeeds.setName("txtSpecialNeeds"); // NOI18N
        jScrollPane7.setName("jScrollPane7"); // NOI18N
        txtNotes.setColumns(20);
        txtNotes.setEditable(false);
        txtNotes.setRows(4);
        txtNotes.setName("txtNotes"); // NOI18N
        jScrollPane7.setViewportView(txtNotes);
        lblSSN.setText(resourceMap.getString("lblSSN.text")); // NOI18N
        lblSSN.setName("lblSSN"); // NOI18N
        txtSSN.setEditable(false);
        txtSSN.setText(resourceMap.getString("txtSSN.text")); // NOI18N
        txtSSN.setName("txtSSN"); // NOI18N
        txtRepeating.setEditable(false);
        txtRepeating.setText(resourceMap.getString("txtRepeating.text")); // NOI18N
        txtRepeating.setName("txtRepeating"); // NOI18N
        javax.swing.GroupLayout otherPanelLayout = new javax.swing.GroupLayout(otherPanel);
        otherPanel.setLayout(otherPanelLayout);
        otherPanelLayout.setHorizontalGroup(
            otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(medicalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, otherPanelLayout.createSequentialGroup()
                                          .addComponent(cmdSave1)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel6))
                                .addGroup(otherPanelLayout.createSequentialGroup()
                                          .addGroup(otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(lblSpecialNeeds)
                                                  .addComponent(lblFeederSchool)
                                                  .addComponent(lblPSEGrade)
                                                  .addComponent(lblRepeating)
                                                  .addComponent(lblNotes)
                                                  .addComponent(lblSSN))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(txtSSN, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                                                  .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                                                  .addComponent(txtPSEGrades, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                                                  .addComponent(txtFeederSchool, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                                                  .addComponent(txtSpecialNeeds, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                                                  .addComponent(txtRepeating, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))))
                      .addContainerGap())
        );
        otherPanelLayout.setVerticalGroup(
            otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otherPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSSN)
                                .addComponent(txtSSN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblFeederSchool)
                                .addComponent(txtFeederSchool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPSEGrade)
                                .addComponent(txtPSEGrades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblRepeating)
                                .addComponent(txtRepeating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSpecialNeeds)
                                .addComponent(txtSpecialNeeds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblNotes)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addGap(9, 9, 9)
                      .addComponent(medicalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                      .addGroup(otherPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel6)
                                .addComponent(cmdSave1))
                      .addContainerGap())
        );
        studentTabbedPane.addTab("Other", resourceMap.getIcon("otherPanel.TabConstraints.tabIcon"), otherPanel); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(studentTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                      .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(studentTabbedPane)
                      .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtSearchKeyPressed
    {
//GEN-HEADEREND:event_txtSearchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            search();
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void tblStudentsMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblStudentsMouseClicked
    {
//GEN-HEADEREND:event_tblStudentsMouseClicked
        if (evt.getClickCount() >= 2)
        {
            next();
        }
    }//GEN-LAST:event_tblStudentsMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCancel1;
    private javax.swing.JButton cmdCancel2;
    private javax.swing.JButton cmdCancel3;
    private javax.swing.JButton cmdCancel5;
    private javax.swing.JButton cmdCancel6;
    private javax.swing.JButton cmdNext1;
    private javax.swing.JButton cmdNext2;
    private javax.swing.JButton cmdNext3;
    private javax.swing.JButton cmdNext4;
    private javax.swing.JButton cmdSave1;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JPanel contactPanel;
    private javax.swing.JPanel generalPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblClass;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblDoctorPhone;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFeederSchool;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblHomeAddress;
    private javax.swing.JLabel lblHospital;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblMailingAddress;
    private javax.swing.JLabel lblNotes;
    private javax.swing.JLabel lblOtherName;
    private javax.swing.JLabel lblPSEGrade;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblPriConAddress;
    private javax.swing.JLabel lblPriConName;
    private javax.swing.JLabel lblPriConPhone;
    private javax.swing.JLabel lblPrimaryDoctor;
    private javax.swing.JLabel lblRepeating;
    private javax.swing.JLabel lblResults;
    private javax.swing.JLabel lblResults2;
    private javax.swing.JLabel lblSSN;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSecConAddress;
    private javax.swing.JLabel lblSecConName;
    private javax.swing.JLabel lblSecConPhone;
    private javax.swing.JLabel lblSpecialNeeds;
    private javax.swing.JPanel medicalPanel;
    private javax.swing.JPanel otherPanel;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JPanel primaryContactPanel;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JPanel secConPanel;
    private javax.swing.JTabbedPane studentTabbedPane;
    private javax.swing.JTable tblStudents;
    private javax.swing.JTextField txtClasss;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtDoctorPhone;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFeederSchool;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextArea txtHomeAddress;
    private javax.swing.JTextField txtHospital;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextArea txtMailingAddress;
    private javax.swing.JTextArea txtNotes;
    private javax.swing.JTextField txtOtherName;
    private javax.swing.JTextField txtPSEGrades;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPhoto;
    private javax.swing.JTextArea txtPriConAddress;
    private javax.swing.JTextField txtPriConName;
    private javax.swing.JTextField txtPriConPhone;
    private javax.swing.JTextField txtPrimaryDoctor;
    private javax.swing.JTextField txtRepeating;
    private javax.swing.JFormattedTextField txtSSN;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextArea txtSecConAddress;
    private javax.swing.JTextField txtSecConName;
    private javax.swing.JTextField txtSecConPhone;
    private javax.swing.JTextField txtSpecialNeeds;
    // End of variables declaration//GEN-END:variables
}
