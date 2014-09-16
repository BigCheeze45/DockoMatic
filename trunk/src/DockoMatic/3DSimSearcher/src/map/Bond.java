package map;
public class Bond {
	private int type; // single, double, triple bond
	private Atom target;

	public Bond(int type, Atom target) {
		this.type = type;
		this.target = target;
	}

	public Atom getAtom() {
		return target;
	}

	public int getType() {
		return type;
	}

	/**
	 * Two bonds are equal if they have the same type AND target or
	 * if they have the same target and one of the types is equal to 0.
	 * @param arg0
	 * @return
	 */
	public boolean equals(Bond arg0) {
		boolean equal = (arg0.getAtom().getElement() + "," + arg0.getType()).equals(target.getElement() + "," + type); //same target and type
		equal = equal || ((arg0.getAtom().getElement()).equals(target.getElement()) && (arg0.type==0 || type==0)); //same target and a wildcard type
		return equal;
	}

	public String toString() {
		return "type: " + type + ", to: " + target.getElement();
	}
}
