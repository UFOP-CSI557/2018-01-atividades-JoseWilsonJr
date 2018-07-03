package agxde;

import java.util.Collections;
import java.util.Random;
/**
*
* @author jose wilson
*/

public class AlgoritimoAGxDE {

    
    Integer tamanho; // Tamanho da população
    Double pCrossover; // Taxa de crossover - operador principal  
    Double pMutacao; // Taxa de mutação - operador secundário
    Integer geracoes; // Critério de parada - número de gerações
    Problema problema;

    // Parametros Funcao Objetivo
    Double minimo; // Mínimo
    Double maximo; // Máximo
    Integer nVariaveis; // Variáveis

    public AlgoritimoAGxDE(Integer tamanho, Double pCrossover, Double pMutacao, Integer geracoes, Problema problema, Double minimo, Double maximo, Integer nVariaveis) {
        this.tamanho = tamanho;
        this.pCrossover = pCrossover;
        this.pMutacao = pMutacao;
        this.geracoes = geracoes;
        this.problema = problema;
        this.minimo = minimo;
        this.maximo = maximo;
        this.nVariaveis = nVariaveis;
    }

    PopulacaoDouble populacao;
    PopulacaoDouble novaPopulacao;
    IndividuoDouble melhorSolucao;

    public IndividuoDouble getMelhorSolucao() {
        return melhorSolucao;
    }

    public Double executar() {

        populacao = new PopulacaoDouble(problema, minimo, maximo, nVariaveis, tamanho);
        novaPopulacao = new PopulacaoDouble(problema, minimo, maximo, nVariaveis, tamanho);

        // Criar a população
        populacao.criar();

        // Avaliar pupulacao
        populacao.avaliar();

        // Recuperar o melhor indivíduo
        Random rnd = new Random();
        int ind1, ind2;

        for (int g = 1; g <= geracoes; g++) {
            for (int i = 0; i < this.tamanho; i++) {
                
            	// Crossover
                if (rnd.nextDouble() <= this.pCrossover) {
                	
                    ind1 = rnd.nextInt(this.tamanho);

                    do {
                        ind2 = rnd.nextInt(this.tamanho);
                    } while (ind1 == ind2);

                    IndividuoDouble desc1 = new IndividuoDouble(minimo, maximo, nVariaveis);
                    IndividuoDouble desc2 = new IndividuoDouble(minimo, maximo, nVariaveis);

                    // Progenitores
                    IndividuoDouble prog1 = (IndividuoDouble) populacao.getIndividuos().get(ind1);
                    IndividuoDouble prog2 = (IndividuoDouble) populacao.getIndividuos().get(ind2);

                    // Ponto de corte
                    int corte = rnd.nextInt(prog1.getVariaveis().size());

                    // Descendente 1 -> Ind1_1 + Ind2_2;
                    crossoverUmPonto(prog1, prog2, desc1, corte);

                    // Descendente 2 -> Ind2_1 + Ind1_2;
                    crossoverUmPonto(prog2, prog1, desc2, corte);
                                        
                    // Mutação Algoritmo DE 
                    mutacaoDE(prog1, desc1);
                    mutacaoDE(prog1, desc2);
                    
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
            Collections.sort(populacao.getIndividuos());

            // Eliminar os demais individuos - criterio: tamanho da população
            populacao.getIndividuos().subList(this.tamanho, populacao.getIndividuos().size()).clear();

            // Limpa a nova população para a geração seguinte
            novaPopulacao.getIndividuos().clear();

        }
		
        return populacao.getIndividuos().get(0).getFuncaoObjetivo();

    }

    private void crossoverUmPonto(IndividuoDouble ind1, IndividuoDouble ind2, IndividuoDouble descendente, int corte) {

        // Crossover aritmetico - 1 ponto de corte
        Random rnd = new Random();
        Double alpha = rnd.nextDouble();
        
        // Ind1_1
        // alpha * P1
        for(int i = 0; i < corte; i++ ) {
            Double valor = alpha * ind1.getVariaveis().get(i);
            descendente.getVariaveis().add(valor);
        }

        // Ind2_2
        // (1 - alpha) * P2
        for(int i = corte; i < this.nVariaveis; i++ ) {
            Double valor = (1.0 - alpha) * ind2.getVariaveis().get(i);
            descendente.getVariaveis().add(valor);
        }

    }
    
    private void mutacaoDE(IndividuoDouble prog, IndividuoDouble desc) {
    	
        // trial <- r0 + F * perturbacao (trial)
        for (int i = 0; i < this.nVariaveis; i++) {

            Double valor = this.pMutacao * desc.getCromossomos().get(i)
                    + this.pMutacao * (prog.getCromossomos().get(i));

            prog.getCromossomos().set(i, reparaValor(valor));
        }

    }
    
    private Double reparaValor(Double valor) {
        if (valor < this.minimo) {
            valor = this.minimo;
        } else if (valor > this.maximo) {
            valor = this.maximo;
        }

        return valor;
    }

}
