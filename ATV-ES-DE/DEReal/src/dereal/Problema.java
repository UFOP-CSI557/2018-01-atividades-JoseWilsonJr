/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dereal;

/**
 *
 * @author fernando
 */
public interface Problema {
 
    void calcularFuncaoObjetivo(Individuo individuo);
    int getDimensao();
    
}
