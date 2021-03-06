//# Program: "DockoMatic"
//# Project: "DNA Safeguard"
//# Filename: "modWizWizardPanel5.java"
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
import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
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
public class modWizWizardPanel5 implements WizardDescriptor.Panel, ListSelectionListener {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private Component component;
    private String fromWhere;
    private boolean isValid;
    private static JPopupMenu jpop;// = new JPopupMenu();
    private String errorLog;

    public modWizWizardPanel5(String from) {
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

    public void valueChanged(ListSelectionEvent e) {
        change();
    }

    private void change() {
        int row = modWizVisualPanel5.getTable().getSelectedRow();
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
        String seqName = (String) ((WizardDescriptor) settings).getProperty("seqName");
        String numPerNode = (String) ((WizardDescriptor) settings).getProperty("jobsPerNode");
        String modJobs = (String) ((WizardDescriptor) settings).getProperty("numModJobs");
        String[] templtList = (String[]) ((WizardDescriptor) settings).getProperty("tmpltAlnList");
        boolean swarm = (Boolean) ((WizardDescriptor) settings).getProperty("swarm");
        String[] residues = (String[]) ((WizardDescriptor) settings).getProperty("diBonds");

        String oDir = (String) ((WizardDescriptor) settings).getProperty("outDir");
        doModellerStuff(seqName, oDir, templtList, swarm, numPerNode, modJobs, residues);

    }

    public String getModelPath() {
        JTable table = ((modWizVisualPanel5) getComponent()).getTable();
        int row = table.getSelectedRow();
        if (row < 0) {
            row = 0;
        }
        int locCol = ((modWizVisualPanel5) getComponent()).getCol("Location");
        int modCol = ((modWizVisualPanel5) getComponent()).getCol("Model");
        return (String) table.getValueAt(row, locCol) + File.separator + (String) table.getValueAt(row, modCol);
    }

    public void storeSettings(Object settings) {
        JComponent jc = (JComponent) this.component;
        jc.putClientProperty("MODPATH", getModelPath());
        ((WizardDescriptor) settings).putProperty("diBonds", new String[1]);
    }

    private void doModellerStuff(final String seqName, final String oDir, final String[] templtList,
            final boolean swarm, final String nodeJobs, final String modJobs, final String[] residues) {
        modWizVisualPanel5.genModelMessage.setVisible(true);

        SwingWorker getAlWorker = new SwingWorker<String, Void>() {

            @Override
            protected String doInBackground() {
                int maxJobs = Integer.parseInt(modJobs);
                runModellerJobs(oDir, createModellerJobs(seqName, oDir, templtList, swarm, maxJobs, residues), nodeJobs);
                //               makeLogFile(oDir, seqName, templtList, maxJobs);
                if (residues[0].equals("")) {
                    parseResults(oDir, (maxJobs * templtList.length), false);
                } else {
                    parseResults(oDir, (maxJobs * templtList.length), true);
                }
                modWizVisualPanel5.genModelMessage.setVisible(false);
                return "DONE";
            }
        };
        getAlWorker.execute();
    }

//    private void makeLogFile(String oDir, String seqName, String[] tmpltList, int max) {
//        File outDir = new File(oDir);
//        String CMD;
//        String line;
//        Process procID;
//
//        try {
//            errorLog = "";
//            
//            int numJobs = tmpltList.length;
//            //int lines = max+1;
//            String log;
//            String cmd = Job.class.getResource("Job.class").getPath();
//            cmd = cmd.substring(cmd.indexOf(":") + 1, cmd.indexOf("modules/"));
//            String[] modJobList = new String[numJobs * max];
//                String tmpFile;
//                for (int i = 0; i < numJobs; i++) {
//                    tmpFile = tmpltList[i].substring(tmpltList[i].indexOf("-") + 1, tmpltList[i].indexOf("."));
//                    // Make a separate command for each model, so we can parallelize.
//                    for (int j = 0; j < max; j++) {
//                        log = oDir + "/model_" + tmpFile + "_" + j + ".log";
//                        modJobList[(i * max) + j] = "grep ^" + seqName + " model_" + tmpFile + "*.log > " + oDir + "/resLog";
//                    }
//                }
//
//            procID = Runtime.getRuntime().exec(swarmCMD, null, outDir);
//            BufferedReader in = new BufferedReader(new InputStreamReader(procID.getErrorStream()));
//            while ((line = in.readLine()) != null) {
//                errorLog += line + "\n";
//            }
//            messageWindowTopComponent.messageArea.setText("");
//            messageWindowTopComponent.appendText(errorLog);
//
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//    }
    private void runModellerJobs(String oDir, String[] jobList, String jobsPerNode) {
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
            String swarmFile = swarmDir.getCanonicalPath() + "/swarmModCmd";
            errorLog = "";

            //remove old resLog file
            String logFile = oDir + "/resLog";
            File resLog = new File(logFile);
            resLog.delete();

            //run swarm Jobs.
            BufferedWriter swarmOut = new BufferedWriter(new FileWriter(swarmFile));
            for (i = 0; i < totalCount; i++) {
                modellerCMD += jobList[i] + "\n";
                messageWindowTopComponent.appendText("Starting Modeller Job " + i + "\n");
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

    private String[] createModellerJobs(String seqName, String oDir, String[] tmpltList, boolean swarm, int max, String[] residues) {
        DefaultTableModel model = (DefaultTableModel) ((modWizVisualPanel5) getComponent()).getTableModel();
        model.setRowCount(0);

        int numJobs = tmpltList.length;
        //int lines = max+1;
        String log;
        String cmd = Job.class.getResource("Job.class").getPath();
        cmd = cmd.substring(cmd.indexOf(":") + 1, cmd.indexOf("modules/"));
        if (residues[0].equals("")) {
            cmd += "lib/scripts/modeller/model-single.py";
            // Make sure model-single.py is executable, since Netbeans
            // changes permissions when creating distribution... neat.
            File tmpfile = new File(cmd);
            tmpfile.setExecutable(true);
        } else {
            cmd += "lib/scripts/modeller/model-disulfide.py";
            makeDisScript(residues);
        }

        String[] modJobList = new String[numJobs * max];
        String tmpFile;
        for (int i = 0; i < numJobs; i++) {
            tmpFile = tmpltList[i].substring(tmpltList[i].indexOf("-") + 1, tmpltList[i].indexOf("."));
            // Make a separate command for each model, so we can parallelize.
            for (int j = 0; j < max; j++) {
                log = oDir + "/model_" + tmpFile + "_" + j + ".log";
                modJobList[(i * max) + j] = cmd + " " + seqName + "-" + tmpFile + " " + tmpFile + " " + j + " " + oDir + " > " + log;
                // modJobList[(i * max) + j] += "; grep ^" + seqName + " model_" + tmpFile + "*.log > " + oDir + "/resLog";
            }
            modJobList[((i + 1) * max) - 1] += "; grep ^" + seqName + " model_" + tmpFile + "*.log >> " + oDir + "/resLog";
        }
        return modJobList;
    }

    private void makeDisScript(String[] res) {
        String str;
        String file = Job.class.getResource("Job.class").getPath();
        file = file.substring(file.indexOf(":") + 1, file.indexOf("modules/"));
        String newFile = file + "lib/scripts/modeller/model-disulfide.py";
        file += "lib/scripts/modeller/model-disulfide_template";
        File nf = new File(newFile);

        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            BufferedWriter out = new BufferedWriter(new FileWriter(newFile));

            do {
                str = in.readLine();
                out.write(str);
                out.newLine();
            } while (!(str.contains("Insert")));

            for (int i = 0; i < res.length; i++) {
                out.write(res[i]);
                out.newLine();
            }

            while ((str = in.readLine()) != null) {
                out.write(str);
                out.newLine();
            }

            in.close();
            out.close();
            nf.setExecutable(true);

        } catch (Exception e) {
            System.out.println("Tried to create " + newFile + " but ran into problems...");
            e.printStackTrace();
        }

    }

    public static void killJob() {
    }

//    // Create a popup menu
//    private static void makePopMenu(final java.awt.event.MouseEvent evtOrig) {
//        JMenuItem optionMenuRamaPlot = new JMenuItem("Ramachandran Plot");
//
//
//        jpop = new JPopupMenu("Options");
//        jpop.add(optionMenuRamaPlot);
//
//
//        optionMenuRamaPlot.addMouseListener(new MouseAdapter() {
//
//            public void mousePressed(MouseEvent e) {
//                modWizVisualPanel5.ramaPlot(e, evtOrig);
//            }
//        });
//
////		optionDisplayMore.addMouseListener(new MouseAdapter(){
////			public void mousePressed(MouseEvent e){
////			    openerTopComponent.displayMore(e, evtOrig);
////			}
////		});
//
//        jpop.show(evtOrig.getComponent(), evtOrig.getX(), evtOrig.getY());
//
//    }
    private boolean isDone(String fname, int max) {
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

    private int countLines(String file) {
        int ret = 0;
        String str;
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));

            while ((str = in.readLine()) != null) {
                ++ret;
            }
            in.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Tried to count lines in nonexistent file: " + file + " ... returning 0.");
        }

        return ret;

    }

    private int getResiduals(String oDir) {
        String file = oDir + "/resLog";
        int logLines = countLines(file);

        return logLines;
    }

    private void parseResults(String dir, int max, boolean disulfide) {
        int count = 0;
        String str;
        String pdbid;
        String evalue;
        String logFile = "";
        boolean done = false;
        DefaultTableModel model = (DefaultTableModel) ((modWizVisualPanel5) getComponent()).getTableModel();
        logFile = dir + "/resLog";

        while (!isDone(logFile, max)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
            }
        }
        messageWindowTopComponent.appendText("Parsing Modeller Results\n");
        try {
            BufferedReader in = new BufferedReader(new FileReader(logFile));

            while ((str = in.readLine()) != null) {
                String[] vals = str.split("[ ]+");
                model.addRow(new Object[]{vals[0].substring(vals[0].indexOf(":") + 1), dir, vals[1], vals[2], vals[3]});
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
