package geometries;

import primitives.Point3D;
import primitives.Vector;

public abstract class Geometry {
	public Geometry() {}
	public Geometry(Geometry g) {}	
	public abstract Vector getNormal(Point3D p);
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
