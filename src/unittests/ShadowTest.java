package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import elements.PointLight;
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
	public void recursiveTest() {

		Scene scene = new Scene("recursive1");
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1)));
		scene.setScreenDistance(300);
		scene.setBackground(new Color(0, 0, 0));
		scene.setAmbientLight(new AmbientLight());

		Sphere sphere = new Sphere(new Point3D(0.0, 0.0, -1000), 500, new Color(0, 0, 100),
				new Material(0.5, 0.3, 10, 0, 0.5));

		scene.addGeometry(sphere);
		Sphere sphere2 = new Sphere(new Point3D(0.0, 0.0, -1000), 250, new Color(100, 20, 20),
				new Material(0.4, 0.4, 10, 0.5, 0));
		scene.addGeometry(sphere2);

		List<LightSource> lights = new ArrayList<LightSource>();
		scene.setLights(lights);
		lights.add(new SpotLight(new Point3D(-200, -200, -150), 0.1, 0.01, 0.0025, new Color(255, 100, 100),
				new Vector(2, 2, -3).scale(-1)));
		//lights.add(new PointLight(new Point3D(-200,-200,-150), 0.10, 0.01, 0.0025, new Color(255, 100, 100)));

		ImageWriter imageWriter = new ImageWriter("Recursive Test", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.getImageWriter().writeToImage();
		;

	}

}
