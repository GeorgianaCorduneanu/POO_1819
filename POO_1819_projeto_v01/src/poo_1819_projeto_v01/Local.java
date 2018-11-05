/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_1819_projeto_v01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ginjo
 */
public class Local {
    private String nome_cidade;
    private List<Ponto_interesse> lista_pontos_interesse;
    
    Local(){}
    Local(String nome_cidade){
        this.nome_cidade = nome_cidade;
        lista_pontos_interesse = new ArrayList<>();
    }
    Local(String nome_cidade, ArrayList<Ponto_interesse> lista_pontos_interesse){
        this.nome_cidade = nome_cidade;
        this.lista_pontos_interesse = lista_pontos_interesse;
    }
    
    protected void add_ponto_interesse(Ponto_interesse p){
        lista_pontos_interesse.add(p);
    }
    protected boolean remove_ponto_interesse(Ponto_interesse p){
        for(Ponto_interesse item: lista_pontos_interesse){
            if (item.getNome_local().equals(p.getNome_local())){
                lista_pontos_interesse.remove(item);
                return true;
            }
        }
        return false;
    }
}
