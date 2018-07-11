package agmochila;

import java.util.ArrayList;
import java.util.Collections;

/**
*
* @author Jose Wilson
*/

public class Individuo implements Comparable<Individuo> {
	
    private ArrayList<Integer> itensMochila ; // Quantidade no compartimentos
    private Integer funcaoObjetivo; // Valor da funcao objetivo do individuo

    Integer capMochila; // Quantidade de Compartimentos
    Integer pesoMochila; // Peso maximo que a mochila suporta
    
	public Individuo (Integer capMochila, Integer pesoMochila) {
		
		this.capMochila = capMochila;
		this.pesoMochila = pesoMochila;
		this.funcaoObjetivo = 0;
		this.itensMochila = new ArrayList<>();

	}

    public void criaIndividuo() {	      
        
        // Inicializa população aleatoriamente de acordo com os intervalos minimos e maximos
        
    	Populacao populacao = new Populacao();
        
        ArrayList<Integer> qtdCompartimento = new ArrayList<>();
        
        // Inicia quantidade de itens do compartimento respeitando quantidade maxima por compartimento
//        do {	
        	
        	for(int i = 0; i < capMochila; i++) {

	        	ArrayList<Integer> rnd = new ArrayList<>();
	        	
	        	 for(int j = 0 ; j <= populacao.getQtdDisponivel().get(i); j++) {
	        		 rnd.add(j);
	        	 }
	        	 Collections.shuffle(rnd);
	        	 qtdCompartimento.add(i, rnd.get(0));
        }
        
        setItensMochila(qtdCompartimento);    
    
//        }while(verifiaPesoMochila(capMochila) <= pesoMochila);
        
	}
    
    // Embaralha Arrays
    public ArrayList<Integer> embaralhaLista (Integer minimo, Integer maximo, Integer tamanho) {
	
    	ArrayList<Integer> aux = new ArrayList<>();    	
    	ArrayList<Integer> array = new ArrayList<>();
    	
        for(int i = minimo; i <= maximo; i++) {
        	aux.add(i);
        }
        
        for (int i = 0; i < tamanho; i++) {
        	Collections.shuffle(aux);
        	array.add(i, aux.get(0));
        }
        return array;
    }

    // Verifica peso total da mochila
    
    public Integer verifiaPesoMochila (Integer tamanho){
    	
    	Populacao pop = new Populacao();
    	
    	Integer resul = 0;
    	for (int i = 0; i < tamanho; i ++) {
    		resul += getItensMochila().get(i) * pop.getMassaItens().get(i);
    	}
    	return resul;
    }
    
    // Encontra Função Objetivo de cada mochila 
    
    public Integer encontraFuncObj (Integer tamanho){
    	
    	Populacao pop = new Populacao();
    	
    	Integer resul = 0;
    	for (int i = 0; i < tamanho; i ++) {
    		resul += getItensMochila().get(i) * pop.getValorItens().get(i);
    	}
    	return resul;
    }

	public ArrayList<Integer> getItensMochila () {
		return itensMochila;
	}

	public void setItensMochila(ArrayList<Integer> itensMochila) {
		this.itensMochila = itensMochila;
	}

	public Integer getFuncaoObjetivo() {
		return funcaoObjetivo;
	}

	public void setFuncaoObjetivo(Integer funcaoObjetivo) {
		this.funcaoObjetivo = funcaoObjetivo;
	}

	public Integer getPesoMochila() {
		return pesoMochila;
	}

	public void setPesoMochila(Integer pesoMochila) {
		this.pesoMochila = pesoMochila;
	}


	public Integer getCapMochila() {
		return capMochila;
	}


	public void setCapMochila(Integer capMochila) {
		this.capMochila = capMochila;
	}

    @Override
    public int compareTo(Individuo o) {
        return this.getFuncaoObjetivo().compareTo(o.getFuncaoObjetivo());
    }
	

}
