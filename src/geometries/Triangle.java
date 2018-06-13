package geometries;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import primitives.*;

/**
 * @author amich 3d Object: Triangle. Basically plane but with cutted corners
 */
public class Triangle extends Plane {

	private Point3D p1, p2, p3;

	// ***************** Constructors ********************** //
	public Triangle(Point3D p1, Point3D p2, Point3D p3, Color color, Material material) {
		super(p1, p2, p3, color, material);
		this.p1 = new Point3D(p1);
		this.p2 = new Point3D(p2);
		this.p3 = new Point3D(p3);

	}

	// ***************** Getters/Setters ********************** //
	public Point3D getP1() {
		return p1;
	}

	public Point3D getP2() {
		return p2;
	}

	public Point3D getP3() {
		return p3;
	}

	// ***************** Administration ******************** //

	@Override
	public String toString() {
		return p1.toString() + ", " + p2.toString() + ", " + p3.toString();
	}

	// ***************** Operations ******************** //
	@Override
	public Map<Intersectable, List<Point3D>> findIntersection(Ray ray) {

		Map<Intersectable, List<Point3D>> result = super.findIntersection(ray);
		if (result.isEmpty())
			return result;

		Point3D p0 = ray.getLocation();

		Vector v1 = new Vector(p1.subtract(p0));
		Vector v2 = new Vector(p2.subtract(p0));
		Vector v3 = new Vector(p3.subtract(p0));

		Vector n1 = v1.crossProduct(v2);
		Vector n2 = v2.crossProduct(v3);
		Vector n3 = v3.crossProduct(v1);

		Vector u = result.get(this).get(0).subtract(p0);

		double t1 = n1.dotProduct(u);
		double t2 = n2.dotProduct(u);
		double t3 = n3.dotProduct(u);

		if (t1 > 0 && t2 > 0 && t3 > 0 || t1 < 0 && t2 < 0 && t3 < 0)
			return result;

		return new HashMap<Intersectable, List<Point3D>>();
	}
}
