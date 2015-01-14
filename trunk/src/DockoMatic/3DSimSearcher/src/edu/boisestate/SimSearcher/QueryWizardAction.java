/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.boisestate.SimSearcher;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import map.FunctionalGroup;
import map.Mapper;
import map.Molecule;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.util.Exceptions;
import query.QueryDriver;

// An example action demonstrating how the wizard could be called from within
// your code. You can move the code below wherever you need, or register an action:
// @ActionID(category="...", id="edu.boisestate.SimSearcher.QueryWizardAction")
// @ActionRegistration(displayName="Open Query Wizard")
// @ActionReference(path="Menu/Tools", position=...)
public final class QueryWizardAction implements ActionListener {
    
    private QueryDriver query_driver;
    private String search_folder;
    private boolean was_cancelled;
    private List<Molecule> molecule_list;
    
    static final String SEARCH_FOLDER = "var1";
    static final String NUM_MOST_SIMILAR = "var2";
    static final String QUERY_MOL_FILE = "var3";
    static final String DISTRIBUTION_TEST = "var4";
    static final String FGROUP_PATH = "var5";
    

    @Override
    public void actionPerformed(ActionEvent e) {
        List<WizardDescriptor.Panel<WizardDescriptor>> panels = new ArrayList<WizardDescriptor.Panel<WizardDescriptor>>();
        panels.add(new QueryWizardPanel1());
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
        wiz.setTitle("Query Wizard");
        was_cancelled = true;
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            
            search_folder = (String) wiz.getProperty(SEARCH_FOLDER); 
            String sdf_path = (String) wiz.getProperty(QUERY_MOL_FILE);
            String distTest = (String) wiz.getProperty(DISTRIBUTION_TEST);
            int numMostSimilar = (Integer) wiz.getProperty(NUM_MOST_SIMILAR);
            
            boolean useFgroups = distTest.equals(SimSearchUtilities.FUNCTIONAL_GROUPS);

            HashMap<String,String> properties = SimSearchUtilities.getProperties(new File(search_folder,ClusteringWizardAction.CLUSTER_PROPS));
            int numBins = 0;
            double stepSize = 0;
            
            molecule_list = new ArrayList<Molecule> ();
            
            if(sdf_path.endsWith(Mapper.MAPPED_SUFFIX)){
                //TODO allow user to query with a mapped molecule
                throw new UnsupportedOperationException("Searching with a mapped file is not yet supported.  Is this stupid? yes.");
            }else{
                if(properties == null){  //we are not querying a clustered folder, so we don't know how to size the query molecule's histogram
                    SimSearchTopComponent.outputTxtArea.append("You are attempting to perform a search against a database that has not been clustered.\n"
                            + "To do this, you need to specify the values to be used in the construction of the query moleculesfor histogram generation.\n"
                            + "More specifically, you must determine and enter the number of bins and upper bound that were used to create the database which you want to query.\n"
                            + "These values are likely in a hidden file in the database's directory or in the outer most directory of a cluster.\n"
                            + "Once you have located them, enter the two values in the dialog box.  Separate them with a comma.");
                    Object result = JOptionPane.showInputDialog(null, "Enter numbers separated by a ,", "Histogram Generation Parameters", JOptionPane.QUESTION_MESSAGE);
                    if(result != null){
                        String[] values = ((String) result).split(",");
                        if(values.length == 2){
                            try{
                                numBins = Integer.parseInt(values[0]);
                                stepSize = Double.parseDouble(values[1]) / Math.max(1, numBins);
                            }catch(NumberFormatException ex){
                                numBins = 0;
                            }
                        }
                    }
                }else{
                    numBins = Integer.parseInt(properties.get(MappingWizardAction.NUM_BINS_LABEL));
                    stepSize = Double.parseDouble(properties.get(MappingWizardAction.MAX_MEASURE_LABEL)) / numBins;
                }

                if(numBins > 0){
                    ArrayList<FunctionalGroup> fgroups = null;
                    if(useFgroups){
                        fgroups = Mapper.generateFgroups((String)wiz.getProperty(FGROUP_PATH));
                    }
                    
                    if(sdf_path.endsWith(".sdf")){
                        molecule_list.add(QueryDriver.createQueryMol(sdf_path, numBins, stepSize, fgroups));
                    }else{
                        fromList(sdf_path, numBins, stepSize, fgroups);
                    }
                }
            }
       
            query_driver = new QueryDriver(useFgroups, distTest, numMostSimilar);
            was_cancelled = false;
            
        }
    }
    
    private void fromList(String list_path, int numBins, double stepSize, ArrayList<FunctionalGroup> fgroups){
        BufferedReader reader = null;

        File list_file = new File(list_path);

        if (list_file.exists()) {
            try {
                reader = new BufferedReader(new FileReader(list_file));
                String sdf_path;
                while((sdf_path = reader.readLine()) != null){
                    molecule_list.add(QueryDriver.createQueryMol(sdf_path, numBins, stepSize, fgroups));
                }
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }
    
    public QueryDriver getQueryDriver(){
        return query_driver;
    }
    
    public String getSearchFolder(){
        return search_folder;
    }
    
    public boolean wasCancelled(){
        return was_cancelled;
    }
    
    Molecule[] getQueryMols(){
        return molecule_list.toArray(new Molecule[molecule_list.size()]);
    }

}
