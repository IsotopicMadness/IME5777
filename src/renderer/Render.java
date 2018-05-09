package renderer;

import java.util.ArrayList;

import primitives.*;
import scene.Scene;

public class Render {
	private Scene _scene;
	private ImageWriter _imageWriter;
	private Color _color;

	/************** operations *******/
	// calc the exact color of the point that we need
	private Color calcColor(Point3D point) {
		_color.setColor(_scene.get_ambientLight().getIntensity());//עמיחי,זה אשמת ניצן שלא עשה גטאמביאנט
		return _color;
	}

		printImage(int interval){};
		
		renderImage();
		
		//Shallow copy. Will be changed
		public Point3D getClosestPoint(ArrayList<Point3D> points){
			Point3D rePoint = new Point3D(0,0,0);
			double distance = Double.MAX_VALUE;
			for(Point3D p : points) {
				if(p.distance(_scene.getCamera().getP0())< distance) {
					distance = p.distance(_scene.getCamera().getP0());
					rePoint = p;
				}
			}
			return rePoint;
		}

}
