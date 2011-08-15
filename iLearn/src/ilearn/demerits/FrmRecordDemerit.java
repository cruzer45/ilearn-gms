/*
 * FrmRecordDemerit.java
 *
 * Created on Jul 13, 2011, 8:44:43 PM
 */
package ilearn.demerits;

import ilearn.kernel.TableColumnAdjuster;
import ilearn.kernel.Utilities;
import ilearn.staff.Staff;
import ilearn.student.Student;
import ilearn.term.Term;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmRecordDemerit extends javax.swing.JInternalFrame
{

    String warnings = "";

    /** Creates new form FrmRecordDemerit */
    public FrmRecordDemerit()
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
        demeritTabbedPane = new javax.swing.JTabbedPane();
        searchPanel = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSearch = new javax.swing.JTable();
        cmdCancel = new javax.swing.JButton();
        cmdNext = new javax.swing.JButton();
        demeritPanel = new javax.swing.JPanel();
        lblID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblDemerit = new javax.swing.JLabel();
        spinnerDemerits = new javax.swing.JSpinner();
        lblRemarks = new javax.swing.JLabel();
        cmdCancel2 = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        cmbRemarks = new javax.swing.JComboBox();
        lblDate = new javax.swing.JLabel();
        calDate = new com.toedter.calendar.JDateChooser();
        cmbStaff = new javax.swing.JComboBox();
        lblStaff = new javax.swing.JLabel();
        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmRecordDemerit.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        demeritTabbedPane.setName("demeritTabbedPane"); // NOI18N
        searchPanel.setName("searchPanel"); // NOI18N
        lblSearch.setText(resourceMap.getString("lblSearch.text")); // NOI18N
        lblSearch.setName("lblSearch"); // NOI18N
        txtSearch.setText(resourceMap.getString("txtSearch.text")); // NOI18N
        txtSearch.setName("txtSearch"); // NOI18N
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmRecordDemerit.class, this);
        cmdSearch.setAction(actionMap.get("search")); // NOI18N
        cmdSearch.setText(resourceMap.getString("cmdSearch.text")); // NOI18N
        cmdSearch.setName("cmdSearch"); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        tblSearch.setAutoCreateRowSorter(true);
        tblSearch.setModel(new javax.swing.table.DefaultTableModel(
                               new Object [][]
                               {

                               },
                               new String []
                               {
                                   "ID", "Name", "Class"
                               }
                           ));
        tblSearch.setName("tblSearch"); // NOI18N
        tblSearch.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tblSearchMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSearch);
        tblSearch.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSearch.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblSearch.columnModel.title0")); // NOI18N
        tblSearch.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblSearch.columnModel.title1")); // NOI18N
        tblSearch.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblSearch.columnModel.title2")); // NOI18N
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setText(resourceMap.getString("cmdCancel.text")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N
        cmdNext.setAction(actionMap.get("next")); // NOI18N
        cmdNext.setText(resourceMap.getString("cmdNext.text")); // NOI18N
        cmdNext.setName("cmdNext"); // NOI18N
        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(searchPanelLayout.createSequentialGroup()
                                          .addComponent(lblSearch)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdSearch))
                                .addGroup(searchPanelLayout.createSequentialGroup()
                                          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                                          .addGap(1, 1, 1))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                                          .addComponent(cmdNext)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel)
                                .addComponent(cmdNext))
                      .addContainerGap())
        );
        demeritTabbedPane.addTab(resourceMap.getString("searchPanel.TabConstraints.tabTitle"), resourceMap.getIcon("searchPanel.TabConstraints.tabIcon"), searchPanel); // NOI18N
        demeritPanel.setName("demeritPanel"); // NOI18N
        lblID.setText(resourceMap.getString("lblID.text")); // NOI18N
        lblID.setName("lblID"); // NOI18N
        txtID.setEditable(false);
        txtID.setText(resourceMap.getString("txtID.text")); // NOI18N
        txtID.setName("txtID"); // NOI18N
        lblName.setText(resourceMap.getString("lblName.text")); // NOI18N
        lblName.setName("lblName"); // NOI18N
        txtName.setEditable(false);
        txtName.setText(resourceMap.getString("txtName.text")); // NOI18N
        txtName.setName("txtName"); // NOI18N
        lblDemerit.setText(resourceMap.getString("lblDemerit.text")); // NOI18N
        lblDemerit.setName("lblDemerit"); // NOI18N
        spinnerDemerits.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        spinnerDemerits.setName("spinnerDemerits"); // NOI18N
        lblRemarks.setText(resourceMap.getString("lblRemarks.text")); // NOI18N
        lblRemarks.setName("lblRemarks"); // NOI18N
        cmdCancel2.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel2.setText(resourceMap.getString("cmdCancel2.text")); // NOI18N
        cmdCancel2.setName("cmdCancel2"); // NOI18N
        cmdSave.setAction(actionMap.get("save")); // NOI18N
        cmdSave.setText(resourceMap.getString("cmdSave.text")); // NOI18N
        cmdSave.setName("cmdSave"); // NOI18N
        cmbRemarks.setEditable(true);
        cmbRemarks.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbRemarks.setName("cmbRemarks"); // NOI18N
        lblDate.setText(resourceMap.getString("lblDate.text")); // NOI18N
        lblDate.setName("lblDate"); // NOI18N
        calDate.setName("calDate"); // NOI18N
        cmbStaff.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbStaff.setName("cmbStaff"); // NOI18N
        lblStaff.setText(resourceMap.getString("lblStaff.text")); // NOI18N
        lblStaff.setName("lblStaff"); // NOI18N
        javax.swing.GroupLayout demeritPanelLayout = new javax.swing.GroupLayout(demeritPanel);
        demeritPanel.setLayout(demeritPanelLayout);
        demeritPanelLayout.setHorizontalGroup(
            demeritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(demeritPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(demeritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, demeritPanelLayout.createSequentialGroup()
                                          .addComponent(cmdSave)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel2))
                                .addGroup(demeritPanelLayout.createSequentialGroup()
                                          .addGroup(demeritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(lblID)
                                                  .addComponent(lblName)
                                                  .addComponent(lblDate)
                                                  .addComponent(lblStaff)
                                                  .addComponent(lblDemerit)
                                                  .addComponent(lblRemarks))
                                          .addGap(1, 1, 1)
                                          .addGroup(demeritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(spinnerDemerits, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                                                  .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                                                  .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                                                  .addComponent(calDate, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                                                  .addComponent(cmbStaff, 0, 328, Short.MAX_VALUE)
                                                  .addComponent(cmbRemarks, javax.swing.GroupLayout.Alignment.TRAILING, 0, 328, Short.MAX_VALUE))))
                      .addContainerGap())
        );
        demeritPanelLayout.setVerticalGroup(
            demeritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(demeritPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(demeritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblID)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(demeritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblName)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(demeritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(calDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDate))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(demeritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblStaff))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(demeritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblDemerit)
                                .addComponent(spinnerDemerits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(demeritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblRemarks)
                                .addComponent(cmbRemarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                      .addGroup(demeritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel2)
                                .addComponent(cmdSave))
                      .addContainerGap())
        );
        demeritTabbedPane.addTab(resourceMap.getString("demeritPanel.TabConstraints.tabTitle"), resourceMap.getIcon("demeritPanel.TabConstraints.tabIcon"), demeritPanel); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(demeritTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                      .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(demeritTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                      .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSearchMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblSearchMouseClicked
    {
//GEN-HEADEREND:event_tblSearchMouseClicked
        if (evt.getClickCount() >= 2 && tblSearch.getSelectedRow() != -1)
        {
            next();
        }
    }//GEN-LAST:event_tblSearchMouseClicked

    private void populateLists()
    {
        ArrayList<String> demeritReasons = Demerits.getDemeritReasons();
        demeritReasons.add(0, " ");
        cmbRemarks.setModel(new DefaultComboBoxModel(demeritReasons.toArray()));
        ArrayList<String> staffList = Staff.getStaffList();
        staffList.add(0, "--- Select One ---");
        cmbStaff.setModel(new DefaultComboBoxModel(staffList.toArray()));
        calDate.setDate(new Date());
    }

    @Action
    public void search()
    {
        String criteria = txtSearch.getText().trim();
        tblSearch.setModel(Student.searchStudents(criteria));
        TableColumnAdjuster tca = new TableColumnAdjuster(tblSearch);
        tca.adjustColumns();
    }

    @Action
    public void cancel()
    {
        Utilities.showCancelScreen(this);
    }

    @Action
    public void save()
    {
        if (!passesValidation())
        {
            Utilities.showWarningMessage(rootPane, warnings);
            return;
        }
        String demStuID = txtID.getText().trim();
        String demDate = Utilities.YMD_Formatter.format(calDate.getDate());
        String demStaCode = Staff.getStaffCodeFromName(cmbStaff.getSelectedItem().toString());
        String demClsCode = Student.getStudentClass(demStuID);
        String demTermID = Term.getCurrentTerm();
        String demerits = spinnerDemerits.getValue().toString();
        String demRemarks = cmbRemarks.getSelectedItem().toString().trim();
        boolean demeritAdded = Demerits.addDemerit(demStuID, demDate, demStaCode, demClsCode, demTermID, demerits, demRemarks);
        if (demeritAdded)
        {
            String message = "The demerit was successfully recorded. \n"
                             + "Would you like to record another?";
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
            String message = "An error occurred while trying to record this demerit.\n"
                             + "Kindly verify your information and try again.";
            Utilities.showErrorMessage(rootPane, message);
        }
    }

    @Action
    public void next()
    {
        if (tblSearch.getSelectedRow() != -1)
        {
            loadInfo();
            demeritTabbedPane.setSelectedIndex(demeritTabbedPane.getSelectedIndex() + 1);
        }
        else
        {
            String message = "Kindly select a student before proceeding.";
            Utilities.showWarningMessage(rootPane, message);
        }
    }

    private void loadInfo()
    {
        String id = tblSearch.getValueAt(tblSearch.getSelectedRow(), 0).toString();
        String name = tblSearch.getValueAt(tblSearch.getSelectedRow(), 1).toString() + " " + tblSearch.getValueAt(tblSearch.getSelectedRow(), 2).toString();
        txtID.setText(id);
        txtName.setText(name);
    }

    private boolean passesValidation()
    {
        boolean passed = true;
        warnings = "";
        String id = txtID.getText().trim();
        int demerits = Integer.valueOf(spinnerDemerits.getValue().toString());
        String reason = cmbRemarks.getSelectedItem().toString().trim();
        String staff = cmbStaff.getSelectedItem().toString().trim();
        if (id.isEmpty())
        {
            warnings += "ID cannot be empty.   Kindly select a student before proceeding.\n";
            passed = false;
        }
        if (staff.equals("--- Select One ---"))
        {
            warnings += "Kindly select a staff member.\n";
            passed = false;
        }
        if (demerits <= 0)
        {
            warnings += "You must have given at least one demerit to save.\n";
            passed = false;
        }
        if (reason.isEmpty())
        {
            warnings += "Kindly enter a reason for the demerit.\n"
                        + "You can also select one of the pre-definied reasons.\n";
            passed = false;
        }
        return passed;
    }

    private void resetForm()
    {
        remove(demeritTabbedPane);
        initComponents();
        populateLists();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser calDate;
    private javax.swing.JComboBox cmbRemarks;
    private javax.swing.JComboBox cmbStaff;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdCancel2;
    private javax.swing.JButton cmdNext;
    private javax.swing.JButton cmdSave;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JPanel demeritPanel;
    private javax.swing.JTabbedPane demeritTabbedPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDemerit;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblRemarks;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblStaff;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JSpinner spinnerDemerits;
    private javax.swing.JTable tblSearch;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}