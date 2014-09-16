package map;

import java.io.File;

/*
 * Output format:   note that anything in <>'s is not literal
 * <CID1>
 * HISTOGRAM:#_of_bins,<list of comma delimited integers representing the histogram for molecule CID>
 * FUNCTIONAL_GROUPS:
 * <FunctionGroup1_Name>,<x>,<y>,<z>
 *  <FunctionGroup2_Name>,<x>,<y>,<z>
 * ...
 *  <FunctionGroupM_Name>,<x>,<y>,<z>
 * $$$$
 * <CID2>
 * HISTOGRAM: <list of comma delimited integers representing the histogram for molecule CID>
 * FUNCTIONAL_GROUPS:
 * <FunctionGroup1Name>,<x>,<y>,<z>
 * <FunctionGroup2Name>,<x>,<y>,<z>
 * ...
 * 
 * 
 */
/**
 * How this class works: This class is meant to be used in tandem with a cluster
 * submission program, such as swarm, the Mapper class, and a configuration
 * file. The configuration file will contain comma delimited lists of the sdf
 * files, which are all assumed to be in a folder given by args[0], which this
 * program is responsible for transforming.
 *
 * To use it, create a configuration file in a folder <MAP_FOLDER> which is in
 * the same directory as the one containing the .sdf database. The config file
 * will need to have the format
 *
 * file1,file2,file3,........,fileN (new line) fileN+1,fileN+2,....,fileM (new
 * line) ...
 *
 * where an instance of MapDriver will be given a line number (<idNum>) from the
 * config file. The MapDriver will grab that line from the config file, parse
 * the file names, and start a Mapper for each file. This format was designed to
 * work in tandem with a batch submission utility like swarm. However, it can be
 * turned into a multithreaded program by making Mapper implement the runnable
 * interface.
 *
 *
 * @author tlong
 *
 */
public class MapDriver {

    public static void main(String[] args) {

        if (args.length < 7) {
            System.err.println("Usage : java mapDriver <sdf_Folder> <outputFolder> <mode> <#_bins> <max_measurement> <functionGroupFile> <files in CSV>");
            return;
        }

        int argnum = 0;
        File sdf_directory = new File(args[argnum++]);
        String outputFolder = args[argnum++];
        String mode = args[argnum++];
        int numBins = Integer.parseInt(args[argnum++]);
        double stepSize = Double.parseDouble(args[argnum++]) / numBins;
        String fGroupPath = args[argnum++];
        String files = args[argnum++];
        int MAX_WRITE = 5000;

        String[] fileNames = files.split(",");

        for (String file_name : fileNames) {
            new Mapper(sdf_directory.getAbsolutePath() + File.separator + file_name, outputFolder, mode, fGroupPath, numBins, stepSize, MAX_WRITE).run();
        }

    }
    
    public static String getClassPath(){
        String rval = MapDriver.class.getResource("MapDriver.class").getPath();
        rval = rval.substring(rval.indexOf(":")+1 , rval.indexOf(".jar")+".jar".length());
        return rval;
    }

}
