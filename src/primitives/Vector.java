
package primitives;

public class Vector extends Point3D {

	private double length;
	
	//Constructors
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		super(x, y, z);
		length=_length();
	}
	public Vector(double x, double y, double z) {
		super(x,y,z);
		length=_length();
	}
	public Vector(Point3D point) {
		super(point);
		length=_length();
	}
	public Vector(Vector other) {
		super(other.getX(), other.getY(), other.getZ());
		length=other.getLength();
	}
	
	//getters/setters
	public double getLength() {
		return length;
	}
	//admin
	private double _length() {
		return super.distance(new Point3D(new Coordinate(0.0), new Coordinate(0.0), new Coordinate(0.0)));
	}
	
	//overrides
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
	
	
	//operations
	public Vector add(Vector other) {
		return new Vector(new Coordinate(getX().add(other.getX().getNum())),
						  new Coordinate(getY().add(other.getY().getNum())),
						  new Coordinate(getZ().add(other.getZ().getNum())));
	}
	
	public Vector subtract(Vector other) {
		return new Vector(new Coordinate(getX().add(-other.getX().getNum())),
				  		  new Coordinate(getY().add(-other.getY().getNum())),
				  		  new Coordinate(getZ().add(-other.getZ().getNum())));
	}
	public Vector scalarMuliplication(double lambda) {
		return new Vector(
				new Coordinate(this.getX().mult(lambda)),
				new Coordinate(this.getY().mult(lambda)),
				new Coordinate(this.getZ().mult(lambda)));
	}
	
	public double dotProduct(Vector other) {
		return (getX().mult(other.getX().getNum())
				+getY().mult(other.getY().getNum())
				+getZ().mult(other.getZ().getNum()));
	}
	//Double check result
	public Vector crossProduct(Vector other) {
		return new Vector(
				new Coordinate(
						getY().mult(other.getZ().getNum())
						-getZ().mult(other.getY().getNum())), 
				new Coordinate(
						getX().mult(other.getZ().getNum())
						-getZ().mult(other.getX().getNum())), 
				new Coordinate(
						getX().mult(other.getY().getNum())
						-getY().mult(other.getX().getNum())));
	}
	public Vector normalize() {
		return this.scalarMuliplication(1/length);
	}
}
