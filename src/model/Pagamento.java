package model;

public class Pagamento {
	public Boolean Quero_financiar = false;
	public Boolean AVista = false;
	public Boolean Parcelado = false;


	public void setPagamento(boolean jud) {
		// TODO Auto-generated method stub
		Quero_financiar = !jud;
		Parcelado = !jud;
		AVista = jud;
	}
}
