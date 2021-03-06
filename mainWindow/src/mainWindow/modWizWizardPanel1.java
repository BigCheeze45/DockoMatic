//# Program: "DockoMatic"
//# Project: "DNA Safeguard"
//# Filename: "modWizWizardPanel1.java"
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
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class modWizWizardPanel1 implements WizardDescriptor.Panel, DocumentListener {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private Component component;
    private boolean isValid = false;
    private String errorlog;
    private boolean highlightMe = true;

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

    private void change() {
        String seqArea = modWizVisualPanel1.getSeqField();
        String outDir = modWizVisualPanel1.getOutField().getText();
        messageWindowTopComponent.messageArea.setText("");
        boolean isValidInput = seqArea != null && seqArea.length() > 0 && outDir != null && outDir.length() > 0 && checkSeq(seqArea);
        if (isValidInput) {
            setValid(true);
        } else {
            setValid(false);
            messageWindowTopComponent.appendText(errorlog);
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
        createSeqFile(((modWizVisualPanel1) getComponent()).getOutDirField(), seq);
        ((WizardDescriptor) settings).putProperty("seq", seq);
        ((WizardDescriptor) settings).putProperty("seqName", ((modWizVisualPanel1) getComponent()).getSeqName());
        ((WizardDescriptor) settings).putProperty("outDir", ((modWizVisualPanel1) getComponent()).getOutDirField());
        ((WizardDescriptor) settings).putProperty("auto", ((modWizVisualPanel1) getComponent()).isAuto());
        ((WizardDescriptor) settings).putProperty("diBonds", newDisulfide(((modWizVisualPanel1) getComponent()).getDis()));
    }

    private String getSeqFromFile(String fname) {
        String seq = "";
        String str;
        try {
            BufferedReader in = new BufferedReader(new FileReader(fname));
            while (!((str = in.readLine()).contains("sequence"))) {
            }
            while ((str = in.readLine()) != null) {
                seq += str;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return seq;
    }

    private void createSeqFile(String outDir, String seq) {
        String seqName = ((modWizVisualPanel1) getComponent()).getSeqName();
        String outFilePath = outDir + File.separator + seqName;
        if ((new File(outFilePath + ".ali")).exists()) {
            outFilePath += ".ali";
        }

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(outFilePath));
            out.write(">P1;" + seqName + "\n");
            out.write("sequence:" + seqName + ":::::::0.00: 0.00\n");
            out.write(seq + "*\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkSeq(String seq) {
        String aminoAcids = "ARNDCQEGHILKMFPSTWYVBZXarndcqeghilkmfpstwyvbzx";
        final String thisSeq = formatSeq(seq);

        // A boolean is being used here to keep setText from entering an infinate loop by recalling checkSeq() and resetting the text.
        // A semaphore lock might be needed at here to fix multi-treaded related errors, or possible complete redesign.
        if (highlightMe) {
            highlightMe = false;
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    ((modWizVisualPanel1) getComponent()).seqArea.setText(thisSeq);
                }
            });
        } else {
            highlightMe = true;
        }

        if (!seq.contains(".ali")) {
            int invalidChar = 0;
            for (int x = 0; x < seq.length(); x++) {
                if (!(aminoAcids.contains(seq.charAt(x) + ""))) {
                    ((modWizVisualPanel1) getComponent()).highlight(((modWizVisualPanel1) getComponent()).seqArea, seq.charAt(x) + "");
                    invalidChar++;
                }
            }
            if (invalidChar > 0) {
                errorlog = "The sequence contains invalid characters, please revise it. \n";
                return false;
            }
        }
        
        return true;
    }

    private String formatSeq(String seq) {
        if (seq.contains(".ali")) {
            seq = seq.substring(seq.lastIndexOf("/") + 1, seq.length());
        } else {
            seq = seq.replace("\n", "");
            seq = seq.replace(" ", "");
            seq = seq.replace("\t", "");
            for (int x = 0; x <= 9; x++) {
                seq = seq.replace((x + ""), "");
            }
            seq = seq.toUpperCase();
        }
        
        return seq;
    }

    private String getSeq() {
        String seq = ((modWizVisualPanel1) getComponent()).getSeqField();
        if (seq.contains(".ali")) {
            seq = getSeqFromFile(seq);
        }
        
        return seq;
    }

    private String[] newDisulfide(String[] args) {
        int count = args.length;
        if (args[0].isEmpty()) {
            return new String[]{""};
        }

        String tmp[];
        // Store the 2 residue numbers after disLine[0] and disLine[1]
        String[] disLine = new String[]{"\tself.patch(residue_type='DISU', residues=(self.residues['",
            "'],self.residues['",
            "']))"
        };
        
        String[] diBonds = new String[count];
        for (int i = 0; i < count; i++) {
            tmp = args[i].split("-");
            diBonds[i] = disLine[0] + tmp[0] + disLine[1] + tmp[1] + disLine[2];
        }
        
        return diBonds;
    }
}
