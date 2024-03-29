/*
 * ILearnAboutBox.java
 */
package ilearn;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;

public class ILearnAboutBox extends javax.swing.JDialog
{

    public ILearnAboutBox(java.awt.Frame parent)
    {
        super(parent);
        initComponents();
        getRootPane().setDefaultButton(closeButton);
        setDocuments();
        aboutTabbedPane.setSelectedIndex(0);
    }

    @Action
    public void closeAboutBox()
    {
        dispose();
    }

    private void setDocuments()
    {
        try
        {
            URL documentURL = ILearnApp.class.getResource("resources/gpl-3.0-standalone.html");
            licensePane.setContentType("text/html");
            licensePane.setPage(documentURL);
            URL iconDocumentURL = ILearnApp.class.getResource("resources/iLearn_Icon_Page.html");
            iconPane.setContentType("text/html");
            iconPane.setPage(iconDocumentURL);
        }
        catch (IOException ex)
        {
            Logger.getLogger(ILearnAboutBox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        closeButton = new javax.swing.JButton();
        aboutTabbedPane = new javax.swing.JTabbedPane();
        generalPanel = new javax.swing.JPanel();
        javax.swing.JLabel versionLabel = new javax.swing.JLabel();
        javax.swing.JLabel appVersionLabel = new javax.swing.JLabel();
        javax.swing.JLabel vendorLabel = new javax.swing.JLabel();
        javax.swing.JLabel appVendorLabel = new javax.swing.JLabel();
        javax.swing.JLabel homepageLabel = new javax.swing.JLabel();
        javax.swing.JLabel appHomepageLabel = new javax.swing.JLabel();
        javax.swing.JLabel appDescLabel = new javax.swing.JLabel();
        javax.swing.JLabel imageLabel = new javax.swing.JLabel();
        licensePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        licensePane = new javax.swing.JEditorPane();
        iconPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        iconPane = new javax.swing.JEditorPane();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getResourceMap(ILearnAboutBox.class);
        setTitle(resourceMap.getString("title")); // NOI18N
        setModal(true);
        setName("aboutBox"); // NOI18N
        setResizable(false);
        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ilearn.ILearnApp.class).getContext().getActionMap(ILearnAboutBox.class, this);
        closeButton.setAction(actionMap.get("closeAboutBox")); // NOI18N
        closeButton.setName("closeButton"); // NOI18N
        aboutTabbedPane.setName("aboutTabbedPane"); // NOI18N
        generalPanel.setName("generalPanel"); // NOI18N
        versionLabel.setFont(versionLabel.getFont().deriveFont(versionLabel.getFont().getStyle() | java.awt.Font.BOLD));
        versionLabel.setText(resourceMap.getString("versionLabel.text")); // NOI18N
        versionLabel.setName("versionLabel"); // NOI18N
        appVersionLabel.setText(resourceMap.getString("Application.version")); // NOI18N
        appVersionLabel.setName("appVersionLabel"); // NOI18N
        vendorLabel.setFont(vendorLabel.getFont().deriveFont(vendorLabel.getFont().getStyle() | java.awt.Font.BOLD));
        vendorLabel.setText(resourceMap.getString("vendorLabel.text")); // NOI18N
        vendorLabel.setName("vendorLabel"); // NOI18N
        appVendorLabel.setText(resourceMap.getString("Application.vendor")); // NOI18N
        appVendorLabel.setName("appVendorLabel"); // NOI18N
        homepageLabel.setFont(homepageLabel.getFont().deriveFont(homepageLabel.getFont().getStyle() | java.awt.Font.BOLD));
        homepageLabel.setText(resourceMap.getString("homepageLabel.text")); // NOI18N
        homepageLabel.setName("homepageLabel"); // NOI18N
        appHomepageLabel.setText(resourceMap.getString("Application.homepage")); // NOI18N
        appHomepageLabel.setName("appHomepageLabel"); // NOI18N
        appDescLabel.setText(resourceMap.getString("appDescLabel.text")); // NOI18N
        appDescLabel.setName("appDescLabel"); // NOI18N
        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(resourceMap.getIcon("imageLabel.icon")); // NOI18N
        imageLabel.setName("imageLabel"); // NOI18N
        javax.swing.GroupLayout generalPanelLayout = new javax.swing.GroupLayout(generalPanel);
        generalPanel.setLayout(generalPanelLayout);
        generalPanelLayout.setHorizontalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(generalPanelLayout.createSequentialGroup()
                                          .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(versionLabel)
                                                  .addComponent(vendorLabel)
                                                  .addComponent(homepageLabel))
                                          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                          .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                  .addComponent(appVersionLabel)
                                                  .addComponent(appVendorLabel)
                                                  .addComponent(appHomepageLabel)))
                                .addComponent(appDescLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                      .addContainerGap())
        );
        generalPanelLayout.setVerticalGroup(
            generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalPanelLayout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(imageLabel)
                      .addGap(31, 31, 31)
                      .addComponent(appDescLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(versionLabel)
                                .addComponent(appVersionLabel))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(vendorLabel)
                                .addComponent(appVendorLabel))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addGroup(generalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(homepageLabel)
                                .addComponent(appHomepageLabel))
                      .addContainerGap(85, Short.MAX_VALUE))
        );
        aboutTabbedPane.addTab(resourceMap.getString("generalPanel.TabConstraints.tabTitle"), resourceMap.getIcon("generalPanel.TabConstraints.tabIcon"), generalPanel); // NOI18N
        licensePanel.setName("licensePanel"); // NOI18N
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        licensePane.setContentType(resourceMap.getString("licensePane.contentType")); // NOI18N
        licensePane.setEditable(false);
        licensePane.setText(resourceMap.getString("licensePane.text")); // NOI18N
        licensePane.setName("licensePane"); // NOI18N
        jScrollPane1.setViewportView(licensePane);
        javax.swing.GroupLayout licensePanelLayout = new javax.swing.GroupLayout(licensePanel);
        licensePanel.setLayout(licensePanelLayout);
        licensePanelLayout.setHorizontalGroup(
            licensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );
        licensePanelLayout.setVerticalGroup(
            licensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
        );
        aboutTabbedPane.addTab(resourceMap.getString("licensePanel.TabConstraints.tabTitle"), resourceMap.getIcon("licensePanel.TabConstraints.tabIcon"), licensePanel); // NOI18N
        iconPanel.setName("iconPanel"); // NOI18N
        jScrollPane2.setName("jScrollPane2"); // NOI18N
        iconPane.setEditable(false);
        iconPane.setName("iconPane"); // NOI18N
        jScrollPane2.setViewportView(iconPane);
        javax.swing.GroupLayout iconPanelLayout = new javax.swing.GroupLayout(iconPanel);
        iconPanel.setLayout(iconPanelLayout);
        iconPanelLayout.setHorizontalGroup(
            iconPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );
        iconPanelLayout.setVerticalGroup(
            iconPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
        );
        aboutTabbedPane.addTab(resourceMap.getString("iconPanel.TabConstraints.tabTitle"), resourceMap.getIcon("iconPanel.TabConstraints.tabIcon"), iconPanel); // NOI18N
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(closeButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(aboutTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE))
                      .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                      .addContainerGap()
                      .addComponent(aboutTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addComponent(closeButton)
                      .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane aboutTabbedPane;
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel generalPanel;
    private javax.swing.JEditorPane iconPane;
    private javax.swing.JPanel iconPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JEditorPane licensePane;
    private javax.swing.JPanel licensePanel;
    // End of variables declaration//GEN-END:variables
}
