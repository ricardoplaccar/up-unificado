package up_backoffice;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import Mock.ImovelMock;
import Mock.VeiculoMock;
import model.Endereco;
import model.Foto;
import model.vTest;
import util.Gerar;

class ProdutosTest {


	int controleTempo = 1000;
	vTest test = new vTest();
	boolean finaliza = false;

	public String Texto ="Que tal realizar aquele sonho do carro novo? Na (nome da loja) você encontra toda a ajuda que precisa para tornar o seu sonho uma realidade! Carros novos e seminovos, de todas as marcas e com as melhores condições que você irá encontrar! Venha nos fazer uma visita (endereço da loja)! Estamos te aguardando!\r\n"
    		+ "Está procurando um carro bom, bonito e barato? Então você veio ao lugar certo! Aqui na (nome da loja) nós temos os melhores carros do mercado, das melhores marcas e pelos melhores preços. (nome da loja), segurança e confiabilidade!\r\n"
    		+ "Que tal ter um pouco mais de conforto além do sofá de casa e da sua cama? Então dê uma passadinha na (nome da loja) e confira todas as nossas ofertas! Carros novos e seminovos, condições incríveis de pagamento e as melhores marcas! Tudo isso te esperando em um único lugar!\r\n"
    		+ "Chega de pegar ônibus para ir trabalhar! Na (nome da loja) você consegue transformar o seu sonho do carro zero em realidade! Temos as melhores condições do mercado! Venha conhecer a nossa loja e aproveite para fazer um test-drive! Carro novo, só na (nome da loja)!\r\n"
    		+ "Cansado de ficar esperando o motorista do aplicativo chegar? Então você precisa conhecer a (nome da loja)! As melhores marcas, os melhores modelos e condições de pagamento imperdíveis! Estamos esperando por você!\r\n"
    		+ "Querendo trocar de carro, mas preocupado com o preço? Aqui na (nome da loja) você não precisa se preocupar! Aceitamos o seu seminovo como pagamento e você ainda encontra condições incríveis para financiar o restante! O melhor preço no seu seminovo só na (nome da loja)!\r\n"
    		+ "Tentando vender o carro e não consegue? Então você está no lugar certo, aqui na (nome da loja) nós trabalhamos com a tabela FIP! Aproveite para fazer uma avaliação justa agora mesmo e não perca dinheiro! Agende um horário agora mesmo!\r\n"
    		+ "Nem toda princesa precisa de carruagem, algumas só precisam de um carro novo! Aproveite os nossos preços incríveis neste Dia Internacional da Mulher! Ainda, comprando o carro no dia 8 de março, você ganha um brinde exclusivo da nossa loja! \r\n"
    		+ "A sua família está crescendo e seu carro não tem mais espaço? Não tem problema, nós da (nome da loja) temos os melhores modelos para deixar a sua família confortável! Fale com um de nossos vendedores e aproveite as nossas condições de pagamento!\r\n"
    		+ "Curtir a vida na natureza é ótimo, mas bom mesmo é não ter que se preocupar com o carro atolando, não é mesmo? Na (nome da loja) nós temos os melhores modelos de 4 por 4 do mercado! Venha conferir a nossa loja, fazer um test-drive e aproveitar as condições únicas de pagamento!\r\n"
    		+ "Em busca de preço bom, conforto e segurança para você e sua família? A (nome da loja) tem o carro perfeito para você! Venha tomar um café com a gente e conhecer nossos modelos. Carros novos e seminovos com bom preço, você só encontra na (nome da loja)!\r\n"
    		+ "Comprar um carro é o sonho de muita gente, e a (nome da loja) te ajuda a realizar esse sonho. Somente aqui você encontra os melhores modelos, as melhores marcas e ótimas condições de pagamento! Ainda aceitamos o seu seminovo de entrada!\r\n"
    		+ "Quer comprar um carro mas não sabe qual? Não tem problema, na (nome da loja), você encontra todos os modelos e marcas! Fale com um de nossos vendedores!\r\n"
    		+ "Para muitos, carro é mais que sonho, é uma paixão! E aqui na (nome da loja) a gente te entende como ninguém. Carros das melhores e em ótimas condições! Fale com quem entende a sua paixão!\r\n"
    		+ "Não perca a chance de trocar de carro! Somente neste final de semana você compra carro com zero de entrada! Isso mesmo que você ouviu, zero de entrada! Carro novo, só na (nome da loja)!";



	@Test
	void Cadastrar_Veiculo_Deve_Retornar_sucesso() {

		var vc = new VeiculoMock(test);

		WebDriver driver = LoginTest.IniciaLogin();

		driver.findElement(By.linkText("Produtos")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Produto_Nome")).click();
		driver.findElement(By.id("Produto_Nome")).sendKeys(vc.Nome);
		Gerar.Aguarde(controleTempo);
		new Select(driver.findElement(By.id("Produto_SubCategoriaCategoriaId"))).selectByVisibleText("Veículos");
		Gerar.Aguarde(controleTempo*2);
		new Select(driver.findElement(By.id("Produto_SubCategoriaId"))).selectByVisibleText("Carro de Passeio");
		Gerar.Aguarde(controleTempo);

		driver.findElement(By.id("Produto_ValorPedido")).sendKeys(vc.LanceInicial);
		driver.findElement(By.id("Produto_ValorAvaliacao")).sendKeys(vc.Avaliado);

		new Select(driver.findElement(By.id("Produto_ComitenteId"))).selectByIndex(1);

		driver.findElement(By.id("Produto_Judicial")).click();
		Gerar.Aguarde(controleTempo*2);

		new Select(driver.findElement(By.id("Produto_ProcessoJuridicoId"))).selectByVisibleText("0032949-14.2023.8.19.5");

		Gerar.Aguarde(controleTempo);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[3]/div[1]/button[1]")).click();// Salvar

		Gerar.Aguarde(controleTempo*2);



		driver.findElement(By.id("Produto_Marca")).sendKeys(vc.Marca);
		driver.findElement(By.id("Produto_Modelo")).sendKeys(vc.Modelo);
		driver.findElement(By.id("Produto_Chassi")).sendKeys(vc.NumMotor);
		driver.findElement(By.id("Produto_Renavam")).sendKeys("0112121212");

		driver.findElement(By.id("Produto_AnoModelo")).sendKeys(vc.Ano);
		driver.findElement(By.id("Produto_AnoFabricacao")).sendKeys(vc.Ano);

		driver.findElement(By.id("Produto_PlacaNumero")).sendKeys(vc.Placa);

		new Select(driver.findElement(By.id("Produto_PlacaEstadoId"))).selectByVisibleText("PR");

		driver.findElement(By.id("Produto_Quilometragem")).sendKeys(vc.KM);
		Gerar.Aguarde(controleTempo);

		var ender = new Endereco(test); //  gg

		driver.findElement(By.id("Produto_Local_Cep")).sendKeys(ender.Cep);

		driver.findElement(By.id("Produto_Local_Numero")).click();
		Gerar.Aguarde(controleTempo*2);
		driver.findElement(By.id("Produto_Local_Numero")).sendKeys("100");

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[4]/div[1]/button[2]")).click();
		Gerar.Aguarde(controleTempo * 2);

		var ListaFoto = vc.ListFotos;

		for (Foto item : ListaFoto) {
			driver.findElement(By.id("Multimidia_NovaImagem")).sendKeys(item.local);
			Gerar.Aguarde(controleTempo);
		}
		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[3]/div[1]/button[1]/i")).click();

	}

//	@Test
	void Cadastrar_Imovel_Deve_Retornar_sucesso() {

		var im = new ImovelMock(test);

			WebDriver driver = LoginTest.IniciaLogin();

		driver.findElement(By.linkText("Produtos")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.partialLinkText("Novo")).click();
		Gerar.Aguarde(controleTempo);
		driver.findElement(By.id("Produto_Nome")).click();
		driver.findElement(By.id("Produto_Nome")).sendKeys(im.Nome);
		Gerar.Aguarde(controleTempo);
		new Select(driver.findElement(By.id("Produto_SubCategoriaCategoriaId"))).selectByVisibleText(im.Categoria.Categoria );
		Gerar.Aguarde(controleTempo);
		new Select(driver.findElement(By.id("Produto_SubCategoriaId"))).selectByVisibleText(im.Categoria.SubCategoria);
		Gerar.Aguarde(controleTempo);

		driver.findElement(By.id("Produto_ValorPedido")).sendKeys(im.LancInicial);
		driver.findElement(By.id("Produto_ValorAvaliacao")).sendKeys(im.Avaliado);

		new Select(driver.findElement(By.id("Produto_ComitenteId"))).selectByIndex(1);

		driver.findElement(By.id("Produto_Judicial")).click();
		Gerar.Aguarde(controleTempo);

		new Select(driver.findElement(By.id("Produto_ProcessoJuridicoId"))).selectByVisibleText("0032949-14.2023.8.19.56");

		Gerar.Aguarde(controleTempo);
	    var endereco = new Endereco(test);
		driver.findElement(By.id("Produto_Local_Cep")).sendKeys(endereco.Cep);

		driver.findElement(By.id("Produto_Local_Numero")).click();
		Gerar.Aguarde(controleTempo*2);
		driver.findElement(By.id("Produto_Local_Numero")).sendKeys("100");


		new Select(driver.findElement(By.id("Produto_SituacaoOcupacao"))).selectByIndex(1);
		new Select(driver.findElement(By.id("Produto_EstagioObra"))).selectByIndex(5);

		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[4]/div[1]/button[2]")).click();
		Gerar.Aguarde(controleTempo * 3);


		var ListaFoto = im.ListaFotos;

		for (Foto item : ListaFoto) {
			driver.findElement(By.id("Multimidia_NovaImagem")).sendKeys(item.local);
			Gerar.Aguarde(controleTempo*2);
		}
		Gerar.Aguarde(controleTempo * 2);
		driver.findElement(By.xpath("//*[@id=\"placeholder\"]/div[3]/div[1]/button[1]/i")).click();

	}


}
