package primitives;

public class Ray extends Vector {

	private Point3D location;

	// Constructors
	public Ray(Vector direction, Point3D point) {
		super(direction.normalize());
		location = new Point3D(point);
	}

	public Ray(Ray other) {
		super(other);
		location = new Point3D(other.getLocation());
	}

	// get/set

	/**
	 * returns the point of origins of the ray
	 * 
	 * @return
	 */
	public Point3D getLocation() {
		return location;
	}

	/**
	 * returns the vector
	 * 
	 * @return
	 */
	public Vector getDirection() {
		return new Vector(x, y, z);
	}

	// Overrides
	@Override
	/**
	 * checks if the given object is a ray and if it's values are equal to this
	 * 
	 * @param obj
	 * @return
	 */
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
	/**
	 * returns the string of this object
	 * 
	 * @return
	 */
	public String toString() {
		return "Direction: " + super.toString() + "\nLocation: " + location.toString();
	}
}