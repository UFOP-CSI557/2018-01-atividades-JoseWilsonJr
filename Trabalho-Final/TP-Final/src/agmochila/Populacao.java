package agmochila;

import java.util.ArrayList;
import java.util.Collections;

/**
*
* @author Jose Wilson
*/

public class Populacao {
	
    private static ArrayList<Integer> valorItens; // Beneficio cada item da mochila
    private static ArrayList<Integer> massaItens; // Peso de cada item da mochila
    private static ArrayList<Integer> qtdDisponivel; // Quantidade MAXIMA de cada item pode ser atribuida
    private ArrayList<Individuo> individuos;
    
	Integer tamanhoPopulacao; // Tamanho da populacao
    Integer minimoValor; // Valor minimo para custo por item
    Integer maximoValor; // Valor maximo para custo por item
	Integer minimoMassa; // Valor minimo para massa KG
	Integer maximoMassa; // Valor maximo para massa KG
    Integer minimoQtdDisp; // Valor minimo quantidades de itens
    Integer maximoQtdDisp; // Valor maximo quantidade de itens
    Integer capMochila; // Quantidade de Compartimentos
    Integer pesoMochila; // Peso maximo que a mochila suporta
    
    Problema problema;
    
	public Populacao(Integer tamanhoPopulacao, Integer minimoValor, Integer maximoValor, Integer minimoMassa, Integer maximoMassa,
			Integer minimoQtdDisp, Integer maximoQtdDisp, Integer capMochila, Integer pesoMochila, Problema problema) {

		this.tamanhoPopulacao = tamanhoPopulacao;
		this.minimoValor = minimoValor;
		this.maximoValor = maximoValor;
		this.minimoMassa = minimoMassa;
		this.maximoMassa = maximoMassa;
		this.minimoQtdDisp = minimoQtdDisp;
		this.maximoQtdDisp = maximoQtdDisp;
		this.capMochila = capMochila;
		this.pesoMochila = pesoMochila;
		this.problema = problema;
		this.individuos = new ArrayList<Individuo>();
		
	}
		
	public Populacao() {
	}
	
	public void criar() {
		
		setMassaItens(embaralhaLista(minimoMassa, maximoMassa, capMochila));
	    setQtdDisponivel(embaralhaLista(minimoQtdDisp, maximoQtdDisp, capMochila));
	    setValorItens(embaralhaLista(minimoValor, maximoValor, capMochila));
		
		for(int i = 0; i < tamanhoPopulacao; i++ ) {
	    	Individuo individuo = new Individuo(capMochila, pesoMochila);
	    	individuo.criaIndividuo();
	    	
	    	this.getIndividuos().add(individuo); 	
		}
		
	}
	
    public void avaliar() {
        
        for(Individuo individuo : this.getIndividuos()) {
            problema.calcularFuncaoObjetivo(individuo);
        }
        
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
    
    public ArrayList<Integer> getValorItens() {
		return valorItens;
	}

	public void setValorItens(ArrayList<Integer> valorItens) {
		Populacao.valorItens = valorItens;
	}

	public ArrayList<Integer> getMassaItens() {
		return massaItens;
	}

	public void setMassaItens(ArrayList<Integer> massaItens) {
		Populacao.massaItens = massaItens;
	}

	public ArrayList<Integer> getQtdDisponivel() {
		return qtdDisponivel;
	}

	public void setQtdDisponivel(ArrayList<Integer> qtdDisponivel) {
		Populacao.qtdDisponivel = qtdDisponivel;
	}

	public ArrayList<Individuo> getIndividuos() {
		return individuos;
	}

	public void setIndividuos(ArrayList<Individuo> individuos) {
		this.individuos = individuos;
	}

	public Integer getTamanhoPopulacao() {
		return tamanhoPopulacao;
	}

	public void setTamanhoPopulacao(Integer tamanhoPopulacao) {
		this.tamanhoPopulacao = tamanhoPopulacao;
	}
    	
}
