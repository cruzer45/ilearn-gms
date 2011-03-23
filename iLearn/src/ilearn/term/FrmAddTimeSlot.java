/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmAddTimeSlot.java
 *
 * Created on Mar 13, 2011, 3:37:00 PM
 */
package ilearn.term;

import ilearn.kernel.Utilities;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmAddTimeSlot extends javax.swing.JInternalFrame
{

    SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
    SimpleDateFormat timeFormat2 = new SimpleDateFormat("HH:mm:ss");
    Date startDate;

    /** Creates new form FrmAddTimeSlot */
    public FrmAddTimeSlot()
    {
        try
        {
            startDate = timeFormat.parse("8:00 AM");
        }
        catch (ParseException ex)
        {
            Logger.getLogger(FrmAddTimeSlot.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        spinnerStart.setModel(new SpinnerDateModel(startDate, null, null, Calendar.HOUR_OF_DAY));
        spinnerEnd.setModel(new SpinnerDateModel(startDate, null, null, Calendar.HOUR_OF_DAY));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTimeCode = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        lblDay = new javax.swing.JLabel();
        cmbDay = new javax.swing.JComboBox();
        lblEnd = new javax.swing.JLabel();
        lblStart = new javax.swing.JLabel();
        SpinnerDateModel sm = new SpinnerDateModel(startDate, null, null, Calendar.HOUR_OF_DAY);
        spinnerStart = spinnerStart = new javax.swing.JSpinner(sm);
        JSpinner.DateEditor de = new JSpinner.DateEditor(spinnerStart, "h:mm a");
        spinnerStart.setEditor(de);
        spinnerEnd = spinnerEnd = new javax.swing.JSpinner(sm);
        JSpinner.DateEditor de2 = new JSpinner.DateEditor(spinnerEnd, "h:mm a");
        spinnerEnd.setEditor(de2);
        cmdCancel = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(FrmAddTimeSlot.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setToolTipText(resourceMap.getString("Form.toolTipText")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N

        lblTimeCode.setText(resourceMap.getString("lblTimeCode.text")); // NOI18N
        lblTimeCode.setName("lblTimeCode"); // NOI18N

        txtCode.setText(resourceMap.getString("txtCode.text")); // NOI18N
        txtCode.setToolTipText(resourceMap.getString("txtCode.toolTipText")); // NOI18N
        txtCode.setName("txtCode"); // NOI18N

        lblDay.setText(resourceMap.getString("lblDay.text")); // NOI18N
        lblDay.setName("lblDay"); // NOI18N

        cmbDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" }));
        cmbDay.setToolTipText(resourceMap.getString("cmbDay.toolTipText")); // NOI18N
        cmbDay.setName("cmbDay"); // NOI18N

        lblEnd.setText(resourceMap.getString("lblEnd.text")); // NOI18N
        lblEnd.setName("lblEnd"); // NOI18N

        lblStart.setText(resourceMap.getString("lblStart.text")); // NOI18N
        lblStart.setName("lblStart"); // NOI18N

        spinnerStart.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
        spinnerStart.setToolTipText(resourceMap.getString("spinnerStart.toolTipText")); // NOI18N
        spinnerStart.setName("spinnerStart"); // NOI18N

        spinnerEnd.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
        spinnerEnd.setToolTipText(resourceMap.getString("spinnerEnd.toolTipText")); // NOI18N
        spinnerEnd.setName("spinnerEnd"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(FrmAddTimeSlot.class, this);
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setText(resourceMap.getString("cmdCancel.text")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N

        cmdSave.setAction(actionMap.get("save")); // NOI18N
        cmdSave.setText(resourceMap.getString("cmdSave.text")); // NOI18N
        cmdSave.setName("cmdSave"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTimeCode)
                            .addComponent(lblDay)
                            .addComponent(lblStart)
                            .addComponent(lblEnd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spinnerStart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                            .addComponent(cmbDay, javax.swing.GroupLayout.Alignment.TRAILING, 0, 292, Short.MAX_VALUE)
                            .addComponent(txtCode, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                            .addComponent(spinnerEnd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cmdSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTimeCode)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDay)
                    .addComponent(cmbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEnd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel)
                    .addComponent(cmdSave))
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-386)/2, (screenSize.height-188)/2, 386, 188);
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void cancel()
    {
        Utilities.showCancelScreen(this);
    }

    @Action
    public void save()
    {
        String code = txtCode.getText().trim();
        String day = cmbDay.getSelectedItem().toString();

        Date startTime1 = new Date(Date.parse(spinnerStart.getModel().getValue().toString()));
        Date endTime1 = new Date(Date.parse(spinnerEnd.getModel().getValue().toString()));
        String startTime = timeFormat2.format(startTime1);
        String endTime = timeFormat2.format(endTime1);

        if (TimeSlots.addTimeSlot(code, day, startTime, endTime))
        {
            String message = "The Time Slot was successfully created.\n"
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
            String message = "An error occurred while trying to add this time slot.\n"
                    + "Kindly verify your information and try again.";
            Utilities.showErrorMessage(rootPane, message);
        }
    }

    private void resetForm()
    {
        txtCode.setText("");
        cmbDay.setSelectedIndex(0);
        spinnerStart.setModel(new SpinnerDateModel(startDate, null, null, Calendar.HOUR_OF_DAY));
        spinnerEnd.setModel(new SpinnerDateModel(startDate, null, null, Calendar.HOUR_OF_DAY));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbDay;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdSave;
    private javax.swing.JLabel lblDay;
    private javax.swing.JLabel lblEnd;
    private javax.swing.JLabel lblStart;
    private javax.swing.JLabel lblTimeCode;
    private javax.swing.JSpinner spinnerEnd;
    private javax.swing.JSpinner spinnerStart;
    private javax.swing.JTextField txtCode;
    // End of variables declaration//GEN-END:variables
}
