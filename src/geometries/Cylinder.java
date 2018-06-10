package geometries;

import java.util.List;
import java.util.Map;

import primitives.*;

public class Cylinder extends RadialGeometry {

	private double _hight;
	private Vector _direction;
	private Point3D _centerPoint1;
	private Point3D _centerPoint2;
	private Plane _plane1;
	private Plane _plane2;

	public Cylinder(double hight, double r, Point3D centerPoint1, Point3D centerPoint2, Plane plane1, Plane plane2,
			Vector directionAndLength, Color color, Material material) {
		super(r, color, material);
		_hight = hight;
		_direction = new Vector(directionAndLength);
		_centerPoint1 = new Point3D(centerPoint1);
		_centerPoint2 = new Point3D(centerPoint2);
		_plane1 = new Plane(plane1);
		_plane2 = new Plane(plane2);

	}

	public Cylinder(Cylinder other) {
		super(other._radius, other.getEmmission(), other);
		
	}

	// Getters/setters
	public Vector getDirectionLength() {
		return _direction;
	}

	// Overriders

	/**
	 * Returns normal
	 */
	@Override
	public Vector getNormal(Point3D p) {
		Vector v = new Vector(p.subtract(getPoint()));
		double t = _direction.dotProduct(v);
		Point3D q = p.add(_direction.scale(t));
		Vector normal = new Vector(p.subtract(q));
		return normal.normalize();
	}

	/**
	 * Checks if equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Cylinder))
			return false;
		if (this == obj)
			return true;
		Cylinder other = new Cylinder((Cylinder) obj);
		return this._direction.equals(other.getDirectionLength()) && this.getPoint() == other.getPoint()
				&& this.getRadius() == other.getRadius();
	}

	@Override
	public String toString() {
		return "Direction and length: " + _direction.toString() + ", " + _direction.getLength() + "\nRadius: "
				+ this.getRadius() + "\nPoint: " + getPoint().toString() + "\nColor: " + getEmmission().toString();
	}

	@Override
	public Map<Intersectable, List<Point3D>> findIntersection(Ray ray) {
		// TODO Auto-generated method stub

	}

}
