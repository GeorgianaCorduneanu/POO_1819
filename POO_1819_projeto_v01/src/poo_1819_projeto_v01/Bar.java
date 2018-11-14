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
public class Bar extends Ponto_interesse{
    private Double classificacao_media;
    private Double custo_consumo_bebidas;
    Bar(){}
    
    Bar(String nome_local){
        super();
        this.classificacao_media = 0.0;
        this.custo_consumo_bebidas = 0.0;
    }
    Bar(String nome_local, String horario){
        super();
        this.classificacao_media = 0.0;
        this.custo_consumo_bebidas = 0.0;
    }
    Bar(String nome_local, String horario, Double classificacao_media){
        super();
        this.classificacao_media = classificacao_media;
    }
    Bar(String nome_local, String horario, Double classificacao_media, Double custo_consumo_bebidas){
        super();
        this.classificacao_media = classificacao_media;
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
}
