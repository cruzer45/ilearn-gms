/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmManageSchool.java
 *
 * Created on Apr 18, 2011, 9:48:01 PM
 */
package ilearn.school;

import ilearn.kernel.ImageFilter;
import ilearn.kernel.Utilities;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmManageSchool extends javax.swing.JInternalFrame
{

    static final Logger logger = Logger.getLogger(FrmManageSchool.class.getName());
    File selectedFile;
    boolean imageChanged = false;

    /** Creates new form FrmManageSchool */
    public FrmManageSchool()
    {
        initComponents();
        loadInfo();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        schoolTabbedPane = new javax.swing.JTabbedPane();
        generalPanel = new javax.swing.JPanel();
        lblSchoolName = new javax.swing.JLabel();
        txtSchoolName = new javax.swing.JTextField();
        lblShortName = new javax.swing.JLabel();
        txtShortName = new javax.swing.JTextField();
        lblTelephone = new javax.swing.JLabel();
        txtTelephone = new javax.swing.JTextField();
        lblTelephone2 = new javax.swing.JLabel();
        txtTelephone2 = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        ImagePanel = new javax.swing.JPanel();
        cmdBrowse = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lblImage = new javax.swing.JLabel();
        cmdCancel = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(FrmManageSchool.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N

        schoolTabbedPane.setName("schoolTabbedPane"); // NOI18N

        generalPanel.setName("generalPanel"); // NOI18N

        lblSchoolName.setText(resourceMap.getString("lblSchoolName.text")); // NOI18N
        lblSchoolName.setName("lblSchoolName"); // NOI18N

        txtSchoolName.setText(resourceMap.getString("txtSchoolName.text")); // NOI18N
        txtSchoolName.setName("txtSchoolName"); // NOI18N

        lblShortName.setText(resourceMap.getString("lblShortName.text")); // NOI18N
        lblShortName.setName("lblShortName"); // NOI18N

        txtShortName.setText(resourceMap.getString("txtShortName.text")); // NOI18N
        txtShortName.setName("txtShortName"); // NOI18N

        lblTelephone.setText(resourceMap.getString("lblTelephone.text")); // NOI18N
        lblTelephone.setName("lblTelephone"); // NOI18N

        txtTelephone.setText(resourceMap.getString("txtTelephone.text")); // NOI18N
        txtTelephone.setName("txtTelephone"); // NOI18N

        lblTelephone2.setText(resourceMap.getString("lblTelephone2.text")); // NOI18N
        lblTelephone2.setName("lblTelephone2"); // NOI18N

        txtTelephone2.setText(resourceMap.getString("txtTelephone2.text")); // NOI18N
        txtTelephone2.setName("txtTelephone2"); // NOI18N

        lblAddress.setText(resourceMap.getString("lblAddress.text")); // NOI18N
        lblAddress.setName("lblAddress"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        txtAddress.setName("txtAddress"); // NOI18N
        jScrollPane1.setViewportView(txtAddress);

        javax.swing.GroupLayout generalPanelLayout = new javax.swing.GroupLayout(generalPanel);
        generalPanel.setLayout(generalPanelLayout);
        generalPanelLayout.setHorizontalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSchoolName)
                    .addComponent(lblShortName)
                    .addComponent(lblTelephone)
                    .addComponent(lblTelephone2)
                    .addComponent(lblAddress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(txtTelephone2, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(txtTelephone, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(txtShortName, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(txtSchoolName, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                .addContainerGap())
        );
        generalPanelLayout.setVerticalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSchoolName)
                    .addComponent(txtSchoolName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblShortName)
                    .addComponent(txtShortName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelephone)
                    .addComponent(txtTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelephone2)
                    .addComponent(txtTelephone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAddress)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        schoolTabbedPane.addTab(resourceMap.getString("generalPanel.TabConstraints.tabTitle"), resourceMap.getIcon("generalPanel.TabConstraints.tabIcon"), generalPanel); // NOI18N

        ImagePanel.setName("ImagePanel"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(FrmManageSchool.class, this);
        cmdBrowse.setAction(actionMap.get("browse")); // NOI18N
        cmdBrowse.setText(resourceMap.getString("cmdBrowse.text")); // NOI18N
        cmdBrowse.setName("cmdBrowse"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setIcon(resourceMap.getIcon("lblImage.icon")); // NOI18N
        lblImage.setText(resourceMap.getString("lblImage.text")); // NOI18N
        lblImage.setAutoscrolls(true);
        lblImage.setName("lblImage"); // NOI18N
        jScrollPane2.setViewportView(lblImage);

        javax.swing.GroupLayout ImagePanelLayout = new javax.swing.GroupLayout(ImagePanel);
        ImagePanel.setLayout(ImagePanelLayout);
        ImagePanelLayout.setHorizontalGroup(
            ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ImagePanelLayout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(cmdBrowse)
                .addContainerGap())
            .addGroup(ImagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        ImagePanelLayout.setVerticalGroup(
            ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ImagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addComponent(cmdBrowse)
                .addContainerGap())
        );

        schoolTabbedPane.addTab(resourceMap.getString("ImagePanel.TabConstraints.tabTitle"), resourceMap.getIcon("ImagePanel.TabConstraints.tabIcon"), ImagePanel); // NOI18N

        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setText(resourceMap.getString("cmdCancel.text")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N

        cmdSave.setAction(actionMap.get("save")); // NOI18N
        cmdSave.setText(resourceMap.getString("cmdSave.text")); // NOI18N
        cmdSave.setName("cmdSave"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(schoolTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cmdSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(schoolTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel)
                    .addComponent(cmdSave))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void cancel()
    {
        Utilities.showCancelScreen(this);
    }

    @Action
    public void save()
    {
        String schName = txtSchoolName.getText().trim(),
                schShortName = txtShortName.getText().trim(),
                schPhone1 = txtTelephone.getText().trim(),
                schPhone2 = txtTelephone2.getText().trim(),
                schAddress = txtAddress.getText().trim();
        File schLogo = selectedFile;

        if (imageChanged)
        {
            if (School.updateSchool1(schName, schShortName, schPhone1, schPhone2, schAddress, schLogo))
            {
                String message = "The information was saved.\n"
                        + "Would you like to continue editing?";
                int response = Utilities.showConfirmDialog(rootPane, message);

                if (response == JOptionPane.YES_OPTION)
                {
                    loadInfo();
                    imageChanged = false;//reset the image
                }
                else
                {
                    this.dispose();
                }
            }
            else
            {
                String message = "An error occurred while updating the school info.\n"
                        + "Kindly verify your information and try again.\n"
                        + "If the problem persists, kindly contact your system administrator.";
                Utilities.showErrorMessage(rootPane, message);
            }
        }
        else
        {
            if (School.updateSchoolInfo(schName, schShortName, schPhone1, schPhone2, schAddress))
            {
                String message = "The information was saved.\n"
                        + "Would you like to continue editing?";
                int response = Utilities.showConfirmDialog(rootPane, message);

                if (response == JOptionPane.YES_OPTION)
                {
                    loadInfo();
                    imageChanged = false;//reset the image
                }
                else
                {
                    this.dispose();
                }
            }
            else
            {
                String message = "An error occurred while updating the school info.\n"
                        + "Kindly verify your information and try again.\n"
                        + "If the problem persists, kindly contact your system administrator.";
                Utilities.showErrorMessage(rootPane, message);
            }
        }
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

            if (ii.getIconHeight() > 170 || ii.getIconWidth() > 320)
            {
                Image img = ii.getImage();
                Image newimg = img.getScaledInstance(320, 170, java.awt.Image.SCALE_SMOOTH);
                ii = new ImageIcon(newimg);
                lblImage.setIcon(ii);


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


        }
    }

    private void loadInfo()
    {
        try
        {
            ArrayList<Object> info = School.getSchoolInfo();
            txtSchoolName.setText(info.get(0).toString());
            txtShortName.setText(info.get(1).toString());
            txtTelephone.setText(info.get(2).toString());
            txtTelephone2.setText(info.get(3).toString());
            txtAddress.setText(info.get(4).toString());
            Blob blob = (Blob) info.get(5);
            ImageIcon ii = new ImageIcon(blob.getBytes(1, (int) blob.length()));
            lblImage.setIcon(ii);
        }
        catch (SQLException ex)
        {
            String message = "An error occurred while loading the information.";
            logger.log(Level.SEVERE, message, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ImagePanel;
    private javax.swing.JButton cmdBrowse;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdSave;
    private javax.swing.JPanel generalPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblSchoolName;
    private javax.swing.JLabel lblShortName;
    private javax.swing.JLabel lblTelephone;
    private javax.swing.JLabel lblTelephone2;
    private javax.swing.JTabbedPane schoolTabbedPane;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtSchoolName;
    private javax.swing.JTextField txtShortName;
    private javax.swing.JTextField txtTelephone;
    private javax.swing.JTextField txtTelephone2;
    // End of variables declaration//GEN-END:variables
}
