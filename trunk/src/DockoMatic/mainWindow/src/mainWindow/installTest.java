/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainWindow;

import java.io.*;
import java.net.URL;

/**
 *
 * @author ncornia
 */
public class installTest {

    String cmd, errorLog = "";

    public installTest() {
        messageWindowTopComponent.messageArea.append("");
        testCommands();
        writeErrors();
    }

    private void testCommands() {
        URL location = installTest.class.getProtectionDomain().getCodeSource().getLocation();
        cmd = location.getFile();
        cmd = cmd.substring(cmd.indexOf(":") + 1, cmd.indexOf("modules/"));

        testSwarm();
        testModeller();
        testPymol();
        //testChecker();
        //testAutoDoc();
    }

    private void testSwarm() {
        String line = null;
        try {
            String swarmCmd = cmd + "swarmFile.txt";
            Process swarm = Runtime.getRuntime().exec("swarm -f " + swarmCmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(swarm.getErrorStream()));
            while ((line = in.readLine()) != null) {
                errorLog += line + "\n";
            }

        } catch (Exception e) {
        }

    }

    private void testModeller() {
        String line;
        try {
            Process modeller = Runtime.getRuntime().exec("mod9.10 -help");
            BufferedReader in = new BufferedReader(new InputStreamReader(modeller.getErrorStream()));
            if ((line = in.readLine()).contains("command not found")) {
                errorLog += "No modeller\n";
            } else { errorLog += "Yes modeller\n"; }
        } catch (Exception e) {
        }
    }

    private void testPymol() {
        String line;
        try {
            Process pymol = Runtime.getRuntime().exec("pymol -help");
            BufferedReader in = new BufferedReader(new InputStreamReader(pymol.getErrorStream()));
            if ((line = in.readLine()).contains("command not found")) {
                errorLog += "No pymol\n";
            } else { errorLog += "Yes pymol\n"; }
        } catch (Exception e) {
        }
    }

    private void testChecker() {
        String line;
        try {
            Process checker = Runtime.getRuntime().exec("/home/ncornia/DockoMatic/build/dockomatic/lib/scripts/resultCheck.pl -d /home/ncornia/DockoMatic/mainWindow/test/dock_1 -b /home/ncornia/DockoMatic/mainWindow/manifest.mf -o /home/ncornia/DockoMatic/mainWindow/test/dock_4");
            BufferedReader in = new BufferedReader(new InputStreamReader(checker.getErrorStream()));
            while ((line = in.readLine()) != null) {
                errorLog += line + "\n";
            }

        } catch (Exception e) {
        }
    }

    private void testAutoDoc() {
        String line;
        try {
            Process autoDoc = Runtime.getRuntime().exec("");
            BufferedReader in = new BufferedReader(new InputStreamReader(autoDoc.getErrorStream()));
            while ((line = in.readLine()) != null) {
                errorLog += line + "\n";
            }

        } catch (Exception e) {
        }
    }

    private void writeErrors() {
        messageWindowTopComponent.messageArea.append(errorLog);
    }
}
