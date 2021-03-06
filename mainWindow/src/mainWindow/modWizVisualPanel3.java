//# Program: "DockoMatic"
//# Project: "DNA Safeguard"
//# Filename: "modWizVisualPanel3.java"
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

/*
 * modWizVisualPanel3.java
 *
 * Created on Apr 14, 2011, 9:02:22 PM
 */
package mainWindow;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author wbullock
 */
public class modWizVisualPanel3 extends javax.swing.JPanel {

    /**
     * Creates new form modWizVisualPanel3
     */
    public modWizVisualPanel3() {
        initComponents();
        // implement kill job buttonlater
        killJobButton.setVisible(false);
    }

    protected static String[] getTempltList() {
        int[] rows = AlmntTable.getSelectedRows();
        String tmplt, loc;
        String[] list = new String[rows.length];

        for (int i = 0; i < rows.length; i++) {
            tmplt = (String) AlmntTable.getValueAt(rows[i], getCol("Alignment"));
            //loc = (String) AlmntTable.getValueAt(rows[i], getCol("Location"));
            //subTmplt = tmplt.substring(0, tmplt.indexOf(":"));
            //list[i] = subTmplt;
            //list[i] = loc+"/"+tmplt;
            list[i] = tmplt;
        }

        return list;
    }

    private static int getCol(String column) {
        int i = 0;
        int colCount = AlmntTable.getColumnCount();
        String name = "";
        for (i = 0; i < colCount; i++) {
            name = AlmntTable.getColumnName(i);
            if (column.equals(name)) {
                return i;
            }
        }

        return i;
    }

    @Override
    public String getName() {
        return "Select Alignment";
    }

    public void clearAllData() {
        DefaultTableModel model = (DefaultTableModel) AlmntTable.getModel();
        model.setRowCount(0);
    }

    public static JTable getTable() {
        return AlmntTable;
    }

    public TableModel getTableModel() {
        return AlmntTable.getModel();
    }

    public String getModelPath() {
        int row = AlmntTable.getSelectedRow();
        if (row < 0) {
            row = 0;
        }
        int locCol = getCol("Location");
        int modCol = getCol("Model");

        return (String) AlmntTable.getValueAt(row, locCol) + (String) AlmntTable.getValueAt(row, modCol);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                AlmntTable = new javax.swing.JTable();
                killJobButton = new javax.swing.JButton();
                jPanel7 = new javax.swing.JPanel();
                genAlmntMessage = new javax.swing.JLabel();

                AlmntTable.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "Alignment", "Location"
                        }
                ) {
                        Class[] types = new Class [] {
                                java.lang.String.class, java.lang.String.class
                        };
                        boolean[] canEdit = new boolean [] {
                                false, false
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                jScrollPane1.setViewportView(AlmntTable);
                AlmntTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel3.class, "modWizVisualPanel3.AlmntTable.columnModel.title0")); // NOI18N
                AlmntTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel3.class, "modWizVisualPanel3.AlmntTable.columnModel.title1")); // NOI18N

                killJobButton.setText(org.openide.util.NbBundle.getMessage(modWizVisualPanel3.class, "modWizVisualPanel3.killJobButton.text")); // NOI18N
                killJobButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                killJobButtonActionPerformed(evt);
                        }
                });

                genAlmntMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                genAlmntMessage.setText(org.openide.util.NbBundle.getMessage(modWizVisualPanel3.class, "modWizVisualPanel3.genAlmntMessage.text")); // NOI18N

                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                jPanel7.setLayout(jPanel7Layout);
                jPanel7Layout.setHorizontalGroup(
                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(genAlmntMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                                .addContainerGap())
                );
                jPanel7Layout.setVerticalGroup(
                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(genAlmntMessage)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(148, 148, 148)
                                .addComponent(killJobButton)
                                .addContainerGap(151, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addContainerGap()))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                                .addComponent(killJobButton))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(288, 288, 288)
                                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(51, Short.MAX_VALUE)))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
        }// </editor-fold>//GEN-END:initComponents

    private void killJobButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_killJobButtonActionPerformed
        modWizWizardPanel3.killJob();
    }//GEN-LAST:event_killJobButtonActionPerformed
        // Variables declaration - do not modify//GEN-BEGIN:variables
        protected static javax.swing.JTable AlmntTable;
        protected static javax.swing.JLabel genAlmntMessage;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel7;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JButton killJobButton;
        // End of variables declaration//GEN-END:variables
}
