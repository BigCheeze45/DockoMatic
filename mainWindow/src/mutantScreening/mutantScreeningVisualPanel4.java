/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutantScreening;

import javax.swing.JPanel;

public final class mutantScreeningVisualPanel4 extends JPanel {

    /**
     * Creates new form mutantScreeningVisualPanel4
     */
    public mutantScreeningVisualPanel4() {
        initComponents();
    }

    @Override
    public String getName() {
        return "View/Modify Parameters";
    }

    public void setParamValues(Object[] values){
        cycles1TxtField.setText(values[0] + "");
        xOverPntsTxtField.setText(values[1] + "");
        mutation_rateTxtField.setText(values[2] + "");
        elitismTxtField.setText(values[3] + "");
        smoothNumTxtField.setText(values[4] + "");
    }
    
    public String[] getTextFieldValues(){
        String[] values = new String[5];
        values[0] = cycles1TxtField.getText();
        values[1] = xOverPntsTxtField.getText();
        values[2] = mutation_rateTxtField.getText();
        values[3] = elitismTxtField.getText();
        values[4] = smoothNumTxtField.getText();
        
        return values;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cycles1TxtField = new javax.swing.JTextField();
        xOverPntsTxtField = new javax.swing.JTextField();
        elitismTxtField = new javax.swing.JTextField();
        mutation_rateTxtField = new javax.swing.JTextField();
        smoothNumTxtField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        cycles1TxtField.setBackground(new java.awt.Color(255, 255, 255));
        cycles1TxtField.setText(org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel4.class, "mutantScreeningVisualPanel4.cycles1TxtField.text")); // NOI18N

        xOverPntsTxtField.setBackground(new java.awt.Color(255, 255, 255));
        xOverPntsTxtField.setText(org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel4.class, "mutantScreeningVisualPanel4.xOverPntsTxtField.text")); // NOI18N

        elitismTxtField.setBackground(new java.awt.Color(255, 255, 255));
        elitismTxtField.setText(org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel4.class, "mutantScreeningVisualPanel4.elitismTxtField.text")); // NOI18N

        mutation_rateTxtField.setBackground(new java.awt.Color(255, 255, 255));
        mutation_rateTxtField.setText(org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel4.class, "mutantScreeningVisualPanel4.mutation_rateTxtField.text")); // NOI18N

        smoothNumTxtField.setBackground(new java.awt.Color(255, 255, 255));
        smoothNumTxtField.setText(org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel4.class, "mutantScreeningVisualPanel4.smoothNumTxtField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel4.class, "mutantScreeningVisualPanel4.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel4.class, "mutantScreeningVisualPanel4.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel4.class, "mutantScreeningVisualPanel4.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel4.class, "mutantScreeningVisualPanel4.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel4.class, "mutantScreeningVisualPanel4.jLabel6.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(elitismTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(mutation_rateTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(smoothNumTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cycles1TxtField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(xOverPntsTxtField))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cycles1TxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xOverPntsTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(elitismTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mutation_rateTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(smoothNumTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(233, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cycles1TxtField;
    private javax.swing.JTextField elitismTxtField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField mutation_rateTxtField;
    private javax.swing.JTextField smoothNumTxtField;
    private javax.swing.JTextField xOverPntsTxtField;
    // End of variables declaration//GEN-END:variables
}
