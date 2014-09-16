/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.boisestate.SimSearcher;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JComponent;
import map.FunctionalGroup;
import map.Mapper;
import map.Molecule;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
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
    private int numMostSimilar;
    
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
            numMostSimilar = (Integer) wiz.getProperty(NUM_MOST_SIMILAR);
            
            boolean useFgroups = distTest.equals(SimSearchUtilities.FUNCTIONAL_GROUPS);

            HashMap<String,String> properties = SimSearchUtilities.getProperties(new File(search_folder,ClusteringWizardAction.CLUSTER_PROPS));
            int numBins = Integer.parseInt(properties.get(MappingWizardAction.NUM_BINS_LABEL));
            double stepSize = Double.parseDouble(properties.get(MappingWizardAction.MAX_MEASURE_LABEL)) / numBins;
            
            ArrayList<FunctionalGroup> fgroups = null;
            if(useFgroups){
                fgroups = Mapper.generateFgroups((String)wiz.getProperty(FGROUP_PATH));
            }

            Molecule queryMol = QueryDriver.createQueryMol(sdf_path, numBins, stepSize, fgroups);

            if (queryMol != null) {            
                query_driver = new QueryDriver(queryMol, useFgroups, distTest);
                was_cancelled = false;
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

    int getNumMostSimilar() {
        return numMostSimilar;
    }

}
