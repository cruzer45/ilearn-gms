/*
 * FrmPermissionMaker.java
 *
 * Created on Jul 22, 2011, 10:03:19 AM
 */
package ilearn.kernel;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author m.rogers
 */
public class FrmPermissionMaker extends javax.swing.JFrame
{

    /** Creates new form FrmPermissionMaker */
    public FrmPermissionMaker()
    {
        initComponents();
        tree.getCheckingModel().setCheckingMode(TreeCheckingModel.CheckingMode.PROPAGATE_PRESERVING_UNCHECK);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tree = new it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPermission = new javax.swing.JTextArea();
        cmdClose = new javax.swing.JButton();
        cmdGenerate = new javax.swing.JButton();
        cmdLoad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setName("jSplitPane1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Menu");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Student");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Add Student");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit Student");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("View Student");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Attendance");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Enter Attendance");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit Attendance");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Grades");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Record Grade");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit Grade");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit Report Card Remarks");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Demerits");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Record Demerits");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit Demerits");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Detention");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Add Detention");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Edit Detention");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Record Served Detention");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Reports");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Attendance Reports");
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Attendance Summary");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Attendance Details");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Student Reports");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Student List");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Repeating Students");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Students By Class");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Student ID Cards");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Class Reports");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Class List Report");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Class Grade Book");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Report Cards Menu");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Mid Term Reports");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Mid Term Class Ranking");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Term End Report");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Term End Ranking");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Demerit Reports");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Demerit Summary by Student");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Demerit Summary by Teacher");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Demerits By Class");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Demerits By Student");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Statistical Reports");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Class Size Distribution");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Gender Distribution");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Manage");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Class");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Add Class");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Edit Class");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("View Class");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Promotions");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Assign Promotions");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Promote Students");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("School");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Staff");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Add Staff");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Edit Staff");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Subjects");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Add Subjects");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Edit Subjects");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Term");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Add Term");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Edit Term");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Time Slots");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Add Time Slot");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Edit Time Slot");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("User Menu");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Add User");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Edit User");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Lock User");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Unlock User");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Utilities Menu");
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Mid Term");
        javax.swing.tree.DefaultMutableTreeNode treeNode5 = new javax.swing.tree.DefaultMutableTreeNode("Calculate Mid Term Grades");
        treeNode4.add(treeNode5);
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("End Of Term");
        treeNode5 = new javax.swing.tree.DefaultMutableTreeNode("Calculate End of Term Grades");
        treeNode4.add(treeNode5);
        treeNode5 = new javax.swing.tree.DefaultMutableTreeNode("Close Term");
        treeNode4.add(treeNode5);
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        tree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        tree.setName("tree"); // NOI18N
        jScrollPane1.setViewportView(tree);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        txtPermission.setColumns(20);
        txtPermission.setRows(5);
        txtPermission.setName("txtPermission"); // NOI18N
        jScrollPane2.setViewportView(txtPermission);

        jSplitPane1.setRightComponent(jScrollPane2);

        cmdClose.setText("Close");
        cmdClose.setName("cmdClose"); // NOI18N
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });

        cmdGenerate.setText("Generate");
        cmdGenerate.setName("cmdGenerate"); // NOI18N
        cmdGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGenerateActionPerformed(evt);
            }
        });

        cmdLoad.setText("Load");
        cmdLoad.setName("cmdLoad"); // NOI18N
        cmdLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdLoad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                        .addComponent(cmdGenerate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdClose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdClose)
                    .addComponent(cmdGenerate)
                    .addComponent(cmdLoad))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmdCloseActionPerformed
    {
//GEN-HEADEREND:event_cmdCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cmdCloseActionPerformed

    private void cmdGenerateActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmdGenerateActionPerformed
    {
//GEN-HEADEREND:event_cmdGenerateActionPerformed
        tree.expandAll();
        String permissions = "";
        for (int i = 1; i < tree.getRowCount(); i++)
        {
            TreePath currentPath = tree.getPathForRow(i);
            boolean isselected = tree.isPathChecked(currentPath);
            String currentPrev = (currentPath.getLastPathComponent() + "-" + isselected);
            permissions += currentPrev + "|";
        }
        txtPermission.setText(permissions);
    }//GEN-LAST:event_cmdGenerateActionPerformed

    private void cmdLoadActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmdLoadActionPerformed
    {
//GEN-HEADEREND:event_cmdLoadActionPerformed
        if (txtPermission.getText().trim().isEmpty())
        {
            String message = "Kindly enter a permission string to parse before loading";
            Utilities.showWarningMessage(rootPane, message);
            return;
        }
        tree.expandAll();
        for (int i = 0; i < tree.getRowCount(); i++)
        {
            TreePath currPath = tree.getPathForRow(i);
            String currentPath = currPath.getLastPathComponent().toString();
            if (previligeAvailable(currentPath))
            {
                tree.addCheckingPath(currPath);
            }
            else
            {
                tree.removeCheckingPath(currPath);
            }
        }
    }//GEN-LAST:event_cmdLoadActionPerformed

    private boolean previligeAvailable(String currentPath)
    {
        String privileges = txtPermission.getText().trim();
        String[] prevList = privileges.split("\\|");
        for (String prevItem : prevList)
        {
            String[] split = prevItem.split("-");
            String item = split[0];
            System.out.println(item + " vs " + currentPath);
            if (item.equals(currentPath) && (split[1].equalsIgnoreCase("True"))) //if the prevelige matches the row set check the path.
            {
                return true;
            }
            else if (item.equals(currentPath) && (split[1].equalsIgnoreCase("false")))
            {
                return false;
            }
        }
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new FrmPermissionMaker().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdGenerate;
    private javax.swing.JButton cmdLoad;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree tree;
    private javax.swing.JTextArea txtPermission;
    // End of variables declaration//GEN-END:variables
}
