package geometries;

import java.util.ArrayList;

import primitives.*;

/**
 * Used as an interface for every object in the scene 
 * 
 */
public abstract class Geometry {
	Color _emmission;
	
	public Vector getNormal(Point3D p) {
		Vector v = new Vector (1,0,0);
		return v;
		
	}
	
	public Geometry() {}
	
	public Geometry(Geometry g) {}	
	
	public ArrayList<Point3D> findIntersection(Ray ray) {
		return null;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
