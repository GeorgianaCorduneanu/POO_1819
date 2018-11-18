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
    
    Aluno(){}
    Aluno(String username){
        this.username = username;
        this.montante_maximo = 0.0;
        this.voto_viagem = new Viagem();
    }
    
    abstract void votar_ponto_interesse(Ponto_interesse_local var, ArrayList<Local> lista_locais); 
    
    public void votar_viagem(Viagem v){
        this.voto_viagem = v;
        
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
}
