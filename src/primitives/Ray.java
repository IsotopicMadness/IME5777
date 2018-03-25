package primitives;

public class Ray extends Vector {

	private Point3D location;
	
	public Ray(Vector direction, Point3D point) {
		super(direction);
		location=new Point3D(point);
	}
	public Ray(Ray other) {
		super(other.getDirection());
		location=new Point3D(other.getLocation());
	}
	
	public Point3D getLocation() {
		return location;
	}
	private Vector getDirection() {
		return new Vector(getX(),getY(),getZ());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(!(obj instanceof Ray))
			return false;
		if(this==obj)
			return true;
		Ray other = new Ray((Ray)obj);
		return this.location.equals(other.getLocation()) && super.equals(getDirection());	
	}
}