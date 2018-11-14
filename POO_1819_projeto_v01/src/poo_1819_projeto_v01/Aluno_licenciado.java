/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_1819_projeto_v01;

import java.util.ArrayList;

/**
 *
 * @author ginjo
 */
public class Aluno_licenciado extends Aluno{
    Aluno_licenciado(){}
    Aluno_licenciado(String username){
        super();
    }
    @Override
    void votar_ponto_interesse(Ponto_interesse_local var, ArrayList<Local> lista_locais) {
        Ponto_interesse p = (Ponto_interesse)var.getVar();
        for(Local item_l:lista_locais){
            for(Ponto_interesse item_p : item_l.lista_pontos_interesse){
                if(item_p.equals(p)){
                    item_p.set_pontuacao_licenciado();
                    return;
                }
            }
        }
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Double getMontante_maximo() {
        return montante_maximo;
    }

    @Override
    public void setMontante_maximo(Double montante_maximo) {
        this.montante_maximo = montante_maximo;
    }
}
