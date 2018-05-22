package primitives;

public class Material {
	protected double _Kd;
	protected double _Ks;
	protected int _nShininess;
	
	public Material(double kd, double ks, int nShininess) {
		_Kd = kd;
		_Ks = ks;
		_nShininess = nShininess;
	}
	
	public Material(Material other) {
		_Kd = other.getKd();
		_Ks = other.getKs();
		_nShininess = other.getShininess();
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
}
