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
		if(p.distance(this.getPoint())!= this.getRadius())
			return null;
		else
			return (new Vector(p.subtract(this.getPoint())).normalize());
	}
	@Override
	public String toString() {
		return super.toString();
	}
}
