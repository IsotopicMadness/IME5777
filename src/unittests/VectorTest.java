package unittests;

import primitives.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VectorTest {
	
	Vector a;
	Vector b;
	@Test
	public void testAdd() {
		
		//normal test
		a = new Vector(1.0,1.0,1.0);
		b = new Vector(2.0, 2.0, 2.0);
		
		assertEquals(new Vector(3.0,3.0,3.0), a.add(b));
		
		a = new Vector(1.0,1.0,1.0);
		b = new Vector(-2.0,-2.0,-2.0);
		
		assertEquals(new Vector(-1.0,-1.0,-1.0),a.add(b));
		
		//boundry test
		a = new Vector(1.0,1.0,1.0);
		b = new Vector(0.0,0.0,0.0);
		
		assertEquals(new Vector(1.0,1.0,1.0),a.add(b));
		
		a = new Vector(1.0,1.0,1.0);
		b = new Vector(0.0000001,0.0000001,0.00000001);
		
		assertEquals(new Vector(1.0,1.0,1.0),a.add(b));
		
		a = new Vector(1.0,1.0,1.0);
		b = new Vector(-0.00000001,-0.00000001,-0.000000001);
		
		assertEquals(new Vector(1.0,1.0,1.0),a.add(b));
	}
	@Test
	public void testSubtract() {
		
	}
	@Test
	public void testScaling() {
		
	}
	@Test
	public void testDotProduct() {
		
	}
	@Test
	public void testLength() {
		
	}
	@Test
	public void testNormalize() {
		
	}
	@Test
	public void testCrossProduct() {
		
	}
	@Test
	public void test() {

	}

}
