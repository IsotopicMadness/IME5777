package primitives;

public class Material {
	protected double _Kd;
	protected double _Ks;
	protected int _nShininess;
	protected double _Kr;
	protected double _Kt;

	public Material(double kd, double ks, int nShininess, double kr, double kt) {
		_Kd = kd;
		_Ks = ks;
		_nShininess = nShininess;

		_Kr = kr > 1 ? 1 : (kt < 0 ? -1 : kt);
		_Kt = kt > 1 ? 1 : (kt < 0 ? -1 : kt);
		if (_Kr == -1 || _Kt == -1)
			throw new IllegalArgumentException("Kt and Kr values are incorrect");
	}

	public Material(Material other) {
		_Kd = other.getKd();
		_Ks = other.getKs();
		_nShininess = other.getShininess();
		_Kr = other.get_Kr();
		_Kt = other.get_Kt();
	}

	/**
	 * returns the shininess of the Material
	 * @return
	 */
	public int getShininess() {
		return _nShininess;
	}

	/**
	 * Returns the material's Ks
	 * @return
	 */
	public double getKs() {
		return _Ks;
	}

	/**
	 * returns the material's Kd
	 * @return
	 */
	public double getKd() {
		return _Kd;
	}

	/**
	 * returns the material's Kr
	 * @return
	 */
	public double get_Kr() {
		return _Kr;
	}

	/**
	 * Returns the Material's Kt
	 * @return
	 */
	public double get_Kt() {
		return _Kt;
	}
}
