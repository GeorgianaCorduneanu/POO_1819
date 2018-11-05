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
public class Ponto_interesse {
    private String horario;
    private double pontuacao;
    private String nome_local;
    
    Ponto_interesse(){}
    Ponto_interesse(String nome_local){
        this.nome_local = nome_local;
        this.horario = null;
        this.pontuacao= 0;
    }
    
    Ponto_interesse(String horario, String nome_local){
        this.horario = horario;
        this.pontuacao = 0;
        this.nome_local = nome_local;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public double getPontuacao() {
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
    
}
