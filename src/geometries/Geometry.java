package geometries;

import java.util.ArrayList;

import primitives.*;

public abstract class Geometry {
	public Geometry() {}
	public Geometry(Geometry g) {}	
	public abstract Vector getNormal(Point3D p);
	public ArrayList<Point3D> findIntersection(Ray ray) {
		return null;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
