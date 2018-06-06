package elements;

import primitives.*;

public class DirectionalLight extends Light {
	
	public DirectionalLight(Color color, double ka, Vector direction) {
		_color = new Color(color);
		_Ka = ka;
		_intensity = new Color(color.scale(ka));
		_direction = new Vector(direction);
		_location = null;
	}
	public DirectionalLight(DirectionalLight other) {
		_direction = new Vector(other.getDirection());
		_color = new Color(other._color);
		_Ka = other.getKa();
		_intensity = new Color(other.getIntensity(new Point3D(0,0,0)));
		_location = null;
		_direction = new Vector(other._direction);
	}
	public Color getIntensity() {
		return _intensity;
	}
	

}
