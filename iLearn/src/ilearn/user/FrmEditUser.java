/*
 * FrmEditUser.java
 *
 * Created on Mar 7, 2011, 7:16:59 PM
 */
package ilearn.user;

import ilearn.kernel.TableColumnAdjuster;
import ilearn.kernel.Utilities;
import javax.swing.DefaultComboBoxModel;
import javax.swing.tree.TreePath;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmEditUser extends javax.swing.JInternalFrame
{

    String warnings = "";

    /** Creates new form FrmEditUser */
    public FrmEditUser()
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
        wrapperPanel = new javax.swing.JPanel();
        userTabbedPane = new javax.swing.JTabbedPane();
        userPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        cmdNext = new javax.swing.JButton();
        detailsPanel = new javax.swing.JPanel();
        lblID = new javax.swing.JLabel();
        lblFirstName = new javax.swing.JLabel();
        lblLastName = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtFirstName = new javax.swing.JTextField();
        txtUserName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        lblGroup = new javax.swing.JLabel();
        cmbGroup = new javax.swing.JComboBox();
        lblStatis = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox();
        lblTimeout = new javax.swing.JLabel();
        spinnerTimeout = new javax.swing.JSpinner();
        linksPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLinks = new javax.swing.JTable();
        cmdUnlink = new javax.swing.JButton();
        cmdLink = new javax.swing.JButton();
        previlegePane = new javax.swing.JScrollPane();
        tree = new it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree();
        cmdReset = new javax.swing.JButton();
        cmdCancel2 = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmEditUser.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setToolTipText(resourceMap.getString("Form.toolTipText")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        wrapperPanel.setName("wrapperPanel"); // NOI18N
        userTabbedPane.setName("userTabbedPane"); // NOI18N
        userPanel.setName("userPanel"); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        tblUsers.setAutoCreateRowSorter(true);
        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
                              new Object [][]
                              {

                              },
                              new String []
                              {
                                  "ID", "Name", "User Name", "Status"
                              }
                          )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, true
            };
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        tblUsers.setToolTipText(resourceMap.getString("tblUsers.toolTipText")); // NOI18N
        tblUsers.setName("tblUsers"); // NOI18N
        tblUsers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblUsers.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tblUsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsers);
        tblUsers.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblUsers.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblUsers.columnModel.title0")); // NOI18N
        tblUsers.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblUsers.columnModel.title1")); // NOI18N
        tblUsers.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblUsers.columnModel.title2")); // NOI18N
        tblUsers.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblUsers.columnModel.title3")); // NOI18N
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmEditUser.class, this);
        cmdNext.setAction(actionMap.get("next")); // NOI18N
        cmdNext.setName("cmdNext"); // NOI18N
        javax.swing.GroupLayout userPanelLayout = new javax.swing.GroupLayout(userPanel);
        userPanel.setLayout(userPanelLayout);
        userPanelLayout.setHorizontalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                                .addComponent(cmdNext))
                      .addContainerGap())
        );
        userPanelLayout.setVerticalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addComponent(cmdNext)
                      .addContainerGap())
        );
        userTabbedPane.addTab("User", resourceMap.getIcon("userPanel.TabConstraints.tabIcon"), userPanel); // NOI18N
        detailsPanel.setName("detailsPanel"); // NOI18N
        lblID.setText(resourceMap.getString("lblID.text")); // NOI18N
        lblID.setName("lblID"); // NOI18N
        lblFirstName.setText(resourceMap.getString("lblFirstName.text")); // NOI18N
        lblFirstName.setName("lblFirstName"); // NOI18N
        lblLastName.setText(resourceMap.getString("lblLastName.text")); // NOI18N
        lblLastName.setName("lblLastName"); // NOI18N
        lblUserName.setText(resourceMap.getString("lblUserName.text")); // NOI18N
        lblUserName.setName("lblUserName"); // NOI18N
        lblPassword.setText(resourceMap.getString("lblPassword.text")); // NOI18N
        lblPassword.setName("lblPassword"); // NOI18N
        txtID.setEditable(false);
        txtID.setText(resourceMap.getString("txtID.text")); // NOI18N
        txtID.setToolTipText(resourceMap.getString("txtID.toolTipText")); // NOI18N
        txtID.setName("txtID"); // NOI18N
        txtFirstName.setToolTipText(resourceMap.getString("txtFirstName.toolTipText")); // NOI18N
        txtFirstName.setName("txtFirstName"); // NOI18N
        txtUserName.setToolTipText(resourceMap.getString("txtUserName.toolTipText")); // NOI18N
        txtUserName.setName("txtUserName"); // NOI18N
        txtLastName.setToolTipText(resourceMap.getString("txtLastName.toolTipText")); // NOI18N
        txtLastName.setName("txtLastName"); // NOI18N
        txtPassword.setToolTipText(resourceMap.getString("txtPassword.toolTipText")); // NOI18N
        txtPassword.setName("txtPassword"); // NOI18N
        lblGroup.setText(resourceMap.getString("lblGroup.text")); // NOI18N
        lblGroup.setName("lblGroup"); // NOI18N
        cmbGroup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbGroup.setToolTipText(resourceMap.getString("cmbGroup.toolTipText")); // NOI18N
        cmbGroup.setName("cmbGroup"); // NOI18N
        lblStatis.setText(resourceMap.getString("lblStatis.text")); // NOI18N
        lblStatis.setName("lblStatis"); // NOI18N
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Disabled", " " }));
        cmbStatus.setToolTipText(resourceMap.getString("cmbStatus.toolTipText")); // NOI18N
        cmbStatus.setName("cmbStatus"); // NOI18N
        lblTimeout.setText(resourceMap.getString("lblTimeout.text")); // NOI18N
        lblTimeout.setName("lblTimeout"); // NOI18N
        spinnerTimeout.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(15), Integer.valueOf(0), null, Integer.valueOf(1)));
        spinnerTimeout.setName("spinnerTimeout"); // NOI18N
        javax.swing.GroupLayout detailsPanelLayout = new javax.swing.GroupLayout(detailsPanel);
        detailsPanel.setLayout(detailsPanelLayout);
        detailsPanelLayout.setHorizontalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblLastName)
                                .addComponent(lblUserName)
                                .addComponent(lblFirstName)
                                .addComponent(lblPassword)
                                .addComponent(lblGroup)
                                .addComponent(lblID)
                                .addComponent(lblTimeout)
                                .addComponent(lblStatis))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbStatus, 0, 260, Short.MAX_VALUE)
                                .addComponent(spinnerTimeout, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                .addComponent(cmbGroup, 0, 260, Short.MAX_VALUE)
                                .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                .addComponent(txtLastName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                .addComponent(txtFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                      .addContainerGap())
        );
        detailsPanelLayout.setVerticalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblID)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblFirstName)
                                .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblLastName)
                                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblUserName)
                                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblPassword)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblGroup)
                                .addComponent(cmbGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(spinnerTimeout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTimeout))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblStatis)
                                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addContainerGap(62, Short.MAX_VALUE))
        );
        userTabbedPane.addTab("Details", resourceMap.getIcon("detailsPanel.TabConstraints.tabIcon"), detailsPanel); // NOI18N
        linksPanel.setName("linksPanel"); // NOI18N
        jScrollPane2.setName("jScrollPane2"); // NOI18N
        tblLinks.setAutoCreateRowSorter(true);
        tblLinks.setModel(new javax.swing.table.DefaultTableModel(
                              new Object [][]
                              {

                              },
                              new String []
                              {
                                  "ID", "Code", "Name"
                              }
                          ));
        tblLinks.setColumnSelectionAllowed(true);
        tblLinks.setName("tblLinks"); // NOI18N
        jScrollPane2.setViewportView(tblLinks);
        tblLinks.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblLinks.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblLinks.columnModel.title0")); // NOI18N
        tblLinks.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblLinks.columnModel.title2")); // NOI18N
        tblLinks.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblLinks.columnModel.title1")); // NOI18N
        cmdUnlink.setAction(actionMap.get("unlinkAccount")); // NOI18N
        cmdUnlink.setName("cmdUnlink"); // NOI18N
        cmdLink.setAction(actionMap.get("linkAccount")); // NOI18N
        cmdLink.setName("cmdLink"); // NOI18N
        javax.swing.GroupLayout linksPanelLayout = new javax.swing.GroupLayout(linksPanel);
        linksPanel.setLayout(linksPanelLayout);
        linksPanelLayout.setHorizontalGroup(
            linksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, linksPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(linksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
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
                      .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addGroup(linksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdUnlink)
                                .addComponent(cmdLink))
                      .addContainerGap())
        );
        userTabbedPane.addTab("Links", resourceMap.getIcon("linksPanel.TabConstraints.tabIcon"), linksPanel); // NOI18N
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
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Mid Term Reports");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Mid Term Class Ranking");
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
        userTabbedPane.addTab("Permissions", resourceMap.getIcon("previlegePane.TabConstraints.tabIcon"), previlegePane); // NOI18N
        cmdReset.setAction(actionMap.get("reset")); // NOI18N
        cmdReset.setText(resourceMap.getString("cmdReset.text")); // NOI18N
        cmdReset.setName("cmdReset"); // NOI18N
        cmdCancel2.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel2.setName("cmdCancel2"); // NOI18N
        cmdSave.setAction(actionMap.get("save")); // NOI18N
        cmdSave.setName("cmdSave"); // NOI18N
        javax.swing.GroupLayout wrapperPanelLayout = new javax.swing.GroupLayout(wrapperPanel);
        wrapperPanel.setLayout(wrapperPanelLayout);
        wrapperPanelLayout.setHorizontalGroup(
            wrapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapperPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(wrapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(userTabbedPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                                .addGroup(wrapperPanelLayout.createSequentialGroup()
                                          .addComponent(cmdReset)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                                          .addComponent(cmdSave)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel2)))
                      .addContainerGap())
        );
        wrapperPanelLayout.setVerticalGroup(
            wrapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapperPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(userTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addGroup(wrapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdReset)
                                .addComponent(cmdCancel2)
                                .addComponent(cmdSave))
                      .addContainerGap())
        );
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wrapperPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wrapperPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        setBounds(0, 0, 400, 400);
    }// </editor-fold>//GEN-END:initComponents

    private void populateLists()
    {
        tblUsers.setModel(User.getUserList());
        TableColumnAdjuster tca = new TableColumnAdjuster(tblUsers);
        tca.adjustColumns();
        //Loads the values from the database into the combo boxes.
        cmbGroup.setModel(new DefaultComboBoxModel(User.getUserGroups().toArray()));
    }

    private void tblUsersMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblUsersMouseClicked
    {
//GEN-HEADEREND:event_tblUsersMouseClicked
        if (tblUsers.getSelectedRow() != -1 && (evt.getClickCount() >= 2))
        {
            loadSelectedUser();
            next();
        }
    }//GEN-LAST:event_tblUsersMouseClicked

    private void loadSelectedUser()
    {
        if (tblUsers.getSelectedRow() != -1)
        {
            String selectedUser = String.valueOf(tblUsers.getValueAt(tblUsers.getSelectedRow(), 0));
            String[] userInfo = User.getUserInfo(selectedUser);
            txtID.setText(selectedUser);
            txtFirstName.setText(userInfo[0]);
            txtLastName.setText(userInfo[1]);
            txtUserName.setText(userInfo[2]);
            txtPassword.setText(userInfo[3]);
            cmbGroup.setSelectedItem(userInfo[4]);
            cmbStatus.setSelectedItem(userInfo[5]);
            spinnerTimeout.setValue(Integer.valueOf(userInfo[6]));
            loadPermissions(userInfo[7].toString());
            User.getStaffLinks(selectedUser);
            tblLinks.setModel(User.loadStaffLinks());
        }
        else
        {
            String message = "Kindly select a user before proceeding.";
            Utilities.showWarningMessage(rootPane, message);
        }
    }

    private void loadPermissions(String permission)
    {
        tree.expandAll();
        for (int i = 0; i < tree.getRowCount(); i++)
        {
            TreePath currPath = tree.getPathForRow(i);
            String currentPath = currPath.getLastPathComponent().toString();
            if (User.previligeAvailable(currentPath, permission))
            {
                tree.addCheckingPath(currPath);
            }
            else
            {
                tree.removeCheckingPath(currPath);
            }
        }
    }

    @Action
    public void cancel()
    {
        Utilities.showCancelScreen(this);
    }

    @Action
    public void next()
    {
        if (userTabbedPane.getSelectedIndex() == 0)
        {
            loadSelectedUser();
        }
        userTabbedPane.setSelectedIndex(userTabbedPane.getSelectedIndex() + 1);
    }

    @Action
    public void save()
    {
        if (!passedValidation())
        {
            Utilities.showWarningMessage(rootPane, warnings);
            return;
        }
        String ID = txtID.getText();
        String username = txtUserName.getText().trim();
        String password = String.valueOf(txtPassword.getPassword()).trim();
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String group = cmbGroup.getSelectedItem().toString();
        String status = cmbStatus.getSelectedItem().toString();
        String timeout = spinnerTimeout.getValue().toString();
        String usrPermission = getPermissions();
        boolean userUpdated = User.updateUser(ID, firstName, lastName, username, password, group, status, timeout, usrPermission);
        boolean linksSaved = User.saveStaffLinks(ID);
        if (userUpdated && linksSaved)
        {
            tblUsers.setModel(User.getUserList());
            String message = "The user's information was successfully updated.";
            Utilities.showInfoMessage(rootPane, message);
        }
        else
        {
            String message = "An error occurred while updating the user's information.\n"
                             + "Kindly verify your information and try again.\n"
                             + "If the problem persists, kindly contact your system administrator.";
            Utilities.showErrorMessage(rootPane, message);
        }
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
        return passed;
    }

    @Action
    public void reset()
    {
        remove(wrapperPanel);
        initComponents();
        populateLists();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbGroup;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JButton cmdCancel2;
    private javax.swing.JButton cmdLink;
    private javax.swing.JButton cmdNext;
    private javax.swing.JButton cmdReset;
    private javax.swing.JButton cmdSave;
    private javax.swing.JButton cmdUnlink;
    private javax.swing.JPanel detailsPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblGroup;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblStatis;
    private javax.swing.JLabel lblTimeout;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JPanel linksPanel;
    private javax.swing.JScrollPane previlegePane;
    private javax.swing.JSpinner spinnerTimeout;
    private javax.swing.JTable tblLinks;
    private javax.swing.JTable tblUsers;
    private it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree tree;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JPanel userPanel;
    private javax.swing.JTabbedPane userTabbedPane;
    private javax.swing.JPanel wrapperPanel;
    // End of variables declaration//GEN-END:variables
}
