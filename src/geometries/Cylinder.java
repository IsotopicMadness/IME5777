package geometries;

import primitives.*;
import primitives.Vector;

public class Cylinder extends RadialGeometry {

	private Vector _directionLength;
	
	public Cylinder(double r, Point3D p, Vector directionAndLength) {
		super(r, p);
		this.directionLength=new Vector(directionAndLength);
	}

	public Cylinder(Cylinder other) {
		super(other.getRadius(),other.getPoint());
		this.directionLength = new Vector(other.getDirectionLength());
	}

	//Getters/setters
	public Vector getDirectionLength() {
		return directionLength;
	}
	
	//Overriders
	@Override
	public Vector getNormal(Point3D p) {
		Vector v =new Vector(p.subtract(getPoint()));
		double t=directionLength.dotProduct(v);
		Point3D q= p.add(directionLength.scalarMuliplication(t));
		Vector normal= new Vector(p.subtract(q));
		return normal.normalize();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(!(obj instanceof Cylinder))
			return false;
		if(this==obj)
			return true;
		Cylinder other = new Cylinder((Cylinder)obj);
		return this.directionLength.equals(other.getDirectionLength()) && this.getPoint() == other.getPoint() && this.getRadius() == other.getRadius();
	}
	@Override
	public String toString() {
		return "Direction and length: "+directionLength.toString()+", "+directionLength.getLength()+"\nRadius: "+this.getRadius()+"\nPoint: "+getPoint().toString();
	}

}
