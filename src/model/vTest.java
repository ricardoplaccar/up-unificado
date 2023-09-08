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
	public int quantidade = 1;
	public int proximo;
	public int lote = 1;
	public int quantidadeMax = 2;
	public int test_num;
	private final String arquivo = "d:\\teste.json.txt";

	public vTest() {
		Leia();

		Update();

	}

	public void Update() {

		this.test_num++;
		if ((quantidadeMax-1) >= quantidade) {
			this.lote++;
			this.proximo++;

		}

		Gravar();

	}

	private int GetInt(Object value) {
		return ((BigDecimal) value).intValue();

	}

	private void Leia() {
		try {

			Reader reader = Files.newBufferedReader(Paths.get(arquivo));

			JsonObject teste = (JsonObject) Jsoner.deserialize(reader);
			JsonObject produto = (JsonObject) teste.get("TEST_UP");

			quantidade = GetInt(produto.get("quantidade"));
			proximo = GetInt(produto.get("proximo"));
			lote = GetInt(produto.get("lote"));
			quantidadeMax = GetInt(produto.get("quantidadeMax"));
			test_num = GetInt(produto.get("test_num"));
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
			produto.put("proximo", proximo);
			produto.put("lote", lote);
			produto.put("quantidadeMax", quantidadeMax);
			produto.put("test_num", test_num);

			teste.put("TEST_UP", produto);
			Jsoner.serialize(teste, arquivoJson);
			arquivoJson.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}