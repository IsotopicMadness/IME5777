package geometries;
import java.util.ArrayList;

import primitives.*;

public class Triangle extends Plane {
	
	private Point3D p1,p2,p3;

	//***************** Constructors ********************** //
	//public Triangle() {return;}
	public Triangle(Point3D p1,Point3D p2,Point3D p3 ) {
		super(p1,p2,p3);
		this.p1=new Point3D(p1);
		this.p2=new Point3D(p2);
		this.p3=new Point3D(p3);
		
	}
	public Triangle(Triangle other) {
		super(other.getP1(),other.getP2(),other.getP3());
		this.p1=new Point3D (other.getP1());
		this.p2=new Point3D (other.getP2());
		this.p3=new Point3D (other.getP3());
	}
	// ***************** Getters/Setters ********************** //
	public Point3D getP1() {return p1;}
	public Point3D getP2() {return p2;}
	public Point3D getP3() {return p3;}
	/*public void setP1(Point3D p) {p1=p;}
	public void setP2(Point3D p) {p2=p;}
	public void setP3(Point3D p) {p3=p;}
	*/
	// ***************** Administration  ******************** //

	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(!(obj instanceof Triangle))
			return false;
		if(this==obj)
			return true;
		Triangle other = new Triangle((Triangle)obj);
		return this.p1.equals(other.p1) && this.p2.equals(other.p2) && this.p3.equals(other.p3);

	}	

	@Override
	public String toString() {
		return p1.toString()+", "+p2.toString()+", "+p3.toString();
		}
	

	// ***************** Operations ******************** // 
	@Override
	public ArrayList<Point3D> findIntersection(Ray ray) {
		
		ArrayList<Point3D> result = new ArrayList<>();
		
		Vector v1 = new Vector(p1.vectorSubstraction(ray.getLocation()));
		Vector v2 = new Vector(p2.vectorSubstraction(ray.getLocation()));
		Vector v3 = new Vector(p3.vectorSubstraction(ray.getLocation()));
		
		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();
		
		result = super.findIntersection(ray);
		
		if(result==null)
			return null;
		Point3D p = new Point3D(result.get(0));
		
		boolean t1 = n1.dotProduct(p.vectorSubstraction(ray.getLocation())) > 0;
		boolean t2 = n2.dotProduct(p.vectorSubstraction(ray.getLocation())) > 0;
		boolean t3 = n3.dotProduct(p.vectorSubstraction(ray.getLocation())) > 0;
		
		if(t1&&t2&&t3||!t1&&!t2&&!t3)
			return result;
		else
			return null;
		
	}
}
