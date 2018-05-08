package renderer;

import primitives.Color;
import primitives.Point3D;
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
		
		getClosestPoint(List<Point3D> points): Point3D;

}
