package dereal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DETestes {

	public static void main(String[] args) {
        
        Double minimo = -5.12;
        Double maximo = 5.12;
        Integer D = 100; // Numero de variaveis
        Integer gmax = 300; // Criterio de parada

        // Numero de Execucoes
        int repeticoes = 30;
        
        Problema problema = new ProblemaRastrigin(D);


        // Parametros nao modificados
        Integer Np = 0;


        // Casos de teste
        // 1 - DE: 1; 2 - DE: 2
        ArrayList<String> nomes = new ArrayList<>(Arrays.asList("DE: 1", "DE: 2"));
        for (int i = 1; i <= repeticoes; i++) {
        	Double Cr = 0.0; // Coeficiente de Crossover
            Double F = 0.0; // Coeficiente de Mutacao
            
            ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1, 2));
            Collections.shuffle(casos);

            for (int c = 1; c <= casos.size(); c++) {               

                DEReal deReal;

                Individuo result;
                long startTime = System.currentTimeMillis();

                int teste = casos.get(c-1);

                switch (teste) {

                    case 1:
                    	Np = 10;
                    	Cr = 0.74;
                        F = 0.0002;
                        break;

                    case 2:
                    	Np = 40;
                    	Cr = 0.9;
                    	F = 0.00089;
                        break;
                }
                
                deReal = new DEReal(minimo, maximo, problema, gmax, D, Np, F, Cr);
                result = deReal.executar();
                
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;

                System.out.println(nomes.get(teste - 1) + ";" + i + ";" + result + ";" + totalTime);
                System.out.flush();
            }

        }

	}

}
