
package primitives;

public class Vector extends Point3D {

	double length;
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		super(x, y, z);
		length=_length();
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
	
	public Vector add(Vector other) {
		return new Vector(new Coordinate(getX().add(other.getX().getNum())),new Coordinate(getY().add(other.getY().getNum())), new Coordinate(getZ().add(other.getZ().getNum())));
	}
	
	public Vector scalarMuliplication(double lambda) {
		return new Vector(new Coordinate(this.getX().mult(lambda)),new Coordinate(this.getY().mult(lambda)),new Coordinate(this.getZ().mult(lambda)));
	}
	
	public double dotProduct(Vector other) {
		return (getX().mult(other.getX().getNum())+getY().mult(other.getY().getNum())+getZ().mult(other.getZ().getNum()));
	}
	//Double check result
	public Vector crossProduct(Vector other) {
		return new Vector(new Coordinate(this.getY().mult(other.getZ().getNum())-this.getZ().mult(other.getY().getNum())), new Coordinate(this.getX().mult(other.getZ().getNum())-this.getZ().mult(other.getZ().getNum())), new Coordinate(this.getX().mult(other.getY().getNum())-this.getY().mult(other.getX().getNum())));
	}
	private double _length() {
		return super.distance(this.getPoint3D());
	}
	public Vector normalize() {
		return new Vector(this.scalarMuliplication(1/length));
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
	@Override
	public String toString() {
		return super.toString();
	}
}
