package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class RenderTestAdditionalLighting {
	@Test
	public void basicRendering() {
		Scene scene = new Scene("Test scene 2");
		/*
		 * scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new
		 * Vector(0, 0, -1))); scene.setAmbientLight(new AmbientLight(new Color(255,
		 * 255, 255), 0)); scene.setScreenDistance(150); scene.setBackground(new
		 * Color(34, 0, 0)); Geometries geometries = new Geometries();
		 * scene.setGeomtries(geometries); ArrayList<Light> lights = new ArrayList<>();
		 * scene.setLights(lights); lights.add(new SpotLight(new Color(255, 255, 255),
		 * 1.0, 0.22, 0.5, new Point3D(0, 0, 2), new Vector(0, 0, -1)));
		 * 
		 * geometries.add(new Sphere(new Point3D(0, 0, 150), 75.0, new Color(34, 70,
		 * 166), new Material(1, 1, 1)));
		 */
		ImageWriter imageWriter = new ImageWriter("test2", 500, 500, 500, 500);

		scene.setScreenDistance(120);
		scene.setBackground(new Color(0, 0, 0));
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1)));
		scene.setAmbientLight(new AmbientLight(new Color(30, 30, 30), 1));
		scene.addLights(new SpotLight(new Color(100, 100, 255), 1.0, 1.0, 1.7, new Point3D(-3, -2, -2),
				new Vector(new Point3D(0, 0, -10).subtract(new Point3D(-3, -2, -2)))));
		scene.addLights(new PointLight(new Color(255, 255, 255), 1, 1, 1, new Point3D(-3, -2, -2)));

		scene.addGeometry(new Sphere(new Point3D(0, 0, -10), 6, new Color(0, 0, 100), new Material(1, 1, 1, 0, 0)));
		scene.addGeometry(new Triangle(new Point3D(-2, -2, -4), new Point3D(2, 0, -4), new Point3D(0, 0, -4),
				new Color(255, 0, 0), new Material(1, 1, 1, 1, 1)));
		scene.addGeometry(new Plane(new Point3D(0, 0, -20), new Vector(0, 0, -1), new Color(200, 200, 50).scale(0.25),
				new Material(1, 1, 1, 0.56, 0.25)));
		Render r = new Render(imageWriter, scene);
		r.renderImage();
		r.getImageWriter().writeToImage();

		// Render render = new Render(imageWriter, scene);

		// render.renderImage();
		// render.getImageWriter().writeToImage();
	}
}