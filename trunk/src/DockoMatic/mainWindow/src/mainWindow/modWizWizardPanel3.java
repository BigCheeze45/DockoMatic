//# Program: "DockoMatic"
//# Project: "DNA Safeguard"
//# Filename: "modWizWizardPanel3.java"
//#
//# Dr. Tim Andersen
//# Department of Computer Science
//# College of Engineering
//# Boise State University
//#
//# Original Author(s): "Casey Bullock"
//#                     "Nic Cornia"
//#
//# Last Modified
//#   Date: "November 14, 2012"
//#
//#
//#  This file is part of DockoMatic.
//#
//#  DockoMatic is free software: you can redistribute it and/or modify
//#  it under the terms of the GNU Lesser General Public License as published by
//#  the Free Software Foundation, either version 3 of the License, or
//#  (at your option) any later version.
//#
//#  DockoMatic is distributed in the hope that it will be useful,
//#  but WITHOUT ANY WARRANTY; without even the implied warranty of
//#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//#  GNU Lesser General Public License for more details.
//#
//#  You should have received a copy of the GNU Lesser General Public License
//#  along with DockoMatic.  If not, see <http://www.gnu.org/licenses/>.
//#

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainWindow;

import utilities.Job;
import java.awt.Component;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
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
public class modWizWizardPanel3 implements WizardDescriptor.Panel, ListSelectionListener {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private Component component;
    private boolean isValid;
    private String errorLog;

    public modWizWizardPanel3() {
    }

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    public Component getComponent() {
        if (component == null) {
            component = new modWizVisualPanel3();
            modWizVisualPanel3.getTable().getSelectionModel().addListSelectionListener(this);
            modWizVisualPanel3.genAlmntMessage.setVisible(false);
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

    public void valueChanged(ListSelectionEvent e) {
        change();
    }

    private void change() {
        int row = modWizVisualPanel3.getTable().getSelectedRow();
        boolean isValidInput = row >= 0;
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
        String seq = (String) ((WizardDescriptor) settings).getProperty("seq");
        String seqName = (String) ((WizardDescriptor) settings).getProperty("seqName");
        String numPerNode = (String) ((WizardDescriptor) settings).getProperty("jobsPerNode");
        String modJobs = (String) ((WizardDescriptor) settings).getProperty("numModJobs");
        String[] tmpltDlList = (String[]) ((WizardDescriptor) settings).getProperty("tmpltDlList");
        boolean swarm = (Boolean) ((WizardDescriptor) settings).getProperty("swarm");

        String oDir = (String) ((WizardDescriptor) settings).getProperty("outDir");
        doAlignStuff(seqName, seq, oDir, tmpltDlList, swarm, numPerNode, modJobs);

    }

    public void storeSettings(Object settings) {
        ((WizardDescriptor) settings).putProperty("tmpltAlnList", ((modWizVisualPanel3) getComponent()).getTempltList());

    }

    private void doAlignStuff(final String seqName, final String seq, final String oDir, final String[] tmpltDlList, final boolean swarm, final String nodeJobs, final String modJobs) {
        modWizVisualPanel3.genAlmntMessage.setVisible(true);

        SwingWorker getAlWorker = new SwingWorker<String, Void>() {

            @Override
            protected String doInBackground() {
                int maxJobs = Integer.parseInt(modJobs);
                String[] fList = createSeqFiles(oDir, seqName, seq, tmpltDlList);
                String[] jList = createAlignJobString(seqName, oDir, tmpltDlList, swarm, maxJobs);

                runAlignJobString(oDir, jList, nodeJobs);
                parseResults(oDir, tmpltDlList.length + getResiduals(fList, oDir));
                modWizVisualPanel3.genAlmntMessage.setVisible(false);
                return "DONE";
            }
        };
        getAlWorker.execute();
    }

    private int countLines(String file) {
        int ret = 0;
        String str;
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));

            while ((str = in.readLine()) != null) {
                ++ret;
            }
            in.close();
        } catch (IOException e) {
            System.err.println("Tried to count lines in nonexistent file: " + file + " ... returning 0.");
        }
        return ret;

    }

    private boolean checkOldList(String[] list, String fname) {

        String tmp;
        for (int i = 0; i < list.length; i++) {
            tmp = list[i] + ".pap";
            if (tmp.equals(fname)) {
                return false;
            }
        }

        return true;
    }

    private int getResiduals(String[] jList, String oDir) {
        int ret = 0;
        String str = "";
        String file = oDir + File.separator+"alLog";
        if (countLines(file) == 0) {
            return 0;
        }

        try {
            BufferedReader in = new BufferedReader(new FileReader(file));

            while ((str = in.readLine()) != null) {
                if (checkOldList(jList, str)) {
                    ++ret;
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    private void runAlignJobString(String oDir, String[] jobList, String jobsPerNode) {
        File outDir = new File(oDir);
        int job, i;
        int totalCount = jobList.length;
        String modellerCMD = "";
        String swarmCMD;
        String line;
        Process procID;

        // *** Bulk submit ***
        try {

            File swarmDir = File.createTempFile("swarmOut", null);
            swarmDir.delete();
            swarmDir.mkdir();
            swarmDir.deleteOnExit();
            String swarmFile = swarmDir.getCanonicalPath() + File.separator+"swarmAlignCmd";
            errorLog = "";

            //remove old resLog file
            String logFile = oDir + File.separator+"alLog";
            File alLog = new File(logFile);
            alLog.delete();
            
            //run swarm Jobs.
            BufferedWriter swarmOut = new BufferedWriter(new FileWriter(swarmFile));
            for (i = 0; i < totalCount; i++) {
                modellerCMD += jobList[i] + "\n";
                messageWindowTopComponent.appendText("Starting Align Job " + i + "\n");
            }
            swarmOut.write(modellerCMD);
            swarmOut.close();
            swarmCMD = "swarm -f " + swarmFile + " -n " + jobsPerNode + " -l walltime=128:00:00";
            procID = Runtime.getRuntime().exec(swarmCMD, null, outDir);
            BufferedReader in = new BufferedReader(new InputStreamReader(procID.getErrorStream()));
            while ((line = in.readLine()) != null) {
                errorLog += line + "\n";
            }
            messageWindowTopComponent.messageArea.setText("");
            messageWindowTopComponent.appendText(errorLog);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private String[] createSeqFiles(String outDir, String seqName, String seq, String[] tmpltList) {
        String outFilePath;//=outDir+File.separator+"mySeq.ali";
        String tmplt;
        String[] retList = new String[tmpltList.length];

        DefaultTableModel model = (DefaultTableModel) ((modWizVisualPanel3) getComponent()).getTableModel();
        model.setRowCount(0);

        for (int i = 0; i < tmpltList.length; i++) {
            if (tmpltList[i].endsWith("pdb")) {
                //tmplt = tmpltList[i].substring(tmpltList[i].lastIndexOf("/")+1,tmpltList[i].indexOf(".pdb"));
                tmplt = tmpltList[i].substring(tmpltList[i].lastIndexOf("/") + 1, tmpltList[i].length());
                tmplt = tmplt.replaceAll(":", "_");
                //tmplt = tmpltList[i].substring(tmpltList[i].lastIndexOf("/")+1,tmpltList[i].indexOf(":"));
            } else {
                //tmplt = tmpltList[i].substring(0, tmpltList[i].indexOf(":"));
                tmplt = tmpltList[i];
                tmplt = tmplt.replaceAll(":", "_");
            }
            retList[i] = seqName + "-" + tmplt;
            outFilePath = outDir + File.separator + seqName + "-" + tmplt + ".ali";
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(outFilePath));
                out.write(">P1;" + seqName + "-" + tmplt + "\n");
                out.write("sequence:" + seqName + "-" + tmplt + ":::::::0.00: 0.00\n");
                out.write(seq + "*\n");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return retList;
    }

    private String[] createAlignJobString(String seqName, String oDir, String[] tmpltList, boolean swarm, int max) {
        String cmd = Job.class.getResource("Job.class").getPath();
        cmd = cmd.substring(cmd.indexOf(":") + 1, cmd.indexOf("modules"+File.separator));
        cmd += "lib"+File.separator+"scripts"+File.separator+"modeller"+File.separator+"align2d.py";
        // Make sure model-disulfide.py is executable, since Netbeans
        //  changes permissions when creating distribution... neat.
        File tmpfile = new File(cmd);
        tmpfile.setExecutable(true);

        String[] alnJobList = new String[tmpltList.length];
        String tmpFile, subunit;
        for (int i = 0; i < tmpltList.length; i++) {
            //tmpFile = getPdbTmpltFile(oDir, tmpltList[i].substring(0, tmpltList[i].indexOf(":")));
            tmpFile = getPdbTmpltFile(oDir, tmpltList[i]);
            //subunit = tmpltList[i].substring(tmpltList[i].lastIndexOf(":")+1, tmpltList[i].length());
            subunit = getSub(tmpltList[i]);//tmpltList[i].substring(tmpltList[i].lastIndexOf(":")+1, tmpltList[i].length());
            alnJobList[i] = cmd + " " + seqName + "-" + tmpFile + " " + tmpFile + " " + oDir + " " + subunit;
            //modJobList[i] = cmd +" query "+ tmpFile +" "+ oDir;
        }
        return alnJobList;
    }

    public static void killJob() {
    }

    private String stripPdb(String pdb) {
        //return pdb.substring(0, pdb.indexOf(":"));
        return pdb.substring(0, pdb.indexOf("_"));
    }

    private String getSub(String pdb) {
        String subunit;
        if (pdb.endsWith(".pdb")) {
            subunit = pdb.substring(pdb.lastIndexOf(":") + 1, pdb.indexOf(".pdb"));
        } else {
            subunit = pdb.substring(pdb.lastIndexOf(":") + 1, pdb.length());
        }

        return subunit;
    }

    private String getPdbTmpltFile(String odir, String pdb) {

        // If using local template, don't download... Copy to output directory.
        messageWindowTopComponent.appendText("Downloading Template file\n");
        pdb = pdb.replaceAll(":", "_");
        if (pdb.endsWith("pdb")) {
            //String tmpPdb = pdb.substring(pdb.lastIndexOf("/")+1, pdb.indexOf(":"));
            String tmpPdb = pdb.substring(pdb.lastIndexOf(File.separator) + 1, pdb.length());
            //Copy file to odir
            try {
                InputStream in = new FileInputStream(pdb);
                OutputStream out = new FileOutputStream(odir + File.separator + tmpPdb);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return tmpPdb;
        }
        String stripped = stripPdb(pdb);
        String fWoPdb = odir + File.separator + pdb;
        String outFilePath = fWoPdb + ".pdb";

        try {
            URL url = new URL("http://www.pdb.org/pdb/files/" + stripped + ".pdb");
            URLConnection urlc = url.openConnection();
            BufferedInputStream in = new BufferedInputStream(urlc.getInputStream());
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFilePath));

            int i;
            while ((i = in.read()) != -1) {
                out.write(i);
            }
            in.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //return stripped;
        return pdb;
    }

    private boolean isItDone(String fname, int max) {
        File file = new File(fname);
        if (!file.exists()) {
            return false;
        }

        int count = countLines(fname);

        if (count == max) {
            return true;
        }

        return false;
    }

    private void parseResults(final String dir, final int max) {
        String str;
        String logFile = dir + File.separator +"alLog";
        DefaultTableModel model = (DefaultTableModel) ((modWizVisualPanel3) getComponent()).getTableModel();

        while (!isItDone(logFile, max)) {
            try {
                Thread.sleep(2000); // Sleep 2 seconds then check again.
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

        messageWindowTopComponent.appendText("Parsing Align Results\n");
        try {
            BufferedReader in = new BufferedReader(new FileReader(logFile));

            while ((str = in.readLine()) != null) {
                model.addRow(new Object[]{str, dir});
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
