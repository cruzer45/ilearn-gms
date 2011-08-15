/*
 * FrmErrors.java
 *
 * Created on Jul 7, 2011, 9:50:02 PM
 */
package ilearn.utils;

import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmErrors extends javax.swing.JDialog
{

    /** Creates new form FrmErrors */
    public FrmErrors(java.awt.Frame parent, boolean modal, String errors)
    {
        super(parent, modal);
        initComponents();
        txtMessage.append(errors);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblErrors1 = new javax.swing.JLabel();
        lblError2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMessage = new javax.swing.JTextArea();
        cmdClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmErrors.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setModal(true);
        setName("Form"); // NOI18N

        lblErrors1.setFont(lblErrors1.getFont().deriveFont(lblErrors1.getFont().getStyle() | java.awt.Font.BOLD, lblErrors1.getFont().getSize()+4));
        lblErrors1.setText(resourceMap.getString("lblErrors1.text")); // NOI18N
        lblErrors1.setName("lblErrors1"); // NOI18N

        lblError2.setText(resourceMap.getString("lblError2.text")); // NOI18N
        lblError2.setName("lblError2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        txtMessage.setColumns(20);
        txtMessage.setEditable(false);
        txtMessage.setRows(5);
        txtMessage.setName("txtMessage"); // NOI18N
        jScrollPane1.setViewportView(txtMessage);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmErrors.class, this);
        cmdClose.setAction(actionMap.get("close")); // NOI18N
        cmdClose.setText(resourceMap.getString("cmdClose.text")); // NOI18N
        cmdClose.setName("cmdClose"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                    .addComponent(cmdClose)
                    .addComponent(lblErrors1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblError2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblErrors1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblError2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addComponent(cmdClose)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void close()
    {
        this.dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdClose;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblError2;
    private javax.swing.JLabel lblErrors1;
    private javax.swing.JTextArea txtMessage;
    // End of variables declaration//GEN-END:variables
}