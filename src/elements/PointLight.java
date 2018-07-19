/**
 * 
 */
package elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import primitives.Color;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource {

	protected Point3D _position;
	protected double _Kc, _Kl, _Kq;
	protected double _radius;

	/********** Constructors ***********/
	public PointLight(Point3D position, double Kc, double Kl, double Kq, Color color, double radius) {
		super(color);
		_position = position;
		_Kc = Kc;
		_Kl = Kl;
		_Kq = Kq;
		_radius = radius;
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

	/*
	 * @Override public List<LightSource> returnMultipleLights(int samples) {
	 * List<LightSource> additionalLights = new ArrayList<LightSource>();
	 * additionalLights.add(new PointLight(_position, _Kc, _Kl, _Kq,
	 * _color.reduce(samples))); Random rand = new Random(); Vector z = new
	 * Vector(0, 0, 1); Vector x = new Vector(1, 0, 0); Vector y = new Vector(0, 1,
	 * 0); for (int i = 0; i < samples; i++) additionalLights .add(new
	 * PointLight(_position.add(x.scale(rand.nextInt(10) +
	 * 1)).add(y.scale(rand.nextInt(10) + 1)) .add(z.add(z.scale(rand.nextInt(10) +
	 * 1))), _Kc, _Kl, _Kq, _color.reduce(samples))); return additionalLights;
	 * 
	 * }
	 */
/**
 * @param normal
 * @return 
 * */
	public List<Point3D> lightSamples(Vector normal) {
		normal = normal.normalize();
		List<Point3D> samples = new ArrayList<>();
		samples.add(_position);
		double d = -normal.dotProduct(new Vector(_position));
		Double xI = Coordinate.isZero(normal.getX().getNum()) || Coordinate.isZero(d) ? null
				: -d / normal.getX().getNum();
		Double yI = Coordinate.isZero(normal.getY().getNum()) || Coordinate.isZero(d) ? null
				: -d / normal.getY().getNum();
		Double zI = Coordinate.isZero(normal.getZ().getNum()) || Coordinate.isZero(d) ? null
				: -d / normal.getZ().getNum();

		Vector v1, v2;

		if (xI == null || yI == null || zI == null) {
			samples.add(_position.add(new Vector(1, 1, 1)));
			samples.add(_position.add(new Vector(-1, -1, -1)));
			samples.add(_position.add(new Vector(1, -1, -1)));
			samples.add(_position.add(new Vector(-1, 1, -1)));
			samples.add(_position.add(new Vector(-1, -1, 1)));
			samples.add(_position.add(new Vector(-1, 1, 1)));
			samples.add(_position.add(new Vector(1, -1, 1)));
			samples.add(_position.add(new Vector(1, 1, -1)));
			return samples;
		}
		Point3D _1 = new Point3D(0, 0, zI);
		Point3D _2 = new Point3D(0, yI, 0);
		if (_1.equals(_2)) {
			throw new IllegalArgumentException("Frick");
		}
		Point3D _3 = new Point3D(xI, 0, 0);
		v1 = _1.subtract(_2).normalize();
		v2 = _3.subtract(_2).normalize();
		samples.add(new Point3D(_position).add(v1.scale(_radius)));
		samples.add(new Point3D(_position.add(v1.scale(-_radius))));
		samples.add(new Point3D(_position.add(v2.scale(_radius))));
		samples.add(new Point3D(_position.add(v2.scale(-_radius))));
		samples.add(new Point3D(_position).add(v1.scale(_radius / 2)));
		samples.add(new Point3D(_position.add(v1.scale(-_radius / 2))));
		samples.add(new Point3D(_position.add(v2.scale(_radius / 2))));
		samples.add(new Point3D(_position.add(v2.scale(-_radius / 2))));
		double _radiusCos =_radius * Math.cos(45);
		double _radiusSin =_radius * Math.sin(45);
		samples.add(new Point3D(_position.add(v1.scale(_radiusSin).add(v2.scale(_radiusCos)))));
		samples.add(
				new Point3D(_position.add(v1.scale(-_radiusSin).add(v2.scale(_radiusCos)))));
		samples.add(
				new Point3D(_position.add(v1.scale(_radiusSin).add(v2.scale(-_radiusCos)))));
		samples.add(
				new Point3D(_position.add(v1.scale(-_radiusSin).add(v2.scale(-_radiusCos)))));
		samples.add(new Point3D(
				_position.add(v1.scale(_radiusSin / 2).add(v2.scale(_radiusCos / 2)))));
		samples.add(new Point3D(
				_position.add(v1.scale(-_radiusSin / 2).add(v2.scale(_radiusCos / 2)))));
		samples.add(new Point3D(
				_position.add(v1.scale(_radiusSin/ 2).add(v2.scale(-_radiusCos / 2)))));
		samples.add(new Point3D(
				_position.add(v1.scale(-_radiusSin / 2).add(v2.scale(-_radiusCos / 2)))));
		return samples;
	}
}
