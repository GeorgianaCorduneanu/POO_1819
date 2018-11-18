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
    
    Universidade(){}
    Universidade(String nome_local){
        super.nome_local = nome_local;
        super.horario = null;
        this.lista_cursos_ei = new ArrayList<>();
    }
    Universidade(String nome_local, String horario){
        super.nome_local = nome_local;
        super.horario = horario;
        this.lista_cursos_ei = new ArrayList<>();

    }
    Universidade(String nome_local, String horario, String curso_ei){
        super.nome_local = nome_local;
        super.horario = horario;
        add_curso_ei(curso_ei);
    }
    Universidade(String nome_local, String horario, ArrayList<String> lista_cursos_ei){
        super.nome_local = nome_local;
        super.horario = horario;
        this.lista_cursos_ei = lista_cursos_ei;
    }
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
    @Override
    public String qual_tipo(){
        return "Universidade";
    }
}
