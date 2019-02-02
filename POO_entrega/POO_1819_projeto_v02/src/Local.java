/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ginjo
 */
public class Local implements Serializable{
    protected String nome_cidade;
    protected List<Ponto_interesse> lista_pontos_interesse;
    protected Double pontuacao_voto;
    private double latitude, longitude;


    Local(){
        this.nome_cidade = null;
        this.lista_pontos_interesse = new ArrayList<>();
        this.pontuacao_voto = 0.0;
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    Local(String nome_cidade){
        this.nome_cidade = nome_cidade;
        this.lista_pontos_interesse = new ArrayList<>();
        this.pontuacao_voto = 0.0;
    }

    Local(String nome_cidade, ArrayList<Ponto_interesse> lista_pontos_interesse){
        this.nome_cidade = nome_cidade;
        this.lista_pontos_interesse = lista_pontos_interesse;
        this.pontuacao_voto = 0.0;
    }

    public List<Ponto_interesse> getLista_pontos_interesse() {
        return lista_pontos_interesse;
    }

    public void setLista_pontos_interesse(List<Ponto_interesse> lista_pontos_interesse) {
        this.lista_pontos_interesse = lista_pontos_interesse;
    }

    /**
     * metodo para votar caso seja um aluno de mestrado
     */
    public void votar_pontuacao_mestrado(){
        this.pontuacao_voto -= 5.0;
    }

    /**
     * caso um dos aluno escolha este local vai ser adicionar 1
     */
    public void votar_pontuacao_voto(){
        this.pontuacao_voto += 1.0;
    }

    /**
     * metodo para adicionar um ponto de interesse ao local
     * @param p, ponto de interesse para adicionar no local
     */
    public void add_ponto_interesse(Ponto_interesse p){
        lista_pontos_interesse.add(p);
    }

    public boolean remove_ponto_interesse(Ponto_interesse p){
        for(Ponto_interesse item: lista_pontos_interesse){
            if (item.getNome_local().equals(p.getNome_local())){
                lista_pontos_interesse.remove(item);
                return true;
            }
        }
        return false;
    }

    public void setPontuacao_voto(Double pontuacao_voto) {
        this.pontuacao_voto = pontuacao_voto;
    }

    public String getNome_cidade() {
        return nome_cidade;
    }

    public void setNome_cidade(String nome_cidade) {
        this.nome_cidade = nome_cidade;
    }

    public Double getPontuacao_voto() {
        return pontuacao_voto;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * local que aparece numa combobox ou numa jlist
     * @return, formato que aparece
     */
    @Override
    public String toString() {
        return "Local: " + nome_cidade ;
     }
}
