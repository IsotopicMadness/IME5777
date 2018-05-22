package elements;

import primitives.*;

public class SpotLight extends PointLight {

	Vector _direction;

	
	public SpotLight(Color color, double ka, double kc, double kl, double kq, Point3D location, Vector direction) {
		super(color,ka, kc, kl, kq,location);
		_direction = new Vector(direction.normalize());
	}
	public SpotLight(SpotLight other) {
		super(other.getColor(),other.getKa(), other.getKc(), other.getKl(), other.getKq(),other.getLocation());
		_direction = new Vector(other.getDirection().normalize());
	}
	
	public Vector getDirection() {
		return _direction;
	}

}
