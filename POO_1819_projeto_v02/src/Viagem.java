/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.List;


/**
 *
 * @author ginjo
 */
public class Viagem implements Serializable {
    protected Local local1;
    protected Local local2;
    protected Local local3;
    protected Aluno aluno;
    protected List<Ponto_interesse> lista_ponto_interesse;
    protected Double custo_total;
    protected Double custo_viagem;
    
    Viagem(){}
    
    Viagem(Local local1, Local local2, Local local3){
        this.local1 = local1;
        this.local2 = local2;
        this.local3 = local3;
        this.aluno = null;
        this.custo_total = 0.0;
        this.custo_viagem = 0.0;
    }
    public Local getLocal1() {
        return local1;
    }

    public void votar_locais(Local local1, Local local2, Local local3){
        setLocal1(local1);
        setLocal2(local2);
        setLocal3(local3);
    }
    public void add_ponto_interesse(Ponto_interesse p){
        for(Ponto_interesse item:lista_ponto_interesse){
            if(item.nome_local.equals(p.nome_local)){
                return;
            }
        }
        lista_ponto_interesse.add(p);
        p.add_pontuacao_utilizador();
    }
    
    public void setLocal1(Local local1) {
        this.local1 = local1;
        this.local1.votar_pontuacao_voto();
    }

    public Local getLocal2() {
        return local2;
    }

    public void setLocal2(Local local2) {
        this.local2 = local2;
        this.local2.votar_pontuacao_voto();
    }

    public Local getLocal3() {
        return local3;
    }

    public void setLocal3(Local local3) {
        this.local3 = local3;
        this.local2.votar_pontuacao_voto();
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }


    public Double getCusto_total() {
        return custo_total;
    }

    public void setCusto_total(Double custo_total) {
        this.custo_total = custo_total;
    }

    public Double getCusto_viagem() {
        return custo_viagem;
    }

    public void setCusto_viagem(Double custo_viagem) {
        this.custo_viagem = custo_viagem;
    }
    
}
