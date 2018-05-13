package unittests;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class RenderTest {
	@Test
	public void basicRendering(){
		Scene scene = new Scene("Test scene");
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1)));
		scene.setAmbientLight(new AmbientLight(new Color(255,255,255), 1));
		scene.setScreenDistance(100);
		scene.setBackground( new Color(0, 0, 0).getColor());
		Geometries geometries = new Geometries();
		
		geometries.add(new Sphere(new Point3D(0, 0, -10), 9.9));
		
		geometries.add(new Sphere(new Point3D(0, 0, 150), 50.0));
		
		geometries.add(new Triangle(new Point3D( 100, 0, 149),
				 							new Point3D(  0, 100, 149),
				 							new Point3D( 100, 100, 149)));
		
		geometries.add(new Triangle(new Point3D( 100, 0, 149),
				 			 				new Point3D(  0, -100, 149),
				 			 				new Point3D( 100,-100, 149)));
		
		geometries.add(new Triangle(new Point3D(-100, 0, 149),
				 							new Point3D(  0, 100, 149),
				 							new Point3D(-100, 100, 149)));
		
		geometries.add(new Triangle(new Point3D(-100, 0, 149),
				 			 				new Point3D(  0,  -100, 149),
				 			 				new Point3D(-100, -100, 149)));
		
		scene.setGeomtries(geometries);
		ImageWriter imageWriter = new ImageWriter("test0", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);
		
		render.renderImage();
		render.printGrid(50);
		render.getImageWriter().writeToImage();
	}
}