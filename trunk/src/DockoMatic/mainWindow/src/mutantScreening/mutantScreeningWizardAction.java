/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutantScreening;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JComponent;
import mainWindow.messageWindowTopComponent;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;

// An example action demonstrating how the wizard could be called from within
// your code. You can move the code below wherever you need, or register an action:
// @ActionID(category="...", id="mutantScreening.mutantScreeningWizardAction")
// @ActionRegistration(displayName="Open mutantScreening Wizard")
// @ActionReference(path="Menu/Tools", position=...)
public final class mutantScreeningWizardAction implements ActionListener {
    
    public final static String PEPTIDE_SEQUENCE = "pep_sequence";
    public final static String MUTABLE_ACIDS = "mutable_acids";
    public final static String MUTATION_CONSTRAINTS = "constraints";
    public final static String EXHAUSTIVE_DOCKING = "exhaustive";
    public final static String NUM_CROSSOVER_CYCLES = "num_crossover_cycles";
    public final static String NUM_MUTATION_CYCLES = "num_mutation_cycles";
    public final static String NUM_CROSSOVER_POINTS = "num_crossover_points";
    public final static String MUTATION_RATE = "mutation_rate";
    public final static String ELITISM = "elitism";
    public final static String TOP_POSES = "smooth_num";
    public final static String TOPX = "topX";
    public final static String CLUSTER_SIZE = "cluster_size";
    
    public final static String DELIM = ",";
    
    private final ParameterSet parameters = new ParameterSet();
    
    
    public ParameterSet initialize(ArrayList<String> peptide_sequence){
        ParameterSet rval = null;
        
        List<WizardDescriptor.Panel<WizardDescriptor>> panels = new ArrayList<WizardDescriptor.Panel<WizardDescriptor>>();
        panels.add(new mutantScreeningWizardPanel1());
        panels.add(new mutantScreeningWizardPanel2());
        panels.add(new mutantScreeningWizardPanel3());
        panels.add(new mutantScreeningWizardPanel4());
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
        wiz.setTitle("Peptide Mutant Screening Wizard");
        
        wiz.putProperty(PEPTIDE_SEQUENCE, peptide_sequence);
        wiz.putProperty(EXHAUSTIVE_DOCKING, true);
        
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {

            parameters.setSitesAndSeq((String)wiz.getProperty(MUTABLE_ACIDS));
            parameters.constraint_set = ((String[]) wiz.getProperty(MUTATION_CONSTRAINTS));
            parameters.exhaustiveScreen = (Boolean)wiz.getProperty(EXHAUSTIVE_DOCKING);
            if(! parameters.isExhaustiveScreen()){
                setGAparameters(wiz);
            }
            rval = parameters;
        }
        return rval;
    }
    
    private void setGAparameters(WizardDescriptor wiz){
        
        parameters.topX = (Integer) wiz.getProperty(mutantScreeningWizardAction.TOPX);
        parameters.cluster_size = (Integer) wiz.getProperty(mutantScreeningWizardAction.CLUSTER_SIZE);
        parameters.num_cycles = (Integer) wiz.getProperty(mutantScreeningWizardAction.NUM_CROSSOVER_CYCLES);
        parameters.num_crossover_points = (Integer) wiz.getProperty(mutantScreeningWizardAction.NUM_CROSSOVER_POINTS);
        parameters.mutation_rate = (Double) wiz.getProperty(mutantScreeningWizardAction.MUTATION_RATE);
        parameters.elitism = (Double) wiz.getProperty(mutantScreeningWizardAction.ELITISM);
        parameters.smooth_num = (Integer) wiz.getProperty(mutantScreeningWizardAction.TOP_POSES);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    public static ArrayList<String> getFullSequence(String fileName) {
        ArrayList<String> seqList = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            HashMap<String, String> acidConv = new HashMap<String, String>();

            //DO NOT CHANGE THESE VALUES
            acidConv.put("ALA", "A");
            acidConv.put("ARG", "R");
            acidConv.put("ASN", "N");
            acidConv.put("ASP", "D");
            acidConv.put("CYS", "C");
            acidConv.put("GLU", "E");
            acidConv.put("GLN", "Q");
            acidConv.put("GLY", "G");
            acidConv.put("HIS", "H");
            acidConv.put("ILE", "I");
            acidConv.put("LEU", "L");
            acidConv.put("LYS", "K");
            acidConv.put("MET", "M");
            acidConv.put("PHE", "F");
            acidConv.put("PRO", "P");
            acidConv.put("SER", "S");
            acidConv.put("THR", "T");
            acidConv.put("TRP", "W");
            acidConv.put("TYR", "Y");
            acidConv.put("VAL", "V");


            String lineSEQRES = "", temp;
            boolean foundFirst = false;

            while ((temp = reader.readLine()) != null) {
                if (temp.startsWith("SEQRES")) {
                    lineSEQRES += temp + " ";
                    foundFirst = true;
                } else if (foundFirst){ //SEQRES lines will be consecutive, so we don't need to finish scanning the file
                    break;
                }
            }

            reader.close();

            if (foundFirst) {
                String[] acids = lineSEQRES.split("\\s");
                int site = 1;
                
                for(int index = 0; index < acids.length; index++){
                    String acid = acids[index].toUpperCase();
                    if (acidConv.containsKey(acid)) {
                        seqList.add(acidConv.get(acid) + (site++));
                    }
                }
            }

        } catch (FileNotFoundException e) {
            messageWindowTopComponent.appendText(e.getLocalizedMessage());
        } catch(IOException ex){
           messageWindowTopComponent.appendText(ex.getLocalizedMessage());
        }
        return seqList;
    }

}
