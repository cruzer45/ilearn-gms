/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmAdjustGrades.java
 *
 * Created on Jan 16, 2013, 11:00:20 PM
 */
package ilearn.grades;

import ilearn.kernel.Utilities;
import ilearn.school.School;
import java.util.ArrayList;
import java.util.HashMap;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmAdjustGrades extends javax.swing.JInternalFrame
{

    /** Creates new form FrmAdjustGrades */
    public FrmAdjustGrades()
    {
        initComponents();
    }
    
    void populateOptions(){
        int passingMark = School.getPassingMark();
        txtGT.setText("0");
        txtLT.setText(String.valueOf(passingMark));
        txtSetTo.setText(String.valueOf(passingMark));
        chkCalculateAverage.setSelected(true);
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblGT = new javax.swing.JLabel();
        txtGT = new javax.swing.JTextField();
        lblLT = new javax.swing.JLabel();
        txtLT = new javax.swing.JTextField();
        lblSetTo = new javax.swing.JLabel();
        txtSetTo = new javax.swing.JTextField();
        chkCalculateAverage = new javax.swing.JCheckBox();
        lblCalcAverage = new javax.swing.JLabel();
        cmdCancel = new javax.swing.JButton();
        cmdProcess = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Adjust Grades");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/ilearn/resources/script_edit.png"))); // NOI18N

        lblGT.setText("Grades greater than or equal to:");
        lblGT.setName("lblGT"); // NOI18N

        txtGT.setText("0");
        txtGT.setName("txtGT"); // NOI18N

        lblLT.setText("but less than:");
        lblLT.setName("lblLT"); // NOI18N

        txtLT.setText("60");
        txtLT.setName("txtLT"); // NOI18N

        lblSetTo.setText("will be set to:");
        lblSetTo.setName("lblSetTo"); // NOI18N

        txtSetTo.setText("60");
        txtSetTo.setName("txtSetTo"); // NOI18N

        chkCalculateAverage.setSelected(true);
        chkCalculateAverage.setName("chkCalculateAverage"); // NOI18N

        lblCalcAverage.setText("Also recalculate the overall average:");
        lblCalcAverage.setName("lblCalcAverage"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmAdjustGrades.class, this);
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setText("Cancel");
        cmdCancel.setName("cmdCancel"); // NOI18N

        cmdProcess.setAction(actionMap.get("adjustGrades")); // NOI18N
        cmdProcess.setText("Run Process");
        cmdProcess.setName("cmdProcess"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGT)
                            .addComponent(lblLT)
                            .addComponent(lblSetTo)
                            .addComponent(lblCalcAverage))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkCalculateAverage)
                            .addComponent(txtSetTo, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(txtLT, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(txtGT, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cmdProcess)
                        .addGap(18, 18, 18)
                        .addComponent(cmdCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGT)
                    .addComponent(txtGT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLT)
                    .addComponent(txtLT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSetTo)
                    .addComponent(txtSetTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkCalculateAverage)
                    .addComponent(lblCalcAverage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel)
                    .addComponent(cmdProcess))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void cancel()
    {
        Utilities.showCancelScreen(this);
    }

    @Action
    public void adjustGrades()
    {
        HashMap params = new HashMap();
        params.put("greaterThan", txtGT.getText().trim());
        params.put("lessThan", txtLT.getText().trim());
        params.put("setTo", txtSetTo.getText().trim());
        params.put("recalculateAverage", chkCalculateAverage.isSelected());
        
        Grade.adjustGrades(params);
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkCalculateAverage;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdProcess;
    private javax.swing.JLabel lblCalcAverage;
    private javax.swing.JLabel lblGT;
    private javax.swing.JLabel lblLT;
    private javax.swing.JLabel lblSetTo;
    private javax.swing.JTextField txtGT;
    private javax.swing.JTextField txtLT;
    private javax.swing.JTextField txtSetTo;
    // End of variables declaration//GEN-END:variables
}
