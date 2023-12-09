package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataEvento {

	public String HoraExibir;
	public String HoraFimExibir;
	public String InicioDisputaReal;
	public String DuracaoDisputa = "29";
	public String Intervalo = "15";
	public String ItervaloPassagem = "15";
	public String BloqueioAposOferta = "3";
	public String InicioDisputaFake;
	public String SegundaData;
	public String TerceiraData;

	private int minutoleilaoRealizar = 10;// 122;
	private int diasRealizar = 0;
	private int diasFim = 1;
	private int minutoleilaoEncerrar = 10;

	public DataEvento() {
		HoraExibir = GetAddHora(0, 0);
		InicioDisputaReal = GetAddHora(diasRealizar, minutoleilaoRealizar);
		InicioDisputaFake = GetAddHora(diasRealizar, 240);
		HoraFimExibir = GetAddHora(diasFim, minutoleilaoRealizar + minutoleilaoEncerrar);

		SegundaData = GetAddHora(diasRealizar + diasFim + 1, minutoleilaoRealizar + minutoleilaoEncerrar);
		TerceiraData = GetAddHora(diasRealizar + diasFim + 2, minutoleilaoRealizar + minutoleilaoEncerrar);

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
