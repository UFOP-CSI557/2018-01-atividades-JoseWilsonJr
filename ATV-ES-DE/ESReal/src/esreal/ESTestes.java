package esreal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ESTestes {

	public static void main(String[] args) {

	    // Parametros de valor fixo
        Double minimo = -5.12;
        Double maximo = 5.12;
        Integer nVariaveis = 100;
        Integer geracoes = 300;
        Double pMutacao = 0.0;

        // Numero de Execucoes
        int repeticoes = 30;

		Problema problema = new ProblemaRastrigin(nVariaveis);
		
        // Casos de teste
        // ES-1, ES-2;
        ArrayList<String> nomes = new ArrayList<>(Arrays.asList("ES: 1", "ES: 2"));
        for (int i = 1; i <= repeticoes; i++) {
            ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2));
            Collections.shuffle(casos);

            for (int c = 1; c <= casos.size(); c++) {
            	
            	Integer mu = 0; // Tamanho da Populacao
            	Integer lambda = 0; // Numero de Descendentes
            	
            	ESReal esReal;

                Individuo result;
                long startTime = System.currentTimeMillis();

                int teste = casos.get(c - 1);

                switch (teste) {

                    case 1:
                        mu = 20;
                        lambda = 100;
                        pMutacao = 0.05;
                        break;

                    case 2:
                        mu = 100;
                        lambda = 200;
                        pMutacao = 0.019;
                        break;
                        
                }

                esReal = new ESReal(minimo, maximo, nVariaveis, problema, mu, lambda, geracoes, pMutacao);
                result = esReal.executar();
                
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;

                
                System.out.println(nomes.get(teste - 1) + ";" + i + ";" + result + ";" + totalTime);
                System.out.flush();

            }

        }

	}

}
