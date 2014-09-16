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
import java.util.List;
import javax.swing.JComponent;
import map.MapDriver;
import map.MappedMolecule;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.util.Exceptions;

// An example action demonstrating how the wizard could be called from within
// your code. You can move the code below wherever you need, or register an action:
// @ActionID(category="...", id="edu.boisestate.SimSearcher.MappingWizardAction")
// @ActionRegistration(displayName="Open Mapping Wizard")
// @ActionReference(path="Menu/Tools", position=...)
public final class MappingWizardAction implements ActionListener {
    
    protected final static String OUTPUT_FOLDER = "var1";
    protected final static String DATABASE_FOLDER = "var2";
    protected final static String NUM_BINS = "var3";
    protected final static String NUM_SWARM_CMDS = "var4";
    protected final static String SWARM_CMD_OPTIONS = "var5";
    protected final static String USE_SHAPE_DIST = "var6";
    protected final static String FGROUP_FILE = "var7";
    protected final static String UPPER_BOUND = "var8";
    
    protected final static String NUM_BINS_LABEL = "num_bins";
    protected final static String MAX_MEASURE_LABEL = "max_measurement";
    
    protected final static String MAPPED_SETTINGS = ".map_properties";
    
    private boolean wasCancelled;
    private File databaseDir;
    private String outputDir;
    private String mapped_properties;
    private String paramTemplate;
    private String swarmCmdOpts;
    private int numNodes;

    @Override
    public void actionPerformed(ActionEvent e) {
        List<WizardDescriptor.Panel<WizardDescriptor>> panels = new ArrayList<WizardDescriptor.Panel<WizardDescriptor>>();
        panels.add(new MappingWizardPanel1());
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
        wiz.setTitle("Molecular Database Mapping");
        wasCancelled = true;
        
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            wasCancelled = false;
            
            String FGroupPath = (String) wiz.getProperty(FGROUP_FILE);
            databaseDir = new File((String) wiz.getProperty(DATABASE_FOLDER));
            outputDir = (String) wiz.getProperty(OUTPUT_FOLDER);
            numNodes = (Integer) wiz.getProperty(NUM_SWARM_CMDS);
            
            swarmCmdOpts = "-n " + numNodes + " " + (String) wiz.getProperty(SWARM_CMD_OPTIONS);
            
            String mode = "";
            if((Boolean)wiz.getProperty(USE_SHAPE_DIST)){
                mode += MappedMolecule.USE_HISTOGRAM_INDICATOR;
            }
            if(FGroupPath != null){
                mode += MappedMolecule.USE_FGROUP_INDICATOR;
            }
            
            String delim = " ";
            
            paramTemplate = databaseDir + delim + outputDir + delim
                    + mode + delim + wiz.getProperty(NUM_BINS) + delim + wiz.getProperty(UPPER_BOUND) + delim
                    + (FGroupPath == null ? "NONE" : FGroupPath) + delim;
            
            //store the arguments used to map a database with the mapped files
            mapped_properties =
                      NUM_BINS_LABEL + SimSearchUtilities.CONFIG_DELIM + wiz.getProperty(NUM_BINS) + "\n"
                    + MAX_MEASURE_LABEL + SimSearchUtilities.CONFIG_DELIM + wiz.getProperty(UPPER_BOUND) + "\n"
                    + "functional_group_file=" + (FGroupPath == null ? "NONE" : FGroupPath) + "\n"
                    + "SDF_Database_Directory=" + databaseDir + "\n";
            
        }
    }
    
    boolean wasCancelled(){
        return wasCancelled;
    }
    
    String getSwarmCmdOptions(){
        return swarmCmdOpts;
    }

    void generateSwarmCmdFile(String Dir, String file_name) {
        String[] partitions = SimSearchUtilities.generatePartitions(databaseDir,".sdf",numNodes);
        String class_path = MapDriver.getClassPath();
        String main_class = "map.MapDriver";
        String preamble = SimSearchUtilities.getPreamble(class_path, main_class);
        BufferedWriter writer = null;
        try {
            File cmd_file = new File(Dir,file_name);
            writer = new BufferedWriter(new FileWriter(cmd_file));
            
            for(String partition : partitions){
                if(!partition.isEmpty()){
                    writer.write(preamble + paramTemplate+partition +"\n");
                }
            }
            
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    /**
     * 
     * @return an array of string such that each element is a comma delimited list of file names 
     */

    void writeDatabaseSettings() {
        
        BufferedWriter writer = null;
        
        try{
            File cmd_file = new File(outputDir,MAPPED_SETTINGS);
            writer = new BufferedWriter(new FileWriter(cmd_file));
            writer.write(mapped_properties);
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
