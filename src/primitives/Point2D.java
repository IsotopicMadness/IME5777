package primitives;

public class Point2D{
	private Coordinate x;
	private Coordinate y;
	
		public Point2D(Coordinate x, Coordinate y) {
		this.x=new Coordinate(x);
		this.y=new Coordinate(y);
		}
		public Point2D(Point2D other) {
			x=new Coordinate(other.getX());
			y=new Coordinate(other.getY());
		}
		
		//get/set
		public Coordinate getX() {
			return x;
		}public Coordinate getY() {
			return y;
		}
		
		//Overrides
		@Override
		public boolean equals(Object obj) {
			if(obj==null)
				return false;
			if(!(obj instanceof Point2D))
				return false;
			if(this==obj)
				return true;
			Point2D other=new Point2D((Point2D)obj);
			return other.getX().equals(this.x) && other.getY().equals(this.y);
		}
		@Override
		public String toString() {
			return "("+x.toString()+", "+y.toString()+")";
		}
		
		//operations
		public Point2D substract(Point2D other) {
			return new Point2D(new Coordinate(this.x.subtract(other.getX().getNum())),new Coordinate(this.y.subtract(other.getY().getNum())));
		}
		public Point2D add(Point2D other) {
			return new Point2D(new Coordinate(this.x.add(other.getX().getNum())),new Coordinate(this.y.add(other.getY().getNum())));
		}
}
