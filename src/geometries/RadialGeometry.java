package geometries;

import primitives.*;

public abstract class RadialGeometry extends Geometry {
	
	private double _radius;
	private Point3D _point;
//***************** Constructors ********************** // 

	public RadialGeometry(double r,Point3D p) {
		if (r<=0)
			throw new IllegalArgumentException("radius cannot be negative\n");
		else
			_radius = r;
		
			point = p;
	
	}
	public RadialGeometry(RadialGeometry ra) {
		this.point = new Point3D(ra.point);
		this._radius = ra._radius;
}
//********* Get/Set*******************//
	public double getRadius() {return _radius;}
	
	public Point3D getPoint() {
		return point;
	}

	@Override
	public String toString() {
		return "Radius: "+_radius+", Point: "+point.toString();
		}
	
	//public void setPoint(Point3D point) {
		//this.point = point;
	//}

	// ***************** Operations ******************** //

}
