//# Program: "DockoMatic"
//# Project: "DNA Safeguard"
//# Filename: "installTest.java"
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
        //testAutoDock();
    }

    private void testSwarm() {
        String line = null;
        try {
            Process swarm = Runtime.getRuntime().exec("swarm");
            BufferedReader in = new BufferedReader(new InputStreamReader(swarm.getErrorStream()));
            if ((line = in.readLine()).contains("command not found")) {
                errorLog += "No swarm\n";
            }
        } catch (Exception e) {
        }

    }

    private void testModeller() {
        String line;
        try {
            Process modeller = Runtime.getRuntime().exec("find ~/ -name modeller");
            BufferedReader in = new BufferedReader(new InputStreamReader(modeller.getErrorStream()));
            if ((line = in.readLine()) == null) {
                errorLog += "No modeller\n";
            } 
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
            }
        } catch (Exception e) {
        }
    }

    private void testChecker() {
        String line;
        try {
            Process checker = Runtime.getRuntime().exec("");
            BufferedReader in = new BufferedReader(new InputStreamReader(checker.getErrorStream()));
            while ((line = in.readLine()) != null) {
                errorLog += line + "\n";
            }

        } catch (Exception e) {
        }
    }

    private void testAutoDock() {
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
