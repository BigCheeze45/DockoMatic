/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
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
public final class outputGridTopComponent extends TopComponent implements TableModelListener{

    //private static JPopupMenu jpop = new JPopupMenu();
    private static Boolean swarmToggle = false;
    private static Boolean includeToggle = true;
    private static JPopupMenu jpop;// = new JPopupMenu();
    private static int tabCount=0;
	private static outputGridTopComponent instance;
	/** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
	private static final String PREFERRED_ID = "outputGridTopComponent";

	public outputGridTopComponent() {
		initComponents();
		makePopMenu();
		setName(NbBundle.getMessage(outputGridTopComponent.class, "CTL_outputGridTopComponent"));
		setToolTipText(NbBundle.getMessage(outputGridTopComponent.class, "HINT_outputGridTopComponent"));
//        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
		putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
		putClientProperty(TopComponent.PROP_KEEP_PREFERRED_SIZE_WHEN_SLIDED_IN, Boolean.TRUE);

	}

	protected static TableModel newTabb()
	{
		tabCount=outGridTPane.getTabCount();

                outGridTPane.addTab("tab "+tabCount, new JScrollPane(new JTable()));
		JScrollPane tmpPane = (JScrollPane)outGridTPane.getComponentAt(tabCount);
		JTable tmpTable = (JTable)tmpPane.getViewport().getView();

                tmpTable.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "Job #", "Ligand", "Output Directory", "Receptor", "Box Coordinate", "Secondary", "Use Swarm", "Status", "Sequence", "Template", "RUN"
                        }
                ) {
                        Class[] types = new Class [] {
                                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                        };
                        boolean[] canEdit = new boolean [] {
                                false, true, true, true, true, true, true, false, true, false, true
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
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
	public void tableChanged(TableModelEvent e){
		TableModel tmpTable = (TableModel)e.getSource();
		int row = e.getFirstRow();
		if(((String)tmpTable.getValueAt(row, 7)).equals("Started"))// &&
			   //((String)tmpTable.getValueAt(row, 5)).length() > 0)
			System.out.println("LISTENER SHOULD START SECONDARY NOW IF DONE");
		//openerTopComponent.doSecondary(row);
	}

	protected static JTable getTabTable(int i){
		return (JTable)((JScrollPane)outGridTPane.getComponentAt(i)).getViewport().getView();
	}

	protected static JTable getSelectedTable(){
		return (JTable)((JScrollPane)outGridTPane.getSelectedComponent()).getViewport().getView();
	}

	protected static TableModel getSelectedTab(){
		return ((JTable)((JScrollPane)outGridTPane.getSelectedComponent()).getViewport().getView()).getModel();
	}

	// Create a popup menu
	private static void makePopMenu(){
		JMenuItem optionMenuStart = new JMenuItem( "Start" );
		JMenuItem optionMenuStop = new JMenuItem( "Stop" );
		JMenuItem optionMenuDelete = new JMenuItem( "Delete" );
		JMenuItem optionMenuRC = new JMenuItem( "Check Result" );
		JMenuItem optionMenuPymol = new JMenuItem( "View in Pymol" );


		jpop = new JPopupMenu( "Options" );
		jpop.add( optionMenuStart );
//		jpop.add( optionMenuStop );
		jpop.add( optionMenuDelete );
		jpop.add( optionMenuRC );
		jpop.add( optionMenuPymol );


		optionMenuStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			    openerTopComponent.startSelected(e);
			}
		});
//		optionMenuStop.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e){
//			    openerTopComponent.startSelected(e);
//			}
//		});
		optionMenuDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			    openerTopComponent.removeSelected(e);
			}
		});
		optionMenuRC.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			    openerTopComponent.checkRes(e);
			}
		});
		optionMenuPymol.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			    openerTopComponent.pymolView(e);
			}
		});


	}

	private static void selectAllInclude()
	{
		//System.out.println("SELECT ALL JOBS SHOULD BE "+includeToggle);
	    JTable table = getSelectedTable();

	    DefaultTableModel model = (DefaultTableModel)table.getModel();
            int job;

            for(int i = 0; i< model.getRowCount(); i++){
                //job = (Integer)table.getValueAt(i, 1);

                if(includeToggle){
//			table.selectAll();
                    //jobVector.get(job).setSwarm(false);
                    //table.removeRowSelectionInterval(i, i);
                    table.setValueAt(false, i, 10);
                }else{
//			table.clearSelection();
                    //jobVector.get(job).setSwarm(true);
                    //table.addRowSelectionInterval(i, i);
                    table.setValueAt(true, i, 10);
                    //table.setRowSelectionInterval(i, i);
                }
            }
                if(includeToggle)
		    includeToggle=false;
		else
		    includeToggle=true;

	}

	private static void selectAllSwarm()
	{
		//System.out.println("SELECT ALL SWARM SHOULD BE "+swarmToggle);
	    JTable table = getSelectedTable();
	    DefaultTableModel model = (DefaultTableModel)table.getModel();
            int job;

            for(int i = 0; i< model.getRowCount(); i++){
                //job = (Integer)table.getValueAt(i, 1);
                if(swarmToggle){
                    //jobVector.get(job).setSwarm(false);
                    table.setValueAt(false, i, 6);
                }else{
                    //jobVector.get(job).setSwarm(true);
                    table.setValueAt(true, i, 6);
                }
            }
                if(swarmToggle)
		    swarmToggle=false;
		else
		    swarmToggle=true;

	}

	private static void headerMousePressed(java.awt.event.MouseEvent e) {

		JTable table = getSelectedTable();
		JTableHeader header = table.getTableHeader();
		//TableColumnModel columns = header.getColumnModel();

//		 if (!columns.getColumnSelectionAllowed())
//        return;

    int column = header.columnAtPoint(e.getPoint());
		//System.out.println("HEADER MOUSE: COL "+column);

    if(column == 10)
	    selectAllInclude();
    else if(column == 6)
            selectAllSwarm();
	}

	private static void TabbMousePressed(java.awt.event.MouseEvent e) {
		//System.out.println("TAB MOUSE");
		if(e.isPopupTrigger()){
			jpop.show(e.getComponent(), e.getX(), e.getY());
			return;
		}

//		JTable table = getSelectedTable();
//		JTableHeader header = table.getTableHeader();
//		//TableColumnModel columns = header.getColumnModel();
//
////		 if (!columns.getColumnSelectionAllowed())
////        return;
//
//    int column = header.columnAtPoint(e.getPoint());
//
//    if(column == 10)
//	    selectAllInclude();
//    else if(column == 6)
//            selectAllSwarm();

//    int count = table.getRowCount();
//
//    if (count != 0)
//        table.setRowSelectionInterval(0, count - 1);
//
//    ListSelectionModel selection = columns.getSelectionModel();
//
//    if (e.isShiftDown())
//    {
//        int anchor = selection.getAnchorSelectionIndex();
//        int lead = selection.getLeadSelectionIndex();
//
//        if (anchor != -1)
//        {
//            boolean old = selection.getValueIsAdjusting();
//            selection.setValueIsAdjusting(true);
//
//            boolean anchorSelected = selection.isSelectedIndex(anchor);
//
//            if (lead != -1)
//            {
//                if (anchorSelected)
//                    selection.removeSelectionInterval(anchor, lead);
//                else
//                    selection.addSelectionInterval(anchor, lead);
//                // The latter is quite unintuitive.
//            }
//
//            if (anchorSelected)
//                selection.addSelectionInterval(anchor, column);
//            else
//                selection.removeSelectionInterval(anchor, column);
//
//            selection.setValueIsAdjusting(old);
//        }
//        else
//            selection.setSelectionInterval(column, column);
//    }
//    else if (e.isControlDown())
//    {
//        if (selection.isSelectedIndex(column))
//            selection.removeSelectionInterval(column, column);
//        else
//            selection.addSelectionInterval(column, column);
//    }
//    else
//    {
//        selection.setSelectionInterval(column, column);
//    }



	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
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
                                "Job #", "Ligand", "Output Directory", "Receptor", "Box Coordinate", "Secondary", "Use Swarm", "Status", "Sequence", "Template", "RUN"
                        }
                ) {
                        Class[] types = new Class [] {
                                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
                        };
                        boolean[] canEdit = new boolean [] {
                                false, true, true, true, true, true, true, true, true, true, true
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                jTable1.setColumnSelectionAllowed(true);
                jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                                jTable1MousePressed(evt);
                        }
                });
                jScrollPane1.setViewportView(jTable1);
                jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                jTable1.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title0_1")); // NOI18N
                jTable1.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title1_1")); // NOI18N
                jTable1.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title2_1")); // NOI18N
                jTable1.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title3_1")); // NOI18N
                jTable1.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title4")); // NOI18N
                jTable1.getColumnModel().getColumn(5).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title5")); // NOI18N
                jTable1.getColumnModel().getColumn(6).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title6")); // NOI18N
                jTable1.getColumnModel().getColumn(7).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title7")); // NOI18N
                jTable1.getColumnModel().getColumn(8).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title8")); // NOI18N
                jTable1.getColumnModel().getColumn(9).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title9")); // NOI18N
                jTable1.getColumnModel().getColumn(10).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title10")); // NOI18N

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

	private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
				if(evt.isPopupTrigger())
					System.out.println("POPUP\n");
				else
		System.out.println("MOUSE CLICKED\n");
		//showPopup(evt);
	}//GEN-LAST:event_jTable1MousePressed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        protected static javax.swing.JScrollPane jScrollPane1;
        protected static javax.swing.JTable jTable1;
        protected static javax.swing.JTabbedPane outGridTPane;
        // End of variables declaration//GEN-END:variables
        protected static javax.swing.JScrollPane jScrollPane2;
        protected static javax.swing.JTable jTable2;
	/**
	 * Gets default instance. Do not use directly: reserved for *.settings files only,
	 * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
	 * To obtain the singleton instance, use {@link #findInstance}.
	 */
	public static synchronized outputGridTopComponent getDefault() {
		if (instance == null) {
			instance = new outputGridTopComponent();
		}
		return instance;
	}

	/**
	 * Obtain the outputGridTopComponent instance. Never call {@link #getDefault} directly!
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
		// TODO store your settings
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
		// TODO read your settings according to their version
	}

	@Override
	protected String preferredID() {
		return PREFERRED_ID;
	}
}
