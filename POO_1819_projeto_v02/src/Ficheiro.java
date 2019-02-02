/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.corba.se.spi.copyobject.CopyobjectDefaults;

import java.awt.peer.CanvasPeer;
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
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *classe para guardar a informacao que esteja tanto no ficheiro de objeto ou no ficheiro txt.
 * E guardado as listas de alunos ja inscritos caso se reinicie a aplciacao, as listas dos locais
 * e os nomes dos ficheiros como referencia caso queira mudar o nome dos mesmo
 * @author ginjo
 * @author projeto de poo 2018/2019
 * @version 0.2
 * @since "%I%, %G%
 */

public class Ficheiro {
    private final List<Aluno> lista_aluno;
    private final List<Local> lista_local;
    private String local_ficheiro_obj_aluno;
    private String local_ficheiro_obj_local;
    private String nome_txt_ficheiro_local;
    private String nome_txt_ficheiro_aluno;
    private String locais_populares_crescente;
    private String locais_populares_descrescente;
    private String pontos_populares_crescente;
    private String pontos_populares_descrescente;

    Ficheiro() {
        this.lista_aluno = new ArrayList<>();
        this.lista_local = new ArrayList<>();
        this.local_ficheiro_obj_aluno = "ficheiro_obj_aluno";
        this.nome_txt_ficheiro_local = "ficheiro_local.txt";
        this.nome_txt_ficheiro_aluno = "ficheiro_aluno.txt";

        leitura_txt_lista_local();
        leitura_txt_lista_aluno();
        //lista_aluno.addAll(leitura_obj_lista_aluno());
        ///escrita_obj_aluno();
        //escrita_obj_local();
    }

    public void atualiza_populares() {
        devolve_populares(true);//ordena crescente
        devolve_populares(false);//ordena decrescente
    }

    private void devolve_populares(boolean ordena) {
        //ordena->true crescente
        //ordena->false decrescente
        ArrayList<Ponto_interesse> lista_pontos_interesse_total = devolve_lista_pontos_interesse();
        String linha_popular_local = "";
        String linha_popular_ponto = "";

        if (ordena) {
            Collections.sort(lista_local, new local_sort_by_pontuacao_crescente());
            Collections.sort(lista_pontos_interesse_total, new ponto_interesse_sort_by_pontuacao_crescente());
        } else if (!ordena) {
            Collections.sort(lista_local, new local_sort_by_pontuacao_decrescente());
            Collections.sort(lista_pontos_interesse_total, new ponto_interesse_sort_by_pontuacao_decrescente());
        }

        for (Local item : lista_local) {
            if (item == null)
                continue;
            linha_popular_local += item.toString() + ", pontuacao: " + item.getPontuacao_voto() + "\n";
        }
        for (Ponto_interesse item : lista_pontos_interesse_total) {
            if (item == null)
                continue;
            linha_popular_ponto += item.toString() + ", pontuacao: " + item.getPontuacao() + "\n";
        }
        if (ordena) {
            this.locais_populares_crescente = linha_popular_local;
            this.pontos_populares_crescente = linha_popular_ponto;
        } else {
            this.locais_populares_descrescente = linha_popular_local;
            this.pontos_populares_descrescente = linha_popular_ponto;
        }
    }

    public void escrita_obj_aluno() {
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

    /**
     * adiciona o aluno a lista de alunos desta classe para depois ser escrita no ficheiro de obejtos
     *
     * @param a, a e a referencia para o aluno que se registou na plataforma.
     * @return retorna true caso o aluno nao esteja inscrito ainda na plataforma e false caso ja esteja ou ja exista um aluno com o mesmo username
     */
    public Boolean add_to_lista_aluno(Aluno a) {
        if (lista_aluno.isEmpty()) {
            lista_aluno.add(a);
            return true;
        }
        for (Aluno item : lista_aluno) {
            System.out.println(item.username + " : " + a.username);
            if (item.username.equals(a.username)) {
                return false;
            }
        }
        lista_aluno.add(a);
        return true;
    }

    /**
     * le-se os ficheiro txt de alunos ja predefinidos, maioritariamente usado para testes
     */
    private void leitura_txt_lista_aluno() {
        Aluno a;
        File f = new File(nome_txt_ficheiro_aluno);
        String[] line_separada;
        if (f.exists() && f.isFile()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String line; //username;grau_universitario
                while ((line = br.readLine()) != null) {
                    line_separada = line.split(";");
                    if (line_separada[1].equals("licenciatura"))
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

        } else {
            System.out.println("Ficheiro nao existe");
        }
    }

    /**
     * le-se os ficheiros txt de locais ja predefinidos com a sua descricao especifica
     */
    private void leitura_txt_lista_local() {
        int aux = 0; //0 local // 1 ponto interesse
        int contagem = -1;
        Local l;
        Ponto_interesse item;
        String nome_cidade = "";
        String[] linha_separada;
        File f = new File(nome_txt_ficheiro_local);
        if (f.exists() && f.isFile()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String line; //username;grau_universitario
                while ((line = br.readLine()) != null) {
                    if (line.equals("local")) {
                        aux = 0;
                        contagem++;
                        continue;
                    } else if (line.equals("ponto")) {
                        aux = 1;
                        continue;
                    } else if (line.equals("latitude")) {
                        aux = 2;
                        continue;
                    } else if (line.equals("longitude")) {
                        aux = 3;
                        continue;
                    }

                    if (aux == 0) {//nomecidade  //aux=0 para local
                        l = new Local(line);
                        nome_cidade = line;
                        lista_local.add(l);
                    }
                    if (aux == 1) { //aux=1 para ponto de interesse
                        l = lista_local.get(contagem);
                        linha_separada = line.split(";");
                        switch (linha_separada[0]) {
                            case "museu"://museu;nome;horario;descricao;preco
                                item = new Museu(linha_separada[1], linha_separada[2], linha_separada[3], (double) Integer.parseInt(linha_separada[4]));
                                item.setNome_local_ponto_interesse(nome_cidade);
                                l.add_ponto_interesse(item);
                                break;
                            case "parque": //parque;nome;horario;boolean;custo
                                item = new Parque(linha_separada[1], linha_separada[2], Boolean.getBoolean(linha_separada[3]), (double) Integer.parseInt(linha_separada[4]));
                                item.setNome_local_ponto_interesse(nome_cidade);
                                l.add_ponto_interesse(item);
                                break;
                            case "universidade": //universidade;nome;horario;curso;ei1,ei2,ei3
                                String[] cursos = linha_separada[3].split(",");
                                ArrayList<String> lista_curso = new ArrayList<>(Arrays.asList(cursos));
                                item = new Universidade(linha_separada[1], linha_separada[2], lista_curso);
                                item.setNome_local_ponto_interesse(nome_cidade);
                                l.add_ponto_interesse(item);
                                break;
                            case "bar": //bar;nome;horario;custo
                                item = new Bar(linha_separada[1], linha_separada[2], (double) Integer.parseInt(linha_separada[3]));
                                item.setNome_local_ponto_interesse(nome_cidade);
                                l.add_ponto_interesse(item);
                        }
                    }
                    if (aux == 2) {//para latitude
                        l = lista_local.get(contagem);
                        l.setLatitude(Double.parseDouble(line));
                    }
                    if (aux == 3) {//longitude
                        l = lista_local.get(contagem);
                        l.setLongitude(Double.parseDouble(line));
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

    public ArrayList<Ponto_interesse> devolve_lista_pontos_interesse() {
        ArrayList<Ponto_interesse> lista_pontos_interesse = new ArrayList<>();

        for (Local item : lista_local) {
            lista_pontos_interesse.addAll(item.getLista_pontos_interesse());
        }
        return lista_pontos_interesse;
    }

    public void atualiza_corrente_viagem(Aluno aluno) {
        for (int i = 0; i < lista_aluno.size(); i++) {
            if (lista_aluno.get(i).getUsername().equals(aluno.getUsername())) {
                lista_aluno.set(i, aluno);
                escrita_obj_aluno();
                System.out.println("Depois de escrever");
                return;
            }
            System.out.println("nao encontrou aluno");
        }
    }

    public List<Aluno> devolve_lista_aluno() {
        return lista_aluno;
    }

    public List<Local> devolve_lista_local() {
        return lista_local;
    }

    public String getLocais_populares_crescente() {
        return locais_populares_crescente;
    }


    public String getLocais_populares_descrescente() {
        return locais_populares_descrescente;
    }


    public String getPontos_populares_crescente() {
        return pontos_populares_crescente;
    }


    public String getPontos_populares_descrescente() {
        return pontos_populares_descrescente;
    }

    private class local_sort_by_pontuacao_crescente implements Comparator<Local> {

        @Override
        public int compare(Local local_1, Local local_2) {
            if (local_1.getPontuacao_voto() < local_2.getPontuacao_voto()) {
                return -1;
            }
            if (local_1.getPontuacao_voto() > local_2.getPontuacao_voto())
                return 1;
            return 0;
        }
    }

    private class ponto_interesse_sort_by_pontuacao_crescente implements Comparator<Ponto_interesse> {

        @Override
        public int compare(Ponto_interesse ponto_1, Ponto_interesse ponto_2) {
            if (ponto_1.getPontuacao() < ponto_2.getPontuacao())
                return -1;
            if (ponto_1.getPontuacao() > ponto_2.getPontuacao())
                return 1;
            return 0;
        }
    }

    private class local_sort_by_pontuacao_decrescente implements Comparator<Local> {

        @Override
        public int compare(Local local_1, Local local_2) {
            if (local_1.getPontuacao_voto() > local_2.getPontuacao_voto()) {
                return -1;
            }
            if (local_1.getPontuacao_voto() < local_2.getPontuacao_voto())
                return 1;
            return 0;
        }
    }

    private class ponto_interesse_sort_by_pontuacao_decrescente implements Comparator<Ponto_interesse> {

        @Override
        public int compare(Ponto_interesse ponto_1, Ponto_interesse ponto_2) {
            if (ponto_1.getPontuacao() > ponto_2.getPontuacao())
                return -1;
            if (ponto_1.getPontuacao() < ponto_2.getPontuacao())
                return 1;
            return 0;
        }
    }

    private ArrayList<Aluno> leitura_obj_lista_aluno() {
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
}
