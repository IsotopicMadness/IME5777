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
		Map<Intersectable, List<Point3D>> result = new HashMap<Intersectable, List<Point3D>>();
		Point3D p0 = ray.getLocation();
		Vector v = ray;
		Vector u = _center.subtract(p0);
		// ...
		if (_center.equals(p0)) {
			List<Point3D> array = new ArrayList<>();
			array.add(p0.add(v.scale(_radius)));
			result.put(this, array);
			return result;
		}
		double tm = (v.dotProduct(u));
		double d = Math.sqrt(u.getLength2() - tm * tm);

		// If d is greater than radius than the ray misses our sphere (i.e. goes
		// outside)
		if (d > _radius)
			return result;

		double th = Math.sqrt(_radius * _radius - d * d);
		// if th=0 than the line of the ray is tangent to our sphere and there will be
		// single
		// intersection point
		if (Coordinate.isZero(th)) {
			List<Point3D> array = new ArrayList<>();
			array.add(p0.add(v.scale(tm)));
			result.put(this, array);
			return result;
		}
		double t1 = tm - th;
		double t2 = tm + th;
		// ...
		Coordinate x = new Coordinate(t1);
		Coordinate y = new Coordinate(t2);
		if (x.getNum() > 0 || y.getNum() > 0) {
			List<Point3D> array = new ArrayList<>();
			// ...
			if (x.getNum() > 0)
				array.add(p0.add(v.scale(x.getNum())));
			if (y.getNum() > 0)
				array.add(p0.add(v.scale(y.getNum())));
			result.put(this, array);
		}

		return result;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
