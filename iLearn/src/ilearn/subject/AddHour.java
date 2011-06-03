/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddHour.java
 *
 * Created on Apr 28, 2011, 10:18:37 PM
 */
package ilearn.subject;

import ilearn.kernel.Utilities;
import ilearn.term.TimeSlots;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class AddHour extends javax.swing.JDialog
{

    /** Creates new form AddHour */
    public AddHour(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        tblTimeSlots.setModel(TimeSlots.getActiveTimeSlotTableModelList());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTimeSlots = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setModal(true);
        setName("Form"); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        tblTimeSlots.setModel(new javax.swing.table.DefaultTableModel(
                                  new Object [][]
                                  {

                                  },
                                  new String []
                                  {
                                      "ID", "Code", "Day", "Start", "End", "Status"
                                  }
                              ));
        tblTimeSlots.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblTimeSlots.setName("tblTimeSlots"); // NOI18N
        tblTimeSlots.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tblTimeSlotsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTimeSlots);
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(AddHour.class, this);
        jButton1.setAction(actionMap.get("cancel")); // NOI18N
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(AddHour.class);
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton2.setAction(actionMap.get("add")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                          .addComponent(jButton2)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(jButton1)))
                      .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1)
                                .addComponent(jButton2))
                      .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblTimeSlotsMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblTimeSlotsMouseClicked
    {
        //GEN-HEADEREND:event_tblTimeSlotsMouseClicked
        if (evt.getClickCount() >= 2)
        {
            add();
        }
    }//GEN-LAST:event_tblTimeSlotsMouseClicked

    @Action
    public void cancel()
    {
        this.dispose();
    }

    @Action
    public void add()
    {
        if (tblTimeSlots.getSelectedRow() != -1)
        {
            String hourID = tblTimeSlots.getValueAt(tblTimeSlots.getSelectedRow(), 0).toString();
            String hourTitle = tblTimeSlots.getValueAt(tblTimeSlots.getSelectedRow(), 1).toString();
            Subject.addHour(hourTitle, hourID);
            this.dispose();
        }
        else
        {
            String message = "Kindly select a slot before proceeding.";
            Utilities.showWarningMessage(rootPane, message);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTimeSlots;
    // End of variables declaration//GEN-END:variables
}