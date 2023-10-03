package up_backoffice;

import model.vTest;

public class AppTeste {

	public static void main(String[] args) throws Exception {

//		var foto = EventoTest.getCapa(3, "","");

//		EventoTest.getCapa(FotoTipo.Veiculo,"D:\\ricsistemas\\Documents\\Placar\\Test\\fotos\\Imoveis\\Comercial1\\1.jpg"  ,"100");		

		/*
		 * var este = DataEvento.GetAddHora(0,10); este = DataEvento.GetAddHora(0,20);
		 * este = DataEvento.GetAddHora(0,240); este = DataEvento.GetAddHora(0,580);
		 * este = DataEvento.GetAddHora(0,1040);
		 */

		var este = new vTest();
// este.Gravar(este.arquivo, 400);

		System.out.println("versao=>" + este.getVersao());
		System.out.println("num_test=>" + este.test_num);
		System.out.println("loteImovel=>" + este.getLoteImovel());
		System.out.println("loteVeiculo=>" + este.getLoteVeiculo());

		System.out.println("Estagio=>" + este.getEstagio());
		System.out.println("Ocupação=>" + este.getOcupacao());
		System.out.println("Comitente=>" + este.getComitente());

		
	}

}
