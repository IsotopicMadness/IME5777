package geometries;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import primitives.*;

public class Geometries implements Intersectable {

	private List<Intersectable> geometries = new ArrayList<>();
	
	public void add(Intersectable g) {
		geometries.add(g);
	}
	
	@Override
	public Map<Intersectable, List<Point3D>> findIntersection(Ray ray) {
		Map<Intersectable, List<Point3D>> returnInteractions = new HashMap<>();
		for(Intersectable g : geometries)
			returnInteractions.putAll(g.findIntersection(ray));
		return returnInteractions;
	}

}
