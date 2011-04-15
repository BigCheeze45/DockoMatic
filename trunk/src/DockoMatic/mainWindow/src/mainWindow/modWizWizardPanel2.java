/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainWindow;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class modWizWizardPanel2 implements WizardDescriptor.Panel {

	/**
	 * The visual component that displays this panel. If you need to access the
	 * component from this class, just use getComponent().
	 */
	private Component component;

	// Get the visual component for the panel. In this template, the component
	// is kept separate. This can be more efficient: if the wizard is created
	// but never displayed, or not all panels are displayed, it is better to
	// create only those which really need to be visible.
	public Component getComponent() {
		if (component == null) {
			component = new modWizVisualPanel2();
		}
		return component;
	}

	public HelpCtx getHelp() {
		// Show no Help button for this panel:
		return HelpCtx.DEFAULT_HELP;
		// If you have context help:
		// return new HelpCtx(SampleWizardPanel1.class);
	}

	public boolean isValid() {
		// If it is always OK to press Next or Finish, then:
		return true;
		// If it depends on some condition (form filled out...), then:
		// return someCondition();
		// and when this condition changes (last form field filled in...) then:
		// fireChangeEvent();
		// and uncomment the complicated stuff below.
	}

	public final void addChangeListener(ChangeListener l) {
	}

	public final void removeChangeListener(ChangeListener l) {
	}
	/*
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
	 */

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
		System.out.println(((WizardDescriptor) settings).getProperty("seq"));
		String oDir = (String)((WizardDescriptor) settings).getProperty("outDir");
		parseResults(oDir+"/MyBlastResults.html");
		//System.out.println((NbPreferences.forModule(modWizWizardPanel1.class).get("seq", "")));

	}

	public void storeSettings(Object settings) {
		((WizardDescriptor) settings).putProperty("Template", ((modWizVisualPanel2)getComponent()).getTemplt());
	}

//	private void newJob(){
//	if(outDirField.getText().isEmpty()) return;
//
//	String[] items = new String[3];
//    //System.out.println("*****************NewJobButtonPressed\n");
////	    ModInfo mofo = new ModInfo(outDirField.getText(), seqArea.getText(), tmpltField.getText());
//    TopComponent tc = WindowManager.getDefault().findTopComponent("openerTopComponent");
//    ArrayList<String> alist = new ArrayList<String>();
//	    alist.add( outDirField.getText());
//	    alist.add( seqArea.getText());
//	    alist.add( tmpltField.getText());
//	    //items[0] = outDirField.getText();
//	    //items[1] = seqArea.getText();
//	    //items[2] = tmpltField.getText();
//    dynamicContent.add(alist);
//    //dynamicContent.set(Collections.singleton(alist), null);
//    /*if(tc != null){
//	    tc.open();
//	    tc.requestActive();
//    }*/
//    this.dynamicContent.remove(alist);
//	    //outDirField.setText("");
//	    //seqArea.setText("");
//	    //tmpltField.setText("");
//
//	//TopComponent opener = WindowManager.getDefault().findTopComponent("openerTopComponent");
//	//dynamicContent.add(outDirField.getText(), seqArea.getText(), tmpltField.getText());
//	//jobInfo = myLookup.lookup(new Lookup.Template(String.class));
//	//jobInfo.allItems();
//	//jobInfo.addLookupListener((LookupListener) opener);
//
//	//dynamicContent.add(tmp);
//        //jobInfo.allItems();
//    //System.out.println("*********************dynCont added\n");
//    }
}

