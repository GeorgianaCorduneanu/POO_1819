/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ginjo
 */
public class Universidade extends Ponto_interesse implements Serializable {
    private List<String> lista_cursos_ei;
    
    Universidade(){
        super.nome_local = null;
        super.horario = null;
        super.pontuacao = 0.0;
        super.custo = 0.0;
        super.nome_local_ponto_interesse=null;
        this.lista_cursos_ei = new ArrayList<>();
    }
    Universidade(String nome_local){
        super.nome_local = nome_local;
        super.horario = null;
        super.pontuacao = 0.0;
        super.custo = 0.0;
        super.nome_local_ponto_interesse=null;
        this.lista_cursos_ei = new ArrayList<>();
    }
    Universidade(String nome_local, String horario){
        super.nome_local = nome_local;
        super.horario = horario;
        super.pontuacao = 0.0;
        super.custo = 0.0;
        super.nome_local_ponto_interesse=null;
        this.lista_cursos_ei = new ArrayList<>();

    }
    Universidade(String nome_local, String horario, String curso_ei){
        super.nome_local = nome_local;
        super.horario = horario;
        super.pontuacao = 0.0;
        add_curso_ei(curso_ei);
    }
    Universidade(String nome_local, String horario, ArrayList<String> lista_cursos_ei){
        super.nome_local = nome_local;
        super.horario = horario;
        super.pontuacao = 0.0;
        super.custo = 0.0;
        super.nome_local_ponto_interesse=null;
        this.lista_cursos_ei = lista_cursos_ei;
    }

    /**
     * adicionar curso a lista do ponto interesse do tipo universidade
     * @param curso, curso para adicionar a lista de cursos que a universidade tem
     * @return, devolve true caso o curso nao exista e seja adicionado a lista de cursos
     * da universidade ou deovle false cas o curso exista na lista de cursos
     * da universidade
     */
    private boolean add_curso_ei(String curso){
        for(String item : lista_cursos_ei) {
            if (item.equals(curso))
                return false;
        }
        lista_cursos_ei.add(curso);
        return true;
    }
    protected boolean remove_curso_ei(String curso){
        for(String item:lista_cursos_ei){
            if(item.equals(curso)){
                lista_cursos_ei.remove(item);
                return true;
            }
        }
        return false;
    }

    /**
     * tipo de ponto de interesse
     * @return
     */
    @Override
    public String qual_tipo(){
        return "Universidade";
    }
}
