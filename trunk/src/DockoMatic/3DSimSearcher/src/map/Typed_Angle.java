package map;

public class Typed_Angle {

    private String type1;
    private String type2;
    private double angle;
    private double magnitudeDiff;

    public Typed_Angle(String type1, String type2, double angle, double magDiff) {
        this.type1 = type1;
        this.type2 = type2;
        this.angle = angle;
        this.magnitudeDiff = magDiff;
    }
    
    //create a typed angle from its string (toString()) representation
    public Typed_Angle(String line){
        String[] fields = line.split(",");
        type1 = fields[0];
        type2 = fields[1];
        angle = Double.parseDouble(fields[2]);
        magnitudeDiff = Double.parseDouble(fields[3]);
    }

    /**
     * Assumes that the centers of the functional group have been adjusted so as
     * to be relative to the molecules center, which is (0,0,0) Calculates the
     * angle between two vectors using the dot product
     *
     * @param vector_1 the head of a vector that originates at the origin
     * @param vector_2 the head of a vector that originates at the origin
     * @return the angle between vector_1 and vector_2
     */
    public Typed_Angle(FunctionalGroup fgroup_1, FunctionalGroup fgroup_2) {
        //angle between two vectors a and b = inverseCosine((a.b)/|a||b|)
        Point3d vector_1, vector_2;
        vector_1 = fgroup_1.getCoordinates();
        vector_2 = fgroup_2.getCoordinates();

        this.type1 = fgroup_1.getType();
        this.type2 = fgroup_2.getType();
        if (vector_1.isEqual(vector_2)) {
            this.magnitudeDiff = this.angle = 0.0;
        } else {
            this.angle = Math.acos(vector_1.dotProduct(vector_2) / (vector_1.getMagnitude() * vector_2.getMagnitude()));
            this.angle = (Double.isNaN(this.angle) ? 0.0 : this.angle);
            this.magnitudeDiff = Math.abs(vector_1.getMagnitude() - vector_2.getMagnitude());
        }

    }

    public double compare2(Typed_Angle other, double gamma) {
        double result;
        if (compatibleTypes(other)) {
            result = gamma * Math.PI - Math.abs(angle - other.getAngle());
            result /= Math.PI;
            //result *= (1 - Math.abs(magnitudeDiff - other.getMagnitudeDiff())); 
        } else {
            result = 0.0; //incompatible types
        }
        return result;
    }

    //could be done in one line but this is easier to read
    public boolean compatibleTypes(Typed_Angle other) {
        boolean rval = false;
        if (type1.equals(other.getType1())) {
            if (type2.equals(other.type2)) {
                rval = true;
            }
        } else if (type1.equals(other.type2)) {
            if (type2.equals(other.type1)) {
                rval = true;
            }
        }

        return rval;
    }

    @Override
    public String toString() {
        return type1 + "," + type2 + "," + angle + "," + magnitudeDiff;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getMagnitudeDiff() {
        return magnitudeDiff;
    }

    public void setMagnitudeDiff(double magnitudeDiff) {
        this.magnitudeDiff = magnitudeDiff;
    }

}
