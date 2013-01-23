//# Program: "DockoMatic"
//# Project: "DNA Safeguard"
//# Filename: "outputGridTopComponent.java"
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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
//import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//mainWindow//outputGrid//EN",
autostore = false)
public final class outputGridTopComponent extends TopComponent implements TableModelListener {

    private static Boolean swarmToggle = false;
    private static Boolean includeToggle = true;
    private static JPopupMenu jpop;// = new JPopupMenu();
    private static int tabCount = 0;
    private static outputGridTopComponent instance;
    /**
     * path to the icon used by the component and its open action
     */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "outputGridTopComponent";

    public outputGridTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(outputGridTopComponent.class, "CTL_outputGridTopComponent"));
        setToolTipText(NbBundle.getMessage(outputGridTopComponent.class, "HINT_outputGridTopComponent"));
//        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_KEEP_PREFERRED_SIZE_WHEN_SLIDED_IN, Boolean.TRUE);
        jTable1.setColumnSelectionAllowed(true);
        //JTableHeader head = jTable1.getTableHeader();
        //head.addMouseListener(new java.awt.event.MouseAdapter() {
        //      public void mousePressed(java.awt.event.MouseEvent evt) {
        //                headerMousePressed(evt);
        //        }
        //});

    }

    protected static void closeTabb(final MouseEvent evtOrig) {
        outGridTPane.remove(outGridTPane.indexOfTabComponent((evtOrig.getComponent()).getParent()));
    }

    protected static TableModel newTabb() {
        tabCount = outGridTPane.getTabCount();

        outGridTPane.addTab("tab " + tabCount, new JScrollPane(new JTable()));
        //outGridTPane.setTabComponentAt(tabCount, new JButton("x"));
        JButton tmpBut = new JButton("x");
        JPanel pan = new JPanel();
        //outGridTPane.setDisabledIconAt(tabCount, null);
        pan.add(new JLabel(outGridTPane.getTitleAt(tabCount), null, JLabel.LEFT));
        pan.add(tmpBut);
        outGridTPane.setTabComponentAt(tabCount, pan);
        //outGridTPane.setTabComponentAt(tabCount, tmpBut);
        //outGridTPane.getTabComponentAt(tabCount).add(new JButton("x"));
        //pan.addMouseListener(new java.awt.event.MouseAdapter() {
        tmpBut.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeTabb(evt);
            }
        });

        JScrollPane tmpPane = (JScrollPane) outGridTPane.getComponentAt(tabCount);
        JTable tmpTable = (JTable) tmpPane.getViewport().getView();

        tmpTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    //"Job #", "Ligand", "Output Directory", "Receptor", "Box Coordinate", "Secondary", "Use Swarm", "Status", "Sequence", "Template", "ACTION"
                    "Job #", "Ligand", "Output Directory", "Receptor", "Box Coordinate", "Secondary", "Status", "Sequence", "Template", "AutoDock Cycles"
                }) {

            Class[] types = new Class[]{
                //java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                //false, true, true, true, true, true, false, false, true, true, true
                false, true, true, true, true, true, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tmpTable.setColumnSelectionAllowed(true);
        JTableHeader header = tmpTable.getTableHeader();
        header.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });
        tmpTable.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mousePressed(java.awt.event.MouseEvent evt) {
                TabbMousePressed(evt);
            }
        });
        //tmpTable.getModel().addTableModelListener(instance);
        //tmpTable.getModel().addTableModelListener(tmListen);
        tmpPane.setViewportView(tmpTable);
        outGridTPane.setSelectedIndex(tabCount);

        return tmpTable.getModel();
    }

    //void insertRowsctionPerformed(ActionEvent e) {
    //  int row = MyWidgetTable.getSelectedRow();
    //  controller.doInsertPagesAction(row);
    //}
    @Override
    public void tableChanged(TableModelEvent e) {
        TableModel tmpTable = (TableModel) e.getSource();
        int row = e.getFirstRow();
        if (((String) tmpTable.getValueAt(row, 7)).equals("Started"))// &&
        //((String)tmpTable.getValueAt(row, 5)).length() > 0)
        {
            System.out.println("LISTENER SHOULD START SECONDARY NOW IF DONE");
        }
        //openerTopComponent.doSecondary(row);
    }

    protected static JTable getTabTable(int i) {
        return (JTable) ((JScrollPane) outGridTPane.getComponentAt(i)).getViewport().getView();
    }

    protected static JTable getSelectedTable() {
        return (JTable) ((JScrollPane) outGridTPane.getSelectedComponent()).getViewport().getView();
    }

    protected static TableModel getSelectedTab() {
        return ((JTable) ((JScrollPane) outGridTPane.getSelectedComponent()).getViewport().getView()).getModel();
    }

    // Create a popup menu
    private static void makePopMenu(final java.awt.event.MouseEvent evtOrig) {
        JMenuItem optionMenuStart = new JMenuItem("Start");
        JMenuItem optionMenuDelete = new JMenuItem("Delete");
        JMenuItem optionMenuRC = new JMenuItem("Analyze");
        JMenuItem optionMenuPymol = new JMenuItem("View in Pymol");
        //JMenuItem optionDisplayMore = new JMenuItem( "Display More Jobs" );


        jpop = new JPopupMenu("Options");
        jpop.add(optionMenuStart);
        jpop.add(optionMenuDelete);
        jpop.add(optionMenuRC);
        jpop.add(optionMenuPymol);
        //jpop.add( optionDisplayMore );


        optionMenuStart.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                openerTopComponent.startSelected(e, evtOrig);
            }
        });
        optionMenuDelete.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                openerTopComponent.removeSelected(e, evtOrig);
            }
        });
        optionMenuRC.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                openerTopComponent.checkRes(e, evtOrig);
            }
        });
        optionMenuPymol.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                openerTopComponent.pymolView(e, evtOrig);
            }
        });

//		optionDisplayMore.addMouseListener(new MouseAdapter(){
//			public void mousePressed(MouseEvent e){
//			    openerTopComponent.displayMore(e, evtOrig);
//			}
//		});

        jpop.show(evtOrig.getComponent(), evtOrig.getX(), evtOrig.getY());

    }

    private static void selectAllInclude() {
        JTable table = getSelectedTable();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int job;

        //for(int i = 0; i< model.getRowCount(); i++){

        if (includeToggle) {
            //table.setValueAt(false, i, getCol("ACTION"));
            //table.removeRowSelectionInterval(i, i);
            table.clearSelection();
        } else {
            //table.setValueAt(true, i, getCol("ACTION"));
            //table.addRowSelectionInterval(i, i);
            table.selectAll();
        }
        //}
        if (includeToggle) {
            includeToggle = false;
        } else {
            includeToggle = true;
        }

    }

    private static int getCol(String column) {
        JTable table = getSelectedTable();
        int i = 0;
        int colCount = table.getColumnCount();
        String name = new String("");
        for (i = 0; i < colCount; i++) {
            name = table.getColumnName(i);
            if (column.equals(name)) {
                return i;
            }
        }

        return i;
    }

    private static void selectAllSwarm() {
        JTable table = getSelectedTable();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int job;

        for (int i = 0; i < model.getRowCount(); i++) {
            if (swarmToggle) {
                table.setValueAt(false, i, getCol("Use Swarm"));
            } else {
                table.setValueAt(true, i, getCol("Use Swarm"));
            }
        }
        if (swarmToggle) {
            swarmToggle = false;
        } else {
            swarmToggle = true;
        }

    }

    private static void headerMousePressed(java.awt.event.MouseEvent e) {

        JTable table = getSelectedTable();
        JTableHeader header = table.getTableHeader();
        int column = header.columnAtPoint(e.getPoint());
        String colName = table.getColumnName(column);
        //JPanel panel;
        JButton button;

        //if(column == 10)
        //if(colName.equals("ACTION"))
        if (colName.equals("Job #")) {
            selectAllInclude();
        }
        //else if(column == 5){
//            else if(colName.equals("Secondary")){
//                //panel = openerTopComponent.jPanel7;
//                button = openerTopComponent.appendButton;
//                if(button.isEnabled()){// panel.setVisible(false);
//		    openerTopComponent.setAppVis(false);
//		} else{
//		//	    panel.setVisible(true);
//		    openerTopComponent.setAppVis(true);
//		    }
//	    //} else if (column == 6)
//	    } else if (colName.equals("Use Swarm"))
//                selectAllSwarm();
    }

    private static void TabbMousePressed(java.awt.event.MouseEvent e) {
        int button = e.getButton();
        JTable table = getSelectedTable();
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);
        int row = table.rowAtPoint(e.getPoint());
        if (table.isRowSelected(row)) {
            table.addRowSelectionInterval(row, row);
            //table.setValueAt(true, row, getCol("ACTION"));
        } else {
            table.removeRowSelectionInterval(row, row);
            //table.setValueAt(false, row, getCol("ACTION"));
        }
        if (e.isPopupTrigger()) {
            //jpop.show(e.getComponent(), e.getX(), e.getY());
            makePopMenu(e);
            return;
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                outGridTPane = new javax.swing.JTabbedPane();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();

                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "Job #", "Ligand", "Output Directory", "Receptor", "Box Coordinate", "Secondary", "Status", "AutoDock Cycles"
                        }
                ) {
                        Class[] types = new Class [] {
                                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                        };
                        boolean[] canEdit = new boolean [] {
                                false, true, true, true, true, true, true, true
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                jScrollPane1.setViewportView(jTable1);
                jTable1.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title0_1")); // NOI18N
                jTable1.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title1_1")); // NOI18N
                jTable1.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title2_1")); // NOI18N
                jTable1.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title3_1")); // NOI18N
                jTable1.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title4")); // NOI18N
                jTable1.getColumnModel().getColumn(5).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title5")); // NOI18N
                jTable1.getColumnModel().getColumn(6).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title7")); // NOI18N
                jTable1.getColumnModel().getColumn(7).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title8")); // NOI18N

                outGridTPane.addTab(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jScrollPane1.TabConstraints.tabTitle"), jScrollPane1); // NOI18N

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(outGridTPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(outGridTPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                );
        }// </editor-fold>//GEN-END:initComponents
        // Variables declaration - do not modify//GEN-BEGIN:variables
        protected static javax.swing.JScrollPane jScrollPane1;
        protected static javax.swing.JTable jTable1;
        protected static javax.swing.JTabbedPane outGridTPane;
        // End of variables declaration//GEN-END:variables
    protected static javax.swing.JScrollPane jScrollPane2;
    protected static javax.swing.JTable jTable2;

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files
     * only, i.e. deserialization routines; otherwise you could get a
     * non-deserialized instance. To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized outputGridTopComponent getDefault() {
        if (instance == null) {
            instance = new outputGridTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the outputGridTopComponent instance. Never call {@link #getDefault}
     * directly!
     */
    public static synchronized outputGridTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(outputGridTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof outputGridTopComponent) {
            return (outputGridTopComponent) win;
        }
        Logger.getLogger(outputGridTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
    }

    Object readProperties(java.util.Properties p) {
        if (instance == null) {
            instance = this;
        }
        instance.readPropertiesImpl(p);
        return instance;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }
}
