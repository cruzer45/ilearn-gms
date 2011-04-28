/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmNewStudent.java
 *
 * Created on Feb 15, 2011, 9:18:09 PM
 */
package ilearn.student;

import ilearn.classes.Classes;
import ilearn.kernel.ImageFilter;
import ilearn.kernel.Utilities;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmNewStudent extends javax.swing.JInternalFrame
{

    Logger logger = Logger.getLogger(FrmNewStudent.class.getName());
    JComponent parent = this.getRootPane();
    File selectedFile;

    /** Creates new form FrmNewStudent */
    public FrmNewStudent()
    {
        initComponents();
        populateLists();
    }

    private void populateLists()
    {
        ArrayList<String> classList = Classes.getClassList();
        cmbClass.setModel(new DefaultComboBoxModel(classList.toArray()));
    }

    @Action
    public void next()
    {
        studentTabbedPane.setSelectedIndex(studentTabbedPane.getSelectedIndex() + 1);
    }

    @Action
    public void cancel()
    {
        Utilities.showCancelScreen(this);
    }

    @Action
    public void resetForm()
    {
        this.remove(studentTabbedPane);
        initComponents();
        populateLists();
    }

    @Action
    public void save()
    {
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
            if (selectedFile == null)
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

        if (stuAddress2.isEmpty())
        {
            stuAddress2 = stuAddress1;
        }


        if (Student.addStudent(stuFirstName, stuLastName, stuOtherNames, stuDOB, stuGender, stuEmail, stuPhone, selectedFile, stuAddress1, stuAddress2, stuPCName, stuPCPhone, stuSCName, stuPCAddress, stuSCPhone, stuSCAddress, stuDoctorName, stuDoctorContact, stuHospital, stuClsCode))
        {
            String message = "The student was successfully added. \n"
                    + "Would you like to add another?";
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

    @Action
    public void browse()
    {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //fc.addChoosableFileFilter(new ImageFilter());
        fc.setFileFilter(new ImageFilter());

        int returnVal = fc.showOpenDialog(rootPane);

        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = fc.getSelectedFile();

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
                    String message = "An error occurred while trying to save the image.";
                    logger.log(Level.SEVERE, message, ex);
                }
            }
            lblImage.setIcon(ii);

            try
            {
                txtPhoto.setText(selectedFile.getCanonicalPath().toString());
            }
            catch (IOException ex)
            {
                Logger.getLogger(FrmNewStudent.class.getName()).log(Level.SEVERE, "Error while selecting the file. ", ex);
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
    private void initComponents() {

        studentTabbedPane = new javax.swing.JTabbedPane();
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
        cmdBrowse = new javax.swing.JButton();
        cmdCancel1 = new javax.swing.JButton();
        cmdNext1 = new javax.swing.JButton();
        cmdReset = new javax.swing.JButton();
        cmbClass = new javax.swing.JComboBox();
        lblClass = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lblImage = new javax.swing.JLabel();
        lblPhoto = new javax.swing.JLabel();
        txtPhoto = new javax.swing.JTextField();
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

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(FrmNewStudent.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setToolTipText(resourceMap.getString("Form.toolTipText")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                frameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        studentTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        studentTabbedPane.setName("studentTabbedPane"); // NOI18N

        generalPanel.setName("generalPanel"); // NOI18N

        lblFirstName.setText(resourceMap.getString("lblFirstName.text")); // NOI18N
        lblFirstName.setName("lblFirstName"); // NOI18N

        txtFirstName.setText(resourceMap.getString("txtFirstName.text")); // NOI18N
        txtFirstName.setToolTipText(resourceMap.getString("txtFirstName.toolTipText")); // NOI18N
        txtFirstName.setName("txtFirstName"); // NOI18N

        lblLastName.setText(resourceMap.getString("lblLastName.text")); // NOI18N
        lblLastName.setName("lblLastName"); // NOI18N

        txtLastName.setText(resourceMap.getString("txtLastName.text")); // NOI18N
        txtLastName.setToolTipText(resourceMap.getString("txtLastName.toolTipText")); // NOI18N
        txtLastName.setName("txtLastName"); // NOI18N

        lblOtherName.setText(resourceMap.getString("lblOtherName.text")); // NOI18N
        lblOtherName.setName("lblOtherName"); // NOI18N

        txtOtherName.setText(resourceMap.getString("txtOtherName.text")); // NOI18N
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

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(FrmNewStudent.class, this);
        cmdBrowse.setAction(actionMap.get("browse")); // NOI18N
        cmdBrowse.setText(resourceMap.getString("cmdBrowse.text")); // NOI18N
        cmdBrowse.setToolTipText(resourceMap.getString("cmdBrowse.toolTipText")); // NOI18N
        cmdBrowse.setName("cmdBrowse"); // NOI18N

        cmdCancel1.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel1.setText(resourceMap.getString("cmdCancel1.text")); // NOI18N
        cmdCancel1.setName("cmdCancel1"); // NOI18N

        cmdNext1.setAction(actionMap.get("next")); // NOI18N
        cmdNext1.setText(resourceMap.getString("cmdNext1.text")); // NOI18N
        cmdNext1.setName("cmdNext1"); // NOI18N

        cmdReset.setAction(actionMap.get("resetForm")); // NOI18N
        cmdReset.setText(resourceMap.getString("cmdReset.text")); // NOI18N
        cmdReset.setName("cmdReset"); // NOI18N

        cmbClass.setName("cmbClass"); // NOI18N

        lblClass.setText(resourceMap.getString("lblClass.text")); // NOI18N
        lblClass.setName("lblClass"); // NOI18N

        jScrollPane5.setMinimumSize(new java.awt.Dimension(128, 128));
        jScrollPane5.setName("jScrollPane5"); // NOI18N

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setIcon(resourceMap.getIcon("lblImage.icon")); // NOI18N
        lblImage.setText(resourceMap.getString("lblImage.text")); // NOI18N
        lblImage.setName("lblImage"); // NOI18N
        jScrollPane5.setViewportView(lblImage);

        lblPhoto.setText(resourceMap.getString("lblPhoto.text")); // NOI18N
        lblPhoto.setName("lblPhoto"); // NOI18N

        txtPhoto.setEditable(false);
        txtPhoto.setText(resourceMap.getString("txtPhoto.text")); // NOI18N
        txtPhoto.setName("txtPhoto"); // NOI18N

        javax.swing.GroupLayout generalPanelLayout = new javax.swing.GroupLayout(generalPanel);
        generalPanel.setLayout(generalPanelLayout);
        generalPanelLayout.setHorizontalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generalPanelLayout.createSequentialGroup()
                        .addComponent(cmdReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                        .addComponent(cmdNext1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdCancel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generalPanelLayout.createSequentialGroup()
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFirstName)
                            .addComponent(lblLastName)
                            .addComponent(lblOtherName)
                            .addComponent(lblGender)
                            .addComponent(lblDOB)
                            .addComponent(lblClass)
                            .addComponent(lblPhoto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(cmbClass, 0, 168, Short.MAX_VALUE)
                            .addComponent(calDOB, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(cmbGender, 0, 168, Short.MAX_VALUE)
                            .addComponent(txtOtherName, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(txtLastName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmdBrowse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        generalPanelLayout.setVerticalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalPanelLayout.createSequentialGroup()
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFirstName)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLastName)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOtherName)
                            .addComponent(txtOtherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGender)
                            .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(calDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDOB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClass)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPhoto)
                    .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmdBrowse)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
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

        txtEmail.setText(resourceMap.getString("txtEmail.text")); // NOI18N
        txtEmail.setToolTipText(resourceMap.getString("txtEmail.toolTipText")); // NOI18N
        txtEmail.setName("txtEmail"); // NOI18N

        lblPhone.setText(resourceMap.getString("lblPhone.text")); // NOI18N
        lblPhone.setName("lblPhone"); // NOI18N

        txtPhone.setText(resourceMap.getString("txtPhone.text")); // NOI18N
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
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                            .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
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

        txtPriConPhone.setText(resourceMap.getString("txtPriConPhone.text")); // NOI18N
        txtPriConPhone.setToolTipText(resourceMap.getString("txtPriConPhone.toolTipText")); // NOI18N
        txtPriConPhone.setName("txtPriConPhone"); // NOI18N

        txtPriConName.setText(resourceMap.getString("txtPriConName.text")); // NOI18N
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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(txtPriConPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(txtPriConName, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(txtSecConPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(txtSecConName, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(parentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel3)
                    .addComponent(cmdNext3))
                .addContainerGap())
        );

        studentTabbedPane.addTab(resourceMap.getString("parentPanel.TabConstraints.tabTitle"), resourceMap.getIcon("parentPanel.TabConstraints.tabIcon"), parentPanel); // NOI18N

        medicalPanel.setName("medicalPanel"); // NOI18N

        lblPrimaryDoctor.setText(resourceMap.getString("lblPrimaryDoctor.text")); // NOI18N
        lblPrimaryDoctor.setName("lblPrimaryDoctor"); // NOI18N

        txtPrimaryDoctor.setText(resourceMap.getString("txtPrimaryDoctor.text")); // NOI18N
        txtPrimaryDoctor.setToolTipText(resourceMap.getString("txtPrimaryDoctor.toolTipText")); // NOI18N
        txtPrimaryDoctor.setName("txtPrimaryDoctor"); // NOI18N

        lblDoctorPhone.setText(resourceMap.getString("lblDoctorPhone.text")); // NOI18N
        lblDoctorPhone.setName("lblDoctorPhone"); // NOI18N

        txtDoctorPhone.setText(resourceMap.getString("txtDoctorPhone.text")); // NOI18N
        txtDoctorPhone.setToolTipText(resourceMap.getString("txtDoctorPhone.toolTipText")); // NOI18N
        txtDoctorPhone.setName("txtDoctorPhone"); // NOI18N

        txtHospital.setText(resourceMap.getString("txtHospital.text")); // NOI18N
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
                            .addComponent(txtDoctorPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(txtPrimaryDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(txtHospital, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
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
                .addComponent(studentTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(studentTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void frameClosing(javax.swing.event.InternalFrameEvent evt)//GEN-FIRST:event_frameClosing
    {//GEN-HEADEREND:event_frameClosing
        cancel();
    }//GEN-LAST:event_frameClosing
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser calDOB;
    private javax.swing.JComboBox cmbClass;
    private javax.swing.JComboBox cmbGender;
    private javax.swing.JButton cmdBrowse;
    private javax.swing.JButton cmdCancel1;
    private javax.swing.JButton cmdCancel2;
    private javax.swing.JButton cmdCancel3;
    private javax.swing.JButton cmdCancel4;
    private javax.swing.JButton cmdNext1;
    private javax.swing.JButton cmdNext2;
    private javax.swing.JButton cmdNext3;
    private javax.swing.JButton cmdReset;
    private javax.swing.JButton cmdSave;
    private javax.swing.JPanel contactPanel;
    private javax.swing.JPanel generalPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblClass;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblDoctorPhone;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblHomeAddress;
    private javax.swing.JLabel lblHospital;
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
    private javax.swing.JLabel lblSecConAddress;
    private javax.swing.JLabel lblSecConName;
    private javax.swing.JLabel lblSecConPhone;
    private javax.swing.JPanel medicalPanel;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JPanel primaryContactPanel;
    private javax.swing.JPanel secConPanel;
    private javax.swing.JTabbedPane studentTabbedPane;
    private javax.swing.JTextField txtDoctorPhone;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextArea txtHomeAddress;
    private javax.swing.JTextField txtHospital;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextArea txtMailingAddress;
    private javax.swing.JTextField txtOtherName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPhoto;
    private javax.swing.JTextArea txtPriConAddress;
    private javax.swing.JTextField txtPriConName;
    private javax.swing.JTextField txtPriConPhone;
    private javax.swing.JTextField txtPrimaryDoctor;
    private javax.swing.JTextArea txtSecConAddress;
    private javax.swing.JTextField txtSecConName;
    private javax.swing.JTextField txtSecConPhone;
    // End of variables declaration//GEN-END:variables
}
