package scene;

import geometries.*;

import java.awt.Color;

import java.util.ArrayList;

import elements.*;

import primitives.*;

public class Scene {

	private String sceneName;
	private Color background;
	private Geometries objects;
	private Camera camera;
	private double screenDistance;
	private AmbientLight ambientLight;
	
	
	
	public Scene() {
		setSceneName("Edward Elric for president");
		setBackground(Color.RED);
		objects = new Geometries();
		setCamera(new Camera(new Point3D(0,0,0),new Vector(0,0,1), new Vector(1,0,0)));
		setScreenDistance(1);
	}
	
	public Scene(String name) {
		sceneName = name;
		background = new Color(0);
		ambientLight = new AmbientLight(new primitives.Color(255,255,255), 1);
	}
	
	public Scene(String sceneName, Color background,ArrayList<Geometry> objects, Camera camera, double screenDistance) {
		
		this.setSceneName(sceneName);
		this.setBackground(background);
		this.objects = new Geometries();
		for(Geometry geo:objects) {
			this.objects.add(geo);
		}
		this.setCamera(camera);
		this.setScreenDistance(screenDistance);
	}
	
	public Scene(Scene other) {
		this.setSceneName(other.getSceneName());
		this.setBackground(other.getBackground());
		this.objects = new Geometries();
		for(Geometry geo:other.getObjects().getGeometries()) {
			this.objects.add(geo);
		}
		this.setCamera(other.getCamera());
		this.setScreenDistance(other.getScreenDistance());
	}

	public void addGeometry(Geometry geo) {
		objects.add(geo);
	}
	
	//////////getter/setter////////////
	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public double getScreenDistance() {
		return screenDistance;
	}

	public void setScreenDistance(double screenDistance) {
		this.screenDistance = screenDistance;
	}
	
	public Geometries getObjects() {
		return objects;
	}
	
	public void setObjects(ArrayList<Geometry> objects) {
		this.objects = new Geometries();
		for(Geometry geo:objects)
			this.objects.add(geo);
	}
	
	public AmbientLight getAmbientLight() {
		return ambientLight;
	}
	
	public void setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
	}

	public Geometries getGeometries() {
		
		return objects;
	}

	public void setGeomtries(Geometries geometries) {
		objects = new Geometries(geometries);
	}
}
