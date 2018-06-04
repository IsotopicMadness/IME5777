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
		_Kr = kr;
		_Kt = kt;
	}
	
	public Material(Material other) {
		_Kd = other.getKd();
		_Ks = other.getKs();
		_nShininess = other.getShininess();
		_Kr = other.get_Kr();
		_Kt = other.get_Kt();
	}

	public int getShininess() {
		return _nShininess;
	}

	public double getKs() {
		return _Ks;
	}

	public double getKd() {
		return _Kd;
	}
	public Material getMaterial() {
		return this;
	}
	public double get_Kr() {
		return _Kr;
	}
	public double get_Kt() {
		return _Kt;
	}
}
