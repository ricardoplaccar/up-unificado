package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import util.Gerar;

public class DataEvento {

	public String HoraExibir;
	public String HoraFimExibir;
	public String InicioDisputaReal;
	public String DuracaoDisputa = "29";
	public String Intervalo = "15";
	public String ItervaloPassagem = "15";
	public String BloqueioAposOferta = "3";
	public String InicioDisputaFake;
	public String SegundaDataFake;
	public String TerceiraDataFake;
	public String SegundaData;
	public String TerceiraData;
	private int minutoleilaoRealizar = 120;// 122;
	private int diasRealizar = 0;
	private int diasFim = 0;
	private int minutoleilaoEncerrar = 0;
	private int SegundaDataMinuto = 5;
	private int TerceiraDataMinuto = 5;
	private int minutofake = 180;
	public boolean fake = minutofake <10 || diasRealizar < 1;
	
	

	private int Segundodia = 0;
	private int Terceirodia = 0;

	public DataEvento(int QtdEventos, char c) {

		if (QtdEventos < 2) {
			Segundodia = 0;
		}
		if (QtdEventos < 3) {
			Terceirodia = 0;

		}

		HoraExibir = GetAddHora(0, 0);
		InicioDisputaReal = GetAddHora(diasRealizar, minutoleilaoRealizar);
		SegundaData = GetAddHora(diasRealizar + diasFim + Segundodia,
				minutoleilaoRealizar + minutoleilaoEncerrar + SegundaDataMinuto);
		TerceiraData = GetAddHora(diasRealizar + diasFim + Terceirodia,
				minutoleilaoRealizar + minutoleilaoEncerrar + SegundaDataMinuto + TerceiraDataMinuto);
		
		//--------------------------------------------------------------------------------------------------------------------------------------	
			InicioDisputaFake = GetAddHora(diasRealizar, minutofake);
		SegundaDataFake = GetAddHora(diasRealizar + diasFim + Segundodia,
				minutoleilaoRealizar + minutoleilaoEncerrar + SegundaDataMinuto + minutofake);
		TerceiraDataFake = GetAddHora(diasRealizar + diasFim + Segundodia + Terceirodia,
				minutoleilaoRealizar + minutoleilaoEncerrar + SegundaDataMinuto + minutofake + TerceiraDataMinuto);
		//--------------------------------------------------------------------------------------------------------------------------------------	
	
	}

	public DataEvento(int mes) {

		int dias = Gerar.randomiza(28);
		int hora = Gerar.randomiza(23);
		int minutos = Gerar.randomiza(59);

		HoraExibir = GetAddHora(dias, hora).substring(8);
		InicioDisputaReal = GetAddHora(dias, minutos).substring(0, 8);
		System.out.println("InicioDisputaReal:" + InicioDisputaReal);
		System.out.println("hora:" + HoraExibir);

	}

	public DataEvento(int dia, int mes) {

		int hora = Gerar.randomiza(23);
		int minutos = Gerar.randomiza(59);

		HoraExibir = GetAddHora(dia, hora).substring(8);
		InicioDisputaReal = GetAddHora(dia, minutos).substring(0, 8);
		System.out.println("InicioDisputaReal:" + InicioDisputaReal);
		System.out.println("hora:" + HoraExibir);

	}

	public static String GetAddHora(int dias, int minutos) {
		SimpleDateFormat DateFor = new SimpleDateFormat("ddMMyyyyHHmm");
		Calendar calendar = Calendar.getInstance();
		calendar.getTime();
		calendar.add(Calendar.DATE, dias);
		calendar.add(Calendar.MINUTE, minutos);
		String stringData = DateFor.format(calendar.getTime());
		// System.out.println(" 1. -> " + stringData);
		return stringData;

	}

}
