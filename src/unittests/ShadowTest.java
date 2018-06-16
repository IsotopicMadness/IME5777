package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import elements.SpotLight;
import geometries.Geometries;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class ShadowTest {

	@Test
	public void shadowTest() {
		Scene scene = new Scene("Test shadow");
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setScreenDistance(100);
		scene.setBackground(new Color(0, 0, 0));
		scene.setAmbientLight(new AmbientLight());

		Geometries geometries = new Geometries();
		Triangle triangle1 = new Triangle(new Point3D(-250, -250, 120), new Point3D(-250, 250, 120),
				new Point3D(250, -250, 120), new Color(0, 0, 0), new Material(0.9, 0.8, 100, 0, 0));
		Triangle triangle2 = new Triangle(new Point3D(250, 250, 120), new Point3D(-250, 250, 120),
				new Point3D(250, -250, 120), new Color(0, 0, 0), new Material(0.9, 0.8, 100, 0, 0));

		Sphere sphere = new Sphere(new Point3D(0, 0, 80), 60, new Color(0, 0, 70), new Material(0.9, 0.5, 30, 0, 0));

		geometries.add(triangle1);
		geometries.add(sphere);
		geometries.add(triangle2);

		scene.setGeomtries(geometries);
		List<LightSource> lights = new ArrayList<LightSource>();
		SpotLight spotLight = new SpotLight(new Point3D(50, -1, -32), 1, 0, 0.69, new Color(200, 200, 200),
				new Vector(-25, 0, 80));
		lights.add(spotLight);
		scene.setLights(lights);

		ImageWriter imageWriter = new ImageWriter("shadow test", 500, 500, 500, 500);
		Render testRender = new Render(imageWriter, scene);

		testRender.renderImage();
		testRender.getImageWriter().writeToImage();

	}

}
