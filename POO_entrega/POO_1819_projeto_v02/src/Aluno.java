/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.*;

/**
 *Ficheiro onde os atributos de cada aluno são guardados e suas escolhas feitas ao longo da aplicacao
 * @author ginjo
 */
public abstract class Aluno implements Serializable {
    protected String username;
    protected Double montante_maximo;
    protected Viagem voto_viagem;
    protected Viagem viagem_escolhida_com_museu;

    Aluno(){}

    Aluno(String username){
        this.username = username;
        this.montante_maximo = 0.0;
        this.voto_viagem = null;
        this.viagem_escolhida_com_museu = null;
    }

    /**
     * class abstrata, pode ser aluno licenciado ou aluno do mestrado
     * caso seja aluno do mestrado vai votar no local com pontuacao negativa
     * caso seja um aluno licenciado vai votar num ponto de interesse com pontuacao positiva
     * @param p, ponto de interesse escolhido por licenciado ou null se for mestrado
     * @param l, local que aluno de mestrado nao quer visitar ou null se for aluno licenciado
     */
    abstract void votar_ponto_interesse_local(Ponto_interesse p, Local l);

    /**
     * aqui vai ficar oficialmente votada a viagem com museu que o aluno escolher
     * @param v, viagem com os atributos escojhidos pelo utilizador
     */
    public void votar_viagem_com_museu(Viagem v){
        this.viagem_escolhida_com_museu = v;
        v.atualiza_pontuacao();
    }

    /**
     * viagem escolhida quando o aluno escolhe uma viagem no inciio com 3 locais
     * @param v, viagem escolhida com os locais preferidos do utilziador
     */
    public void votar_viagem(Viagem v){
        this.voto_viagem = v;
        this.voto_viagem.custo_transporte();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getMontante_maximo() {
        return montante_maximo;
    }

    public void setMontante_maximo(Double montante_maximo) {
        this.montante_maximo = montante_maximo;
    }

    public Viagem getVoto() {
        return voto_viagem;
    }

    /**
     * devolve o tipo de aluno, esta classe será overridesd pelas subclassses para
     * devolver o tipo de aluno
     * @return
     */
    public String qual_tipo(){
        return "Aluno";
    }

    public Viagem getViagem_escolhida_com_museu() {
        return viagem_escolhida_com_museu;
    }

    public void setViagem_escolhida_com_museu(Viagem viagem_escolhida_com_museu) {
        this.viagem_escolhida_com_museu = viagem_escolhida_com_museu;
    }

    /**
     * e neste formato que e impresso no Jlist
     * @return, string para por no jlist
     */
    @Override
    public String toString() {
        return "Aluno{" +
                "username='" + username + '\'' +
                ", montante_maximo=" + montante_maximo +
                ", voto_viagem=" + voto_viagem.toString() +
                '}';
    }
}
