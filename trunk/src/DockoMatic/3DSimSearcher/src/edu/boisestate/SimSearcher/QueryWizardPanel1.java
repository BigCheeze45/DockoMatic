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

public class QueryWizardPanel1 implements WizardDescriptor.ValidatingPanel<WizardDescriptor> {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private QueryVisualPanel1 component;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public QueryVisualPanel1 getComponent() {
        if (component == null) {
            component = new QueryVisualPanel1();
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx("help.key.here");
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
        wiz.putProperty(QueryWizardAction.SEARCH_FOLDER, getComponent().getSearchDir());
        wiz.putProperty(QueryWizardAction.QUERY_MOL_FILE, getComponent().getQueryFile()); 
        wiz.putProperty(QueryWizardAction.NUM_MOST_SIMILAR, Integer.parseInt(getComponent().getNumTopResults())); 
        wiz.putProperty(QueryWizardAction.DISTRIBUTION_TEST, getComponent().getDistTest());
    }

    @Override
    public void validate() throws WizardValidationException {
        String message = "";
        
        component = getComponent();
        String srchDir = component.getSearchDir();
        String qFile = component.getQueryFile();
        
        if(srchDir.isEmpty()){
            message += "You must specify a clustered folder to search.";
        }
        
        if(qFile.isEmpty()){
            message += "You must specify a molecule for which to search for similar molecules.";
        }
        
        try{
            Integer tmp = Integer.parseInt(component.getNumTopResults());
        }catch(NumberFormatException ex){
            message += "# top results to identify must be a positive integer.";
        }
        
        if(!message.isEmpty()){
            throw new WizardValidationException(null,message,null);
        }
    }

}
