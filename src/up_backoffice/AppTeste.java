package up_backoffice;
import model.DataEvento;
public class AppTeste {

	public static void main(String[] args) throws Exception {
	
		
		
//		var foto = EventoTest.getCapa(3, "","");
		
	//	EventoTest.getCapa(2,"100","100");		
		EventoTest.getCapa(0,"D:\\ricsistemas\\Documents\\Placar\\Test\\fotos\\Imoveis\\Comercial1\\1.jpg"  ,"100");		
//		EventoTest.getCapa(0,"D:\\ricsistemas\\Documents\\Placar\\Test\\fotos\\Veiculos\\AUDI R8 SPYDER 2012\\175201825240230.jpg","100");	
		

		
		var este = 	DataEvento.GetAddHora(0,10);
		 este = 	DataEvento.GetAddHora(0,20);
		 este = 	DataEvento.GetAddHora(0,240);
		 este = 	DataEvento.GetAddHora(0,580);
		 este = 	DataEvento.GetAddHora(0,1040);


	}

}
