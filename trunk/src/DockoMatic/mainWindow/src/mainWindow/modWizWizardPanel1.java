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
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.pdb.webservices.PdbWebService;
import org.pdb.webservices.PdbWebServiceServiceLocator;

public class modWizWizardPanel1 implements WizardDescriptor.Panel {

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
			component = new modWizVisualPanel1();
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
	}

	public void storeSettings(Object settings) {
		//Get Sequence
		String seq = getSeq();
		System.out.println("GOT SEQ:"+seq);
		lookupAlgnmnts(seq);
		((WizardDescriptor) settings).putProperty("seq", seq);
		((WizardDescriptor) settings).putProperty("outDir", ((modWizVisualPanel1)getComponent()).getOutDirField());
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
	    if(!seq.contains(".ali")){
	        createSeqFile(seq);
	    }else{
                seq = getSeqFromFile(seq);
	    }
            seq = seq.replace("\n", "");

	    return seq;
    }

    private void createSeqFile(String seq){
	String outDir = ((modWizVisualPanel1)getComponent()).getOutDirField();
	String outFilePath=outDir+File.separator+"mySeq.ali";
	try{
	    BufferedWriter out = new BufferedWriter(new FileWriter(outFilePath));
	    out.write(">P1;mySeq\n");
	    out.write("sequence:mySeq:::::::0.00: 0.00\n");
	    out.write(seq+"*\n");
	    out.close();
	}catch(IOException e){e.printStackTrace();}
        //seq = seq.replace("\n", "");
	//seqArea.setText(outFilePath);

	//return seq;
    }

    private void lookupAlgnmnts(String seq){
	    String outDir = ((modWizVisualPanel1)getComponent()).getOutDirField();
	    PdbWebServiceServiceLocator locator = new PdbWebServiceServiceLocator();
            try{
                        String _url = "http://www.pdb.org/pdb/services/pdbws";
                        URL url = new URL(_url);
                        PdbWebService p = locator.getpdbws(url);
                        String output = p.blastPDB(seq, 10, "BLOSUM62" , "HTML");
			String outName = outDir+File.separator+"MyBlastResults.html";
                        File outputFile = new File (outName);
                        PrintStream printer = new PrintStream ( outputFile );

                        printer.print(output);
                        printer.flush();
                        printer.close();

			//parseResults(outName);
                } catch (Exception _e) {
                        _e.printStackTrace();
                }
    }

//    private void parseResults(String fname)
//    {
//	    String str;
//	    String pdbid;
//	    String evalue;
//	    model = (DefaultTableModel)modOutTopComponent.newTmpTabb("Available Alignments");
//
//	    try{
//	        BufferedReader in = new BufferedReader(new FileReader(fname));
//	        while(!((str = in.readLine()).contains("|pdbid"))){}
//
//		do{
//	            pdbid = str.substring(0, str.indexOf("|"));
//	            evalue = str.substring(str.indexOf("/a>")+3, str.length());
//		    model.addRow(new Object[]{pdbid, evalue});
//		} while (!((str = in.readLine()).contains("/PRE")));
//		in.close();
//	    } catch (IOException e) {
//		    e.printStackTrace();
//	    }
//
//
//
//    }
}
