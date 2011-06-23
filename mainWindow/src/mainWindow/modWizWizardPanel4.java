/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mainWindow;

import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

/**
 *
 * @author wbullock
 */
public class modWizWizardPanel4  implements WizardDescriptor.Panel, ListSelectionListener {
	private Component component;
	private boolean isValid;
	private String[] file;

	public modWizWizardPanel4(){
	}

	// Get the visual component for the panel. In this template, the component
	// is kept separate. This can be more efficient: if the wizard is created
	// but never displayed, or not all panels are displayed, it is better to
	// create only those which really need to be visible.
	public Component getComponent() {
		if (component == null) {
			//component = new modWizVisualPanel4(file);
			component = new modWizVisualPanel4();
		        //modWizVisualPanel4.getTable().getSelectionModel().addListSelectionListener(this);
		        //modWizVisualPanel4.genModelMessage.setVisible(false);
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
		//return isValid;
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
       String text = ((modWizVisualPanel4)getComponent()).getEditArea().getText();
        if (text.length()>0) {
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


        private void setContent(File fname, JTextArea alnEditArea){
            try{
	        FileReader fileRead=new FileReader(fname);
                alnEditArea.read(fileRead, null);
	    } catch(Exception e){e.printStackTrace();}
	}

	// You can use a settings object to keep track of state. Normally the
	// settings object will be the WizardDescriptor, so you can use
	// WizardDescriptor.getProperty & putProperty to store information entered
	// by the user.
	public void readSettings(Object settings) {
		file = (String[])((WizardDescriptor) settings).getProperty("tmpltName");
                addTabbs(file);
	}

	public void storeSettings(Object settings) {
            writeAlnFile();
	}

    synchronized private void writeAlnFile(){
        JTabbedPane  alnEditPane = (JTabbedPane)((modWizVisualPanel4)getComponent()).getTabbPane();
	String title;
	JTextArea editArea;
	for(int i=0; i<alnEditPane.getTabCount(); i++){
          title = alnEditPane.getTitleAt(i);
	  editArea = (JTextArea)((JScrollPane)alnEditPane.getComponentAt(i)).getViewport().getView();
	  try{
	    BufferedWriter out = new BufferedWriter(new FileWriter(title));
	    out.write(editArea.getText());
	    out.close();
	  }catch(IOException e){e.printStackTrace();}

	}
        //seq = seq.replace("\n", "");
	//seqArea.setText(outFilePath);

	//return seq;
    }

	protected void addTabbs(String[] tabs)
	{
                JTabbedPane  alnEditPane = (JTabbedPane)((modWizVisualPanel4)getComponent()).getTabbPane();
		alnEditPane.removeTabAt(0);
		JScrollPane jScrollPane1;
		JTextArea editArea;
		for(int i=0; i<tabs.length; i++){
                    jScrollPane1 = new javax.swing.JScrollPane();
                    editArea = new javax.swing.JTextArea();

                    editArea.setColumns(20);
                    editArea.setRows(5);
                    jScrollPane1.setViewportView(editArea);

                    alnEditPane.addTab(tabs[i], jScrollPane1);
                    setContent(new File(tabs[i]), editArea);

		}

	}

}
