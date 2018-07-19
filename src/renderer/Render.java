package renderer;

import elements.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import primitives.*;
import geometries.*;
import scene.Scene;

public class Render {
	private Scene _scene;
	private ImageWriter _imageWriter;
	private final int MAX_LEVEL = 3;
	private final int SAMPLES = 8;
	private List<LightSource> softShadows;

	public Render(ImageWriter _imageWriter, Scene _scene) {
		this._scene = new Scene(_scene);
		this._imageWriter = new ImageWriter(_imageWriter);
	}

	/************** operations *******/
	private Color calcColor(Geometry geo, Point3D point, Ray ray) {
		return calcColor(geo, point, ray, MAX_LEVEL, 1);
	}

	/**
	 * calculates the exact color of the point
	 * 
	 * @param geo
	 * @param point
	 * @param inRay
	 * @param levels
	 * @param k
	 * @return
	 */
	private Color calcColor(Geometry geo, Point3D point, Ray inRay, int levels, double k) {

		if (levels == 0 || Coordinate.isZero(k))
			return _scene.getBackground();

		if (geo == null || point == null)
			throw new IllegalArgumentException("Geometry or Point not found");

		Color color = _scene.getAmbientLight().getIntensity();
		color = color.add(geo.getEmmission());

		Vector v = inRay.getDirection();
		Vector n = geo.getNormal(point);
		// if (Coordinate.isZero(v.dotProduct(n)))
		// return _scene.getBackground();
		int nShininess = geo.getShininess();
		double kd = geo.getKd();
		double ks = geo.getKs();
		for (LightSource ls : _scene.getLights()) {
			Vector l = ls.getL(point);
			// occlusion
			if (n.dotProduct(l) * n.dotProduct(v) > 0) {
				double o = occluded(l, geo, point);
				o = softShadowing(o, l, point);
				if (!Coordinate.isZero(o * k)) {
					Color lightIntensity = new Color(ls.getIntensity(point)).scale(o);
					color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}

		// recrusive call for the reflections
		Ray reflectedRay = constructReflectedRay(n, point, inRay);
		Map<Intersectable, List<Point3D>> reflectedIntersectionPoints = new HashMap<Intersectable, List<Point3D>>();
		reflectedIntersectionPoints.putAll(_scene.findRayIntersections(reflectedRay));
		Color reflected;
		if (reflectedIntersectionPoints.isEmpty()) {
			reflected = _scene.getBackground();
		} else {
			Point3D reflectedPoint = null;
			Geometry reflectedGeo = null;
			Map<Intersectable, Point3D> refMap = getClosestPoint(reflectedIntersectionPoints);
			for (Entry<Intersectable, Point3D> p : refMap.entrySet()) {
				reflectedGeo = (Geometry) p.getKey();
				reflectedPoint = p.getValue();
			}
			double kr = geo.get_Kr();
			reflected = calcColor(reflectedGeo, reflectedPoint, reflectedRay, levels - 1, k * kr).scale(kr);

		}

		// refraction
		Ray refractedRay = constructRefractedRay(point, inRay);
		Map<Intersectable, List<Point3D>> refractedIntersectionPoints = new HashMap<Intersectable, List<Point3D>>(
				_scene.findRayIntersections(refractedRay));

		Color refracted = new Color();
		if (refractedIntersectionPoints.isEmpty()) {
			refracted = _scene.getBackground();
		} else {
			Map<Intersectable, Point3D> refractedPoint = getClosestPoint(refractedIntersectionPoints);
			double kt = geo.get_Kt();
			for (Entry<Intersectable, Point3D> p : refractedPoint.entrySet()) {
				if (!(p.getKey() instanceof Geometry))
					throw new IllegalArgumentException("Must be Geometry");
				refracted = calcColor((Geometry) p.getKey(), p.getValue(), refractedRay, levels - 1, k * kt).scale(kt);
			}
		}
		color.add(reflected, refracted);
		return color;

	}

	/**
	 * calculates the value of o after sampling the light sources
	 * 
	 * @param o
	 * @return double
	 */
	private double softShadowing(double o, Vector normal, Point3D point) {

		normal = normal.scale(-1);
		List<Point3D> lightPoints = new ArrayList<Point3D>();
		for (LightSource l : _scene.getLights()) {
			lightPoints.addAll(l.lightSamples(normal));
		}
		int counter = 0;
		for (Point3D p : lightPoints) {
			Map<Intersectable, List<Point3D>> intersectionPoints = _scene.getGeometries()
					.findIntersection(new Ray(p.subtract(point), point));
			if (intersectionPoints.isEmpty())
				counter++;
		}
		double coveragePercentage = (double)counter / (double)lightPoints.size();
		if (coveragePercentage > 1 || coveragePercentage < 0)
			throw new IllegalArgumentException("Counter can't be bigger than points");
		return o * coveragePercentage;
	}

	/**
	 * constructs the reflected ray
	 * 
	 * @param Vector
	 *            n
	 * @param Point3D
	 *            p
	 * @param Ray
	 *            inRay
	 * @return Ray
	 */
	private Ray constructReflectedRay(Vector n, Point3D point, Ray ray) {
		Vector v = ray;
		Vector r = v.subtract(n.scale(2 * (v.dotProduct(n)))).normalize();
		return new Ray(r, point);
	}

	/**
	 * constructs refracted ray
	 * 
	 * @param point
	 * @param ray
	 * @return
	 */
	private Ray constructRefractedRay(Point3D point, Ray ray) {
		Ray refracted = new Ray(ray.getDirection(), point);
		return refracted;
	}

	/**
	 * Calculates the specular light
	 * 
	 * @param double
	 *            ks
	 * @param Vector
	 *            l
	 * @param Vector
	 *            n
	 * @param Vector
	 *            v
	 * @param int
	 *            nShininess
	 * @param Color
	 *            lightIntensity
	 * @return Color
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r = l.add(n.scale(-2 * l.dotProduct(n))).normalize();
		double vr = v.dotProduct(r);
		if (vr > 0)
			return new Color(0, 0, 0);
		return new Color(lightIntensity).scale(ks * Math.pow(Math.abs(r.dotProduct(v)), nShininess));
	}

	/**
	 * Calculates the diffusive light
	 * 
	 * @param double
	 *            kd
	 * @param Vector
	 *            l
	 * @param Vector
	 *            n
	 * @param Color
	 *            lightIntensity
	 * @return Color
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		return new Color(lightIntensity).scale(kd * Math.abs(l.dotProduct(n)));
	}

	public ImageWriter getImageWriter() {
		return _imageWriter;
	}

	private double occluded(Vector l, Geometry geo, Point3D point) {

		Vector lightDirection = l.scale(-1); // from point to light source
		Vector normal = geo.getNormal(point);
		Vector epsVector = normal.scale(normal.dotProduct(lightDirection) > 0 ? 2 : -2);
		Point3D geometryPoint = point.add(epsVector);
		Ray lightRay = new Ray(lightDirection, geometryPoint);

		double occlusionK = 1;
		Map<Intersectable, List<Point3D>> intersectionPoints = _scene.getGeometries().findIntersection(lightRay);
		for (Map.Entry<Intersectable, List<Point3D>> entry : intersectionPoints.entrySet()) {
			occlusionK *= ((Geometry) entry.getKey()).get_Kt();
		}

		return occlusionK;
	}

	/**
	 * Renders the image
	 */
	public void renderImage() {
		for (int i = 0; i < _imageWriter.getNx(); i++) {
			for (int j = 0; j < _imageWriter.getNy(); j++) {
				Ray ray = _scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(), i, j,
						_scene.getScreenDistance(), _imageWriter.getWidth(), _imageWriter.getHeight());
				Map<Intersectable, List<Point3D>> intersectionsPoints = new HashMap<Intersectable, List<Point3D>>(
						_scene.getGeometries().findIntersection(ray));
				Map<Intersectable, Point3D> closestPoint = getClosestPoint(intersectionsPoints);
				if (intersectionsPoints.values().isEmpty() || intersectionsPoints == null || closestPoint == null)
					_imageWriter.writePixel(i, j, _scene.getBackground().getColor());
				else {
					Geometry geom = (Geometry) closestPoint.keySet().toArray()[0];
					Point3D poi = closestPoint.get(geom);
					_imageWriter.writePixel(i, j, calcColor(geom, poi, ray).getColor()); // calcColor(closestPpoint)
				}
			}
			System.err.println(i + "/" + _imageWriter.getNx());
		}

	}
	/*public void renderImage() {

		// softShadowsLightGenerator(SAMPLES);
		for (int j = 0; j < _imageWriter.getNy(); ++j) {
			for (int i = 0; i < _imageWriter.getNx(); ++i) {

				Ray ray = _scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(), i, j,
						_scene.getScreenDistance(), _imageWriter.getWidth(), _imageWriter.getHeight());

				Map<Intersectable, List<Point3D>> intersectionPoints = new HashMap<>();
				intersectionPoints.putAll(_scene.getGeometries().findIntersection(ray));

				if (intersectionPoints.values().isEmpty())
					_imageWriter.writePixel(i, j, _scene.getBackground().getColorArray());

				else {
					Map<Intersectable, Point3D> closestPoint = new HashMap<>();
					closestPoint.putAll(getClosestPoint(intersectionPoints));

					// In order to avoid compilation error arguments must be initialised with a
					// value
					Geometry geo = null;
					Point3D p = null;

					for (Entry<Intersectable, Point3D> ed : closestPoint.entrySet()) {
						if (!(ed.getKey() instanceof Geometry))
							throw new IllegalArgumentException("Must be Geometry");
						geo = (Geometry) ed.getKey();
						p = ed.getValue();
					}

					_imageWriter.writePixel(i, j, calcColor(geo, p,
							new Ray(p.subtract(_scene.getCamera().getP0()).normalize(), _scene.getCamera().getP0()),
							MAX_LEVEL, 1.0).getColor());
				}
			}
		}
	}*/

	/**
	 * Finds the closest point on a geometry from list of points of ray intersection
	 * 
	 * @param Map<Intersectable,List<Point3D>>
	 * @return Map<Intersectable, Point3D>
	 */
	public Map<Intersectable, Point3D> getClosestPoint(Map<Intersectable, List<Point3D>> intersectionPoints) {
		Map<Intersectable, Point3D> rePoint = new HashMap<>();

		double distance = Double.MAX_VALUE;

		for (Entry<Intersectable, List<Point3D>> ed : intersectionPoints.entrySet()) {
			if (!(ed.getKey() instanceof Geometry))
				throw new IllegalArgumentException("Must be a Geometry. Can't use Interface");
			Geometry k = (Geometry) ed.getKey();
			List<Point3D> v = ed.getValue();
			for (Point3D p : v) {
				if (p.distance(_scene.getCamera().getP0()) < distance) {
					distance = p.distance(_scene.getCamera().getP0());
					rePoint.clear();
					rePoint.put(k, p);
				}
			}
		}
		return rePoint;

	}

	public void printGrid(int x) {
		for (int i = 0; i < _imageWriter.getNx(); i++) {
			for (int j = 0; j < _imageWriter.getNy(); j++) {
				if (i % x == 0 || j % x == 0)
					_imageWriter.writePixel(i, j, 255, 255, 255);
			}
		}

	}
	/*
	 * private void softShadowsLightGenerator(int samples) { for(LightSource ls :
	 * _scene.getLights()) { softShadows = new ArrayList<>();
	 * softShadows.addAll(ls.returnMultipleLights(samples)); } }
	 */
}
