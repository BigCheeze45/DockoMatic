/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutantScreening;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public final class mutantScreeningVisualPanel1 extends JPanel {
    ArrayList<String> peptide_sequence;
    
    /**
     * Creates new form mutantScreeningVisualPanel1
     */
    public mutantScreeningVisualPanel1() {
        initComponents();
    }

    @Override
    public String getName() {
        return "Mutation Sites";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchCriteriaTxtField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        selectedAcidsTxtField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        goToButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        sequenceList = new javax.swing.JList();

        searchCriteriaTxtField.setBackground(new java.awt.Color(255, 255, 255));
        searchCriteriaTxtField.setText(org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel1.class, "mutantScreeningVisualPanel1.searchCriteriaTxtField.text")); // NOI18N
        searchCriteriaTxtField.setToolTipText(org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel1.class, "mutantScreeningVisualPanel1.searchCriteriaTxtField.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel1.class, "mutantScreeningVisualPanel1.jLabel1.text")); // NOI18N

        selectedAcidsTxtField.setEditable(false);
        selectedAcidsTxtField.setBackground(new java.awt.Color(255, 255, 255));
        selectedAcidsTxtField.setText(org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel1.class, "mutantScreeningVisualPanel1.selectedAcidsTxtField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel1.class, "mutantScreeningVisualPanel1.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(goToButton, org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel1.class, "mutantScreeningVisualPanel1.goToButton.text")); // NOI18N
        goToButton.setToolTipText(org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel1.class, "mutantScreeningVisualPanel1.goToButton.toolTipText")); // NOI18N
        goToButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToButtonActionPerformed(evt);
            }
        });

        sequenceList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        sequenceList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        sequenceList.setVisibleRowCount(1);
        sequenceList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                sequenceListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(sequenceList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(searchCriteriaTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(goToButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jLabel1))
                    .addComponent(selectedAcidsTxtField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jLabel3)))
                .addGap(75, 75, 75))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(goToButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchCriteriaTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectedAcidsTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /*
    * TODO handle E11 type entry ... optional
     */
    private void goToButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToButtonActionPerformed
        String criteria = searchCriteriaTxtField.getText().trim().toUpperCase();
        
        try{
            sequenceList.ensureIndexIsVisible(Integer.parseInt(criteria)-1); //method handles out of bounds issues.  Biologists indices of [1,length]
        }catch(NumberFormatException ex){
            if(criteria.length() == 1){
                for(int index = sequenceList.getFirstVisibleIndex(); index < peptide_sequence.size(); index++){
                    if(peptide_sequence.get(index).charAt(0) == criteria.charAt(0) && !sequenceList.isSelectedIndex(index)){
                        sequenceList.ensureIndexIsVisible(index);
                        break;
                    }
                }
            }else{
                JOptionPane.showMessageDialog(this, "The search field needs to contain either an index (integer) or the single letter abbreviation of an amino acid.");
            }
        }
    }//GEN-LAST:event_goToButtonActionPerformed

    public String getMutableAcids(){
        return selectedAcidsTxtField.getText().trim();
    }
    
    public void setSequence(ArrayList<String> seq){
        peptide_sequence = seq;
        sequenceList.setListData(peptide_sequence.toArray());
    }
    
    private void sequenceListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_sequenceListValueChanged
        String toDisplay = "";
        int[] selectedIndices = sequenceList.getSelectedIndices();
        for(int index : selectedIndices){
            toDisplay += mutantScreeningWizardAction.DELIM + peptide_sequence.get(index);
        }
        selectedAcidsTxtField.setText(toDisplay.substring(mutantScreeningWizardAction.DELIM.length()));
    }//GEN-LAST:event_sequenceListValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton goToButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchCriteriaTxtField;
    private javax.swing.JTextField selectedAcidsTxtField;
    private javax.swing.JList sequenceList;
    // End of variables declaration//GEN-END:variables
}
