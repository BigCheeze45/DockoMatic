/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainWindow;

import java.util.logging.Logger;
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
public final class outputGridTopComponent extends TopComponent {

	private static outputGridTopComponent instance;
	/** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
	private static final String PREFERRED_ID = "outputGridTopComponent";

	public outputGridTopComponent() {
		initComponents();
		setName(NbBundle.getMessage(outputGridTopComponent.class, "CTL_outputGridTopComponent"));
		setToolTipText(NbBundle.getMessage(outputGridTopComponent.class, "HINT_outputGridTopComponent"));
//        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
		putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
		putClientProperty(TopComponent.PROP_KEEP_PREFERRED_SIZE_WHEN_SLIDED_IN, Boolean.TRUE);

	}

	protected static javax.swing.table.TableModel newTabb()
	{
                jScrollPane1 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();

                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "Job #", "Ligand", "Output Directory", "Receptor", "Box Coordinate", "Secondary", "Use Swarm", "Status", "Sequence", "Template"
                        }
                ) {
                        Class[] types = new Class [] {
                                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                        };
                        boolean[] canEdit = new boolean [] {
                                false, true, true, true, true, true, true, false, true, false
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                jScrollPane1.setViewportView(jTable1);
                //jTable2.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable2.columnModel.title0_1")); // NOI18N
                //jTable2.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable2.columnModel.title1_1")); // NOI18N
                //jTable2.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable2.columnModel.title2_1")); // NOI18N
                //jTable2.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable2.columnModel.title3_1")); // NOI18N
                //jTable2.getColumnModel().getColumn(4).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable2.columnModel.title4")); // NOI18N
                //jTable2.getColumnModel().getColumn(5).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable2.columnModel.title5")); // NOI18N
                //jTable2.getColumnModel().getColumn(6).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable2.columnModel.title6")); // NOI18N
                //jTable2.getColumnModel().getColumn(7).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable2.columnModel.title7")); // NOI18N
                //jTable2.getColumnModel().getColumn(8).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable2.columnModel.title8")); // NOI18N
                //jTable2.getColumnModel().getColumn(9).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable2.columnModel.title9")); // NOI18N

                outGridTPane.addTab("lenny", jScrollPane1); // NOI18N

	    return jTable1.getModel();
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
                "Job #", "Ligand", "Output Directory", "Receptor", "Box Coordinate", "Secondary", "Use Swarm", "Status", "Sequence", "Template"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, false, true, false
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
        jTable1.getColumnModel().getColumn(6).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title6")); // NOI18N
        jTable1.getColumnModel().getColumn(7).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title7")); // NOI18N
        jTable1.getColumnModel().getColumn(8).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title8")); // NOI18N
        jTable1.getColumnModel().getColumn(9).setHeaderValue(org.openide.util.NbBundle.getMessage(outputGridTopComponent.class, "outputGridTopComponent.jTable1.columnModel.title9")); // NOI18N

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
