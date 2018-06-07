package geometries;
import java.util.ArrayList;
import java.util.HashMap;

import primitives.*;

/**
 * 3D object: Sphere
 *
 */
public class Sphere extends RadialGeometry{
	//Constructors
	public Sphere(Point3D center, double radius, Color color, Material material) {
		super(radius, new Point3D(center), color, material);
	}
	public Sphere(Sphere other) {
		super(other.getRadius(),new Point3D(other.getPoint()), other.getEmmission(), other.getMaterial());
	}
	
	//Overrides
	
	@Override
	/**returns normal from a given point
	 * @param Point3D
	 */
	public Vector getNormal(Point3D p) {
		//if(p.distance(this.getPoint())!= this.getRadius())
			//throw new IllegalArgumentException("Point is not on sphere");
		//else
			return (new Vector(p.subtract(this.getPoint())).normalize());
	}
	@Override
	/**
	 * @param ray
	 * Calculates and returns the points where the given ray crosses the Sphere
	 */
	public HashMap<Geometry,ArrayList<Point3D>> findIntersection(Ray ray) {
		
		HashMap<Geometry,ArrayList<Point3D>> result = new HashMap<Geometry,ArrayList<Point3D>>();
		
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
		
		if(t1!=0){
			ArrayList<Point3D> array = new ArrayList<>();
			array.add(new Point3D(ray.getLocation().add(ray.getDirection().scale(t1))));
			array.add(new Point3D(ray.getLocation().add(ray.getDirection().scale(t2))));
			result.put(this, array);
		}
		return result;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
