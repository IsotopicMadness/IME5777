/**
 * 
 */
package primitives;

/**
 * @author amich
 *
 */
public class Coordinate {
	
	private double x;
	
	private static final int ACCURACY = -20;

	public Coordinate(double coord) {
		x = (getExponent(coord) < ACCURACY) ? 0.0 : coord;
	}
	public Coordinate(Coordinate other) {
		x = other._x();
	}
	
	//get/set
	public double _x() {
		return x;
	}
	
	public void _x(double x) {
		this.x = x;
	}
	
	// double store format: seee eeee eeee (1.)mmmm … mmmm
	// 1 bit sign, 11 bits exponent, 53 bits (52 stored) normalized mantissa
		
	private int getExponent(double num) {
		return (int)((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
	}
	
	private double add(double other) {
		int otherExp = getExponent(x);
		int thisExp = getExponent(x);
		// if other is too small relatively to our coordinate return the original coordinate
		if (otherExp - thisExp < ACCURACY) return x;
		// if our coordinate is too small relatively to other return other
		if (thisExp - otherExp < ACCURACY) return other;
		double result = x + other;
		int resultExp = getExponent(result);
		// if the result is relatively small - tell that it is zero
		return resultExp - thisExp < ACCURACY ? 0.0 : result;
		}
	
	private double subtract(double other) {
		int otherExp = getExponent(other);
		int thisExp = getExponent(x);
		// if other is too small relatively to our coordinate return the original coordinate
		if (otherExp - thisExp < ACCURACY) return x;
		// if our coordinate is too small relatively to other return negative of other
		if (thisExp - otherExp < ACCURACY) return -other;
		double result = x - other;
		int resultExp = getExponent(result);
		// if the result is relatively small - tell that it is zero
		return resultExp - thisExp < ACCURACY ? 0.0 : result;
	}	

	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(!(obj instanceof Coordinate))
			return false;
		if(this==obj)
			return true;
		Coordinate other = new Coordinate((Coordinate)obj);
		return this.subtract(other._x())==0.0;
	}
}
