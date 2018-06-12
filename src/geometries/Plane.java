package geometries;

import primitives.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 3D Object: Plane.
 *
 */
public class Plane extends Geometry {
	private Point3D point;
	private Vector normal;

	/**
	 * "Description Resource Path Location Type Implicit super constructor Plane()
	 * is undefined. Must explicitly invoke another constructor Triangle.java
	 * /IME5778-E1-1913-5687/src/geometries line 16 Java Problem"
	 *
	 * In order to avoid the above error a default constructor had had to be built
	 */

	public Plane(Point3D p, Vector v, Color color, Material material) {
		super(color, material);
		point = new Point3D(p);
		normal = new Vector(v);
	}

	public Plane(Plane obj) {
		super(obj.getEmmission(), obj);
		point = new Point3D(obj.getPoint());
		normal = new Vector(obj.getNormal());
	}

	/**
	 * @param p1
	 * @param p2
	 * @param p3
	 *            The constructor takes three points and calculates the plane
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3, Color color, Material material) {
		super(color, material);
		Vector p1_2 = new Vector(p1.subtract(p2));
		Vector p1_3 = new Vector(p2.subtract(p3));
		Vector n = p1_2.crossProduct(p1_3);
		normal = n.normalize();
		point = new Point3D(p1);
	}

	// ***************** Getters/Setters ********************** //
	/**
	 * Returns the point of the plane
	 * 
	 * @return
	 */
	public Point3D getPoint() {
		return point;
	}

	/**
	 * Returns the Plane's normal
	 * 
	 * @return
	 */
	public Vector getNormal() {
		return normal;
	}

	/**
	 * checks whether or not the given object is
	 * 
	 * @param obj
	 * @return
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Plane))
			return false;
		if (this == obj)
			return true;
		Plane other = new Plane((Plane) obj);
		return this.point.equals(other.point) && this.normal.equals(other.normal)
				&& this.getEmmission().equals(other.getEmmission());
	}

	@Override
	/**
	 * 
	 * Calculates and returns the points where the given ray cross the plane
	 * 
	 * @param ray
	 * @return
	 */
	public Map<Intersectable, List<Point3D>> findIntersection(Ray ray) {
		HashMap<Intersectable, List<Point3D>> result = new HashMap<Intersectable, List<Point3D>>();
		ray = new Ray(ray.normalize(), ray.getLocation());
		double t = (normal.dotProduct(point.subtract(ray.getLocation()))) / (normal.dotProduct(ray.getDirection()));
		if (t > 0) {
			List<Point3D> arr = new ArrayList<Point3D>();
			arr.add(ray.getLocation().add(ray.getDirection().scale(t)));
			result.put(this, arr);
		}
		return result;
	}

	@Override
	/**a
	 * Returns the string of this object
	 * 
	 * @return
	 */
	public String toString() {
		return "Point: " + point.toString() + ", Vector: " + normal.toString();
	}

	// Operators
	/**
	 * returns a normal of the plane
	 * 
	 * @return
	 */
	//@Override
	public Vector getNormal(Point3D p) {
		return normal.normalize();
	}

}
