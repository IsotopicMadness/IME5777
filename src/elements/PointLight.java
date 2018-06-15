/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource {

	protected Point3D _position;
	protected double _Kc, _Kl, _Kq;

	/********** Constructors ***********/
	public PointLight(Point3D position, double Kc, double Kl, double Kq, Color color) {
		super(color);
		_position = position;
		_Kc = Kc;
		_Kl = Kl;
		_Kq = Kq;
	}

	/************** Getters/Setters *******/
	public Point3D get_position() {
		return _position;
	}

	public void set_position(Point3D position) {
		_position = position;
	}

	/************** Operations ***************/
	@Override
	public Color getIntensity(Point3D p) {
		double l = _position.distance(p);
		double d = (_Kc < 1 ? _Kc = 1 : _Kc) + _Kl * l + _Kq * l * l;
		return getIntensity().reduce(d);
	}

	@Override
	public Vector getL(Point3D p) {
		return p.subtract(_position).normalize();
	}

	@Override
	public Vector getD(Point3D p) {
		return getL(p);
	}
}
