/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainWindow;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class modWizWizardPanel2 implements WizardDescriptor.Panel, ListSelectionListener {

	/**
	 * The visual component that displays this panel. If you need to access the
	 * component from this class, just use getComponent().
	 */
	private Component component;
	private boolean isValid = false;

	// Get the visual component for the panel. In this template, the component
	// is kept separate. This can be more efficient: if the wizard is created
	// but never displayed, or not all panels are displayed, it is better to
	// create only those which really need to be visible.
	public Component getComponent() {
		if (component == null) {
			component = new modWizVisualPanel2();
		        modWizVisualPanel2.getTable().getSelectionModel().addListSelectionListener(this);
		        modWizVisualPanel2.genModelMessage.setVisible(false);
		}
		return component;
	}

	public String getModelPath(){
		return "";
	}

	public HelpCtx getHelp() {
		// Show no Help button for this panel:
		return HelpCtx.DEFAULT_HELP;
		// If you have context help:
		// return new HelpCtx(SampleWizardPanel1.class);
	}

	public boolean isValid() {
		// If it is always OK to press Next or Finish, then:
		//return true;
		return isValid;
		// If it depends on some condition (form filled out...), then:
		// return someCondition();
		// and when this condition changes (last form field filled in...) then:
		// fireChangeEvent();
		// and uncomment the complicated stuff below.
	}


	public void valueChanged(ListSelectionEvent e){
		change();
	}

private void change(){
       int row = modWizVisualPanel2.getTable().getSelectedRow();
        boolean isValidInput = row >= 0 ;
        if (isValidInput) {
            setValid(true);
	    //modWizVisualPanel2.genModelMessage.setVisible(true);
        } else {
            setValid(false);
        }
}

private void setValid(boolean val) {
        if (isValid != val) {
            isValid = val;
            fireChangeEvent();
        }
    }


//	public final void addChangeListener(ChangeListener l) {
//	}
//
//	public final void removeChangeListener(ChangeListener l) {
//	}
	
	private final Set<ChangeListener> listeners = new HashSet<ChangeListener>(1); // or can use ChangeSupport in NB 6.0
	public final void addChangeListener(ChangeListener l) {
	synchronized (listeners) {
	listeners.add(l);
	}
	}
	public final void removeChangeListener(ChangeListener l) {
	synchronized (listeners) {
	listeners.remove(l);
	}
	}
	protected final void fireChangeEvent() {
	Iterator<ChangeListener> it;
	synchronized (listeners) {
	it = new HashSet<ChangeListener>(listeners).iterator();
	}
	ChangeEvent ev = new ChangeEvent(this);
	while (it.hasNext()) {
	it.next().stateChanged(ev);
	}
	}
	 

    private void parseResults(String fname)
    {
	    String str;
	    String pdbid;
	    String evalue;
	    DefaultTableModel model = (DefaultTableModel)((modWizVisualPanel2)getComponent()).getTableModel();

	    try{
	        BufferedReader in = new BufferedReader(new FileReader(fname));
	        while(!((str = in.readLine()).contains("|pdbid"))){}

		do{
	            pdbid = str.substring(0, str.indexOf("|"));
	            evalue = str.substring(str.indexOf("/a>")+3, str.length());
		    model.addRow(new Object[]{pdbid, evalue});
		} while (!((str = in.readLine()).contains("/PRE")));
		in.close();
	    } catch (IOException e) {
		    e.printStackTrace();
	    }

    }

	// You can use a settings object to keep track of state. Normally the
	// settings object will be the WizardDescriptor, so you can use
	// WizardDescriptor.getProperty & putProperty to store information entered
	// by the user.
	public void readSettings(Object settings) {
		//System.out.println(((WizardDescriptor) settings).getProperty("seq"));
		String oDir = (String)((WizardDescriptor) settings).getProperty("outDir");
		parseResults(oDir+"/MyBlastResults.html");
		//System.out.println((NbPreferences.forModule(modWizWizardPanel1.class).get("seq", "")));

	}

	public void storeSettings(Object settings) {
		((WizardDescriptor) settings).putProperty("Template", ((modWizVisualPanel2)getComponent()).getTemplt());
		((WizardDescriptor) settings).putProperty("swarm", ((modWizVisualPanel2)getComponent()).isSwarm());
	}

}

