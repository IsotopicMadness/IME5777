package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane extends Geometry {
	private Point3D point;
	private Vector normal;
	
	/**
	 * Description	Resource	Path	Location	Type
	 *Implicit super constructor Plane() is undefined. Must explicitly invoke another constructor	Triangle.java	/IME5778-E1-1913-5687/src/geometries	line 16	Java Problem
	 *
	 *In order to avoid the above error a default constructor had had to be built
	 */
	public Plane() {
		try {
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Plane(Point3D p,Vector v){
		point = new Point3D(p);
		normal = new Vector(v);
	}
	public Plane(Plane obj) {
		point = new Point3D(obj.getPoint());
		normal = new Vector(obj.getNormal());
	}
	public Plane(Point3D p1, Point3D p2, Point3D p3) {
		Vector p1_2 = new Vector(p1.subtract(p2));
		Vector p1_3 = new Vector(p2.subtract(p3));
		Vector n = p1_2.crossProduct(p1_3);
		normal = n.normalize();
		point = new Point3D(p1);
	}
	
	// ***************** Getters/Setters ********************** //
	public Point3D getPoint() {return point;}
	
	public Vector getNormal() {return normal;}


	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(!(obj instanceof Plane))
			return false;
		if(this==obj)
			return true;
		Plane other = new Plane((Plane)obj);
		return this.point.equals(other.point) && this.normal.equals(other.normal);
	}
	
	@Override
	public String toString() {
		return "Point: "+point.toString()+", Vector: "+normal.toString();
		}
	
	//Operators
	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

}