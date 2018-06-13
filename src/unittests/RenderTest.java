package unittests;

import java.util.ArrayList;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class RenderTest {
	@Test
	public void basicRendering() {
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
		scene.setScreenDistance(150);
		scene.setBackground(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.setGeomtries(geometries);
		List<Light> lights = new ArrayList<>();
		scene.setLights(lights);
		lights.add(new SpotLight(new Color(255, 255, 255), 1.0, 1.0, 1.0, new Point3D(0, 0, 2), new Vector(0, 0, -1)));

		geometries.add(new Sphere(new Point3D(0, 0, 150), 50.0, new Color(34, 70, 166), new Material(1,1,1,0,0)));

		geometries.add(new Triangle(new Point3D(100, 0, 149), new Point3D(0, 100, 149), new Point3D(100, 100, 149),
				new Color(0, 255, 0), new Material(1,1,1,0,0)));

		geometries.add(new Triangle(new Point3D(100, 0, 149), new Point3D(0, -100, 149), new Point3D(100, -100, 149),
				new Color(0, 0, 255), new Material(1,1,1,0,0)));

		geometries.add(new Triangle(new Point3D(-100, 0, 149), new Point3D(0, 100, 149), new Point3D(-100, 100, 149),
				new Color(255, 0, 0), new Material(1,1,1,0,0)));

		geometries.add(new Triangle(new Point3D(-100, 0, 149), new Point3D(0, -100, 149), new Point3D(-100, -100, 149),
				new Color(0, 255, 0), new Material(1,1,1,0,0)));

		ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.printGrid(50);
		render.getImageWriter().writeToImage();
	}
}