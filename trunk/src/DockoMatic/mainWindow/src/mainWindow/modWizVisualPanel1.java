/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainWindow;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public final class modWizVisualPanel1 extends JPanel {
    private DefaultTableModel model;
    private JTable table;
    private boolean fullAuto = false;

	/** Creates new form modWizVisualPanel1 */
	public modWizVisualPanel1() {
		initComponents();
		jPanel1.setVisible(false);
	}

	@Override
	public String getName() {
		return "Enter Sequence";
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	public void clearAllData(){
	    outDirField1.setText("");
	    seqArea.setText("");
	}

        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLabel1 = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                seqArea = new javax.swing.JTextArea();
                seqButton = new javax.swing.JButton();
                jPanel4 = new javax.swing.JPanel();
                outDirButton1 = new javax.swing.JButton();
                outDirField1 = new javax.swing.JTextField();
                jPanel1 = new javax.swing.JPanel();
                fullAutoChkBox = new javax.swing.JCheckBox();

                org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(modWizVisualPanel1.class, "modWizVisualPanel1.jLabel1.text")); // NOI18N

                seqArea.setColumns(20);
                seqArea.setLineWrap(true);
                seqArea.setRows(5);
                jScrollPane1.setViewportView(seqArea);

                org.openide.awt.Mnemonics.setLocalizedText(seqButton, org.openide.util.NbBundle.getMessage(modWizVisualPanel1.class, "modWizVisualPanel1.seqButton.text")); // NOI18N
                seqButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                seqButtonActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                        .addComponent(seqButton))
                                .addContainerGap())
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(seqButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                .addContainerGap())
                );

                org.openide.awt.Mnemonics.setLocalizedText(outDirButton1, org.openide.util.NbBundle.getMessage(modWizVisualPanel1.class, "modWizVisualPanel1.outDirButton1.text")); // NOI18N
                outDirButton1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                outDirButton1ActionPerformed(evt);
                        }
                });

                outDirField1.setText(org.openide.util.NbBundle.getMessage(modWizVisualPanel1.class, "modWizVisualPanel1.outDirField1.text")); // NOI18N

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(outDirField1, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                        .addComponent(outDirButton1))
                                .addContainerGap())
                );
                jPanel4Layout.setVerticalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(outDirButton1)
                                .addGap(18, 18, 18)
                                .addComponent(outDirField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                org.openide.awt.Mnemonics.setLocalizedText(fullAutoChkBox, org.openide.util.NbBundle.getMessage(modWizVisualPanel1.class, "modWizVisualPanel1.fullAutoChkBox.text")); // NOI18N
                fullAutoChkBox.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                fullAutoChkBoxActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(fullAutoChkBox, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(fullAutoChkBox)
                                .addContainerGap(43, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                );
        }// </editor-fold>//GEN-END:initComponents
    private String getDirChoose(String lastDir){
        String dir = null;
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser(lastDir);
        fc.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        int retval = fc.showOpenDialog(this);
        if (retval == fc.APPROVE_OPTION) {
           dir = fc.getSelectedFile().getPath();
        }

        return dir;

    }

    private String getFileChoose(String lastDir){
        String file = null;
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser(lastDir);
        int retval = fc.showOpenDialog(this);
        if (retval == fc.APPROVE_OPTION) {
           file = fc.getSelectedFile().getPath();
        }

        return file;

    }

    public static JTextField getOutField(){
        return outDirField1;
    }

    public String getOutDirField(){
        return outDirField1.getText();
    }

	public static JTextArea getTextField(){
		return seqArea;
	}

    public static String getSeqField(){
        return seqArea.getText();
    }

    public boolean isAuto(){
	//return fullAuto;
	return false;
    }


	private void seqButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seqButtonActionPerformed
		String file = getFileChoose("./");
		if(file != null)
			seqArea.setText(file);
}//GEN-LAST:event_seqButtonActionPerformed

	private void outDirButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outDirButton1ActionPerformed
		String dir = getDirChoose("./");
		if(dir != null){
			//lastOutDir = dir;
			outDirField1.setText(dir);
		}    // TODO add your handling code here:
}//GEN-LAST:event_outDirButton1ActionPerformed

	private void fullAutoChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullAutoChkBoxActionPerformed
		if(fullAuto) fullAuto = false;
		else fullAuto = true;
	}//GEN-LAST:event_fullAutoChkBoxActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JCheckBox fullAutoChkBox;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JButton outDirButton1;
        protected static javax.swing.JTextField outDirField1;
        protected static javax.swing.JTextArea seqArea;
        private javax.swing.JButton seqButton;
        // End of variables declaration//GEN-END:variables
}
