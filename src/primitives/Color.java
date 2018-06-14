package primitives;

/**
 * Wrapper class for Color instead of java.awt.Color
 *
 */
public class Color {

	private double _red;
	private double _green;
	private double _blue;

	/********** Constructors ***********/
	public Color() {
		_red = 0.0;
		_green = 0.0;
		_blue = 0.0;
	}

	public Color(double red, double green, double blue) {
		// if (red>255 || green>255 || blue>255 || red<0 || green<0 || blue<0 )
		// throw new ArithmeticException("colors in java has limits, color out
		// of bound");
		_red = red < 0 ? 0 : red;
		_green = green < 0 ? 0 : green;
		_blue = blue < 0 ? 0 : blue;
	}

	public Color(Color other) {
		_red = other._red;
		_green = other._green;
		_blue = other._blue;
	}

	public Color(java.awt.Color color) {
		_red = color.getRed();
		_green = color.getGreen();
		_blue = color.getBlue();
	}

	/************** Getters/Setters *******/
	public Color setColor() {
		_red = 0.0;
		_green = 0.0;
		_blue = 0.0;
		return this;
	}

	public Color setColor(double red, double green, double blue) {
		_red = red;
		_green = green;
		_blue = blue;
		return this;
	}

	public Color setColor(Color other) {
		_red = other._red;
		_green = other._green;
		_blue = other._blue;
		return this;
	}

	public Color setColor(java.awt.Color color) {
		_red = color.getRed();
		_green = color.getGreen();
		_blue = color.getBlue();
		return this;
	}

	/**
	 * Getter for the color - produces final java.awt.Color
	 * 
	 * @return color in java.awt type
	 */
	public java.awt.Color getColor() {
		int r = (int) _red;
		int g = (int) _green;
		int b = (int) _blue;
		return new java.awt.Color(r > 255 || r<0 ? 255 : r, g > 255 || g<0 ? 255 : g , b > 255 || b<0 ? 255 : b);
	}
	
	public int[] getColorArray(){
		int[] arr = {(int) _red, (int) _green, (int) _blue}; 
		return arr;
	}

	/********** Operations ************/

	// add two colors, makes sure rules
	public Color add(Color... colors) {
		for (Color color : colors) {
			_red += color._red;
			_green += color._green;
			_blue += color._blue;
		}
		return this;
	}

	// scale color in exponent
	public Color scale(double exp) {
		if (exp < 0 /*|| exp > 1*/)
			throw new ArithmeticException("Scaling color with a value larger than 0");
		Color re = new Color(_red * exp, _green * exp, _blue * exp);
		return re;
	}
/**
 * Dont know why this's here
 * @param exp
 * @return
 *
 *	// reduce color with exponent
 *	public Color reduce(double exp) {
 *	if (exp < 0 || exp > 1)
 *			throw new ArithmeticException("Reducing color with a value smaller than 1");
 *		_red /= exp;
 *		_green /= exp;
 *		_blue /= exp;
 *		return this;
 *	}
 */	
}
