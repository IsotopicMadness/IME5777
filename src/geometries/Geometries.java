package geometries;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import primitives.*;

public class Geometries {

	private List<Geometry> geometries;

	public Geometries() {
		geometries = new ArrayList<>();
	}

	public Geometries(Geometries g) {
		geometries = new ArrayList<>();
		for (Geometry t : g.getGeometries()) {
			geometries.add(t);
		}

	}

	public void add(Geometry g) {
		geometries.add(g);
	}

	public HashMap<Geometry, ArrayList<Point3D>> findIntersections(Ray ray) {

		HashMap<Geometry, ArrayList<Point3D>> returnInteractions = new HashMap<>();

		for (Geometry g : geometries) {
			returnInteractions.putAll(g.findIntersection(ray));
		}
		return returnInteractions;
	}

	public List<Geometry> getGeometries() {
		return geometries;
	}

}
