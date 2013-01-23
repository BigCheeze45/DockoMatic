//# Program: "DockoMatic"
//# Project: "DNA Safeguard"
//# Filename: "modWizVisualPanel1.java"
//#
//# Dr. Tim Andersen
//# Department of Computer Science
//# College of Engineering
//# Boise State University
//#
//# Original Author(s): "Casey Bullock"
//#                     "Nic Cornia"
//#
//# Last Modified
//#   Date: "November 14, 2012"
//#
//#
//#  This file is part of DockoMatic.
//#
//#  DockoMatic is free software: you can redistribute it and/or modify
//#  it under the terms of the GNU Lesser General Public License as published by
//#  the Free Software Foundation, either version 3 of the License, or
//#  (at your option) any later version.
//#
//#  DockoMatic is distributed in the hope that it will be useful,
//#  but WITHOUT ANY WARRANTY; without even the implied warranty of
//#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//#  GNU Lesser General Public License for more details.
//#
//#  You should have received a copy of the GNU Lesser General Public License
//#  along with DockoMatic.  If not, see <http://www.gnu.org/licenses/>.
//#

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainWindow;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

public final class modWizVisualPanel1 extends JPanel {
    private DefaultTableModel model;
    private JTable table;
    private boolean fullAuto = false;

	/** Creates new form modWizVisualPanel1 */
	public modWizVisualPanel1() {
		initComponents();
		jPanel1.setVisible(false);
                clearAllData();
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
	    outDirField1.setText(System.getProperty("user.dir"));
	    seqArea.setText("");
	}

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        outDirButton1 = new javax.swing.JButton();
        outDirField1 = new javax.swing.JTextField();
        seqButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        seqNameField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        seqArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        res1Field = new javax.swing.JTextField();
        res2Field = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        fullAutoChkBox = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(modWizVisualPanel1.class, "modWizVisualPanel1.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(outDirButton1, org.openide.util.NbBundle.getMessage(modWizVisualPanel1.class, "modWizVisualPanel1.outDirButton1.text")); // NOI18N
        outDirButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outDirButton1ActionPerformed(evt);
            }
        });

        outDirField1.setText(org.openide.util.NbBundle.getMessage(modWizVisualPanel1.class, "modWizVisualPanel1.outDirField1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(seqButton, org.openide.util.NbBundle.getMessage(modWizVisualPanel1.class, "modWizVisualPanel1.seqButton.text")); // NOI18N
        seqButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seqButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(modWizVisualPanel1.class, "modWizVisualPanel1.jLabel5.text")); // NOI18N

        seqNameField.setText(org.openide.util.NbBundle.getMessage(modWizVisualPanel1.class, "modWizVisualPanel1.seqNameField.text")); // NOI18N

        seqArea.setColumns(20);
        seqArea.setLineWrap(true);
        seqArea.setRows(5);
        jScrollPane1.setViewportView(seqArea);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(modWizVisualPanel1.class, "modWizVisualPanel1.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(modWizVisualPanel1.class, "modWizVisualPanel1.jLabel3.text")); // NOI18N

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(modWizVisualPanel1.class, "modWizVisualPanel1.jLabel4.text")); // NOI18N

        res1Field.setText(org.openide.util.NbBundle.getMessage(modWizVisualPanel1.class, "modWizVisualPanel1.res1Field.text")); // NOI18N

        res2Field.setEditable(false);
        res2Field.setText(org.openide.util.NbBundle.getMessage(modWizVisualPanel1.class, "modWizVisualPanel1.res2Field.text")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(res2Field, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(res1Field, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(res1Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(res2Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
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
                .addComponent(fullAutoChkBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(fullAutoChkBox)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(outDirField1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(seqButton)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seqNameField))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(outDirButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outDirButton1)
                .addGap(18, 18, 18)
                .addComponent(outDirField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seqButton)
                    .addComponent(jLabel5)
                    .addComponent(seqNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
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

    private String getALIChoose(String lastDir, String title){
        String file = null;
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser(lastDir);
        fc.setDialogTitle(title);
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(new ALIFilter());
        fc.setAcceptAllFileFilterUsed(true);
        fc.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
        int retval = fc.showOpenDialog(this);
        if (retval == fc.APPROVE_OPTION) {
            file = fc.getSelectedFile().getPath();
        }

        return file;
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

    public String[] getDis(){
	String[] ret = res1Field.getText().split(":");
	//ret = new String[] {res1Field.getText(), res2Field.getText()};

        return ret;
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

    public String getSeqName(){
        return seqNameField.getText();
    }
    
    public void setSeqName(String seqName){
        seqNameField.setText(seqName);
    }

    public static String getSeqField(){
        return seqArea.getText();
    }

    public boolean isAuto(){
	//return fullAuto;
	return false;
    }

     // Highlight the occurrences of the word "public"


// Creates highlights around all occurrences of pattern in textComp
    public void highlight(JTextComponent textComp, String pattern) {
        // First remove all old highlights

        try {
            Highlighter hilite = textComp.getHighlighter();
            Document doc = textComp.getDocument();
            String text = doc.getText(0, doc.getLength());
            int pos = 0;

            // Search for pattern
            while ((pos = text.indexOf(pattern, pos)) >= 0) {
                // Create highlighter using private painter and apply around pattern
                hilite.addHighlight(pos, ++pos, myHighlightPainter);

            }
        } catch (BadLocationException e) {
        }
    }

// Removes only our private highlights
    public void removeHighlights(JTextComponent textComp) {
        Highlighter hilite = textComp.getHighlighter();
        Highlighter.Highlight[] hilites = hilite.getHighlights();

        for (int i = 0; i < hilites.length; i++) {
            if (hilites[i].getPainter() instanceof MyHighlightPainter) {
                hilite.removeHighlight(hilites[i]);
            }
        }
    }
// An instance of the private subclass of the default highlight painter
    Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter(Color.red);

// A private subclass of the default highlight painter
    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {

        public MyHighlightPainter(Color color) {
            super(color);
        }
    }

	private void seqButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seqButtonActionPerformed
		String file = getALIChoose("./", "Select Sequence File");
		if(file != null)
			seqArea.setText(file);
}//GEN-LAST:event_seqButtonActionPerformed

	private void outDirButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outDirButton1ActionPerformed
		String dir = getDirChoose("./");
		if(dir != null){
			//lastOutDir = dir;
			outDirField1.setText(dir);
		}   
}//GEN-LAST:event_outDirButton1ActionPerformed

	private void fullAutoChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullAutoChkBoxActionPerformed
		if(fullAuto) fullAuto = false;
		else fullAuto = true;
	}//GEN-LAST:event_fullAutoChkBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox fullAutoChkBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton outDirButton1;
    protected static javax.swing.JTextField outDirField1;
    private javax.swing.JTextField res1Field;
    private javax.swing.JTextField res2Field;
    protected static javax.swing.JTextArea seqArea;
    private javax.swing.JButton seqButton;
    private javax.swing.JTextField seqNameField;
    // End of variables declaration//GEN-END:variables
}
