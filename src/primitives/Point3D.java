/**
 * 
 */
package primitives;
import java.lang.Math;



public class Point3D extends Point2D {

	/**
	 * 
	 */
	private Coordinate z;
	
	//Constructors
	public Point3D(Point3D other) {
		super(other.getX(),other.getY());
		z=new Coordinate(other.getZ());
	}
	public Point3D(double x, double y, double z) {
		super(new Coordinate(x),new Coordinate(y));
		this.z = new Coordinate(z);
	}
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		super(x, y);
		this.z=new Coordinate(z);
	}
	
	//get/set
	public Coordinate getZ() {
		return z;
	}
	/**
	 * this functions get used when the Vector is required to return a Point3D
	 */
	public Point3D getPoint3D() {
		return this;

	}
	
	//Overrides
	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(!(obj instanceof Point3D))
			return false;
		if(this==obj)
			return true;
		Point3D other= new Point3D((Point3D)obj);
		return this.z.equals(other.getZ()) && super.equals(new Point2D(getX(),getY()));
	};
	@Override
	public String toString() {
		return "("+getX().toString()+", "+getY().toString()+", "+z.toString()+")";
	}
	
	//operators
	public Vector vectorSubstraction(Point3D other) {
		return new Vector(new Coordinate(getX().subtract(other.getX().getNum())),new Coordinate(getY().subtract(other.getY().getNum())),new Coordinate(z.subtract(other.getZ().getNum())));
	}
	public Point3D subtract(Point3D other) {
		return new Point3D(new Coordinate(this.getX().subtract(other.getX().getNum())),new Coordinate(this.getY().subtract(other.getY().getNum())), new Coordinate(this.z.subtract(other.getZ().getNum())));
	}
	
	public double distance(Point3D other) {
		return Math.sqrt(Math.pow(getX().subtract(other.getX().getNum()), 2)+Math.pow(getY().subtract(other.getY().getNum()), 2)+Math.pow(z.subtract(other.getZ().getNum()), 2));
	}
}
