/*
 * FrmCalculateMidTerms.java
 *
 * Created on Jul 7, 2011, 9:49:34 PM
 */
package ilearn.utils;

import ilearn.grades.Grade;
import ilearn.kernel.Environment;
import ilearn.kernel.Utilities;
import ilearn.kernel.logger.iLogger;
import ilearn.reports.ReportLoader;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;
import org.jdesktop.application.Task;

/**
 *
 * @author mrogers
 */
public class FrmCalculateFinals extends javax.swing.JInternalFrame
{

    /** Creates new form FrmCalculateMidTerms */
    public FrmCalculateFinals()
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
        cmdCancel = new javax.swing.JButton();
        lblWarning2 = new javax.swing.JLabel();
        lblWarning = new javax.swing.JLabel();
        cmdGenerate = new javax.swing.JButton();
        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FrmCalculateFinals.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(FrmCalculateFinals.class, this);
        cmdCancel.setAction(actionMap.get("cancel")); // NOI18N
        cmdCancel.setText(resourceMap.getString("cmdCancel.text")); // NOI18N
        cmdCancel.setName("cmdCancel"); // NOI18N
        lblWarning2.setText(resourceMap.getString("lblWarning2.text")); // NOI18N
        lblWarning2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblWarning2.setName("lblWarning2"); // NOI18N
        lblWarning.setFont(lblWarning.getFont().deriveFont(lblWarning.getFont().getStyle() | java.awt.Font.BOLD, lblWarning.getFont().getSize()+4));
        lblWarning.setText(resourceMap.getString("lblWarning.text")); // NOI18N
        lblWarning.setName("lblWarning"); // NOI18N
        cmdGenerate.setAction(actionMap.get("generateMidTerms")); // NOI18N
        cmdGenerate.setText(resourceMap.getString("cmdGenerate.text")); // NOI18N
        cmdGenerate.setName("cmdGenerate"); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblWarning)
                                .addComponent(lblWarning2, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                          .addComponent(cmdGenerate)
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                          .addComponent(cmdCancel)))
                      .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(lblWarning)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addComponent(lblWarning2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 15, Short.MAX_VALUE)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmdCancel)
                                .addComponent(cmdGenerate))
                      .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void cancel()
    {
        Utilities.showCancelScreen(this);
    }

    @Action(block = Task.BlockingScope.WINDOW)
    public Task generateMidTerms()
    {
        return new GenerateMidTermsTask(org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class));
    }

    private class GenerateMidTermsTask extends org.jdesktop.application.Task<Object, Void>
    {

        String warnings = "Cannot calculate final grades due to the following issue(s):\n\n";

        GenerateMidTermsTask(org.jdesktop.application.Application app)
        {
            super(app);
        }

        @Override
        protected Object doInBackground()
        {
            setMessage("Running pre-generation checks.");
            boolean previouslyGenerated = iLogger.checkAction(iLogger.finalGrades);
            if (previouslyGenerated)
            {
                try
                {
                    Thread.sleep(2000);
                }
                catch (Exception e)
                {
                }
                String message = "Finals have already been calculated for this Term.\n"
                                 + "Do you wish to recalculate?";
                int response = Utilities.showConfirmDialog(rootPane, message);
                if (response == JOptionPane.YES_OPTION)
                {
                }
                else
                {
                    warnings += message;
                    return false;
                }
            }
            setMessage("Checking grades");
            int missingGrades = Grade.getMissingGradeCount();
            if (missingGrades > 0)
            {
                String message = " There are " + missingGrades + " missing grades currently. \n\n"
                                 + "Select Yes to proceed calculating Finals with these missing grades.\n"
                                 + "Select No to view a report displaying these missing grades.\n"
                                 + "Select Cancel to stop the process.";
                try
                {
                    Thread.sleep(2000);
                }
                catch (Exception e)
                {
                }
                int response = Utilities.showYNCConfirmDialog(rootPane, message);
                if (response == JOptionPane.NO_OPTION)
                {
                    setMessage("Loading reporting engine");
                    ReportLoader.showMissingGradeReportReport();
                    warnings += "Missing Grades were found in the system.\n"
                                + "A report displaying the missing grades was generated.";
                    this.cancel(true);
                    return false;
                }
                else if (response == JOptionPane.CANCEL_OPTION)
                {
                    warnings += "Missing Grades were found in the system.\n"
                                + "The user cancelled the process.";
                    this.cancel(true);
                    return false;
                }
            }
            if (!isCancelled())
            {
                setProgress(1, 0, 4);
                setMessage("Calculating Final Grades.");
                boolean calculateFinals = Grade.calculateFinalGrade();
                if (!calculateFinals)
                {
                    warnings += "An error occurred while calculating the grades.\n";
                    return false;
                }
                setProgress(2, 0, 4);
                setMessage("Saving Grades");
                boolean saveMidTerms = Grade.saveFinalGrades();
                if (!saveMidTerms)
                {
                    warnings += "An error occurred while saving the grades.\n";
                    return false;
                }
                setProgress(3, 0, 4);
                setMessage("Calculating GPAs");
                Grade.updateFinalGPA();
                setMessage("Calculating Averages");
                Grade.calculateFinalAverage();
                setProgress(4, 0, 4);
                return true;  // return your result
            }
            return null;
        }

        @Override
        protected void succeeded(Object result)
        {
            if (result == Boolean.TRUE)
            {
                String message = "Final grades were successfully generated.";
                Utilities.showInfoMessage(rootPane, message);
                iLogger.logAction(iLogger.finalGrades);
                return;
            }
            else
            {
                FrmErrors frmErrors = new FrmErrors(null, true, warnings);
                frmErrors.setLocationRelativeTo(Environment.getMainFrame());
                frmErrors.setVisible(true);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdGenerate;
    private javax.swing.JLabel lblWarning;
    private javax.swing.JLabel lblWarning2;
    // End of variables declaration//GEN-END:variables
}
