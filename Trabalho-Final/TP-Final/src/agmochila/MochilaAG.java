package agmochila;

import java.util.ArrayList;
import java.util.Random;

/**
*
* @author Jose Wilson
*/

public class MochilaAG {
	
	Integer tamanhoPopulacao; // Tamanho da populacao
    Integer minimoValor; // Valor minimo para custo por item
    Integer maximoValor; // Valor maximo para custo por item
	Integer minimoMassa; // Valor minimo para massa KG
	Integer maximoMassa; // Valor maximo para massa KG
    Integer minimoQtdDisp; // Valor minimo quantidades de itens
    Integer maximoQtdDisp; // Valor maximo quantidade de itens
    Integer capMochila; // Quantidade de Compartimentos
    Integer pesoMochila; // Peso maximo que a mochila suporta
    
    Double percentCrossover; // Porcentagem de Crossover
    Double percentMutacao; // Porcentagem de mutação
    Integer geracoes; //Numero de gerações da populacao
    
    
    Problema problema;


	public MochilaAG(Integer tamanhoPopulacao, Integer minimoValor, Integer maximoValor, Integer minimoMassa,
			Integer maximoMassa, Integer minimoQtdDisp, Integer maximoQtdDisp, Integer capMochila, Integer pesoMochila,
			Double percentCrossover, Double percentMutacao, Problema problema, Integer geracoes) {
		
		this.tamanhoPopulacao = tamanhoPopulacao;
		this.minimoValor = minimoValor;
		this.maximoValor = maximoValor;
		this.minimoMassa = minimoMassa;
		this.maximoMassa = maximoMassa;
		this.minimoQtdDisp = minimoQtdDisp;
		this.maximoQtdDisp = maximoQtdDisp;
		this.capMochila = capMochila;
		this.pesoMochila = pesoMochila;
		this.percentCrossover = percentCrossover;
		this.percentMutacao = percentMutacao;
		this.problema = problema;
		this.geracoes = geracoes;
	}
    
    Populacao populacao;
    Populacao novaPopulacao;
    Individuo melhorSolucao;

    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public Integer executar() {

        populacao = new Populacao(tamanhoPopulacao, minimoValor, maximoValor, minimoMassa, maximoMassa, minimoQtdDisp, maximoQtdDisp, capMochila, pesoMochila, problema);
        novaPopulacao = new Populacao(tamanhoPopulacao, minimoValor, maximoValor, minimoMassa, maximoMassa, minimoQtdDisp, maximoQtdDisp, capMochila, pesoMochila, problema);

        // Criar a população
        populacao.criar();

        // Avaliar
        populacao.avaliar();
        
        Random rnd = new Random();

        // Enquanto o critério de parada não for satisfeito - Gerações
        for (int g = 1; g <= geracoes; g++) {

            for (int i = 0; i < this.tamanhoPopulacao; i++) {
                // Crossover
                if (rnd.nextDouble() <= this.percentCrossover) {
                    // Realizar a operação

                	Individuo desc1 = new Individuo(capMochila, pesoMochila);
                    Individuo desc2 = new Individuo(capMochila, pesoMochila);
                    
                    // Descendente 1 -> Ind1_1 + Ind2_2;
                    crossoverUmPonto(desc1);

                    // Descendente 2 -> Ind2_1 + Ind1_2;
                    crossoverUmPonto(desc2);

                    // Mutação
                    // Descendente 1
                    mutacaoPorVariavel(desc1);
                    // Descendente 2
                    mutacaoPorVariavel(desc2);

                    // Avaliar as novas soluções
                    problema.calcularFuncaoObjetivo(desc1);
                    problema.calcularFuncaoObjetivo(desc2);

                    // Inserir na nova população
                    novaPopulacao.getIndividuos().add(desc1);
                    novaPopulacao.getIndividuos().add(desc2);
                }
            }

            // Definir sobreviventes -> populacao + descendentes
            // Merge: combinar pop+desc
            populacao.getIndividuos().addAll(novaPopulacao.getIndividuos());
            
            // Ordenar populacao;
            populacao = ordenaDecrescente(populacao);

            // Eliminar os demais individuos - criterio: tamanho da população
            populacao = eliminaExcessoInd(populacao);

            // Limpa a nova população para a geração seguinte
            novaPopulacao.getIndividuos().clear();

            //Imprimir a situacao atual
            System.out.println("Gen = " + g +
                    "\tBeneficio = "
                    + populacao.getIndividuos().get(0).getFuncaoObjetivo() +" Peso Mochila = "+ populacao.getIndividuos().get(0).verifiaPesoMochila(capMochila));
        }

        System.out.println("Referencia: ");
        System.out.println("Mochila: " + populacao.getIndividuos().get(0).getItensMochila());
        System.out.println("Massa por item: " + populacao.getMassaItens());
		System.out.println("Preço por item: " + populacao.getValorItens()); 
		System.out.println("Limite de Peso mochila: " + populacao.getIndividuos().get(0).getPesoMochila());
		System.out.println("Peso total atual da Mochila: " + populacao.getIndividuos().get(0).verifiaPesoMochila(capMochila));
        return populacao.getIndividuos().get(0).getFuncaoObjetivo();

    }

    private void crossoverUmPonto(Individuo descendente) {
    	
        Random rnd = new Random();
        ArrayList<Integer> aux = new ArrayList<>();
 
        // Ind1_1
        do {
        	
            // Recuperar o melhor indivíduo
            int ind1, ind2;
            aux = populacao.embaralhaLista(0,9, capMochila);
        	
            ind1 = rnd.nextInt(this.tamanhoPopulacao);

            do {
                ind2 = rnd.nextInt(this.tamanhoPopulacao);
            } while (ind1 == ind2);

            // Progenitores
            Individuo p1 = populacao.getIndividuos().get(ind1);
            Individuo p2 = populacao.getIndividuos().get(ind2);

            // Ponto de corte
            int corte = rnd.nextInt(p1.getCapMochila());
            
        	descendente.getItensMochila().clear();
        	
        	//CROSSOVER
        	for (int i = 0; i < corte; i++) {
	            Integer valor = aux.get(i) * p1.getItensMochila().get(i);
	            descendente.getItensMochila().add(valor);
	        }
	
	        // Ind2_2
	        for (int i = corte; i < this.capMochila; i++) {
	            Integer valor = p2.getItensMochila().get(i);
	            descendente.getItensMochila().add(valor);
	        }

        }while(descendente.verifiaPesoMochila(capMochila) > pesoMochila);

    }

    private void mutacaoPorVariavel(Individuo individuo) {

        Random rnd = new Random();
        Populacao pop = new Populacao();
        ArrayList<Integer> aux = new ArrayList<>();

        
        do { 
        	
	        for (int i = 0; i < individuo.getItensMochila().size(); i++) {
	        	if (rnd.nextDouble() <= this.percentMutacao) {
	
	                // Mutacao aritmetica
	                // Multiplicar rnd e inverter ou nao o sinal
	                Integer valor = individuo.getItensMochila().get(i);
	                // Multiplica por rnd
	                aux = pop.embaralhaLista(0, pop.getMassaItens().get(i), capMochila);
	                
	                valor = aux.get(i);
	                
	                if (valor >= 0 && valor <= pop.getQtdDisponivel().get(i)) {
	                    individuo.getItensMochila().set(i, valor);
	                }
	
	            }
	            
	        }

        }while(individuo.verifiaPesoMochila(capMochila) > pesoMochila);    

    }
    
    
    // Ordena F.O Decrescente
    public Populacao ordenaDecrescente (Populacao poulacao) {
    	
	    int teste = 0, contador = 0, indice = 0;
	    
	    Populacao ord = new Populacao(tamanhoPopulacao, minimoValor, maximoValor, minimoMassa, maximoMassa, minimoQtdDisp, maximoQtdDisp, capMochila, pesoMochila, problema);
	    
	    contador = populacao.getIndividuos().size();
	    
	    do {
	    	Individuo best = new Individuo(this.capMochila, this.pesoMochila);
	    	
	    	for(int i = 0; i < populacao.getIndividuos().size(); i++) {
		    	
		    	if (teste < populacao.getIndividuos().get(i).getFuncaoObjetivo()) {
		    		teste = populacao.getIndividuos().get(i).getFuncaoObjetivo();
		    		indice = i;
		    	}

		    	if (best.getFuncaoObjetivo() < populacao.getIndividuos().get(indice).getFuncaoObjetivo()) {
		    		
		    		best = populacao.getIndividuos().get(indice);
		    	}
		    	
		    }
		    
		    populacao.getIndividuos().remove(indice);
		    ord.getIndividuos().add(best);
		    
		    teste = 0;
		    --contador;
	    	
	    }while(contador > 0);
	    
	    return ord;
    
    }
    
    // Elimina Excesso de Individuos da populacao
    
    public Populacao eliminaExcessoInd (Populacao populacao) { 
    	
    	Populacao aux = new Populacao(tamanhoPopulacao, minimoValor, maximoValor, minimoMassa, maximoMassa, minimoQtdDisp, maximoQtdDisp, capMochila, pesoMochila, problema);
    	
    	for (int i = 0; i < this.capMochila; i++) {
    		aux.getIndividuos().add(populacao.getIndividuos().get(i));
    	}
    	return aux;
    }

}