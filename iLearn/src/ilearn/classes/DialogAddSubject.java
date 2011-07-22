/*
 * DialogAddSubject.java
 *
 * Created on Apr 29, 2011, 2:33:30 AM
 */
package ilearn.classes;

import ilearn.kernel.Utilities;
import ilearn.subject.Subject;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class DialogAddSubject extends javax.swing.JDialog
{

    /** Creates new form DialogAddSubject */
    public DialogAddSubject(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        search();
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
        searchPanel = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblResults = new javax.swing.JTable();
        lblResults1 = new javax.swing.JLabel();
        lblResults2 = new javax.swing.JLabel();
        cmdCancel1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(DialogAddSubject.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setAlwaysOnTop(true);
        setModal(true);
        setName("Form"); // NOI18N
        searchPanel.setName("searchPanel"); // NOI18N
        lblSearch.setText(resourceMap.getString("lblSearch.text")); // NOI18N
        lblSearch.setName("lblSearch"); // NOI18N
        txtSearch.setName("txtSearch"); // NOI18N
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(DialogAddSubject.class, this);
        cmdSearch.setAction(actionMap.get("search")); // NOI18N
        cmdSearch.setName("cmdSearch"); // NOI18N
        jScrollPane3.setName("jScrollPane3"); // NOI18N
        tblResults.setAutoCreateRowSorter(true);
        tblResults.setModel(new javax.swing.table.DefaultTableModel(
                                new Object [][]
                                {

                                },
                                new String []
                                {
                                    "ID", "Code", "Title"
                                }
                            ));
        tblResults.setName("tblResults"); // NOI18N
        tblResults.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tblResultsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblResults);
        lblResults1.setText(resourceMap.getString("lblResults1.text")); // NOI18N
        lblResults1.setName("lblResults1"); // NOI18N
        lblResults2.setText(resourceMap.getString("lblResults2.text")); // NOI18N
        lblResults2.setName("lblResults2"); // NOI18N
        cmdCancel1.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel1.setName("cmdCancel1"); // NOI18N
        jButton1.setAction(actionMap.get("add")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchPanelLayout.createSequentialGroup()
                                          .addComponent(lblSearch)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdSearch))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchPanelLayout.createSequentialGroup()
                                          .addComponent(lblResults1)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(lblResults2)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                                          .addComponent(jButton1)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel1)))
                      .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSearch)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmdSearch))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblResults1)
                                .addComponent(lblResults2)
                                .addComponent(cmdCancel1)
                                .addComponent(jButton1))
                      .addContainerGap())
        );
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblResultsMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblResultsMouseClicked
    {
//GEN-HEADEREND:event_tblResultsMouseClicked
        if (evt.getClickCount() >= 2)
        {
            add();
        }
    }//GEN-LAST:event_tblResultsMouseClicked

    @Action
    public void cancel()
    {
        this.dispose();
    }

    @Action
    public void add()
    {
        if (tblResults.getSelectedRow() != -1)
        {
            for (int rowID : tblResults.getSelectedRows())
            {
                String id = tblResults.getValueAt(rowID, 0).toString();
                String code = tblResults.getValueAt(rowID, 1).toString();
                String teacher = tblResults.getValueAt(rowID, 2).toString();
                String title = tblResults.getValueAt(rowID, 3).toString();
                Classes.addSubject(id, code, title, teacher);
            }
            this.dispose();
        }
        else
        {
            String message = "Kindly select a subject before clicking add.";
            Utilities.showWarningMessage(rootPane, message);
        }
    }

    @Action
    public void search()
    {
        String criteria = txtSearch.getText().trim();
        tblResults.setModel(Subject.getSubjectList(criteria));
        lblResults2.setText(String.valueOf(tblResults.getRowCount()));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCancel1;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblResults1;
    private javax.swing.JLabel lblResults2;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTable tblResults;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
