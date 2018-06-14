
package primitives;

public class Vector extends Point3D {

	private double length;
	private double length2;

	// Constructors
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		super(x, y, z);
		if (Point3D.ZERO.equals(this))
			throw new IllegalArgumentException("Zero vector is not valid");
		length = _length();
		length2 = _length2();
	}

	public Vector(double x, double y, double z) {
		super(x, y, z);
		if (Point3D.ZERO.equals(this))
			throw new IllegalArgumentException("Zero vector is not valid");
		length = _length();
		length2 = _length2();
	}

	public Vector(Point3D point) {
		super(point);
		if (Point3D.ZERO.equals(this))
			throw new IllegalArgumentException("Zero vector is not valid");
		length = _length();
		length2 = _length2();
	}

	public Vector(Vector other) {
		super(other);
		length = other.length;
		length2 = other.length2;
	}

	// getters/setters
	/**
	 * returns the length of the vector
	 * 
	 * @return
	 */
	public double getLength() {
		return length;
	}
	
	// getters/setters
	/**
	 * returns square of the length of the vector
	 * 
	 * @return
	 */
	public double getLength2() {
		return length2;
	}


	// admin
	/**
	 * Calculates the length of the vector
	 * 
	 * @return
	 */
	private double _length() {
		return super.distance(Point3D.ZERO);
	}
	
	/**
	 * Calculates the length of the vector
	 * 
	 * @return
	 */
	private double _length2() {
		return super.distance2(Point3D.ZERO);
	}

	// overrides
	@Override
	/**
	 * checks whether the given object has the same values as this
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;
		if (this == obj)
			return true;
		return super.equals((Vector) obj);
	}

	/**
	 * Adds a given vector to this
	 * 
	 * @param other
	 * @return
	 */
	public Vector add(Vector other) {
		return new Vector(x.add(other.x), y.add(other.y), z.add(other.z));
	}

	/**
	 * Subtracts a given vector from this
	 * 
	 * @param other
	 * @return
	 */
	public Vector subtract(Vector other) {
		return new Vector(new Coordinate(x.add(-other.x.getNum())), new Coordinate(y.add(-other.y.getNum())),
				new Coordinate(z.add(-other.z.getNum())));
	}

	/**
	 * Returns a vector
	 * 
	 * @param lambda
	 * @return
	 */
	public Vector scale(double lambda) {
		return new Vector(x.mult(lambda), y.mult(lambda), z.mult(lambda));
	}

	/**
	 * returns the dot product of a given vector and this one
	 * 
	 * @param other
	 * @return
	 */
	public double dotProduct(Vector other) {
		return x.mult(other.x) + y.mult(other.y) + z.mult(other.z);
	}

	// Double check result
	/**
	 * Returns the new cross product of the given vector and this one
	 * 
	 * @param other
	 * @return
	 */
	public Vector crossProduct(Vector other) {
		return new Vector(new Coordinate(y.mult(other.z.getNum()) - z.mult(other.y.getNum())),
				new Coordinate(x.mult(other.z.getNum()) - z.mult(other.x.getNum())),
				new Coordinate(x.mult(other.y.getNum()) - y.mult(other.x.getNum())));
	}

	/**
	 * Normalises the vector. That is, creates a new vector of length=1 in the same direction
	 * 
	 * @return new normalised vector
	 */
	public Vector normalize() {
		return this.scale(1 / length);
	}
}
