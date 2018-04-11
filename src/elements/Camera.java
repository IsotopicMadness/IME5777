/**
 * 
 */
package elements;
import geometries.*;
import primitives.*;
/**
 * @author amich
 *
 */
public class Camera {

	private Point3D p0;
	Vector vUp;
	Vector vTo;
	Vector vRight;	
	/**
	 * @throws Exception 
	 * 
	 */
	public Camera(Point3D p0, Vector vUp, Vector vTo/*, Vector vRight*/) throws Exception {
		this.p0 = new Point3D(p0);
		this.vUp = new Vector(vUp);
		this.vTo = new Vector(vTo);
		//this.vRight = new Vector(vRight);
		if(!(vUp.dotProduct(vTo)==0))
			throw new Exception();
		else
			vRight = vUp.crossProduct(vTo);
	}

}
