/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esreal;



/**
 *
 * @author fernando
 */
public class Problema {

    private Integer nVariaveis;

    public Problema(Integer nVariaveis) {
        this.nVariaveis = nVariaveis;
    }
        
    public void calcularFuncaoObjetivo(Individuo individuo) {

        Double value = 0.0;
        
        for(int i = 0; i < individuo.getCromossomos().size(); i++) {
            Double xi = (Double) individuo.getCromossomos().get(i);
            Double minus = 10.0 * Math.cos(2.0 * Math.PI * xi);
            value +=  Math.pow(xi, 2.0) - minus;                      
        }
        
        value += 10.0 * this.nVariaveis;
        
        individuo.setFuncaoObjetivo(value);
        
    }

    public int getDimensao() {
        return this.nVariaveis;
    }
    
}
