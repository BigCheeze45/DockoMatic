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

    /** Creates new form modWizVisualPanel3 */
    public modWizVisualPanel3() {
        initComponents();
    }

	@Override
	public String getName() {
		return "Tim #3";
	}

	public void clearAllData(){
	    DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
	}

	public int getCol(String column){
	    int i =0;
	    int colCount = jTable1.getColumnCount();
	    String name = new String("");
	    for(i=0; i<colCount; i++){
		    name = jTable1.getColumnName(i);
		    if(column.equals(name)) return i;
	    }

            return i;
	}

    public JTable getTable(){
	    return jTable1;
	}

    public TableModel getTableModel(){
	    return jTable1.getModel();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();
                killJobButton = new javax.swing.JButton();

                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "Model #", "Location", "Score"
                        }
                ) {
                        Class[] types = new Class [] {
                                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
                        };
                        boolean[] canEdit = new boolean [] {
                                false, false, false
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                jScrollPane1.setViewportView(jTable1);
                jTable1.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel3.class, "modWizVisualPanel3.jTable1.columnModel.title0")); // NOI18N
                jTable1.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel3.class, "modWizVisualPanel3.jTable1.columnModel.title1")); // NOI18N
                jTable1.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel3.class, "modWizVisualPanel3.jTable1.columnModel.title2")); // NOI18N

                killJobButton.setText(org.openide.util.NbBundle.getMessage(modWizVisualPanel3.class, "modWizVisualPanel3.killJobButton.text")); // NOI18N
                killJobButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                killJobButtonActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(167, 167, 167)
                                .addComponent(killJobButton)
                                .addContainerGap(129, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(killJobButton))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
        }// </editor-fold>//GEN-END:initComponents

    private void killJobButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_killJobButtonActionPerformed
	    modWizWizardPanel3.killJob();
    }//GEN-LAST:event_killJobButtonActionPerformed


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JPanel jPanel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable jTable1;
        private javax.swing.JButton killJobButton;
        // End of variables declaration//GEN-END:variables

}
