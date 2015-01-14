package map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class Molecule {

    private final String ID;
    private double[] histogram;  //normalized histogram
    private ArrayList<Typed_Angle> typed_Angles;
    private final ArrayList<Atom> atoms;
    private Point3d center;

    /**
     * Create a new molecule from the pubChem database
     *
     * @param CID the identification number for the molecule
     * @param complete whether this molecule will
     */
    public Molecule(String ID) {
        this.ID = ID;
        atoms = new ArrayList<Atom>();
    }

    public void addAtom(Atom toAdd) {
        atoms.add(toAdd);
    }

    public int getNumAtoms() {
        return atoms.size();
    }

    public String getCID() {
        return ID;
    }

    public Atom getAtom(int index) {
        return atoms.get(index);
    }

    public void calcCenter() {
        double x = 0;
        double y = 0;
        double z = 0;
        for (Atom tmp : atoms) {
            x += tmp.getPoint().getX();
            y += tmp.getPoint().getY();
            z += tmp.getPoint().getZ();
        }
        this.center = new Point3d(x / atoms.size(), y / atoms.size(), z / atoms.size());
    }
    
    @Override
    public String toString() {
        return "CID: " + ID;
    }

    public double[] getHistogram() {
        return histogram;
    }

    public ArrayList<FunctionalGroup> checkForChargedAtoms() {
        ArrayList<FunctionalGroup> list = new ArrayList<FunctionalGroup>();
        for (Atom adam : atoms) {
            if (adam.getCharge() < 0) {
                list.add(new FunctionalGroup("anion", adam.getPoint()));
            } else if (adam.getCharge() > 0) {
                list.add(new FunctionalGroup("cation", adam.getPoint()));
            }
        }
        return list;
    }

	// assumes that a function group is a directed graph, takes the head of the
    // graph
    public FunctionalGroup checkForGroup(FunctionalGroup Fgroup) {
        FunctionalGroup funcGroup = null;
        for (Atom adam : atoms) {
            if (Fgroup.getHead().getElement().equals(adam.getElement())) {
                ArrayList<Atom> tmpList = new ArrayList<Atom>();
                //use different logic for rings so that we don't need to enumerate all possible permutations of a ring
                if (foundGroup(Fgroup.getHead(), adam, tmpList)) {
                    double x = 0;
                    double y = 0;
                    double z = 0;

                    for (Atom tmp : tmpList) {
                        x += tmp.getPoint().getX();
                        y += tmp.getPoint().getY();
                        z += tmp.getPoint().getZ();
                    }

                    // since a molecule might have multiple instances of a functional group
                    funcGroup = new FunctionalGroup(Fgroup.getType());
                    funcGroup.setCenter(x / tmpList.size(), y / tmpList.size(), z / tmpList.size());

                }
                Fgroup.reverseFlags(); // clear the 'node has been visited' flags for the functional group
            }
        }
        return funcGroup;
    }

    // recursive graph search .. depth first
    public boolean foundGroup(Atom func, Atom test, ArrayList<Atom> list) {
        list.add(test);
        int numBonds = func.getNumBonds();
        Atom temp;
        if (numBonds == 0) // base case
        {
            return true;
        }
        if (func.isInAgroup()) // for the functional groups, this variable stores whether the atom has been traversed
        {
            return true;
        }

        func.setInAgroup(true); // set the visited flag to true else
        Bond[] outgoing = new Bond[numBonds]; // create list of outgoing bonds of function group
        for (int i = 0; i < numBonds; i++) {
            outgoing[i] = func.getBond(i);
        }

        boolean hasAllBonds = true;

        for (int i = 0; i < numBonds; i++) {
            hasAllBonds = hasAllBonds && test.hasBond(outgoing[i]);
        }

        if (hasAllBonds) {
            for (int i = 0; i < numBonds; i++) {
                temp = test.traverse(outgoing[i]);
                hasAllBonds = hasAllBonds && foundGroup(func.traverse(outgoing[i]), temp, list); // short Circuit == awesome
            }
        }
        return hasAllBonds;
    }

    public ArrayList<Double> getMeasurements(int numMeasures) {

        // if numMeasures < 0, use all possible measurements
        if (numMeasures < 0) {
            numMeasures = atoms.size() * (atoms.size() + 1) / 2; // n + n-1 + n-2 + .. + 1 = n(n+1)/2
        }

		// to ensure uniqueness of sampling points, remove each sampled pair from the list after being used
        // to clarify, possMeasurements will contain all possible neighbor - neighbor pairs using the index of
        // the atoms in the molecule (atoms Collection).
        ArrayList<Integer[]> possPairs = generatePossiblePairs(atoms.size());

        // generate the measurements
        ArrayList<Double> results = new ArrayList<Double>();

        int count = 0;
        Random rand = new Random();
        Point3d point1, point2;
        int index;

        while (!possPairs.isEmpty() && count < numMeasures) {
            index = rand.nextInt(possPairs.size());
            point1 = atoms.get(possPairs.get(index)[0]).getPoint();
            point2 = atoms.get(possPairs.get(index)[1]).getPoint();
            results.add(point1.getEuclideanDistance(point2));
            possPairs.remove(index); // remove the point to ensure distinct sampling
            count++;
        }

        return results;
    }

    /**
     * Creates a histogram from sample measurements.
     *
     * The histogram will consist of numBins bins, each of which will have a
     * width of stepSize. In each bin will be an integer indicating the number
     * of sample measurements that were within the bins range. The range of the
     * first bin, histogram[0], is (0,stepSize]. Otherwise, the range of
     * histogram[i] is ( uB[i-1], uB[i-1]+stepSize], where uL[i-1] = upper bound
     * of histogram[i-1].
     *
     * Last step is to normalize the histogram
     *
     * @param measurements measurements to be binned so as to create a histogram
     * distribution
     * @param numBins the number of bins to be used for the histogram
     * @param stepSize the size of each bin in the histogram
     */
    public void createHistogram(ArrayList<Double> measurements, int numBins, double stepSize) {
        int[] curr_histogram = new int[numBins];

        Collections.sort(measurements); // important step
        int index = 0;
        double currBinsUpperLimit = stepSize;

        for (Double value : measurements) {
            // the measurement is outside of the range
            if (index == numBins) {
                curr_histogram[numBins - 1]++; // the last bin will hold all values greater than the max value - stepSize (inaccurate, thus error)
            } else {
                if (value <= currBinsUpperLimit) {
                    curr_histogram[index]++;
                } else {
                    currBinsUpperLimit += stepSize;
                    index++;
                }
            }
        }

        this.histogram = new double[curr_histogram.length];

        //normalize histogram
        if (measurements.isEmpty()) {
            System.err.println("WARNING: Molecule " + ID + " is thought to have no measurements.");
        } else {
            for (int i = 0; i < histogram.length; i++) {
                this.histogram[i] = ((double) curr_histogram[i]) / measurements.size();
            }
        }

    }

    public void generateFunctionalGroups(HashSet<FunctionalGroup> groups) {
        this.typed_Angles = new ArrayList<Typed_Angle>();
        Object[] array = groups.toArray();

        for (int i = 0; i < array.length; i++) {
            ((FunctionalGroup) array[i]).recenter(this.center); //change fgroups so as to make vector starting from
        }																//the molecules center

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                this.typed_Angles.add(new Typed_Angle((FunctionalGroup) array[i], (FunctionalGroup) array[j]));
            }
        }
    }

    public void setHistogram(double[] hist) {
        this.histogram = hist;
    }

    /*
     * This method will generate an upper triangular matrix that represents all
     * possible uniques pairs of the numbers 0 - size-1. These pairs will be
     * used as the basis of measurements which will in turn be used to build a
     * shape distribution for the molecule.
     */
    public static ArrayList<Integer[]> generatePossiblePairs(int size) {
        ArrayList<Integer[]> possPairs = new ArrayList<Integer[]>();
        Integer[] tmp;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                tmp = new Integer[2];
                tmp[0] = i;
                tmp[1] = j;
                possPairs.add(tmp);
            }
        }

        return possPairs;
    }

    public int getTAngleCount() {
        if (this.typed_Angles == null) {
            return 0;
        } else {
            return typed_Angles.size();
        }
    }

    /* 
     * Assumption: The size of this.typed_angles <= mol.typed_angles.  This must be enforced elsewhere (in DemoSearcher)
     */
    public double compareFunctionGroups(Molecule mol, double gamma) {
        double results = 0.0;
        ArrayList<Typed_Angle> others = new ArrayList<Typed_Angle>(mol.getTAngles());  //make copy so we can remove from it

        double denominator = gamma * mol.getTAngleCount();
        double tmpMax, tmpVal;
        int indexOfMax = 0;

        if (denominator == 0) {
            return 0.0;
        }

        for (Typed_Angle angle : this.typed_Angles) {
            tmpMax = -1.0;
            for (int i = 0; i < others.size(); i++) {
                tmpVal = angle.compare2(others.get(i), gamma);
                if (tmpVal > tmpMax) {
                    tmpMax = tmpVal;
                    indexOfMax = i;
                }
            }
            results += tmpMax;
            if (tmpMax > 0.0) {
                others.remove(indexOfMax);  //ensure that an angle is only compared one time.
            }
        }

        return results / denominator;
    }

    public ArrayList<Typed_Angle> getTAngles() {
        return this.typed_Angles;
    }

    public void addTyped_Angle(Typed_Angle angle) {
        if (this.typed_Angles == null) {
            typed_Angles = new ArrayList<Typed_Angle>();
        }
        typed_Angles.add(angle);
    }
}
