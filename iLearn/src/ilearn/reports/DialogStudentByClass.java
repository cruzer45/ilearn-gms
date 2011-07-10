/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DialogStudentByClass.java
 *
 * Created on May 1, 2011, 1:37:20 AM
 */
package ilearn.reports;

import ilearn.classes.Classes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;

/**
 *
 * @author mrogers
 */
public class DialogStudentByClass extends javax.swing.JDialog
{

    /** Creates new form DialogStudentByClass */
    public DialogStudentByClass(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        ArrayList<String> classList = Classes.getClassList();
        classList.add(0, "All");
        cmbClass.setModel(new DefaultComboBoxModel(classList.toArray()));
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
        lblTitle = new javax.swing.JLabel();
        lblClass = new javax.swing.JLabel();
        cmbClass = new javax.swing.JComboBox();
        cmdCancel = new javax.swing.JButton();
        cmdRun = new javax.swing.JButton();
        lblCaution = new javax.swing.JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(DialogStudentByClass.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        lblTitle.setFont(resourceMap.getFont("lblTitle.font")); // NOI18N
        lblTitle.setText(resourceMap.getString("lblTitle.text")); // NOI18N
        lblTitle.setName("lblTitle"); // NOI18N
        lblClass.setText(resourceMap.getString("lblClass.text")); // NOI18N
        lblClass.setName("lblClass"); // NOI18N
        cmbClass.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbClass.setName("cmbClass"); // NOI18N
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(DialogStudentByClass.class, this);
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setText(resourceMap.getString("cmdCancel.text")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N
        cmdRun.setAction(actionMap.get("run")); // NOI18N
        cmdRun.setText(resourceMap.getString("cmdRun.text")); // NOI18N
        cmdRun.setName("cmdRun"); // NOI18N
        lblCaution.setIcon(resourceMap.getIcon("lblCaution.icon")); // NOI18N
        lblCaution.setText(resourceMap.getString("lblCaution.text")); // NOI18N
        lblCaution.setName("lblCaution"); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCaution, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                                .addComponent(lblTitle)
                                .addGroup(layout.createSequentialGroup()
                                          .addComponent(lblClass)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(cmbClass, 0, 297, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                          .addComponent(cmdRun)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel)))
                      .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(lblTitle)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblClass)
                                .addComponent(cmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addGap(18, 18, 18)
                      .addComponent(lblCaution, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel)
                                .addComponent(cmdRun))
                      .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void cancel()
    {
        this.dispose();
    }

    @Action
    public Task run()
    {
        return new RunTask(org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class), this);
    }

    private class RunTask extends org.jdesktop.application.Task<Object, Void>
    {

        JDialog currentDialog;

        RunTask(org.jdesktop.application.Application app, JDialog current)
        {
            super(app);
            currentDialog = current;
        }

        @Override
        protected Object doInBackground()
        {
            String report = "reports/Student-Student_List_by_Class.jasper";
            String title = "Report - Student List by Class";
            String classCode = cmbClass.getSelectedItem().toString();
            if (classCode.equals("All"))
            {
                classCode = "%";
            }
            // Second, create a map of parameters to pass to the report.
            Map parameters = new HashMap();
            parameters.put("SUBREPORT_DIR", "reports/");
            parameters.put("classCode", classCode);
            try
            {
                ReportViewer.generateReport(report, parameters, title);
            }
            catch (Exception exception)
            {
                String message = "An error occurred while generating a report.";
                Logger.getLogger(DialogStudentByClass.class.getName()).log(Level.SEVERE, message, exception);
            }
            return null;  // return your result
        }

        @Override
        protected void succeeded(Object result)
        {
            currentDialog.dispose();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbClass;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdRun;
    private javax.swing.JLabel lblCaution;
    private javax.swing.JLabel lblClass;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}
