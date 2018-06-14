package geometries;

import primitives.*;
import primitives.Vector;

public class Cylinder extends RadialGeometry {

	/**
	 * Cylinder inherits from RadialGeometry. Therefore the only additional
	 */
	private Vector directionLength;

	public Cylinder(double r, Point3D p, Vector directionAndLength, Color color) {
		super(r, p, color);
		this.directionLength = new Vector(directionAndLength);
	}

	// Getters/setters
	public Vector getDirectionLength() {
		return directionLength;
	}

	// Overriders

	/**
	 * Returns normal
	 */
	@Override
	public Vector getNormal(Point3D p) {
		Vector v = new Vector(p.subtract(getPoint()));
		double t = directionLength.dotProduct(v);
		Point3D q = p.add(directionLength.scalarMuliplication(t));
		Vector normal = new Vector(p.subtract(q));
		return normal.normalize();
	}

	@Override
	public String toString() {
		return "Direction and length: " + directionLength.toString() + ", " + directionLength.getLength() + "\nRadius: "
				+ this.getRadius() + "\nPoint: " + getPoint().toString() + "\nColor: " + getEmmission().toString();
	}

}
