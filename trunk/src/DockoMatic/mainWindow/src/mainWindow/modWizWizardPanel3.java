/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mainWindow;

import java.awt.Component;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

/**
 *
 * @author wbullock
 */
public class modWizWizardPanel3  implements WizardDescriptor.Panel {
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
			component = new modWizVisualPanel3();
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

	// You can use a settings object to keep track of state. Normally the
	// settings object will be the WizardDescriptor, so you can use
	// WizardDescriptor.getProperty & putProperty to store information entered
	// by the user.
	public void readSettings(Object settings) {
		String templt = (String)((WizardDescriptor) settings).getProperty("Template");
		String oDir = (String)((WizardDescriptor) settings).getProperty("outDir");
		getPdbTmpltFile(oDir, templt);


	}

	public void storeSettings(Object settings) {
	}


    private void getPdbTmpltFile(String odir, String pdb){

//	    String pdb = tmpltField.getText();
	    String file = pdb.substring(0, pdb.indexOf(":"));
	    String outFilePath=odir+File.separator+file+".pdb";

	    try{
	        URL url = new URL("http://www.pdb.org/pdb/files/"+file+".pdb");
	        URLConnection urlc = url.openConnection();
	        BufferedInputStream in = new BufferedInputStream(urlc.getInputStream());
	        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFilePath));

	        int i;
	        while((i=in.read()) != -1)
	        {
		    out.write(i);
	        }
	        in.close();
	        out.close();

	    }catch(IOException e){e.printStackTrace();}
	    //tmpltField.setText(outFilePath);
    }

}
