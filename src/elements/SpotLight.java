/**
 * 
 */
package elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight {

	private Vector _direction;

	/********** Constructors ***********/
	/**
	 * @param position
	 * @param Kc
	 * @param Kl
	 * @param Kq
	 * @param color
	 */
	public SpotLight(Point3D position, double Kc, double Kl, double Kq, Color color, Vector direction, double radius) {
		super(position, Kc, Kl, Kq, color, radius);
		_direction = direction;
	}

	/************** Getters/Setters *******/
	public Vector get_direction() {
		return _direction;
	}

	public void set_direction(Vector _direction) {
		this._direction = _direction;
	}

	/************** Operations ***************/
	@Override
	public Color getIntensity(Point3D p) {
		double l = _position.distance(p);
		double a = _direction.dotProduct(getL(p));
		double d = (_Kc < 1 ? _Kc = 1 : _Kc) + _Kl * l + _Kq * l * l;
		return new Color(getIntensity().scale(a).reduce(d));
	}

	@Override
	public Vector getD(Point3D p) {
		return _direction;
	}

/*	@Override
	public List<LightSource> returnMultipleLights(int samples) {
		List<LightSource> additionalSamples = new ArrayList<LightSource>();
		additionalSamples.add(new SpotLight(_position, _Kc, _Kl, _Kq, _color.reduce(samples), _direction));
		// Calculates the SpotLight's Plane in order to add parallel lights
		Random rand = new Random();
		double d = -_direction.dotProduct(new Vector(_position));
		double xI = -d / _direction.getX().getNum();
		double yI = -d / _direction.getY().getNum();
		double zI = -d / _direction.getZ().getNum();
		Point3D _1 = new Point3D(0, 0, zI);
		Point3D _2 = new Point3D(0, yI, 0);
		Point3D _3 = new Point3D(xI, 0, 0);
		Vector v1 = _1.subtract(_2);
		Vector v2 = _3.subtract(_2);
		for (int i = 0; i < samples; i++) {
			int t = rand.nextInt(10) + 1;
			int s = rand.nextInt(10) + 1;
			additionalSamples
					.add(new SpotLight(new Point3D(0, 0, 0).add(v1.scale(t)).add(v2.scale(s)).add(new Vector(_2)), _Kc,
							_Kl, _Kq, _color.reduce(samples), _direction));
		}
		return additionalSamples;
	}*/
}
