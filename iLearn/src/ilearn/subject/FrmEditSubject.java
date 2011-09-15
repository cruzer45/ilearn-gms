/*
 * FrmAddSubject.java
 *
 * Created on Feb 22, 2011, 9:28:12 AM
 */
package ilearn.subject;

import ilearn.kernel.Utilities;
import ilearn.staff.Staff;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;

/**
 *
 * @author m.rogers
 */
public class FrmEditSubject extends javax.swing.JInternalFrame
{

    /** Creates new form FrmAddSubject */
    public FrmEditSubject()
    {
        initComponents();
        loadTeacherList();
        search();
    }

    @Action
    public void cancel()
    {
        this.dispose();
    }

    @Action
    public void save()
    {
        String subCode = txtSubjectCode.getText().trim(),
               subStaffCode = Staff.getStaffCodeFromName(cmbTeacher.getSelectedItem().toString()),
               subName = txtSubjectName.getText().trim(),
               subDescription = txtDescription.getText().trim(),
               subStatus = cmbStatus.getSelectedItem().toString(),
               subID = txtID.getText().trim(),
               subCredits = spinnerCredits.getValue().toString();
        if (Subject.updateSubject(subCode, subStaffCode, subName, subDescription, subCredits, subStatus, subID))
        {
            String message = "The Subject was successfully updated.\n"
                             + "Would you like to edit another?";
            int response = Utilities.showConfirmDialog(rootPane, message);
            if (response == JOptionPane.YES_OPTION)
            {
                resetForm();
                subjectTabbedPane.setSelectedIndex(subjectTabbedPane.getSelectedIndex() - 1);
            }
            else
            {
                this.dispose();
            }
        }
        else
        {
            String message = "An error occurred while trying to modify this subject.\n"
                             + "Kindly verify your information and try again.\n"
                             + "If the problem persists kindly contact your system administrator.";
            Utilities.showErrorMessage(rootPane, message);
        }
    }

    @Action
    public void next()
    {
        if (tblResults.getSelectedRow() != -1)
        {
            loadSubjectInfo();
            subjectTabbedPane.setSelectedIndex(subjectTabbedPane.getSelectedIndex() + 1);
        }
    }

    private void loadSubjectInfo()
    {
        String id = tblResults.getValueAt(tblResults.getSelectedRow(), 0).toString();
        ArrayList<String> detail = Subject.getSubjectDetails(id);
        Subject.getSubjectHours(detail.get(1));
        loadSelectedHours();
        txtID.setText(detail.get(0));
        txtSubjectCode.setText(detail.get(1));
        cmbTeacher.setSelectedItem(detail.get(2));
        txtSubjectName.setText(detail.get(3));
        txtDescription.setText(detail.get(4));
    }

    @Action
    public void search()
    {
        String criteria = txtSearch.getText().trim();
        tblResults.setModel(Subject.getSubjectList(criteria));
        lblResults2.setText(String.valueOf(tblResults.getRowCount()));
    }

    @Action
    public void resetForm()
    {
        loadSubjectInfo();
        search();
    }

    private void loadSelectedHours()
    {
        TimeList.setListData(Subject.getHours().toArray());
    }

    private void loadTeacherList()
    {
        ArrayList<String> teacherList = new ArrayList<String>();
        teacherList.add("--- Select One ---");
        teacherList.addAll(Staff.getStaffList());
        cmbTeacher.setModel(new DefaultComboBoxModel(teacherList.toArray()));
    }

    @Action
    public void add()
    {
        AddHour addHour = new AddHour(null, true);
        addHour.setLocationRelativeTo(this);
        addHour.setVisible(true);
        loadSelectedHours();
    }

    @Action
    public void remove()
    {
        if (TimeList.getSelectedIndex() != -1)
        {
            Subject.removeHour(TimeList.getSelectedValue().toString());
            loadSelectedHours();
        }
        else
        {
            String message = "Kindly select an item before clicking remove.";
            Utilities.showWarningMessage(rootPane, message);
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
        subjectTabbedPane = new javax.swing.JTabbedPane();
        searchPanel = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblResults = new javax.swing.JTable();
        lblResults1 = new javax.swing.JLabel();
        lblResults2 = new javax.swing.JLabel();
        cmdCancel1 = new javax.swing.JButton();
        cmdNext = new javax.swing.JButton();
        detailsPanel = new javax.swing.JPanel();
        lblSubjectCode = new javax.swing.JLabel();
        txtSubjectCode = new javax.swing.JTextField();
        lblSubjectName = new javax.swing.JLabel();
        txtSubjectName = new javax.swing.JTextField();
        lblDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        lblDays_Time = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TimeList = new javax.swing.JList();
        cmdCancel = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        cmdReset = new javax.swing.JButton();
        cmdAdd = new javax.swing.JButton();
        cmdRemove = new javax.swing.JButton();
        cmbTeacher = new javax.swing.JComboBox();
        lblTeacer = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lblID = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox();
        lblStatus = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        spinnerCredits = new javax.swing.JSpinner();
        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmEditSubject.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setToolTipText(resourceMap.getString("Form.toolTipText")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        subjectTabbedPane.setName("subjectTabbedPane"); // NOI18N
        searchPanel.setName("searchPanel"); // NOI18N
        lblSearch.setText(resourceMap.getString("lblSearch.text")); // NOI18N
        lblSearch.setName("lblSearch"); // NOI18N
        txtSearch.setText(resourceMap.getString("txtSearch.text")); // NOI18N
        txtSearch.setName("txtSearch"); // NOI18N
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmEditSubject.class, this);
        cmdSearch.setAction(actionMap.get("search")); // NOI18N
        cmdSearch.setText(resourceMap.getString("cmdSearch.text")); // NOI18N
        cmdSearch.setName("cmdSearch"); // NOI18N
        jScrollPane3.setName("jScrollPane3"); // NOI18N
        tblResults.setAutoCreateRowSorter(true);
        tblResults.setModel(new javax.swing.table.DefaultTableModel(
                                new Object [][]
                                {

                                },
                                new String []
                                {
                                    "ID", "Code", "Title"
                                }
                            ));
        tblResults.setName("tblResults"); // NOI18N
        tblResults.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tblResultsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblResults);
        tblResults.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblResults.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblResults.columnModel.title0")); // NOI18N
        tblResults.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblResults.columnModel.title1")); // NOI18N
        tblResults.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblResults.columnModel.title2")); // NOI18N
        lblResults1.setText(resourceMap.getString("lblResults1.text")); // NOI18N
        lblResults1.setName("lblResults1"); // NOI18N
        lblResults2.setText(resourceMap.getString("lblResults2.text")); // NOI18N
        lblResults2.setName("lblResults2"); // NOI18N
        cmdCancel1.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel1.setName("cmdCancel1"); // NOI18N
        cmdNext.setAction(actionMap.get("next")); // NOI18N
        cmdNext.setText(resourceMap.getString("cmdNext.text")); // NOI18N
        cmdNext.setName("cmdNext"); // NOI18N
        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchPanelLayout.createSequentialGroup()
                                          .addComponent(lblSearch)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdSearch))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchPanelLayout.createSequentialGroup()
                                          .addComponent(lblResults1)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(lblResults2)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                                          .addComponent(cmdNext)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel1)))
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
                      .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblResults1)
                                .addComponent(lblResults2)
                                .addComponent(cmdCancel1)
                                .addComponent(cmdNext))
                      .addContainerGap())
        );
        subjectTabbedPane.addTab(resourceMap.getString("searchPanel.TabConstraints.tabTitle"), resourceMap.getIcon("searchPanel.TabConstraints.tabIcon"), searchPanel); // NOI18N
        detailsPanel.setName("detailsPanel"); // NOI18N
        lblSubjectCode.setText(resourceMap.getString("lblSubjectCode.text")); // NOI18N
        lblSubjectCode.setName("lblSubjectCode"); // NOI18N
        txtSubjectCode.setToolTipText(resourceMap.getString("txtSubjectCode.toolTipText")); // NOI18N
        txtSubjectCode.setName("txtSubjectCode"); // NOI18N
        lblSubjectName.setText(resourceMap.getString("lblSubjectName.text")); // NOI18N
        lblSubjectName.setName("lblSubjectName"); // NOI18N
        txtSubjectName.setToolTipText(resourceMap.getString("txtSubjectName.toolTipText")); // NOI18N
        txtSubjectName.setName("txtSubjectName"); // NOI18N
        lblDescription.setText(resourceMap.getString("lblDescription.text")); // NOI18N
        lblDescription.setName("lblDescription"); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        txtDescription.setToolTipText(resourceMap.getString("txtDescription.toolTipText")); // NOI18N
        txtDescription.setName("txtDescription"); // NOI18N
        jScrollPane1.setViewportView(txtDescription);
        lblDays_Time.setText(resourceMap.getString("lblDays_Time.text")); // NOI18N
        lblDays_Time.setName("lblDays_Time"); // NOI18N
        jScrollPane2.setName("jScrollPane2"); // NOI18N
        TimeList.setToolTipText(resourceMap.getString("TimeList.toolTipText")); // NOI18N
        TimeList.setName("TimeList"); // NOI18N
        jScrollPane2.setViewportView(TimeList);
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N
        cmdSave.setAction(actionMap.get("save")); // NOI18N
        cmdSave.setName("cmdSave"); // NOI18N
        cmdReset.setAction(actionMap.get("resetForm")); // NOI18N
        cmdReset.setName("cmdReset"); // NOI18N
        cmdAdd.setAction(actionMap.get("add")); // NOI18N
        cmdAdd.setIcon(resourceMap.getIcon("cmdAdd.icon")); // NOI18N
        cmdAdd.setName("cmdAdd"); // NOI18N
        cmdRemove.setAction(actionMap.get("remove")); // NOI18N
        cmdRemove.setIcon(resourceMap.getIcon("cmdRemove.icon")); // NOI18N
        cmdRemove.setName("cmdRemove"); // NOI18N
        cmbTeacher.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbTeacher.setName("cmbTeacher"); // NOI18N
        lblTeacer.setText(resourceMap.getString("lblTeacer.text")); // NOI18N
        lblTeacer.setName("lblTeacer"); // NOI18N
        txtID.setEditable(false);
        txtID.setText(resourceMap.getString("txtID.text")); // NOI18N
        txtID.setName("txtID"); // NOI18N
        lblID.setText(resourceMap.getString("lblID.text")); // NOI18N
        lblID.setName("lblID"); // NOI18N
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        lblStatus.setText(resourceMap.getString("lblStatus.text")); // NOI18N
        lblStatus.setName("lblStatus"); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        spinnerCredits.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(3), Integer.valueOf(0), null, Integer.valueOf(1)));
        spinnerCredits.setName("spinnerCredits"); // NOI18N
        javax.swing.GroupLayout detailsPanelLayout = new javax.swing.GroupLayout(detailsPanel);
        detailsPanel.setLayout(detailsPanelLayout);
        detailsPanelLayout.setHorizontalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailsPanelLayout.createSequentialGroup()
                                          .addComponent(cmdReset)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                                          .addComponent(cmdSave)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel))
                                .addGroup(detailsPanelLayout.createSequentialGroup()
                                          .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                  .addComponent(lblStatus)
                                                  .addComponent(lblID)
                                                  .addComponent(lblSubjectCode)
                                                  .addComponent(lblSubjectName)
                                                  .addComponent(lblDescription)
                                                  .addComponent(jLabel1)
                                                  .addComponent(lblTeacer)
                                                  .addComponent(cmdRemove, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                                  .addComponent(lblDays_Time)
                                                  .addComponent(cmdAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                                  .addComponent(spinnerCredits, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                                  .addComponent(cmbStatus, javax.swing.GroupLayout.Alignment.TRAILING, 0, 307, Short.MAX_VALUE)
                                                  .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                                  .addComponent(cmbTeacher, javax.swing.GroupLayout.Alignment.TRAILING, 0, 307, Short.MAX_VALUE)
                                                  .addComponent(txtSubjectName, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                                  .addComponent(txtSubjectCode, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                                                  .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))))
                      .addContainerGap())
        );
        detailsPanelLayout.setVerticalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailsPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblID))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSubjectCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSubjectCode))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSubjectName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblSubjectName))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblDescription)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(spinnerCredits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTeacer))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(detailsPanelLayout.createSequentialGroup()
                                          .addComponent(lblDays_Time)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(cmdAdd)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(cmdRemove))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblStatus))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 41, Short.MAX_VALUE)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel)
                                .addComponent(cmdSave)
                                .addComponent(cmdReset))
                      .addContainerGap())
        );
        subjectTabbedPane.addTab(resourceMap.getString("detailsPanel.TabConstraints.tabTitle"), resourceMap.getIcon("detailsPanel.TabConstraints.tabIcon"), detailsPanel); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(subjectTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                      .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(subjectTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addContainerGap(15, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblResultsMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblResultsMouseClicked
    {
//GEN-HEADEREND:event_tblResultsMouseClicked
        if (evt.getClickCount() >= 2)
        {
            next();
        }
    }//GEN-LAST:event_tblResultsMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList TimeList;
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JComboBox cmbTeacher;
    private javax.swing.JButton cmdAdd;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdCancel1;
    private javax.swing.JButton cmdNext;
    private javax.swing.JButton cmdRemove;
    private javax.swing.JButton cmdReset;
    private javax.swing.JButton cmdSave;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JPanel detailsPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDays_Time;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblResults1;
    private javax.swing.JLabel lblResults2;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSubjectCode;
    private javax.swing.JLabel lblSubjectName;
    private javax.swing.JLabel lblTeacer;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JSpinner spinnerCredits;
    private javax.swing.JTabbedPane subjectTabbedPane;
    private javax.swing.JTable tblResults;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSubjectCode;
    private javax.swing.JTextField txtSubjectName;
    // End of variables declaration//GEN-END:variables
}
