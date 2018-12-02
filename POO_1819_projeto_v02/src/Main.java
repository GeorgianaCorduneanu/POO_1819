/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import sun.misc.JavaxCryptoSealedObjectAccess;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private static List<Ponto_interesse> lista_pontos_interesse;
    private static List<Aluno> lista_alunos;
    private static JFrame frame;
    private static Ficheiro ficheiro_txt;

    
    public static void main(String [] args){
        ficheiro_txt = new Ficheiro();
        lista_locais = ficheiro_txt.devolve_lista_local();
        lista_alunos = ficheiro_txt.devolve_lista_aluno();
        frame = new JFrame();
        menu_inicial();
    }

    public static void menu_inicial(){
        JButton botao_login = new JButton("LOGIN");
        botao_login.setSize(10,10);
        JButton botao_regitar = new JButton("REGISTAR");
        JTextField caixa_texto_username = new JTextField("username", 10);
        JLabel label_titulo = new JLabel("MENU INICIAL");

        JRadioButton radio_botao_licenciatura = new JRadioButton("Licenciatura");
        JRadioButton radio_botao_mestrado = new JRadioButton("Mestrado");

        JPanel painel_login = new JPanel();
        JPanel painel_referencias_alunos = new JPanel();
        JPanel painel_principal = new JPanel();
        JList list;

        Aluno [] lista_alunos_list = lista_alunos.toArray(new Aluno[0]);
        //definir a frame do menu inicial com listas de alunos
        list = new JList(lista_alunos_list);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(3);
        JScrollPane listScroller = new JScrollPane(list);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //selecionar apenas um username

        frame.setTitle("Menu inicial");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //usar o flowlayout

        painel_referencias_alunos.setLayout(new GridLayout(3,1));
        painel_referencias_alunos.add(label_titulo);
        painel_referencias_alunos.add(listScroller);
        painel_referencias_alunos.add(botao_login);

        painel_login.setLayout(new GridLayout(2,2));
        painel_login.add(caixa_texto_username);
        painel_login.add(botao_regitar);
        painel_login.add(radio_botao_licenciatura);
        painel_login.add(radio_botao_mestrado);

        painel_principal.setLayout(new FlowLayout(FlowLayout.CENTER, 5,10));
        painel_principal.add(painel_referencias_alunos);

        painel_principal.add(painel_login);

        frame.add(painel_principal);
        frame.setVisible(true);

        botao_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(list.getSelectedValue() != null){
                    limpa_frame();
                    botao_login.removeActionListener(this::actionPerformed);
                    aluno_corrente = (Aluno)list.getSelectedValue();
                    System.out.println(aluno_corrente.username);
                    verifica_voto_null();
                }
            }
        });
        botao_regitar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Aluno a;
                if(radio_botao_licenciatura.isSelected()) {
                    a = registar_aluno(caixa_texto_username.getText(), true);
                    if(a == null) {//caso o aluno licenciado ja exista
                        JOptionPane.showMessageDialog(null, "Aluno ja existe", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                    limpa_frame();
                    botao_regitar.removeActionListener(this::actionPerformed);
                    pagina_votar_viagem();
                }else if(radio_botao_mestrado.isSelected()) {
                    a = registar_aluno(caixa_texto_username.getText(), false);
                    if(a == null) {//caso o aluno em mestrado ja exista
                        JOptionPane.showMessageDialog(null, "Aluno ja existe", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                        return;
                    }
                    limpa_frame();
                    botao_regitar.removeActionListener(this::actionPerformed);
                    pagina_votar_viagem();
                }else if(!radio_botao_licenciatura.isSelected() && !radio_botao_mestrado.isSelected()){
                    JOptionPane.showMessageDialog(null, "Selecionar tipo gradutacao", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

    private static void limpa_frame(){
        frame.getContentPane().removeAll();
        frame.repaint();
    }

    private static void verifica_voto_null(){
        System.out.println("Voto e:" + aluno_corrente.getVoto());
        if(aluno_corrente.getVoto() == null){
            pagina_votar_viagem();
        }else{
            pagina_inicial();
        }
    }

    private static void pagina_votar_viagem(){
        Local [] lista_locais_list = lista_locais.toArray(new Local[0]);

        JLabel label_titulo = new JLabel("VOTAR VIAGEM");
        JLabel label_aluno = new JLabel("Aluno: " + aluno_corrente.getUsername());
        JPanel painel_principal = new JPanel(new FlowLayout());
        JPanel painel_curso = new JPanel(new GridLayout(2,1));
        JPanel painel_locais = new JPanel(new GridLayout(3, 1));
        JPanel painel_botao = new JPanel(new BorderLayout());

        JButton botao_voltar = new JButton("VOLTAR");
        JButton botao_seguinte = new JButton("SEGUINTE");

        JComboBox lista_locais1 = new JComboBox<>(lista_locais_list);
        lista_locais1.setPreferredSize(new Dimension(120,25));
        JComboBox lista_locais2 = new JComboBox<>(lista_locais_list);
        lista_locais2.setPreferredSize(new Dimension(120,25));
        JComboBox lista_locais3 = new JComboBox<>(lista_locais_list);
        lista_locais3.setPreferredSize(new Dimension(120,25));
        if(aluno_corrente.getVoto()!=null){
            lista_locais1.setSelectedItem(aluno_corrente.getVoto().getLocal1());
            lista_locais2.setSelectedItem(aluno_corrente.getVoto().getLocal2());
            lista_locais3.setSelectedItem(aluno_corrente.getVoto().getLocal3());
        }else {
            lista_locais1.setSelectedItem(null);
            lista_locais2.setSelectedItem(null);
            lista_locais3.setSelectedItem(null);
        }


        botao_seguinte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lista_locais1.getSelectedItem() != null && lista_locais2.getSelectedItem()!= null &&
                        lista_locais3.getSelectedItem()!= null && lista_locais1.getSelectedItem() != lista_locais2.getSelectedItem()
                && lista_locais1.getSelectedItem() != lista_locais3.getSelectedItem() && lista_locais2.getSelectedItem()!=lista_locais3.getSelectedItem()){
                    Viagem v = new Viagem((Local)lista_locais1.getSelectedItem(), (Local)lista_locais2.getSelectedItem(), (Local)lista_locais3.getSelectedItem());
                    aluno_corrente.votar_viagem(v);
                    ficheiro_txt.atualiza_corrente_viagem(aluno_corrente);
                    limpa_frame();
                    votar_pontos_hot_not();
                }else{
                    JOptionPane.showMessageDialog(null, "Erro! locais iguais ou sem locais selecionados!", "Mensagem", JOptionPane.PLAIN_MESSAGE);

                }
            }
        });
        /*lista_locais_pontos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicializar_ponto_lista((Local)lista_locais1.getSelectedItem(), (Local)lista_locais2.getSelectedItem(), (Local)lista_locais3.getSelectedItem(), lista_locais_pontos);
                Ponto_interesse [] lista_ponto_list = lista_pontos_voto.toArray(new Ponto_interesse[0]);
                lista_locais_pontos = new JComboBox<>(lista_ponto_list);

            }
        });*/
        botao_voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int botao_yes = JOptionPane.YES_NO_OPTION;
                int resultado = JOptionPane.showConfirmDialog(null, "Certeza pretende confirmar", " confirmar",botao_yes);
                if(resultado == 0){
                    botao_voltar.removeActionListener(this::actionPerformed);
                    ficheiro_txt.atualiza_corrente_viagem(aluno_corrente);
                    limpa_frame();
                    menu_inicial();
                }
            }
        });
       /* if(aluno_corrente.qual_tipo().equals("Aluno_licenciado")) {
            Ponto_interesse [] lista_ponto_interesse = lista_pontos_voto.toArray(new Ponto_interesse[0]);
            lista_locais_pontos = new JComboBox(lista_ponto_interesse);
        }*/
        painel_locais.add(lista_locais1);
        painel_locais.add(lista_locais2);
        painel_locais.add(lista_locais3);

        painel_botao.add(botao_voltar, BorderLayout.WEST);
        painel_botao.add(botao_seguinte, BorderLayout.EAST);

        painel_principal.add(label_titulo);
        painel_principal.add(label_aluno);
        painel_principal.add(painel_curso);
        painel_principal.add(painel_locais);
        painel_principal.add(painel_botao);

        frame.setTitle("Votar Viagem");
        frame.add(painel_principal);
        frame.validate();
    }

    private static void votar_pontos_hot_not(){
        JLabel label_titulo = new JLabel("Votar pontos hot");
        JLabel label_aluno = new JLabel("Aluno: " + aluno_corrente.getUsername());
        JLabel label_hot_not;
        JLabel label_pontos_interesse = new JLabel("Selecione Pontos de Interesse");
        JComboBox comboBox_hot_not;
        JPanel painel_principal = new JPanel(new FlowLayout());
        JPanel painel_hot_not = new JPanel(new GridLayout(2, 1));
        JPanel painel_pontos_interesse = new JPanel(new GridLayout(2, 1));
        JPanel painel_botao = new JPanel(new BorderLayout());
        JList list_ponto_interesse_para_inserir;
        JScrollPane listScroller;
        JButton botao_voltar;
        JButton botao_registar;

        ArrayList<Ponto_interesse> lista_locais_pontos = new ArrayList<Ponto_interesse>();

        lista_locais_pontos.addAll(aluno_corrente.getVoto().getLocal1().getLista_pontos_interesse());
        lista_locais_pontos.addAll(aluno_corrente.getVoto().getLocal2().getLista_pontos_interesse());
        lista_locais_pontos.addAll(aluno_corrente.getVoto().getLocal3().getLista_pontos_interesse());
        Ponto_interesse [] lista_ponto_interesse = lista_locais_pontos.toArray(new Ponto_interesse[0]);

        if(aluno_corrente.qual_tipo().equals("Aluno_licenciado")){
            System.out.println(aluno_corrente.qual_tipo());
            label_hot_not = new JLabel("Selecione Hot Spot");
            comboBox_hot_not = new JComboBox(lista_ponto_interesse);
        }else{
            System.out.println(aluno_corrente.qual_tipo());
            label_hot_not = new JLabel("Slecione Local a Evitar");
            Local [] lista_locais_list = lista_locais.toArray(new Local[0]);
            comboBox_hot_not = new JComboBox(lista_locais_list);
        }

        list_ponto_interesse_para_inserir = new JList(lista_ponto_interesse);
        list_ponto_interesse_para_inserir.setLayoutOrientation(JList.VERTICAL);
        list_ponto_interesse_para_inserir.setVisibleRowCount(5);
        listScroller = new JScrollPane(list_ponto_interesse_para_inserir);
        list_ponto_interesse_para_inserir.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        if(aluno_corrente.getVoto() != null && aluno_corrente.qual_tipo().equals("Aluno_licenciado")) {
            comboBox_hot_not.setSelectedItem(((Aluno_licenciado) aluno_corrente).getHotspot());
        }else if(aluno_corrente.getVoto() != null && aluno_corrente.qual_tipo().equals("Aluno_mestrado")) {
            comboBox_hot_not.setSelectedItem(((Aluno_mestrado) aluno_corrente).getLocal_evitar());
        }else
            comboBox_hot_not.setSelectedItem(null);

        botao_registar = new JButton("REGISTAR");
        botao_voltar = new JButton("VOLTAR");

        comboBox_hot_not.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(aluno_corrente.qual_tipo().equals("Aluno_licenciado")){
                    System.out.println("em licenciatura");
                    aluno_corrente.votar_ponto_interesse_local((Ponto_interesse)comboBox_hot_not.getSelectedItem(), null);
                }else{
                    System.out.println("em mestrado");
                    aluno_corrente.votar_ponto_interesse_local(null, (Local)comboBox_hot_not.getSelectedItem());
                }
            }
        });
        botao_registar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comboBox_hot_not.getSelectedItem()!=null && list_ponto_interesse_para_inserir.getSelectedValue() != null) {
                    botao_registar.removeActionListener(this::actionPerformed);
                    //criar a funcao de aceitar os pontos de interesse da lista e por no aluno_corrente depois atualizar o ficheiro
                    aluno_corrente.setLista_pontos_interesse((ArrayList<Ponto_interesse>) list_ponto_interesse_para_inserir.getSelectedValuesList());
                    ficheiro_txt.atualiza_corrente_viagem(aluno_corrente);
                    limpa_frame();
                    pagina_inicial();
                }else{
                    JOptionPane.showMessageDialog(null, "Ponto nao escolhido", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        botao_voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botao_voltar.removeActionListener(this::actionPerformed);
                ficheiro_txt.atualiza_corrente_viagem(aluno_corrente);
                limpa_frame();
                pagina_votar_viagem();
            }
        });

        painel_hot_not.add(label_hot_not);
        painel_hot_not.add(comboBox_hot_not);

        painel_pontos_interesse.add(label_pontos_interesse);
        painel_pontos_interesse.add(list_ponto_interesse_para_inserir);

        painel_botao.add(botao_voltar, BorderLayout.WEST);
        painel_botao.add(botao_registar, BorderLayout.EAST);

        painel_principal.add(label_titulo);
        painel_principal.add(label_aluno);
        painel_principal.add(painel_hot_not);
        painel_principal.add(painel_pontos_interesse);
        painel_principal.add(painel_botao);

        frame.setTitle("Votar Pontos/Loca Hot/Evitar");
        frame.add(painel_principal);
        frame.validate();
    }

    private static void pagina_inicial(){
        System.out.println("em pagina inicial");
        JLabel label_titulo = new JLabel("PAGINA INICIAL");
        JLabel label_aluno = new JLabel("Aluno: " + aluno_corrente.getUsername());
        JButton botao_escolher_viagem_sem_museu,botao_sair, botao_escolher_viagem_com_museu, botao_ver_populares, botao_personalizar_viagem, botao_ver_perfil;
        JPanel painel_principal = new JPanel(new FlowLayout());
        JPanel painel_botao = new JPanel(new BorderLayout());

        botao_escolher_viagem_com_museu = new JButton("Escolher viagem com museu");
        botao_escolher_viagem_sem_museu = new JButton("Escolher viagem sem museu");
        botao_personalizar_viagem = new JButton("Personalizar viagem");
        botao_ver_populares = new JButton("Ver Populares");
        botao_ver_perfil = new JButton("Perfil");
        botao_sair = new JButton("SAIR");

        botao_sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });
        botao_ver_perfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpa_frame();
                botao_ver_perfil.removeActionListener(this::actionPerformed);
                ver_perfil();
            }
        });
        botao_personalizar_viagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpa_frame();
                botao_personalizar_viagem.removeActionListener(this::actionPerformed);
                pagina_votar_viagem();
            }
        });

        botao_escolher_viagem_com_museu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpa_frame();
                botao_escolher_viagem_com_museu.removeActionListener(this::actionPerformed);
                escolher_viagem_com_museu();
            }
        });

        painel_botao.add(botao_sair, BorderLayout.WEST);
        painel_botao.add(botao_ver_perfil, BorderLayout.EAST);

        painel_principal.add(label_titulo);
        painel_principal.add(label_aluno);
        painel_principal.add(botao_escolher_viagem_com_museu);
        painel_principal.add(botao_escolher_viagem_sem_museu);
        painel_principal.add(botao_personalizar_viagem);
        painel_principal.add(botao_ver_populares);
        painel_principal.add(painel_botao);

        frame.setTitle("Pagina Inicial");
        frame.add(painel_principal);
        frame.validate();
    }

   /* public static Boolean login_aluno(String username){
        return lista_alunos.stream().anyMatch((item) -> (item.username.equals(username)));
    }*/
    public static Aluno registar_aluno(String username, Boolean licenciado){
        Aluno a;
        if (!lista_alunos.stream().noneMatch((item) -> (item.getUsername().equals(username))//aluno já está registado
        )) {
            return null;
        }
        if(licenciado) {
            a = new Aluno_licenciado(username);
            aluno_corrente = a;
            System.out.println("em licenciado: " + aluno_corrente.username + " : " + a.username);
        }else {
            a = new Aluno_mestrado(username);
            aluno_corrente = a;
            System.out.println("em mestrado: " + aluno_corrente.username + " : " + a.username);

        }

        System.out.println(aluno_corrente.username);

        lista_alunos.add(aluno_corrente);
        ficheiro_txt.add_to_lista_aluno(aluno_corrente);
        ficheiro_txt.escrita_obj_aluno();
        return aluno_corrente;
    }
    public static void ver_perfil(){
        JPanel painel_utilizador = new JPanel(new GridLayout(1,2));
        JPanel painel_voto_viagem = new JPanel(new GridLayout(1, 2));
        JPanel painel_lista_ponto_interesse = new JPanel(new GridLayout(1,1));
        JPanel painel_botao = new JPanel(new BorderLayout());
        JPanel painel_principal = new JPanel(new FlowLayout());

        JLabel label_username, label_montante, label_locais, label_ponto_hot_evitar;
        JList list_pontos_interesse;
        JButton botao_voltar = new JButton("VOLTAR");
        JButton botao_sair = new JButton("SAIR");
        JScrollPane listScroller;
        label_username = new JLabel("Username: " + aluno_corrente.getUsername());
        label_montante = new JLabel("Montante Maximo: " + aluno_corrente.getMontante_maximo());
        label_locais = new JLabel("Viagem: " + aluno_corrente.getVoto().getLocal1() + ",    " + aluno_corrente.getVoto().getLocal2() + ",    " + aluno_corrente.getVoto().getLocal3());

        if(aluno_corrente.qual_tipo().equals("Aluno_licenciado"))
            label_ponto_hot_evitar = new JLabel("   HotSpot: "+ ((Aluno_licenciado)aluno_corrente).getHotspot());
        else
            label_ponto_hot_evitar = new JLabel("   Local Evitar: " + ((Aluno_mestrado)aluno_corrente).getLocal_evitar());

        //pegar nos pontos de interesse do aluno e por aqui
        Ponto_interesse [] lista_ponto_interesse = aluno_corrente.getLista_pontos_interesse().toArray(new Ponto_interesse[0]);

        list_pontos_interesse = new JList(lista_ponto_interesse);
        list_pontos_interesse.setLayoutOrientation(JList.VERTICAL);
        list_pontos_interesse.setVisibleRowCount(5);
        listScroller = new JScrollPane(list_pontos_interesse);
        list_pontos_interesse.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        botao_voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botao_voltar.removeActionListener(this::actionPerformed);
                limpa_frame();
                pagina_inicial();
            }
        });
        botao_sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

        painel_utilizador.add(label_username);
        painel_utilizador.add(label_montante);

        painel_voto_viagem.add(label_locais);
        painel_voto_viagem.add(label_ponto_hot_evitar);

        painel_lista_ponto_interesse.add(list_pontos_interesse);

        painel_botao.add(botao_sair, BorderLayout.WEST);
        painel_botao.add(botao_voltar, BorderLayout.EAST);

        painel_principal.add(painel_utilizador);
        painel_principal.add(painel_voto_viagem);
        painel_principal.add(painel_lista_ponto_interesse);
        painel_principal.add(painel_botao);

        frame.setTitle("Ver Perfil");
        frame.add(painel_principal);
        frame.validate();
    }
    public static void escolher_viagem_com_museu(){
        int valor_default=240, valor_minimo=20, valor_maximo=500;
        JPanel painel_principal = new JPanel(new FlowLayout());
        JPanel painel_botao = new JPanel(new BorderLayout());
        JPanel painel_escolhe_viagem = new JPanel(new GridLayout(2, 1));
        JLabel label_utilizador = new JLabel("Aluno: " + aluno_corrente.getUsername());
        JLabel montante_maximo = new JLabel(valor_default + "€");
        JSlider barra_montante_maximo = new JSlider(JSlider.HORIZONTAL, valor_minimo, valor_maximo, valor_default);
        JList lista_viagens_possiveis;
        JScrollPane listScroller;
        JButton botao_voltar = new JButton("VOLTAR");
        JButton botao_sair = new JButton("SAIR");

        barra_montante_maximo.setMajorTickSpacing(100);
        barra_montante_maximo.setMinorTickSpacing(50);
        barra_montante_maximo.setPaintTicks(true);
        barra_montante_maximo.setPaintLabels(true);

        lista_viagens_possiveis = new JList();
        lista_viagens_possiveis.setLayoutOrientation(JList.VERTICAL);
        lista_viagens_possiveis.setVisibleRowCount(5);
        listScroller = new JScrollPane(lista_viagens_possiveis);
        lista_viagens_possiveis.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        barra_montante_maximo.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                /*ArrayList<Viagem> lista_viagens = viagens_possiveis_com_museu();
                Ponto_interesse [] lista_ponto_interesse = lista_viagens.toArray(new Ponto_interesse[0]);*/
                viagens_possiveis_com_museu(lista_viagens_possiveis, barra_montante_maximo.getValue(), montante_maximo);
            }
        });
        botao_voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpa_frame();
                botao_voltar.removeActionListener(this::actionPerformed);
                pagina_inicial();
            }
        });
        botao_sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });
        painel_botao.add(botao_voltar, BorderLayout.WEST);
        painel_botao.add(botao_sair, BorderLayout.EAST);

        painel_escolhe_viagem.add(montante_maximo);
        painel_escolhe_viagem.add(lista_viagens_possiveis);

        painel_principal.add(label_utilizador);
        painel_principal.add(barra_montante_maximo);
        painel_principal.add(painel_escolhe_viagem);
        painel_principal.add(painel_botao);

        frame.setTitle("Viagem Com Museu");
        frame.add(painel_principal);
        frame.validate();
    }
    public static void viagens_possiveis_com_museu(JList lista_viagens_possiveis, int montante_escolhido, JLabel montante){
        //falar com o setor sobre isto
        Local [] lista_ponto_interesse = lista_locais.toArray(new Local[0]);
        lista_viagens_possiveis.setListData(lista_ponto_interesse);
        lista_viagens_possiveis.validate();
        montante.setText(montante_escolhido + "€");
        montante.validate();
        lista_viagens_possiveis.validate();
    }
    public static void escolher_viagem_sem_museu(){

    }
    public static void ver_populares(){

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