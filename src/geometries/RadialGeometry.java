package geometries;

import primitives.*;

/**
 * The Class acts as an interface for radius-based objects
 *
 */
public abstract class RadialGeometry extends Geometry {

	protected double _radius;
	// ***************** Constructors ********************** //

	public RadialGeometry(double r, Color color, Material material) {
		super(color, material);
		if (r <= 0)
			throw new IllegalArgumentException("radius cannot be negative\n");
		else {
			_radius = r;

		}
	}

	public RadialGeometry(RadialGeometry ra, Material material) {
		super(ra.getEmmission(), material);
		this._radius = ra._radius;
	}

	// ********* Get/Set*******************//
	/**
	 * returns the shape's radius
	 * 
	 * @return
	 */
	public double getRadius() {
		return _radius;
	}

	@Override
	public String toString() {
		return "Radius: " + _radius + ", " + "Color: " + getEmmission().toString();
	}

}
