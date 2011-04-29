/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class FrmAddSubject extends javax.swing.JInternalFrame
{

    /** Creates new form FrmAddSubject */
    public FrmAddSubject()
    {
        initComponents();
        Subject.resetHours();
        loadTeacherList();
        //TimeList.setListData(TimeSlots.getTimeSlotList().toArray());
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
               subStaffCode = Staff.getStaffCode(cmbTeacher.getSelectedItem().toString()),
               subName = txtSubjectName.getText().trim(),
               subDescription = txtDescription.getText().trim();
        if (Subject.addSubject(subCode, subStaffCode, subName, subDescription))
        {
            String message = "The Subject was successfully added.\n"
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
            String message = "An error occurred while trying to add this subject.\n"
                             + "Kindly verify your information and try again.\n"
                             + "If the problem persists kindly contact your system administrator.";
            Utilities.showErrorMessage(rootPane, message);
        }
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
        Subject.removeHour(TimeList.getSelectedValue().toString());
        loadSelectedHours();
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
    public void resetForm()
    {
        //TimeList.setListData(TimeSlots.getTimeSlotList().toArray());
        txtDescription.setText("");
        txtSubjectCode.setText("");
        txtSubjectName.setText("");
        Subject.resetHours();
        loadSelectedHours();
        loadTeacherList();
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
        lblTeacher = new javax.swing.JLabel();
        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(FrmAddSubject.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setToolTipText(resourceMap.getString("Form.toolTipText")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        lblSubjectCode.setText(resourceMap.getString("lblSubjectCode.text")); // NOI18N
        lblSubjectCode.setName("lblSubjectCode"); // NOI18N
        txtSubjectCode.setText(resourceMap.getString("txtSubjectCode.text")); // NOI18N
        txtSubjectCode.setToolTipText(resourceMap.getString("txtSubjectCode.toolTipText")); // NOI18N
        txtSubjectCode.setName("txtSubjectCode"); // NOI18N
        lblSubjectName.setText(resourceMap.getString("lblSubjectName.text")); // NOI18N
        lblSubjectName.setName("lblSubjectName"); // NOI18N
        txtSubjectName.setText(resourceMap.getString("txtSubjectName.text")); // NOI18N
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
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(FrmAddSubject.class, this);
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setText(resourceMap.getString("cmdCancel.text")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N
        cmdSave.setAction(actionMap.get("save")); // NOI18N
        cmdSave.setText(resourceMap.getString("cmdSave.text")); // NOI18N
        cmdSave.setName("cmdSave"); // NOI18N
        cmdReset.setAction(actionMap.get("resetForm")); // NOI18N
        cmdReset.setText(resourceMap.getString("cmdReset.text")); // NOI18N
        cmdReset.setName("cmdReset"); // NOI18N
        cmdAdd.setAction(actionMap.get("add")); // NOI18N
        cmdAdd.setIcon(resourceMap.getIcon("cmdAdd.icon")); // NOI18N
        cmdAdd.setText(resourceMap.getString("cmdAdd.text")); // NOI18N
        cmdAdd.setName("cmdAdd"); // NOI18N
        cmdRemove.setAction(actionMap.get("remove")); // NOI18N
        cmdRemove.setIcon(resourceMap.getIcon("cmdRemove.icon")); // NOI18N
        cmdRemove.setText(resourceMap.getString("cmdRemove.text")); // NOI18N
        cmdRemove.setName("cmdRemove"); // NOI18N
        cmbTeacher.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbTeacher.setName("cmbTeacher"); // NOI18N
        lblTeacher.setText(resourceMap.getString("lblTeacher.text")); // NOI18N
        lblTeacher.setName("lblTeacher"); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                          .addComponent(cmdReset)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                                          .addComponent(cmdSave)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel))
                                .addGroup(layout.createSequentialGroup()
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(lblSubjectCode)
                                                  .addComponent(lblSubjectName)
                                                  .addComponent(lblDescription)
                                                  .addComponent(lblTeacher)
                                                  .addComponent(lblDays_Time))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                  .addComponent(cmbTeacher, 0, 309, Short.MAX_VALUE)
                                                  .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                                                  .addComponent(txtSubjectName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                                                  .addComponent(txtSubjectCode, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                                                  .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                          .addComponent(cmdAdd)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdRemove)))
                      .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSubjectCode)
                                .addComponent(txtSubjectCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSubjectName)
                                .addComponent(txtSubjectName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblDescription)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblTeacher))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblDays_Time)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdRemove)
                                .addComponent(cmdAdd))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel)
                                .addComponent(cmdSave)
                                .addComponent(cmdReset))
                      .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList TimeList;
    private javax.swing.JComboBox cmbTeacher;
    private javax.swing.JButton cmdAdd;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdRemove;
    private javax.swing.JButton cmdReset;
    private javax.swing.JButton cmdSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDays_Time;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblSubjectCode;
    private javax.swing.JLabel lblSubjectName;
    private javax.swing.JLabel lblTeacher;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtSubjectCode;
    private javax.swing.JTextField txtSubjectName;
    // End of variables declaration//GEN-END:variables
}
