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
		public Coordinate getX() {
			return x;
		}public Coordinate getY() {
			return y;
		}
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
}
