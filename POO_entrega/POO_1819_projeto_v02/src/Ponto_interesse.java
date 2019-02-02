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
    protected Double custo;
    protected String nome_local_ponto_interesse;

    Ponto_interesse(){
        this.nome_local = null;
        this.horario = null;
        this.pontuacao = 0.0;
        this.custo = 0.0;
        this.nome_local_ponto_interesse = null;
    }
    Ponto_interesse(String nome_local){
        this.nome_local = nome_local;
        this.horario = null;
        this.pontuacao = 0.0;
        this.custo = 0.0;
        this.nome_local_ponto_interesse = null;
    }
    
    Ponto_interesse(String horario, String nome_local){
        this.horario = horario;
        this.pontuacao = 0.0;
        this.nome_local = nome_local;
        this.custo = 0.0;
    }

    /**
     * adicionar pontuacao ao ponto de interesse caso seja escolhido pelo utilizador
     */
    public void add_pontuacao_utilizador(){
        this.pontuacao += 1.0;
    }

    /**
     * caso seja um aluno licenciado e tenha escolhido como hotspot entao vai ser
     * votado esse ponto de interesse com uma pontuacao maior que os pontos de
     * interesse comuns
     */
    public void set_pontuacao_licenciado(){
            this.pontuacao += 5.0;
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

    /**
     * tipo de ponto de interesse
     * @return
     */
    public String qual_tipo(){
        return "Ponto_interesse";
    }

    public void setPontuacao(Double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public void setNome_local_ponto_interesse(String nome_local_ponto_interesse) {
        this.nome_local_ponto_interesse = nome_local_ponto_interesse;
    }

    public String getNome_local_ponto_interesse() {
        return nome_local_ponto_interesse;
    }

    /**
     * String que vai ser escrita na combobox ou na jlist
     * @return
     */
    @Override
    public String toString() {
        return "Cidade: " + nome_local_ponto_interesse + ", nome: " + nome_local + ", horario: " + horario + ", custo: " + custo;
    }
}
