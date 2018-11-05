/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo_1819_projeto_v01;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ginjo
 */
public class Universidade extends Local{
    private List<String> lista_cursos_ei;
    
    Universidade(){}
    Universidade(String nome_local){
        super();
        this.lista_cursos_ei = new ArrayList<>();
    }
    Universidade(String nome_local, String horario){
        super();
        this.lista_cursos_ei = new ArrayList<>();

    }
    Universidade(String nome_local, String horario, ArrayList<String> lista_cursos_ei){
        super();
        this.lista_cursos_ei = lista_cursos_ei;
    }
    protected void add_curso_ei(String curso){
        lista_cursos_ei.add(curso);
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
}
