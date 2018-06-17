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
	 * @param p
	 * @return new Vector
	 */
	public Vector getL(Point3D p);

	/**
	 * returns direction
	 * @param p
	 * @return
	 */
	public Vector getD(Point3D p);
	
	public List<LightSource> returnMultipleLights();
}
