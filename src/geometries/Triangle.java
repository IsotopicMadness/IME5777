package geometries;
import primitives.Point3D;
public class Triangle extends Geometry {
private Point3D p1,p2,p3;
//***************** Constructors ********************** //
public Triangle() {return;}
public Triangle(Point3D p1,Point3D p2,Point3D p3 ) {

}
	// ***************** Getters/Setters ********************** //
public Point3D getp1() {return p1;}
public Point3D getp2() {return p2;}
public Point3D getp3() {return p3;}
public void setp1(Point3D p) {p1=p;}
public void setp2(Point3D p) {p2=p;}
public void setp3(Point3D p) {p3=p;}
	// ***************** Administration  ******************** //

	@Override
	public boolean equals(Triangle other) {

	}	

	@Override
	public String toString() { 
	System.out.println("\n");
	return null;
	}

	// ***************** Operations ******************** // 
	
}
