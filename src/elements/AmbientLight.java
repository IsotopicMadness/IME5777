package elements;

import primitives.*;


public class AmbientLight extends Light {
	
	/********** Constructors ***********/	
	public AmbientLight(Color color, double ka) {
		_color = color;
		_Ka = ka;
		_intensity = new Color(_color.scale(_Ka));
		_location = null;
		_direction = null;
	}
	
	public AmbientLight(AmbientLight other) {
		_color = new Color(other.getColor());
		_Ka=other.getKa();
		_intensity = new Color(other.getIntensity());
		_location = null;
		_direction = null;
	}
	
	public Color getIntensity() {
		return _intensity;
	}

	
	

}
