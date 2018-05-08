package elements;

import primitives.Color;

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
	public Color get_color() {
		return _color;
	}

	public void set_color(Color _color) {
		this._color = _color;
	}

	public double getKa() {
		return _Ka;
	}

	public void setKa(double ka) {
		_Ka = ka;
	}
	// retuurn final Ambiant light after multiply in Ka 
	public Color getIntensity()
	{
		return _intensity;
	}
}
