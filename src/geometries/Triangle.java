package geometries;
import primitives.*;

public class Triangle extends Geometry {
	
	private Point3D p1,p2,p3;

	//***************** Constructors ********************** //
	//public Triangle() {return;}
	public Triangle(Point3D p1,Point3D p2,Point3D p3 ) {
		this.p1=new Point3D(p1);
		this.p2=new Point3D(p2);
		this.p3=new Point3D(p3);
	}
	public Triangle(Triangle other) {
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

	/*Override
	public String toString() {
		
	return "";
	}*/
	
	@Override
	public Vector getNormal(Point3D p) {
		// TODO Auto-generated method stub
		return null;
	}

	// ***************** Operations ******************** // 
	
}
