package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane extends Geometry {
	private Point3D point;
	private Vector vector;
public Plane(Point3D p,Vector v)
{//TODO
}
	public Plane(Plane obj) {
	// TODO Auto-generated constructor stub
}
	// ***************** Getters/Setters ********************** //
public Point3D getp() {return point;}
public Vector getv() {return vector;}


public boolean equals(Plane obj) {
if(obj==null)
	return false;
if(!(obj instanceof Plane))
	return false;
if(this==obj)
	return true;
Plane other = new Plane((Plane)obj);
return this.point.equals(obj.point) && this.vector.equals(obj.vector);
}
	@Override
	public String toString() {return "\n";}
	
	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

}
