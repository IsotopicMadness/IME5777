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

	public Triangle(Triangle other) {
		super(other.getP1(), other.getP2(), other.getP3(), other.getEmmission(), other);
		this.p1 = new Point3D(other.getP1());
		this.p2 = new Point3D(other.getP2());
		this.p3 = new Point3D(other.getP3());
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
	/*
	 * checks if equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof Triangle))
			return false;
		Triangle other = new Triangle((Triangle) obj);
		return this.p1.equals(other.p1) && this.p2.equals(other.p2) && this.p3.equals(other.p3)
				&& this.getEmmission().equals(other.getEmmission());

	}

	@Override
	public String toString() {
		return p1.toString() + ", " + p2.toString() + ", " + p3.toString();
	}

	// ***************** Operations ******************** //
	@Override
	/**
	 * @param ray
	 *            Calculates and returns the points where the given ray cross the
	 *            triangle
	 * @return
	 */
	public Map<Intersectable, List<Point3D>> findIntersection(Ray ray) {

		Map<Intersectable, List<Point3D>> result = new HashMap<Intersectable, List<Point3D>>();

		Vector v1 = new Vector(p1.subtract(ray.getLocation()));
		Vector v2 = new Vector(p2.subtract(ray.getLocation()));
		Vector v3 = new Vector(p3.subtract(ray.getLocation()));

		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();

		result = super.findIntersection(ray);

		if (result.size() == 0)
			return result;

		result.forEach((k, v) -> k = this); // In order to make the keys Triangles and not Planes

		Point3D p = new Point3D(result.get(this).get(0));

		boolean t1 = n1.dotProduct(p.subtract(ray.getLocation())) > 0;
		boolean t2 = n2.dotProduct(p.subtract(ray.getLocation())) > 0;
		boolean t3 = n3.dotProduct(p.subtract(ray.getLocation())) > 0;

		if (t1 && t2 && t3 || !t1 && !t2 && !t3)
			return result;
		else
			return new HashMap<Intersectable, List<Point3D>>();
	}
}
