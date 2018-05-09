package renderer;

import java.util.ArrayList;

import primitives.*;
import scene.Scene;

public class Render {
	private Scene _scene;
	private ImageWriter _imageWriter;
	private Color _color;

	public Render(ImageWriter _imageWriter, Scene _scene) {
		this._scene = new Scene(_scene);
		this._imageWriter = new ImageWriter(_imageWriter);
	}
	/************** operations *******/
	// calc the exact color of the point that we need
	private Color calcColor(Point3D point) {
		_color.setColor(_scene.getAmbientLight().getIntensity());//עמיחי,זה אשמת ניצן שלא עשה גטאמביאנט
		return _color;
	}

		printImage(int interval){}
		
		public void renderImage(){
			int i = _imageWriter.getWidth()/_imageWriter.getNx();
			int j = _imageWriter.getHeight()/_imageWriter.getNy();
			for(int k = 0; k<i;++k) {
			
				for(int l = 0; l<j;++l) {
					Ray ray = _scene.getCamera().constructRayThroughPixel
						(_imageWriter.getNx(), _imageWriter.getNy() , i, j,
								_scene.getScreenDistance(),
								_imageWriter.getWidth(), _imageWriter.getHeight());
					ArrayList<Point3D> intersectionPoints = _scene.getGeometries().findIntersections(ray);
				}
			}
		}
		
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
