/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmEditStudent.java
 *
 * Created on Apr 12, 2011, 7:29:19 PM
 */
package ilearn.student;

import ilearn.classes.Classes;
import ilearn.kernel.ImageFilter;
import ilearn.kernel.Utilities;
import ilearn.school.FrmManageSchool;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmEditStudent extends javax.swing.JInternalFrame
{

    File selectedFile;
    boolean imageChanged = false;
    boolean infoAlreadyLoaded = false;

    /** Creates new form FrmEditStudent */
    public FrmEditStudent()
    {
        initComponents();
        populateLists();
    }

    private void populateLists()
    {
        ArrayList<String> classList = Classes.getClassList();
        classList.add(0, "New Student");
        cmbClass.setModel(new DefaultComboBoxModel(classList.toArray()));
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
        if (!infoAlreadyLoaded)
        {
            loadStudentInfo();
            infoAlreadyLoaded = true;
        }
        studentTabbedPane.setSelectedIndex(studentTabbedPane.getSelectedIndex() + 1);
    }

    @Action
    public void cancel()
    {
        Utilities.showCancelScreen(this);
    }

    @Action
    public void browse()
    {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setFileFilter(new ImageFilter());
        int returnVal = fc.showOpenDialog(rootPane);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = fc.getSelectedFile();
            imageChanged = true;
            ImageIcon ii = new ImageIcon(selectedFile.getAbsolutePath());
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
    }

    @Action
    public void save()
    {
        String stuID = txtID.getText().toString();
        String stuFirstName = txtFirstName.getText().trim();
        String stuLastName = txtLastName.getText().trim();
        String stuOtherNames = txtOtherName.getText().trim();
        String stuDOB = "";
        String stuGender = cmbGender.getSelectedItem().toString();
        String stuEmail = txtEmail.getText().trim();
        String stuPhone = txtPhone.getText().trim();
        String stuAddress1 = txtHomeAddress.getText().trim();
        String stuAddress2 = txtMailingAddress.getText().trim();
        String stuPCName = txtPriConName.getText().trim();
        String stuPCPhone = txtPriConPhone.getText().trim();
        String stuSCName = txtSecConName.getText().trim();
        String stuPCAddress = txtSecConAddress.getText().trim();
        String stuSCPhone = txtSecConPhone.getText().trim();
        String stuSCAddress = txtSecConAddress.getText().trim();
        String stuDoctorName = txtPrimaryDoctor.getText().trim();
        String stuDoctorContact = txtDoctorPhone.getText().trim();
        String stuHospital = txtHospital.getText().trim();
        String stuClsCode = cmbClass.getSelectedItem().toString();
        try
        {
            stuDOB = Utilities.YMD_Formatter.format(calDOB.getDate());
            if (selectedFile == null && imageChanged)
            {
                URL sampleImage = FrmNewStudent.class.getResource("/ilearn/resources/no-image-selected.png");
                selectedFile = new File(sampleImage.toURI());
            }
        }
        catch (Exception e)
        {
            String message = "An error occurred while validating your input.\n"
                             + "Kindly verify your information and try again.";
            Utilities.showErrorMessage(rootPane, message);
            return;
        }
        if (imageChanged) // If the image was changed the run the command that takes an image file.
        {
            if (Student.updateStudent(stuID, stuFirstName, stuLastName, stuOtherNames, stuDOB, stuGender, stuEmail, stuPhone, selectedFile, stuAddress1, stuAddress2, stuPCName, stuPCPhone, stuSCName, stuPCAddress, stuSCPhone, stuSCAddress, stuDoctorName, stuDoctorContact, stuHospital, stuClsCode))
            {
                String message = "The student's information  was successfully updated. \n"
                                 + "Would you like to modify another another?";
                int response = Utilities.showConfirmDialog(rootPane, message);
                if (response == JOptionPane.YES_OPTION)
                {
                    resetForm();
                }
                else
                {
                    this.dispose();
                }
            }
            else
            {
                String message = "An error occurred while trying to add this student.\n"
                                 + "Kindly verify your information and try again.";
                Utilities.showErrorMessage(rootPane, message);
            }
        }
        else // If the image wasn't changed just update text values.
        {
            if (Student.updateStudent(stuID, stuFirstName, stuLastName, stuOtherNames, stuDOB, stuGender, stuEmail, stuPhone, stuAddress1, stuAddress2, stuPCName, stuPCPhone, stuSCName, stuPCAddress, stuSCPhone, stuSCAddress, stuDoctorName, stuDoctorContact, stuHospital, stuClsCode))
            {
                String message = "The student's information  was successfully updated. \n"
                                 + "Would you like to modify another another?";
                int response = Utilities.showConfirmDialog(rootPane, message);
                if (response == JOptionPane.YES_OPTION)
                {
                    resetForm();
                }
                else
                {
                    this.dispose();
                }
            }
            else
            {
                String message = "An error occurred while trying to add this student.\n"
                                 + "Kindly verify your information and try again.";
                Utilities.showErrorMessage(rootPane, message);
            }
        }
    }

    @Action
    public void resetForm()
    {
        this.remove(studentTabbedPane);
        initComponents();
        populateLists();
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
            try
            {
                calDOB.setDate(Utilities.YMD_Formatter.parse(studentInfo.get(4).toString()));
            }
            catch (ParseException ex)
            {
                Logger.getLogger(FrmEditStudent.class.getName()).log(Level.SEVERE, null, ex);
            }
            cmbGender.setSelectedItem(studentInfo.get(5).toString());
            txtEmail.setText(studentInfo.get(8).toString());
            txtPhone.setText(studentInfo.get(9).toString());
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
            cmbClass.setSelectedItem(studentInfo.get(22).toString());
            //Set the flag so that the next action won't reset the form.
            infoAlreadyLoaded = true;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        cmbGender = new javax.swing.JComboBox();
        lblDOB = new javax.swing.JLabel();
        calDOB = new com.toedter.calendar.JDateChooser();
        lblPhoto = new javax.swing.JLabel();
        cmdBrowse = new javax.swing.JButton();
        cmdCancel1 = new javax.swing.JButton();
        cmdNext1 = new javax.swing.JButton();
        cmdReset = new javax.swing.JButton();
        cmbClass = new javax.swing.JComboBox();
        lblClass = new javax.swing.JLabel();
        txtPhoto1 = new javax.swing.JTextField();
        lblID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        lblImage = new javax.swing.JLabel();
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
        medicalPanel = new javax.swing.JPanel();
        lblPrimaryDoctor = new javax.swing.JLabel();
        txtPrimaryDoctor = new javax.swing.JTextField();
        lblDoctorPhone = new javax.swing.JLabel();
        txtDoctorPhone = new javax.swing.JTextField();
        txtHospital = new javax.swing.JTextField();
        lblHospital = new javax.swing.JLabel();
        cmdSave = new javax.swing.JButton();
        cmdCancel4 = new javax.swing.JButton();

        txtPhoto.setEditable(false);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(FrmEditStudent.class);
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
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(FrmEditStudent.class, this);
        cmdSearch.setAction(actionMap.get("search")); // NOI18N
        cmdSearch.setText(resourceMap.getString("cmdSearch.text")); // NOI18N
        cmdSearch.setName("cmdSearch"); // NOI18N

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        tblStudents.setAutoCreateRowSorter(true);
        tblStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "Class"
            }
        ));
        tblStudents.setName("tblStudents"); // NOI18N
        tblStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
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

        txtFirstName.setToolTipText(resourceMap.getString("txtFirstName.toolTipText")); // NOI18N
        txtFirstName.setName("txtFirstName"); // NOI18N

        lblLastName.setText(resourceMap.getString("lblLastName.text")); // NOI18N
        lblLastName.setName("lblLastName"); // NOI18N

        txtLastName.setToolTipText(resourceMap.getString("txtLastName.toolTipText")); // NOI18N
        txtLastName.setName("txtLastName"); // NOI18N

        lblOtherName.setText(resourceMap.getString("lblOtherName.text")); // NOI18N
        lblOtherName.setName("lblOtherName"); // NOI18N

        txtOtherName.setToolTipText(resourceMap.getString("txtOtherName.toolTipText")); // NOI18N
        txtOtherName.setName("txtOtherName"); // NOI18N

        lblGender.setText(resourceMap.getString("lblGender.text")); // NOI18N
        lblGender.setName("lblGender"); // NOI18N

        cmbGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
        cmbGender.setToolTipText(resourceMap.getString("cmbGender.toolTipText")); // NOI18N
        cmbGender.setName("cmbGender"); // NOI18N

        lblDOB.setText(resourceMap.getString("lblDOB.text")); // NOI18N
        lblDOB.setToolTipText(resourceMap.getString("lblDOB.toolTipText")); // NOI18N
        lblDOB.setName("lblDOB"); // NOI18N

        calDOB.setToolTipText(resourceMap.getString("calDOB.toolTipText")); // NOI18N
        calDOB.setName("calDOB"); // NOI18N

        lblPhoto.setText(resourceMap.getString("lblPhoto.text")); // NOI18N
        lblPhoto.setName("lblPhoto"); // NOI18N

        cmdBrowse.setAction(actionMap.get("browse")); // NOI18N
        cmdBrowse.setName("cmdBrowse"); // NOI18N

        cmdCancel1.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel1.setName("cmdCancel1"); // NOI18N

        cmdNext1.setAction(actionMap.get("next")); // NOI18N
        cmdNext1.setName("cmdNext1"); // NOI18N

        cmdReset.setAction(actionMap.get("resetForm")); // NOI18N
        cmdReset.setName("cmdReset"); // NOI18N

        cmbClass.setName("cmbClass"); // NOI18N
        cmbClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClassActionPerformed(evt);
            }
        });

        lblClass.setText(resourceMap.getString("lblClass.text")); // NOI18N
        lblClass.setName("lblClass"); // NOI18N

        txtPhoto1.setEditable(false);
        txtPhoto1.setToolTipText(resourceMap.getString("txtPhoto1.toolTipText")); // NOI18N
        txtPhoto1.setName("txtPhoto1"); // NOI18N

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

        javax.swing.GroupLayout generalPanelLayout = new javax.swing.GroupLayout(generalPanel);
        generalPanel.setLayout(generalPanelLayout);
        generalPanelLayout.setHorizontalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalPanelLayout.createSequentialGroup()
                        .addComponent(cmdReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
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
                            .addComponent(lblPhoto)
                            .addComponent(lblClass))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(generalPanelLayout.createSequentialGroup()
                                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPhoto1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                    .addComponent(calDOB, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                    .addComponent(cmbGender, 0, 151, Short.MAX_VALUE)
                                    .addComponent(txtID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                    .addComponent(txtFirstName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                    .addComponent(txtLastName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                    .addComponent(txtOtherName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cmdBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(cmbClass, 0, 288, Short.MAX_VALUE))))
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
                            .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(calDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDOB)))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblPhoto))
                    .addGroup(generalPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdBrowse)
                            .addComponent(txtPhoto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClass))
                .addGap(124, 124, 124)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel1)
                    .addComponent(cmdNext1)
                    .addComponent(cmdReset))
                .addContainerGap())
        );

        studentTabbedPane.addTab(resourceMap.getString("generalPanel.TabConstraints.tabTitle"), resourceMap.getIcon("generalPanel.TabConstraints.tabIcon"), generalPanel); // NOI18N

        contactPanel.setName("contactPanel"); // NOI18N

        lblHomeAddress.setText(resourceMap.getString("lblHomeAddress.text")); // NOI18N
        lblHomeAddress.setName("lblHomeAddress"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtHomeAddress.setColumns(20);
        txtHomeAddress.setLineWrap(true);
        txtHomeAddress.setRows(5);
        txtHomeAddress.setToolTipText(resourceMap.getString("txtHomeAddress.toolTipText")); // NOI18N
        txtHomeAddress.setWrapStyleWord(true);
        txtHomeAddress.setName("txtHomeAddress"); // NOI18N
        jScrollPane1.setViewportView(txtHomeAddress);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtMailingAddress.setColumns(20);
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

        txtEmail.setToolTipText(resourceMap.getString("txtEmail.toolTipText")); // NOI18N
        txtEmail.setName("txtEmail"); // NOI18N

        lblPhone.setText(resourceMap.getString("lblPhone.text")); // NOI18N
        lblPhone.setName("lblPhone"); // NOI18N

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
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
        txtPriConAddress.setLineWrap(true);
        txtPriConAddress.setRows(3);
        txtPriConAddress.setToolTipText(resourceMap.getString("txtPriConAddress.toolTipText")); // NOI18N
        txtPriConAddress.setWrapStyleWord(true);
        txtPriConAddress.setName("txtPriConAddress"); // NOI18N
        jScrollPane3.setViewportView(txtPriConAddress);

        lblPriConAddress.setText(resourceMap.getString("lblPriConAddress.text")); // NOI18N
        lblPriConAddress.setName("lblPriConAddress"); // NOI18N

        txtPriConPhone.setToolTipText(resourceMap.getString("txtPriConPhone.toolTipText")); // NOI18N
        txtPriConPhone.setName("txtPriConPhone"); // NOI18N

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
        txtSecConAddress.setLineWrap(true);
        txtSecConAddress.setRows(3);
        txtSecConAddress.setToolTipText(resourceMap.getString("txtSecConAddress.toolTipText")); // NOI18N
        txtSecConAddress.setWrapStyleWord(true);
        txtSecConAddress.setName("txtSecConAddress"); // NOI18N
        jScrollPane4.setViewportView(txtSecConAddress);

        lblSecConAddress.setText(resourceMap.getString("lblSecConAddress.text")); // NOI18N
        lblSecConAddress.setName("lblSecConAddress"); // NOI18N

        txtSecConPhone.setToolTipText(resourceMap.getString("txtSecConPhone.toolTipText")); // NOI18N
        txtSecConPhone.setName("txtSecConPhone"); // NOI18N

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(parentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel3)
                    .addComponent(cmdNext3))
                .addContainerGap())
        );

        studentTabbedPane.addTab(resourceMap.getString("parentPanel.TabConstraints.tabTitle"), resourceMap.getIcon("parentPanel.TabConstraints.tabIcon"), parentPanel); // NOI18N

        medicalPanel.setName("medicalPanel"); // NOI18N

        lblPrimaryDoctor.setText(resourceMap.getString("lblPrimaryDoctor.text")); // NOI18N
        lblPrimaryDoctor.setName("lblPrimaryDoctor"); // NOI18N

        txtPrimaryDoctor.setToolTipText(resourceMap.getString("txtPrimaryDoctor.toolTipText")); // NOI18N
        txtPrimaryDoctor.setName("txtPrimaryDoctor"); // NOI18N

        lblDoctorPhone.setText(resourceMap.getString("lblDoctorPhone.text")); // NOI18N
        lblDoctorPhone.setName("lblDoctorPhone"); // NOI18N

        txtDoctorPhone.setToolTipText(resourceMap.getString("txtDoctorPhone.toolTipText")); // NOI18N
        txtDoctorPhone.setName("txtDoctorPhone"); // NOI18N

        txtHospital.setToolTipText(resourceMap.getString("txtHospital.toolTipText")); // NOI18N
        txtHospital.setName("txtHospital"); // NOI18N

        lblHospital.setText(resourceMap.getString("lblHospital.text")); // NOI18N
        lblHospital.setName("lblHospital"); // NOI18N

        cmdSave.setAction(actionMap.get("save")); // NOI18N
        cmdSave.setName("cmdSave"); // NOI18N

        cmdCancel4.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel4.setName("cmdCancel4"); // NOI18N

        javax.swing.GroupLayout medicalPanelLayout = new javax.swing.GroupLayout(medicalPanel);
        medicalPanel.setLayout(medicalPanelLayout);
        medicalPanelLayout.setHorizontalGroup(
            medicalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medicalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(medicalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(medicalPanelLayout.createSequentialGroup()
                        .addGroup(medicalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrimaryDoctor)
                            .addComponent(lblDoctorPhone)
                            .addComponent(lblHospital))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(medicalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDoctorPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addComponent(txtPrimaryDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addComponent(txtHospital, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, medicalPanelLayout.createSequentialGroup()
                        .addComponent(cmdSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdCancel4)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                .addGroup(medicalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel4)
                    .addComponent(cmdSave))
                .addContainerGap())
        );

        studentTabbedPane.addTab(resourceMap.getString("medicalPanel.TabConstraints.tabTitle"), resourceMap.getIcon("medicalPanel.TabConstraints.tabIcon"), medicalPanel); // NOI18N

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
            loadStudentInfo();
            next();
        }
    }//GEN-LAST:event_tblStudentsMouseClicked

    private void cmbClassActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmbClassActionPerformed
    {//GEN-HEADEREND:event_cmbClassActionPerformed
        System.out.println(cmbClass.getSelectedItem().toString());
    }//GEN-LAST:event_cmbClassActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser calDOB;
    private javax.swing.JComboBox cmbClass;
    private javax.swing.JComboBox cmbGender;
    private javax.swing.JButton cmdBrowse;
    private javax.swing.JButton cmdCancel1;
    private javax.swing.JButton cmdCancel2;
    private javax.swing.JButton cmdCancel3;
    private javax.swing.JButton cmdCancel4;
    private javax.swing.JButton cmdCancel5;
    private javax.swing.JButton cmdNext1;
    private javax.swing.JButton cmdNext2;
    private javax.swing.JButton cmdNext3;
    private javax.swing.JButton cmdNext4;
    private javax.swing.JButton cmdReset;
    private javax.swing.JButton cmdSave;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JPanel contactPanel;
    private javax.swing.JPanel generalPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblClass;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblDoctorPhone;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblHomeAddress;
    private javax.swing.JLabel lblHospital;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblMailingAddress;
    private javax.swing.JLabel lblOtherName;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JLabel lblPriConAddress;
    private javax.swing.JLabel lblPriConName;
    private javax.swing.JLabel lblPriConPhone;
    private javax.swing.JLabel lblPrimaryDoctor;
    private javax.swing.JLabel lblResults;
    private javax.swing.JLabel lblResults2;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSecConAddress;
    private javax.swing.JLabel lblSecConName;
    private javax.swing.JLabel lblSecConPhone;
    private javax.swing.JPanel medicalPanel;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JPanel primaryContactPanel;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JPanel secConPanel;
    private javax.swing.JTabbedPane studentTabbedPane;
    private javax.swing.JTable tblStudents;
    private javax.swing.JTextField txtDoctorPhone;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextArea txtHomeAddress;
    private javax.swing.JTextField txtHospital;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextArea txtMailingAddress;
    private javax.swing.JTextField txtOtherName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPhoto;
    private javax.swing.JTextField txtPhoto1;
    private javax.swing.JTextArea txtPriConAddress;
    private javax.swing.JTextField txtPriConName;
    private javax.swing.JTextField txtPriConPhone;
    private javax.swing.JTextField txtPrimaryDoctor;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextArea txtSecConAddress;
    private javax.swing.JTextField txtSecConName;
    private javax.swing.JTextField txtSecConPhone;
    // End of variables declaration//GEN-END:variables
}
