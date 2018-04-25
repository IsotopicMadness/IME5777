package geometries;
import java.util.ArrayList;

import primitives.*;

public class Sphere extends RadialGeometry{
	//Constructors
	public Sphere(Point3D center, double radius) {
		super(radius,new Point3D(center));
	}
	public Sphere(Sphere other) {
		super(other.getRadius(),new Point3D(other.getPoint()));
	}
	
	//Overrides
	@Override
	public Vector getNormal(Point3D p) {
		if(p.distance(this.getPoint())!= this.getRadius())
			return null;
		else
			return (new Vector(p.subtract(this.getPoint())).normalize());
	}
	@Override
	public ArrayList<Point3D> findIntersection(Ray ray) {
		
		ArrayList<Point3D> result = new ArrayList<Point3D>();
		
		Vector u = new Vector(this.getPoint().subtract(ray.getLocation()));
		ray=new Ray(ray.normalize(),ray.getLocation());
		double tm = (u.dotProduct(ray.getDirection()));
		double d = Math.sqrt(
				Math.pow(u.getLength(), 2)-
				Math.pow(tm, 2));

		if( d > this.getRadius())
			return result;
		double th = Math.sqrt(
				Math.pow(this.getRadius(),2)
				-Math.pow(d, 2));
		double t1 = tm+th;
		double t2 = tm-th;
		
		if(t1!=0)
			result.add(new Point3D(ray.getLocation().add(ray.getDirection().scalarMuliplication(t1))));
			result.add(new Point3D(ray.getLocation().add(ray.getDirection().scalarMuliplication(t2))));
		return result;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
