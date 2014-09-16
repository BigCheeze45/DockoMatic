/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.boisestate.SimSearcher;

import javax.swing.JPanel;

public final class MappingVisualPanel1 extends JPanel {
    
    private final String homeDir;
    private String functional_group_file;

    /**
     * Creates new form MappingVisualPanel1
     */
    public MappingVisualPanel1() {
        initComponents();
        setShpDstParamVisible(false);
        homeDir = System.getProperty("user.home");
    }
    
    private void setShpDstParamVisible(boolean isVisible){
        uppBoundLabel.setVisible(isVisible);
        uppBoundTxtField.setVisible(isVisible);
        numBinsLabel.setVisible(isVisible);
        numBinsTxtField.setVisible(isVisible);
    }

    @Override
    public String getName() {
        return "Mapping Parameters";
    }
    
    String getDatabaseDir(){
        return dbDirTxtField.getText();
    }
    
    String getOutputDir(){
        return outputDirTxtField.getText();
    }
    
    boolean getUseShapeDist(){
        return useShapeDistChkBox.isSelected();
    }
    
    String getNumNodes(){
        return numNodesTxtField.getText();
    }
    
    String getJobsPerNode(){
        return jobsPerNodeTxtField.getText();
    }
    
    String getSwarmOpts(){
        return swrmCmdOptsTxtField.getText();
    }
    
    String getNumBins(){
        return numBinsTxtField.getText();
    }
    
    String getUpperBoundMeasure(){
        return uppBoundTxtField.getText();
    }
    
    String getFgroupFile(){
        return functional_group_file;
    }
    
    boolean getUseFgroups(){
        return usePharmFeaturesChkBox.isSelected();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dbDirButton = new javax.swing.JButton();
        dbDirTxtField = new javax.swing.JTextField();
        outputDirTxtField = new javax.swing.JTextField();
        outputDirButton = new javax.swing.JButton();
        usePharmFeaturesChkBox = new javax.swing.JCheckBox();
        useShapeDistChkBox = new javax.swing.JCheckBox();
        jobsPerNodeTxtField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        swrmCmdOptsTxtField = new javax.swing.JTextField();
        numNodesTxtField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        numBinsTxtField = new javax.swing.JTextField();
        uppBoundTxtField = new javax.swing.JTextField();
        numBinsLabel = new javax.swing.JLabel();
        uppBoundLabel = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(dbDirButton, org.openide.util.NbBundle.getMessage(MappingVisualPanel1.class, "MappingVisualPanel1.dbDirButton.text")); // NOI18N
        dbDirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbDirButtonActionPerformed(evt);
            }
        });

        dbDirTxtField.setText(org.openide.util.NbBundle.getMessage(MappingVisualPanel1.class, "MappingVisualPanel1.dbDirTxtField.text")); // NOI18N

        outputDirTxtField.setEditable(false);
        outputDirTxtField.setText(org.openide.util.NbBundle.getMessage(MappingVisualPanel1.class, "MappingVisualPanel1.outputDirTxtField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(outputDirButton, org.openide.util.NbBundle.getMessage(MappingVisualPanel1.class, "MappingVisualPanel1.outputDirButton.text")); // NOI18N
        outputDirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputDirButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(usePharmFeaturesChkBox, org.openide.util.NbBundle.getMessage(MappingVisualPanel1.class, "MappingVisualPanel1.usePharmFeaturesChkBox.text")); // NOI18N
        usePharmFeaturesChkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usePharmFeaturesChkBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(useShapeDistChkBox, org.openide.util.NbBundle.getMessage(MappingVisualPanel1.class, "MappingVisualPanel1.useShapeDistChkBox.text")); // NOI18N
        useShapeDistChkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useShapeDistChkBoxActionPerformed(evt);
            }
        });

        jobsPerNodeTxtField.setBackground(new java.awt.Color(255, 255, 255));
        jobsPerNodeTxtField.setText(org.openide.util.NbBundle.getMessage(MappingVisualPanel1.class, "MappingVisualPanel1.jobsPerNodeTxtField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(MappingVisualPanel1.class, "MappingVisualPanel1.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(MappingVisualPanel1.class, "MappingVisualPanel1.jLabel2.text")); // NOI18N

        swrmCmdOptsTxtField.setBackground(new java.awt.Color(255, 255, 255));
        swrmCmdOptsTxtField.setText(org.openide.util.NbBundle.getMessage(MappingVisualPanel1.class, "MappingVisualPanel1.swrmCmdOptsTxtField.text")); // NOI18N

        numNodesTxtField.setBackground(new java.awt.Color(255, 255, 255));
        numNodesTxtField.setText(org.openide.util.NbBundle.getMessage(MappingVisualPanel1.class, "MappingVisualPanel1.numNodesTxtField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(MappingVisualPanel1.class, "MappingVisualPanel1.jLabel3.text")); // NOI18N

        numBinsTxtField.setBackground(new java.awt.Color(255, 255, 255));
        numBinsTxtField.setText(org.openide.util.NbBundle.getMessage(MappingVisualPanel1.class, "MappingVisualPanel1.numBinsTxtField.text")); // NOI18N

        uppBoundTxtField.setBackground(new java.awt.Color(255, 255, 255));
        uppBoundTxtField.setText(org.openide.util.NbBundle.getMessage(MappingVisualPanel1.class, "MappingVisualPanel1.uppBoundTxtField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(numBinsLabel, org.openide.util.NbBundle.getMessage(MappingVisualPanel1.class, "MappingVisualPanel1.numBinsLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(uppBoundLabel, org.openide.util.NbBundle.getMessage(MappingVisualPanel1.class, "MappingVisualPanel1.uppBoundLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usePharmFeaturesChkBox)
                            .addComponent(numNodesTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(useShapeDistChkBox)
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(numBinsTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(numBinsLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(uppBoundLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(uppBoundTxtField))))
                        .addGap(197, 197, 197))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jobsPerNodeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(swrmCmdOptsTxtField)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel2)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dbDirButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(outputDirButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(outputDirTxtField)
                                    .addComponent(dbDirTxtField))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dbDirButton)
                    .addComponent(dbDirTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputDirTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outputDirButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numBinsLabel)
                    .addComponent(uppBoundLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(useShapeDistChkBox)
                    .addComponent(numBinsTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uppBoundTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(usePharmFeaturesChkBox)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(numNodesTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jobsPerNodeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(swrmCmdOptsTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dbDirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbDirButtonActionPerformed
        String dbFolder = SimSearchUtilities.getDirChoose(homeDir, "Database Directory", SimSearchUtilities.DIRECTORIES, null);
        if(dbFolder != null){
            dbDirTxtField.setText(dbFolder);
        }
    }//GEN-LAST:event_dbDirButtonActionPerformed

    private void outputDirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputDirButtonActionPerformed
        String outFolder = SimSearchUtilities.getDirChoose(homeDir, "Place Output in ", SimSearchUtilities.DIRECTORIES, null);
        if(outFolder != null){
            outputDirTxtField.setText(outFolder);
        }
    }//GEN-LAST:event_outputDirButtonActionPerformed

    private void usePharmFeaturesChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usePharmFeaturesChkBoxActionPerformed
        if(usePharmFeaturesChkBox.isSelected()){
            String[] extensions = {SimSearchUtilities.FGROUP_EXTENSION};
            String tmp = SimSearchUtilities.getDirChoose(homeDir, "Function Group File (" + SimSearchUtilities.FGROUP_EXTENSION + ")", SimSearchUtilities.FGROUP_EXTENSION, extensions);
            if(tmp == null){
                usePharmFeaturesChkBox.setSelected(false);
            }
            functional_group_file = tmp;
        }
    }//GEN-LAST:event_usePharmFeaturesChkBoxActionPerformed

    private void useShapeDistChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useShapeDistChkBoxActionPerformed
        setShpDstParamVisible(useShapeDistChkBox.isSelected());
    }//GEN-LAST:event_useShapeDistChkBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dbDirButton;
    private javax.swing.JTextField dbDirTxtField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jobsPerNodeTxtField;
    private javax.swing.JLabel numBinsLabel;
    private javax.swing.JTextField numBinsTxtField;
    private javax.swing.JTextField numNodesTxtField;
    private javax.swing.JButton outputDirButton;
    private javax.swing.JTextField outputDirTxtField;
    private javax.swing.JTextField swrmCmdOptsTxtField;
    private javax.swing.JLabel uppBoundLabel;
    private javax.swing.JTextField uppBoundTxtField;
    private javax.swing.JCheckBox usePharmFeaturesChkBox;
    private javax.swing.JCheckBox useShapeDistChkBox;
    // End of variables declaration//GEN-END:variables
}
