/*
 * FrmEditTerm.java
 *
 * Created on Mar 8, 2011, 10:13:40 PM
 */
package ilearn.term;

import ilearn.kernel.Utilities;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmEditTerm extends javax.swing.JInternalFrame
{

    /** Creates new form FrmEditTerm */
    public FrmEditTerm()
    {
        initComponents();
        tblTerms.setModel(Term.getTermList());
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
        termTabbedPane = new javax.swing.JTabbedPane();
        termPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTerms = new javax.swing.JTable();
        cmdCancel2 = new javax.swing.JButton();
        cmdNExt = new javax.swing.JButton();
        detailsPanel = new javax.swing.JPanel();
        lblTermID = new javax.swing.JLabel();
        txtTermID = new javax.swing.JTextField();
        lblTermCode = new javax.swing.JLabel();
        txtTermCode = new javax.swing.JTextField();
        lblShortName = new javax.swing.JLabel();
        txtShortName = new javax.swing.JTextField();
        lblLongName = new javax.swing.JLabel();
        txtLongName = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox();
        cmdCancel = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(FrmEditTerm.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setToolTipText(resourceMap.getString("Form.toolTipText")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        termTabbedPane.setName("termTabbedPane"); // NOI18N
        termPanel.setName("termPanel"); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        tblTerms.setAutoCreateRowSorter(true);
        tblTerms.setModel(new javax.swing.table.DefaultTableModel(
                              new Object [][]
                              {

                              },
                              new String []
                              {
                                  "ID", "Term Code", "Short Name", "Long Name"
                              }
                          )
        {
            boolean[] canEdit = new boolean []
            {
                false, true, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        tblTerms.setToolTipText(resourceMap.getString("tblTerms.toolTipText")); // NOI18N
        tblTerms.setName("tblTerms"); // NOI18N
        tblTerms.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tblTermsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTerms);
        tblTerms.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblTerms.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblTerms.columnModel.title0")); // NOI18N
        tblTerms.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblTerms.columnModel.title1")); // NOI18N
        tblTerms.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblTerms.columnModel.title2")); // NOI18N
        tblTerms.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblTerms.columnModel.title3")); // NOI18N
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(FrmEditTerm.class, this);
        cmdCancel2.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel2.setText(resourceMap.getString("cmdCancel2.text")); // NOI18N
        cmdCancel2.setName("cmdCancel2"); // NOI18N
        cmdNExt.setAction(actionMap.get("next")); // NOI18N
        cmdNExt.setText(resourceMap.getString("cmdNExt.text")); // NOI18N
        cmdNExt.setName("cmdNExt"); // NOI18N
        javax.swing.GroupLayout termPanelLayout = new javax.swing.GroupLayout(termPanel);
        termPanel.setLayout(termPanelLayout);
        termPanelLayout.setHorizontalGroup(
            termPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, termPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(termPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                                .addGroup(termPanelLayout.createSequentialGroup()
                                          .addComponent(cmdNExt)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel2)))
                      .addContainerGap())
        );
        termPanelLayout.setVerticalGroup(
            termPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, termPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addGroup(termPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel2)
                                .addComponent(cmdNExt))
                      .addContainerGap())
        );
        termTabbedPane.addTab(resourceMap.getString("termPanel.TabConstraints.tabTitle"), resourceMap.getIcon("termPanel.TabConstraints.tabIcon"), termPanel); // NOI18N
        detailsPanel.setName("detailsPanel"); // NOI18N
        lblTermID.setText(resourceMap.getString("lblTermID.text")); // NOI18N
        lblTermID.setName("lblTermID"); // NOI18N
        txtTermID.setEditable(false);
        txtTermID.setText(resourceMap.getString("txtTermID.text")); // NOI18N
        txtTermID.setToolTipText(resourceMap.getString("txtTermID.toolTipText")); // NOI18N
        txtTermID.setName("txtTermID"); // NOI18N
        lblTermCode.setText(resourceMap.getString("lblTermCode.text")); // NOI18N
        lblTermCode.setName("lblTermCode"); // NOI18N
        txtTermCode.setToolTipText(resourceMap.getString("txtTermCode.toolTipText")); // NOI18N
        txtTermCode.setName("txtTermCode"); // NOI18N
        lblShortName.setText(resourceMap.getString("lblShortName.text")); // NOI18N
        lblShortName.setName("lblShortName"); // NOI18N
        txtShortName.setToolTipText(resourceMap.getString("txtShortName.toolTipText")); // NOI18N
        txtShortName.setName("txtShortName"); // NOI18N
        lblLongName.setText(resourceMap.getString("lblLongName.text")); // NOI18N
        lblLongName.setName("lblLongName"); // NOI18N
        txtLongName.setToolTipText(resourceMap.getString("txtLongName.toolTipText")); // NOI18N
        txtLongName.setName("txtLongName"); // NOI18N
        lblStatus.setText(resourceMap.getString("lblStatus.text")); // NOI18N
        lblStatus.setName("lblStatus"); // NOI18N
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));
        cmbStatus.setToolTipText(resourceMap.getString("cmbStatus.toolTipText")); // NOI18N
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N
        cmdSave.setAction(actionMap.get("save")); // NOI18N
        cmdSave.setName("cmdSave"); // NOI18N
        javax.swing.GroupLayout detailsPanelLayout = new javax.swing.GroupLayout(detailsPanel);
        detailsPanel.setLayout(detailsPanelLayout);
        detailsPanelLayout.setHorizontalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailsPanelLayout.createSequentialGroup()
                                          .addComponent(cmdSave)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel)
                                          .addContainerGap())
                                .addGroup(detailsPanelLayout.createSequentialGroup()
                                          .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(lblTermCode)
                                                  .addComponent(lblShortName)
                                                  .addComponent(lblLongName)
                                                  .addComponent(lblStatus)
                                                  .addComponent(lblTermID))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addGroup(detailsPanelLayout.createSequentialGroup()
                                                          .addComponent(txtTermID, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                                                          .addContainerGap())
                                                  .addGroup(detailsPanelLayout.createSequentialGroup()
                                                          .addComponent(cmbStatus, 0, 311, Short.MAX_VALUE)
                                                          .addContainerGap())
                                                  .addGroup(detailsPanelLayout.createSequentialGroup()
                                                          .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                  .addComponent(txtLongName, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                                                                  .addComponent(txtShortName, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                                                                  .addComponent(txtTermCode, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE))
                                                          .addGap(13, 13, 13))))))
        );
        detailsPanelLayout.setVerticalGroup(
            detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTermID)
                                .addComponent(txtTermID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTermCode)
                                .addComponent(txtTermCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblShortName)
                                .addComponent(txtShortName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblLongName)
                                .addComponent(txtLongName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblStatus)
                                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                      .addGroup(detailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel)
                                .addComponent(cmdSave))
                      .addContainerGap())
        );
        termTabbedPane.addTab(resourceMap.getString("detailsPanel.TabConstraints.tabTitle"), resourceMap.getIcon("detailsPanel.TabConstraints.tabIcon"), detailsPanel); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(termTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                      .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(termTabbedPane)
                      .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblTermsMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblTermsMouseClicked
    {
//GEN-HEADEREND:event_tblTermsMouseClicked
        if (evt.getClickCount() >= 2)
        {
            if (tblTerms.getSelectedRow() != -1)
            {
                loadSelectedTerm();
                next();
            }
        }
    }//GEN-LAST:event_tblTermsMouseClicked

    private void loadSelectedTerm()
    {
        txtTermID.setText(String.valueOf(tblTerms.getValueAt(tblTerms.getSelectedRow(), 0)));
        txtTermCode.setText(String.valueOf(tblTerms.getValueAt(tblTerms.getSelectedRow(), 1)));
        txtShortName.setText(String.valueOf(tblTerms.getValueAt(tblTerms.getSelectedRow(), 2)));
        txtLongName.setText(String.valueOf(tblTerms.getValueAt(tblTerms.getSelectedRow(), 3)));
        cmbStatus.setSelectedItem(String.valueOf(tblTerms.getValueAt(tblTerms.getSelectedRow(), 4)));
    }

    @Action
    public void cancel()
    {
        Utilities.showCancelScreen(this);
    }

    @Action
    public void next()
    {
        loadSelectedTerm();
        termTabbedPane.setSelectedIndex(termTabbedPane.getSelectedIndex() + 1);
    }

    @Action
    public void save()
    {
        String ID = txtTermID.getText().trim();
        String termCode = txtTermCode.getText().trim();
        String shortName = txtShortName.getText().trim();
        String longName = txtLongName.getText().trim();
        String status = cmbStatus.getSelectedItem().toString().trim();
        if (Term.updateTerm(ID, termCode, shortName, longName, status))
        {
            //reload the list
            tblTerms.setModel(Term.getTermList());
            String message = "Succesfully updated the term's information.";
            Utilities.showInfoMessage(rootPane, message);
        }
        else
        {
            String message = "An error occurred while updating the term's information.\n"
                             + "Kindly verify your information and try again.\n"
                             + "If the problem persists, kindly contact your system administrator.";
            Utilities.showErrorMessage(rootPane, message);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbStatus;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdCancel2;
    private javax.swing.JButton cmdNExt;
    private javax.swing.JButton cmdSave;
    private javax.swing.JPanel detailsPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLongName;
    private javax.swing.JLabel lblShortName;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTermCode;
    private javax.swing.JLabel lblTermID;
    private javax.swing.JTable tblTerms;
    private javax.swing.JPanel termPanel;
    private javax.swing.JTabbedPane termTabbedPane;
    private javax.swing.JTextField txtLongName;
    private javax.swing.JTextField txtShortName;
    private javax.swing.JTextField txtTermCode;
    private javax.swing.JTextField txtTermID;
    // End of variables declaration//GEN-END:variables
}
