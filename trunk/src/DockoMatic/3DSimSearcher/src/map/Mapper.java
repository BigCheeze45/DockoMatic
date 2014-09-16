package map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Calendar;
import org.openide.util.Exceptions;

/**
 * This class is responsible for mapping the small molecules within a single
 * .sdf file to a characteristic form which is more readily comparable to
 * another member of the same form. This mapping is done by creating a shape
 * distribution for each molecule (as detailed by Robert Osada) or by creating a
 * set of Typed_Angles for each molecule. A typed_angle is a class which
 * attempts to use the angles between a molecule's pharmacophore features to
 * characterize it. This class should be used in tandem with a driver class and
 * some form of batch submission utility (like swarm).
 *
 * @author tlong
 *
 */
public class Mapper {

    public static final int ALL = -1;
    public static final int NUM_SKIP_LINES = 2;
    private final String sdfPath;					   //path to the .sdf file which is to be parsed
    private String outputPath;				   //path for the output file
    private final String mode;						//the basis of the mapping, h (histogram) or f (functional groups)
    private final String funcGroupPath;				//path for the file which defines functional groups (as acyclic graphs)
    private final int numBins;						//the number of bins to use for the histogram
    private final double stepSize;					//the width of each bin
    private final int max2write;						//the maximum number of molecules to parse before writing to a file (in case of failure)

    private ArrayList<FunctionalGroup> fgroups;			//a list of functional groups which can be recognized
    public final static String MAPPED_SUFFIX = ".xfrm";
    private final static String SDF_DELIM = "$$$$";

    public Mapper(String sdfPath, String outPath, String mode, String fGrpPth,
            int numBins, double stepSize, int maxWrite) {

        this.sdfPath = sdfPath;
        this.outputPath = outPath + File.separator;

        String[] fields = sdfPath.split(File.separator);
        outputPath += fields[fields.length - 1];
        outputPath = outputPath.substring(0, outputPath.indexOf(".sdf"));
        outputPath += MAPPED_SUFFIX;

        this.mode = mode;
        this.funcGroupPath = fGrpPth;
        this.numBins = numBins;
        this.stepSize = stepSize;
        max2write = maxWrite;
    }
    
    public void run(){
        Calendar cal = Calendar.getInstance();
        System.out.println("Beginning work on " + sdfPath + " at " + cal.getTime());
        
        ArrayList<Molecule> moleculeList = new ArrayList<Molecule>();

        if (mode.contains("f")) {  //parse file which specifies the functional groups once
            fgroups = Mapper.generateFgroups(funcGroupPath);
        }

        BufferedReader bread = null;
        
        try{
            bread = new BufferedReader(new FileReader(new File(sdfPath)));

            String line;
            while ((line = bread.readLine()) != null) {
                if (line.contains(SDF_DELIM)) {
                    line = bread.readLine();
                    if(line == null){
                        break;
                    }else{
                        Long cid = Long.parseLong(line);
                        for (int i = 0; i < NUM_SKIP_LINES; i++) {
                            bread.readLine();
                        }
                        Molecule mol = parseMolecule(bread,cid);
                        moleculeList.add(mol);
                    }
                }
                if (moleculeList.size() >= max2write) {  //prevent memory issues ... I hope
                    cal = Calendar.getInstance();
                    System.out.println("Wrote " + moleculeList.size() + " molecules from file " + sdfPath + " at " + cal.getTime());
                    writeToFile(moleculeList);
                    moleculeList.clear();
                }
            }
        }catch(IOException ex){
            Exceptions.printStackTrace(ex);
        }finally{
            try{
                if(bread != null){
                    bread.close();
                }
            }catch(IOException ex){
                Exceptions.printStackTrace(ex);
            }
        }

        cal = Calendar.getInstance();
        System.out.println("Completed processing file " + sdfPath + " at " + cal.getTime());
        writeToFile(moleculeList);
    }
    
    /*
     Call this method to parse a molecule from an sdf file.  For sdf files containing lots of molecules,
    wrap this method and call it only after encountering the delimiter.  Pass it the next line and the 
    BufferedReader.
    */
    public static Molecule parseMolecule(BufferedReader bread, long cid) throws IOException{
        
        String line = bread.readLine();
        
        Molecule molecule = new Molecule(cid);
        int numAtomLines;
        int numConnectLines;

        String[] data = line.trim().split("\\s+");
        int[] safeData = parseAndFix(data);
        numAtomLines = safeData[0];
        numConnectLines = safeData[1];

        // construct atoms with coordinates, type, and index
        for (int i = 0; i < numAtomLines; i++) {
            molecule.addAtom(createAtom(bread.readLine().trim().split("\\s+")));
        }

        // redundancy in the bonds, can we get around this?
        for (int j = 0; j < numConnectLines; j++) {
            createConnections(molecule, bread.readLine().trim().split("\\s+"));
        }

        line = bread.readLine().trim();
        if (line.contains("M  CHG")) {
            String[] record = line.trim().split("\\s+");
            int numAtoms = Integer.parseInt(record[2]);
            int start = 3;
            for (int i = start; i < numAtoms * 2 + start; i += 2) {
                molecule.getAtom(Integer.parseInt(record[i]) - 1).setCharge(Integer.parseInt(record[i + 1]));
            }
        }
        
        return molecule;
    }

    private void writeToFile(ArrayList<Molecule> molecules) {

        File file = new File(outputPath);
        boolean append = file.exists();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, append));

            for (Molecule molecule : molecules) {
                transform(molecule);
                MappedMolecule mapMol = new MappedMolecule(molecule);
                writer.write(mapMol.toString());
            }

            writer.flush();
            writer.close();

        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    /**
     * Test: make functional groups a directed graph
     *
     * @param list
     * @return
     */
    public static ArrayList<FunctionalGroup> generateFgroups(String fileName) {
        // note that coordinates do not matter for my testing purposes
        ArrayList<FunctionalGroup> list = null;
        
        File file = new File(fileName);
        if(file.exists()){
            list = new ArrayList<FunctionalGroup>();

            try {
                BufferedReader bread = new BufferedReader(new FileReader(new File(fileName)));
                String line;
                while ((line = bread.readLine()) != null) {
                    if (line.equals("")) {
                        continue;
                    }
                    String[] record = line.trim().split("\\s+");

                    String fType = record[0];
                    int numAtomLines = Integer.parseInt(record[1]);
                    int numConnectLines = Integer.parseInt(record[2]);

                    ArrayList<Atom> atomList = new ArrayList<Atom>();

                    for (int i = 0; i < numAtomLines; i++) {
                        atomList.add(createAtom(bread.readLine().trim().split("\\s+")));
                    }
                    for (int j = 0; j < numConnectLines; j++) {
                        record = bread.readLine().trim().split("\\s+");

                        int fromAtomIndex = Integer.parseInt(record[0]) - 1;
                        int toAtomIndex = Integer.parseInt(record[1]) - 1;
                        int type = Integer.parseInt(record[2]);

                        atomList.get(fromAtomIndex).addBond(new Bond(type, atomList.get(toAtomIndex)));
                    }

                    list.add(new FunctionalGroup(atomList.get(0), fType));
                    atomList.clear(); //empty the list
                }
                bread.close();
            } catch (FileNotFoundException e) {
                System.err.println(e.getLocalizedMessage());
            } catch (IOException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
        return list;
    }

    public static Atom createAtom(String[] record) {
        double[] points = new double[3];
        if (record[0].equals("null") || record[1].equals("null") || record[2].equals("null")) {
            points = null;
        } else {
            points[0] = Double.parseDouble(record[0]); // X Coordinate
            points[1] = Double.parseDouble(record[1]); // Y Coordinate
            points[2] = Double.parseDouble(record[2]); // Z Coordinate
        }

        String atomType = record[3]; // Element Symbol

        int charge = Integer.parseInt(record[5]); // Atom Charge

        if (charge <= 0 || charge > 7) {
            charge = 0;
        } else {
            charge = 4 - charge; // look at the SDF format for an explanation
        }

        return new Atom(atomType, points, charge);
    }

    public static void createConnections(Molecule currentMolecule, String[] record) {

        int[] data = parseAndFix(record);
        int fromAtomIndex = data[0] - 1;
        int toAtomIndex = data[1] - 1;
        int type = data[2];

        currentMolecule.getAtom(toAtomIndex).addBond(new Bond(type, currentMolecule.getAtom(fromAtomIndex)));
        currentMolecule.getAtom(fromAtomIndex).addBond(new Bond(type, currentMolecule.getAtom(toAtomIndex)));

    }

    public static void prepareMolecule(Molecule molecule, int numBins, double stepSize, ArrayList<FunctionalGroup> fGroups) {
        molecule.createHistogram(molecule.getMeasurements(-1), numBins, stepSize);
        molecule.calcCenter();

        if(fGroups != null){
            HashSet<FunctionalGroup> tmpGroups = new HashSet<FunctionalGroup>(); // hold the intermediate funcGroups for the atom
            tmpGroups.addAll(molecule.checkForChargedAtoms()); // check for anions and cations
            for (FunctionalGroup funcG : fGroups) {
                FunctionalGroup tmp = molecule.checkForGroup(funcG);
                if (tmp != null) {
                    tmpGroups.add(tmp);
                }
            }
            molecule.generateFunctionalGroups(tmpGroups);
        }
    }

    /**
     * This method is used to account for deficiencies in the SDF format. The
     * format, or at least its implementation by PubChem, seems to be XX YY. If
     * X is 3 digits, it becomes XXXYY and we need to do a little work in order
     * to decide how that XXXYY or XXXYYY number should be split. This method
     * does that work and returns the two numbers. To do this, we note that for
     * the connection table, the left value is always going to be smaller due to
     * format specifications. For the atom count and connection table, we know
     * that it is impossible for a group of N connected atoms to have less than
     * N-1 connections. Thus, the only way that the # of connections can be 2
     * digits and N > 99 would be if N = 100 and # connections = 99. This allows
     * us to determine how the number should be split into two.
     *
     * @param tokenRow the row of data to parse, split into an array
     * @return two integers representing the counts
     */
    public static int[] parseAndFix(String[] tokenRow) {
        int[] result = new int[3];

        result[0] = Integer.parseInt(tokenRow[0]);
        if (result[0] < 1000) {
            result[1] = Integer.parseInt(tokenRow[1]);
            result[2] = Integer.parseInt(tokenRow[2]);
        } else { // both could be 3 digits or one could be 2 and the other 3 OR one could be 1 and the other 3
            int first, second;

            if (tokenRow[0].length() == 6) {
                first = Integer.parseInt(tokenRow[0].substring(0, 3));
                second = Integer.parseInt(tokenRow[0].substring(3));

            } else if (tokenRow[0].length() == 4) {  //connection table, atom Y connected to atom YYY
                first = Integer.parseInt(tokenRow[0].substring(0, 1));
                second = Integer.parseInt(tokenRow[0].substring(1));

            } else { // guess that the first is 2 and second is 3
                first = Integer.parseInt(tokenRow[0].substring(0, 2));
                second = Integer.parseInt(tokenRow[0].substring(2));

                if (first == 10 && second == 99) { // 099 -> 99
                    first = 100;
                }
            }

            result[0] = first;
            result[1] = second;
            result[2] = Integer.parseInt(tokenRow[1]);
        }

        return result;
    }

    private void transform(Molecule molecule) {
        if (mode.contains(MappedMolecule.USE_HISTOGRAM_INDICATOR)) {
            molecule.createHistogram(molecule.getMeasurements(ALL), numBins, stepSize);
        }
        if (mode.contains(MappedMolecule.USE_FGROUP_INDICATOR)) {
            HashSet<FunctionalGroup> tmpGroups = new HashSet<FunctionalGroup>(); //hold the intermediate funcGroups for the atom
            molecule.calcCenter();  								//calculate the center of the molecule
            tmpGroups.addAll(molecule.checkForChargedAtoms());  //check for anions and cations
            for (FunctionalGroup fgroup : fgroups) {
                FunctionalGroup tmp = molecule.checkForGroup(fgroup);
                if (tmp != null) {
                    tmpGroups.add(tmp);
                }
            }
            molecule.generateFunctionalGroups(tmpGroups);
        }
    }

}
