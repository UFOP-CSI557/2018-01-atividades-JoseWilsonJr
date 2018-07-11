package agmochila;

/**
*
* @author Jose Wilson
*/

public class MochilaAGPrincipal {

	public static void main(String[] args) {
		
		// Parametros da Populacao
	    Integer minimoValor = 10; // Valor minimo para custo por item
	    Integer maximoValor = 50; // Valor maximo para custo por item
		Integer minimoMassa = 2; // Valor minimo para massa KG
		Integer maximoMassa = 20; // Valor maximo para massa KG
	    Integer minimoQtdDisp = 1; // Valor minimo quantidades de itens
	    Integer maximoQtdDisp = 7; // Valor maximo quantidade de itens
	    
	    Integer tamanhoPopulacao = 4; // Tamanho da Populacao
	    
	    // Parametros do Individuo
	    Integer capMochila = 4; // Quantidade de Compartimentos
	    Integer pesoMochila = 100; // Pexo maximo suportado pela mochila
	    
	    Double percentCrossover = 0.7;
	    Double percentMutacao =  0.051;
	    Integer geracoes = 100;
		
	    Problema problema = new Problema();
		
		MochilaAG ag = new MochilaAG(tamanhoPopulacao, minimoValor, maximoValor, minimoMassa, maximoMassa, minimoQtdDisp, maximoQtdDisp, capMochila, pesoMochila, percentCrossover, percentMutacao, problema, geracoes);
		ag.executar();
	}

}
