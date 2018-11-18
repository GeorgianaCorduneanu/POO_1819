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
public class Aluno_licenciado extends Aluno implements Serializable {
    Aluno_licenciado(){}
    Aluno_licenciado(String username){
        super.username = username;
        super.montante_maximo = 0.0;
        super.voto_viagem = new Viagem();
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
    @Override
    public String qual_tipo(){
        return "Aluno_licenciado";
    }
}
