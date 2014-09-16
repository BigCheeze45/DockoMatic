package map;


/*
 * It is very important that the graph representation of the functional group is directed.
 * That is, if two atoms are bonded only one of them should have a Bond for that connection.
 * If this is not the case the functionGroup search in the Molecule class is difficult and might even break.
 * Also, one should be able to get to any node in the group if they start from the head of the functionalGroup.
 *  
 */

public class FunctionalGroup implements Comparable<FunctionalGroup>{
	private String type;
	private Atom head;
	private Point3d coordinates;

	/**
	 * Creates a new functional group
	 * @param fType 
	 * 
	 * @param type
	 *            the name of the functional group
	 * @param head
	 *            the head of a graph which represents the chemical structure of
	 *            the functional group
	 */
	public FunctionalGroup(String fType) {
		this.type = fType.toUpperCase();
	}
	
	public FunctionalGroup(Atom head, String fType) {
		this.head = head;
		this.type = fType.toUpperCase();
	}
	public FunctionalGroup(String fType, Point3d center){
		this.coordinates = center;
		this.type = fType.toUpperCase();
	}

	public Atom getHead() {
		return head;
	}

	public String getType() {
		return type;
	}

	// TODO make this value relative to the center of the molecule from which it
	// came ...
	public void setCenter(double x, double y, double z) {
		coordinates = new Point3d(x, y, z);
	}

	public Point3d getCoordinates() {
		return coordinates;
	}

	public void reverseFlags() {
		reverseFlags(head);
	}

	public void reverseFlags(Atom atom) {
		int numBonds = atom.getNumBonds();

		// base case
		if (numBonds == 0)
			return;

		// check if the flag has already been changed
		if (!atom.isInAgroup()) {
			return;
		}

		atom.setInAgroup(false);

		// create list of outgoing bonds of function group
		Bond[] outgoing = new Bond[numBonds];
		for (int i = 0; i < numBonds; i++)
			outgoing[i] = atom.getBond(i);

		for (int i = 0; i < numBonds; i++)
			reverseFlags(atom.traverse(outgoing[i]));

	}

	public String printGroup() {
		return type + "," + coordinates.toPrintCSV() + "\n";
	}
	
	public String toString() {
		return type;
	}
	
	public double getDistFrom(FunctionalGroup fg){
		return coordinates.getEuclideanDistance(fg.getCoordinates());
	}

	@Override
	public int compareTo(FunctionalGroup arg0) {
		if(type.equals(arg0.getType()) && coordinates.isEqual(arg0.getCoordinates()))
			return 0;
		return 1;
	}
	
	//Modify the coordinates of this functional group so that they are as if the
	//center of the molecule which contains it was (0,0,0)
	public void recenter(Point3d origin){
		coordinates.adjustX(-origin.getX());
		coordinates.adjustY(-origin.getY());
		coordinates.adjustZ(-origin.getZ());
	}

}
