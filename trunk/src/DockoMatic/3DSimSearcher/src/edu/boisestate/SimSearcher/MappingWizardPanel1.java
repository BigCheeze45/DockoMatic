/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.boisestate.SimSearcher;

import java.io.File;
import java.io.FilenameFilter;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;
import org.openide.util.HelpCtx;

public class MappingWizardPanel1 implements WizardDescriptor.ValidatingPanel<WizardDescriptor> {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private MappingVisualPanel1 component;
    private String swmCmdOptions;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public MappingVisualPanel1 getComponent() {
        if (component == null) {
            component = new MappingVisualPanel1();
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
        wiz.putProperty(MappingWizardAction.DATABASE_FOLDER, getComponent().getDatabaseDir());
        wiz.putProperty(MappingWizardAction.OUTPUT_FOLDER, getComponent().getOutputDir());
        wiz.putProperty(MappingWizardAction.NUM_SWARM_CMDS, Integer.parseInt(getComponent().getNumNodes()));
        wiz.putProperty(MappingWizardAction.SWARM_CMD_OPTIONS, getComponent().getSwarmOpts());
        wiz.putProperty(MappingWizardAction.USE_SHAPE_DIST, getComponent().getUseShapeDist());
        wiz.putProperty(MappingWizardAction.NUM_BINS, Integer.parseInt(getComponent().getNumBins()));
        wiz.putProperty(MappingWizardAction.UPPER_BOUND, Integer.parseInt(getComponent().getUpperBoundMeasure()));
        wiz.putProperty(MappingWizardAction.FGROUP_FILE, getComponent().getFgroupFile());
    }

    @Override
    public void validate() throws WizardValidationException {
        String message = "";
        
        component = getComponent();
        String outDir = component.getOutputDir();
        String dbDir = component.getDatabaseDir();
        
        if(dbDir.isEmpty()){
            message += "You must specify the database folder!\n";
        }else if(!containsSDFs(dbDir)){
            message += "The directory " + dbDir + " is empty!  It should contain at least one SDF file!\n";
        }
        if(outDir.isEmpty()){
            message += "You must specify where to place the output folder!\n";
        }
        
        try{
            Integer tmp = Integer.parseInt(component.getNumNodes());
        }catch(NumberFormatException ex){
            message += "# Processes must be positive integers!\n";
        }
        
        if(component.getUseShapeDist()){
            try{
                Integer tmp = Integer.parseInt(component.getNumBins());
                tmp = Integer.parseInt(component.getUpperBoundMeasure());
            }catch(NumberFormatException ex){
                message += "# Bins and Upper Bound must be positive integers!\n";
            }        
        }
        
        if(!(component.getUseShapeDist() || component.getUseFgroups())){
            message += "You must chose to use functional groups, shape distributions, or both.";
        }
        
        if(!message.isEmpty()){
            throw new WizardValidationException(null,message,null);
        }
    }

    private boolean containsSDFs(String dbDir) {
        File dir = new File(dbDir);
        String[] list = dir.list(new FilenameFilter(){
            
            @Override
            public boolean accept(File dir, String name) {
                boolean rval = false;
                if(name.endsWith(".sdf")){
                    rval = true;
                }
                return rval;
            }
            
        });
        
        return list.length  > 0;
    }

}
