/*
 * DialogStudentByClass.java
 *
 * Created on May 1, 2011, 1:37:20 AM
 */
package ilearn.reports;

import ilearn.grades.Grade;
import ilearn.classes.Classes;
import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.term.Term;
import ilearn.user.User;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;

/**
 *
 * @author mrogers
 */
public class FrmClassMastersheet extends javax.swing.JDialog
{

    File selectedFile;
    static final Logger logger = Logger.getLogger(FrmClassMastersheet.class.getName());

    /** Creates new form DialogStudentByClass */
    public FrmClassMastersheet(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
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

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        lblTitle = new javax.swing.JLabel();
        lblClass = new javax.swing.JLabel();
        cmbClass = new javax.swing.JComboBox();
        cmdCancel = new javax.swing.JButton();
        cmdRun = new javax.swing.JButton();
        lblSubject = new javax.swing.JLabel();
        cmbSubject = new javax.swing.JComboBox();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmClassMastersheet.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        lblTitle.setFont(resourceMap.getFont("lblTitle.font")); // NOI18N
        lblTitle.setText(resourceMap.getString("lblTitle.text")); // NOI18N
        lblTitle.setName("lblTitle"); // NOI18N
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
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmClassMastersheet.class, this);
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setText(resourceMap.getString("cmdCancel.text")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N
        cmdRun.setAction(actionMap.get("run")); // NOI18N
        cmdRun.setText(resourceMap.getString("cmdRun.text")); // NOI18N
        cmdRun.setName("cmdRun"); // NOI18N
        lblSubject.setText(resourceMap.getString("lblSubject.text")); // NOI18N
        lblSubject.setName("lblSubject"); // NOI18N
        cmbSubject.setName("cmbSubject"); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTitle)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                          .addComponent(cmdRun)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel))
                                .addGroup(layout.createSequentialGroup()
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(lblClass)
                                                  .addComponent(lblSubject))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                  .addComponent(cmbClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                  .addComponent(cmbSubject, 0, 270, Short.MAX_VALUE))))
                      .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(lblTitle)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblClass)
                                .addComponent(cmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSubject)
                                .addComponent(cmbSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel)
                                .addComponent(cmdRun))
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

    @Action
    public void cancel()
    {
        this.dispose();
    }

    @Action(block = Task.BlockingScope.COMPONENT)
    public Task run()
    {
        return new RunTask(org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class));
    }

    private class RunTask extends org.jdesktop.application.Task<Object, Void>
    {

        File selFile;
        String classID;
        String classCode;
        String subjectID;

        RunTask(org.jdesktop.application.Application app)
        {
            super(app);
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnVal = fc.showSaveDialog(rootPane);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = fc.getSelectedFile();
                try
                {
                    if (!selectedFile.getCanonicalPath().endsWith(".xls"))
                    {
                        String filename = selectedFile.getCanonicalPath();
                        filename = filename + ".xls";
                        selectedFile = new File(filename);
                    }
                }
                catch (IOException ex)
                {
                    Logger.getLogger(FrmClassMastersheet.class.getName()).log(Level.SEVERE, null, ex);
                }
                selFile = selectedFile;
                classCode = cmbClass.getSelectedItem().toString();
                classID = Classes.getClassID(cmbClass.getSelectedItem().toString());
                subjectID = cmbSubject.getSelectedItem().toString();
            }
            else
            {
                return;
            }
        }

        @Override
        protected Object doInBackground()
        {
            if (selFile == null)
            {
                return null;
            }
            try
            {
                //Create the list objects
                ArrayList<Integer> assmtIDs = new ArrayList<Integer>();
                ArrayList<String> assmtTypes = new ArrayList<String>();
                ArrayList<String> assmtTitles = new ArrayList<String>();
                ArrayList<Date> assmtDates = new ArrayList<Date>();
                ArrayList<Integer> assmtTotalPoints = new ArrayList<Integer>();
                ArrayList<String> studentIDs = new ArrayList<String>();
                ArrayList<String> studentNames = new ArrayList<String>();
                //Get the list of assessments
                String sql1 = "SELECT `assmtID`, `assmtType`, `assmtTitle`, `assmtDate`, `assmtTotalPoints`, `assmtClassID`, `assmtSubject`, `assmtTerm`, `assmtTeacher`, `assmtStatus` FROM `Assments` WHERE `assmtClassID` = ? AND `assmtTerm` = ? AND `assmtSubject` = ? AND `assmtStatus` = 'Active' ORDER BY  `assmtDate` ASC LIMIT 0, 1000;";
                PreparedStatement prep = Environment.getConnection().prepareStatement(sql1);
                prep.setString(1, classID);
                prep.setString(2, Term.getCurrentTerm());
                prep.setString(3, subjectID);
                ResultSet rs = prep.executeQuery();
                while (rs.next())
                {
                    assmtIDs.add(rs.getInt("assmtID"));
                    assmtTypes.add(rs.getString("assmtType"));
                    assmtTitles.add(rs.getString("assmtTitle"));
                    assmtTotalPoints.add(rs.getInt("assmtTotalPoints"));
                    assmtDates.add(rs.getDate("assmtDate"));
                }
                rs.close();
                prep.close();
                Object[] stuInfo = Classes.getStudentNameList(classCode);
                studentIDs = (ArrayList<String>) stuInfo[0];
                studentNames = (ArrayList<String>) stuInfo[1];
                setMessage("Creating the workbook.");
                Workbook wb = new HSSFWorkbook();
                CreationHelper createHelper = wb.getCreationHelper();
                Sheet sheet = wb.createSheet(classCode + " Gradebook");
                setMessage("Creating Gradebook.");
                //Create the number format cell style
                CellStyle numberStyle = wb.createCellStyle();
                DataFormat format = wb.createDataFormat();
                numberStyle.setDataFormat(format.getFormat("#,##0.00"));
                //Insert Initial info
                Row row = sheet.createRow(0);
                Cell cell = row.createCell(0);
                cell.setCellValue("Gradebook");
                row = sheet.createRow(1);
                row.createCell(0).setCellValue("Class:");
                row.createCell(1).setCellValue(classCode);
                row.createCell(3).setCellValue("Subject:");
                row.createCell(4).setCellValue(subjectID);
                row.createCell(6).setCellValue("Term:");
                row.createCell(7).setCellValue(Term.getCurrentTermName());
                setMessage("Inserting Data.");
                row = sheet.createRow(3);
                row.createCell(0).setCellValue(createHelper.createRichTextString("ID"));
                row.createCell(1).setCellValue(createHelper.createRichTextString("Name"));
                int startColumn = 2; //This is the column the assessments will start to print.
                int finishColumn = 0;
                int studentStartRow = 7;
                Row maxGradeRow = sheet.createRow(6);
                maxGradeRow.createCell(1).setCellValue("Total Points");
                Row titleRow = sheet.createRow(4);
                titleRow.createCell(1).setCellValue("Title");
                Row dateRow = sheet.createRow(5);
                dateRow.createCell(1).setCellValue("Date");
                //Print The student List.
                for (int i = 0; i < studentIDs.size(); i++)
                {
                    Row studentRow = sheet.createRow(i + studentStartRow);
                    studentRow.createCell(0).setCellValue(Integer.valueOf(studentIDs.get(i)));
                    studentRow.createCell(1).setCellValue(studentNames.get(i));
                }
                //Print the assessments
                for (int i = 0; i < assmtIDs.size(); i++)
                {
                    finishColumn = startColumn + i;
                    Row typeRow = sheet.getRow(3);
                    typeRow.createCell(startColumn + i).setCellValue(assmtIDs.get(i) + " - " + assmtTypes.get(i));
                    maxGradeRow.createCell(startColumn + i).setCellValue(assmtTotalPoints.get(i));
                    titleRow.createCell(startColumn + i).setCellValue(assmtTitles.get(i));
                    Cell dateCell = dateRow.createCell(startColumn + i);
                    dateCell.setCellValue(new Date(assmtDates.get(i).getTime()));
                    CellStyle cellStyle = wb.createCellStyle();
                    cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MMM d, yyyy"));
                    dateCell.setCellStyle(cellStyle);
                    //for each student get their grade
                    for (int j = 7; j < (sheet.getLastRowNum() + 1); j++)
                    {
                        Row studentRow = sheet.getRow(j);
                        double stuID = studentRow.getCell(0).getNumericCellValue();
                        ArrayList<String> stuGrade = Grade.getStudentGrade(assmtIDs.get(i).toString(), String.valueOf(stuID));
                        try
                        {
                            String grade = stuGrade.get(0);
                            Cell gradeCell = studentRow.createCell(startColumn + i);
                            gradeCell.setCellType(Cell.CELL_TYPE_NUMERIC);
                            try
                            {
                                double maxPoints = Double.valueOf(assmtTotalPoints.get(i));
                                double assmtGrade = Double.valueOf(grade);
                                gradeCell.setCellValue(assmtGrade);
                                if ((assmtGrade / maxPoints) < .7)
                                {
                                    CellStyle style = wb.createCellStyle();
                                    Font font = wb.createFont();
                                    font.setColor(HSSFColor.RED.index);
                                    style.setFont(font);
                                    studentRow.getCell(startColumn + i).setCellStyle(style);
                                }
                            }
                            catch (NumberFormatException numberFormatException)
                            {
                                gradeCell.setCellValue(grade);
                                if (grade.equals("Excused"))
                                {
                                    CellStyle style = wb.createCellStyle();
                                    Font font = wb.createFont();
                                    font.setColor(HSSFColor.LIGHT_BLUE.index);
                                    style.setFont(font);
                                    studentRow.getCell(startColumn + i).setCellStyle(style);
                                }
                                if (grade.equals("Incomplete"))
                                {
                                    CellStyle style = wb.createCellStyle();
                                    Font font = wb.createFont();
                                    font.setColor(HSSFColor.RED.index);
                                    style.setFont(font);
                                    studentRow.getCell(startColumn + i).setCellStyle(style);
                                }
                                if (grade.equals("Absent"))
                                {
                                    CellStyle style = wb.createCellStyle();
                                    Font font = wb.createFont();
                                    font.setColor(HSSFColor.RED.index);
                                    style.setFont(font);
                                    studentRow.getCell(startColumn + i).setCellStyle(style);
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            studentRow.createCell(startColumn + i).setCellValue("Missing");
                            CellStyle style = wb.createCellStyle();
                            Font font = wb.createFont();
                            font.setColor(HSSFColor.RED.index);
                            style.setFont(font);
                            studentRow.getCell(startColumn + i).setCellStyle(style);
                        }
                    }
                }
                //Calculate student averages
                String totalPointsCell = "";
                titleRow = sheet.getRow(3);
                titleRow.createCell(finishColumn + 1).setCellValue("Average");
                for (int j = 6; j <= sheet.getLastRowNum(); j++)
                {
                    Row studentRow = sheet.getRow(j);
                    CellReference cellRefStart = new CellReference(studentRow.getRowNum(), startColumn);
                    CellReference cellRefEnd = new CellReference(studentRow.getRowNum(), finishColumn);
                    if (totalPointsCell.isEmpty())
                    {
                        CellReference totalMaxPoints = new CellReference(studentRow.getRowNum(), finishColumn + 1);
                        totalPointsCell = totalMaxPoints.formatAsString();
                    }
                    if (j == 6)
                    {
                        studentRow.createCell(finishColumn + 1).setCellFormula("(SUM(" + cellRefStart.formatAsString() + ": " + cellRefEnd.formatAsString() + "))");
                    }
                    else
                    {
                        Cell avgCell = studentRow.createCell(finishColumn + 1);
                        avgCell.setCellFormula("(SUM(" + cellRefStart.formatAsString() + ": " + cellRefEnd.formatAsString() + ")/" + totalPointsCell + "*100)");
                        avgCell.setCellStyle(numberStyle);
                    }
                }
                //calculate assessment minimum , maximum and average
                Row minRow = sheet.createRow(8 + studentIDs.size());
                Row avgRow = sheet.createRow(9 + studentIDs.size());
                Row maxRow = sheet.createRow(10 + studentIDs.size());
                minRow.createCell(1).setCellValue("Minimum");
                avgRow.createCell(1).setCellValue("Average");
                maxRow.createCell(1).setCellValue("Maximum");
                for (int i = 2; i <= finishColumn; i++)
                {
                    CellReference cellRefStart = new CellReference(7, i);
                    CellReference cellRefEnd = new CellReference(studentIDs.size() + 7, i);
                    Cell minCell = minRow.createCell(i);
                    minCell.setCellFormula("MIN(" + cellRefStart.formatAsString() + ":" + cellRefEnd.formatAsString() + ")");
                    minCell.setCellStyle(numberStyle);
                    Cell avgCell = avgRow.createCell(i);
                    avgCell.setCellFormula("AVERAGE(" + cellRefStart.formatAsString() + ":" + cellRefEnd.formatAsString() + ")");
                    avgCell.setCellStyle(numberStyle);
                    Cell maxCell = maxRow.createCell(i);
                    maxCell.setCellFormula("MAX(" + cellRefStart.formatAsString() + ":" + cellRefEnd.formatAsString() + ")");
                    maxCell.setCellStyle(numberStyle);
                }
                setMessage("Saving the file.");
                // Write the output to a file
                FileOutputStream fileOut = new FileOutputStream(selFile);
                wb.write(fileOut);
                fileOut.close();
                if (Desktop.isDesktopSupported())
                {
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(selFile);
                }
            }
            catch (Exception ex)
            {
                String message = "An error occurred while generating the gradebook.\n"
                                 + "Kindly check to make sure the file is not open in Excel and try again.";
                Utilities.showErrorMessage(rootPane, message);
                Logger.getLogger(FrmClassMastersheet.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        @Override
        protected void succeeded(Object result)
        {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbClass;
    private javax.swing.JComboBox cmbSubject;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdRun;
    private javax.swing.JLabel lblClass;
    private javax.swing.JLabel lblSubject;
    private javax.swing.JLabel lblTitle;
    // End of variables declaration//GEN-END:variables
}
