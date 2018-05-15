package elements;

import primitives.*;


public class AmbientLight {
	private Color _color;
	private double _Ka;
	private Color _intensity;
	
	/********** Constructors ***********/	
	public AmbientLight(Color color, double ka) {
		_color = color;
		_Ka = ka;
		_intensity = new Color(_color).scale(_Ka);
	}

	/************** Getters/Setters *******/
	/**
	 * Returns Color
	 * @return
	 */
	public Color get_color() {
		return _color;
	}

	/**
	 * sets color
	 * @param _color
	 */
	public void set_color(Color _color) {
		this._color = _color;
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
	 *  return final Ambient light after multiply in Ka 
	 * @return
	 */
	public Color getIntensity()
	{
		return _intensity;
	}

	

}
