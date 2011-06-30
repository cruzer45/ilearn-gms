/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmEditClass.java
 *
 * Created on Mar 28, 2011, 10:27:35 PM
 */
package ilearn.classes;

import ilearn.kernel.TableColumnAdjuster;
import ilearn.kernel.Utilities;
import ilearn.staff.Staff;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmEditClass extends javax.swing.JInternalFrame
{

    /** Creates new form FrmEditClass */
    public FrmEditClass()
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
        classTabbedPane = new javax.swing.JTabbedPane();
        classPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        classTable = new javax.swing.JTable();
        cmdCancel1 = new javax.swing.JButton();
        cmdNext = new javax.swing.JButton();
        lblTotalClasses2 = new javax.swing.JLabel();
        lblTotalClasses = new javax.swing.JLabel();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        detailsPanel = new javax.swing.JPanel();
        lblID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lblHomeRoom = new javax.swing.JLabel();
        txtClassCode = new javax.swing.JTextField();
        txtClassName = new javax.swing.JTextField();
        lblClassName = new javax.swing.JLabel();
        lblClassLevel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtClassDescription = new javax.swing.JTextArea();
        cmbHomeRoom = new javax.swing.JComboBox();
        lblClassDesc = new javax.swing.JLabel();
        lblClassCode = new javax.swing.JLabel();
        cmbClassLevel = new javax.swing.JComboBox();
        lblStatus = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox();
        cmdReset = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        cmdCancel = new javax.swing.JButton();
        subjectPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSubjects = new javax.swing.JTable();
        cmdDelete = new javax.swing.JButton();
        cmdAdd = new javax.swing.JButton();
        cmdSave1 = new javax.swing.JButton();
        cmdCancel2 = new javax.swing.JButton();
        lblsubjectCount = new javax.swing.JLabel();
        lblTotalSubjects = new javax.swing.JLabel();
        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(FrmEditClass.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setToolTipText(resourceMap.getString("Form.toolTipText")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        classTabbedPane.setName("classTabbedPane"); // NOI18N
        classPanel.setName("classPanel"); // NOI18N
        jScrollPane2.setName("jScrollPane2"); // NOI18N
        classTable.setAutoCreateRowSorter(true);
        classTable.setModel(new javax.swing.table.DefaultTableModel(
                                new Object [][]
                                {

                                },
                                new String []
                                {
                                    "ID", "Code", "Name", "Home Room", "Status"
                                }
                            ));
        classTable.setName("classTable"); // NOI18N
        classTable.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                classTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(classTable);
        classTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        classTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("classTable.columnModel.title0")); // NOI18N
        classTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("classTable.columnModel.title1")); // NOI18N
        classTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("classTable.columnModel.title2")); // NOI18N
        classTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("classTable.columnModel.title3")); // NOI18N
        classTable.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("classTable.columnModel.title4")); // NOI18N
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(FrmEditClass.class, this);
        cmdCancel1.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel1.setText(resourceMap.getString("cmdCancel1.text")); // NOI18N
        cmdCancel1.setName("cmdCancel1"); // NOI18N
        cmdNext.setAction(actionMap.get("next")); // NOI18N
        cmdNext.setText(resourceMap.getString("cmdNext.text")); // NOI18N
        cmdNext.setName("cmdNext"); // NOI18N
        lblTotalClasses2.setText(resourceMap.getString("lblTotalClasses2.text")); // NOI18N
        lblTotalClasses2.setName("lblTotalClasses2"); // NOI18N
        lblTotalClasses.setText(resourceMap.getString("lblTotalClasses.text")); // NOI18N
        lblTotalClasses.setName("lblTotalClasses"); // NOI18N
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
        cmdSearch.setAction(actionMap.get("search")); // NOI18N
        cmdSearch.setText(resourceMap.getString("cmdSearch.text")); // NOI18N
        cmdSearch.setName("cmdSearch"); // NOI18N
        javax.swing.GroupLayout classPanelLayout = new javax.swing.GroupLayout(classPanel);
        classPanel.setLayout(classPanelLayout);
        classPanelLayout.setHorizontalGroup(
            classPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, classPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(classPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, classPanelLayout.createSequentialGroup()
                                          .addComponent(lblTotalClasses)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(lblTotalClasses2)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                                          .addComponent(cmdNext)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel1))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, classPanelLayout.createSequentialGroup()
                                          .addComponent(lblSearch)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdSearch)))
                      .addContainerGap())
        );
        classPanelLayout.setVerticalGroup(
            classPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, classPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(classPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSearch)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmdSearch))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addGroup(classPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTotalClasses2)
                                .addComponent(lblTotalClasses)
                                .addComponent(cmdCancel1)
                                .addComponent(cmdNext))
                      .addContainerGap())
        );
        classTabbedPane.addTab(resourceMap.getString("classPanel.TabConstraints.tabTitle"), resourceMap.getIcon("classPanel.TabConstraints.tabIcon"), classPanel); // NOI18N
        detailsPanel.setName("detailsPanel"); // NOI18N
        lblID.setText(resourceMap.getString("lblID.text")); // NOI18N
        lblID.setName("lblID"); // NOI18N
        txtID.setEditable(false);
        txtID.setText(resourceMap.getString("txtID.text")); // NOI18N
        txtID.setName("txtID"); // NOI18N
        lblHomeRoom.setText(resourceMap.getString("lblHomeRoom.text")); // NOI18N
        lblHomeRoom.setName("lblHomeRoom"); // NOI18N
        txtClassCode.setToolTipText(resourceMap.getString("txtClassCode.toolTipText")); // NOI18N
        txtClassCode.setName("txtClassCode"); // NOI18N
        txtClassName.setToolTipText(resourceMap.getString("txtClassName.toolTipText")); // NOI18N
        txtClassName.setName("txtClassName"); // NOI18N
        lblClassName.setText(resourceMap.getString("lblClassName.text")); // NOI18N
        lblClassName.setName("lblClassName"); // NOI18N
        lblClassLevel.setText(resourceMap.getString("lblClassLevel.text")); // NOI18N
        lblClassLevel.setName("lblClassLevel"); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        txtClassDescription.setColumns(20);
        txtClassDescription.setLineWrap(true);
        txtClassDescription.setRows(5);
        txtClassDescription.setToolTipText(resourceMap.getString("txtClassDescription.toolTipText")); // NOI18N
        txtClassDescription.setWrapStyleWord(true);
        txtClassDescription.setName("txtClassDescription"); // NOI18N
        jScrollPane1.setViewportView(txtClassDescription);
        cmbHomeRoom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbHomeRoom.setToolTipText(resourceMap.getString("cmbHomeRoom.toolTipText")); // NOI18N
        cmbHomeRoom.setName("cmbHomeRoom"); // NOI18N
        lblClassDesc.setText(resourceMap.getString("lblClassDesc.text")); // NOI18N
        lblClassDesc.setName("lblClassDesc"); // NOI18N
        lblClassCode.setText(resourceMap.getString("lblClassCode.text")); // NOI18N
        lblClassCode.setName("lblClassCode"); // NOI18N
        cmbClassLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "First", "Second", "Third", "Fourth" }));
        cmbClassLevel.setToolTipText(resourceMap.getString("cmbClassLevel.toolTipText")); // NOI18N
        cmbClassLevel.setName("cmbClassLevel"); // NOI18N
        lblStatus.setText(resourceMap.getString("lblStatus.text")); // NOI18N
        lblStatus.setName("lblStatus"); // NOI18N
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmdReset.setAction(actionMap.get("resetForm")); // NOI18N
        cmdReset.setName("cmdReset"); // NOI18N
        cmdSave.setAction(actionMap.get("save")); // NOI18N
        cmdSave.setName("cmdSave"); // NOI18N
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N
        javax.swing.GroupLayout detailsPanelLayout = new javax.swing.GroupLayout(detailsPanel);
        detailsPanel.setLayout(detailsPanelLayout);
        detailsPanelLayout.setHorizontalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(detailsPanelLayout.createSequentialGroup()
                                          .addComponent(cmdReset)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                                          .addComponent(cmdSave)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel))
                                .addGroup(detailsPanelLayout.createSequentialGroup()
                                          .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(lblClassCode)
                                                  .addComponent(lblClassLevel)
                                                  .addComponent(lblClassName)
                                                  .addComponent(lblClassDesc)
                                                  .addComponent(lblHomeRoom)
                                                  .addComponent(lblStatus)
                                                  .addComponent(lblID))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                                  .addComponent(cmbHomeRoom, javax.swing.GroupLayout.Alignment.TRAILING, 0, 350, Short.MAX_VALUE)
                                                  .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                                  .addComponent(txtClassName, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                                  .addComponent(cmbClassLevel, 0, 350, Short.MAX_VALUE)
                                                  .addComponent(txtClassCode, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                                  .addComponent(cmbStatus, 0, 350, Short.MAX_VALUE))))
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
                                .addComponent(lblClassCode)
                                .addComponent(txtClassCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblClassLevel)
                                .addComponent(cmbClassLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblClassName)
                                .addComponent(txtClassName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblClassDesc)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbHomeRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblHomeRoom))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblStatus)
                                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 120, Short.MAX_VALUE)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel)
                                .addComponent(cmdSave)
                                .addComponent(cmdReset))
                      .addContainerGap())
        );
        classTabbedPane.addTab(resourceMap.getString("detailsPanel.TabConstraints.tabTitle"), resourceMap.getIcon("detailsPanel.TabConstraints.tabIcon"), detailsPanel); // NOI18N
        subjectPanel.setName("subjectPanel"); // NOI18N
        jScrollPane3.setName("jScrollPane3"); // NOI18N
        tblSubjects.setAutoCreateRowSorter(true);
        tblSubjects.setModel(new javax.swing.table.DefaultTableModel(
                                 new Object [][]
                                 {

                                 },
                                 new String []
                                 {
                                     "ID", "Code", "Title", "Teacher"
                                 }
                             ));
        tblSubjects.setName("tblSubjects"); // NOI18N
        jScrollPane3.setViewportView(tblSubjects);
        cmdDelete.setAction(actionMap.get("delete")); // NOI18N
        cmdDelete.setName("cmdDelete"); // NOI18N
        cmdAdd.setAction(actionMap.get("add")); // NOI18N
        cmdAdd.setName("cmdAdd"); // NOI18N
        cmdSave1.setAction(actionMap.get("save")); // NOI18N
        cmdSave1.setName("cmdSave1"); // NOI18N
        cmdCancel2.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel2.setName("cmdCancel2"); // NOI18N
        lblsubjectCount.setText(resourceMap.getString("lblsubjectCount.text")); // NOI18N
        lblsubjectCount.setName("lblsubjectCount"); // NOI18N
        lblTotalSubjects.setText(resourceMap.getString("lblTotalSubjects.text")); // NOI18N
        lblTotalSubjects.setName("lblTotalSubjects"); // NOI18N
        javax.swing.GroupLayout subjectPanelLayout = new javax.swing.GroupLayout(subjectPanel);
        subjectPanel.setLayout(subjectPanelLayout);
        subjectPanelLayout.setHorizontalGroup(
            subjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subjectPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(subjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subjectPanelLayout.createSequentialGroup()
                                          .addGroup(subjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                  .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                                                  .addGroup(subjectPanelLayout.createSequentialGroup()
                                                          .addComponent(cmdSave1)
                                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                          .addComponent(cmdCancel2)))
                                          .addContainerGap())
                                .addGroup(subjectPanelLayout.createSequentialGroup()
                                          .addComponent(lblsubjectCount)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(lblTotalSubjects)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                                          .addComponent(cmdAdd)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdDelete)
                                          .addGap(12, 12, 12))))
        );
        subjectPanelLayout.setVerticalGroup(
            subjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subjectPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(subjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdDelete)
                                .addComponent(cmdAdd)
                                .addComponent(lblsubjectCount)
                                .addComponent(lblTotalSubjects))
                      .addGap(24, 24, 24)
                      .addGroup(subjectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel2)
                                .addComponent(cmdSave1))
                      .addContainerGap())
        );
        classTabbedPane.addTab("Subjects", resourceMap.getIcon("subjectPanel.TabConstraints.tabIcon"), subjectPanel); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(classTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                                .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 466, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(classTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                                .addContainerGap()))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void classTableMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_classTableMouseClicked
    {
//GEN-HEADEREND:event_classTableMouseClicked
        if (evt.getClickCount() >= 2)
        {
            next();
        }
    }//GEN-LAST:event_classTableMouseClicked

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtSearchKeyPressed
    {
//GEN-HEADEREND:event_txtSearchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            search();
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void populateLists()
    {
        //Set the home room list
        ArrayList<String> staffList = Staff.getStaffList();
        staffList.add(0, "--- Select One ---");
        cmbHomeRoom.setModel(new DefaultComboBoxModel(staffList.toArray()));
        //Load the table
        classTable.setModel(Classes.getClassTableModel());
        lblTotalClasses2.setText(String.valueOf(classTable.getRowCount()));
    }

    @Action
    public void cancel()
    {
        Utilities.showCancelScreen(this);
    }

    @Action
    public void resetForm()
    {
        populateLists();
        loadClassInfo();
    }

    private void loadClassInfo()
    {
        if (classTable.getSelectedRow() != -1)
        {
            //Get the class information
            String selectedClass = String.valueOf(classTable.getValueAt(classTable.getSelectedRow(), 0));
            ArrayList<String> classInfo = Classes.getClassInfo(selectedClass);
            txtID.setText(selectedClass);
            txtClassCode.setText(classInfo.get(0));
            txtClassName.setText(classInfo.get(1));
            txtClassDescription.setText(classInfo.get(2));
            cmbClassLevel.setSelectedItem(classInfo.get(3));
            cmbHomeRoom.setSelectedItem(classInfo.get(4));
            cmbStatus.setSelectedItem(classInfo.get(5));
            //Get the subject information
            tblSubjects.setModel(Classes.getSubjects(classInfo.get(0)));
            lblTotalSubjects.setText(String.valueOf(tblSubjects.getRowCount()));
            TableColumnAdjuster tca = new TableColumnAdjuster(tblSubjects);
            tca.adjustColumns();
        }
    }

    @Action
    public void next()
    {
        if (classTable.getSelectedRow() != -1)
        {
            loadClassInfo();
            classTabbedPane.setSelectedIndex(classTabbedPane.getSelectedIndex() + 1);
        }
    }

    @Action
    public void save()
    {
        String code = txtClassCode.getText().trim();
        String name = txtClassName.getText().trim();
        String description = txtClassDescription.getText().trim();
        String level = cmbClassLevel.getSelectedItem().toString();
        String homeRoom = cmbHomeRoom.getSelectedItem().toString();
        String status = cmbStatus.getSelectedItem().toString();
        String id = txtID.getText().trim();
        if (Classes.updateClass(code, name, description, level, homeRoom, status, id))
        {
            //reload the list
            populateLists();
            String message = "Succesfully updated the class' information.";
            Utilities.showInfoMessage(rootPane, message);
        }
        else
        {
            String message = "An error occurred while updating the class' information.\n"
                             + "Kindly verify your information and try again.\n"
                             + "If the problem persists, kindly contact your system administrator.";
            Utilities.showErrorMessage(rootPane, message);
        }
    }

    @Action
    public void search()
    {
        String criteria = txtSearch.getText().trim();
        classTable.setModel(Classes.getClassTableModel(criteria));
        lblTotalClasses2.setText(String.valueOf(classTable.getRowCount()));
    }

    @Action
    public void delete()
    {
        if (tblSubjects.getSelectedRow() != -1)
        {
            String code = tblSubjects.getValueAt(tblSubjects.getSelectedRow(), 1).toString();
            Classes.removeSubject(code);
            loadSubjects();
        }
        else
        {
            String message = "Kindly select a subject to remove.";
            Utilities.showWarningMessage(rootPane, message);
        }
    }

    @Action
    public void add()
    {
        DialogAddSubject dialogAddSubject = new DialogAddSubject(null, true);
        dialogAddSubject.setLocationRelativeTo(this);
        dialogAddSubject.setVisible(true);
        loadSubjects();
    }

    private void loadSubjects()
    {
        tblSubjects.setModel(Classes.getSubjects());
        TableColumnAdjuster tca = new TableColumnAdjuster(tblSubjects);
        tca.adjustColumns();
        lblTotalSubjects.setText(String.valueOf(tblSubjects.getRowCount()));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel classPanel;
    private javax.swing.JTabbedPane classTabbedPane;
    private javax.swing.JTable classTable;
    private javax.swing.JComboBox cmbClassLevel;
    private javax.swing.JComboBox cmbHomeRoom;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JButton cmdAdd;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdCancel1;
    private javax.swing.JButton cmdCancel2;
    private javax.swing.JButton cmdDelete;
    private javax.swing.JButton cmdNext;
    private javax.swing.JButton cmdReset;
    private javax.swing.JButton cmdSave;
    private javax.swing.JButton cmdSave1;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JPanel detailsPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblClassCode;
    private javax.swing.JLabel lblClassDesc;
    private javax.swing.JLabel lblClassLevel;
    private javax.swing.JLabel lblClassName;
    private javax.swing.JLabel lblHomeRoom;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTotalClasses;
    private javax.swing.JLabel lblTotalClasses2;
    private javax.swing.JLabel lblTotalSubjects;
    private javax.swing.JLabel lblsubjectCount;
    private javax.swing.JPanel subjectPanel;
    private javax.swing.JTable tblSubjects;
    private javax.swing.JTextField txtClassCode;
    private javax.swing.JTextArea txtClassDescription;
    private javax.swing.JTextField txtClassName;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
