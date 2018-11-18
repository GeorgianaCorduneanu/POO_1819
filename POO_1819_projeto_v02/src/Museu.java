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
public class Museu extends Ponto_interesse implements Serializable {
    private String descricao;
    private Double custo_entrada;
    
    Museu(){}
    
    Museu(String nome_local){
        super.nome_local = nome_local;
        super.horario = horario;
        this.custo_entrada = null;
        this.descricao = null;
    }
    Museu(String nome_local, String horario){
        super.nome_local = nome_local;
        super.horario = horario;
        this.custo_entrada = null;
        this.descricao = null;
    }
    Museu(String nome_local, String horario, String descricao){
        super.nome_local = nome_local;
        super.horario = horario;
        this.descricao = descricao;
        this.custo_entrada = null;
    }
    Museu(String nome_local, String horario, String descricao, Double custo_entrada){
        super.nome_local = nome_local;
        super.horario = horario;
        this.descricao = descricao;
        this.custo_entrada = custo_entrada;
    }
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getCusto_entrada() {
        return custo_entrada;
    }

    public void setCusto_entrada(Double custo_entrada) {
        this.custo_entrada = custo_entrada;
    }
    @Override
    public String qual_tipo(){
        return "Museu";
    }
    
}
