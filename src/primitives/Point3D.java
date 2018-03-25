/**
 * 
 */
package primitives;
import java.lang.Math;

/**
 * @author amich
 *
 */
public class Point3D extends Point2D {

	/**
	 * 
	 */
	private Coordinate z;
	
	public Point3D(Point3D other) {
		super(other.getX(),other.getY());
		z=new Coordinate(other.getZ());
	}
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		super(x, y);
		this.z=new Coordinate(z);
	}
	
	public Coordinate getZ() {
		return z;
	}
	
	public Point3D getPoint3D() {
		return this;

	}
	
	public Vector vectorSubstraction(Point3D other) {
		return new Vector(new Coordinate(getX().subtract(other.getX().getNum())),new Coordinate(getY().subtract(other.getY().getNum())),new Coordinate(z.subtract(other.getZ().getNum())));
	}
	
	public double distance(Point3D other) {
		return Math.sqrt(Math.pow(getX().subtract(other.getX().getNum()), 2)+Math.pow(getY().subtract(other.getY().getNum()), 2)+Math.pow(z.subtract(other.getZ().getNum()), 2));
	}
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
}
