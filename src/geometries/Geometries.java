package geometries;

import java.util.ArrayList;

import primitives.*;

public class Geometries extends Geometry {

	private ArrayList<Geometry> geometries;
	
	public Geometries() {
		
	}

	public Geometries(Geometry g) {
		super(g);
		
	}
	
	public void add(Geometry g) {
		geometries.add(g);
	}
	
	public ArrayList<Point3D> findIntersections(Ray ray){
		ArrayList<Point3D> reInt = new ArrayList<>();
		for(Geometry g : geometries) {
			for(Point3D p : g.findIntersection(ray))
				reInt.add(p);
		}
		return reInt;
	}
	
	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		throw new ArgumentException("Cannot find normal of Geometries");
	}

}
