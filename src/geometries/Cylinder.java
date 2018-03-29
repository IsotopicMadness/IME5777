package geometries;

import primitives.*;
import primitives.Vector;

public class Cylinder extends RadialGeometry {

	private Ray directionLength;
	public Cylinder(double r, Point3D p, Ray directionAndLength) {
		super(r, p);
		this.directionLength=new Ray(directionAndLength);
	}

	public Cylinder(Cylinder other) {
		super(other.getRadius(),other.getPoint());
		this.directionLength = new Ray(other.getDirectionLength());
	}

	//Getters/setters
	public Ray getDirectionLength() {
		return directionLength;
	}
	
	//Overriders
	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return null;
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

}
