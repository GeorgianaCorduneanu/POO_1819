/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

/**
 *
 * @author ginjo
 */
public class Parque extends Ponto_interesse implements Serializable {
    private Boolean if_cultural;
    private Double custo_entrada_espetaculos;
    
    Parque(String nome_local){
        super.nome_local = nome_local;
        super.horario = null;
        this.if_cultural = true;
        this.custo_entrada_espetaculos = null;
    }
    Parque(String nome_local, String horario){
        super.nome_local = nome_local;
        super.horario = horario;
        this.if_cultural = true;
        this.custo_entrada_espetaculos = null;
    }
    Parque(String nome_local, String horario, Boolean if_cultural){
        super.nome_local = nome_local;
        super.horario = horario;
        this.if_cultural = true;
        this.custo_entrada_espetaculos = null;
        this.if_cultural = if_cultural;
    }
    Parque(String nome_local, String horario, Boolean if_cultural, Double custo_entrada_espetaculos){
        super.nome_local = nome_local;
        super.horario = horario;
        this.if_cultural = if_cultural;
        this.custo_entrada_espetaculos = custo_entrada_espetaculos;
    }
    @Override
    public String qual_tipo(){
        return "Parque";
    }
}
