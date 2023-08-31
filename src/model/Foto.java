package model;

public class Foto {
	public String local;

	public Foto(String local) {

		this.local = local;
	} 

	@Override
	public String toString() {
		return "Foto [local=" + local + "]";
	}

	public Foto() {
	}

}
