/**
 * 
 */
package primitives;

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
	/**
	 * Returns the Coordinate's value
	 * 
	 * @return
	 */
	public double getNum() {
		return _x;
	}

	// admin

	/**
	 * Returns the exponent of the Coordinate's value
	 * 
	 * double store format: seee eeee eeee (1.)mmmm … mmmm 1 bit sign, 11 bits
	 * exponent, 53 bits (52 stored) normalized mantissa
	 * 
	 * @return
	 */
	private static int getExponent(double num) {
		return (int) ((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
	}

	// Override
	@Override
	/**
	 * Checks if the Coordinate shares the same value with the second one
	 * 
	 * @return
	 */
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Coordinate))
			return false;
		if (this == obj)
			return true;
		Coordinate other = new Coordinate((Coordinate) obj);
		return subtract(other) == 0.0;
	}

	@Override
	/**
	 * returns the coordinate in string form
	 * 
	 * @return
	 */
	public String toString() {

		return Double.toString(_x);
	}

	// operations
	/**
	 * Adds a given value to the Coordinate. However if the value is smaller than the pre-determined accuracy it won't be added and will be treated as a zero
	 * @param other
	 * @return
	 */
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

	/**
	 * Subtracts a given value from the Coordinate. However if the value is smaller than the pre-determined accuracy it won't be subtracted and will be treated as a zero.
	 * @param other
	 * @return
	 */
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

	/**
	 * multiplies the Coordinate's value by the given number
	 * 
	 * @param num
	 * @return
	 */
	private double _mult(double num) {
		int deltaExp = getExponent(num - 1);
		return deltaExp < ACCURACY ? _x : _x * num;
	}

	// API
	/**
	 * Adds given value to Coordinate
	 * 
	 * @param other
	 * @return
	 */
	public double add(double other) {
		return _add(other);
	}

	/**
	 * Adds given value to Coordinate
	 * 
	 * @param other
	 * @return
	 */
	public double add(Coordinate other) {
		return _add(other._x);
	}

	/**
	 * Subtracts given value from Coordinate
	 * 
	 * @param other
	 * @return
	 */
	public double subtract(double other) {
		return _subtract(other);
	}

	/**
	 * Subtracts given value from Coordinate
	 * 
	 * @param other
	 * @return
	 */
	public double subtract(Coordinate other) {
		return _subtract(other._x);
	}

	/**
	 * multiplies the Coordinate's value by the given number
	 * 
	 * @param num
	 * @return
	 */
	public double mult(double num) {
		return _mult(num);
	}

	/**
	 * multiplies the Coordinate's value by the given number
	 * 
	 * @param num
	 * @return
	 */
	public double mult(Coordinate other) {
		return _mult(other._x);
	}

	/**
	 * Checks if given number is Zero or very close to it.
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isZero(double number) {
		return getExponent(number) < ACCURACY;
	}

}
