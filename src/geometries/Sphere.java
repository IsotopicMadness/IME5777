package geometries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import primitives.*;

/**
 * 3D object: Sphere
 *
 */
public class Sphere extends RadialGeometry {

	private Point3D _center;

	// Constructors
	public Sphere(Point3D center, double radius, Color color, Material material) {
		super(radius, color, material);
		_center = new Point3D(center);
	}

	// Overrides

	@Override
	/**
	 * returns normal from a given point
	 * 
	 * @param Point3D
	 */
	public Vector getNormal(Point3D p) {
		// if(p.distance(this.getPoint())!= this.getRadius())
		// throw new IllegalArgumentException("Point is not on sphere");
		// else
		return (p.subtract(_center).normalize());
	}

	@Override
	/**
	 * Calculates and returns the points where the given ray crosses the Sphere
	 * 
	 * @param ray
	 * @return Intersections map
	 */
	public Map<Intersectable, List<Point3D>> findIntersection(Ray ray) {
		Map<Intersectable, List<Point3D>> intersections = new HashMap<Intersectable, List<Point3D>>();
		ArrayList<Point3D> arrPoints = new ArrayList<>();
		Vector u = this._center.subtract(ray);
		double tm = ray.dotProduct(u);
		double dSquared = u.dotProduct(u) - (tm * tm);
		double temp = this._radius * this._radius - dSquared;
		if (temp < 0)
			return new HashMap<Intersectable, List<Point3D>>();
		double th = Math.sqrt(temp);

		if (Coordinate.isZero(th)) {
			if (!Coordinate.isZero(tm))
				arrPoints.add(ray.getLocation().add(ray.getDirection().scale(tm)));
			else
				return intersections;
		} else {
			double t1 = tm + th;
			double t2 = tm - th;
			if (t1 > 0 && !Coordinate.isZero(t1))
				arrPoints.add(ray.getLocation().add(ray.getDirection().scale(t1)));
			if (t2 > 0 && !Coordinate.isZero(t2))
				arrPoints.add(ray.getLocation().add(ray.getDirection().scale(t2)));
		}

		if (arrPoints.size() != 0)
			intersections.put(this, arrPoints);

		return intersections;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
