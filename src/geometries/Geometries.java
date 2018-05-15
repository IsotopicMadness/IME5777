package geometries;

import java.util.ArrayList;

import java.util.HashMap;

import primitives.*;

public class Geometries extends Geometry {

	private ArrayList<Geometry> geometries;
	
	public Geometries() {
		geometries = new ArrayList<>();
	}

	public Geometries(Geometries g) {
		geometries = new ArrayList<>();
		for(Geometry t : g.getGeometries()) {
			geometries.add(t);
		}
		
		
	}
	
	public void add(Geometry g) {
		geometries.add(g);
	}
	
	public ArrayList<Point3D> findIntersections(Ray ray){
		ArrayList<Point3D> returnInteractions = new ArrayList<>();
		for(Geometry g : geometries) {
			g.findIntersection(ray).forEach((key,value) ->{
				returnInteractions.addAll(value);
			});
		}
		return returnInteractions;
	}
	
	public ArrayList<Geometry> getGeometries() {
		return geometries;
	}
	
	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		throw new ArgumentException("Cannot find normal of Geometries");
	}

}
