package primitives;

/**
 * skj hfgkhl jdflkh jdfhj fd kh
 * 
 * @author amich
 *
 */
public class Point2D {
	protected Coordinate x;
	protected Coordinate y;

	public Point2D(Coordinate x, Coordinate y) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
	}

	public Point2D(double x, double y) {
		this.x = new Coordinate(x);
		this.y = new Coordinate(y);
	}
	
	public Point2D(Point2D other) {
		x = new Coordinate(other.getX());
		y = new Coordinate(other.getY());
	}

	// get/set
	public Coordinate getX() {
		return x;
	}

	public Coordinate getY() {
		return y;
	}

	// Overrides
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof Point2D))
			return false;
		Point2D other = (Point2D) obj;
		return other.x.equals(this.x) && other.y.equals(this.y);
	}

	@Override
	public String toString() {
		return "(" + x.toString() + ", " + y.toString() + ")";
	}

	// operations
	public Point2D substract(Point2D other) {
		return new Point2D(new Coordinate(this.x.subtract(other.getX().getNum())),
				new Coordinate(this.y.subtract(other.getY().getNum())));
	}

	public Point2D add(Point2D other) {
		return new Point2D(new Coordinate(this.x.add(other.getX().getNum())),
				new Coordinate(this.y.add(other.getY().getNum())));
	}
}
