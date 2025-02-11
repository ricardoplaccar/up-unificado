package model;

public class Pagamento {
	public Boolean Quero_financiar = false;
	public Boolean AVista = false;
	public Boolean Parcelado = false;
	public String Prioridade = "Parcelas";

	public void setPagamento(boolean pag) {
		// TODO Auto-generated method stub
		Quero_financiar = !pag;
		AVista = pag;
		Prioridade = (pag) ? "Entrada" : "Parcelas";
		Parcelado =pag;
	}
}
