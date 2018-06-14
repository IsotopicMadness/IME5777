package primitives;

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

	/**
	 * returns the X value of the point
	 * 
	 * @return
	 */
	public Coordinate getX() {
		return x;
	}

	/**
	 * returns the Y value of the point
	 * 
	 * @return
	 */
	public Coordinate getY() {
		return y;
	}

	// Overrides
	@Override
	/**
	 * Checks if the given point shares the same values as this one
	 * 
	 * @return
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof Point2D))
			return false;
		Point2D other = (Point2D) obj;
		return other.x.equals(x) && other.y.equals(y);
	}

	@Override
	/**
	 * returns the point's string
	 * @return
	 */
	public String toString() {
		return "(" + x.toString() + ", " + y.toString() + ")";
	}

	// operations
	/**
	 * subtracts the values of the given point from @this
	 * 
	 * @param other
	 * @return
	 */
	public Point2D substract(Point2D other) {
		return new Point2D(new Coordinate(x.subtract(other.getX().getNum())),
				new Coordinate(y.subtract(other.getY().getNum())));
	}

	/**
	 * Adds the values of the given point to @this
	 * 
	 * @param other
	 * @return
	 */
	public Point2D add(Point2D other) {
		return new Point2D(new Coordinate(x.add(other.getX().getNum())), new Coordinate(y.add(other.getY().getNum())));
	}
}
