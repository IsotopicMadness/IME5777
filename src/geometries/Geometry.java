package geometries;

import primitives.Point3D;
import primitives.Vector;

public abstract class Geometry {
public Geometry() {}
public Geometry(Geometry g) {}	
public abstract Vector getNormal(Point3D p);
public boolean equals(Geometry other) {
	// TODO Auto-generated method stub
	return false;
}
}
