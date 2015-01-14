/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package map;

/**
 *
 * @author tlong
 */
public class MappedMolecule {
    
    private final String ID;
    private double[] histogram;  //normalized histogram
    private Typed_Angle[] typed_Angles;
    
    public final static String USE_HISTOGRAM_INDICATOR = "h";
    public final static String USE_FGROUP_INDICATOR = "f";
    public final static String ID_TAG = "<Id>";
    public final static String HISTOGRAM_TAG = "<Histogram>";
    public final static String TYPED_ANGLES_START_TAG = "<Typed_angles>";
    public final static String TYPED_ANGLES_END_TAG = "</Typed_angles>";
    
    public MappedMolecule(Molecule molecule){
        ID = molecule.getCID();
        histogram = molecule.getHistogram();
        if(molecule.getTAngles() != null){
            typed_Angles = molecule.getTAngles().toArray(new Typed_Angle[molecule.getTAngleCount()]);
        }
    }
    
    public MappedMolecule(String id){
        ID = id;
    }
    
    public void setHistogram(double[] histogram){
        this.histogram = histogram;
    }
    
    public void setTypedAngles(Typed_Angle[] tAngles){
        this.typed_Angles = tAngles;
    }
    
    public double[] getHistogram(){
        return histogram;
    }
    
    public String getID(){
        return ID;
    }
    
    @Override
    public String toString() {
        String result = ID_TAG + ID + "\n";
        result += histogramToString();
        result += anglesToString();

        return result;
    }
    
    public String histogramToString() {
        String result = "";
        if (histogram != null) {
            result = HISTOGRAM_TAG;
            for(double bin : histogram) {
                result += bin + ",";
            }
        }
        return result + "\n";
    }

    public String anglesToString() {
        String result = "";
        if (typed_Angles != null) {
            result = TYPED_ANGLES_START_TAG + "\n";
            for (Typed_Angle tAngle : typed_Angles) {
                result += tAngle + "\n";
            }
            result += TYPED_ANGLES_END_TAG + "\n";
        }
        
        return result;
    }
}
