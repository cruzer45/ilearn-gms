/*
 * FrmMastersheetExport.java
 *
 * Created on Feb 6, 2012, 6:55:36 PM
 */
package ilearn.reports;

import ilearn.classes.Classes;
import ilearn.grades.Grade;
import ilearn.kernel.Utilities;
import ilearn.school.School;
import ilearn.term.Term;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;

/**
 *
 * @author mrogers
 */
public class FrmMastersheetExport extends javax.swing.JInternalFrame
{

    File selectedFile;
    static final Logger logger = Logger.getLogger(FrmMastersheetExport.class.getName());
    String validationText = "Kindly correct the following before prceeding.\n\n";

    /** Creates new form FrmMastersheetExport */
    public FrmMastersheetExport()
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
        wrapperPanel = new javax.swing.JPanel();
        cmdCancel = new javax.swing.JButton();
        cmdExport = new javax.swing.JButton();
        lblTerm = new javax.swing.JLabel();
        cmbTerm = new javax.swing.JComboBox();
        lblClass = new javax.swing.JLabel();
        cmbClass = new javax.swing.JComboBox();
        cmbTerm1 = new javax.swing.JComboBox();
        lblTerm1 = new javax.swing.JLabel();
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Mastersheet Export");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/ilearn/resources/report.png"))); // NOI18N
        wrapperPanel.setName("wrapperPanel"); // NOI18N
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmMastersheetExport.class, this);
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N
        cmdExport.setAction(actionMap.get("export")); // NOI18N
        cmdExport.setName("cmdExport"); // NOI18N
        lblTerm.setText("First Term:");
        lblTerm.setName("lblTerm"); // NOI18N
        cmbTerm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--- Select One ---" }));
        cmbTerm.setName("cmbTerm"); // NOI18N
        lblClass.setText("Class:");
        lblClass.setName("lblClass"); // NOI18N
        cmbClass.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--- Select Term ---" }));
        cmbClass.setName("cmbClass"); // NOI18N
        cmbTerm1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--- Select One ---" }));
        cmbTerm1.setName("cmbTerm1"); // NOI18N
        lblTerm1.setText("Second Term:");
        lblTerm1.setName("lblTerm1"); // NOI18N
        javax.swing.GroupLayout wrapperPanelLayout = new javax.swing.GroupLayout(wrapperPanel);
        wrapperPanel.setLayout(wrapperPanelLayout);
        wrapperPanelLayout.setHorizontalGroup(
            wrapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapperPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(wrapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wrapperPanelLayout.createSequentialGroup()
                                          .addComponent(cmdExport)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel))
                                .addGroup(wrapperPanelLayout.createSequentialGroup()
                                          .addGroup(wrapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(lblTerm1)
                                                  .addComponent(lblTerm)
                                                  .addComponent(lblClass))
                                          .addGap(14, 14, 14)
                                          .addGroup(wrapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(cmbClass, 0, 327, Short.MAX_VALUE)
                                                  .addComponent(cmbTerm, 0, 327, Short.MAX_VALUE)
                                                  .addComponent(cmbTerm1, 0, 327, Short.MAX_VALUE))))
                      .addContainerGap())
        );
        wrapperPanelLayout.setVerticalGroup(
            wrapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wrapperPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(wrapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTerm)
                                .addComponent(cmbTerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(wrapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTerm1)
                                .addComponent(cmbTerm1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(wrapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblClass)
                                .addComponent(cmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                      .addGroup(wrapperPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel)
                                .addComponent(cmdExport))
                      .addContainerGap())
        );
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wrapperPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wrapperPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void populateLists()
    {
        ArrayList<String> list = Term.getTermList();
        list.add(0, "--- Select One ---");
        cmbTerm.setModel(new DefaultComboBoxModel(list.toArray()));
        cmbTerm1.setModel(new DefaultComboBoxModel(list.toArray()));
        list = Classes.getClassList();
        list.add(0, "--- Select One ---");
        cmbClass.setModel(new DefaultComboBoxModel(list.toArray()));
    }

    @Action
    public void cancel()
    {
        Utilities.showCancelScreen(this);
    }

    private boolean passedValudation()
    {
        boolean passedValidation = true;
        validationText = "Kindly correct the following before prceeding.\n\n";
        String selectedTerm = cmbTerm.getSelectedItem().toString();
        String selectedClass = cmbClass.getSelectedItem().toString();
        if (selectedTerm.equals("--- Select One ---"))
        {
            passedValidation = false;
            validationText += "You must select a term to export from.\n";
        }
        if (selectedClass.equals("--- Select One ---"))
        {
            passedValidation = false;
            validationText += "You must select a class to export from.\n";
        }
        return passedValidation;
    }

    @Action
    public Task export()
    {
        if (!passedValudation())
        {
            Utilities.showWarningMessage(rootPane, validationText);
            return null;
        }
        return new ExportTask(org.jdesktop.application.Application.getInstance());
    }

    private class ExportTask extends org.jdesktop.application.Task<Object, Void>
    {

        File selFile;
        String firstTerm;
        String secondTerm;
        String classCode;

        ExportTask(org.jdesktop.application.Application app)
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
                    Logger.getLogger(FrmMastersheetExport.class.getName()).log(Level.SEVERE, null, ex);
                }
                selFile = selectedFile;
                classCode = cmbClass.getSelectedItem().toString();
                firstTerm = cmbTerm.getSelectedItem().toString();
                secondTerm = cmbTerm1.getSelectedItem().toString();
            }
            else
            {
                return;
            }
        }

        @Override
        protected Object doInBackground()
        {
            try
            {
                int passingMark = (School.getPassingMark());
                String firstTermID = Term.getTermIDFromShortName(firstTerm);
                String secondTermID = Term.getTermIDFromShortName(secondTerm);
                Object[] stuInfo = Term.getClassListForTerm(classCode, firstTermID);
                ArrayList<String> studentIDs = (ArrayList<String>) stuInfo[0];
                ArrayList<String> studentNames = (ArrayList<String>) stuInfo[1];
                setMessage("Creating Gradebook.");
                Workbook wb = new HSSFWorkbook();
                CreationHelper createHelper = wb.getCreationHelper();
                Sheet sheet = wb.createSheet(classCode + " Mastersheet - " + firstTerm + " and " + secondTerm);
                //Create the number format cell style
                CellStyle numberStyle = wb.createCellStyle();
                CellStyle numberStyle2 = wb.createCellStyle();
                CellStyle percentStyle = wb.createCellStyle();
                DataFormat format = wb.createDataFormat();
                numberStyle.setDataFormat(format.getFormat("#,##0.00"));
                numberStyle2.setDataFormat(format.getFormat("#,##0"));
                percentStyle.setDataFormat((short) 0xa);
                CellStyle redTextStyle = wb.createCellStyle();
                Font redFont = wb.createFont();
                redFont.setColor(HSSFColor.RED.index);
                redTextStyle.setFont(redFont);
                redTextStyle.setDataFormat(format.getFormat("#,##0.00"));
                //Insert Initial info
                Row row = sheet.createRow(0);
                Cell cell = row.createCell(0);
                cell.setCellValue("Mastersheet");
                row = sheet.createRow(1);
                row.createCell(0).setCellValue("Class:");
                row.createCell(1).setCellValue(classCode);
                row.createCell(3).setCellValue("Term:");
                row.createCell(4).setCellValue(firstTerm + " and " + secondTerm);
                setMessage("Inserting Data.");
                row = sheet.createRow(4);
                row.createCell(0).setCellValue(createHelper.createRichTextString("ID"));
                row.createCell(1).setCellValue(createHelper.createRichTextString("Name"));
                int studentStartRow = 6;
                int startColumn = 2; //This is the column the assessments will start to print.
                int finishColumn = 0;
                //Print The student List.
                for (int i = 0; i < studentIDs.size(); i++)
                {
                    Row studentRow = sheet.createRow(i + studentStartRow);
                    studentRow.createCell(0).setCellValue(Integer.valueOf(studentIDs.get(i)));
                    studentRow.createCell(1).setCellValue(studentNames.get(i));
                }
                Object[] subjectList = Term.getSubjectsforTerm(classCode, firstTermID);
                ArrayList<String> subCodes = (ArrayList<String>) subjectList[0];
                ArrayList<String> subNames = (ArrayList<String>) subjectList[1];
                Row subjectTitleRow = sheet.createRow(3);
                Row termTitleRow = sheet.createRow(4);
                //Print the subjects
                for (int i = 0; i < subNames.size(); i++)
                {
                    if (i == 0)
                    {
                        finishColumn = startColumn + i;
                    }
                    Cell subjectCell = subjectTitleRow.createCell(finishColumn);
                    subjectCell.setCellValue(subNames.get(i));
                    sheet.addMergedRegion(new CellRangeAddress(3, 3, finishColumn, finishColumn + 2));
                    Cell term1Cell = termTitleRow.createCell(finishColumn);
                    term1Cell.setCellValue("Sem 1");
                    Cell term2Cell = termTitleRow.createCell(finishColumn + 1);
                    term2Cell.setCellValue("Sem 2");
                    Cell term3Cell = termTitleRow.createCell(finishColumn + 2);
                    term3Cell.setCellValue("Average");
                    //for each student get their grade
                    for (int j = studentStartRow; j < (sheet.getLastRowNum() + 1); j++)
                    {
                        Row studentRow = sheet.getRow(j);
                        double stuID = studentRow.getCell(0).getNumericCellValue();
                        double grade = Term.getStudentGradeforTerm(classCode, firstTermID, subCodes.get(i), String.valueOf(stuID));
                        //Term1
                        Cell gradeCell = studentRow.createCell(finishColumn);
                        gradeCell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        gradeCell.setCellValue(grade);
                        gradeCell.setCellStyle(numberStyle);
                        if (grade < passingMark)
                        {
                            gradeCell.setCellStyle(redTextStyle);
                        }
                        //Term2
                        double grade2 = Term.getStudentGradeforTerm(classCode, secondTermID, subCodes.get(i), String.valueOf(stuID));
                        Cell gradeCell2 = studentRow.createCell(finishColumn + 1);
                        gradeCell2.setCellType(Cell.CELL_TYPE_NUMERIC);
                        gradeCell2.setCellValue(grade2);
                        gradeCell2.setCellStyle(numberStyle);
                        if (grade2 < passingMark)
                        {
                            gradeCell2.setCellStyle(redTextStyle);
                        }
                        //Average
                        double average = Term.getStudentGradeforYear(subCodes.get(i), String.valueOf(stuID));
                        Cell averageCell = studentRow.createCell(finishColumn + 2);
                        averageCell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        averageCell.setCellValue(average);
                        averageCell.setCellStyle(numberStyle);
                        if (average < passingMark)
                        {
                            averageCell.setCellStyle(redTextStyle);
                        }
                    }
                    finishColumn += 3;
                }
                finishColumn++;
                //Print the final grades
                //for each student get their grade
                Cell sem1Title = subjectTitleRow.createCell(finishColumn);
                sem1Title.setCellValue("Sem 1 Average");
                Cell sem2Title = subjectTitleRow.createCell(finishColumn + 1);
                sem2Title.setCellValue("Sem 2 Average");
                Cell yearTitle = subjectTitleRow.createCell(finishColumn + 2);
                yearTitle.setCellValue("Year Average");
                for (int j = studentStartRow; j < (sheet.getLastRowNum() + 1); j++)
                {
                    Row studentRow = sheet.getRow(j);
                    double stuID = studentRow.getCell(0).getNumericCellValue();
                    double sem1 = Term.getStudentAverageforTerm(classCode, firstTermID, String.valueOf(stuID));
                    double sem2 = Term.getStudentAverageforTerm(classCode, secondTermID, String.valueOf(stuID));
                    double yearGrade = Term.getStudentYearAverage(String.valueOf(stuID));
                    //Sem1 Average
                    Cell sem1Cell = studentRow.createCell(finishColumn);
                    sem1Cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    sem1Cell.setCellValue(sem1);
                    sem1Cell.setCellStyle(numberStyle);
                    if (sem1 < passingMark)
                    {
                        sem1Cell.setCellStyle(redTextStyle);
                    }
                    //Sem2 Average
                    Cell sem2Cell = studentRow.createCell(finishColumn + 1);
                    sem2Cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    sem2Cell.setCellValue(sem2);
                    sem2Cell.setCellStyle(numberStyle);
                    if (sem2 < passingMark)
                    {
                        sem2Cell.setCellStyle(redTextStyle);
                    }
                    //Year Average
                    Cell yearCell = studentRow.createCell(finishColumn + 2);
                    yearCell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    yearCell.setCellValue(yearGrade);
                    yearCell.setCellStyle(numberStyle);
                    if (yearGrade < passingMark)
                    {
                        yearCell.setCellStyle(redTextStyle);
                    }
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
            catch (Exception e)
            {
                String message = "An error occurred while tring to export the master sheet.";
                logger.log(Level.SEVERE, message, e);
                return false;
            }
            return false;
        }

        @Override
        protected void succeeded(Object result)
        {
            if (result == Boolean.TRUE)
            {
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbClass;
    private javax.swing.JComboBox cmbTerm;
    private javax.swing.JComboBox cmbTerm1;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdExport;
    private javax.swing.JLabel lblClass;
    private javax.swing.JLabel lblTerm;
    private javax.swing.JLabel lblTerm1;
    private javax.swing.JPanel wrapperPanel;
    // End of variables declaration//GEN-END:variables
}
