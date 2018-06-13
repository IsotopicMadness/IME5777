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
		normal = v.normalize();
	}

	/**
	 *  The constructor takes three points and calculates the plane
	 * @param p1
	 * @param p2
	 * @param p3
	 *           
	 */
	public Plane(Point3D p1, Point3D p2, Point3D p3, Color color, Material material) {
		super(color, material);
		
		// if p1=p2 or p1=p3, subtract function will generate an exception
		// and it is acceptable since we can't build a plane basing on less than 3 separate points
		Vector p1_2 = p1.subtract(p2);
		Vector p1_3 = p2.subtract(p3);
		
		// if p1=p3 or the points are located on the same line, crossProduct function will generate an exception
		// and it is acceptable since we can't build a plane basing on less than 3 separate points or if they are
		// not on the same line
		normal = p1_2.crossProduct(p1_3).normalize();

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

	@Override
	/**
	 * 
	 * Calculates and returns the points where the given ray cross the plane
	 * 
	 * @param ray
	 * @return
	 */
	public Map<Intersectable, List<Point3D>> findIntersection(Ray ray) {
		Map<Intersectable, List<Point3D>> result = new HashMap<Intersectable, List<Point3D>>();
		Vector v = ray;
		Point3D p0 = ray.getLocation();

		double denom = normal.dotProduct(v);
		// if the denominator (denom) is zero then the ray is parallel to our plane
		// and therefore there are no intersection points
		if (Coordinate.isZero(denom))
			return result;
		
		double t = (normal.dotProduct(point.subtract(p0))) / denom;
		if (t > 0) {
			List<Point3D> arr = new ArrayList<Point3D>();
			arr.add(p0.add(v.scale(t)));
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
		return normal;
	}

}
