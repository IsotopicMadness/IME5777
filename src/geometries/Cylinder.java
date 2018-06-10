package geometries;

import primitives.*;

public class Cylinder extends RadialGeometry {

	/**
	 * Cylinder inherits from RadialGeometry. Therefore the only additional 
	 */
	private Vector directionLength;
	
	public Cylinder(double r, Point3D p, Vector directionAndLength, Color color, Material material) {
		super(r, p, color, material);
		this.directionLength=new Vector(directionAndLength);
	}

	public Cylinder(Cylinder other) {
		super(other.getRadius(),other.getPoint(), other.getEmmission(), other.getMaterial());
		this.directionLength = new Vector(other.getDirectionLength());
	}

	//Getters/setters
	public Vector getDirectionLength() {
		return directionLength;
	}
	
	//Overriders
	
	/**
	 * Returns normal
	 */
	@Override
	public Vector getNormal(Point3D p) {
		Vector v =new Vector(p.subtract(getPoint()));
		double t=directionLength.dotProduct(v);
		Point3D q= p.add(directionLength.scale(t));
		Vector normal= new Vector(p.subtract(q));
		return normal.normalize();
	}
	
	/**
	 * Checks if equals
	 */
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
		return "Direction and length: "+directionLength.toString()+", "+directionLength.getLength()+"\nRadius: "+this.getRadius()+"\nPoint: "+getPoint().toString()+"\nColor: "+getEmmission().toString();
	}

}
