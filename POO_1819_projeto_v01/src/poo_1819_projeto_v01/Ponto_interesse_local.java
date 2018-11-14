/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_1819_projeto_v01;

/**
 *
 * @author ginjo
 * @param <E>
 */
public class Ponto_interesse_local<E> {
    private E var;
    public Ponto_interesse_local(E var){
        this.var = var;
    }

    public E getVar() {
        return var;
    }

    public void setVar(E var) {
        this.var = var;
    }
    
}
