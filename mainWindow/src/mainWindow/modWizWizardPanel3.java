/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mainWindow;

import Job.Job;
import java.awt.Component;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JTable;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
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
	private static Job modJob;
	private String fromWhere;
	private String pathToModel;

	public modWizWizardPanel3(String from){
		this.fromWhere = from;
	}

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

	public String getModelPath(){
		return this.pathToModel;
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
                String seq = (String)((WizardDescriptor) settings).getProperty("seq");
		String templt = (String)((WizardDescriptor) settings).getProperty("Template");
		boolean swarm = (Boolean)((WizardDescriptor) settings).getProperty("swarm");

		String oDir = (String)((WizardDescriptor) settings).getProperty("outDir");
		String pdbName = getPdbTmpltFile(oDir, templt);
		runModellerJob(oDir, "mySeq", pdbName, swarm);
		parseResults(oDir, "mySeq");


	}

	public void storeSettings(Object settings) {
            JTable table = ((modWizVisualPanel3)getComponent()).getTable();
	    int row = table.getSelectedRow();
	    if(row < 0) row = 0;
	    int locCol = ((modWizVisualPanel3)getComponent()).getCol("Location");
	    int modCol = ((modWizVisualPanel3)getComponent()).getCol("Model");

	    this.pathToModel = (String)table.getValueAt(row, locCol)+(String)table.getValueAt(row, modCol);
	}


	private void runModellerJob(String oDir, String seq, String tmplt, boolean swarm){
		modJob = new Job(1, "", "", "", oDir, swarm, "", seq, tmplt);
		modJob.runJob();
	}

    public static void killJob(){
        modJob.killJob();

    }

        private String getPdbTmpltFile(String odir, String pdb){

//	    String pdb = tmpltField.getText();
	    String file = pdb.substring(0, pdb.indexOf(":"));
	    String fWoPdb = odir+File.separator+file;
	    String outFilePath=fWoPdb+".pdb";

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
	    return file;
        }

    private void parseResults(String dir, String seq)
    {
	    String str;
	    String pdbid;
	    String evalue;
	    DefaultTableModel model = (DefaultTableModel)((modWizVisualPanel3)getComponent()).getTableModel();

	        File fDir  = new File(dir);
                String flist[] = fDir.list();

		for(int i=0; i<flist.length; i++){
	            if(flist[i].contains(".pdb") && flist[i].contains(seq))
		        model.addRow(new Object[]{flist[i], dir});
		} 

    }

}
