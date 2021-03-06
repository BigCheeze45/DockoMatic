/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutantScreening;

import javax.swing.JPanel;

public final class mutantScreeningVisualPanel3 extends JPanel {

    /**
     * Creates new form mutantScreeningVisualPanel3
     */
    public mutantScreeningVisualPanel3() {
        initComponents();
    }

    @Override
    public String getName() {
        return "Set GA parameters";
    }
    
    public String getNumProcessors(){
        return clusterSizeTxtField.getText();
    }
    
    public String getTopX(){
        return resultSizeTxtField.getText();
    }
    
    public double getSliderFraction(){
        return ((double) gaParamSlider.getValue()) / gaParamSlider.getMaximum();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        resultSizeTxtField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        gaParamSlider = new javax.swing.JSlider();
        clusterSizeTxtField = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel3.class, "mutantScreeningVisualPanel3.jLabel10.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel3.class, "mutantScreeningVisualPanel3.jLabel9.text")); // NOI18N

        resultSizeTxtField.setBackground(new java.awt.Color(255, 255, 255));
        resultSizeTxtField.setText(org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel3.class, "mutantScreeningVisualPanel3.resultSizeTxtField.text")); // NOI18N
        resultSizeTxtField.setToolTipText(org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel3.class, "mutantScreeningVisualPanel3.resultSizeTxtField.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel3.class, "mutantScreeningVisualPanel3.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel3.class, "mutantScreeningVisualPanel3.jLabel6.text")); // NOI18N

        clusterSizeTxtField.setBackground(new java.awt.Color(255, 255, 255));
        clusterSizeTxtField.setText(org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel3.class, "mutantScreeningVisualPanel3.clusterSizeTxtField.text")); // NOI18N
        clusterSizeTxtField.setToolTipText(org.openide.util.NbBundle.getMessage(mutantScreeningVisualPanel3.class, "mutantScreeningVisualPanel3.clusterSizeTxtField.toolTipText")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clusterSizeTxtField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resultSizeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(132, 132, 132))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gaParamSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(342, 342, 342)
                        .addComponent(jLabel10)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clusterSizeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resultSizeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gaParamSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(245, 245, 245))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField clusterSizeTxtField;
    private javax.swing.JSlider gaParamSlider;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField resultSizeTxtField;
    // End of variables declaration//GEN-END:variables
}
