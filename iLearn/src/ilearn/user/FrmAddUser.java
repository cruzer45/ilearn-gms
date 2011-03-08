
/*
 * FrmAddUser.java
 *
 * Created on Mar 5, 2011, 8:59:24 PM
 */
package ilearn.user;

import ilearn.kernel.Utilities;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmAddUser extends javax.swing.JInternalFrame
{

    /** Creates new form FrmAddUser */
    public FrmAddUser()
    {
        initComponents();
        
        //Loads the values from the database into the combo boxes.
        cmbGroup.setModel(new DefaultComboBoxModel(User.getUserGroups().toArray()));
        cmbLevel.setModel(new DefaultComboBoxModel(User.getGroupLevels(cmbGroup.getSelectedItem().toString()).toArray()));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFirstName = new javax.swing.JLabel();
        lblLastName = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtUserName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        lblGroup = new javax.swing.JLabel();
        cmbGroup = new javax.swing.JComboBox();
        lblLevel = new javax.swing.JLabel();
        cmbLevel = new javax.swing.JComboBox();
        cmdCancel = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        cmdReset = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(FrmAddUser.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setToolTipText(resourceMap.getString("Form.toolTipText")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N

        lblFirstName.setText(resourceMap.getString("lblFirstName.text")); // NOI18N
        lblFirstName.setName("lblFirstName"); // NOI18N

        lblLastName.setText(resourceMap.getString("lblLastName.text")); // NOI18N
        lblLastName.setName("lblLastName"); // NOI18N

        lblUserName.setText(resourceMap.getString("lblUserName.text")); // NOI18N
        lblUserName.setName("lblUserName"); // NOI18N

        lblPassword.setText(resourceMap.getString("lblPassword.text")); // NOI18N
        lblPassword.setName("lblPassword"); // NOI18N

        txtFirstName.setText(resourceMap.getString("txtFirstName.text")); // NOI18N
        txtFirstName.setName("txtFirstName"); // NOI18N

        txtUserName.setText(resourceMap.getString("txtUserName.text")); // NOI18N
        txtUserName.setName("txtUserName"); // NOI18N

        txtLastName.setText(resourceMap.getString("txtLastName.text")); // NOI18N
        txtLastName.setName("txtLastName"); // NOI18N

        txtPassword.setText(resourceMap.getString("txtPassword.text")); // NOI18N
        txtPassword.setName("txtPassword"); // NOI18N

        lblGroup.setText(resourceMap.getString("lblGroup.text")); // NOI18N
        lblGroup.setName("lblGroup"); // NOI18N

        cmbGroup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbGroup.setName("cmbGroup"); // NOI18N
        cmbGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGroupActionPerformed(evt);
            }
        });

        lblLevel.setText(resourceMap.getString("lblLevel.text")); // NOI18N
        lblLevel.setName("lblLevel"); // NOI18N

        cmbLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbLevel.setName("cmbLevel"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(FrmAddUser.class, this);
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setText(resourceMap.getString("cmdCancel.text")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N

        cmdSave.setAction(actionMap.get("save")); // NOI18N
        cmdSave.setText(resourceMap.getString("cmdSave.text")); // NOI18N
        cmdSave.setName("cmdSave"); // NOI18N

        cmdReset.setAction(actionMap.get("resetForm")); // NOI18N
        cmdReset.setText(resourceMap.getString("cmdReset.text")); // NOI18N
        cmdReset.setName("cmdReset"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLastName)
                            .addComponent(lblUserName)
                            .addComponent(lblFirstName)
                            .addComponent(lblPassword)
                            .addComponent(lblGroup)
                            .addComponent(lblLevel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbLevel, 0, 257, Short.MAX_VALUE)
                            .addComponent(cmbGroup, 0, 257, Short.MAX_VALUE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                            .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                            .addComponent(txtLastName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cmdReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(cmdSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstName)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLastName)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUserName)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGroup)
                    .addComponent(cmbGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLevel)
                    .addComponent(cmbLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel)
                    .addComponent(cmdSave)
                    .addComponent(cmdReset))
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-353)/2, (screenSize.height-244)/2, 353, 244);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This is called when the user changes the user group.
     * The subgroups are reloaded when the group is changed.
     * @param evt
     */
    private void cmbGroupActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmbGroupActionPerformed
    {//GEN-HEADEREND:event_cmbGroupActionPerformed
        cmbLevel.setModel(new DefaultComboBoxModel(User.getGroupLevels(cmbGroup.getSelectedItem().toString()).toArray()));
    }//GEN-LAST:event_cmbGroupActionPerformed

    @Action
    public void cancel()
    {
        Utilities.showCancelScreen(this);
    }

    /**
     * This function tries to add the user to the system.
     * It tells them if it was successful or not.
     */
    @Action
    public void save()
    {
        //Declare the variables.
        String username = txtUserName.getText().trim();
        String password = String.valueOf(txtPassword.getPassword()).trim();
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String group = cmbGroup.getSelectedItem().toString();
        String level = cmbLevel.getSelectedItem().toString();

        //If the user was added successfully, it displays a message.
        if (User.addUser(username, password, firstName, lastName, group, level))
        {
            String message = "The user was successfully added.\n"
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
        else // If it doesn't get added tell the user something went wrong.
        {
            String message = "An error occurred while adding this user.\n"
                    + "Kindly verify your information and try again.\n"
                    + "If the problem persists, kindly contact your system administrator.";
            Utilities.showErrorMessage(rootPane, message);
        }
    }

    /**
     * This function clears all inputs from the forms.
     */
    @Action
    public void resetForm()
    {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
        cmbGroup.setSelectedIndex(0);
        cmbLevel.setSelectedIndex(0);
        txtFirstName.grabFocus();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbGroup;
    private javax.swing.JComboBox cmbLevel;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdReset;
    private javax.swing.JButton cmdSave;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblGroup;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblLevel;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
