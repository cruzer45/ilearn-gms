/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmAddNewClass.java
 *
 * Created on Feb 21, 2011, 9:58:49 PM
 */

package ilearn.classes;

import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmAddNewClass extends javax.swing.JInternalFrame {

    /** Creates new form FrmAddNewClass */
    public FrmAddNewClass() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblClassCode = new javax.swing.JLabel();
        txtClassCode = new javax.swing.JTextField();
        lblClassName = new javax.swing.JLabel();
        txtClassName = new javax.swing.JTextField();
        lblClassDesc = new javax.swing.JLabel();
        txtClassDescription = new javax.swing.JTextField();
        lblRoom = new javax.swing.JLabel();
        cmbRoom = new javax.swing.JComboBox();
        lblHomeRoom = new javax.swing.JLabel();
        cmbHomeRoom = new javax.swing.JComboBox();
        cmdCancel = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        lblClassSize = new javax.swing.JLabel();
        spinnerClassSize = new javax.swing.JSpinner();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(FrmAddNewClass.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setToolTipText(resourceMap.getString("Form.toolTipText")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N

        lblClassCode.setText(resourceMap.getString("lblClassCode.text")); // NOI18N
        lblClassCode.setName("lblClassCode"); // NOI18N

        txtClassCode.setText(resourceMap.getString("txtClassCode.text")); // NOI18N
        txtClassCode.setName("txtClassCode"); // NOI18N

        lblClassName.setText(resourceMap.getString("lblClassName.text")); // NOI18N
        lblClassName.setName("lblClassName"); // NOI18N

        txtClassName.setText(resourceMap.getString("txtClassName.text")); // NOI18N
        txtClassName.setName("txtClassName"); // NOI18N

        lblClassDesc.setText(resourceMap.getString("lblClassDesc.text")); // NOI18N
        lblClassDesc.setName("lblClassDesc"); // NOI18N

        txtClassDescription.setText(resourceMap.getString("txtClassDescription.text")); // NOI18N
        txtClassDescription.setName("txtClassDescription"); // NOI18N

        lblRoom.setText(resourceMap.getString("lblRoom.text")); // NOI18N
        lblRoom.setName("lblRoom"); // NOI18N

        cmbRoom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbRoom.setName("cmbRoom"); // NOI18N

        lblHomeRoom.setText(resourceMap.getString("lblHomeRoom.text")); // NOI18N
        lblHomeRoom.setName("lblHomeRoom"); // NOI18N

        cmbHomeRoom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbHomeRoom.setName("cmbHomeRoom"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(FrmAddNewClass.class, this);
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setText(resourceMap.getString("cmdCancel.text")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N

        cmdSave.setAction(actionMap.get("save")); // NOI18N
        cmdSave.setText(resourceMap.getString("cmdSave.text")); // NOI18N
        cmdSave.setName("cmdSave"); // NOI18N

        lblClassSize.setText(resourceMap.getString("lblClassSize.text")); // NOI18N
        lblClassSize.setName("lblClassSize"); // NOI18N

        spinnerClassSize.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(30), null, null, Integer.valueOf(1)));
        spinnerClassSize.setName("spinnerClassSize"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cmdSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmdCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblClassCode)
                            .addComponent(lblClassName)
                            .addComponent(lblClassDesc)
                            .addComponent(lblRoom)
                            .addComponent(lblHomeRoom)
                            .addComponent(lblClassSize))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbHomeRoom, 0, 279, Short.MAX_VALUE)
                            .addComponent(cmbRoom, 0, 279, Short.MAX_VALUE)
                            .addComponent(txtClassDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                            .addComponent(txtClassName, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                            .addComponent(txtClassCode, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                            .addComponent(spinnerClassSize, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClassCode)
                    .addComponent(txtClassCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClassName)
                    .addComponent(txtClassName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClassDesc)
                    .addComponent(txtClassDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRoom)
                    .addComponent(cmbRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHomeRoom)
                    .addComponent(cmbHomeRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClassSize)
                    .addComponent(spinnerClassSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel)
                    .addComponent(cmdSave))
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
    public void save()
    {
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbHomeRoom;
    private javax.swing.JComboBox cmbRoom;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdSave;
    private javax.swing.JLabel lblClassCode;
    private javax.swing.JLabel lblClassDesc;
    private javax.swing.JLabel lblClassName;
    private javax.swing.JLabel lblClassSize;
    private javax.swing.JLabel lblHomeRoom;
    private javax.swing.JLabel lblRoom;
    private javax.swing.JSpinner spinnerClassSize;
    private javax.swing.JTextField txtClassCode;
    private javax.swing.JTextField txtClassDescription;
    private javax.swing.JTextField txtClassName;
    // End of variables declaration//GEN-END:variables

}
