/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutantScreening;

import java.util.ArrayList;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;
import org.openide.util.HelpCtx;

public class mutantScreeningWizardPanel1 implements WizardDescriptor.ValidatingPanel<WizardDescriptor> {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private mutantScreeningVisualPanel1 component;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public mutantScreeningVisualPanel1 getComponent() {
        if (component == null) {
            component = new mutantScreeningVisualPanel1();
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
        ArrayList<String> peptide_sequence = (ArrayList<String>) wiz.getProperty(mutantScreeningWizardAction.PEPTIDE_SEQUENCE);
        getComponent().setSequence(peptide_sequence);
    }

    /*
    This method is 
    */
    @Override
    public void storeSettings(WizardDescriptor wiz) {
        wiz.putProperty(mutantScreeningWizardAction.MUTABLE_ACIDS, getComponent().getMutableAcids());
    }

    @Override
    public void validate() throws WizardValidationException {
        String mutable_acids = getComponent().getMutableAcids();
        
        if(mutable_acids.isEmpty()){
            throw new WizardValidationException(null,"There must be at least one mutable acid",null);
        }
    }

}
