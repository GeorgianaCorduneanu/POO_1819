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
    Local local_evitar;
    Aluno_mestrado(){}
    
    Aluno_mestrado(String username){
        super.username = username;
        super.montante_maximo = 0.0;
        super.voto_viagem = null;
        local_evitar = null;
    }

    public Local getLocal_evitar() {
        return local_evitar;
    }

    public void setLocal_evitar(Local local_evitar) {
        this.local_evitar = local_evitar;
    }

    @Override
    void votar_ponto_interesse_local(Ponto_interesse p, Local l) {
        setLocal_evitar(l);
        l.votar_pontuacao_mestrado();
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

    @Override
    public String toString() {
        return "Aluno_mestrado: " + username;
    }
}
