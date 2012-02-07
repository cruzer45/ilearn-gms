/*
 * FrmRecordGrade.java
 *
 * Created on Apr 30, 2011, 7:41:29 PM
 */
package ilearn.grades;

import ilearn.classes.Classes;
import ilearn.kernel.Utilities;
import ilearn.subject.Subject;
import ilearn.term.Term;
import ilearn.user.User;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.application.Action;

/**
 *
 * @author mrogers
 */
public class FrmRecordGrade extends javax.swing.JInternalFrame
{

    String validationText = "";
    String gradesText = "";

    /** Creates new form FrmRecordGrade */
    public FrmRecordGrade()
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
        generalPanel = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        calDate = new com.toedter.calendar.JDateChooser();
        lblDate = new javax.swing.JLabel();
        lblMaxPoints = new javax.swing.JLabel();
        lblClass = new javax.swing.JLabel();
        cmbClass = new javax.swing.JComboBox();
        cmdCancel1 = new javax.swing.JButton();
        lblSubject = new javax.swing.JLabel();
        cmbSubject = new javax.swing.JComboBox();
        lblType = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        spinnerMaxPoints = new javax.swing.JSpinner();
        gradesPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGrades = new javax.swing.JTable();
        cmdCancel = new javax.swing.JButton();
        cmdSave = new javax.swing.JButton();
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmRecordGrade.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        assmtTabbedPane.setName("assmtTabbedPane"); // NOI18N
        generalPanel.setName("generalPanel"); // NOI18N
        lblTitle.setText(resourceMap.getString("lblTitle.text")); // NOI18N
        lblTitle.setName("lblTitle"); // NOI18N
        txtTitle.setText(resourceMap.getString("txtTitle.text")); // NOI18N
        txtTitle.setName("txtTitle"); // NOI18N
        calDate.setName("calDate"); // NOI18N
        lblDate.setText(resourceMap.getString("lblDate.text")); // NOI18N
        lblDate.setName("lblDate"); // NOI18N
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
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmRecordGrade.class, this);
        cmdCancel1.setAction(actionMap.get("Cancel")); // NOI18N
        cmdCancel1.setName("cmdCancel1"); // NOI18N
        lblSubject.setText(resourceMap.getString("lblSubject.text")); // NOI18N
        lblSubject.setName("lblSubject"); // NOI18N
        cmbSubject.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--- Select A Class ---" }));
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
        cmbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--- Select Subject ---" }));
        cmbType.setName("cmbType"); // NOI18N
        cmbType.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cmbTypeActionPerformed(evt);
            }
        });
        jButton1.setAction(actionMap.get("next")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        spinnerMaxPoints.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(100), Integer.valueOf(0), null, Integer.valueOf(1)));
        spinnerMaxPoints.setName("spinnerMaxPoints"); // NOI18N
        javax.swing.GroupLayout generalPanelLayout = new javax.swing.GroupLayout(generalPanel);
        generalPanel.setLayout(generalPanelLayout);
        generalPanelLayout.setHorizontalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generalPanelLayout.createSequentialGroup()
                                          .addComponent(jButton1)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel1))
                                .addGroup(generalPanelLayout.createSequentialGroup()
                                          .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(lblTitle)
                                                  .addComponent(lblDate)
                                                  .addComponent(lblMaxPoints)
                                                  .addComponent(lblType)
                                                  .addComponent(lblClass)
                                                  .addComponent(lblSubject))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(cmbSubject, javax.swing.GroupLayout.Alignment.TRAILING, 0, 288, Short.MAX_VALUE)
                                                  .addComponent(spinnerMaxPoints, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                                  .addComponent(calDate, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                                  .addComponent(txtTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                                                  .addComponent(cmbType, 0, 288, Short.MAX_VALUE)
                                                  .addComponent(cmbClass, javax.swing.GroupLayout.Alignment.TRAILING, 0, 288, Short.MAX_VALUE))))
                      .addContainerGap())
        );
        generalPanelLayout.setVerticalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblClass)
                                .addComponent(cmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSubject)
                                .addComponent(cmbSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(lblMaxPoints)
                                .addComponent(spinnerMaxPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel1)
                                .addComponent(jButton1))
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
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
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
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
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
                      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
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
                      .addComponent(assmtTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                      .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbClassActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmbClassActionPerformed
    {
//GEN-HEADEREND:event_cmbClassActionPerformed
        if (!cmbClass.getSelectedItem().toString().equals("--- Select One ---"))
        {
            ArrayList<String> classSubjects = new ArrayList<String>();
            if (User.getUserGroup().equals("Administration"))
            {
                classSubjects = Classes.getSubjectList(cmbClass.getSelectedItem().toString());
            }
            else
            {
                classSubjects.addAll(Classes.getPermittedSubjects(cmbClass.getSelectedItem().toString()));
            }
            classSubjects.add(0, "--- Select One ---");
            cmbSubject.setModel(new DefaultComboBoxModel(classSubjects.toArray()));
        }
    }//GEN-LAST:event_cmbClassActionPerformed

    private void cmbSubjectActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmbSubjectActionPerformed
    {
//GEN-HEADEREND:event_cmbSubjectActionPerformed
        String subID = Subject.getSubjectID(cmbSubject.getSelectedItem().toString());
        if (Subject.hasWeighting(subID))
        {
            ArrayList<String> assmtTypes = Subject.getSubjectAssessmentTypes(subID);
            cmbType.setModel(new DefaultComboBoxModel(assmtTypes.toArray()));
        }
        else
        {
            ArrayList<String> assmtTypes = Grade.getAssessmentTypes();
            cmbType.setModel(new DefaultComboBoxModel(assmtTypes.toArray()));
        }
    }//GEN-LAST:event_cmbSubjectActionPerformed

    private void cmbTypeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cmbTypeActionPerformed
    {
//GEN-HEADEREND:event_cmbTypeActionPerformed
        next();
    }//GEN-LAST:event_cmbTypeActionPerformed

    @Action
    public void Cancel()
    {
        Utilities.showCancelScreen(this);
    }

    @Action
    public void save()
    {
        if (!passedValidation())
        {
            Utilities.showWarningMessage(rootPane, validationText);
            return;
        }
        if (!gradesOK())
        {
            gradesText += "Is this correct?";
            int response = Utilities.showConfirmDialog(rootPane, gradesText);
            if (response != JOptionPane.YES_OPTION)
            {
                return;
            }
        }
        String assmtTerm = Term.getCurrentTerm(),
               assmtSubject = cmbSubject.getSelectedItem().toString(),
               assmtTeacher = Subject.getSubjectTeacher(assmtSubject),
               assmtTitle = txtTitle.getText().trim(),
               assmtDate = Utilities.YMD_Formatter.format(calDate.getDate()),
               assmtType = cmbType.getSelectedItem().toString(),
               assmtTotalPoints = String.valueOf(spinnerMaxPoints.getValue()),
               assmtClassID = Classes.getClassID(cmbClass.getSelectedItem().toString());
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
        //Create the assessment.
        boolean addAssessment = Grade.addAssessment(assmtTerm, assmtSubject, assmtTeacher, assmtTitle, assmtDate, assmtType, assmtTotalPoints, assmtClassID);
        //Get the assessment ID.
        String assmtID = Grade.getAssmtID(assmtTerm, assmtSubject, assmtTeacher, assmtTitle, assmtDate, assmtType, assmtTotalPoints, assmtClassID);
        //save the grades
        boolean addGrades = Grade.addGrades(assmtID, stuID, grade, remarks);
        if (addAssessment && addGrades)
        {
            String message = "The assessment was successfully saved. \n"
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
            String message = "An error occurred while trying to save this assessment.\n"
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
        Date today = new Date();
        calDate.setDate(today);
        spinnerMaxPoints.setValue(100);
//        ArrayList<String> assmtTypes = Grade.getAssessmentTypes();
//        cmbType.setModel(new DefaultComboBoxModel(assmtTypes.toArray()));
//        cmbType.setSelectedItem("Home Work");
        ArrayList<String> classList = new ArrayList<String>();
        if (User.getUserGroup().equals("Administration"))
        {
            classList = Classes.getClassList();
            classList.add(0, "--- Select One ---");
            cmbClass.setModel(new DefaultComboBoxModel(classList.toArray()));
        }
        else
        {
            classList.addAll(User.getPermittedClasses());
            classList.add(0, "--- Select One ---");
            cmbClass.setModel(new DefaultComboBoxModel(classList.toArray()));
        }
    }

    @Action
    public void next()
    {
        if (!passedValidation())
        {
            Utilities.showWarningMessage(rootPane, validationText);
            return;
        }
        DefaultTableModel model = Grade.getStudentList(cmbClass.getSelectedItem().toString());
        tblGrades.setModel(model);
        //JComboBox comboBox = new JComboBox(Grade.getValidStates());
        //comboBox.setEditable(true);
        //DefaultCellEditor editor = new DefaultCellEditor(comboBox);
        // Assign the editor to the fourth column
        //TableColumnModel tcm = tblGrades.getColumnModel();
        //tcm.getColumn(3).setCellEditor(editor);
        assmtTabbedPane.setSelectedIndex(assmtTabbedPane.getSelectedIndex() + 1);
    }

    private boolean gradesOK()
    {
        boolean gradesOK = true;
        gradesText = "";
        int totalPoints = (Integer) spinnerMaxPoints.getValue();
        for (int i = 0; i < tblGrades.getRowCount(); i++)
        {
            String points = tblGrades.getValueAt(i, 3).toString().trim();
            if (points.equals("A") || points.equals("a"))
            {
                points = "Absent";
                tblGrades.setValueAt(points, i, 3);
            }
            else if (points.equals("E") || points.equals("e"))
            {
                points = "Excused";
                tblGrades.setValueAt(points, i, 3);
            }
            else if (points.equals("I") || points.equals("i"))
            {
                points = "Incomplete";
                tblGrades.setValueAt(points, i, 3);
            }
            String name = tblGrades.getValueAt(i, 1).toString() + " " + tblGrades.getValueAt(i, 2).toString();
            if (!points.equals("Absent") && !points.equals("Excused") && !points.equals("Incomplete") && !points.equals(" ") && !points.equals(""))
            {
                double grade = Double.valueOf(points);
                if (grade > totalPoints)
                {
                    gradesOK = false;
                    gradesText += (name + " has a grade that is greater than the maximum points.\n");
                }
            }
        }
        return gradesOK;
    }

    private boolean passedValidation()
    {
        boolean inputValid = true;
        validationText = "";
        if (cmbClass.getSelectedItem().toString().equals("--- Select One ---"))
        {
            validationText = validationText + "Kindly select a Class before saving.\n";
            inputValid = false;
        }
        if (cmbSubject.getSelectedItem().toString().equals("--- Select One ---"))
        {
            validationText = validationText + "Kindly select a Subject before saving.\n";
            inputValid = false;
        }
        if (txtTitle.getText().trim().isEmpty())
        {
            validationText = validationText + "Kindly enter a title for the assignment.\n";
            inputValid = false;
        }
        return inputValid;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane assmtTabbedPane;
    private com.toedter.calendar.JDateChooser calDate;
    private javax.swing.JComboBox cmbClass;
    private javax.swing.JComboBox cmbSubject;
    private javax.swing.JComboBox cmbType;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdCancel1;
    private javax.swing.JButton cmdSave;
    private javax.swing.JPanel generalPanel;
    private javax.swing.JPanel gradesPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClass;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblMaxPoints;
    private javax.swing.JLabel lblSubject;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblType;
    private javax.swing.JSpinner spinnerMaxPoints;
    private javax.swing.JTable tblGrades;
    private javax.swing.JTextField txtTitle;
    // End of variables declaration//GEN-END:variables
}
