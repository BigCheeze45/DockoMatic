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

public class mutantScreeningWizardPanel3 implements WizardDescriptor.ValidatingPanel<WizardDescriptor>, WizardDescriptor.FinishablePanel<WizardDescriptor> {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private mutantScreeningVisualPanel3 component;
    private int num_processors;
    private int topX;
    private double slider_fract;
    
    private final double MAX_XOVER_CYCLES_RATIO = .02;  //screen at most this percent before terminating
    private final int MAX_MUTATE_CYCLES = 6;
    private final double MAX_MUTATE_RATE = .08;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public mutantScreeningVisualPanel3 getComponent() {
        if (component == null) {
            component = new mutantScreeningVisualPanel3();
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
        // If it is always OK to press Next or Finish, then:
        return true;
        // If it depends on some condition (form filled out...) and
        // this condition changes (last form field filled in...) then
        // use ChangeSupport to implement add/removeChangeListener below.
        // WizardDescriptor.ERROR/WARNING/INFORMATION_MESSAGE will also be useful.
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
        
        wiz.putProperty(mutantScreeningWizardAction.CLUSTER_SIZE,num_processors);
        wiz.putProperty(mutantScreeningWizardAction.TOPX,topX);
        wiz.putProperty(mutantScreeningWizardAction.EXHAUSTIVE_DOCKING, false);
        
        
        String[] constraint_set = (String []) wiz.getProperty(mutantScreeningWizardAction.MUTATION_CONSTRAINTS);
        
        int library_size = 1;
        for(String elem : constraint_set){
            library_size *= elem.length();
        } 
        
        //break the calculation out for clarity
        double percent_to_dock = slider_fract*MAX_XOVER_CYCLES_RATIO;
        double num_to_dock = percent_to_dock * library_size;
        
        int num_crossover_cycles = (int) (num_to_dock / num_processors) + 2;
        int num_mutation_cycles = Math.max(2,(int)(MAX_MUTATE_CYCLES*slider_fract));
        int num_crossover_points = constraint_set.length / 2;
        double mutation_rate = Math.max(.02, MAX_MUTATE_RATE * slider_fract);
        double elitism = Math.min(.5, ((double)(topX) / num_processors));
        
        wiz.putProperty(mutantScreeningWizardAction.NUM_CROSSOVER_CYCLES, num_crossover_cycles);
        wiz.putProperty(mutantScreeningWizardAction.NUM_MUTATION_CYCLES, num_mutation_cycles);
        wiz.putProperty(mutantScreeningWizardAction.NUM_CROSSOVER_POINTS, num_crossover_points);
        wiz.putProperty(mutantScreeningWizardAction.MUTATION_RATE, mutation_rate);
        wiz.putProperty(mutantScreeningWizardAction.ELITISM, elitism);
        wiz.putProperty(mutantScreeningWizardAction.TOP_POSES,1);
        
    }

    @Override
    public void validate() throws WizardValidationException {
        boolean isValid = false;
        try{
            num_processors = Integer.parseInt(getComponent().getNumProcessors());
            topX = Integer.parseInt(getComponent().getTopX());
            slider_fract = getComponent().getSliderFraction();
            isValid = (topX > 0) && (num_processors > 0);
            
        }catch(NumberFormatException ex){}
        
        if(!isValid){
            throw new WizardValidationException(null,"# processors and result set size must be integers and greater than 0",null);
        }
    }

    @Override
    public boolean isFinishPanel() {
        return true;
    }

}
