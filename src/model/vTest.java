package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

public class vTest {
	public String Desc;
	public String Num;
	public int quantidade = 0;
	public int LoteVeiculoMax = 5;
	public int LoteImovelMax = 5;

//	public int test_num= 471;
	public int test_num;
	private int LoteVeiculo;
	private int LoteImovel;
	private int Ocupacao;
	public int OcupacaoMax = 4;
	private int Estagio;
	public int EstagioMax = 5;
	private int Comitente = -1;
	private int ComitenteMax = 10;
	private String versao = "1.5.61.5";
	private String sistema = "Local_UP";
	private final String arquivo = "d:\\Up_Local.json";

	public int getComitente() {
		Comitente++;
		if (Comitente >= ComitenteMax)
			Comitente = 0;
		Gravar();

		return Comitente;
	}

	public String getVersao() {
		return versao;
	}

	public int getOcupacao() {
		Ocupacao++;
		if (Ocupacao > OcupacaoMax)
			Ocupacao = 1;
		Gravar();
		return Ocupacao;
	}

	public int getEstagio() {
		Estagio++;
		if (Estagio > EstagioMax)
			Estagio = 1;
		Gravar();
		return Estagio;
	}

	public int getLoteVeiculo() {
		LoteVeiculo++;
		if (LoteVeiculo > LoteVeiculoMax)
			LoteVeiculo = 0;

		Gravar();
		return LoteVeiculo;
	}

	public int getLoteImovel() {

		LoteImovel++;
		if (LoteImovel > LoteImovelMax)
			LoteImovel = 0;

		Gravar();
		return LoteImovel;
	}

	public vTest() {

		// Gravar();

		Leia();
		Desc = " - Test(" + Num + ")";

		Update();

	}

	public void Update() {

		this.test_num++;

		Gravar();

	}

	private int GetInt(Object value) {
		return ((BigDecimal) value).intValue();

	}

	private void Leia() {
		try {

			Reader reader = Files.newBufferedReader(Paths.get(arquivo));

			JsonObject teste = (JsonObject) Jsoner.deserialize(reader);
			JsonObject produto = (JsonObject) teste.get(sistema);

			quantidade = GetInt(produto.get("quantidade"));
			LoteVeiculoMax = GetInt(produto.get("LoteVeiculoMax"));
			LoteImovelMax = GetInt(produto.get("LoteImovelMax"));
			LoteImovel = GetInt(produto.get("LoteImovel"));
			LoteVeiculo = GetInt(produto.get("LoteVeiculo"));
			Comitente = GetInt(produto.get("Comitente"));
			ComitenteMax = GetInt(produto.get("ComitenteMax"));

			Ocupacao = GetInt(produto.get("Ocupacao"));
			OcupacaoMax = GetInt(produto.get("OcupacaoMax"));
			Estagio = GetInt(produto.get("Estagio"));
			EstagioMax = GetInt(produto.get("EstagioMax"));

			test_num = GetInt(produto.get("test_num"));
			versao = (String) produto.get("versao");
			Num = String.valueOf(test_num);
			reader.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void Gravar() {
		try {

			// create a writer
			BufferedWriter arquivoJson = Files.newBufferedWriter(Paths.get(arquivo));
			JsonObject teste = new JsonObject();
			JsonObject produto = new JsonObject();
			produto.put("quantidade", quantidade);
			produto.put("LoteVeiculo", LoteVeiculo);
			produto.put("LoteVeiculoMax", LoteVeiculoMax);
			produto.put("LoteImovel", LoteImovel);
			produto.put("LoteImovelMax", LoteImovelMax);
			produto.put("Comitente", Comitente);
			produto.put("ComitenteMax", ComitenteMax);
			produto.put("Ocupacao", Ocupacao);
			produto.put("OcupacaoMax", OcupacaoMax);
			produto.put("Estagio", Estagio);
			produto.put("EstagioMax", EstagioMax);

			produto.put("versao", versao);

			produto.put("test_num", test_num);

			teste.put(sistema, produto);
			Jsoner.serialize(teste, arquivoJson);
			arquivoJson.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}