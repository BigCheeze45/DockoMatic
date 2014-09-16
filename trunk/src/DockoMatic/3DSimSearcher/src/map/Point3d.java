package map;

public class Point3d {
	private double x, y, z;
	private final double THRESH_HOLD = .00001;

	public Point3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
	
	//Assumes that the point represents the head of a 3d vector which
	//originates at (0,0,0)
	public double dotProduct(Point3d point){
		return x*point.getX() + y*point.getY() + z*point.getZ();
	}
	
	//Assumes that the point represents the head of a 3d vector which
	//originates at (0,0,0)
	public double getMagnitude(){
		return Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2));
	}
	
	public void adjustX(double addTo){
		x += addTo;
	}
	public void adjustY(double addTo){
		y += addTo;
	}
	public void adjustZ(double addTo){
		z += addTo;
	}
	

	public double getEuclideanDistance(Point3d arg) {
		double distance = 0.0;

		distance = Math.pow(x - arg.getX(), 2);
		distance += Math.pow(y - arg.getY(), 2);
		distance += Math.pow(z - arg.getZ(), 2);

		return Math.sqrt(distance);

	}

	public boolean isEqual(Point3d pnt) {
		double temp;

		temp = Math.abs(this.x - pnt.getX());
		temp += Math.abs(this.y - pnt.getY());
		temp += Math.abs(this.z - pnt.getZ());
		if (temp > THRESH_HOLD)
			return false;
		return true;
	}

	public String toString() {
		return "(" + x + " " + y + " " + z + ")";
	}

	public String toPrintCSV() {
		return x + "," + y + "," + z;
	}

}
