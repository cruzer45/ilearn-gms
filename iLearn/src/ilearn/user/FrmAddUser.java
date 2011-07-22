/*
 * FrmAddUser.java
 *
 * Created on Mar 5, 2011, 8:59:24 PM
 */
package ilearn.user;

import ilearn.kernel.TableColumnAdjuster;
import ilearn.kernel.Utilities;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel.CheckingMode;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.tree.TreePath;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmAddUser extends javax.swing.JInternalFrame
{

    String warnings = "";

    /** Creates new form FrmAddUser */
    public FrmAddUser()
    {
        initComponents();
        populateLists();
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
        cmdCancel = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        cmdReset = new javax.swing.JButton();
        userTabbedPane = new javax.swing.JTabbedPane();
        generalPanel = new javax.swing.JPanel();
        lblFirstName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        lblPassword = new javax.swing.JLabel();
        lblLastName = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        lblGroup = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        lblUserName = new javax.swing.JLabel();
        cmbGroup = new javax.swing.JComboBox();
        linksPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLinks = new javax.swing.JTable();
        cmdUnlink = new javax.swing.JButton();
        cmdLink = new javax.swing.JButton();
        previlegePane = new javax.swing.JScrollPane();
        tree = new it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree();
        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmAddUser.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setToolTipText(resourceMap.getString("Form.toolTipText")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(400, 380));
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmAddUser.class, this);
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setText(resourceMap.getString("cmdCancel.text")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N
        cmdSave.setAction(actionMap.get("save")); // NOI18N
        cmdSave.setText(resourceMap.getString("cmdSave.text")); // NOI18N
        cmdSave.setName("cmdSave"); // NOI18N
        cmdReset.setAction(actionMap.get("resetForm")); // NOI18N
        cmdReset.setText(resourceMap.getString("cmdReset.text")); // NOI18N
        cmdReset.setName("cmdReset"); // NOI18N
        userTabbedPane.setName("userTabbedPane"); // NOI18N
        generalPanel.setName("generalPanel"); // NOI18N
        lblFirstName.setText(resourceMap.getString("lblFirstName.text")); // NOI18N
        lblFirstName.setName("lblFirstName"); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        txtFirstName.setText(resourceMap.getString("txtFirstName.text")); // NOI18N
        txtFirstName.setToolTipText(resourceMap.getString("txtFirstName.toolTipText")); // NOI18N
        txtFirstName.setName("txtFirstName"); // NOI18N
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(5), Integer.valueOf(0), null, Integer.valueOf(1)));
        jSpinner1.setName("jSpinner1"); // NOI18N
        lblPassword.setText(resourceMap.getString("lblPassword.text")); // NOI18N
        lblPassword.setName("lblPassword"); // NOI18N
        lblLastName.setText(resourceMap.getString("lblLastName.text")); // NOI18N
        lblLastName.setName("lblLastName"); // NOI18N
        txtUserName.setText(resourceMap.getString("txtUserName.text")); // NOI18N
        txtUserName.setToolTipText(resourceMap.getString("txtUserName.toolTipText")); // NOI18N
        txtUserName.setName("txtUserName"); // NOI18N
        txtPassword.setText(resourceMap.getString("txtPassword.text")); // NOI18N
        txtPassword.setToolTipText(resourceMap.getString("txtPassword.toolTipText")); // NOI18N
        txtPassword.setName("txtPassword"); // NOI18N
        lblGroup.setText(resourceMap.getString("lblGroup.text")); // NOI18N
        lblGroup.setName("lblGroup"); // NOI18N
        txtLastName.setText(resourceMap.getString("txtLastName.text")); // NOI18N
        txtLastName.setToolTipText(resourceMap.getString("txtLastName.toolTipText")); // NOI18N
        txtLastName.setName("txtLastName"); // NOI18N
        lblUserName.setText(resourceMap.getString("lblUserName.text")); // NOI18N
        lblUserName.setName("lblUserName"); // NOI18N
        cmbGroup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbGroup.setToolTipText(resourceMap.getString("cmbGroup.toolTipText")); // NOI18N
        cmbGroup.setName("cmbGroup"); // NOI18N
        cmbGroup.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cmbGroupActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout generalPanelLayout = new javax.swing.GroupLayout(generalPanel);
        generalPanel.setLayout(generalPanelLayout);
        generalPanelLayout.setHorizontalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblLastName)
                                .addComponent(lblUserName)
                                .addComponent(lblFirstName)
                                .addComponent(lblPassword)
                                .addComponent(lblGroup)
                                .addComponent(jLabel1))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cmbGroup, javax.swing.GroupLayout.Alignment.LEADING, 0, 279, Short.MAX_VALUE)
                                .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                .addComponent(txtUserName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                .addComponent(txtFirstName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                .addComponent(txtLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
                      .addContainerGap())
        );
        generalPanelLayout.setVerticalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblFirstName)
                                .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblLastName)
                                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblUserName)
                                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPassword)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblGroup)
                                .addComponent(cmbGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addContainerGap(115, Short.MAX_VALUE))
        );
        userTabbedPane.addTab(resourceMap.getString("generalPanel.TabConstraints.tabTitle"), resourceMap.getIcon("generalPanel.TabConstraints.tabIcon"), generalPanel); // NOI18N
        linksPanel.setName("linksPanel"); // NOI18N
        jScrollPane2.setName("jScrollPane2"); // NOI18N
        tblLinks.setAutoCreateRowSorter(true);
        tblLinks.setModel(new javax.swing.table.DefaultTableModel(
                              new Object [][]
                              {

                              },
                              new String []
                              {
                                  "ID", "Name"
                              }
                          ));
        tblLinks.setName("tblLinks"); // NOI18N
        jScrollPane2.setViewportView(tblLinks);
        tblLinks.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblLinks.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblLinks.columnModel.title0")); // NOI18N
        tblLinks.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblLinks.columnModel.title1")); // NOI18N
        cmdUnlink.setAction(actionMap.get("unlinkAccount")); // NOI18N
        cmdUnlink.setText(resourceMap.getString("cmdUnlink.text")); // NOI18N
        cmdUnlink.setName("cmdUnlink"); // NOI18N
        cmdLink.setAction(actionMap.get("linkAccount")); // NOI18N
        cmdLink.setText(resourceMap.getString("cmdLink.text")); // NOI18N
        cmdLink.setName("cmdLink"); // NOI18N
        javax.swing.GroupLayout linksPanelLayout = new javax.swing.GroupLayout(linksPanel);
        linksPanel.setLayout(linksPanelLayout);
        linksPanelLayout.setHorizontalGroup(
            linksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, linksPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(linksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                                .addGroup(linksPanelLayout.createSequentialGroup()
                                          .addComponent(cmdLink)
                                          .addGap(18, 18, 18)
                                          .addComponent(cmdUnlink)))
                      .addContainerGap())
        );
        linksPanelLayout.setVerticalGroup(
            linksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, linksPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addGroup(linksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdUnlink)
                                .addComponent(cmdLink))
                      .addContainerGap())
        );
        userTabbedPane.addTab(resourceMap.getString("linksPanel.TabConstraints.tabTitle"), resourceMap.getIcon("linksPanel.TabConstraints.tabIcon"), linksPanel); // NOI18N
        previlegePane.setName("previlegePane"); // NOI18N
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Menu");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Student");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Add Student");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit Student");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("View Student");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Attendance");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Enter Attendance");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit Attendance");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Grades");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Create Assessment");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit Assessment");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit Mid Terms");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Demerits");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Record Demerits");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit Demerits");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Reports");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Student Reports");
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Student List");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Repeating Students");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Students By Class");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Student ID Cards");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Class Reports");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Class List Report");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Class Grade Book");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Report Cards Menu");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Mid-Term Reports");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Mid-Term Class Ranking");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Term End Report");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Term End Ranking");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Demerit Reports");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Demerits By Class");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Demerits By Student");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Statistical Reports");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Class Size Distribution");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Gender Distribution");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Manage");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Class");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Add Class");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Edit Class");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("View Class");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Promotions");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Assign Promotions");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Promote Students");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("School");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Staff");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Add Staff");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Edit Staff");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Subjects");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Add Subjects");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Edit Subjects");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Term");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Add Term");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Edit Term");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Time Slots");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Add Time Slot");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Edit Time Slot");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("User Menu");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Add User");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Edit User");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Utilities Menu");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Mid Term");
        javax.swing.tree.DefaultMutableTreeNode treeNode5 = new javax.swing.tree.DefaultMutableTreeNode("Calculate Mid Term Grades");
        treeNode4.add(treeNode5);
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("End Of Term");
        treeNode5 = new javax.swing.tree.DefaultMutableTreeNode("Calculate End of Term Grades");
        treeNode4.add(treeNode5);
        treeNode5 = new javax.swing.tree.DefaultMutableTreeNode("Close Term");
        treeNode4.add(treeNode5);
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        tree.setName("tree"); // NOI18N
        previlegePane.setViewportView(tree);
        userTabbedPane.addTab(resourceMap.getString("previlegePane.TabConstraints.tabTitle"), resourceMap.getIcon("previlegePane.TabConstraints.tabIcon"), previlegePane); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(userTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                          .addComponent(cmdReset)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                                          .addComponent(cmdSave)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel)))
                      .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(userTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                      .addGap(18, 18, 18)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel)
                                .addComponent(cmdSave)
                                .addComponent(cmdReset))
                      .addContainerGap())
        );
        setBounds(0, 0, 400, 400);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbGroupActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmbGroupActionPerformed
    {
//GEN-HEADEREND:event_cmbGroupActionPerformed
        if (cmbGroup.getSelectedItem().toString().equals("Administration"))
        {
            String permission = User.administrationString;
            tree.expandAll();
            for (int i = 0; i < tree.getRowCount(); i++)
            {
                TreePath currPath = tree.getPathForRow(i);
                String currentPath = currPath.getLastPathComponent().toString();
                if (previligeAvailable(currentPath, permission))
                {
                    tree.addCheckingPath(currPath);
                }
                else
                {
                    tree.removeCheckingPath(currPath);
                }
            }
        }
        else if (cmbGroup.getSelectedItem().toString().equals("Teachers"))
        {
            String permission = User.teacherString;
            tree.expandAll();
            for (int i = 0; i < tree.getRowCount(); i++)
            {
                TreePath currPath = tree.getPathForRow(i);
                String currentPath = currPath.getLastPathComponent().toString();
                if (previligeAvailable(currentPath, permission))
                {
                    tree.addCheckingPath(currPath);
                }
                else
                {
                    tree.removeCheckingPath(currPath);
                }
            }
        }
    }//GEN-LAST:event_cmbGroupActionPerformed

    private boolean previligeAvailable(String currentPath, String permissionString)
    {
        String[] prevList = permissionString.split("\\|");
        for (String prevItem : prevList)
        {
            String[] split = prevItem.split("-");
            String item = split[0];
            if (item.equals(currentPath) && (split[1].equalsIgnoreCase("True"))) //if the prevelige matches the row set check the path.
            {
                return true;
            }
        }
        return false;
    }

    private void populateLists()
    {
        tree.getCheckingModel().setCheckingMode(CheckingMode.PROPAGATE_PRESERVING_CHECK);
        //Loads the values from the database into the combo boxes.
        ArrayList<String> userGroups = User.getUserGroups();
        userGroups.add(0, "--- Select One ---");
        cmbGroup.setModel(new DefaultComboBoxModel(userGroups.toArray()));
        User.resetStaffLinks();
    }

    /**
     * This is called when the user changes the user group.
     * The subgroups are reloaded when the group is changed.
     * @param evt
     */
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
        if (!passedValidation())
        {
            Utilities.showWarningMessage(rootPane, warnings);
            return;
        }
        //Declare the variables.
        String username = txtUserName.getText().trim();
        String password = String.valueOf(txtPassword.getPassword()).trim();
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String group = cmbGroup.getSelectedItem().toString();
        //If the user was added successfully, it displays a message.
        String permissions = getPermissions();
        boolean userAdded = User.addUser(username, password, firstName, lastName, group, permissions);
        String usrID = User.getUserID(username);
        boolean linksSaved = User.saveStaffLinks(usrID);
        if (userAdded && linksSaved)
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
        remove(userTabbedPane);
        initComponents();
        populateLists();
    }

    @Action
    public void unlinkAccount()
    {
        for (int row : tblLinks.getSelectedRows())
        {
            String staffID = tblLinks.getValueAt(row, 0).toString();
            String staffCode = tblLinks.getValueAt(row, 1).toString();
            String staffName = tblLinks.getValueAt(row, 2).toString();
            User.removeStaffLink(staffID, staffCode, staffName);
        }
        loadLinkedAccounts();
    }

    @Action
    public void linkAccount()
    {
        FrmLinkStaff frmLinkStaff = new FrmLinkStaff(null, true);
        frmLinkStaff.setLocationRelativeTo(this);
        frmLinkStaff.setVisible(true);
        loadLinkedAccounts();
    }

    private void loadLinkedAccounts()
    {
        tblLinks.setModel(User.loadStaffLinks());
        TableColumnAdjuster tca = new TableColumnAdjuster(tblLinks);
        tca.adjustColumns();
    }

    private boolean passedValidation()
    {
        boolean passed = true;
        warnings = "";
        String username = txtUserName.getText().trim();
        String password = String.valueOf(txtPassword.getPassword()).trim();
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String group = cmbGroup.getSelectedItem().toString();
        if (username.isEmpty())
        {
            warnings += "Username cannot be empty.\n";
            passed = false;
        }
        if (password.isEmpty())
        {
            warnings += "Password cannot be empty.\n";
            passed = false;
        }
        if (firstName.isEmpty())
        {
            warnings += "First Name cannot be empty.\n";
            passed = false;
        }
        if (lastName.isEmpty())
        {
            warnings += "Last Name cannot be empty.\n";
            passed = false;
        }
        if (group.equals("--- Select One ---"))
        {
            warnings += "Kindly select a group to which the user will belong.\n";
            passed = false;
        }
        return passed;
    }

    private String getPermissions()
    {
        tree.expandAll();
        String permissions = "";
        for (int i = 1; i < tree.getRowCount(); i++)
        {
            TreePath currentPath = tree.getPathForRow(i);
            boolean isselected = tree.isPathChecked(currentPath);
            String currentPrev = (currentPath.getLastPathComponent() + "-" + isselected);
            permissions += currentPrev + "|";
        }
        return permissions;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbGroup;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdLink;
    private javax.swing.JButton cmdReset;
    private javax.swing.JButton cmdSave;
    private javax.swing.JButton cmdUnlink;
    private javax.swing.JPanel generalPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblGroup;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JPanel linksPanel;
    private javax.swing.JScrollPane previlegePane;
    private javax.swing.JTable tblLinks;
    private it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree tree;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JTabbedPane userTabbedPane;
    // End of variables declaration//GEN-END:variables
}
