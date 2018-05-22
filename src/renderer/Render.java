package renderer;

import java.util.ArrayList;
import elements.*;
import java.util.HashMap;
import java.util.Map.Entry;

import primitives.*;
import geometries.*;
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
	private Color calcColor(Geometry geo, Point3D point, ArrayList<Light> lights) {
		if(geo==null || point ==null)
			throw new IllegalArgumentException("Geometry or Point not found");
		
		_color = new Color(_scene.getAmbientLight().getIntensity(point).add(geo.getEmmission()));
		Color color = new Color(_color);
		
		Vector v = new Vector(point.subtract(_scene.getCamera().getP0()));
		Vector n = geo.getNormal();
		int nShininess = geo.getShininess();
		double kd = geo.getKd(); double ks = geo.getKs();
		for (Light lightSource : _scene.getLights()) {
			Vector l = lightSource.getL(point);
			if (n.dotProduct(l)*n.dotProduct(v) > 0) {
				Color lightIntensity = lightSource.getIntensity(point);
				color.add(calcDiffusive(kd, l, n, lightIntensity),
						calcSpecular(ks, l, n, v, nShininess, lightIntensity));
			}
		}
		return _color;

	}
	
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r = new Vector(n.add(n.add(l)));
		return 	lightIntensity.scale(ks*Math.pow((Math.abs(v.dotProduct(r))),nShininess));
	}
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		return lightIntensity.scale((Math.abs(l.dotProduct(n))*kd));
	}
	public ImageWriter getImageWriter() {
		return _imageWriter;
	}

		/**
		 * Renders every the image
		 */
		public void renderImage(){

			for(int i = 0; i<_imageWriter.getNx();++i) {
			
				for(int j = 0; j<_imageWriter.getNy();++j) {
					Ray ray = _scene.getCamera().constructRayThroughPixel
						(_imageWriter.getNx(), _imageWriter.getNy() , i, j,
								_scene.getScreenDistance(),
								_imageWriter.getWidth(), _imageWriter.getHeight());
					
					HashMap<Geometry,ArrayList<Point3D>> intersectionPoints = new HashMap<>(); 
							intersectionPoints.putAll(_scene.getGeometries().findIntersections(ray));
					
					if(intersectionPoints.size()==0)
						_imageWriter.writePixel(i, j, _scene.getBackground().getColorArray());
					
					else {
						HashMap<Geometry,Point3D> closestPoint = new HashMap<>();
								closestPoint.putAll(getClosestPoint(intersectionPoints));
								
								//In order to avoid compilation error arguments must be initialised with a value
								Geometry geo = null;
								Point3D p = null;
								
								for(Entry<Geometry,Point3D> ed : closestPoint.entrySet()) {
									geo = ed.getKey();
									p = ed.getValue();
								}
								
								_imageWriter.writePixel(i,j, calcColor(geo, p, _scene.getLights()).getColor());
					}
				}
			}
		}
		
		
		public HashMap<Geometry,Point3D> getClosestPoint(HashMap<Geometry,ArrayList<Point3D>> points){
			HashMap<Geometry, Point3D> rePoint = new HashMap<>();
			
			double distance = Double.MAX_VALUE;
			
			for(Entry<Geometry, ArrayList<Point3D>> ed : points.entrySet()) {
			Geometry k = ed.getKey();
			ArrayList<Point3D> v = ed.getValue();
				for(Point3D p : v) {
					if(p.distance(_scene.getCamera().getP0())< distance) { 
						distance = p.distance(_scene.getCamera().getP0());
						rePoint.clear();
						rePoint.put(k, p);
					}
				}
			}
			return rePoint;
			
		}
		
		public void printGrid(int x) {
			for (int i = 0; i<_imageWriter.getNx(); i++) {
				for (int j=0;j<_imageWriter.getNy();j++) {
					if(i%x == 0 || j%x == 0)
						_imageWriter.writePixel(i, j, 255,255,255);
				}
			}
			
		}

	
}
