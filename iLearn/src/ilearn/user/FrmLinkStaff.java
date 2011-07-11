/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmLinkStaff.java
 *
 * Created on Jul 10, 2011, 8:40:53 AM
 */
package ilearn.user;

import ilearn.kernel.TableColumnAdjuster;
import ilearn.kernel.Utilities;
import ilearn.staff.Staff;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmLinkStaff extends javax.swing.JDialog
{

    /** Creates new form FrmLinkStaff */
    public FrmLinkStaff(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        populateLists();
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
        tblStaff = new javax.swing.JTable();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        cmdCancel = new javax.swing.JButton();
        cmdLink = new javax.swing.JButton();
        lblResults1 = new javax.swing.JLabel();
        lblResults = new javax.swing.JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(FrmLinkStaff.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setModal(true);
        setName("Form"); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        tblStaff.setModel(new javax.swing.table.DefaultTableModel(
                              new Object [][]
                              {

                              },
                              new String []
                              {
                                  "ID", "Code", "Name"
                              }
                          ));
        tblStaff.setColumnSelectionAllowed(true);
        tblStaff.setName("tblStaff"); // NOI18N
        jScrollPane1.setViewportView(tblStaff);
        tblStaff.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblStaff.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblStaff.columnModel.title0")); // NOI18N
        tblStaff.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblStaff.columnModel.title1")); // NOI18N
        tblStaff.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblStaff.columnModel.title2")); // NOI18N
        lblSearch.setText(resourceMap.getString("lblSearch.text")); // NOI18N
        lblSearch.setName("lblSearch"); // NOI18N
        txtSearch.setText(resourceMap.getString("txtSearch.text")); // NOI18N
        txtSearch.setName("txtSearch"); // NOI18N
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(FrmLinkStaff.class, this);
        cmdSearch.setAction(actionMap.get("search")); // NOI18N
        cmdSearch.setText(resourceMap.getString("cmdSearch.text")); // NOI18N
        cmdSearch.setName("cmdSearch"); // NOI18N
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setText(resourceMap.getString("cmdCancel.text")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N
        cmdLink.setAction(actionMap.get("addLink")); // NOI18N
        cmdLink.setText(resourceMap.getString("cmdLink.text")); // NOI18N
        cmdLink.setName("cmdLink"); // NOI18N
        lblResults1.setText(resourceMap.getString("lblResults1.text")); // NOI18N
        lblResults1.setName("lblResults1"); // NOI18N
        lblResults.setText(resourceMap.getString("lblResults.text")); // NOI18N
        lblResults.setName("lblResults"); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                          .addComponent(lblResults1)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(lblResults)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                                          .addComponent(cmdLink)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                          .addComponent(lblSearch)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(cmdSearch))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
                      .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSearch)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmdSearch))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel)
                                .addComponent(lblResults1)
                                .addComponent(lblResults)
                                .addComponent(cmdLink))
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
    public void addLink()
    {
        if (tblStaff.getSelectedRow() != -1)
        {
            for (int rowID : tblStaff.getSelectedRows())
            {
                String id = tblStaff.getValueAt(rowID, 0).toString();
                String code = tblStaff.getValueAt(rowID, 1).toString();
                String teacher = tblStaff.getValueAt(rowID, 2).toString();
                User.addStaffLink(id, code, teacher);
            }
            this.dispose();
        }
        else
        {
            String message = "Kindly select a Staff member before clicking add.";
            Utilities.showWarningMessage(rootPane, message);
        }
    }

    private void populateLists()
    {
        String criteria = txtSearch.getText().trim();
        tblStaff.setModel(Staff.searchStaffListTableModel(criteria));
        TableColumnAdjuster tca = new TableColumnAdjuster(tblStaff);
        tca.adjustColumns();
        lblResults.setText(String.valueOf(tblStaff.getRowCount()));
    }

    @Action
    public void search()
    {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdLink;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblResults;
    private javax.swing.JLabel lblResults1;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JTable tblStaff;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
