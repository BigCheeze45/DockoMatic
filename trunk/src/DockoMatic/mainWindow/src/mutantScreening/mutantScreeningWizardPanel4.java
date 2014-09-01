/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutantScreening;

import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;
import org.openide.util.HelpCtx;

public class mutantScreeningWizardPanel4 implements WizardDescriptor.ValidatingPanel<WizardDescriptor> {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private mutantScreeningVisualPanel4 component;
    private Object[] param_values;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public mutantScreeningVisualPanel4 getComponent() {
        if (component == null) {
            component = new mutantScreeningVisualPanel4();
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

    //TODO string[] will not work, need to fix it
    @Override
    public void readSettings(WizardDescriptor wiz) {
        param_values = new Object[6];
        param_values[0] = wiz.getProperty(mutantScreeningWizardAction.NUM_CROSSOVER_CYCLES);
        param_values[1] = wiz.getProperty(mutantScreeningWizardAction.NUM_MUTATION_CYCLES);
        param_values[2] = wiz.getProperty(mutantScreeningWizardAction.NUM_CROSSOVER_POINTS);
        param_values[3] = wiz.getProperty(mutantScreeningWizardAction.MUTATION_RATE);
        param_values[4] = wiz.getProperty(mutantScreeningWizardAction.ELITISM);
        param_values[5] = wiz.getProperty(mutantScreeningWizardAction.TOP_POSES);
        
        getComponent().setParamValues(param_values);
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        wiz.putProperty(mutantScreeningWizardAction.NUM_CROSSOVER_CYCLES, param_values[0]);
        wiz.putProperty(mutantScreeningWizardAction.NUM_MUTATION_CYCLES, param_values[1]);
        wiz.putProperty(mutantScreeningWizardAction.NUM_CROSSOVER_POINTS, param_values[2]);
        wiz.putProperty(mutantScreeningWizardAction.MUTATION_RATE, param_values[3]);
        wiz.putProperty(mutantScreeningWizardAction.ELITISM, param_values[4]);
        wiz.putProperty(mutantScreeningWizardAction.TOP_POSES,param_values[5]);
    }

    @Override
    public void validate() throws WizardValidationException {
        String err_message = validateFields();
        if(err_message.isEmpty()){
            
        }else{
            throw new WizardValidationException(null,err_message,null);
        }
    }
    
    private String validateFields(){
        final Double MAX_MUTATE = .5;  //absurdly high value max values
        final Double MAX_ELITISM = .75;  
      
        String rval = "";
        String[] field_values = getComponent().getTextFieldValues();
        int xoverCycles,mutateCycles,xoverPoints,smoothNum;
        double mutation_rate, elitism;
        
        try{
            xoverCycles = Math.max(0, Integer.parseInt(field_values[0]));
            mutateCycles = Math.max(0,Integer.parseInt(field_values[1]));
            xoverPoints = Math.max(1, Integer.parseInt(field_values[2]));
            mutation_rate = Math.max(0.0,Double.parseDouble(field_values[3]));
            mutation_rate = Math.min(mutation_rate,MAX_MUTATE);   //
            elitism = Math.max(0,Double.parseDouble(field_values[4]));
            elitism = Math.min(elitism, MAX_ELITISM);
            smoothNum = Math.max(1,Integer.parseInt(field_values[5]));
            
            //if we made it to here then all parameters are valid, save in class variable
            param_values[0] = xoverCycles;
            param_values[1] = mutateCycles;
            param_values[2] = xoverPoints;
            param_values[3] = mutation_rate;
            param_values[4] = elitism;
            param_values[5] = smoothNum;
   
        }catch(NumberFormatException ex){
            rval = "All fields must contain non-negative numeric values";
        }

        return rval;
    }

}
