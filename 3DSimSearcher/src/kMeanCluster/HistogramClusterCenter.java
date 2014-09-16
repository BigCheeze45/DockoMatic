package kMeanCluster;

import map.MappedMolecule;

public class HistogramClusterCenter implements ClusterCenter {

    
    private final static String CARDINALITY_TAG = "<Size>";
    private double[] histogram;
    private int numMembers;

    public HistogramClusterCenter(String line) {
        String tmp = line.substring(line.indexOf(CARDINALITY_TAG) + CARDINALITY_TAG.length());
        numMembers = Integer.parseInt(tmp.substring(0,tmp.indexOf(MappedMolecule.HISTOGRAM_TAG)));
        String hist_line = line.substring(line.indexOf(MappedMolecule.HISTOGRAM_TAG) + MappedMolecule.HISTOGRAM_TAG.length());
        String[] hist_values = hist_line.split(",");
        histogram = new double[hist_values.length];

        for (int i = 0; i < histogram.length; i++) {
            histogram[i] = Double.parseDouble(hist_values[i]);
        }
    }
    
    public HistogramClusterCenter(){
        numMembers = 0;
    }
    
    public HistogramClusterCenter(double[] histogram){
        this.histogram = histogram;
        numMembers = 0;
    }

    @Override
    public void averageValues() {
        for (int i = 0; i < histogram.length; i++) {
            histogram[i] /= Math.max(numMembers, 1);
        }
    }

    @Override
    public double getDistanceFrom(MappedMolecule mapMol, String dTest) {
        double[] tmp_hist = mapMol.getHistogram();
        return calcDistTest(tmp_hist, this.histogram, dTest);
    }

    @Override
    public void updateTotalWith(MappedMolecule mapMol) {
        double[] tmp_hist = mapMol.getHistogram();
        
        if(histogram == null){
            histogram = new double[tmp_hist.length];
            System.arraycopy(tmp_hist, 0, histogram, 0, histogram.length);
        }else{
            for (int i = 0; i < histogram.length; i++) {
                histogram[i] += tmp_hist[i];
            }
        }
        numMembers++;
    }
    
    @Override
    public void mergeWith(ClusterCenter center) {
        
        try{
            HistogramClusterCenter histCenter = (HistogramClusterCenter) center;   
            if(histogram == null){
                histogram = histCenter.histogram;
            }else{
                for(int i = 0; i < histogram.length; i++){
                    histogram[i] += histCenter.histogram[i];
                }
            }
            
            numMembers += histCenter.numMembers;
            
        }catch(ClassCastException ex){
            System.err.println(ex.getLocalizedMessage());
        }
    }
    
    @Override
    public String toString() {
        String result = CARDINALITY_TAG + numMembers + MappedMolecule.HISTOGRAM_TAG;
        for (int i = 0; i < histogram.length; i++) {
            result += histogram[i] + ",";
        }
        return result;
    }

    public static double chiSquare(double a, double b) {
        double result = 0.0;
        if ((a + b) != 0.0) {
            result = ((a - b) * (a - b)) / (a + b);
        }
        return result;
    }

    public static double pdfL1(double a, double b) {
        double result;
        if (a > b) {
            result = a - b;
        } else {
            result = b - a;
        }
        return result;
    }

    public static double pdfL2(double a, double b) {
        return (a - b) * (a - b);
    }

    public static double rootOfProduct(double a, double b) {
        return Math.sqrt(a * b);
    }

    public static double calcDistTest(double[] dist1, double[] dist2, String dtest) {
        int x;
        double sum = 0.0;

        if (dist1.length != dist2.length) {
            sum = 0.0;
        } else if (dtest.equals("chiSquare")) {
            for (x = 0; x < dist1.length; x++) {
                sum += chiSquare(dist1[x], dist2[x]);
            }
            sum /= 2.0;
        } else if (dtest.equals("pdfL1")) {
            for (x = 0; x < dist1.length; x++) {
                sum += pdfL1(dist1[x], dist2[x]);
            }
            sum /= 2.0;
        } else if (dtest.equals("pdfL2")) {
            for (x = 0; x < dist1.length; x++) {
                sum += pdfL2(dist1[x], dist2[x]);
            }
            sum = Math.sqrt(sum);
        } else if (dtest.equals("rootOfProduct")) {
            for (x = 0; x < dist1.length; x++) {
                sum += rootOfProduct(dist1[x], dist2[x]);
            }
            sum = 1 - sum;
        }

        return sum;
    }

}
