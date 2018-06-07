/**
 * 
 */
package primitives;

import java.lang.Math;

public class Point3D extends Point2D {
	public static final Point3D ZERO = new Point3D(0, 0, 0);
	/**
	 * 
	 */
	protected Coordinate z;

	// Constructors
	public Point3D(Point3D other) {
		super(other);
		z = new Coordinate(other.z);
	}

	public Point3D(double x, double y, double z) {
		super(x, y);
		this.z = new Coordinate(z);
	}

	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		super(x, y);
		this.z = new Coordinate(z);
	}

	// get/set
	/**
	 * return the Z value of the point
	 * 
	 * @return
	 */
	public Coordinate getZ() {
		return z;
	}

	// Overrides
	@Override
	/**
	 * Checks if the given object is a Point3D and if the point contains the same
	 * values as this
	 * 
	 * @return
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		if (this == obj)
			return true;
		Point3D other = (Point3D) obj;
		return z.equals(other.z) && super.equals(other);
	};

	@Override
	/**
	 * returns the Point in string form
	 * 
	 * @return
	 */
	public String toString() {
		return "(" + x.toString() + ", " + y.toString() + ", " + z.toString() + ")";
	}

	// operators
	/**
	 * Subtracts the given point from this and retuns a Vector
	 * 
	 * @param other
	 * @return
	 */
	public Vector subtract(Point3D other) {
		return new Vector(x.subtract(other.x), y.subtract(other.y), z.subtract(other.z));
	}

	/**
	 * Adds a given vector and returns a point
	 * 
	 * @param other
	 * @return
	 */
	public Point3D add(Vector other) {
		return new Point3D(this.x.add(other.x), this.getY().add(other.y), this.z.add(other.z));
	}

	/**
	 * Calculates the distance from a give point to this one.
	 * 
	 * @param other
	 * @return
	 */
	public double distance(Point3D other) {
		return Math.sqrt(distance2(other));
	}

	/**
	 * Calculates the distance from a given point but without the square root
	 * @param other
	 * @return
	 */
	public double distance2(Point3D other) {
		double dx = x.subtract(other.x);
		double dy = y.subtract(other.y);
		double dz = z.subtract(other.z);
		return dx * dx + dy * dy + dz * dz;
	}
}
