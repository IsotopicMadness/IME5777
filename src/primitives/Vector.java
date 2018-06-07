
package primitives;

public class Vector extends Point3D {

	private double length;

	// Constructors
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		super(x, y, z);
		if (Point3D.ZERO.equals(this))
			throw new IllegalArgumentException("Zero vector is not valid");
		length = _length();
	}

	public Vector(double x, double y, double z) {
		super(x, y, z);
		if (Point3D.ZERO.equals(this))
			throw new IllegalArgumentException("Zero vector is not valid");
		length = _length();
	}

	public Vector(Point3D point) {
		super(point);
		if (Point3D.ZERO.equals(this))
			throw new IllegalArgumentException("Zero vector is not valid");
		length = _length();
	}

	public Vector(Vector other) {
		super(other);
		length = other.getLength();
	}

	// getters/setters
	public double getLength() {
		return length;
	}

	// admin
	private double _length() {
		return super.distance(Point3D.ZERO);
	}

	// overrides
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof Vector))
			return false;
		return super.equals((Vector) obj);
	}

	// operations
	public Vector add(Vector other) {
		return new Vector(x.add(other.x), y.add(other.y), z.add(other.z));
	}

	public Vector subtract(Vector other) {
		return new Vector(new Coordinate(getX().add(-other.getX().getNum())),
				new Coordinate(getY().add(-other.getY().getNum())), new Coordinate(getZ().add(-other.getZ().getNum())));
	}

	public Vector scale(double lambda) {
		return new Vector(x.mult(lambda), y.mult(lambda), z.mult(lambda));
	}

	public double dotProduct(Vector other) {
		return x.mult(other.x) + y.mult(other.y) + z.mult(other.z);
	}

	// Double check result
	public Vector crossProduct(Vector other) {
		return new Vector(new Coordinate(getY().mult(other.getZ().getNum()) - getZ().mult(other.getY().getNum())),
				new Coordinate(getX().mult(other.getZ().getNum()) - getZ().mult(other.getX().getNum())),
				new Coordinate(getX().mult(other.getY().getNum()) - getY().mult(other.getX().getNum())));
	}

	public Vector normalize() {
		return this.scale(1 / length);
	}
}
