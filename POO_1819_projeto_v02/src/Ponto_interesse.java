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
public class Ponto_interesse implements Serializable {
    protected String horario;
    protected Double pontuacao;
    protected String nome_local;
    
    Ponto_interesse(){}
    Ponto_interesse(String nome_local){
        this.nome_local = nome_local;
        this.horario = null;
        this.pontuacao= 0.0;
    }
    
    Ponto_interesse(String horario, String nome_local){
        this.horario = horario;
        this.pontuacao = 0.0;
        this.nome_local = nome_local;
    }
    public void add_pontuacao_utilizador(){
        this.pontuacao += 1;
    }
    public void set_pontuacao_licenciado(){
        this.pontuacao += 5;
    }
    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getNome_local() {
        return nome_local;
    }

    public void setNome_local(String nome_local) {
        this.nome_local = nome_local;
    }

    public String qual_tipo(){
        return "Ponto_interesse";
    }
}
