/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmEditMidTerms.java
 *
 * Created on Jul 9, 2011, 7:36:34 AM
 */
package ilearn.grades;

import ilearn.kernel.TableColumnAdjuster;
import ilearn.kernel.Utilities;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmEditMidTerms extends javax.swing.JInternalFrame
{

    /** Creates new form FrmEditMidTerms */
    public FrmEditMidTerms()
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
        midTabbedPane = new javax.swing.JTabbedPane();
        searchPanel = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSearch = new javax.swing.JTable();
        cmdCancel2 = new javax.swing.JButton();
        cmdNext2 = new javax.swing.JButton();
        gradesPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGrades = new javax.swing.JTable();
        cmdCancel = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        lblClass = new javax.swing.JLabel();
        txtClass = new javax.swing.JTextField();
        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(FrmEditMidTerms.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        midTabbedPane.setName("midTabbedPane"); // NOI18N
        searchPanel.setName("searchPanel"); // NOI18N
        lblSearch.setText(resourceMap.getString("lblSearch.text")); // NOI18N
        lblSearch.setName("lblSearch"); // NOI18N
        txtSearch.setName("txtSearch"); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtSearchKeyPressed(evt);
            }
        });
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(FrmEditMidTerms.class, this);
        cmdSearch.setAction(actionMap.get("search")); // NOI18N
        cmdSearch.setName("cmdSearch"); // NOI18N
        jScrollPane2.setName("jScrollPane2"); // NOI18N
        tblSearch.setAutoCreateRowSorter(true);
        tblSearch.setModel(new javax.swing.table.DefaultTableModel(
                               new Object [][]
                               {

                               },
                               new String []
                               {
                                   "ID", "Class", "Name"
                               }
                           )
        {
            Class[] types = new Class []
            {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }
        });
        tblSearch.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblSearch.setColumnSelectionAllowed(true);
        tblSearch.setName("tblSearch"); // NOI18N
        tblSearch.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                tblSearchMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSearch);
        tblSearch.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSearch.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblSearch.columnModel.title0")); // NOI18N
        tblSearch.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblSearch.columnModel.title3")); // NOI18N
        tblSearch.getColumnModel().getColumn(2).setResizable(false);
        tblSearch.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblSearch.columnModel.title2")); // NOI18N
        cmdCancel2.setAction(actionMap.get("Cancel")); // NOI18N
        cmdCancel2.setName("cmdCancel2"); // NOI18N
        cmdNext2.setAction(actionMap.get("next")); // NOI18N
        cmdNext2.setName("cmdNext2"); // NOI18N
        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                .addGroup(searchPanelLayout.createSequentialGroup()
                                          .addComponent(cmdNext2)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel2))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchPanelLayout.createSequentialGroup()
                                          .addComponent(lblSearch)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdSearch)))
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
                      .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel2)
                                .addComponent(cmdNext2))
                      .addContainerGap())
        );
        midTabbedPane.addTab(resourceMap.getString("searchPanel.TabConstraints.tabTitle"), resourceMap.getIcon("searchPanel.TabConstraints.tabIcon"), searchPanel); // NOI18N
        gradesPanel.setName("gradesPanel"); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        tblGrades.setAutoCreateRowSorter(true);
        tblGrades.setModel(new javax.swing.table.DefaultTableModel(
                               new Object [][]
                               {

                               },
                               new String []
                               {
                                   "Subject", "Grade", "Remark"
                               }
                           )
        {
            Class[] types = new Class []
            {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }
        });
        tblGrades.setColumnSelectionAllowed(true);
        tblGrades.setName("tblGrades"); // NOI18N
        jScrollPane1.setViewportView(tblGrades);
        tblGrades.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblGrades.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblGrades.columnModel.title1")); // NOI18N
        tblGrades.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblGrades.columnModel.title2")); // NOI18N
        tblGrades.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblGrades.columnModel.title3")); // NOI18N
        cmdCancel.setAction(actionMap.get("Cancel")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N
        cmdSave.setAction(actionMap.get("save")); // NOI18N
        cmdSave.setName("cmdSave"); // NOI18N
        lblName.setText(resourceMap.getString("lblName.text")); // NOI18N
        lblName.setName("lblName"); // NOI18N
        txtName.setText(resourceMap.getString("txtName.text")); // NOI18N
        txtName.setName("txtName"); // NOI18N
        lblID.setText(resourceMap.getString("lblID.text")); // NOI18N
        lblID.setName("lblID"); // NOI18N
        txtID.setText(resourceMap.getString("txtID.text")); // NOI18N
        txtID.setName("txtID"); // NOI18N
        lblClass.setText(resourceMap.getString("lblClass.text")); // NOI18N
        lblClass.setName("lblClass"); // NOI18N
        txtClass.setText(resourceMap.getString("txtClass.text")); // NOI18N
        txtClass.setName("txtClass"); // NOI18N
        javax.swing.GroupLayout gradesPanelLayout = new javax.swing.GroupLayout(gradesPanel);
        gradesPanel.setLayout(gradesPanelLayout);
        gradesPanelLayout.setHorizontalGroup(
            gradesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradesPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(gradesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                .addGroup(gradesPanelLayout.createSequentialGroup()
                                          .addComponent(cmdSave)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, gradesPanelLayout.createSequentialGroup()
                                          .addGroup(gradesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(lblName)
                                                  .addComponent(lblID)
                                                  .addComponent(lblClass))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(gradesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(txtClass, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                                                  .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                                                  .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))))
                      .addContainerGap())
        );
        gradesPanelLayout.setVerticalGroup(
            gradesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradesPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(gradesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblID)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addGap(6, 6, 6)
                      .addGroup(gradesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblName)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(gradesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblClass)
                                .addComponent(txtClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addGroup(gradesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel)
                                .addComponent(cmdSave))
                      .addContainerGap())
        );
        midTabbedPane.addTab(resourceMap.getString("gradesPanel.TabConstraints.tabTitle"), resourceMap.getIcon("gradesPanel.TabConstraints.tabIcon"), gradesPanel); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(midTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                      .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(midTabbedPane)
                      .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void Cancel()
    {
        Utilities.showCancelScreen(this);
    }

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtSearchKeyPressed
    {
        //GEN-HEADEREND:event_txtSearchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            search();
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void tblSearchMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblSearchMouseClicked
    {
        //GEN-HEADEREND:event_tblSearchMouseClicked
        if (evt.getClickCount() >= 2)
        {
            loadInfo();
        }
    }//GEN-LAST:event_tblSearchMouseClicked

    @Action
    public void search()
    {
        String criteria = txtSearch.getText().trim();
        tblSearch.setModel(Grade.searchMidTermList(criteria));
        TableColumnAdjuster tca = new TableColumnAdjuster(tblSearch);
        tca.adjustColumns();
    }

    private void loadInfo()
    {
        if (tblSearch.getSelectedRow() != -1)
        {
            String stuID = tblSearch.getValueAt(tblSearch.getSelectedRow(), 0).toString();
            String name = tblSearch.getValueAt(tblSearch.getSelectedRow(), 1).toString();
            String cls = tblSearch.getValueAt(tblSearch.getSelectedRow(), 2).toString();
            txtID.setText(stuID);
            txtName.setText(name);
            txtClass.setText(cls);
            tblGrades.setModel(Grade.getMidTermGrades(stuID));
            TableColumnAdjuster tca = new TableColumnAdjuster(tblGrades);
            tca.adjustColumns();
            TableColumn tcm = tblGrades.getColumnModel().getColumn(0);
            tcm.setWidth(0);
            midTabbedPane.setSelectedIndex(midTabbedPane.getSelectedIndex() + 1);
        }
    }

    @Action
    public void next()
    {
        if (tblSearch.getSelectedRow() != -1)
        {
            loadInfo();
        }
    }

    @Action
    public void save()
    {
        ArrayList<String> graID = new ArrayList<String>();
        ArrayList<String> remarks = new ArrayList<String>();
        for (int i = 0; i < tblGrades.getRowCount(); i++)
        {
            String grID = tblGrades.getValueAt(i, 0).toString();
            String rem = tblGrades.getValueAt(i, 3).toString();
            if (!rem.isEmpty())
            {
                graID.add(grID);
                remarks.add(rem);
            }
        }
        boolean remarksUpdated = Grade.updateRemarks(graID, remarks);
        if (remarksUpdated)
        {
            String message = "The remarks were successfully saved.";
            Utilities.showInfoMessage(rootPane, message);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdCancel2;
    private javax.swing.JButton cmdNext2;
    private javax.swing.JButton cmdSave;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JPanel gradesPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblClass;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JTabbedPane midTabbedPane;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTable tblGrades;
    private javax.swing.JTable tblSearch;
    private javax.swing.JTextField txtClass;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
