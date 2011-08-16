/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmLockUsers.java
 *
 * Created on Aug 8, 2011, 10:48:21 PM
 */
package ilearn.user;

import ilearn.kernel.Utilities;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;

/**
 *
 * @author mrogers
 */
public class FrmUnlockUsers extends javax.swing.JInternalFrame
{

    /** Creates new form FrmLockUsers */
    public FrmUnlockUsers()
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        setClosable(true);
        setIconifiable(true);
        setTitle("Unlock Users");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/ilearn/resources/lock_open.png"))); // NOI18N
        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+4));
        jLabel1.setText("Warning");
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel2.setText("<html>\nAn Administrator had previously restricted access... most likely to prevent modification of grades.<br /><br />\nOnly unlock the application if you are sure users may once again make modifications.\n</html>"); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel2.setName("jLabel2"); // NOI18N
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmUnlockUsers.class, this);
        jButton1.setAction(actionMap.get("cancel")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton2.setAction(actionMap.get("unlockUsers")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                          .addComponent(jButton2)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(jButton1))
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                      .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(jLabel1)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1)
                                .addComponent(jButton2))
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
    public Task unlockUsers()
    {
        return new UnlockUsersTask(org.jdesktop.application.Application.getInstance());
    }

    private class UnlockUsersTask extends org.jdesktop.application.Task<Object, Void>
    {

        UnlockUsersTask(org.jdesktop.application.Application app)
        {
            super(app);
        }

        @Override
        protected Object doInBackground()
        {
            boolean lockUsers = User.unlockUsers();
            return lockUsers;  // return your result
        }

        @Override
        protected void succeeded(Object result)
        {
            if (result.equals(Boolean.TRUE))
            {
                String message = "Users were successfully unlocked.";
                Utilities.showInfoMessage(rootPane, message);
            }
            else
            {
                String message = "An error occurred while trying to unlock the users.";
                Utilities.showWarningMessage(rootPane, message);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
