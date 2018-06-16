package elements;

import primitives.*;

/**
 * Camera. Used as a substitute for a real eye. Used mainly for the math used to
 * create the image through the grid
 *
 */

public class Camera {

	private Point3D p0;
	Vector vUp;
	Vector vTo;
	Vector vRight;

	/**
	 * Constructor. Receives the camera's location (p0), Upwards vector (vUp), The
	 * vector that points towards the grid/screen (vTo) and calculates vRight, the
	 * vector Vertical to both vTo and vUp.
	 */
	public Camera(Point3D p0, Vector vUp, Vector vTo) {
		this.p0 = new Point3D(p0);
		this.vUp = new Vector(vUp).normalize();
		this.vTo = new Vector(vTo).normalize();

		// if the two vectors are not vertical to each other throw an exception
		if (!(vUp.dotProduct(vTo) == 0))
			throw new IllegalArgumentException();
		else
			vRight = vUp.crossProduct(vTo).normalize();
	}

	public Camera(Camera other) {
		this.p0 = new Point3D(other.getP0());
		this.vUp = other.getvUp().normalize();
		this.vTo = other.getvTo().normalize();
		this.vRight = other.getvRight();
		// if the two vectors are not vertical to each other throw an exception
		if (!(vUp.dotProduct(vTo) == 0))
			throw new IllegalArgumentException();
		else
			vRight = vUp.crossProduct(vTo).normalize();
	}

	/**
	 * The function constructs a ray through a given pixel in the given grid
	 * 
	 * @param Nx
	 * @param Ny
	 * @param i
	 * @param j
	 * @param screenDistance
	 * @param screenWidth
	 * @param screenHeight
	 * @return
	 */

	public Ray constructRayThroughPixel(int Nx, int Ny, int i, int j, double screenDistance, double screenWidth,
			double screenHeight) {
		Point3D p = advanceRayToViewPlane(Nx, Ny, i, j, screenDistance, screenWidth, screenHeight);
		return new Ray(p.subtract(p0), p0);
	}

	public Point3D advanceRayToViewPlane(int Nx, int Ny, int i, int j, double screenDistance, double screenWidth,
			double screenHeight) {
		Point3D pC = p0.add(getvTo().scale(screenDistance));
		double Rx = screenWidth / Nx;
		double Ry = screenHeight / Ny;
		double y = (j - Ny / 2.0 + 0.5) * Ry;
		double x = (i - Nx / 2.0 + 0.5) * Rx;

		Point3D p = pC;
		if (x != 0.0)
			p = p.add(getvRight().scale(x));
		if (y != 0.0)
			p = p.add(getvUp().scale(-y));
		return new Point3D(p);
	}

	public Point3D getP0() {
		return p0;
	}

	public Vector getvTo() {
		return vTo;
	}

	public Vector getvRight() {
		return vRight;
	}

	public Vector getvUp() {
		return vUp;
	}

}
