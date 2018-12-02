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
public class Bar extends Ponto_interesse implements Serializable {
    private Double classificacao_media;
    private Double custo_consumo_bebidas;
    Bar(){}
    
    Bar(String nome_local){
        super.nome_local = nome_local;
        super.horario = null;
        this.classificacao_media = 0.0;
        this.custo_consumo_bebidas = 0.0;
    }
    Bar(String nome_local, String horario){
        super.nome_local = nome_local;
        super.horario = horario;    }
    Bar(String nome_local, String horario, Double custo_consumo_bebidas){
        super.nome_local = nome_local;
        super.horario = horario;
        this.custo_consumo_bebidas = custo_consumo_bebidas;
    }

    public Double getClassificacao_media() {
        return classificacao_media;
    }

    public void setClassificacao_media(Double classificacao_media) {
        this.classificacao_media = classificacao_media;
    }

    public Double getCusto_consumo_bebidas() {
        return custo_consumo_bebidas;
    }

    public void addConsumo(Double c){
        custo_consumo_bebidas += custo_consumo_bebidas;
    }

    @Override
    public String qual_tipo(){
        return "Bar";
    }

}
