package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import util.Gerar;

public class DataEvento {

	public String HoraExibir;
	public String HoraFimExibir;
	public String InicioDisputaReal;
	public String Intervalo = "15";
	public String ItervaloPassagem = "15";
	public String BloqueioAposOferta = "3";

	public String InicioDisputaFake;
	public String SegundaDataFake;
	public String TerceiraDataFake;
	public String SegundaData;
	public String TerceiraData;
	public String EmBreve;
	private final int autodia = 0;
	private final int seg = 5;
	private final int ter = 4;
	private final int qua = 3;
	private final int qui = 2;
	private final int sex = 1;

	private int diasRealizar = 10;
	private int Segundodia = 10;
	private int Terceirodia = 365;

	public int AntDisputa = 0;
	public int AtrasoDisputa = 0;
	public int DuracaoDisputa = 30;

	private int minutoleilaoRealizar = 120; // 3600*8; // 122;(6 horas)

	private int diasFim = 0;
	private int minutoleilaoEncerrar = 10;
	private int SegundaDataMinuto = 10;
	private int TerceiraDataMinuto = 10;
	private int minutofake = 180;
	public boolean fake = minutoleilaoRealizar < minutofake && diasRealizar <= 1;

	private void AutoDiaVaslid() {

		SimpleDateFormat DateFor = new SimpleDateFormat("ddMMyyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.getTime();
		int disSemana = calendar.get(Calendar.DAY_OF_WEEK - 1);

		switch (disSemana) {
		case 1: // code to be executed
			Segundodia = 6;
			
			
			break; // optional
		case 2: // code to be executed
			Segundodia = 5;
			
	
			break; // optional

		
		
		
		default: // code to be executed if
			Segundodia = 1; 
			
		}

	}

	public DataEvento(int QtdEventos) {

		var duracaoMinutos = DuracaoDisputa / 60;

		if (QtdEventos < 2) {
			Segundodia = 0;
		}
		if (QtdEventos < 3) {
			Terceirodia = 0;

		}

		HoraExibir = GetAddHora(0, 0);
		EmBreve = GetAddHora(0, 5);

		InicioDisputaReal = GetAddHora(diasRealizar, minutoleilaoRealizar + AntDisputa);
		SegundaData = GetAddHora(diasRealizar + diasFim + Segundodia,
				minutoleilaoRealizar + minutoleilaoEncerrar + SegundaDataMinuto + AntDisputa + duracaoMinutos * 2);
		TerceiraData = GetAddHora(diasRealizar + diasFim + Terceirodia, minutoleilaoRealizar + minutoleilaoEncerrar
				+ SegundaDataMinuto + TerceiraDataMinuto + AntDisputa + duracaoMinutos * 3);

		// --------------------------------------------------------------------------------------------------------------------------------------
		InicioDisputaFake = GetAddHora(diasRealizar, minutofake);
		SegundaDataFake = GetAddHora(diasRealizar + diasFim + Segundodia,
				minutoleilaoRealizar + minutoleilaoEncerrar + SegundaDataMinuto + minutofake);
		TerceiraDataFake = GetAddHora(diasRealizar + diasFim + Segundodia + Terceirodia,
				minutoleilaoRealizar + minutoleilaoEncerrar + SegundaDataMinuto + minutofake + TerceiraDataMinuto);
		// --------------------------------------------------------------------------------------------------------------------------------------

	}

	/*
	 * public DataEvento(int mes) {
	 * 
	 * int dias = Gerar.randomiza(28); int hora = Gerar.randomiza(23); int minutos =
	 * Gerar.randomiza(59);
	 * 
	 * HoraExibir = GetAddHora(dias, hora).substring(8); InicioDisputaReal =
	 * GetAddHora(dias, minutos).substring(0, 8);
	 * System.out.println("InicioDisputaReal:" + InicioDisputaReal);
	 * System.out.println("hora:" + HoraExibir);
	 * 
	 * }
	 */

	public DataEvento(int dia, int mes) {

		int hora = Gerar.randomiza(23);
		int minutos = Gerar.randomiza(59);

		HoraExibir = GetAddHora(dia, hora).substring(8);
		InicioDisputaReal = GetAddHora(dia, minutos).substring(0, 8);
		System.out.println("InicioDisputaReal:" + InicioDisputaReal);
		System.out.println("hora:" + HoraExibir);

	}

	private static String GetAddHora(int dias, int minutos) {
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
