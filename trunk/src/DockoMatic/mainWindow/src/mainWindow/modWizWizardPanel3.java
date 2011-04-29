/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mainWindow;

import Job.Job;
import java.awt.Component;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
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
public class modWizWizardPanel3  implements WizardDescriptor.Panel, ListSelectionListener {
	/**
	 * The visual component that displays this panel. If you need to access the
	 * component from this class, just use getComponent().
	 */
	private Component component;
	//private static Job[] modJobList;
	private static Job modJob;
	private String fromWhere;
	private boolean isValid;

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
		        modWizVisualPanel3.getTable().getSelectionModel().addListSelectionListener(this);
		        modWizVisualPanel3.genModelMessage.setVisible(false);
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
       int row = modWizVisualPanel3.getTable().getSelectedRow();
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
		((WizardDescriptor) settings).putProperty("numModJobs", modWizVisualPanel2.getNumModJobs());
		((WizardDescriptor) settings).putProperty("jobsPerNode", modWizVisualPanel2.getNumJobsPerNode());
                //String seq = (String)((WizardDescriptor) settings).getProperty("seq");
		//String templt = (String)((WizardDescriptor) settings).getProperty("Template");
		String numPerNode = (String)((WizardDescriptor) settings).getProperty("jobsPerNode");
		String modJobs = (String)((WizardDescriptor) settings).getProperty("numModJobs");
		String[] templtList = (String[])((WizardDescriptor) settings).getProperty("Templates");
		boolean swarm = (Boolean)((WizardDescriptor) settings).getProperty("swarm");

		String oDir = (String)((WizardDescriptor) settings).getProperty("outDir");
		//String pdbName = getPdbTmpltFile(oDir, templt);
		//runModellerJob(oDir, "mySeq", pdbName, swarm);
		doModellerStuff(oDir, templtList, swarm, numPerNode, modJobs);

	}

	public String getModelPath() {
            JTable table = ((modWizVisualPanel3)getComponent()).getTable();
	    int row = table.getSelectedRow();
	    if(row < 0) row = 0;
	    int locCol = ((modWizVisualPanel3)getComponent()).getCol("Location");
	    int modCol = ((modWizVisualPanel3)getComponent()).getCol("Model");
	    return (String)table.getValueAt(row, locCol)+File.separator+(String)table.getValueAt(row, modCol);
	}

	public void storeSettings(Object settings) {
	    JComponent jc = (JComponent) this.component;
	    jc.putClientProperty("MODPATH", getModelPath());
	}


	private void doModellerStuff(final String oDir, final String[] templtList, final boolean swarm, final String nodeJobs, final String modJobs){
	    modWizVisualPanel3.genModelMessage.setVisible(true);

        SwingWorker getAlWorker = new SwingWorker<String, Void>(){

	  @Override
	  protected String doInBackground(){
		//runModellerJobs(oDir, "mySeq", getPdbTmpltFile(oDir, templts), swarm);
		//Job[] list = createModellerJobs(oDir, "mySeq", templtList, swarm);
		runModellerJobs(oDir, createModellerJobs(oDir, "mySeq", templtList, swarm, modJobs), nodeJobs);
		parseResults(oDir, "mySeq");
	        modWizVisualPanel3.genModelMessage.setVisible(false);
	  return "DONE";
	  }
        };
	getAlWorker.execute();
	}

	private void runModellerJobs(String oDir, Job[] jobList, String jobsPerNode){
	    File outDir = new File(oDir);
	    int job, i;
            int totalCount = jobList.length;
	    Process procID;

            // *** Bulk submit ***
            try{
	       String base = Job.class.getResource("Job.class").getPath();
               base = base.substring(base.indexOf(":")+1, base.indexOf("dockomatic/modules/"));
               base += "swarmOut";
               //File swarmDir = File.createTempFile("swarm", null, new File(base));
               File swarmDir = File.createTempFile("swarmOut", null);
	       swarmDir.delete();
	       swarmDir.mkdir();
	       //swarmDir.deleteOnExit();
	       String swarmFile = swarmDir.getCanonicalPath()+"/swarmCmd";

              //run swarm Jobs.
                BufferedWriter swarmOut = new BufferedWriter(new FileWriter(swarmFile));
                for(i = 0; i< totalCount; i++){
                    swarmOut.write(jobList[i].getCmd()+"\n");
                    messageWindowTopComponent.messageArea.append("Starting Modeller Job "+i+"\n");
                }
                swarmOut.close();

                procID = Runtime.getRuntime().exec("swarm -f " + swarmFile + " -n "+jobsPerNode+" -l walltime=128:00:00", null, outDir);

                }catch(Exception e){System.out.println(e);}

	}

	private Job[] createModellerJobs(String oDir, String seq, String[] tmpltList, boolean swarm, String modJobs){
		Job[] modJobList = new Job[tmpltList.length];
		String tmp;
		for(int i=0; i< tmpltList.length; i++){
		    modJobList[i] = new Job(1, "", "", "", oDir, swarm, "", seq, getPdbTmpltFile(oDir, tmpltList[i])+"_"+modJobs);
		}
		return modJobList;
	}

	private void runModellerJob(String oDir, String seq, String tmplt, boolean swarm){
		modJob = new Job(1, "", "", "", oDir, swarm, "", seq, tmplt);
		modJob.runJob(true);
	}

    public static void killJob(){
        modJob.killJob();

    }

        private String getPdbTmpltFile(String odir, String pdb){

//	    String pdb = tmpltField.getText();
	    //String file = pdb.substring(0, pdb.indexOf(":"));
	    //String fWoPdb = odir+File.separator+file;
            messageWindowTopComponent.messageArea.append("Downloading Template file\n");
	    String fWoPdb = odir+File.separator+pdb;
	    String outFilePath=fWoPdb+".pdb";

	    try{
	        //URL url = new URL("http://www.pdb.org/pdb/files/"+file+".pdb");
	        URL url = new URL("http://www.pdb.org/pdb/files/"+pdb+".pdb");
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
	    //return file;
	    return pdb;
        }

    private void parseResults(String dir, String seq)
    {
	    int count = 0;
	    String str;
	    String pdbid;
	    String evalue;
	    String logFile="";
	    boolean done = false;
	    DefaultTableModel model = (DefaultTableModel)((modWizVisualPanel3)getComponent()).getTableModel();

	        File fDir  = new File(dir);
                String flist[] = fDir.list();

            while(!done){
		for(int i=0; i<flist.length; i++){
	            if(flist[i].contains("ModSntnl")){
	                logFile = dir+"/modelSingle.log";
		        done = true;
			break;
		    }else{
			try{
	                    Thread.sleep(5000);
			}catch(InterruptedException ie){}
		    }
                    flist = fDir.list();
		}
	    }
            messageWindowTopComponent.messageArea.append("Parsing Modeller Results\n");
	    try{
	        BufferedReader in = new BufferedReader(new FileReader(logFile));

	        while(!((str = in.readLine()).contains("molpdf")) && in.ready()){}
		if(!in.ready()) return;
		str = in.readLine();

	        while((str = in.readLine())!=null){
		    String[] vals = str.split("[ ]+");

		    model.addRow(new Object[]{vals[0], dir, vals[1], vals[2], vals[3]});
	        }
	        in.close();
	    } catch (IOException e) {
		    e.printStackTrace();
	    }
//		for(int i=0; i<flist.length; i++){
//	            if(flist[i].contains(".pdb") && flist[i].contains(seq)){
//		        model.addRow(new Object[]{flist[i], dir});
//			++count;
//		    }
//		}
		if(count==0)
	            model.addRow(new Object[]{"fakeFile", "FakeDir"});

    }

}
