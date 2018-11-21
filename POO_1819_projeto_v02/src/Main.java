/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 *
 * @author ginjo
 */
public class Main {
    protected Viagem viagem_popular;
    protected static Local local_popular;
    protected Ponto_interesse interesse_popular;
    private static Aluno aluno_corrente;
    private static List<Local> lista_locais;
    private static List<Aluno> lista_alunos;
    private static JFrame frame;

    
    public static void main(String [] args){
        Ficheiro ficheiro_txt = new Ficheiro();
        JButton botao_login = new JButton("LOGIN");
        JButton botao_regitar = new JButton("REGISTAR");
        JTextField caixa_texto_username = new JTextField("username", 10);
        JPanel painel = new JPanel();
        JPanel painel_login = new JPanel();
        JPanel painel_referencias_alunos = new JPanel();
        JPanel painel_principal = new JPanel();
        JList list;
        JScrollPane listScroller;
        SpringLayout layout = new SpringLayout();

        lista_locais = ficheiro_txt.devolve_lista_local();
        lista_alunos = ficheiro_txt.devolve_lista_aluno();
        Aluno [] lista_alunos_list = lista_alunos.toArray(new Aluno[0]);
        //definir a frame do menu inicial com listas de alunos
        list = new JList(lista_alunos_list);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
       // listScroller = new JScrollPane(list);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //selecionar apenas um username

        frame = new JFrame();
        frame.setTitle("Menu inicial");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //usar o flowlayout

        /*painel_referencias_alunos.setLayout(new GridLayout(2,1));
        painel_referencias_alunos.add(list);
        painel_referencias_alunos.add(botao_regitar);

        painel_login.setLayout(new GridLayout(1,2));
        painel_login.add(caixa_texto_username);
        painel_login.add(botao_login);*/

        /*painel.setLayout(new BorderLayout());

        painel.add(painel_referencias_alunos, BorderLayout.NORTH);
        painel.add(painel_login, BorderLayout.SOUTH);*/
      /*  painel.setLayout(new GridLayout(3,1));

        painel.add(painel_referencias_alunos);
        painel.add(botao_regitar);*/
       // painel.add(painel_login);

       // painel_principal.setLayout(new SpringLayout());
       // painel_principal.add(painel_login);

        painel_principal.add(painel_referencias_alunos);
        painel_principal.add(painel_login);

        frame.add(painel_principal);
        frame.setVisible(true);
        /*System.out.println("********** Dentro da classe main **********");

        for(Local l:lista_locais){
            for(Ponto_interesse p:l.getLista_pontos_interesse()){
                System.out.println(l.getNome_cidade()+ " : " + p.getNome_local());
            }
        }
        for(Aluno a:lista_alunos){
            System.out.println(a.getUsername());
        }*/
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