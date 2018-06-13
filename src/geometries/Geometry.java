package geometries;

import primitives.*;

/**
 * Used as an interface for every object in the scene
 * 
 */
public abstract class Geometry extends Material implements Intersectable {
	private Color _emmission;

	/**
	 * returns a normal Vector. Not supposed to be implemented.
	 * 
	 * @param p
	 * @return
	 */
	public abstract Vector getNormal(Point3D p);

	public Geometry(Color emmi, Material material) {
		super(material);
		_emmission = new Color(emmi);
	}

	public Color getEmmission() {
		return _emmission;
	}

}
