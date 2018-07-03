package agxde;

/**
*
* @author jose wilson
*/

public class ProblemaSchwefels implements Problema{

	private Integer nVar;

    public ProblemaSchwefels(Integer nVar) {
        this.nVar = nVar;
    }
    
    @Override
	public void calcularFuncaoObjetivo(Individuo individuo) {
    	
		double sum = 0.0;
		double result = 0.0; 
		
		for (int i = 0; i < individuo.getCromossomos().size(); i++) {
			Double xi = (Double) individuo.getCromossomos().get(i);
			sum += xi * Math.sin(Math.sqrt(Math.abs(xi)));
		}
		
		result = 418.9829 * individuo.getCromossomos().size() - sum;
		
		individuo.setFuncaoObjetivo(result);
	}

	@Override
	public int getDimensao() {
		return this.nVar;
	}
}