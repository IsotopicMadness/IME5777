/**
 * 
 */
package elements;

import java.util.List;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public interface LightSource {
	public Color getIntensity(Point3D p);

	/**
	 * returns the normalized light vector
	 * 
	 * @param p
	 * @return new Vector
	 */
	public Vector getL(Point3D p);

	/**
	 * returns direction
	 * 
	 * @param p
	 * @return
	 */
	public Vector getD(Point3D p);
	
	/**
	 * returns multiple samples of the light source
	 * 
	 * @param Vector
	 *            normal - should point towards the original point
	 * @return List<Point3D>
	 */
	public List<Point3D> lightSamples(Vector normal);

	/**(failed)
	 * Returns multiple instances of the same light but in a different location. The
	 * amount of additional lights will be t=determined by the given param All
	 * lights will be around the original light in a radius of ten.
	 * 
	 * @param samples
	 *            - the amount of additional lights
	 * @return List of lights
	 */
	//public List<LightSource> returnMultipleLights(int samples);
}
