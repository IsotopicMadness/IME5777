/**
 * 
 */
import primitives.*;
import geometries.*;

/**
 * @author amich
 *
 */
public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * We shall test the primitives package first.
		 * The tests will revolve around actions done on each class and the extreme cases.
		 */
		
		Coordinate x=new Coordinate(1);
		Coordinate y=new Coordinate(2);
		Coordinate z=new Coordinate(-4);
		
		System.out.println(x.toString()+" "+y.toString()+" "+z.toString());
		
		System.out.println(x.mult(0.5));
		
		Point2D p2 = new Point2D(x,y);
		Point2D p2_1 = new Point2D (new Coordinate(-0.4), new Coordinate(5.0));
		Point2D p2_2 = new Point2D(p2_1);
		
		System.out.println(p2.toString()+"\n"+p2_2.toString());
		
		Point3D p3 = new Point3D(x,y,z);
		Point3D p3_1 = new Point3D(p3);

		System.out.println(p3.toString()+"\n"+p3_1.toString());
		
		Vector v = new Vector(new Coordinate(x.add(x.getNum())),new Coordinate(y.add(y.getNum())), new Coordinate(z.subtract(y.getNum())));
		Vector vc = v.crossProduct(new Vector(new Coordinate(2),new Coordinate(5),new Coordinate(1)));
		Vector vd = v.scale(4).crossProduct(vc);
		Vector vn = vd.normalize();
		System.out.println(v.toString()+"\n"+vc.toString()+"\n"+vd.toString()+"\n"+vn.toString());
		
		/**
		 * Testing 1-0.999999999
		 */
		Ray r = new Ray(v, new Point3D(new Coordinate(x.subtract(0.99999999999999999999)),new Coordinate(y.mult(z.getNum())), new Coordinate(z.mult(-0.5))));
		
		System.out.println(r.toString());
		
		/**
		 * Output from the begging until this point:
		 * The "add", "Subtract" and "mult/scale" seems to be working even with extreme cases
	  	 *
		 * 1.0 2.0 -4.0
		 * 0.5
		 * (1.0, 2.0)
 		 * (-0.4, 5.0)
		 * (1.0, 2.0, -4.0)
		 * (1.0, 2.0, -4.0)
		 * (2.0, 4.0, -6.0)
		 * (34.0, 14.0, 2.0)
		 * (368.0, 832.0, -432.0)
		 * (0.36540173102745377, 0.8261256527577217, -0.4289498581626631)
		 * (2.0, 4.0, -6.0)
		 * Direction: (2.0, 4.0, -6.0)
		 * Location: (0.0, -8.0, 2.0)
		 */
		
		Point3D t1 = new Point3D(new Coordinate(1), new Coordinate(4), new Coordinate(-9));
		Point3D t2 = new Point3D(new Coordinate(-1), new Coordinate(2), new Coordinate(3));
		Point3D t3 = new Point3D(new Coordinate(7), new Coordinate(-0.0000000000001), new Coordinate(5));
		
		System.out.println(t1.toString()+"\n"+t2.toString()+"\n"+t3.toString());
		
		Triangle tri = new Triangle(t1,t2,t3, new Color(),new Material(1,1,1,1,1));
		Plane pl = new Plane(t1, v, new Color(), new Material(1,1,1,1,1));
		Sphere sp = new Sphere(t3,5.0,new Color(), new Material(1,1,1,1,1));
		//Cylinder cyl = new Cylinder(5.0,t2,r,new Color(), new Material(1,1,1,1,1));
		
		System.out.println(tri.toString()+"\n"+pl.toString()+"\n"+sp.toString()+"\n"/*+cyl.toString()*/);
		/**
		 * 
		 *(1.0, 4.0, -9.0)
		 *(-1.0, 2.0, 3.0)
		 *(7.0, 0.0, 5.0)
		 *(1.0, 4.0, -9.0), (-1.0, 2.0, 3.0), (7.0, 0.0, 5.0)
		 *Point: (1.0, 4.0, -9.0), Vector: (2.0, 4.0, -6.0)
		 *Radius: 5.0, Point: (7.0, 0.0, 5.0)
		 *Direction and length: (2.0, 4.0, -6.0), 7.483314773547883
		 *Radius: 5.0
		 *Point: (-1.0, 2.0, 3.0)
		 */
		
	}

}
