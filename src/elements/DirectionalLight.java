package elements;

import primitives.*;

public class DirectionalLight extends Light {

	private Vector _direction;
	
	public DirectionalLight(Color color, double ka, Vector direction) {
		_color = new Color(color);
		_Ka = ka;
		_intensity = new Color(color.scale(ka));
		_direction = new Vector(direction);
		_location = null;
	}
	public DirectionalLight(DirectionalLight other) {
	_color = new Color(other._color);
	_Ka = other.getKa();
	_intensity = new Color(other.getIntensity());
	}
	
	

}
