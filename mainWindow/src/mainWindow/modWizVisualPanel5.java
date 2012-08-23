/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * modWizVisualPanel5.java
 *
 * Created on Apr 14, 2011, 9:02:22 PM
 */
package mainWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author wbullock
 */
public class modWizVisualPanel5 extends javax.swing.JPanel {

    private static modWizVisualPanel5 instance;
    private static JPopupMenu jpop;// = new JPopupMenu();

    /**
     * Creates new form modWizVisualPanel5
     */
    public modWizVisualPanel5() {
        initComponents();
        // implement kill job buttonlater
        killJobButton.setVisible(false);
    }

    @Override
    public String getName() {
        return "Select Model";
    }

    public void clearAllData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
    }

    public static int getCol(String column) {
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

    public static JTable getTable() {
        return jTable1;
    }

    public TableModel getTableModel() {
        return jTable1.getModel();
    }

    public String getModelPath() {
        int row = jTable1.getSelectedRow();
        if (row < 0) {
            row = 0;
        }
        int locCol = getCol("Location");
        int modCol = getCol("Model");

        return (String) jTable1.getValueAt(row, locCol) + (String) jTable1.getValueAt(row, modCol);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTable1.setAutoCreateRowSorter(true);
        killJobButton = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        genModelMessage = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Model", "Location", "MolPdf", "DOPE", "GA341"
                }) {

            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel5.class, "modWizVisualPanel4.jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel5.class, "modWizVisualPanel4.jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel5.class, "modWizVisualPanel4.jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel5.class, "modWizVisualPanel4.jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(modWizVisualPanel5.class, "modWizVisualPanel4.jTable1.columnModel.title4")); // NOI18N

        killJobButton.setText(org.openide.util.NbBundle.getMessage(modWizVisualPanel5.class, "modWizVisualPanel4.killJobButton.text")); // NOI18N
        killJobButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                killJobButtonActionPerformed(evt);
            }
        });

        genModelMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        genModelMessage.setText(org.openide.util.NbBundle.getMessage(modWizVisualPanel5.class, "modWizVisualPanel4.genModelMessage.text")); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel7Layout.createSequentialGroup().addContainerGap().addComponent(genModelMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE).addContainerGap()));
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel7Layout.createSequentialGroup().addGap(42, 42, 42).addComponent(genModelMessage).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE).addGroup(jPanel1Layout.createSequentialGroup().addGap(148, 148, 148).addComponent(killJobButton).addContainerGap(151, Short.MAX_VALUE)).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap())));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE).addComponent(killJobButton)).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(288, 288, 288).addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(51, Short.MAX_VALUE))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    }// </editor-fold>

    // Create a popup menu
    private static void makePopMenu(final java.awt.event.MouseEvent evtOrig, final String[] tmplts) {
        JMenuItem optionMenuInfo = new JMenuItem("Make Ramachandran Plot");

        jpop = new JPopupMenu("Options");
        jpop.add(optionMenuInfo);

        optionMenuInfo.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                //goToInfo(tmplts);
            }
        });

        jpop.show(evtOrig.getComponent(), evtOrig.getX(), evtOrig.getY());

    }

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {

        if (evt.isPopupTrigger()) {
            int[] rows = jTable1.getSelectedRows();
            makePopMenu(evt, getTempltList());
        }

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

    

//	private void browseTmpltButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                  
//            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
//
//            String file = getFileChoose("~/");
//            tm.addRow(new Object[]{file});
//
//	}                                                 
//    private static void goToInfo(String[] tmplts) {
//        Desktop dt = Desktop.getDesktop();
//
//        for (int i = 0; i < tmplts.length; i++) {
//            try {
//                dt.browse(new java.net.URI("http://www.pdb.org/pdb/explore/explore.do?structureId=" + tmplts[i].substring(0, tmplts[i].indexOf(":"))));
//            } catch (Exception e) {
//                System.out.println("CAUGHT EXCEPTION: " + e);
//            }
//
//        }
//    }
//    protected static void ramaPlot(java.awt.event.MouseEvent e, java.awt.event.MouseEvent evtOrig) {
//        instance.CreateRamaPlotActionPerformed(e);
//
//    }
//    private void CreateRamaPlotActionPerformed(java.awt.event.MouseEvent evt) {
//        SwingWorker jobStartWorker = new SwingWorker<String, Void>() {
//
//            @Override
//            public String doInBackground() {
//
//                
//                try {     
//                   // Process procID = Runtime.getRuntime().exec("swarm -f " + swarmFile + " -n " + swmJobNum.getText() + " -l walltime=128:00:00", null, outDir);
//                    //System.out.println("USING DOCK CMD: swarm -f " + swarmFile + " -n "+swmJobNum.getText()+" -l walltime=128:00:00");
//
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//
//                return "Done";
//            }
//        };
//
//        jobStartWorker.execute();
//    }
    private void killJobButtonActionPerformed(java.awt.event.ActionEvent evt) {
        modWizWizardPanel5.killJob();
    }
    // Variables declaration - do not modify
    protected static javax.swing.JLabel genModelMessage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    protected static javax.swing.JTable jTable1;
    private javax.swing.JButton killJobButton;
    // End of variables declaration
}
