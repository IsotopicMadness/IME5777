/**
 * 
 */
package primitives;

import sun.awt.www.content.audio.x_aiff;

/**
 * @author amich
 *
 */
public class Coordinate {

	private double _x;

	private static final int ACCURACY = -20;

	public Coordinate(double coord) {
		_x = (getExponent(coord) < ACCURACY) ? 0.0 : coord;
	}

	public Coordinate(Coordinate other) {
		_x = other.getNum();
	}

	// get/set
	public double getNum() {
		return _x;
	}

	// admin

	/**
	 * double store format: seee eeee eeee (1.)mmmm … mmmm 1 bit sign, 11 bits
	 * exponent, 53 bits (52 stored) normalized mantissa
	 */
	private static int getExponent(double num) {
		return (int) ((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
	}

	// Override
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Coordinate))
			return false;
		if (this == obj)
			return true;
		Coordinate other = new Coordinate((Coordinate) obj);
		return this.subtract(other.getNum()) == 0.0;
	}

	@Override
	public String toString() {

		return Double.toString(_x);
	}

	// operations
	private double _add(double other) {
		int otherExp = getExponent(_x);
		int thisExp = getExponent(_x);
		// if other is too small relatively to our coordinate return the original
		// coordinate
		if (otherExp - thisExp < ACCURACY)
			return _x;
		// if our coordinate is too small relatively to other return other
		if (thisExp - otherExp < ACCURACY)
			return other;
		double result = _x + other;
		int resultExp = getExponent(result);
		// if the result is relatively small - tell that it is zero
		return resultExp - thisExp < ACCURACY ? 0.0 : result;
	}

	private double _subtract(double other) {
		int otherExp = getExponent(other);
		int thisExp = getExponent(_x);
		// if other is too small relatively to our coordinate return the original
		// coordinate
		if (otherExp - thisExp < ACCURACY)
			return _x;
		// if our coordinate is too small relatively to other return negative of other
		if (thisExp - otherExp < ACCURACY)
			return -other;
		double result = _x - other;
		int resultExp = getExponent(result);
		// if the result is relatively small - tell that it is zero
		return resultExp - thisExp < ACCURACY ? 0.0 : result;
	}

	private double _mult(double num) {
		int deltaExp = getExponent(num - 1);
		return deltaExp < ACCURACY ? _x : _x * num;
	}

	// API
	public double add(double other) {
		return _add(other);
	}

	public double subtract(double other) {
		return _subtract(other);
	}

	public double mult(double num) {
		return _mult(num);
	}

	public static boolean isZero(double number) {
		return getExponent(number)<ACCURACY;
	}

}
