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
public class Aluno_mestrado extends Aluno implements Serializable {

    Aluno_mestrado(){}
    
    Aluno_mestrado(String username){
        super.username = username;
        super.montante_maximo = 0.0;
        super.voto_viagem = new Viagem();
    }
    @Override
    void votar_ponto_interesse(Ponto_interesse_local var, ArrayList<Local> lista_locais) {
        Local p = (Local)var.getVar();
        for(Local item:lista_locais){
            if(item.nome_cidade.equals(p.nome_cidade)){
                item.votar_pontuacao_mestrado();
                return;
            }
        }
        /*this.voto_viagem.set_pontuacao_licenciado_mestrado((double)-5);
        this.voto_viagem.setPonto_interesse(p);*/
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
        return "Aluno_mestrado";
    }
}
