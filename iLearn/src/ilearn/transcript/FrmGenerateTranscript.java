/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmGenerateTranscript.java
 *
 * Created on Jul 23, 2012, 10:15:50 AM
 */
package ilearn.transcript;

import ilearn.kernel.Utilities;
import ilearn.student.FrmViewStudent;
import ilearn.student.Student;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import org.jdesktop.application.Action;

/**
 *
 * @author m.rogers
 */
public class FrmGenerateTranscript extends javax.swing.JInternalFrame
{

    static final Logger logger = Logger.getLogger(FrmGenerateTranscript.class.getName());

    /** Creates new form FrmGenerateTranscript */
    public FrmGenerateTranscript()
    {
        initComponents();
    }

    private void loadStudent()
    {
        String stuID = tblResults.getValueAt(tblResults.getSelectedRow(), 0).toString();
        HashMap details = Student.getStudentDetails(stuID);
        txtID.setText(stuID);
        txtFirstName.setText(details.get("stuFirstName").toString());
        txtLastName.setText(details.get("stuLastName").toString());
        txtGender.setText(details.get("stuGender").toString());
        txtOtherName.setText(details.get("stuOtherNames").toString());
        txtDOB.setText(Utilities.MDY_Formatter.format(details.get("stuDOB")));
        txtNotes.setText(details.get("stuNotes").toString());
        try
        {
            Blob blob = (Blob) details.get("stuPhoto");
            ImageIcon ii = new ImageIcon(blob.getBytes(1, (int) blob.length()));
            if (ii.getIconHeight() > 128 || ii.getIconWidth() > 128)
            {
                Image img = ii.getImage();
                Image newimg = img.getScaledInstance(128, 128, java.awt.Image.SCALE_SMOOTH);
                ii = new ImageIcon(newimg);
                UUID randomUUID = java.util.UUID.randomUUID();
                File image = new File(System.getProperty("java.io.tmpdir") + "/" + randomUUID.toString() + ".jpg");
                BufferedImage bi = new BufferedImage(ii.getIconWidth(), ii.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = bi.createGraphics();
                g2.drawImage(newimg, 0, 0, null);
                g2.dispose();
                try
                {
                    ImageIO.write(bi, "jpg", image);
                }
                catch (IOException ex)
                {
                    Logger.getLogger(FrmViewStudent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            lblImage.setIcon(ii);
        }
        catch (Exception e)
        {
            String message = "An error occurred while rendering the student's image.";
            logger.log(Level.SEVERE, message, e);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        searchPanel = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResults = new javax.swing.JTable();
        cmdCancel = new javax.swing.JButton();
        cmdNext = new javax.swing.JButton();
        infoPanel = new javax.swing.JPanel();
        txtID = new javax.swing.JTextField();
        lblOtherName = new javax.swing.JLabel();
        lblDOB = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        txtFirstName = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        lblImage = new javax.swing.JLabel();
        lblLastName = new javax.swing.JLabel();
        txtGender = new javax.swing.JTextField();
        lblGender = new javax.swing.JLabel();
        txtOtherName = new javax.swing.JTextField();
        txtDOB = new javax.swing.JTextField();
        lblID = new javax.swing.JLabel();
        lblFirstName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Generate Transcript");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/ilearn/resources/report_user_16.png"))); // NOI18N
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N
        searchPanel.setName("searchPanel"); // NOI18N
        lblSearch.setText("Search:");
        lblSearch.setName("lblSearch"); // NOI18N
        txtSearch.setName("txtSearch"); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtSearchKeyPressed(evt);
            }
        });
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmGenerateTranscript.class, this);
        cmdSearch.setAction(actionMap.get("search")); // NOI18N
        cmdSearch.setName("cmdSearch"); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        tblResults.setAutoCreateRowSorter(true);
        tblResults.setModel(new javax.swing.table.DefaultTableModel(
                                new Object [][]
                                {

                                },
                                new String []
                                {
                                    "ID", "First Name", "Last Name", "DOB"
                                }
                            ));
        tblResults.setName("tblResults"); // NOI18N
        tblResults.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tblResultsMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                tblResultsMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblResults);
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N
        cmdNext.setAction(actionMap.get("next")); // NOI18N
        cmdNext.setName("cmdNext"); // NOI18N
        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                                .addGroup(searchPanelLayout.createSequentialGroup()
                                          .addComponent(lblSearch)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(cmdSearch))
                                .addGroup(searchPanelLayout.createSequentialGroup()
                                          .addComponent(cmdNext)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(cmdCancel)))
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
                      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel)
                                .addComponent(cmdNext))
                      .addContainerGap())
        );
        jTabbedPane1.addTab("Search", new javax.swing.ImageIcon(getClass().getResource("/ilearn/resources/find.png")), searchPanel); // NOI18N
        infoPanel.setName("infoPanel"); // NOI18N
        txtID.setEditable(false);
        txtID.setName("txtID"); // NOI18N
        lblOtherName.setText("Other Name:");
        lblOtherName.setName("lblOtherName"); // NOI18N
        lblDOB.setText("DOB:");
        lblDOB.setToolTipText("Date of Birth");
        lblDOB.setName("lblDOB"); // NOI18N
        txtLastName.setEditable(false);
        txtLastName.setToolTipText("<html>\nThis is the student's last name.<br />\n<strong>This is a mandatory field.</strong><br />\nE.g. Johnson\n</html>");
        txtLastName.setName("txtLastName"); // NOI18N
        txtFirstName.setEditable(false);
        txtFirstName.setToolTipText("<html>\nThis is the student's first name.<br />\n<strong>This is a mandatory field.</strong> <br />\nE.g. Mark\n</html>");
        txtFirstName.setName("txtFirstName"); // NOI18N
        jScrollPane6.setMaximumSize(new java.awt.Dimension(128, 128));
        jScrollPane6.setMinimumSize(new java.awt.Dimension(128, 128));
        jScrollPane6.setName("jScrollPane6"); // NOI18N
        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setName("lblImage"); // NOI18N
        jScrollPane6.setViewportView(lblImage);
        lblLastName.setText("Last Name:");
        lblLastName.setName("lblLastName"); // NOI18N
        txtGender.setEditable(false);
        txtGender.setName("txtGender"); // NOI18N
        lblGender.setText("Gender:");
        lblGender.setName("lblGender"); // NOI18N
        txtOtherName.setEditable(false);
        txtOtherName.setToolTipText("<html>\nThis is any other name that the student carries.<br />\nAn example could be their middle name.<br />\nE.g. Anthony\n</html>");
        txtOtherName.setName("txtOtherName"); // NOI18N
        txtDOB.setEditable(false);
        txtDOB.setName("txtDOB"); // NOI18N
        lblID.setText("ID:");
        lblID.setName("lblID"); // NOI18N
        lblFirstName.setText("First Name:");
        lblFirstName.setName("lblFirstName"); // NOI18N
        jLabel2.setText("Notes:");
        jLabel2.setName("jLabel2"); // NOI18N
        jScrollPane2.setName("jScrollPane2"); // NOI18N
        txtNotes.setColumns(20);
        txtNotes.setEditable(false);
        txtNotes.setLineWrap(true);
        txtNotes.setRows(5);
        txtNotes.setName("txtNotes"); // NOI18N
        jScrollPane2.setViewportView(txtNotes);
        jButton4.setAction(actionMap.get("cancel")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton5.setAction(actionMap.get("generateTranscript")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                      .addGap(10, 10, 10)
                      .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblID)
                                .addComponent(lblFirstName)
                                .addComponent(lblLastName)
                                .addComponent(lblOtherName)
                                .addComponent(lblGender)
                                .addComponent(lblDOB))
                      .addGap(4, 4, 4)
                      .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                                .addComponent(txtFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                                .addComponent(txtLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                                .addComponent(txtOtherName, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                                .addComponent(txtGender, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                                .addComponent(txtDOB, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                      .addGap(6, 6, 6)
                      .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addGap(10, 10, 10))
            .addGroup(infoPanelLayout.createSequentialGroup()
                      .addGap(10, 10, 10)
                      .addComponent(jLabel2)
                      .addGap(34, 34, 34)
                      .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                      .addGap(13, 13, 13))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoPanelLayout.createSequentialGroup()
                      .addContainerGap(144, Short.MAX_VALUE)
                      .addComponent(jButton5)
                      .addGap(18, 18, 18)
                      .addComponent(jButton4)
                      .addContainerGap())
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                      .addGap(11, 11, 11)
                      .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(infoPanelLayout.createSequentialGroup()
                                          .addGap(3, 3, 3)
                                          .addComponent(lblID)
                                          .addGap(12, 12, 12)
                                          .addComponent(lblFirstName)
                                          .addGap(12, 12, 12)
                                          .addComponent(lblLastName)
                                          .addGap(13, 13, 13)
                                          .addComponent(lblOtherName, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addGap(13, 13, 13)
                                          .addComponent(lblGender)
                                          .addGap(12, 12, 12)
                                          .addComponent(lblDOB))
                                .addGroup(infoPanelLayout.createSequentialGroup()
                                          .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addGap(6, 6, 6)
                                          .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addGap(6, 6, 6)
                                          .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addGap(6, 6, 6)
                                          .addComponent(txtOtherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addGap(6, 6, 6)
                                          .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addGap(6, 6, 6)
                                          .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addGap(6, 6, 6)
                      .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                      .addGap(102, 102, 102)
                      .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton4)
                                .addComponent(jButton5))
                      .addContainerGap())
        );
        jTabbedPane1.addTab("Student Info ", new javax.swing.ImageIcon(getClass().getResource("/ilearn/resources/user.png")), infoPanel); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                      .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
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

    private void tblResultsMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblResultsMouseClicked
    {
//GEN-HEADEREND:event_tblResultsMouseClicked
    }//GEN-LAST:event_tblResultsMouseClicked

    private void tblResultsMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblResultsMousePressed
    {
//GEN-HEADEREND:event_tblResultsMousePressed
        if (tblResults.getSelectedRow() != -1 && evt.getClickCount() >= 2)
        {
            next();
        }
    }//GEN-LAST:event_tblResultsMousePressed

    @Action
    public void cancel()
    {
        Utilities.showCancelScreen(this);
    }

    @Action
    public void next()
    {
        if (tblResults.getSelectedRow() != -1)
        {
            loadStudent();
            jTabbedPane1.setSelectedIndex(jTabbedPane1.getSelectedIndex() + 1);
        }
        else
        {
            String message = "Kindly select a student before proceeding.";
            Utilities.showWarningMessage(rootPane, message);
        }
    }

    @Action
    public void generateTranscript()
    {
        String id = txtID.getText().trim();
        File selectedFile = null;
        JFileChooser fc = new JFileChooser();
        int showOpenDialog = fc.showOpenDialog(this);
        if (showOpenDialog == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = fc.getSelectedFile();
            try
            {
                if (!selectedFile.getCanonicalPath().endsWith(".xls"))
                {
                    String filename = selectedFile.getCanonicalPath();
                    filename = filename + ".xls";
                    selectedFile = new File(filename);
                }
            }
            catch (IOException ex)
            {
                logger.log(Level.SEVERE, null, ex);
            }
            Transcript.generateTranscript(id, selectedFile);
        }
    }

    @Action
    public void search()
    {
        String criteria = txtSearch.getText().trim();
        tblResults.setModel(Student.getStudentsSearchTable(criteria));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdNext;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblOtherName;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTable tblResults;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextArea txtNotes;
    private javax.swing.JTextField txtOtherName;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}