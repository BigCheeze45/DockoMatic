/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Job;

import java.io.*;

/**
 *
 * @author wbullock
 */
public class Job {
    public Process procID = null;
    public String swarmID = null;
    public boolean isRunning;
    public boolean usingSwarm;

    public int jobNum;
    private String ligand = "";
    private String receptor = "";
    private String boxCoord = "";
    private String outDir = "";
    private String append = "";
    private String cmd = "";
    private String sequence = "";
    private String template = "";
    /** Creates new form Job */

        // Variables declaration - do not modify
        // End of variables declaration

    // Constructor
    public Job(int me, String lig, String rec, String box,
                   String out, boolean swarm, String app) {
                   //String out, boolean swarm, String app, String seq, String tplt) {
        this.jobNum = me;
        this.ligand = lig;
        this.receptor = rec;
        this.boxCoord = box;
        this.outDir = out;
        this.append = app;
        //this.sequence = seq;
        //this.template = tplt;

        this.setSwarm(swarm);
        this.isRunning = false;


    }

    // Update info for job.
    public void update(String lig, String out, String rec,
                        String box, boolean swarm, String seq, String tplt){
        this.ligand = lig;
        this.outDir = out;
        this.receptor = rec;
        this.boxCoord = box;
        //this.append = app;
        this.setSwarm(swarm);
        this.sequence = seq;
        this.template = tplt;


    }

    // Creates String for command to be used by system.  This may or may not be used in
    // conjunction with swarm.
    private void setCmd(){
           this.cmd = ClassLoader.getSystemClassLoader().getResource("DNA.png").getPath();
           this.cmd = this.cmd.substring(this.cmd.indexOf(":")+1, this.cmd.indexOf("DockoMatic.jar"));
           this.cmd += "dockOmatic.pl";
           //this.cmd = ClassLoader.getSystemClassLoader().getResource("MainFrame.class").getPath();
           //this.cmd = this.cmd.substring(0, this.cmd.lastIndexOf(File.separator));
           //this.cmd += "dockOmatic.pl";

	   if(sequence.length() > 0){ cmd += " -m "+ sequence; }
           if(template.length() > 0){ cmd += " -t "+ template; }

           if(ligand.length() > 0){ cmd += " -p "+ ligand; }
           if(outDir.length() > 0){ cmd += " -o "+ outDir; }
           if(receptor.length() > 0){ cmd += " -r "+ receptor; }
           if(boxCoord.length() > 0){ cmd += " -b "+ boxCoord; }
           if(append.length() > 0){ cmd += " -a "+ append; }

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
    public void runJob(){
        setCmd();

        if(usingSwarm){
                String swarmFile = this.outDir + "/swarmCmd.txt";
                File out = new File(this.outDir);
                //run swarm Job.
                try{
                    BufferedWriter swarmOut = new BufferedWriter(new FileWriter(swarmFile));
                    swarmOut.write(this.cmd);
                    swarmOut.close();

                    procID = Runtime.getRuntime().exec("/usr/local/bin/swarm -f " + swarmFile + " -n 1 -l walltime=128:00:00", null, out);
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

            }catch(java.io.IOException e){
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
            return this.cmd;
    }
}
