package map;

import java.util.ArrayList;

/*
 * Problems : how to check / uncheck whether the atom is in a group.
 * 	[solution: Whatever method is called traverse keeps a list of the Atom references returned.
 * 	If the list maps to a functional group, the inAgroup variable is set to true for all atoms in the list.
 * 
 * 
 */

public class Atom {
	private Point3d coordinates;
	private String element;
	private ArrayList<Bond> bonds;
	private boolean inAgroup;
	private int charge;

	public Atom(String element, double[] points, int charge) {
		this.element = element;
		this.setCharge(charge);
		if (points == null) 
			coordinates = null;
		else
			coordinates = new Point3d(points[0], points[1], points[2]);
		bonds = new ArrayList<Bond>();
		inAgroup = false;
	}

	// returns the result of traversing a bond. Note that no hopping takes place, so maybe traverse is a poor name
	public Atom traverse(Bond bond) {
		for (Bond bondTest : bonds) {
			if (bond.equals(bondTest))
				return bondTest.getAtom();
		}
		return null;
	}

	public boolean hasBond(Bond bond) {
		for (Bond bondTest : bonds) {
			if (bond.equals(bondTest))
				return true;
		}
		return false;
	}

	public Bond getBond(int index) {
		if (index >= 0 && index < bonds.size())
			return bonds.get(index);

		return null;
	}

	public int getNumBonds() {
		return bonds.size();
	}

	public String getElement() {
		return element;
	}

	public int getCharge() {
		return charge;
	}

	public Point3d getPoint() {
		return coordinates;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public void setInAgroup(boolean inAgroup) {
		this.inAgroup = inAgroup;
	}

	public void addBond(Bond bond) {
		bonds.add(bond);
	}

	public boolean isInAgroup() {
		return inAgroup;
	}

	public String toString() {
		return "[ " + element + " : " + charge + " : " + coordinates + "]";
	}

}
