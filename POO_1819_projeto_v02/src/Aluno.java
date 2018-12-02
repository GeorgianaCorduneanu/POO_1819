/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ginjo
 */
public abstract class Aluno implements Serializable {
    protected String username;
    protected Double montante_maximo;
    protected Viagem voto_viagem;
    protected Viagem viagem_escolhida_com_museu;
    protected Viagem viagem_escolhida_sem_museu;
    protected ArrayList<Ponto_interesse> lista_pontos_interesse;
    
    Aluno(){}
    Aluno(String username){
        this.username = username;
        this.montante_maximo = 0.0;
        this.voto_viagem = null;
        this.viagem_escolhida_com_museu = null;
        this.viagem_escolhida_sem_museu = null;
        lista_pontos_interesse = new ArrayList<>();
    }

    abstract void votar_ponto_interesse_local(Ponto_interesse p, Local l);


    public void votar_viagem(Viagem v){
        this.voto_viagem = v;
    }

    public ArrayList<Ponto_interesse> getLista_pontos_interesse() {
        return lista_pontos_interesse;
    }

    public void setLista_pontos_interesse(ArrayList<Ponto_interesse> lista_pontos_interesse) {
        this.lista_pontos_interesse = lista_pontos_interesse;
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

    public String qual_tipo(){
        return "Aluno";
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "username='" + username + '\'' +
                ", montante_maximo=" + montante_maximo +
                ", voto_viagem=" + voto_viagem.toString() +
                '}';
    }
}
