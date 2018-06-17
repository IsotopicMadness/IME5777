 package unittests;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class RenderTest_AmbientLight {
	@Test
	public void basicRendering() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
		scene.setScreenDistance(150);
		scene.setBackground(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.setGeomtries(geometries);

		geometries
				.add(new Sphere(new Point3D(0, 0, 150), 50.0, new Color(34, 70, 166), new Material(1, 1, 20, 0, 0.5)));

		geometries.add(new Triangle(new Point3D(100, 0, 149), new Point3D(0, 100, 149), new Point3D(100, 100, 149),
				new Color(0, 255, 0), new Material(1, 1, 20, 0, 0.5)));

		geometries.add(new Triangle(new Point3D(100, 0, 149), new Point3D(0, -100, 149), new Point3D(100, -100, 149),
				new Color(0, 0, 255), new Material(1, 1, 20, 0, 0.5)));

		geometries.add(new Triangle(new Point3D(-100, 0, 149), new Point3D(0, 100, 149), new Point3D(-100, 100, 149),
				new Color(255, 0, 0), new Material(1, 1, 20, 0, 0.5)));

		geometries.add(new Triangle(new Point3D(-100, 0, 149), new Point3D(0, -100, 149), new Point3D(-100, -100, 149),
				new Color(0, 255, 0), new Material(1, 1, 20, 0, 0.5)));

		ImageWriter imageWriter = new ImageWriter("RenerTestAmbient", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.printGrid(50);
		render.getImageWriter().writeToImage();
	}
}