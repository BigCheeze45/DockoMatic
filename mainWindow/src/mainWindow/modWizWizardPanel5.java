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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

/**
 *
 * @author wbullock
 */
public class modWizWizardPanel5  implements WizardDescriptor.Panel, ListSelectionListener {
	/**
	 * The visual component that displays this panel. If you need to access the
	 * component from this class, just use getComponent().
	 */
	private Component component;
	private String fromWhere;
	private boolean isValid;

	public modWizWizardPanel5(String from){
		this.fromWhere = from;
	}

	// Get the visual component for the panel. In this template, the component
	// is kept separate. This can be more efficient: if the wizard is created
	// but never displayed, or not all panels are displayed, it is better to
	// create only those which really need to be visible.
	public Component getComponent() {
		if (component == null) {
			component = new modWizVisualPanel5();
		        modWizVisualPanel5.getTable().getSelectionModel().addListSelectionListener(this);
		        modWizVisualPanel5.genModelMessage.setVisible(false);
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
        int row = modWizVisualPanel5.getTable().getSelectedRow();
        boolean isValidInput = row >= 0 ;
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
		String seqName = (String)((WizardDescriptor) settings).getProperty("seqName");
		String numPerNode = (String)((WizardDescriptor) settings).getProperty("jobsPerNode");
		String modJobs = (String)((WizardDescriptor) settings).getProperty("numModJobs");
		String[] templtList = (String[])((WizardDescriptor) settings).getProperty("tmpltAlnList");
		boolean swarm = (Boolean)((WizardDescriptor) settings).getProperty("swarm");
		String[] residues = (String[])((WizardDescriptor) settings).getProperty("diBonds");

		String oDir = (String)((WizardDescriptor) settings).getProperty("outDir");
		doModellerStuff(seqName, oDir, templtList, swarm, numPerNode, modJobs, residues);

	}

	public String getModelPath() {
            JTable table = ((modWizVisualPanel5)getComponent()).getTable();
	    int row = table.getSelectedRow();
	    if(row < 0) row = 0;
	    int locCol = ((modWizVisualPanel5)getComponent()).getCol("Location");
	    int modCol = ((modWizVisualPanel5)getComponent()).getCol("Model");
	    return (String)table.getValueAt(row, locCol)+File.separator+(String)table.getValueAt(row, modCol);
	}

	public void storeSettings(Object settings) {
	    JComponent jc = (JComponent) this.component;
	    jc.putClientProperty("MODPATH", getModelPath());
	    ((WizardDescriptor) settings).putProperty("diBonds", new String[1]);
	}


	private void doModellerStuff(final String seqName, final String oDir, final String[] templtList,
		final boolean swarm, final String nodeJobs, final String modJobs, final String[] residues){
	    modWizVisualPanel5.genModelMessage.setVisible(true);


        SwingWorker getAlWorker = new SwingWorker<String, Void>(){

	  @Override
	  protected String doInBackground(){
		int maxJobs = Integer.parseInt(modJobs);
		runModellerJobs(oDir, createModellerJobs(seqName, oDir, templtList, swarm, maxJobs, residues), nodeJobs);
		if(residues[0].equals(""))
		    parseResults(oDir, (maxJobs*templtList.length)+getResiduals(oDir), false);
		else
		    parseResults(oDir, (maxJobs*templtList.length)+getResiduals(oDir), true);
	        modWizVisualPanel5.genModelMessage.setVisible(false);
	  return "DONE";
	  }
        };
	getAlWorker.execute();
	}

	private void runModellerJobs(String oDir, String[] jobList, String jobsPerNode){
	    File outDir = new File(oDir);
	    int job, i;
            int totalCount = jobList.length;
	    Process procID;

            // *** Bulk submit ***
            try{
               File swarmDir = File.createTempFile("swarmOut", null);
	       swarmDir.delete();
	       swarmDir.mkdir();
	       swarmDir.deleteOnExit();
	       String swarmFile = swarmDir.getCanonicalPath()+"/swarmModCmd";

              //run swarm Jobs.
                BufferedWriter swarmOut = new BufferedWriter(new FileWriter(swarmFile));
                for(i = 0; i< totalCount; i++){
                    swarmOut.write(jobList[i]+"\n");
                    messageWindowTopComponent.messageArea.append("Starting Modeller Job "+i+"\n");
                }
                swarmOut.close();

                procID = Runtime.getRuntime().exec("swarm -f " + swarmFile + " -n "+jobsPerNode+" -l walltime=128:00:00", null, outDir);

                }catch(Exception e){System.out.println(e);}

	}

	private String[] createModellerJobs(String seqName, String oDir, String[] tmpltList, boolean swarm, int max, String[] residues){
	    DefaultTableModel model = (DefaultTableModel)((modWizVisualPanel5)getComponent()).getTableModel();
            model.setRowCount(0);

		int numJobs = tmpltList.length;
		int lines = max+1;
		String log;
	        String cmd = Job.class.getResource("Job.class").getPath();
                cmd = cmd.substring(cmd.indexOf(":")+1, cmd.indexOf("dockomatic/modules/"));
		if(residues[0].equals("")){
		    cmd += "lib/scripts/modeller/model-single.py";
		} else {
		    cmd += "lib/scripts/modeller/model-disulfide.py";
		    makeDisScript(residues);
		}

		String[] modJobList = new String[numJobs];
		String tmpFile;
		for(int i=0; i< numJobs; i++){
	            tmpFile = tmpltList[i].substring(tmpltList[i].indexOf("-")+1, tmpltList[i].indexOf("."));
		    log = oDir + "/model_"+tmpFile+".log";
		    modJobList[i] = cmd +" "+ seqName +"-"+tmpFile +" "+ tmpFile +" "+ max + " "+ oDir + " > "+ log;
		    modJobList[i] += "; tail -n "+lines+" model_*.log | grep pdb > "+oDir+"/resLog" ;
		}
		return modJobList;
	}

    private void makeDisScript(String[] res){
	    String str;
	    String file = Job.class.getResource("Job.class").getPath();
            file = file.substring(file.indexOf(":")+1, file.indexOf("dockomatic/modules/"));
	    String newFile = file + "lib/scripts/modeller/model-disulfide.py";
	    file += "lib/scripts/modeller/model-disulfide_template";
	    File nf = new File(newFile);

            try{
	        BufferedReader in = new BufferedReader(new FileReader(file));
	        BufferedWriter out = new BufferedWriter(new FileWriter(newFile));

		do{
		    str = in.readLine();
                    out.write(str);
		    out.newLine();
		} while (!(str.contains("Insert")));

		for(int i=0; i< res.length; i++){
	            out.write(res[i]);
		    out.newLine();
		}

	        while((str = in.readLine())!=null){
                    out.write(str);
		    out.newLine();
	        }

	        in.close();
		out.close();
		nf.setExecutable(true);

	    }catch(Exception e){
		    System.out.println("Tried to create "+newFile+" but ran into problems...");
		    e.printStackTrace();
	    }


    }

    public static void killJob(){

    }

    private boolean isDone(String fname, int max)
    {
	    File file = new File(fname);
	    if(!file.exists()) return false;

	    int count = countLines(fname);

            if(count == max) return true;

	    return false;
    }

    private int countLines(String file){
	    int ret = 0;
	    String str;
            try{
	        BufferedReader in = new BufferedReader(new FileReader(file));

	        while((str = in.readLine())!=null){
                    ++ret;
	        }
	        in.close();
	    }catch(Exception e){
		    //e.printStackTrace();
		    System.out.println("Tried to count lines in nonexistent file: "+file+" ... returning 0.");
	    }

	    return ret;

    }
	private int getResiduals(String oDir){
	    String file = oDir+"/resLog";
            int ret = countLines(file);

	    File rLog = new File(file);
	    rLog.delete();

	    return ret;
	}


    private void parseResults(String dir, int max, boolean disulfide)
    {
	    int count = 0;
	    String str;
	    String pdbid;
	    String evalue;
	    String logFile="";
	    boolean done = false;
	    DefaultTableModel model = (DefaultTableModel)((modWizVisualPanel5)getComponent()).getTableModel();
	    logFile = dir+"/resLog";

            while(!isDone(logFile, max)){
		try{
	            Thread.sleep(1000);
		}catch(InterruptedException ie){}
	    }
            messageWindowTopComponent.messageArea.append("Parsing Modeller Results\n");
	    try{
	        BufferedReader in = new BufferedReader(new FileReader(logFile));

	        while((str = in.readLine())!=null){
		    String[] vals = str.split("[ ]+");

		    if(disulfide)
		        model.addRow(new Object[]{vals[0].substring(vals[0].indexOf(":")+1), dir, vals[1]});
		    else
		        model.addRow(new Object[]{vals[0].substring(vals[0].indexOf(":")+1), dir, vals[1], vals[2], vals[3]});
	        }
	        in.close();
	    } catch (IOException e) {
		    e.printStackTrace();
	    }

    }

}
