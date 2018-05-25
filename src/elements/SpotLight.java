package elements;

import primitives.*;

public class SpotLight extends PointLight {

	Vector _direction;

	
	public SpotLight(Color color, double kc, double kl, double kq, Point3D location, Vector direction) {
		super(color, kc, kl, kq,location);
		_direction = new Vector(direction.normalize());
	}
	public SpotLight(SpotLight other) {
		super(other.getColor(), other.getKc(), other.getKl(), other.getKq(),other.getLocation());
		_direction = new Vector(other.getDirection().normalize());
	}
	
	public Vector getDirection() {
		return _direction;
	}
	
	public Color getIntensity(Point3D point) {
		double distance = point.distance(_location);
		Vector l = new Vector(point.subtract(_location));
		return new Color(_color.scale(
				Math.abs(l.dotProduct(_direction))
				/(_Kc + (_Kl*distance) + (_Kq*Math.pow(distance, 2)))
				));
	}

}
