package agmochila;

/**
*
* @author Jose Wilson
*/

public class Problema {
	
	public void calcularFuncaoObjetivo(Individuo mochila) {
		    Populacao populacao = new Populacao();		    	
	    	Integer resultado = 0;
	    	for (int i = 0; i < mochila.getCapMochila(); i ++) {
	    		resultado += mochila.getItensMochila().get(i) * populacao.getValorItens().get(i);
	    	}
	    	mochila.setFuncaoObjetivo(resultado);
	}
}
