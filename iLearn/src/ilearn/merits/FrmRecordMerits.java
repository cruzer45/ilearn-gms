/*
 * FrmRecordMerits.java
 *
 * Created on Nov 16, 2011, 9:25:07 PM
 */
package ilearn.merits;

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
public class FrmRecordMerits extends javax.swing.JInternalFrame
{

    String warnings = "";

    /** Creates new form FrmRecordMerits */
    public FrmRecordMerits()
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
    private void initComponents() {

        meritTabbedPane1 = new javax.swing.JTabbedPane();
        searchPanel = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSearch = new javax.swing.JTable();
        cmdCancel = new javax.swing.JButton();
        cmdNext = new javax.swing.JButton();
        meritPanel = new javax.swing.JPanel();
        lblID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblDemerit = new javax.swing.JLabel();
        spinnerMerits = new javax.swing.JSpinner();
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
        setTitle("Record Merits");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/ilearn/resources/star_add.png"))); // NOI18N

        meritTabbedPane1.setName("meritTabbedPane1"); // NOI18N

        searchPanel.setName("searchPanel"); // NOI18N

        lblSearch.setText("Search:");
        lblSearch.setName("lblSearch"); // NOI18N

        txtSearch.setName("txtSearch"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmRecordMerits.class, this);
        cmdSearch.setAction(actionMap.get("search")); // NOI18N
        cmdSearch.setName("cmdSearch"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tblSearch.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Class"
            }
        ));
        tblSearch.setName("tblSearch"); // NOI18N
        tblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSearchMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSearch);
        tblSearch.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N

        cmdNext.setText("Next");
        cmdNext.setName("cmdNext"); // NOI18N

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addComponent(cmdNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdCancel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchPanelLayout.createSequentialGroup()
                        .addComponent(lblSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdSearch)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel)
                    .addComponent(cmdNext))
                .addContainerGap())
        );

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmRecordMerits.class);
        meritTabbedPane1.addTab("Search", resourceMap.getIcon("jPanel1.TabConstraints.tabIcon"), searchPanel); // NOI18N

        meritPanel.setName("meritPanel"); // NOI18N

        lblID.setText("ID:");
        lblID.setName("lblID"); // NOI18N

        txtID.setEditable(false);
        txtID.setName("txtID"); // NOI18N

        lblName.setText("Name:");
        lblName.setName("lblName"); // NOI18N

        txtName.setEditable(false);
        txtName.setName("txtName"); // NOI18N

        lblDemerit.setText("Merits:");
        lblDemerit.setName("lblDemerit"); // NOI18N

        spinnerMerits.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        spinnerMerits.setName("spinnerMerits"); // NOI18N

        lblRemarks.setText("Remarks:");
        lblRemarks.setName("lblRemarks"); // NOI18N

        cmdCancel2.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel2.setName("cmdCancel2"); // NOI18N

        cmdSave.setAction(actionMap.get("Save")); // NOI18N
        cmdSave.setName("cmdSave"); // NOI18N

        cmbRemarks.setEditable(true);
        cmbRemarks.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbRemarks.setName("cmbRemarks"); // NOI18N

        lblDate.setText("Date:");
        lblDate.setName("lblDate"); // NOI18N

        calDate.setName("calDate"); // NOI18N

        cmbStaff.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbStaff.setName("cmbStaff"); // NOI18N

        lblStaff.setText("Staff:");
        lblStaff.setName("lblStaff"); // NOI18N

        javax.swing.GroupLayout meritPanelLayout = new javax.swing.GroupLayout(meritPanel);
        meritPanel.setLayout(meritPanelLayout);
        meritPanelLayout.setHorizontalGroup(
            meritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meritPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(meritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, meritPanelLayout.createSequentialGroup()
                        .addComponent(cmdSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdCancel2))
                    .addGroup(meritPanelLayout.createSequentialGroup()
                        .addGroup(meritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblID)
                            .addComponent(lblName)
                            .addComponent(lblDate)
                            .addComponent(lblStaff)
                            .addComponent(lblDemerit)
                            .addComponent(lblRemarks))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(meritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbStaff, 0, 293, Short.MAX_VALUE)
                            .addComponent(calDate, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(spinnerMerits, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(cmbRemarks, 0, 293, Short.MAX_VALUE))))
                .addContainerGap())
        );
        meritPanelLayout.setVerticalGroup(
            meritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(meritPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(meritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(meritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(meritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(calDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(meritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStaff))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(meritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDemerit)
                    .addComponent(spinnerMerits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(meritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRemarks)
                    .addComponent(cmbRemarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addGroup(meritPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel2)
                    .addComponent(cmdSave))
                .addContainerGap())
        );

        meritTabbedPane1.addTab("Merit", resourceMap.getIcon("jPanel2.TabConstraints.tabIcon"), meritPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(meritTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(meritTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
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
        ArrayList<String> demeritReasons = Merits.getMeritReasons();
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
    public void next()
    {
        if (tblSearch.getSelectedRow() != -1)
        {
            loadInfo();
            meritTabbedPane1.setSelectedIndex(meritTabbedPane1.getSelectedIndex() + 1);
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
        int merits = Integer.valueOf(spinnerMerits.getValue().toString());
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
        if (merits <= 0)
        {
            warnings += "You must give at least one merit to save.\n";
            passed = false;
        }
        if (reason.isEmpty())
        {
            warnings += "Kindly enter a reason for the merit.\n"
                        + "You can also select one of the pre-definied reasons.\n";
            passed = false;
        }
        return passed;
    }

    private void resetForm()
    {
        remove(meritTabbedPane1);
        initComponents();
        populateLists();
    }

    @Action
    public void Save()
    {
        if (!passesValidation())
        {
            Utilities.showWarningMessage(rootPane, warnings);
            return;
        }
        String merStuID = txtID.getText().trim();
        String merDate = Utilities.YMD_Formatter.format(calDate.getDate());
        String merStaID = Staff.getStaffID(Staff.getStaffCodeFromName(cmbStaff.getSelectedItem().toString()));
        String merClsCode = Student.getStudentClass(merStuID);
        String merTermID = Term.getCurrentTerm();
        String merits = spinnerMerits.getValue().toString();
        String merRemarks = cmbRemarks.getSelectedItem().toString();
        boolean meritAdded = Merits.addMerit(merStuID, merDate, merStaID, merClsCode, merTermID, merits, merRemarks);
        if (meritAdded)
        {
            String message = "The merit was successfully recorded. \n"
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
            String message = "An error occurred while trying to record this merit.\n"
                             + "Kindly verify your information and try again.";
            Utilities.showErrorMessage(rootPane, message);
        }
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDemerit;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblRemarks;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblStaff;
    private javax.swing.JPanel meritPanel;
    private javax.swing.JTabbedPane meritTabbedPane1;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JSpinner spinnerMerits;
    private javax.swing.JTable tblSearch;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
