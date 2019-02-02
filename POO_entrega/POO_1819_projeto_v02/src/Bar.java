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
    Bar(){
        super.nome_local = null;
        super.horario = null;
        super.pontuacao = 0.0;
        super.nome_local_ponto_interesse=null;
        this.classificacao_media = 0.0;
        super.custo = 0.0;
    }
    
    Bar(String nome_local){
        super.nome_local = nome_local;
        super.horario = null;
        super.pontuacao = 0.0;
        super.nome_local_ponto_interesse=null;
        this.classificacao_media = 0.0;
        super.custo = 0.0;
    }
    Bar(String nome_local, String horario){
        super.nome_local = nome_local;
        super.horario = horario;
        super.pontuacao = 0.0;
        super.custo = 0.0;
        super.nome_local_ponto_interesse=null;
    }
    Bar(String nome_local, String horario, Double custo_consumo_bebidas){
        super.nome_local = nome_local;
        super.horario = horario;
        super.custo = custo_consumo_bebidas;
        super.pontuacao = 0.0;
        super.nome_local_ponto_interesse=null;
    }

    public Double getClassificacao_media() {
        return classificacao_media;
    }

    public void setClassificacao_media(Double classificacao_media) {
        this.classificacao_media = classificacao_media;
    }

    @Override
    public void setCusto(Double custo) {
        super.setCusto(this.custo +=custo);
    }

    @Override
    public String qual_tipo(){
        return "Bar";
    }

}
