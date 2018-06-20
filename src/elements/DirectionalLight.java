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

public class DirectionalLight extends Light implements LightSource {
	private Vector _direction;

	/********** Constructors ***********/
	public DirectionalLight(Vector direction, Color color) {
		super(color);
		_direction = direction.normalize();
	}

	/************** Getters/Setters *******/
	public Vector get_direction() {
		return _direction;
	}

	public void set_direction(Vector direction) {
		_direction = direction;
	}

	/************** Operations ***************/
	@Override
	public Color getIntensity(Point3D p) {
		return getIntensity();
	}

	@Override
	public Vector getL(Point3D p) {
		return getD(p).normalize();
	}

	@Override
	public Vector getD(Point3D p) {
		return _direction;
	}

	@Override
	public List<Point3D> lightSamples(Vector normal) {
		normal = normal.normalize();
		List<Point3D> points = new ArrayList<Point3D>();
		Point3D point = new Point3D(_direction.scale(-1 * Double.MAX_VALUE / 2));
		points.add(point);
		double d = -normal.dotProduct(new Vector(point));
		Double xI = normal.getX().getNum() == 0 ? null : -d / normal.getX().getNum();
		Double yI = normal.getY().getNum() == 0 ? null : -d / normal.getY().getNum();
		Double zI = normal.getZ().getNum() == 0 ? null : -d / normal.getZ().getNum();
		Vector v1, v2;
		if (xI == null && yI != null && zI != null) {
			v1 = new Point3D(0, 0, zI).subtract(new Point3D(0, yI, 0)).normalize();
			v2 = new Point3D(0, yI, 0).subtract(point).normalize();
		}
		if (xI != null && yI == null && zI != null) {
			v1 = new Point3D(0, 0, zI).subtract(new Point3D(xI, 0, 0)).normalize();
			v2 = new Point3D(0, xI, 0).subtract(point).normalize();
		}
		if (xI != null && yI != null && zI == null) {
			v1 = new Point3D(xI, 0, 0).subtract(new Point3D(0, yI, 0)).normalize();
			v2 = new Point3D(0, yI, 0).subtract(point).normalize();
		}
		if(xI != null && yI == null && zI == null || xI == null && yI != null && zI == null || xI == null && yI == null && zI != null)
		{
			points.add(point.add(new Vector(1,1,1)));
			points.add(point.add(new Vector(-1,-1,-1)));
			points.add(point.add(new Vector(1,-1,-1)));
			points.add(point.add(new Vector(-1,1,-1)));
			points.add(point.add(new Vector(-1,-1,1)));
			points.add(point.add(new Vector(-1,1,1)));
			points.add(point.add(new Vector(1,-1,1)));
			points.add(point.add(new Vector(1,1,-1)));
			return points;
		}
		Point3D _1 = new Point3D(0, 0, zI);
		Point3D _2 = new Point3D(0, yI, 0);
		Point3D _3 = new Point3D(xI, 0, 0);
		v1 = _1.subtract(_2).normalize();
		v2 = _3.subtract(_2).normalize();
		points.add(new Point3D(point).add(v1.scale(5)));
		points.add(new Point3D(point.add(v1.scale(-5))));
		points.add(new Point3D(point.add(v2.scale(5))));
		points.add(new Point3D(point.add(v2.scale(-5))));
		points.add(new Point3D(point).add(v1.scale(5 / 2)));
		points.add(new Point3D(point.add(v1.scale(-5 / 2))));
		points.add(new Point3D(point.add(v2.scale(5 / 2))));
		points.add(new Point3D(point.add(v2.scale(-5 / 2))));
		points.add(new Point3D(point.add(v1.scale(5 * Math.sin(45)).add(v2.scale(5 * Math.cos(45))))));
		points.add(
				new Point3D(point.add(v1.scale(-5 * Math.sin(45)).add(v2.scale(5 * Math.cos(45))))));
		points.add(
				new Point3D(point.add(v1.scale(5 * Math.sin(45)).add(v2.scale(-5 * Math.cos(45))))));
		points.add(
				new Point3D(point.add(v1.scale(-5 * Math.sin(45)).add(v2.scale(-5 * Math.cos(45))))));
		points.add(new Point3D(
				point.add(v1.scale(5 * Math.sin(45) / 2).add(v2.scale(5 * Math.cos(45) / 2)))));
		points.add(new Point3D(
				point.add(v1.scale(-5 * Math.sin(45) / 2).add(v2.scale(5 * Math.cos(45) / 2)))));
		points.add(new Point3D(
				point.add(v1.scale(5 * Math.sin(45) / 2).add(v2.scale(-5 * Math.cos(45) / 2)))));
		points.add(new Point3D(
				point.add(v1.scale(-5 * Math.sin(45) / 2).add(v2.scale(-5 * Math.cos(45) / 2)))));
		return points;
	}

	/*
	 * @Override public List<LightSource> returnMultipleLights(int samples) {
	 * List<LightSource> lst = new ArrayList<LightSource>(); lst.add(this); return
	 * lst;
	 * 
	 * }
	 */

}
