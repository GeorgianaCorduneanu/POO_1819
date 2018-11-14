/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_1819_projeto_v01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ginjo
 */
public class Main {
    protected Viagem viagem_popular;
    protected static Local local_popular;
    protected Ponto_interesse interesse_popular;
    protected static Aluno aluno_corrente;
    protected static List<Local> lista_locais;
    protected static List<Aluno> lista_alunos;
    
    public static void main(String [] args){
        lista_locais = new ArrayList<>();
        lista_alunos = new ArrayList<>();
        //Collections.sort(lista_preferencia, new voto_custo_comparator()); //ultma coisa a fazer para apresentar por custo
        //inicializar lista de locais
        //inicializar 
    }
    public static Boolean login_aluno(String username){
        return lista_alunos.stream().anyMatch((item) -> (item.username.equals(username)));
    }
    public static Boolean registar_aluno(String username, Boolean licenciado){
        if (!lista_alunos.stream().noneMatch((item) -> (item.getUsername().equals(username))//aluno já está registado
        )) {
            return false;
        }
        if(licenciado)
            aluno_corrente = new Aluno_licenciado(username);
        else
            aluno_corrente = new Aluno_mestrado(username);
        return true;
    }
    
    public Local devolve_local_popular(){
        Local aux=null;
        for(int i=0; i<lista_locais.size()-1 ; i++){
            if(lista_locais.get(i).getPontuacao_voto() > lista_locais.get(i+1).getPontuacao_voto())
                //caso local1 seja maio
                aux = lista_locais.get(i);
            else//caso local2 seja maior
                aux = lista_locais.get(i);
            if(aux.pontuacao_voto<lista_locais.get(i).getPontuacao_voto())
                aux = lista_locais.get(i);
        }
        return aux;
                
    }
    public Ponto_interesse interesse_popular(){
        Ponto_interesse aux1=null, aux2=null, aux3=null, aux_final=null;
        for(int i=0 ; i<lista_locais.size() ; i++){
            Collections.sort(lista_locais.get(i).getLista_pontos_interesse(), new voto_pontuacao_ponto_interesse_comparator());
            Collections.sort(lista_locais.get(i).getLista_pontos_interesse(), new voto_pontuacao_ponto_interesse_comparator());
            Collections.sort(lista_locais.get(i).getLista_pontos_interesse(), new voto_pontuacao_ponto_interesse_comparator());
            
            aux_final = compara_ponto_interesse(lista_locais.get(i).getLista_pontos_interesse().get(0),
                lista_locais.get(i).getLista_pontos_interesse().get(0),
                lista_locais.get(i).getLista_pontos_interesse().get(0));
        }
        return aux_final;
    }
    private Ponto_interesse compara_ponto_interesse(Ponto_interesse aux1, Ponto_interesse aux2, Ponto_interesse aux3){
        Ponto_interesse aux_final;
        if(aux1.getPontuacao()>aux2.getPontuacao())
            aux_final = aux1;
        else
            aux_final = aux2;
        if(aux_final.getPontuacao()<aux3.getPontuacao())
            aux_final = aux3;
        return aux_final;
    }
    class voto_custo_comparator implements Comparator<Viagem>{
        @Override
        public int compare(Viagem v1, Viagem v2){
            return v1.getCusto_total().compareTo(v2.getCusto_total());
        }
    }
    class voto_pontuacao_ponto_interesse_comparator implements Comparator<Ponto_interesse>{
        @Override
        public int compare(Ponto_interesse p1, Ponto_interesse p2){
            return p1.getPontuacao().compareTo(p2.getPontuacao());
        }
    }
}