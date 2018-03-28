package geometries;

import primitives.Point3D;
import primitives.Vector;

public abstract class RadialGeometry extends Geometry {
	
private double _radius;
private Point3D point;
//***************** Constructors ********************** // 

public RadialGeometry(double r,Point3D p) {
	if (r<=0)
		throw new IllegalArgumentException("radius cannot be negative\n");
	else
		_radius = r;
	
		setPoint(p);
	
}
public RadialGeometry(RadialGeometry ra) {
	this.point=ra.point;
	this._radius=ra._radius;
}

public double getRadius() {return _radius;}


	@Override
	public String toString() {
		return null;}
	public Point3D getPoint() {
		return point;
	}
	public void setPoint(Point3D point) {
		this.point = point;
	}

	// ***************** Operations ******************** //

}
