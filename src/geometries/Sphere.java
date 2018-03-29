package geometries;
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
		// TODO Auto-generated method stub
		return null;
	}
}
