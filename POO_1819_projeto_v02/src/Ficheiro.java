/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ginjo
 */
public class Ficheiro {
    private final List<Aluno> lista_aluno;
    private final List<Local> lista_local;
    private String local_ficheiro_obj_local;
    private String local_ficheiro_obj_aluno;
    private String nome_txt_ficheiro_local;
    private String nome_txt_ficheiro_aluno;
    
    Ficheiro(){
        this.lista_aluno = new ArrayList<>();
        this.lista_local = new ArrayList<>();
        this.local_ficheiro_obj_local = "ficheiro_obj_local";
        this.local_ficheiro_obj_aluno = "ficheiro_obj_aluno";
        this.nome_txt_ficheiro_local = "ficheiro_local.txt";
        this.nome_txt_ficheiro_aluno = "ficheiro_aluno.txt";
        leitura_txt_lista_local();
        leitura_txt_lista_aluno();
        escrita_obj_aluno();
        escrita_obj_local();
        /*System.out.println("********** Dentro da classe ficheiro **********");
        ArrayList<Local> l = leitura_obj_lista_local();
        ArrayList<Aluno> a = leitura_obj_lista_aluno();
        for(Local item :l){
            for(Ponto_interesse p:item.getLista_pontos_interesse()){
                System.out.println(item.getNome_cidade()+ " : " + p.getNome_local());
            }
        }
        for(Aluno item :a){
            System.out.println(item.getUsername());
        }*/

    }
    Ficheiro(String nome_ficheiro_obj_local, String nome_ficheiro_obj_aluno, String nome_txt_ficheiro_local, String nome_txt_ficheiro_aluno){
        this.lista_aluno = new ArrayList<>();
        this.lista_local = new ArrayList<>();
        this.local_ficheiro_obj_aluno = nome_ficheiro_obj_aluno;
        this.local_ficheiro_obj_local = nome_ficheiro_obj_local;
        this.nome_txt_ficheiro_local = nome_txt_ficheiro_local;
        this.nome_txt_ficheiro_aluno = nome_txt_ficheiro_aluno;
    }
    public void escrita_obj_local(){
        FileOutputStream fout;
        ObjectOutputStream oos;
        try {
            fout = new FileOutputStream(local_ficheiro_obj_local);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(lista_local);
            fout.close();
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ficheiro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ficheiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void escrita_obj_aluno(){
        FileOutputStream fout;
        ObjectOutputStream oos;
        try {
            fout = new FileOutputStream(local_ficheiro_obj_aluno);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(lista_aluno);
            fout.close();
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ficheiro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ficheiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Boolean add_to_lista_local(Local local){
        for(Local item :lista_local){
            if(item.nome_cidade.equals(local.nome_cidade)){
                return false;
            }
        }
        return true;
    }
    public Boolean add_to_lista_aluno(Aluno a){
       for(Aluno item :lista_aluno){
            if(item.username.equals(a.username)){
                return false;
            }
        }
        return true;
    }
    private void leitura_txt_lista_aluno(){
        Aluno a;
        File f =  new File(nome_txt_ficheiro_aluno);
        String [] line_separada;
        if(f.exists() && f.isFile()){
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                
                String line; //username;grau_universitario
                while((line = br.readLine()) != null){
                    line_separada = line.split(";");
                    if(line_separada[1].equals("licenciatura"))
                        a = new Aluno_licenciado(line_separada[0]);
                    else
                        a = new Aluno_mestrado(line_separada[0]);
                    lista_aluno.add(a);
                }
                br.close();
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            
        }else{
            System.out.println("Ficheiro nao existe");
        }    
    }
    private void leitura_txt_lista_local(){
        int aux=0; //0 local // 1 ponto interesse
        int contagem=-1;
        Local l;
        Ponto_interesse item;
        String [] linha_separada;
        File f =  new File(nome_txt_ficheiro_local);
        if(f.exists() && f.isFile()){
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                
                String line; //username;grau_universitario
                while((line = br.readLine()) != null){
                    if(line.equals("local")){
                        aux=0;
                        contagem ++;
                        continue;
                    }else if(line.equals("ponto")) {
                        aux = 1;
                        continue;
                    }
                    if(aux == 0){//nomecidade
                        l = new Local(line);
                        lista_local.add(l);
                    }
                    if(aux == 1){
                        l = lista_local.get(contagem);
                        linha_separada = line.split(";");
                        switch (linha_separada[0]) {
                            case "museu"://museu;nome;horario;descricao;preco
                                item = new Museu(linha_separada[1], linha_separada[2], linha_separada[3], (double)Integer.parseInt(linha_separada[4]));
                                System.out.println(item.getNome_local() + " : " + linha_separada[0] + " : " + linha_separada[1] +" : " + linha_separada[2] +" : " + linha_separada[3] +" : " + (double)Integer.parseInt(linha_separada[4]));
                                l.add_ponto_interesse(item);
                                break;
                            case "parque": //parque;nome;horario;boolean;custo
                                item = new Parque(linha_separada[1], linha_separada[2], Boolean.getBoolean(linha_separada[3]), (double)Integer.parseInt(linha_separada[4]));
                                l.add_ponto_interesse(item);
                                break;
                            case "universidade": //universidade;nome;horario;curso;ei1,ei2,ei3
                                String [] cursos = linha_separada[3].split(",");
                                ArrayList<String> lista_curso = new ArrayList<>(Arrays.asList(cursos));
                                item = new Universidade(linha_separada[1], linha_separada[2], lista_curso);
                                l.add_ponto_interesse(item);
                                break;
                            case "bar": //bar;nome;horario;custo
                                item = new Bar(linha_separada[1], linha_separada[2], (double)Integer.parseInt(linha_separada[3]));
                                l.add_ponto_interesse(item);
                        }

                    }

                }
                br.close();
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            
        }
    }
    private ArrayList<Local> leitura_obj_lista_local(){
        ArrayList<Local> lista = new ArrayList<>();
        FileInputStream filein;
        ObjectInputStream ois;
        try {
            filein = new FileInputStream(local_ficheiro_obj_local);
            ois = new ObjectInputStream(filein);
            lista = (ArrayList<Local>) ois.readObject();
            ois.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ficheiro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ficheiro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ficheiro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    private ArrayList<Aluno> leitura_obj_lista_aluno(){
        ArrayList<Aluno> lista = new ArrayList<>();
        FileInputStream filein;
        ObjectInputStream ois;
        try {
            filein = new FileInputStream(local_ficheiro_obj_aluno);
            ois = new ObjectInputStream(filein);
            lista = (ArrayList<Aluno>) ois.readObject();
            ois.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ficheiro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ficheiro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ficheiro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void setNome_ficheiro_obj_local(String nome_ficheiro_obj_local) {
        this.local_ficheiro_obj_local = nome_ficheiro_obj_local;
    }

    public void setNome_ficheiro_obj_aluno(String nome_ficheiro_obj_aluno) {
        this.local_ficheiro_obj_aluno = nome_ficheiro_obj_aluno;
    }

    public void setNome_txt_ficheiro_local(String nome_txt_ficheiro_local) {
        this.nome_txt_ficheiro_local = nome_txt_ficheiro_local;
    }

    public void setNome_txt_ficheiro_aluno(String nome_txt_ficheiro_aluno) {
        this.nome_txt_ficheiro_aluno = nome_txt_ficheiro_aluno;
    }
    
    public List<Aluno> devolve_lista_aluno() {
        return lista_aluno;
    }

    public List<Local> devolve_lista_local() {
        return lista_local;
    }

    public String getNome_ficheiro_obj_local() {
        return local_ficheiro_obj_local;
    }

    public String getNome_ficheiro_obj_aluno() {
        return local_ficheiro_obj_aluno;
    }

    public String getNome_txt_ficheiro_local() {
        return nome_txt_ficheiro_local;
    }

    public String getNome_txt_ficheiro_aluno() {
        return nome_txt_ficheiro_aluno;
    }
    
    
}
