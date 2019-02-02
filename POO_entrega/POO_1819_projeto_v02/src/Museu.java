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

    Museu(){
        super.nome_local = null;
        super.pontuacao = 0.0;
        super.custo = 0.0;
        super.nome_local_ponto_interesse=null;
        this.descricao = null;
    }
    
    Museu(String nome_local){
        super.nome_local = nome_local;
        super.pontuacao = 0.0;
        super.custo = 0.0;
        super.nome_local_ponto_interesse=null;
        this.descricao = null;
    }
    Museu(String nome_local, String horario){
        super.nome_local = nome_local;
        super.horario = horario;
        super.pontuacao = 0.0;
        super.nome_local_ponto_interesse=null;
        this.custo = 0.0;
        this.descricao = null;
    }
    Museu(String nome_local, String horario, String descricao){
        super.nome_local = nome_local;
        super.horario = horario;
        super.pontuacao = 0.0;
        super.nome_local_ponto_interesse=null;
        this.descricao = descricao;
        super.custo = 0.0;
    }
    Museu(String nome_local, String horario, String descricao, Double custo_entrada){
        super.nome_local = nome_local;
        super.horario = horario;
        super.pontuacao = 0.0;
        super.nome_local_ponto_interesse=null;
        this.descricao = descricao;
        super.custo = custo_entrada;
    }
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * devolve o tipo de ponto de interesse
     * @return
     */
    @Override
    public String qual_tipo(){
        return "Museu";
    }
}
