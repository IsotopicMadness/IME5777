/**
 * 
 */
package primitives;

import java.lang.Math;

public class Point3D extends Point2D {
	public static final Point3D ZERO = new Point3D(0,0,0);
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
	public Coordinate getZ() {
		return z;
	}

	/**
	 * this functions get used when the Vector is required to return a Point3D
	 */

	// Overrides
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		if (this == obj)
			return true;
		Point3D other = (Point3D) obj;
		return this.z.equals(other.z) && super.equals(other);
	};

	@Override
	public String toString() {
		return "(" + x.toString() + ", " + y.toString() + ", " + z.toString() + ")";
	}

	// operators
	public Vector subtract(Point3D other) {
		return new Vector(x.subtract(other.x), y.subtract(other.y), z.subtract(other.z));
	}

	public Point3D add(Vector other) {
		return new Point3D(this.x.add(other.x), this.getY().add(other.y), this.z.add(other.z));
	}

	// public Point3D subtract(Point3D other) {
	// return new Point3D(new
	// Coordinate(this.getX().subtract(other.getX().getNum())),
	// new Coordinate(this.getY().subtract(other.getY().getNum())),
	// new Coordinate(this.z.subtract(other.getZ().getNum())));
	// }

	public double distance(Point3D other) {
		return Math.sqrt(distance2(other));
	}

	public double distance2(Point3D other) {
		double dx = x.subtract(other.x);
		double dy = y.subtract(other.y);
		double dz = z.subtract(other.z);
		return dx * dx + dy * dy + dz * dz;
	}
}
