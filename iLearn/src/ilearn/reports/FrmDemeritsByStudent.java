/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmDemeritsByStudent.java
 *
 * Created on Jul 14, 2011, 11:55:35 PM
 */
package ilearn.reports;

import ilearn.ILearnApp;
import ilearn.kernel.TableColumnAdjuster;
import ilearn.kernel.Utilities;
import ilearn.student.Student;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;

/**
 *
 * @author mrogers
 */
public class FrmDemeritsByStudent extends javax.swing.JInternalFrame
{

    /** Creates new form FrmDemeritsByStudent */
    public FrmDemeritsByStudent()
    {
        initComponents();
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
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSearch = new javax.swing.JTable();
        cmdCancel = new javax.swing.JButton();
        cmdLoadReport = new javax.swing.JButton();
        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(FrmDemeritsByStudent.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        lblSearch.setText(resourceMap.getString("lblSearch.text")); // NOI18N
        lblSearch.setName("lblSearch"); // NOI18N
        txtSearch.setName("txtSearch"); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtSearchKeyPressed(evt);
            }
        });
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(FrmDemeritsByStudent.class, this);
        cmdSearch.setAction(actionMap.get("search")); // NOI18N
        cmdSearch.setName("cmdSearch"); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        tblSearch.setAutoCreateRowSorter(true);
        tblSearch.setModel(new javax.swing.table.DefaultTableModel(
                               new Object [][]
                               {

                               },
                               new String []
                               {
                                   "ID", "Student", "Class"
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
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N
        cmdLoadReport.setAction(actionMap.get("loadReport")); // NOI18N
        cmdLoadReport.setText(resourceMap.getString("cmdLoadReport.text")); // NOI18N
        cmdLoadReport.setName("cmdLoadReport"); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                          .addComponent(lblSearch)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(cmdSearch))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                          .addComponent(cmdLoadReport)
                                          .addGap(18, 18, 18)
                                          .addComponent(cmdCancel)))
                      .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSearch)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmdSearch))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                      .addGap(12, 12, 12)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel)
                                .addComponent(cmdLoadReport))
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

    private void tblSearchMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblSearchMouseClicked
    {
        //GEN-HEADEREND:event_tblSearchMouseClicked
        if (evt.getClickCount() >= 2)
        {
            loadReport();
        }
    }//GEN-LAST:event_tblSearchMouseClicked

    @Action
    public void cancel()
    {
        this.dispose();
    }

    @Action
    public Task loadReport()
    {
        if (tblSearch.getSelectedRow() != -1)
        {
            return new LoadReportTask(org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class), this);
        }
        else
        {
            String message = "Kindly select a student before running the report.";
            Utilities.showWarningMessage(rootPane, message);
            return null;
        }
    }

    private class LoadReportTask extends org.jdesktop.application.Task<Object, Void>
    {

        String stuID;
        JInternalFrame thisDialog;

        LoadReportTask(org.jdesktop.application.Application app, JInternalFrame dialog)
        {
            super(app);
            String selectedID = tblSearch.getValueAt(tblSearch.getSelectedRow(), 0).toString();
            stuID = selectedID;
            thisDialog = dialog;
        }

        @Override
        protected Object doInBackground()
        {
            setMessage("Loading reporting enging.");
            ReportLoader.showDemeritsByStudent(stuID);
            return null;
        }
    }

    @Action
    public void search()
    {
        String criteria = txtSearch.getText().trim();
        tblSearch.setModel(Student.searchStudents(criteria));
        TableColumnAdjuster tca = new TableColumnAdjuster(tblSearch);
        tca.adjustColumns();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdLoadReport;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JTable tblSearch;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
