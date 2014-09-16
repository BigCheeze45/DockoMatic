/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.boisestate.SimSearcher;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JComponent;
import kMeanCluster.ClusterDaemon;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.util.Exceptions;

// An example action demonstrating how the wizard could be called from within
// your code. You can move the code below wherever you need, or register an action:
// @ActionID(category="...", id="edu.boisestate.SimSearcher.ClusteringWizardAction")
// @ActionRegistration(displayName="Open Clustering Wizard")
// @ActionReference(path="Menu/Tools", position=...)
public final class ClusteringWizardAction implements ActionListener {

    private boolean wasCancelled;
    final static String OUTPUT_DIR = "var1";
    final static String MAPPED_DIR = "var2";
    final static String SWARM_OPTS = "var3";
    final static String CLUSTER_SIZES = "var4";
    final static String NUM_SWARM_CMDS = "var5";
    final static String DISTRIBUTION_TEST = "var6";
    final static String VERBOSE = "var7";
    private ClusterDaemon daemon;
    private final String config_path = "/home/tlong/ClusteredDB/.config";  //TODO
    private int numBins;
    private String properties;
    static final String CLUSTER_PROPS = ".cluster_properties";
    String output_path;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        List<WizardDescriptor.Panel<WizardDescriptor>> panels = new ArrayList<WizardDescriptor.Panel<WizardDescriptor>>();
        panels.add(new ClusteringWizardPanel1());
        String[] steps = new String[panels.size()];
        for (int i = 0; i < panels.size(); i++) {
            Component c = panels.get(i).getComponent();
            // Default step name to component name of panel.
            steps[i] = c.getName();
            if (c instanceof JComponent) { // assume Swing components
                JComponent jc = (JComponent) c;
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, i);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
                jc.putClientProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, true);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, true);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, true);
            }
        }
        WizardDescriptor wiz = new WizardDescriptor(new WizardDescriptor.ArrayIterator<WizardDescriptor>(panels));
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        wiz.setTitleFormat(new MessageFormat("{0}"));
        wiz.setTitle("Clustering Wizard");
        wasCancelled = true;
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            File config_file = new File(config_path);
            File indexedFolder = new File((String)wiz.getProperty(MAPPED_DIR));
            output_path = (String)wiz.getProperty(OUTPUT_DIR);
            File outputFolder = new File(output_path);
            int numProcesses = (Integer) wiz.getProperty(NUM_SWARM_CMDS);
            HashMap<String,String> mappedDB_properties = SimSearchUtilities.getProperties(new File(indexedFolder,MappingWizardAction.MAPPED_SETTINGS));
            numBins = Integer.parseInt(mappedDB_properties.get(MappingWizardAction.NUM_BINS_LABEL));
            boolean verbose = (Boolean) wiz.getProperty(VERBOSE);
            
            if(numBins > 0){
                String clusterSizes = (String) wiz.getProperty(CLUSTER_SIZES);
                String[] tmp = clusterSizes.split(",");

                int[] numClusters = new int[tmp.length];

                for (int i = 0; i < tmp.length; i++) {
                    numClusters[i] = Integer.parseInt(tmp[i]);
                }

                boolean good2go = true;
                File mainFolder = new File(outputFolder, ClusterDaemon.MAIN_FOLDER);
                if (!mainFolder.exists()) {
                    if (!mainFolder.mkdir()) {
                        System.out.println("ERROR:Could not create folder " + mainFolder);
                        good2go = false;
                    }
                }
                
                if(good2go){
                    daemon = new ClusterDaemon(verbose, config_file, indexedFolder, mainFolder, outputFolder, numProcesses, (String)wiz.getProperty(DISTRIBUTION_TEST), numClusters);
                    properties = "MappedDatabase" + SimSearchUtilities.CONFIG_DELIM + indexedFolder + "\n"
                            + "Similarity_Metric" + SimSearchUtilities.CONFIG_DELIM + wiz.getProperty(DISTRIBUTION_TEST) +"\n"
                            + "num_bins" + SimSearchUtilities.CONFIG_DELIM + mappedDB_properties.get(MappingWizardAction.NUM_BINS_LABEL) + "\n"
                            + "max_measurement" + SimSearchUtilities.CONFIG_DELIM + mappedDB_properties.get(MappingWizardAction.MAX_MEASURE_LABEL) + "\n";
                    
                    wasCancelled = false;
                }  
            }else{
                
            }
        }
    }
    
    public ClusterDaemon getDaemon(){
        return daemon;
    }
    
    public boolean wasCancelled(){
        return wasCancelled;
    }
    
    void writeDatabaseSettings() {
        
        BufferedWriter writer = null;
        
        try{
            File cmd_file = new File(output_path,CLUSTER_PROPS);
            writer = new BufferedWriter(new FileWriter(cmd_file));
            writer.write(properties);
            writer.flush();
        }catch(IOException ex){
            Exceptions.printStackTrace(ex);
        }finally{
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

}
