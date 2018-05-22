package elements;

import primitives.*;

public class PointLight extends Light {

	protected double _Kc;
	protected double _Kl;
	protected double _Kq;
	
	public PointLight(Color color, double ka, double kc, double kl, double kq, Point3D location) {
		_color = new Color(color);
		if(ka>1 || ka<0
				||kc>1||kc<0
				||kl>1||kl<0)
			throw new ArgumentException("Attenuation factors must be between 1 and 0");
		_Ka = ka;
		_Kc = kc;
		_Kl = kl;
		_Kq = kq;
		
		_location = new Point3D(location);
	}
	public PointLight(PointLight other) {
		_color = new Color(other.getColor());
		_Ka = other.getKa();
		_location = new Point3D(getLocation());
	}

	/*****getters/setters*****/
	
	public double getKc() {
		return _Kc;
	}

	public double getKq() {
		return _Kq;
	}

	public double getKl() {
		return _Kl;
	}
	
	@Override
	public Color getIntensity(Point3D point) {
		double d = _location.distance(point);
		Color _intensity = new Color(_color.scale(1/
				(_Kc+(_Kl*d)+_Kq*Math.pow(d,2))
				));
		return _intensity; 
	}
	
}
