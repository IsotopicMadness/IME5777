package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane extends Geometry {
	private Point3D point;
	private Vector vector;
	
	public Plane(Point3D p,Vector v){
		point = new Point3D(p);
		vector = new Vector(v);
	}
	public Plane(Plane obj) {
		point = new Point3D(obj.getPoint());
		vector = new Vector(obj.getVector());
	}
	
	// ***************** Getters/Setters ********************** //
	public Point3D getPoint() {return point;}
	
	public Vector getVector() {return vector;}


	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(!(obj instanceof Plane))
			return false;
		if(this==obj)
			return true;
		Plane other = new Plane((Plane)obj);
		return this.point.equals(other.point) && this.vector.equals(other.vector);
	}
	
	@Override
	public String toString() {
		return "Point: "+point.toString()+", Vector: "+vector.toString();
		}
	
	//Operators
	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

}
