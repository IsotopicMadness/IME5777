package renderer;

import elements.*;
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
	private Color _color;

	public Render(ImageWriter _imageWriter, Scene _scene) {
		this._scene = new Scene(_scene);
		this._imageWriter = new ImageWriter(_imageWriter);
	}

	/************** operations *******/
	// calc the exact color of the point that we need
	private Color calcColor(Geometry geo, Point3D point, Ray inRay, int levels, double k) {

		if (levels == 0 || Coordinate.isZero(k))
			return _scene.getBackground();

		if (geo == null || point == null)
			throw new IllegalArgumentException("Geometry or Point not found");

		_color = new Color(_scene.getAmbientLight().getIntensity(point).add(geo.getEmmission()));
		Color color = new Color(_color);

		Vector v = inRay.getDirection();
		Vector n = geo.getNormal(point);
		int nShininess = geo.getShininess();
		double kd = geo.getKd();
		double ks = geo.getKs();
		for (Light lightSource : _scene.getLights()) {
			Vector l = lightSource.getL(point);
			// not working
			if (n.dotProduct(l) * n.dotProduct(v) > 0) {
				Color lightIntensity = new Color();

				double occ = occluded(l, geo, point);
				if (!Coordinate.isZero(occ * k)) {
					lightIntensity = lightSource.getIntensity(point);
					color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));
				}
			}
		}

		// reflection
		Ray reflectedRay = constructReflectedRay(n, point, inRay);
		Map<Intersectable, List<Point3D>> reflectedIntersectionPoints = new HashMap<Intersectable, List<Point3D>>();
		reflectedIntersectionPoints.putAll(_scene.findRayIntersections(reflectedRay));

		Color reflected = new Color();
		if (reflectedIntersectionPoints.isEmpty()) {
			reflected = _scene.getBackground();
		} else {
			Map<Intersectable, Point3D> reflectedPoint = getClosestPoint(reflectedIntersectionPoints);
			double kr = geo.get_Kr();
			for (Entry<Intersectable, Point3D> p : reflectedPoint.entrySet()) {
				if (!(p.getKey() instanceof Geometry))
					throw new IllegalArgumentException("Must be Geometry");
				reflected = calcColor((Geometry) p.getKey(), p.getValue(), reflectedRay, levels - 1, k * kr).scale(kr);
			}
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
		color.add(reflected);
		return color.add(refracted);

	}

	private Ray constructReflectedRay(Vector n, Point3D point, Ray ray) {
		Ray reflected = new Ray(ray.getDirection().add(n.scale(-2 * ray.getDirection().dotProduct(n))), point);
		return reflected;
	}

	private Ray constructRefractedRay(Point3D point, Ray ray) {
		Ray refracted = new Ray(ray.getDirection(), point);
		return refracted;
	}

	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		Vector r = new Vector(l.add(n.scale(-2 * l.dotProduct(n))).normalize());
		double vr = v.dotProduct(r);
		if (vr > 0)
			return new Color(0, 0, 0);
		return lightIntensity.scale(ks * Math.pow((Math.abs(v.dotProduct(r))), nShininess));
	}

	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		return lightIntensity.scale((Math.abs(l.dotProduct(n)) * kd));
	}

	public ImageWriter getImageWriter() {
		return _imageWriter;
	}

	private double occluded(Vector l, Geometry geo, Point3D point) {

		Vector lightDirection = l.scale(-1); // from point to light source
		Vector normal = geo.getNormal(point).scale(2);
		Vector epsVector = normal.scale((normal.dotProduct(lightDirection) > 0) ? 1 / 10000 : -1 / 10000);

		Point3D geometryPoint = point.add(epsVector);

		Ray lightRay = new Ray(lightDirection, geometryPoint);

		double occlusionK = 0;
		Map<Intersectable, List<Point3D>> intersectionPoints = _scene.getGeometries().findIntersection(lightRay);
		for (Map.Entry<Intersectable, List<Point3D>> entry : intersectionPoints.entrySet()) {
			occlusionK *= ((Geometry) entry.getKey()).get_Kt();
		}

		return occlusionK;
	}

	/**
	 * Renders every the image
	 */
	public void renderImage() {

		for (int i = 0; i < _imageWriter.getNx(); ++i) {

			for (int j = 0; j < _imageWriter.getNy(); ++j) {
				Ray ray = _scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(), i, j,
						_scene.getScreenDistance(), _imageWriter.getWidth(), _imageWriter.getHeight());

				Map<Intersectable, List<Point3D>> intersectionPoints = new HashMap<>();
				intersectionPoints.putAll(_scene.getGeometries().findIntersection(ray));

				if (intersectionPoints.size() == 0)
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

					_imageWriter.writePixel(i, j,
							calcColor(geo, p, new Ray(new Vector(p.subtract(_scene.getCamera().getP0())).normalize(),
									_scene.getCamera().getP0()), 5, 1.0).getColor());
				}
			}
		}
	}

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
}
