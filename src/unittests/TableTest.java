/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittests;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;
import java.awt.Color;
import org.junit.Test;

/**
 *
 * @author david salmon
 */
public class TableTest {

    @Test
    public void TableMaker() throws Exception {
        Scene scene = new Scene(new AmbientLight(Color.BLACK, 0.1), Color.black, new Camera(), 49);
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1)));

// the surface
        Triangle triangle1 = new Triangle(new Point3D(-100, 50, -60),
                new Point3D(100, -50, -60),
                new Point3D(200, 50, -60));

        Triangle triangle2 = new Triangle(new Point3D(-100, 50, -60),
                new Point3D(100, -50, -60),
                new Point3D(-200, -50, -60));

        triangle1.setmmission(new Color(0, 0, 255));
        triangle2.setmmission(new Color(0, 0, 255));

//the sides of the table
        //the staright side
        Triangle triangle3 = new Triangle(new Point_3D(-200, -70, -60),
                new Point_3D(-200, -50, -60),
                new Point_3D(100, -50, -60));

        Triangle triangle4 = new Triangle(new Point_3D(-200, -70, -60),
                new Point_3D(100, -70, -60),
                new Point_3D(100, -50, -60));

        triangle3.setEmmission(new Color(0, 50, 255));
        triangle4.setEmmission(new Color(0, 50, 255));

        //the slant side
        Triangle triangle5 = new Triangle(new Point_3D(100, -50, -60),
                new Point_3D(100, -70, -60),
                new Point_3D(200, 50, -60));

        Triangle triangle6 = new Triangle(new Point_3D(200, 30, -60),
                new Point_3D(100, -70, -60),
                new Point_3D(200, 50, -60));

        triangle5.setEmmission(new Color(0, 50, 255));
        triangle6.setEmmission(new Color(0, 50, 255));

//the legs of the table
        //the right straight leg
        Triangle triangle7 = new Triangle(new Point_3D(80, -70, -60),
                new Point_3D(80, -200, -60),
                new Point_3D(100, -200, -60));

        Triangle triangle8 = new Triangle(new Point_3D(80, -70, -60),
                new Point_3D(100, -70, -60),
                new Point_3D(100, -200, -60));

        triangle7.setEmmission(new Color(0, 255, 255));
        triangle8.setEmmission(new Color(0, 255, 255));
        Triangle triangle9 = new Triangle(new Point_3D(100, -70, -60),
                new Point_3D(110, -60, -60),
                new Point_3D(100, -200, -60));

        Triangle triangle10 = new Triangle(new Point_3D(110, -180, -60),
                new Point_3D(110, -60, -60),
                new Point_3D(100, -200, -60));

        triangle9.setEmmission(new Color(255, 0, 0));
        triangle10.setEmmission(new Color(255, 0, 0));

        //the right slant leg
        Triangle triangle11 = new Triangle(new Point_3D(170, -120, -60),
                new Point_3D(190, -120, -60),
                new Point_3D(170, 0, -60));

        Triangle triangle12 = new Triangle(new Point_3D(190, 20, -60),
                new Point_3D(190, -120, -60),
                new Point_3D(170, 0, -60));

        triangle11.setEmmission(new Color(0, 255, 255));
        triangle12.setEmmission(new Color(0, 255, 255));
        Triangle triangle13 = new Triangle(new Point_3D(190, 20, -60),
                new Point_3D(200, 30, -60),
                new Point_3D(190, -120, -60));

        Triangle triangle14 = new Triangle(new Point_3D(200, -100, -60),
                new Point_3D(200, 30, -60),
                new Point_3D(190, -120, -60));

        triangle13.setEmmission(new Color(255, 0, 0));
        triangle14.setEmmission(new Color(255, 0, 0));

        //the left straight leg
        Triangle triangle15 = new Triangle(new Point_3D(-200, -70, -60),
                new Point_3D(-180, -200, -60),
                new Point_3D(-200, -200, -60));

        Triangle triangle16 = new Triangle(new Point_3D(-200, -70, -60),
                new Point_3D(-180, -200, -60),
                new Point_3D(-180, -70, -60));

        triangle15.setEmmission(new Color(0, 255, 255));
        triangle16.setEmmission(new Color(0, 255, 255));
        Triangle triangle17 = new Triangle(new Point_3D(-170, -70, -60),
                new Point_3D(-180, -70, -60),
                new Point_3D(-180, -200, -60));

        Triangle triangle18 = new Triangle(new Point_3D(-170, -70, -60),
                new Point_3D(-170, -180, -60),
                new Point_3D(-180, -200, -60));

        triangle17.setEmmission(new Color(255, 0, 0));
        triangle18.setEmmission(new Color(255, 0, 0));

        scene.addGeometry(triangle1);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);
        scene.addGeometry(triangle5);
        scene.addGeometry(triangle6);
        scene.addGeometry(triangle7);
        scene.addGeometry(triangle8);
        scene.addGeometry(triangle9);
        scene.addGeometry(triangle10);
        scene.addGeometry(triangle11);
        scene.addGeometry(triangle12);
        scene.addGeometry(triangle13);
        scene.addGeometry(triangle14);
        scene.addGeometry(triangle15);
        scene.addGeometry(triangle16);
        scene.addGeometry(triangle17);
        scene.addGeometry(triangle18);

        //SpotLight spotLight=new SpotLight(new Color(255, 48, 60), new Point_3D(0, 0, -5), new Vector(0, 0, -10),0.001, 0.01, 0.00001);
        //scene.addLight(spotLight);
        ImageWriter imageWriter = new ImageWriter("SpotLightTest_1", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render._imageWriter.writeToimage();
    }
}
