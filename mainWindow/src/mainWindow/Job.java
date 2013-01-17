# Program: "DockoMatic"
# Project: "DNA Safeguard"
# Filename: "Job.java"
#
# Dr. Tim Andersen
# Department of Computer Science
# College of Engineering
# Boise State University
#
# Original Author(s): "Casey Bullock"
#                     "Nic Cornia"
#
# Last Modified
#   Date: "November 14, 2012"
#
#
#  This file is part of DockoMatic.
#
#  DockoMatic is free software: you can redistribute it and/or modify
#  it under the terms of the GNU Lesser General Public License as published by
#  the Free Software Foundation, either version 3 of the License, or
#  (at your option) any later version.
#
#  DockoMatic is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU Lesser General Public License for more details.
#
#  You should have received a copy of the GNU Lesser General Public License
#  along with DockoMatic.  If not, see <http://www.gnu.org/licenses/>.
#

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mainWindow;

import java.io.*;

/**
 *
 * @author wbullock
 */
public class Job {
    public Process procID = null;
    public ProcessBuilder pb = null;
    public String swarmID = null;
    public boolean isRunning;
    public boolean usingSwarm;
    public boolean usingVina;

    public int jobNum;
    private String ligand = "";
    private String receptor = "";
    private String boxCoord = "";
    private String outDir = "";
    private String append = "";
    private String cmd = "";
    private String sequence = "";
    private String template = "";
    private String cycles = "";
    /** Creates new form Job */

        // Variables declaration - do not modify
        // End of variables declaration

    // Constructor
    public Job(int me, String lig, String rec, String box,
                   //String out, boolean swarm, String app) {
                   String out, boolean swarm, String app, String seq, String tplt, String dockCycles, boolean vina) {
        this.jobNum = me;
        this.ligand = lig;
        this.receptor = rec;
        this.boxCoord = box;
        this.outDir = out;
        this.append = app;
        this.sequence = seq;
        this.template = tplt;
	this.cycles = dockCycles;
	this.usingVina = vina;

        this.setSwarm(swarm);
        this.isRunning = false;


    }

    public String getLig()
    {
	    return ligand;
    }

    public String getRec()
    {
	    return receptor;
    }

    public String getBox()
    {
	    return boxCoord;
    }

    public String getOut()
    {
	    return outDir;
    }

    public String getAppd()
    {
	    return append;
    }

    public String getSeq()
    {
	    return sequence;
    }

    public String getTplt()
    {
	    return template;
    }

    public String getCycles()
    {
	    return cycles;
    }

    // Update info for job.
    public void update(String lig, String out, String rec,
                        String box, boolean swarm, String seq, String tplt, String dockCycles){
        this.ligand = lig;
        this.outDir = out;
        this.receptor = rec;
        this.boxCoord = box;
        //this.append = app;
        this.setSwarm(swarm);
        this.sequence = seq;
        this.template = tplt;
	this.cycles = dockCycles;


    }

    // Creates String for command to be used by system.  This may or may not be used in
    // conjunction with swarm.
    private void setCmd(){
	   this.cmd = Job.class.getResource("Job.class").getPath();
	   // Use this one for running DockoMatic OUTSIDE Netbeans.
           this.cmd = this.cmd.substring(this.cmd.indexOf(":")+1, this.cmd.indexOf("modules/"));
	   ////// Use this one for running DockoMatic INSIDE Netbeans.
           ////this.cmd = this.cmd.substring(this.cmd.indexOf(":")+1, this.cmd.indexOf("build/"));
           this.cmd += "lib/dockOmatic.pl";

           // Make sure dockOmatic.pl is executable, since Netbeans
	   //  changes permissions when creating distribution... neat.
	   File tmpfile = new File(cmd);
	   tmpfile.setExecutable(true);

	   if(usingVina){ cmd += " -v ";}
	   if(sequence.length() > 0){ cmd += " -m "+ sequence; }
           if(template.length() > 0){ cmd += " -t "+ template; }

           if(ligand.length() > 0){ cmd += " -p "+ ligand; }
           if(outDir.length() > 0){ cmd += " -o "+ outDir; }
           if(receptor.length() > 0){ cmd += " -r "+ receptor; }
           if(boxCoord.length() > 0){ cmd += " -b "+ boxCoord; }
           if(append.length() > 0){ cmd += " -a "+ append; }
           if(cycles.length() > 0){ cmd += " -g "+ cycles; }

           //cmd += "\n";
    }

    // Sets boolean to reflect if this job uses swarm or not.
    public void setSwarm(boolean bool){
            this.usingSwarm = bool;
    }

    // Parse list of PBS jobs to find the right one for given swarm ID.
    private String parseQstat(String swmID){
        Process proc;
        String line;
        try{
        proc = Runtime.getRuntime().exec("qstat");
           BufferedReader qstatOut = new BufferedReader(new InputStreamReader(proc.getInputStream()));

           while((line = qstatOut.readLine()) != null){
               if(line.contains(swmID)) return line.substring(0, line.indexOf(" "));
           }

         }catch(java.io.IOException e){
           // Should Do Something about it.
         }
         return null;
    }

    // Gets swarm ID from path of swarm command file.
    private String getSwarmID(){
        String dir = this.outDir + "/.swarm";
        File swarmDir = new File(dir);
        File[] swmFiles = swarmDir.listFiles();

        String ret = swmFiles[0].toString();
        return parseQstat(ret.substring(ret.lastIndexOf("swarm")));
    }

    // Runs job on system exec'ing an instance.
    public void runJob(boolean wait){
	    System.out.println("STARTTING JOB");
	    int ret=0;
        setCmd();

        if(usingSwarm){
                String swarmFile = this.outDir + "/swarmCmd.txt";
                File out = new File(this.outDir);
                //run swarm Job.
                try{
                    BufferedWriter swarmOut = new BufferedWriter(new FileWriter(swarmFile));
	    System.out.println("WRiting cmd");
                    swarmOut.write(this.cmd);
	    System.out.println("WROTE cmd");
                    swarmOut.close();

                    //procID = Runtime.getRuntime().exec("/usr/local/bin/swarm -f " + swarmFile + " -n 1 -l walltime=128:00:00", null, out);
                    procID = Runtime.getRuntime().exec("swarm -f " + swarmFile + " -n 1 -l walltime=128:00:00", null, out);
                try{
                 Thread.sleep(500);
                }catch(InterruptedException e) {
                     throw new RuntimeException(e);
                }
                    this.swarmID = getSwarmID();

                  }catch(java.io.IOException e){
                    // Should Do Something about it.
                  }
        }else{
            try{

                this.procID = Runtime.getRuntime().exec(this.cmd);
            InputStream stdin = procID.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdin);
            BufferedReader br = new BufferedReader(isr);
	    while(br.readLine()!=null){}

//                this.pb = new ProcessBuilder(this.cmd);
//		this.pb.redirectErrorStream(true);
//                this.procID = this.pb.start();


		if(wait){
                    try{
		        ret = this.procID.waitFor();
			System.out.println("GOT "+ret+"AFTER WAITING");
                    }catch(InterruptedException e) {
                        throw new RuntimeException(e);
                    }
		}
            //}catch(java.io.IOException e){
            }catch(Exception e){
                    System.out.println("Caught eX "+e);
                    //System.exit(0);
            //}catch(java.lang.InterruptedException e){
                    // Should Do Something about it.
            }
        }
        this.cmd = "";
        this.isRunning = true;
    }

    // Kils job instance.
    public void killJob(){

        if((this.procID != null) && !this.usingSwarm ){
           this.procID.destroy();
        }else if(this.procID != null && this.swarmID != null){
            try{
                   Process k = Runtime.getRuntime().exec("qdel "+ this.swarmID);
            }catch(java.io.IOException e){}
        }
        this.isRunning = false;

    }

    // Public getter method to return job string created with setCmd().
    public String getCmd(){
        setCmd();
        return this.cmd;
    }
}
