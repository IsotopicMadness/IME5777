package geometries;

import java.util.ArrayList;

import java.util.HashMap;

import primitives.*;

/**
 * Used as an interface for every object in the scene
 * 
 */
public abstract class Geometry extends Material {
	private Color _emmission;

	/**
	 * returns a normal Vector. Not supposed to be implemented.
	 * 
	 * @param p
	 * @return
	 */
	public Vector getNormal(Point3D p) {
		Vector v = new Vector(1, 0, 0);
		return v;

	}

	public Geometry(Color emmi, Material material) {
		super(material);
		_emmission = new Color(emmi);
	}

	public Geometry(Geometry g) {
		super(g.getMaterial());
		_emmission = new Color(getEmmission());
	}

	public Color getEmmission() {
		return _emmission;
	}

	public void setEmmission(Color _emmission) {
		this._emmission = _emmission;
	}

	public HashMap<Geometry, ArrayList<Point3D>> findIntersection(Ray ray) {
		return new HashMap<Geometry, ArrayList<Point3D>>();
	}

	public Vector getNormal() {
		throw new ArgumentException("Can't execute getNormal() on parent");
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
