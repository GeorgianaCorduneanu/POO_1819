/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_1819_projeto_v01;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ginjo
 */
public class Local {
    protected String nome_cidade;
    protected List<Ponto_interesse> lista_pontos_interesse;
    protected Double pontuacao_voto;
    
    Local(){}
    Local(String nome_cidade){
        this.nome_cidade = nome_cidade;
        lista_pontos_interesse = new ArrayList<>();
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
    
    public void votar_pontuacao_mestrado(){
        pontuacao_voto -= 5;
    }
    public void votar_pontuacao_voto(){
        pontuacao_voto += 1;
    }
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

    public String getNome_cidade() {
        return nome_cidade;
    }

    public void setNome_cidade(String nome_cidade) {
        this.nome_cidade = nome_cidade;
    }

    public Double getPontuacao_voto() {
        return pontuacao_voto;
    }
    
}
