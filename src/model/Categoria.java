package model;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Categoria {

	public String Categoria;
	public String SubCategoria;

	String[] scategoria = { "Veículo", "Imóvel", "Diversos" };
	String[] subveiculo = { "Carro de Passeio", "Caminhão", "Motocicleta" };
	String[] subImovel = { "Imóvel Comercial", "Apartamento", "Prédio", "Sala Comercial", "Casa" };
	String[] subGeral = { "Diversos", "Eletrônicos", "Informática" };

	private int imovel = 1;
	private int veiculo = 0;

	public Categoria(String categoria, String Sub) {
		Categoria = categoria;
		SubCategoria = Sub;
	}

	public Categoria(vTest test, ProdutoTipo tipo) {

		try {
			int ncategoria = test.Leia("Categoria");
			int ncategoraMax = scategoria.length;
			Categoria = scategoria[ncategoria];
			ncategoria++;
			if (ncategoria >= ncategoraMax)
				ncategoria = 0;
			test.Gravar("Categoria", ncategoria);
			if (tipo == ProdutoTipo.Imovel) {

				int nSubIm = test.Leia("SubImovel");
				int SubMax = subImovel.length;
				SubCategoria = subImovel[nSubIm];
				nSubIm++;
				if (nSubIm >= SubMax)
					nSubIm = 0;
				test.Gravar("SubImovel", nSubIm);

			} else

			if (tipo == ProdutoTipo.Veiculo) {
				try {
					int nSubVc = test.Leia("SubVeiculo");
					int SubMax = subveiculo.length;
					SubCategoria = subveiculo[nSubVc];
					nSubVc++;
					if (nSubVc >= SubMax)
						nSubVc = 0;
					test.Gravar("SubVeiculo", nSubVc);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (tipo == ProdutoTipo.Geral) {

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Categoria(vTest test, ProdutoTipo tipo, int cat) {
		if (tipo == ProdutoTipo.Imovel) {
			Categoria = scategoria[imovel];
			try {
				int nSubIm = test.Leia("SubImovel");
				int SubMax = subImovel.length;
				SubCategoria = subImovel[nSubIm];
				nSubIm++;
				if (nSubIm >= SubMax)
					nSubIm = 0;
				test.Gravar("SubImovel", nSubIm);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			Categoria = scategoria[veiculo];
			try {
				int nSubVc = test.Leia("SubVeiculo");
				int SubMax = subveiculo.length;
				SubCategoria = subveiculo[nSubVc];
				nSubVc++;
				if (nSubVc >= SubMax)
					nSubVc = 0;
				test.Gravar("SubVeiculo", nSubVc);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
