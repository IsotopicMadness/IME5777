package elements;

import primitives.*;

public class PointLight extends Light {

	protected double _Kc;
	protected double _Kl;
	protected double _Kq;
	
	public PointLight(Color color, double kc, double kl, double kq, Point3D location) {
		_color = new Color(color);
		if(kc<1
				||kl<0
				||kq<0)
			throw new IllegalArgumentException("Attenuation factors have wrong values");
		_Kc = kc;
		_Kl = kl;
		_Kq = kq;
		
		_location = new Point3D(location);
	}
	public PointLight(PointLight other) {
		_color = new Color(other.getColor());
		_location = new Point3D(getLocation());
		_Kc = other.getKc();
		_Kl = other.getKl();
		_Kq = other.getKq();
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
