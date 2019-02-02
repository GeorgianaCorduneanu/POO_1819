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
    Ponto_interesse hotspot;
    Aluno_licenciado(){}
    Aluno_licenciado(String username){
        super.username = username;
        super.montante_maximo = 0.0;
        super.voto_viagem = null;
        this.hotspot = null;
    }

    public Ponto_interesse getHotspot() {
        return hotspot;
    }

    public void setHotspot(Ponto_interesse hotspot) {
        this.hotspot = hotspot;
    }

    /**
     *
     * @param p, ponto de interesse escolhido por licenciado ou null se for mestrado
     * @param l, local que aluno de mestrado nao quer visitar ou null se for aluno licenciado
     */
    @Override
    void votar_ponto_interesse_local(Ponto_interesse p, Local l) {
        setHotspot(p);
        p.set_pontuacao_licenciado();
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

    /**
     * devolve o tipo de aluno
     * @return
     */
    @Override
    public String qual_tipo(){
        return "Aluno_licenciado";
    }

    @Override
    public String toString() {
        return "Aluno_licenciado: " + username;
    }
}
