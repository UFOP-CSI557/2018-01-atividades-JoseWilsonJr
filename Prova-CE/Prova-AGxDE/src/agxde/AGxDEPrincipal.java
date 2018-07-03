package agxde;

/**
 *
 * @author jose wilson
 */
public class AGxDEPrincipal {

    public static void main(String[] args) {       
        
        Double minimo = -500.0;
        Double maximo = 500.0;
        Integer nVariaveis = 50;
        Double pCrossover = 0.78;
        Double pMutacao = 0.0039;
        Integer tamanho = 100;
        Integer geracoes = 300;

        int repeticoes = 30;
        
        Problema problemaReal = new ProblemaSchwefels(nVariaveis); 

        // Caso de teste
        for (int i = 1; i <= repeticoes; i++) {
	
	        AlgoritimoAGxDE agReal;
	
	        Double result = 0.0;
	        long startTime = System.currentTimeMillis();
	        
	        agReal = new AlgoritimoAGxDE(tamanho, pCrossover, pMutacao, geracoes, problemaReal, minimo, maximo, nVariaveis);
	        result = agReal.executar();
	        
	        long endTime = System.currentTimeMillis();
	        long totalTime = endTime - startTime;
	
	        System.out.println(i + ";" + result + ";" + totalTime);
	        System.out.flush();
	    }
        
    }
}

