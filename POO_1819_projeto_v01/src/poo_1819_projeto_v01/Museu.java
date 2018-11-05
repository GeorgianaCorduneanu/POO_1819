/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_1819_projeto_v01;

/**
 *
 * @author ginjo
 */
public class Museu extends Ponto_interesse{
    private String descricao;
    private Double custo_entrada;
    
    Museu(){}
    
    Museu(String nome_local){
        super();
        this.custo_entrada = null;
        this.descricao = null;
    }
    Museu(String nome_local, String horario){
        super();
        this.custo_entrada = null;
        this.descricao = null;
    }
    Museu(String nome_local, String horario, String descricao){
        super();
        this.descricao = descricao;
        this.custo_entrada = null;
    }
    Museu(String nome_local, String horario, String descricao, Double custo_entrada){
        super();
        this.descricao = descricao;
        this.custo_entrada = custo_entrada;
    }
    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getCusto_entrada() {
        return custo_entrada;
    }

    public void setCusto_entrada(Double custo_entrada) {
        this.custo_entrada = custo_entrada;
    }
    
}
