package elements;

import primitives.*;

public abstract class Light {

	protected Color _color;
	protected double _Ka;
	protected Color _intensity;
	protected Point3D _location;
	protected Vector _direction;
	
	/************** Getters/Setters *******/
	/**
	 * Returns Color
	 * @return
	 */
	public Color getColor() {
		return _color;
	}

	/**
	 * sets color
	 * @param _color
	 */
	public void set_color(Color _color) {
		this._color = new Color(_color);
	}

	/**
	 * Gets Ka (intensity modifier)
	 * @return
	 */
	public double getKa() {
		return _Ka;
	}

	/**
	 * sets the ka (intensity modifier)
	 * @param ka
	 */
	public void setKa(double ka) {
		_Ka = ka;
	}
	
	/**
	 *  return final color after multiply in Ka 
	 * @return
	 */
	public Color getIntensity(Point3D point)
	{
		return new Color(_intensity);
	}
	
	public Point3D getLocation() {
		return _location;
	}
	
	public Vector getDirection() {
		return _direction;
	}
	
	/**
	 * returns the L vector from the light source to the poin
	 * @param point
	 * @return
	 */
	public Vector getL(Point3D point) {
		if(_location == null)
			return _direction.normalize();
		else
			return new Vector(_location.subtract(point)).normalize();
	}

}
