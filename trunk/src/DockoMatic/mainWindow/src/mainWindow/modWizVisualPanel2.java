# Program: "DockoMatic"
# Project: "DNA Safeguard"
# Filename: "modWizVisualPanel2.java"
#
# Dr. Tim Andersen
# Department of Computer Science
# College of Engineering
# Boise State University
#
# Original Author(s): "Casey Bullock"
#                     "Nic Cornia"
#
# Last Modified
#   Date: "November 14, 2012"
#
#
#  This file is part of DockoMatic.
#
#  DockoMatic is free software: you can redistribute it and/or modify
#  it under the terms of the GNU Lesser General Public License as published by
#  the Free Software Foundation, either version 3 of the License, or
#  (at your option) any later version.
#
#  DockoMatic is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU Lesser General Public License for more details.
#
#  You should have received a copy of the GNU Lesser General Public License
#  along with DockoMatic.  If not, see <http://www.gnu.org/licenses/>.
#

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainWindow;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;

public final class modWizVisualPanel2 extends JPanel {

    private boolean useSwarm = false;
    private static JPopupMenu jpop;// = new JPopupMenu();

    /**
     * Creates new form modWizVisualPanel2
     */
    public modWizVisualPanel2() {
        initComponents();
        swarmChkBox.setVisible(false);
    }

    @Override
    public String getName() {
        return "Select Template";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        numModelsField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        swarmChkBox = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        getTempltMessage = new javax.swing.JLabel();
        moreInfoLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jobsPerNodeLabel = new javax.swing.JLabel();
        jobsPerNodeField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        browseTmpltButton = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(modWizVisualPanel2.class, "modWizVisualPanel2.jLabel1.text")); // NOI18N

        numModelsField.setText(org.openide.util.NbBundle.getMessage(modWizVisualPanel2.class, "modWizVisualPanel2.numModelsField.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(numModelsField, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numModelsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Template", "E Value", "Length", "Score", "Identities", "Positives", "Gaps"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel2.class, "modWizVisualPanel2.jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel2.class, "modWizVisualPanel2.jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel2.class, "modWizVisualPanel2.jTable1.columnModel.title2_1")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel2.class, "modWizVisualPanel2.jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel2.class, "modWizVisualPanel2.jTable1.columnModel.title6")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel2.class, "modWizVisualPanel2.jTable1.columnModel.title7")); // NOI18N
        jTable1.getColumnModel().getColumn(6).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel2.class, "modWizVisualPanel2.jTable1.columnModel.title8")); // NOI18N

        swarmChkBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(swarmChkBox, org.openide.util.NbBundle.getMessage(modWizVisualPanel2.class, "modWizVisualPanel2.swarmChkBox.text")); // NOI18N
        swarmChkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                swarmChkBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(swarmChkBox, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(swarmChkBox)
                .addContainerGap())
        );

        getTempltMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(getTempltMessage, org.openide.util.NbBundle.getMessage(modWizVisualPanel2.class, "modWizVisualPanel2.getTempltMessage.text")); // NOI18N

        moreInfoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(moreInfoLabel, org.openide.util.NbBundle.getMessage(modWizVisualPanel2.class, "modWizVisualPanel2.moreInfoLabel.text")); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(moreInfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(getTempltMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(getTempltMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(moreInfoLabel)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jobsPerNodeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.openide.awt.Mnemonics.setLocalizedText(jobsPerNodeLabel, org.openide.util.NbBundle.getMessage(modWizVisualPanel2.class, "modWizVisualPanel2.jobsPerNodeLabel.text")); // NOI18N

        jobsPerNodeField.setText(org.openide.util.NbBundle.getMessage(modWizVisualPanel2.class, "modWizVisualPanel2.jobsPerNodeField.text")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jobsPerNodeLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                    .addComponent(jobsPerNodeField, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jobsPerNodeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jobsPerNodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.openide.awt.Mnemonics.setLocalizedText(browseTmpltButton, org.openide.util.NbBundle.getMessage(modWizVisualPanel2.class, "modWizVisualPanel2.browseTmpltButton.text")); // NOI18N
        browseTmpltButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseTmpltButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(browseTmpltButton, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(browseTmpltButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

	private void swarmChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_swarmChkBoxActionPerformed
            if (swarmChkBox.isSelected()) {
                useSwarm = true;
            } else {
                useSwarm = false;
            }
	}//GEN-LAST:event_swarmChkBoxActionPerformed

    // Create a popup menu
    private static void makePopMenu(final java.awt.event.MouseEvent evtOrig, final String[] tmplts) {
        JMenuItem optionMenuInfo = new JMenuItem("Open in Browser");

        jpop = new JPopupMenu("Options");
        jpop.add(optionMenuInfo);

        optionMenuInfo.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                goToInfo(tmplts);
            }
        });

        jpop.show(evtOrig.getComponent(), evtOrig.getX(), evtOrig.getY());

    }

	private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed

            if (evt.isPopupTrigger()) {
                int[] rows = jTable1.getSelectedRows();
                makePopMenu(evt, getTempltList());
            }

	}//GEN-LAST:event_jTable1MousePressed

	private void browseTmpltButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseTmpltButtonActionPerformed
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();

            String file = getFileChoose("~/");
            tm.addRow(new Object[]{file});

	}//GEN-LAST:event_browseTmpltButtonActionPerformed

    private static void goToInfo(String[] tmplts) {
        Desktop dt = Desktop.getDesktop();

        for (int i = 0; i < tmplts.length; i++) {
            try {
                dt.browse(new java.net.URI("http://www.pdb.org/pdb/explore/explore.do?structureId=" + tmplts[i].substring(0, tmplts[i].indexOf(":"))));
            } catch (Exception e) {
                System.out.println("CAUGHT EXCEPTION: " + e);
            }

        }
    }

    public static String getNumModJobs() {
        return numModelsField.getText();
    }

    public static String getNumJobsPerNode() {
        return jobsPerNodeField.getText();
    }

    public static JTable getTable() {
        return jTable1;
    }

    public boolean isSwarm() {
        return useSwarm;
    }

    public void clearAllData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        numModelsField.setText("5");
        jobsPerNodeField.setText("5");
    }

    private static int getCol(String column) {
        int i = 0;
        int colCount = jTable1.getColumnCount();
        String name = new String("");
        for (i = 0; i < colCount; i++) {
            name = jTable1.getColumnName(i);
            if (column.equals(name)) {
                return i;
            }
        }

        return i;
    }

    protected static String[] getTempltList() {
        int[] rows = jTable1.getSelectedRows();
        String tmplt, subTmplt;
        String[] list = new String[rows.length];

        for (int i = 0; i < rows.length; i++) {
            tmplt = (String) jTable1.getValueAt(rows[i], getCol("Template"));
//	    if(tmplt.contains(":")){
//	        subTmplt = tmplt.substring(0, tmplt.indexOf(":"));
//	        list[i] = subTmplt;
//	    }else
            list[i] = tmplt;
        }

        return list;
    }

    public String getTemplt() {
        int row = jTable1.getSelectedRow();
        if (row < 0) {
            row = 0;
        }
        return (String) jTable1.getValueAt(row, getCol("Template"));
    }

    public TableModel getTableModel() {
        return jTable1.getModel();
    }

    private String getFileChoose(String lastDir) {
        String file = null;
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser(lastDir);
        int retval = fc.showOpenDialog(this);
        if (retval == fc.APPROVE_OPTION) {
            file = fc.getSelectedFile().getPath();
        }

        return file;

    }

    private String getDirChoose(String lastDir) {
        String dir = null;
        javax.swing.JFileChooser fc = new javax.swing.JFileChooser(lastDir);
        fc.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        int retval = fc.showOpenDialog(this);
        if (retval == fc.APPROVE_OPTION) {
            dir = fc.getSelectedFile().getPath();
        }

        return dir;

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseTmpltButton;
    protected static javax.swing.JLabel getTempltMessage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    protected static javax.swing.JTable jTable1;
    protected static javax.swing.JTextField jobsPerNodeField;
    private javax.swing.JLabel jobsPerNodeLabel;
    protected static javax.swing.JLabel moreInfoLabel;
    protected static javax.swing.JTextField numModelsField;
    private javax.swing.JCheckBox swarmChkBox;
    // End of variables declaration//GEN-END:variables
}
