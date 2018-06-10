package scene;

import geometries.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import elements.*;

import primitives.*;

public class Scene {

	private String _sceneName;
	private Color _background;
	private Geometries _objects;
	private Camera _camera;
	private double _screenDistance;
	private AmbientLight _ambientLight;
	private ArrayList<Light> _lights;

	public Scene() {
		setSceneName("Edward Elric for president");
		setBackground(new Color(255, 0, 0));
		_objects = new Geometries();
		setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(1, 0, 0)));
		setScreenDistance(1);
		_ambientLight = new AmbientLight(new Color(255, 255, 255), 1);
		_lights = new ArrayList<>();
	}

	public Scene(String name) {
		_sceneName = name;
		_background = new Color(0, 0, 0);
		_ambientLight = new AmbientLight(new Color(255, 255, 255), 1);
		_objects = new Geometries();
		_lights = new ArrayList<>();
	}

	public Scene(String sceneName, Color background, ArrayList<Geometry> objects, Camera camera, double screenDistance,
			AmbientLight aLight, ArrayList<Light> lights) {

		_sceneName = new String(sceneName);
		_background = new Color(background);
		_objects = new Geometries();
		for (Geometry geo : objects) {
			_objects.add(geo);
		}
		setCamera(camera);
		setScreenDistance(screenDistance);
		_ambientLight = new AmbientLight(aLight);
		_lights = new ArrayList<>();
		for (Light l : lights) {
			_lights.add(l);
		}
	}

	public Scene(Scene other) {
		setSceneName(other.getSceneName());
		setBackground(other.getBackground());
		_objects = other._objects;
		setCamera(other.getCamera());
		setScreenDistance(other.getScreenDistance());
		_ambientLight = new AmbientLight(other.getAmbientLight());
		_lights = new ArrayList<>();
		for (Light l : other.getLights()) {
			_lights.add(l);
		}
	}

	public void addGeometry(Geometry geo) {
		_objects.add(geo);
	}

	public void addLights(Light light) {
		_lights.add(light);
	}

	////////// getter/setter////////////
	public String getSceneName() {
		return _sceneName;
	}

	public void setSceneName(String sceneName) {
		this._sceneName = new String(sceneName);
	}

	public Color getBackground() {
		return _background;
	}

	public void setBackground(Color background) {
		this._background = new Color(background);
	}

	public Camera getCamera() {
		return _camera;
	}

	public void setCamera(Camera camera) {
		this._camera = new Camera(camera);
	}

	public double getScreenDistance() {
		return _screenDistance;
	}

	public void setScreenDistance(double screenDistance) {
		this._screenDistance = screenDistance;
	}

	public Geometries getObjects() {
		return _objects;
	}

	public void setObjects(ArrayList<Geometry> objects) {
		this._objects = new Geometries();
		for (Geometry geo : objects)
			this._objects.add(geo);
	}

	public AmbientLight getAmbientLight() {
		return _ambientLight;
	}

	public void setAmbientLight(AmbientLight ambientLight) {
		this._ambientLight = ambientLight;
	}

	public Geometries getGeometries() {

		return _objects;
	}

	public void setGeomtries(Geometries geometries) {
		_objects = geometries;
	}

	public ArrayList<Light> getLights() {
		return _lights;
	}

	/**
	 * sets lights with a shallow set.
	 * 
	 * @param lights
	 */
	public void setLights(ArrayList<Light> lights) {
		_lights = lights;
	}

	public Map<Intersectable, List<Point3D>> findRayIntersections(Ray ray) {

		return _objects.findIntersection(ray);
	}
}
