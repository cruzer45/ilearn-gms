/*
 * FrmAddTerm.java
 *
 * Created on Mar 8, 2011, 9:06:01 PM
 */
package ilearn.term;

import ilearn.kernel.Utilities;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmAddTerm extends javax.swing.JInternalFrame
{

    /** Creates new form FrmAddTerm */
    public FrmAddTerm()
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
    private void initComponents() {

        lblTermCode = new javax.swing.JLabel();
        txtTermCode = new javax.swing.JTextField();
        lblShortName = new javax.swing.JLabel();
        txtShortName = new javax.swing.JTextField();
        lblLongName = new javax.swing.JLabel();
        txtLongName = new javax.swing.JTextField();
        cmdCancel = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        cmdReset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmAddTerm.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setToolTipText(resourceMap.getString("Form.toolTipText")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N

        lblTermCode.setText(resourceMap.getString("lblTermCode.text")); // NOI18N
        lblTermCode.setName("lblTermCode"); // NOI18N

        txtTermCode.setText(resourceMap.getString("txtTermCode.text")); // NOI18N
        txtTermCode.setToolTipText(resourceMap.getString("txtTermCode.toolTipText")); // NOI18N
        txtTermCode.setName("txtTermCode"); // NOI18N

        lblShortName.setText(resourceMap.getString("lblShortName.text")); // NOI18N
        lblShortName.setName("lblShortName"); // NOI18N

        txtShortName.setText(resourceMap.getString("txtShortName.text")); // NOI18N
        txtShortName.setToolTipText(resourceMap.getString("txtShortName.toolTipText")); // NOI18N
        txtShortName.setName("txtShortName"); // NOI18N

        lblLongName.setText(resourceMap.getString("lblLongName.text")); // NOI18N
        lblLongName.setName("lblLongName"); // NOI18N

        txtLongName.setText(resourceMap.getString("txtLongName.text")); // NOI18N
        txtLongName.setToolTipText(resourceMap.getString("txtLongName.toolTipText")); // NOI18N
        txtLongName.setName("txtLongName"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmAddTerm.class, this);
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setText(resourceMap.getString("cmdCancel.text")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N

        cmdSave.setAction(actionMap.get("save")); // NOI18N
        cmdSave.setText(resourceMap.getString("cmdSave.text")); // NOI18N
        cmdSave.setName("cmdSave"); // NOI18N

        cmdReset.setAction(actionMap.get("resetForm")); // NOI18N
        cmdReset.setName("cmdReset"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jDateChooser1.setName("jDateChooser1"); // NOI18N

        jDateChooser2.setName("jDateChooser2"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setName("jComboBox1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, 0, 294, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTermCode)
                                    .addComponent(lblShortName)
                                    .addComponent(lblLongName)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                    .addComponent(txtLongName, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                    .addComponent(txtShortName, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                    .addComponent(txtTermCode, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(cmdReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
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
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTermCode)
                    .addComponent(txtTermCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblShortName)
                    .addComponent(txtShortName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLongName)
                    .addComponent(txtLongName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel)
                    .addComponent(cmdSave)
                    .addComponent(cmdReset))
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-410)/2, (screenSize.height-238)/2, 410, 238);
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void cancel()
    {
        Utilities.showCancelScreen(this);
    }

    @Action
    public void save()
    {
        String trmCode = txtTermCode.getText().trim();
        String shortName = txtShortName.getText().trim();
        String longName = txtLongName.getText().trim();
        if(Term.addTerm(trmCode, shortName, longName))
        {
            String message = "The term was successfully added. \n"
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
            String message = "An error occurred while trying to save this term.\n"
                             + "Kindly verify your information and try again.";
            Utilities.showErrorMessage(rootPane, message);
        }
    }

    @Action
    public void resetForm()
    {
        txtTermCode.setText("");
        txtShortName.setText("");
        txtLongName.setText("");
        txtTermCode.grabFocus();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdReset;
    private javax.swing.JButton cmdSave;
    private javax.swing.JComboBox jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblLongName;
    private javax.swing.JLabel lblShortName;
    private javax.swing.JLabel lblTermCode;
    private javax.swing.JTextField txtLongName;
    private javax.swing.JTextField txtShortName;
    private javax.swing.JTextField txtTermCode;
    // End of variables declaration//GEN-END:variables
}
