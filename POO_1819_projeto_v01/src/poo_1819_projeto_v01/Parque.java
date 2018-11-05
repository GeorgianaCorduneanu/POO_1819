/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_1819_projeto_v01;

/**
 *
 * @author ginjo
 */
public class Parque extends Ponto_interesse{
    private Boolean if_cultural;
    private Double custo_entrada_espetaculos;
    
    Parque(String nome_local){
        super();
        this.if_cultural = true;
        this.custo_entrada_espetaculos = null;
    }
    Parque(String nome_local, String horario){
        super();
        this.if_cultural = true;
        this.custo_entrada_espetaculos = null;
    }
    Parque(String nome_local, String horario, Boolean if_cultural){
        super();
        this.if_cultural = true;
        this.custo_entrada_espetaculos = null;
        this.if_cultural = if_cultural;
    }
    Parque(String nome_local, String horario, Boolean if_cultural, Double custo_entrada_espetaculos){
        super();
        this.if_cultural = if_cultural;
        this.custo_entrada_espetaculos = custo_entrada_espetaculos;
    }
}
