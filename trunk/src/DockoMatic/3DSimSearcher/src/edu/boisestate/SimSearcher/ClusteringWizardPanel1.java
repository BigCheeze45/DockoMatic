/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.boisestate.SimSearcher;

import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;
import org.openide.util.HelpCtx;

public class ClusteringWizardPanel1 implements WizardDescriptor.ValidatingPanel<WizardDescriptor> {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private ClusteringVisualPanel1 component;
    private String swmCmdOptions;
    private String clusterSizes;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public ClusteringVisualPanel1 getComponent() {
        if (component == null) {
            component = new ClusteringVisualPanel1();
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
        // use wiz.getProperty to retrieve previous panel state
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        wiz.putProperty(ClusteringWizardAction.OUTPUT_DIR,getComponent().getOutputDir());
        wiz.putProperty(ClusteringWizardAction.MAPPED_DIR,getComponent().getMapDir());
        wiz.putProperty(ClusteringWizardAction.SWARM_OPTS,swmCmdOptions);
        wiz.putProperty(ClusteringWizardAction.CLUSTER_SIZES,clusterSizes);
        wiz.putProperty(ClusteringWizardAction.NUM_SWARM_CMDS,Integer.parseInt(getComponent().getNumNodes()));
        wiz.putProperty(ClusteringWizardAction.DISTRIBUTION_TEST,getComponent().getDistTest());
        wiz.putProperty(ClusteringWizardAction.VERBOSE,getComponent().getVerbosity());
    }

    @Override
    public void validate() throws WizardValidationException {
        String message = "";
        
        component = getComponent();
        String outDir = component.getOutputDir();
        String mapDir = component.getMapDir();
        
        if(mapDir.isEmpty()){
            message += "You must specify the mapped folder.";
        }
        
        if(outDir.isEmpty()){
            message += "You must specify the output folder.";
        }
        
        try{
            
            Integer tmp = Integer.parseInt(component.getClusters(1));
            if(tmp < 2){
                message += "There must be at least 2 Level 1 clusters";
            }
            clusterSizes = tmp + "";
            for(int i = 2; i < 4; i++){
                tmp = Integer.parseInt(component.getClusters(i));
                if(tmp > 0){
                    clusterSizes += "," + tmp;
                }else{
                    break;  //there can't be subclusters without a parent cluster
                }
            }
            tmp = Integer.parseInt(component.getNumNodes());
            Integer jobsPerNode = Integer.parseInt(component.getJobsPerNode());
            swmCmdOptions = " -n " + jobsPerNode + " " + component.getSwarmCmdOpts();
        }catch(NumberFormatException ex){
            message += "# Nodes, # clusters at each level, and Swarm Jobs Per Node must be positive integers.";
        }
        
        if(!message.isEmpty()){
            throw new WizardValidationException(null,message,null);
        }
    }

}
