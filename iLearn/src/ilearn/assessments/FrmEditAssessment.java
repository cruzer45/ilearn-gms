/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmCreateAssessment.java
 *
 * Created on Apr 30, 2011, 7:41:29 PM
 */
package ilearn.assessments;

import ilearn.classes.Classes;
import ilearn.kernel.TableColumnAdjuster;
import ilearn.kernel.Utilities;
import ilearn.term.Term;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmEditAssessment extends javax.swing.JInternalFrame
{

    /** Creates new form FrmCreateAssessment */
    public FrmEditAssessment()
    {
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
        assmtTabbedPane = new javax.swing.JTabbedPane();
        searchPanel = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSearch = new javax.swing.JTable();
        cmdCancel2 = new javax.swing.JButton();
        cmdNext2 = new javax.swing.JButton();
        generalPanel = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        calDate = new com.toedter.calendar.JDateChooser();
        lblDate = new javax.swing.JLabel();
        txtMaxPoints = new javax.swing.JTextField();
        lblMaxPoints = new javax.swing.JLabel();
        lblClass = new javax.swing.JLabel();
        cmbClass = new javax.swing.JComboBox();
        lblSubject = new javax.swing.JLabel();
        cmbSubject = new javax.swing.JComboBox();
        lblType = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox();
        cmdCancel1 = new javax.swing.JButton();
        cmdNext = new javax.swing.JButton();
        lblID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        gradesPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGrades = new javax.swing.JTable();
        cmdCancel = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(FrmEditAssessment.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        assmtTabbedPane.setName("assmtTabbedPane"); // NOI18N
        searchPanel.setName("searchPanel"); // NOI18N
        lblSearch.setText(resourceMap.getString("lblSearch.text")); // NOI18N
        lblSearch.setName("lblSearch"); // NOI18N
        txtSearch.setText(resourceMap.getString("txtSearch.text")); // NOI18N
        txtSearch.setName("txtSearch"); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtSearchKeyPressed(evt);
            }
        });
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(FrmEditAssessment.class, this);
        cmdSearch.setAction(actionMap.get("search")); // NOI18N
        cmdSearch.setText(resourceMap.getString("cmdSearch.text")); // NOI18N
        cmdSearch.setName("cmdSearch"); // NOI18N
        jScrollPane2.setName("jScrollPane2"); // NOI18N
        tblSearch.setAutoCreateRowSorter(true);
        tblSearch.setModel(new javax.swing.table.DefaultTableModel(
                               new Object [][]
                               {

                               },
                               new String []
                               {
                                   "ID", "Title", "Date", "Class", "Subject"
                               }
                           )
        {
            Class[] types = new Class []
            {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }
        });
        tblSearch.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
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
        tblSearch.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblSearch.columnModel.title1")); // NOI18N
        tblSearch.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblSearch.columnModel.title2")); // NOI18N
        tblSearch.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblSearch.columnModel.title3")); // NOI18N
        tblSearch.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("tblSearch.columnModel.title4")); // NOI18N
        cmdCancel2.setAction(actionMap.get("Cancel")); // NOI18N
        cmdCancel2.setText(resourceMap.getString("cmdCancel2.text")); // NOI18N
        cmdCancel2.setName("cmdCancel2"); // NOI18N
        cmdNext2.setAction(actionMap.get("next")); // NOI18N
        cmdNext2.setText(resourceMap.getString("cmdNext2.text")); // NOI18N
        cmdNext2.setName("cmdNext2"); // NOI18N
        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                                .addGroup(searchPanelLayout.createSequentialGroup()
                                          .addComponent(cmdNext2)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel2))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchPanelLayout.createSequentialGroup()
                                          .addComponent(lblSearch)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
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
                      .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel2)
                                .addComponent(cmdNext2))
                      .addContainerGap())
        );
        assmtTabbedPane.addTab(resourceMap.getString("searchPanel.TabConstraints.tabTitle"), resourceMap.getIcon("searchPanel.TabConstraints.tabIcon"), searchPanel); // NOI18N
        generalPanel.setName("generalPanel"); // NOI18N
        lblTitle.setText(resourceMap.getString("lblTitle.text")); // NOI18N
        lblTitle.setName("lblTitle"); // NOI18N
        txtTitle.setText(resourceMap.getString("txtTitle.text")); // NOI18N
        txtTitle.setName("txtTitle"); // NOI18N
        calDate.setName("calDate"); // NOI18N
        lblDate.setText(resourceMap.getString("lblDate.text")); // NOI18N
        lblDate.setName("lblDate"); // NOI18N
        txtMaxPoints.setText(resourceMap.getString("txtMaxPoints.text")); // NOI18N
        txtMaxPoints.setName("txtMaxPoints"); // NOI18N
        lblMaxPoints.setText(resourceMap.getString("lblMaxPoints.text")); // NOI18N
        lblMaxPoints.setName("lblMaxPoints"); // NOI18N
        lblClass.setText(resourceMap.getString("lblClass.text")); // NOI18N
        lblClass.setName("lblClass"); // NOI18N
        cmbClass.setName("cmbClass"); // NOI18N
        cmbClass.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cmbClassActionPerformed(evt);
            }
        });
        lblSubject.setText(resourceMap.getString("lblSubject.text")); // NOI18N
        lblSubject.setName("lblSubject"); // NOI18N
        cmbSubject.setName("cmbSubject"); // NOI18N
        cmbSubject.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cmbSubjectActionPerformed(evt);
            }
        });
        lblType.setText(resourceMap.getString("lblType.text")); // NOI18N
        lblType.setName("lblType"); // NOI18N
        cmbType.setName("cmbType"); // NOI18N
        cmdCancel1.setAction(actionMap.get("Cancel")); // NOI18N
        cmdCancel1.setName("cmdCancel1"); // NOI18N
        cmdNext.setAction(actionMap.get("next")); // NOI18N
        cmdNext.setText(resourceMap.getString("cmdNext.text")); // NOI18N
        cmdNext.setName("cmdNext"); // NOI18N
        lblID.setText(resourceMap.getString("lblID.text")); // NOI18N
        lblID.setName("lblID"); // NOI18N
        txtID.setEditable(false);
        txtID.setText(resourceMap.getString("txtID.text")); // NOI18N
        txtID.setName("txtID"); // NOI18N
        javax.swing.GroupLayout generalPanelLayout = new javax.swing.GroupLayout(generalPanel);
        generalPanel.setLayout(generalPanelLayout);
        generalPanelLayout.setHorizontalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generalPanelLayout.createSequentialGroup()
                                          .addComponent(cmdNext)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel1))
                                .addGroup(generalPanelLayout.createSequentialGroup()
                                          .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(lblTitle)
                                                  .addComponent(lblDate)
                                                  .addComponent(lblMaxPoints)
                                                  .addComponent(lblClass)
                                                  .addComponent(lblSubject)
                                                  .addComponent(lblType)
                                                  .addComponent(lblID))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                                  .addComponent(cmbType, 0, 315, Short.MAX_VALUE)
                                                  .addComponent(txtMaxPoints, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                                  .addComponent(calDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                                  .addComponent(txtTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                                  .addComponent(cmbClass, 0, 315, Short.MAX_VALUE)
                                                  .addComponent(cmbSubject, 0, 315, Short.MAX_VALUE))))
                      .addContainerGap())
        );
        generalPanelLayout.setVerticalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblID)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblType)
                                .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTitle)
                                .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(calDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDate))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaxPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblMaxPoints))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblClass)
                                .addComponent(cmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSubject)
                                .addComponent(cmbSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel1)
                                .addComponent(cmdNext))
                      .addContainerGap())
        );
        assmtTabbedPane.addTab(resourceMap.getString("generalPanel.TabConstraints.tabTitle"), resourceMap.getIcon("generalPanel.TabConstraints.tabIcon"), generalPanel); // NOI18N
        gradesPanel.setName("gradesPanel"); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        tblGrades.setAutoCreateRowSorter(true);
        tblGrades.setModel(new javax.swing.table.DefaultTableModel(
                               new Object [][]
                               {

                               },
                               new String []
                               {
                                   "ID", "First Name", "Last Name", "Grade", "Remark"
                               }
                           )
        {
            Class[] types = new Class []
            {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }
        });
        tblGrades.setColumnSelectionAllowed(true);
        tblGrades.setName("tblGrades"); // NOI18N
        jScrollPane1.setViewportView(tblGrades);
        tblGrades.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblGrades.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("tblGrades.columnModel.title0")); // NOI18N
        tblGrades.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("tblGrades.columnModel.title1")); // NOI18N
        tblGrades.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("tblGrades.columnModel.title3")); // NOI18N
        tblGrades.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("tblGrades.columnModel.title2")); // NOI18N
        tblGrades.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("tblGrades.columnModel.title4")); // NOI18N
        cmdCancel.setAction(actionMap.get("Cancel")); // NOI18N
        cmdCancel.setText(resourceMap.getString("cmdCancel.text")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N
        cmdSave.setAction(actionMap.get("save")); // NOI18N
        cmdSave.setText(resourceMap.getString("cmdSave.text")); // NOI18N
        cmdSave.setName("cmdSave"); // NOI18N
        javax.swing.GroupLayout gradesPanelLayout = new javax.swing.GroupLayout(gradesPanel);
        gradesPanel.setLayout(gradesPanelLayout);
        gradesPanelLayout.setHorizontalGroup(
            gradesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradesPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(gradesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                                .addGroup(gradesPanelLayout.createSequentialGroup()
                                          .addComponent(cmdSave)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel)))
                      .addContainerGap())
        );
        gradesPanelLayout.setVerticalGroup(
            gradesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradesPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addGroup(gradesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel)
                                .addComponent(cmdSave))
                      .addContainerGap())
        );
        assmtTabbedPane.addTab(resourceMap.getString("gradesPanel.TabConstraints.tabTitle"), resourceMap.getIcon("gradesPanel.TabConstraints.tabIcon"), gradesPanel); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(assmtTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                      .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(assmtTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                      .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbClassActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmbClassActionPerformed
    {
//GEN-HEADEREND:event_cmbClassActionPerformed
        if (!cmbClass.getSelectedItem().toString().equals("--- Select One ---"))
        {
            ArrayList<String> classSubjects = Classes.getSubjectList(cmbClass.getSelectedItem().toString());
            classSubjects.add(0, "--- Select One ---");
            cmbSubject.setModel(new DefaultComboBoxModel(classSubjects.toArray()));
            DefaultTableModel model = Assessment.getStudentList(cmbClass.getSelectedItem().toString());
            tblGrades.setModel(model);
            JComboBox comboBox = new JComboBox(Assessment.getValidStates());
            comboBox.setEditable(true);
            DefaultCellEditor editor = new DefaultCellEditor(comboBox);
            // Assign the editor to the fourth column
            TableColumnModel tcm = tblGrades.getColumnModel();
            tcm.getColumn(3).setCellEditor(editor);
            //Get the grades and if the class changed.
            getStudentGrades();
        }
    }//GEN-LAST:event_cmbClassActionPerformed

    private void cmbSubjectActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmbSubjectActionPerformed
    {
//GEN-HEADEREND:event_cmbSubjectActionPerformed
        next();
    }//GEN-LAST:event_cmbSubjectActionPerformed

    private void tblSearchMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tblSearchMouseClicked
    {
//GEN-HEADEREND:event_tblSearchMouseClicked
        if (evt.getClickCount() >= 2)
        {
            loadInfo();
        }
    }//GEN-LAST:event_tblSearchMouseClicked

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtSearchKeyPressed
    {
//GEN-HEADEREND:event_txtSearchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            search();
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    @Action
    public void Cancel()
    {
        Utilities.showCancelScreen(this);
    }

    @Action
    public void save()
    {
        String assmtTerm = Term.getCurrentTerm(),
               assmtSubject = cmbSubject.getSelectedItem().toString(),
               assmtTeacher = "",
               assmtTitle = txtTitle.getText().trim(),
               assmtDate = Utilities.YMD_Formatter.format(calDate.getDate()),
               assmtType = cmbType.getSelectedItem().toString(),
               assmtTotalPoints = txtMaxPoints.getText().trim(),
               assmtClassID = Classes.getClassID(cmbClass.getSelectedItem().toString()),
               assmtID = txtID.getText().trim();
        ArrayList<String> stuID = new ArrayList<String>(),
        grade = new ArrayList<String>(),
        remarks = new ArrayList<String>();
        //get the values from the tables.
        for (int i = 0; i < tblGrades.getRowCount(); i++)
        {
            stuID.add(tblGrades.getValueAt(i, 0).toString());
            grade.add(tblGrades.getValueAt(i, 3).toString());
            remarks.add(tblGrades.getValueAt(i, 4).toString());
        }
        //Update the assessment.
        boolean updateAssessment = Assessment.updateAssessment(assmtID, assmtSubject, assmtTitle, assmtDate, assmtType, assmtTotalPoints, assmtClassID);
        //save the grades
        boolean updateGrades = Assessment.updateGrades(assmtID, stuID, grade, remarks);
        if (updateAssessment && updateGrades)
        {
            String message = "The assessment was successfully updated. \n"
                             + "Would you like to update another?";
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
            String message = "An error occurred while trying to update this assessment.\n"
                             + "Kindly verify your information and try again.";
            Utilities.showErrorMessage(rootPane, message);
        }
    }

    private void resetForm()
    {
        remove(assmtTabbedPane);
        initComponents();
        populateLists();
    }

    private void populateLists()
    {
        String criteria = txtSearch.getText().trim();
        tblSearch.setModel(Assessment.getAssessmentTable(criteria));
        TableColumnAdjuster tca = new TableColumnAdjuster(tblSearch);
        tca.adjustColumns();
        ArrayList<String> assmtTypes = Assessment.getAssessmentTypes();
        cmbType.setModel(new DefaultComboBoxModel(assmtTypes.toArray()));
        cmbType.setSelectedItem("Home Work");
        ArrayList<String> classList = Classes.getClassList();
        classList.add(0, "--- Select One ---");
        cmbClass.setModel(new DefaultComboBoxModel(classList.toArray()));
    }

    private void loadInfo()
    {
        //Load the assessment info
        String id = tblSearch.getValueAt(tblSearch.getSelectedRow(), 0).toString();
        ArrayList<Object> assmt = Assessment.getAssessmentInfo(id);
        txtID.setText(assmt.get(0).toString());
        cmbType.setSelectedItem(assmt.get(1));
        txtTitle.setText(assmt.get(2).toString());
        try
        {
            calDate.setDate(Utilities.MDY_Formatter.parse(assmt.get(3).toString()));
        }
        catch (ParseException ex)
        {
        }
        txtMaxPoints.setText(assmt.get(4).toString());
        cmbClass.setSelectedItem(assmt.get(5).toString());
        cmbSubject.setSelectedItem(assmt.get(6).toString());
        //Load the grades table.
        DefaultTableModel model = Assessment.getStudentList(cmbClass.getSelectedItem().toString());
        tblGrades.setModel(model);
        JComboBox comboBox = new JComboBox(Assessment.getValidStates());
        comboBox.setEditable(true);
        DefaultCellEditor editor = new DefaultCellEditor(comboBox);
        // Assign the editor to the fourth column
        TableColumnModel tcm = tblGrades.getColumnModel();
        tcm.getColumn(3).setCellEditor(editor);
        getStudentGrades();
        next();
    }

    @Action
    public void next()
    {
        if (tblSearch.getSelectedRow() != -1)
        {
            assmtTabbedPane.setSelectedIndex(assmtTabbedPane.getSelectedIndex() + 1);
        }
    }

    private void getStudentGrades()
    {
        //Load the assessment info
        String id = tblSearch.getValueAt(tblSearch.getSelectedRow(), 0).toString();
        ArrayList<Object> assmt = Assessment.getAssessmentInfo(id);
        //Get the students grades
        for (int i = 0; i < tblGrades.getRowCount(); i++)
        {
            String stuID = tblGrades.getValueAt(i, 0).toString();
            ArrayList<String> stuGrade = Assessment.getStudentGrade(assmt.get(0).toString(), stuID);
            try
            {
                tblGrades.setValueAt(stuGrade.get(0), i, 3);
                tblGrades.setValueAt(stuGrade.get(1), i, 4);
            }
            catch (Exception e)
            {
                Assessment.addBlankGrades(assmt.get(0).toString(), stuID);
            }
        }
    }

    private boolean canEnterGrades()
    {
        boolean canProceed = false;
        if (!cmbClass.getSelectedItem().toString().equals("--- Select One ---"))
        {
            if (!cmbSubject.getSelectedItem().toString().equals("--- Select One ---"))
            {
                canProceed = true;
            }
        }
        return canProceed;
    }

    @Action
    public void search()
    {
        String criteria = txtSearch.getText().trim();
        tblSearch.setModel(Assessment.getAssessmentTable(criteria));
        TableColumnAdjuster tca = new TableColumnAdjuster(tblSearch);
        tca.adjustColumns();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane assmtTabbedPane;
    private com.toedter.calendar.JDateChooser calDate;
    private javax.swing.JComboBox cmbClass;
    private javax.swing.JComboBox cmbSubject;
    private javax.swing.JComboBox cmbType;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdCancel1;
    private javax.swing.JButton cmdCancel2;
    private javax.swing.JButton cmdNext;
    private javax.swing.JButton cmdNext2;
    private javax.swing.JButton cmdSave;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JPanel generalPanel;
    private javax.swing.JPanel gradesPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblClass;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblMaxPoints;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblSubject;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblType;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTable tblGrades;
    private javax.swing.JTable tblSearch;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMaxPoints;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables
}
