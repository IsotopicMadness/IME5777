/**
 * 
 */
package elements;

import java.util.List;

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
	public List<LightSource> returnMultipleLights() {
		
	}

}
