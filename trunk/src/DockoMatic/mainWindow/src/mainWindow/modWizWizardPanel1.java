/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainWindow;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.pdb.webservices.PdbWebService;
import org.pdb.webservices.PdbWebServiceServiceLocator;

public class modWizWizardPanel1 implements WizardDescriptor.Panel, DocumentListener {

	/**
	 * The visual component that displays this panel. If you need to access the
	 * component from this class, just use getComponent().
	 */
	private Component component;
	private boolean isValid=false;

	// Get the visual component for the panel. In this template, the component
	// is kept separate. This can be more efficient: if the wizard is created
	// but never displayed, or not all panels are displayed, it is better to
	// create only those which really need to be visible.
	public Component getComponent() {
		if (component == null) {
			component = new modWizVisualPanel1();
			modWizVisualPanel1.getTextField().getDocument().addDocumentListener(this);
			modWizVisualPanel1.getOutField().getDocument().addDocumentListener(this);
		}
		return component;
	}

	public HelpCtx getHelp() {
		// Show no Help button for this panel:
		return HelpCtx.DEFAULT_HELP;
		// If you have context help:
		// return new HelpCtx(SampleWizardPanel1.class);
	}

    public void insertUpdate(DocumentEvent e) {
        change();
    }

    public void removeUpdate(DocumentEvent e) {
        change();
    }

    public void changedUpdate(DocumentEvent e) {
        change();
    }

private void change(){
       String seqArea = modWizVisualPanel1.getSeqField();
       String outDir = modWizVisualPanel1.getOutField().getText();
        boolean isValidInput = seqArea != null && seqArea.length() > 0 && outDir != null && outDir.length() > 0 ;
        if (isValidInput) {
            setValid(true);
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

	public boolean isValid() {
		// If it is always OK to press Next or Finish, then:
		return isValid;
	//	return true;
		// If it depends on some condition (form filled out...), then:
		// return someCondition();
		// and when this condition changes (last form field filled in...) then:
		// fireChangeEvent();
		// and uncomment the complicated stuff below.
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
	 

	// You can use a settings object to keep track of state. Normally the
	// settings object will be the WizardDescriptor, so you can use
	// WizardDescriptor.getProperty & putProperty to store information entered
	// by the user.
	public void readSettings(Object settings) {
	}

	public void storeSettings(Object settings) {
		//Get Sequence
		String seq = getSeq();
		((WizardDescriptor) settings).putProperty("seq", seq);
		((WizardDescriptor) settings).putProperty("outDir", ((modWizVisualPanel1)getComponent()).getOutDirField());
		((WizardDescriptor) settings).putProperty("auto", ((modWizVisualPanel1)getComponent()).isAuto());
	}

    private String getSeqFromFile(String fname){
	String seq = "";
	String str;
	try{
	    BufferedReader in = new BufferedReader(new FileReader(fname));
	    while(!((str = in.readLine()).contains("sequence"))){}
	    while((str = in.readLine())!= null)
		    seq += str;
	    in.close();
	}catch(IOException e){e.printStackTrace();}

	    return seq;
    }

    private String getSeq(){
	    String seq = ((modWizVisualPanel1)getComponent()).getSeqField();
	    if(seq.contains(".ali"))
                seq = getSeqFromFile(seq);

            seq = seq.replace("\n", "");

	    return seq;
    }

}
