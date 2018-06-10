package unittests;

import primitives.*;

import geometries.*;

import elements.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class PlaneTest {

	/*
	 * @Test public void testGetNormalPoint3D() { fail("Not yet implemented"); }
	 */

	@Test
	public void testIntersectionPoints() {
		final int WIDTH = 3;
		final int HEIGHT = 3;
		Ray[][] rays = new Ray[HEIGHT][WIDTH];
		Camera camera = new Camera(new Point3D(0.0, 0.0, 0.0),

				new Vector(0.0, 1.0, 0.0), new Vector(0.0, 0.0, -1.0));

		// plane orthogonal to the view plane
		Plane plane = new Plane(new Point3D(0.0, 0.0, -3.0), new Vector(0.0, 0.0, -1.0), new Color(),
				new Material(1, 1, 1, 1, 1));

		// 45 degrees to the view plane
		Plane plane2 = new Plane(new Point3D(0.0, 0.0, -3.0), new Vector(0.0, 0.25, -1.0), new Color(),
				new Material(1, 1, 1, 1, 1));

		ArrayList<Point3D> intersectionPointsPlane = new ArrayList<Point3D>();
		ArrayList<Point3D> intersectionPointsPlane2 = new ArrayList<Point3D>();

		System.out.println("Camera:\n" + camera);

		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				rays[i][j] = camera.constructRayThroughPixel(WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);

				Map<Intersectable, List<Point3D>> rayIntersectionPoints = new HashMap<>();
				rayIntersectionPoints.putAll(plane.findIntersection(rays[i][j]));
				Map<Intersectable, List<Point3D>> rayIntersectionPoints2 = new HashMap<>();
				rayIntersectionPoints2.putAll(plane2.findIntersection(rays[i][j]));

				rayIntersectionPoints.forEach((k, v) -> {
					intersectionPointsPlane.addAll(v);
				});

				rayIntersectionPoints2.forEach((k, v) -> {
					intersectionPointsPlane2.addAll(v);
				});

			}
		}
		assertTrue(intersectionPointsPlane.size() == 9);
		assertTrue(intersectionPointsPlane2.size() == 9);

		for (Point3D iPoint : intersectionPointsPlane)
			System.out.println(iPoint);

		System.out.println("---");

		for (Point3D iPoint : intersectionPointsPlane2)
			System.out.println(iPoint);

	}

	@Test
	public void testEqualsObject() {

		Plane p1 = new Plane(new Point3D(0.0, 0.0, -3.0), new Vector(0.0, 0.25, -1.0), new Color(),
				new Material(1, 1, 1, 1, 1));
		Plane p2 = new Plane(new Point3D(0.0, 0.0, 0.0000001), new Vector(0.0, 0.25, -0.999999999999), new Color(),
				new Material(1, 1, 1, 1, 1));
		Plane p3 = new Plane(new Point3D(1.0, 3.0, -3.0), new Vector(0.0, 0.25, -1.0), new Color(),
				new Material(1, 1, 1, 1, 1));

		assertTrue(!p1.equals(p2));
		assertTrue(!p1.equals(p3));
	}

	/*
	 * @Test public void testPlaneCctr() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testPlane3Point3D() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testGetPoint() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testGetNormal() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testToString() { fail("Not yet implemented"); }
	 */

}
