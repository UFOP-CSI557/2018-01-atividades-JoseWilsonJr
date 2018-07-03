/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agxde;

import java.util.ArrayList;

/**
 *
 * @author fernando
 */
public class PopulacaoDouble extends Populacao<Double> {

    // Valor m�nimo
    Double minimo;
    // Valor m�ximo
    Double maximo;
    
    // N�mero de vari�veis
    Integer nVar;

    public PopulacaoDouble() {
        this.individuos = new ArrayList<>();
    }
    
    public PopulacaoDouble(Problema problema, Double minimo, Double maximo, Integer nVar, Integer tamanho) {
        this.minimo = minimo;
        this.maximo = maximo;
        this.nVar = nVar;
        this.tamanho = tamanho;
        this.problema = problema;
    }
    
    @Override
    public void criar() {
        individuos = new ArrayList<>();
        
        for (int i = 0; i < this.getTamanho(); i++) {
            Individuo individuo = 
                    new IndividuoDouble(minimo, maximo, nVar);
            individuo.criar();
            individuos.add(individuo);
            
        }        
    }
    
    
}
