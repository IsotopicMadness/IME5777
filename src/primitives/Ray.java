package primitives;

public class Ray extends Vector {

	private Point3D location;

	// Constructors
	public Ray(Vector direction, Point3D point) {
		super(direction);
		location = new Point3D(point);
	}

	public Ray(Ray other) {
		super(other.getDirection());
		location = new Point3D(other.getLocation());
	}

	// get/set
	public Point3D getLocation() {
		return location;
	}

	public Vector getDirection() {
		return new Vector(getX(), getY(), getZ());
	}

	// Overrides
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		if (this == obj)
			return true;
		Ray other = new Ray((Ray) obj);
		return this.location.equals(other.getLocation()) && super.equals(getDirection());
	}

	@Override
	public String toString() {
		return "Direction: " + super.toString() + "\nLocation: " + location.toString();
	}
}