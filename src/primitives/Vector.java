
package primitives;

public class Vector extends Point3D {

	
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		super(x, y, z);
	}
	public Vector(Point3D point) {
		super(point);
	}
	public Vector(Vector other) {
		super(other.getX(), other.getY(), other.getZ());
	}
	
	public Vector getVector() {
		return new Vector(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(!(obj instanceof Vector))
			return false;
		if(this==obj)
			return true;
		Vector other = new Vector((Vector)obj);
		return super.equals(other.getPoint3D());
	}
}
