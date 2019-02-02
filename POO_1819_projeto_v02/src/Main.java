/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

/**
 *coracao da aplicacao, aqui criam-se as paginas da aplicacao e todas as funcionalidades necessarias para a mesma
 * @author ginjo
 * @author projeto de poo 2018/2019
 * @version 0.2
 * @since "%I%, %G%"
 */

public class Main {
    private Aluno aluno_corrente;
    private List<Local> lista_locais;
    private List<Aluno> lista_alunos;
    private JFrame frame;
    private Ficheiro ficheiro_txt;

    /**
     * Aqui sao guardados os locais e os alunos ja inscritos na plataforma bem como o
     * ficheiro de texto e a frame que será sempre a msesna e será usada
     * ao longo da aplicacao. É aqui que se assegura que o utilizador tem
     * a certezq que quer sair da aplicacao caso carregue em fechar a pagina
     * @param args
     */
    Main(String [] args){
        ficheiro_txt = new Ficheiro();
        lista_locais = ficheiro_txt.devolve_lista_local();
        lista_alunos = ficheiro_txt.devolve_lista_aluno();
        frame = new JFrame();
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit the program?", "Exit Program Message Box",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    System.exit(0);
                }else
                    frame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        menu_inicial();
    }

    /**
     * Construtor criado para os seus metodos e seus atributos nao seresm estaticos
     * @param args
     */
    public static void main(String [] args){
        Main oie = new Main(args);
    }

    /**
     * Pagina inicial do programa onde o cliente inicia a sessão ou
     * se resgista na plataforma
     */
    private void menu_inicial(){
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
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(3);
        JScrollPane listScroller = new JScrollPane(list);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //selecionar apenas um username

        frame.setTitle("Menu inicial");
        frame.setSize(300, 300);
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //usar o flowlayout

        caixa_texto_username.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                caixa_texto_username.setText(null);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(caixa_texto_username.getText()==null || caixa_texto_username.getText().equals(""))
                    caixa_texto_username.setText("username");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        radio_botao_licenciatura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radio_botao_mestrado.isSelected()) {
                    radio_botao_licenciatura.setSelected(true);
                    radio_botao_mestrado.setSelected(false);
                }
                else
                    radio_botao_licenciatura.setSelected(true);
            }
        });
        radio_botao_mestrado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radio_botao_licenciatura.isSelected()) {
                    radio_botao_licenciatura.setSelected(false);
                    radio_botao_mestrado.setSelected(true);
                }
                else
                    radio_botao_mestrado.setSelected(true);
            }
        });
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


    }

    /**
     * sempre chamado quando se passa de uma pagina para outra sem renovar
     * a frame mas sim removendo-lhe todos os componentes para adicionar outros
     */
    private void limpa_frame(){
        frame.getContentPane().removeAll();
        frame.repaint();
    }

    /**
     * verifica se o aluno ja escolheu ou nao os pontos de interese e locais que ser visitar
     * caso nao tenha escolhido e direcionado para la caso tena escolhido
     * vai para a pagina principal da aplicacao
     */
    private void verifica_voto_null(){
        System.out.println("Voto e:" + aluno_corrente.getVoto());
        if(aluno_corrente.getVoto() == null){
            pagina_votar_viagem();
        }else{
            pagina_inicial();
        }
    }

    /***
     * pagina onde o utilziador escolhe 3 locais que quer visitar, é também assegurado
     * que o utulizador tem a certeza que quer sair da pagina caso carregue em voltar
     * ou que os votos não estão a null caso prossiga para o passo seguinte
     */
    private void pagina_votar_viagem(){
        Local [] lista_locais_list = lista_locais.toArray(new Local[0]);

        JLabel label_titulo = new JLabel("VOTAR VIAGEM");
        JLabel label_aluno = new JLabel(aluno_corrente.qual_tipo()+ ": " + aluno_corrente.getUsername());
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
                    for(Local l:lista_locais){
                        if(l.equals(aluno_corrente.getVoto().getLocal1()))
                            l.votar_pontuacao_voto();
                        else if(l.equals(aluno_corrente.getVoto().getLocal2()))
                            l.votar_pontuacao_voto();
                        else if(l.equals(aluno_corrente.getVoto().getLocal3()))
                            l.votar_pontuacao_voto();
                    }
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
        System.out.println(frame.getSize().height + " : " + frame.getSize().width);
        frame.validate();
    }

    /**
     * o utilizador e direcionado para esta pagina depois de escolher os
     * locais preferidos. Aqui caso o aluno seja do mestrado este escolhe um
     * lugar que não quer visitar e o local perde pontuacao. Caso o aluno seja
     * do mestrado pode escolher um hotspot. Todos os tipos de utilizadores
     * podem escolher pontos de interesse a sua escolha ou nao. Aqui também se
     * escolhe o montante que quer gastar numa viagem
     */
    private void votar_pontos_hot_not(){
        int valor_minimo= aluno_corrente.getVoto().getCusto_transporte(), valor_maximo=500, valor_default=(valor_maximo+valor_minimo)/2;
        JLabel label_titulo = new JLabel("Votar pontos hot");
        JLabel label_aluno = new JLabel(aluno_corrente.qual_tipo()+ ": " + aluno_corrente.getUsername());
        JLabel label_hot_not, montante_pontos_interesse_escolhidos;
        JLabel montante_maximo = new JLabel(valor_default + "€");
        montante_pontos_interesse_escolhidos = new JLabel("Montante acumulado: " + aluno_corrente.getVoto().getCusto_transporte() + "€");
        JLabel label_pontos_interesse = new JLabel("Selecione Pontos de Interesse");
        JComboBox comboBox_hot_not;
        JPanel painel_principal = new JPanel();
        JPanel painel_hot_not = new JPanel();
        JPanel painel_pontos_interesse = new JPanel();
        JPanel painel_botao = new JPanel();
        JSlider barra_montante_maximo = new JSlider(JSlider.HORIZONTAL, valor_minimo, valor_maximo, valor_default);
        JList list_ponto_interesse_para_inserir;
        JButton botao_voltar;
        JButton botao_registar;

        barra_montante_maximo.setMajorTickSpacing(100);
        barra_montante_maximo.setMinorTickSpacing(50);
        barra_montante_maximo.setPaintTicks(true);
        barra_montante_maximo.setPaintLabels(true);

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
            //ArrayList<Local> lista_locais_mestrado = org.apache.commons.lang3.SerializationUtils.clone((ArrayList<Local>)lista_locais);
            ArrayList<Local> lista_locais_mestrado = new ArrayList<Local>();
            String aux;
            for(int i=0; i<lista_locais.size() ; i++) {
                aux = lista_locais.get(i).nome_cidade;
                if (!aux.equals(aluno_corrente.getVoto().getLocal1().getNome_cidade()) && !aux.equals(aluno_corrente.getVoto().getLocal2().getNome_cidade()) && !aux.equals(aluno_corrente.getVoto().getLocal3().getNome_cidade())) {
                    lista_locais_mestrado.add(lista_locais.get(i));
                }
            }
            Local [] lista_locais_list = lista_locais_mestrado.toArray(new Local[0]);
            comboBox_hot_not = new JComboBox(lista_locais_list);
        }

        list_ponto_interesse_para_inserir = new JList(lista_ponto_interesse);
        list_ponto_interesse_para_inserir.setLayoutOrientation(JList.VERTICAL);
        list_ponto_interesse_para_inserir.setVisibleRowCount(5);
        JScrollPane listScroller = new JScrollPane(list_ponto_interesse_para_inserir);
        list_ponto_interesse_para_inserir.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        //votar nos locais escolhidos pois a viagem final sera com base nos locais definidos aqui
        if(aluno_corrente.qual_tipo().equals("Aluno_licenciado") && ((Aluno_licenciado)aluno_corrente).getHotspot()!=null) {
            comboBox_hot_not.setSelectedItem(((Aluno_licenciado) aluno_corrente).getHotspot());
            atualiza_jlist_votar_pontos_hot_not(list_ponto_interesse_para_inserir, lista_locais_pontos, ((Aluno_licenciado) aluno_corrente).getHotspot());
        }else if(aluno_corrente.qual_tipo().equals("Aluno_mestrado") && ((Aluno_mestrado)aluno_corrente).getLocal_evitar()!=null) {
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
                    atualiza_jlist_votar_pontos_hot_not(list_ponto_interesse_para_inserir, lista_locais_pontos, (Ponto_interesse)comboBox_hot_not.getSelectedItem());
                    aluno_corrente.votar_ponto_interesse_local((Ponto_interesse)comboBox_hot_not.getSelectedItem(), null);
                    montante_pontos_interesse_escolhidos.setText("Montante acumulado: " + String.valueOf(montante_acumulado(list_ponto_interesse_para_inserir.getSelectedValuesList(), comboBox_hot_not)
) + "€");
                    montante_pontos_interesse_escolhidos.validate();
                }else{
                    System.out.println("em mestrado");
                    aluno_corrente.votar_ponto_interesse_local(null, (Local)comboBox_hot_not.getSelectedItem());
                }
            }
        });
        list_ponto_interesse_para_inserir.setSelectionModel(new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                if(super.isSelectedIndex(index0)) {
                    super.removeSelectionInterval(index0, index1);
                }
                else {
                    super.addSelectionInterval(index0, index1);
                }
            }
        });
        list_ponto_interesse_para_inserir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    list_ponto_interesse_para_inserir.clearSelection();
                }
            }
        });
        list_ponto_interesse_para_inserir.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                double montante;
                montante = montante_acumulado(list_ponto_interesse_para_inserir.getSelectedValuesList(), comboBox_hot_not);
                montante_pontos_interesse_escolhidos.setText("Montante acumulado: " + String.valueOf( + montante) + "€");
                montante_pontos_interesse_escolhidos.validate();
                System.out.println("aqui");
            }
        });
        botao_registar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double montante;
                montante = montante_acumulado(list_ponto_interesse_para_inserir.getSelectedValuesList(), comboBox_hot_not);
                if(montante>=barra_montante_maximo.getValue()){
                    JOptionPane.showMessageDialog(null, "Montante escolhido superior ao montante acumulado", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                }else if(comboBox_hot_not.getSelectedItem()==null){
                    JOptionPane.showMessageDialog(null, "Ponto nao escolhido", "Mensagem", JOptionPane.PLAIN_MESSAGE);
                }else{
                    botao_registar.removeActionListener(this::actionPerformed);
                    //criar a funcao de aceitar os pontos de interesse da lista e por no aluno_corrente depois atualizar o ficheiro
                    try{
                        aluno_corrente.getVoto().setLista_ponto_interesse((ArrayList<Ponto_interesse>) list_ponto_interesse_para_inserir.getSelectedValuesList());
                    }catch (ClassCastException c){
                    }
                    if(aluno_corrente.getVoto().getLista_ponto_interesse() != null) {
                        for (Ponto_interesse item : aluno_corrente.getVoto().getLista_ponto_interesse())
                            ((Ponto_interesse) item).add_pontuacao_utilizador();
                    }
                    aluno_corrente.setMontante_maximo((double)barra_montante_maximo.getValue()+aluno_corrente.getVoto().getCusto_transporte());
                    ficheiro_txt.atualiza_corrente_viagem(aluno_corrente);
                    limpa_frame();
                    pagina_inicial();
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
        barra_montante_maximo.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                montante_maximo.setText(barra_montante_maximo.getValue() + "€");
                montante_maximo.validate();
                aluno_corrente.setMontante_maximo((double)barra_montante_maximo.getValue());
            }
        });

        painel_hot_not.setLayout(new GridLayout(2, 1));
        painel_hot_not.add(label_hot_not);
        painel_hot_not.add(comboBox_hot_not);

        painel_pontos_interesse.setLayout(new GridLayout(1, 2));
        painel_pontos_interesse.add(label_pontos_interesse);
        painel_pontos_interesse.add(montante_pontos_interesse_escolhidos);

        painel_botao.setLayout(new BorderLayout());
        painel_botao.add(botao_voltar, BorderLayout.WEST);
        painel_botao.add(botao_registar, BorderLayout.EAST);

        painel_principal.setLayout(new FlowLayout());
        painel_principal.add(label_titulo);
        painel_principal.add(label_aluno);
        painel_principal.add(montante_maximo);
        painel_principal.add(barra_montante_maximo);
        painel_principal.add(painel_hot_not);
        painel_principal.add(painel_pontos_interesse);
        painel_principal.add(listScroller);
        painel_principal.add(painel_botao);


        frame.setTitle("Votar Pontos/Loca Hot/Evitar");
        frame.add(painel_principal);
        frame.setSize(new Dimension(500,300));
        System.out.println(frame.getSize().height + " : " + frame.getSize().width);
        frame.validate();
    }

    /**
     * Aqui alcula-se para todos os pontos de interesse escolhidos qual o montante
     * acumulado até ao momento,
     * @param lista_pontos_interesse, jlist que contem os pontos de itneresse escilhidos
     * @param combo, comobox de onde o aluno escolheu
     * @return o montante acumulado com pontos de itneresse e transporte
     */
    private double montante_acumulado(List lista_pontos_interesse, JComboBox combo){
        double montante=0.0;
        Ponto_interesse ponto;
        for(Object item: lista_pontos_interesse){
            ponto = (Ponto_interesse)item;
            montante += ponto.getCusto();
        }
        if(aluno_corrente.qual_tipo().equals("Aluno_licenciado") && combo.getSelectedItem()!=null){
            return montante+aluno_corrente.getVoto().getCusto_transporte() + ((Ponto_interesse)combo.getSelectedItem()).getCusto();
        }
        return montante+aluno_corrente.getVoto().getCusto_transporte();
    }

    /**
     * depois de escolher os locais e facultativamente os pontos de interesse
     * o utilizador é direcionado para aqui onde o utilizador pode fazer alterações
     * da sua escolha, ver a sua escolha ou sair da aplicacao ou da sessão
     */
    private void pagina_inicial(){
        System.out.println("em pagina inicial");
        JLabel label_titulo = new JLabel("PAGINA INICIAL");
        JLabel label_aluno = new JLabel(aluno_corrente.qual_tipo()+ ": " + aluno_corrente.getUsername());
        JButton botao_escolher_viagem_sem_museu,botao_sair, botao_escolher_viagem_com_museu, botao_ver_populares, botao_personalizar_viagem, botao_ver_perfil;
        JPanel painel_principal = new JPanel(new FlowLayout());
        JPanel painel_botao = new JPanel(new BorderLayout());

        botao_escolher_viagem_com_museu = new JButton("Escolher viagem com museu");
        botao_personalizar_viagem = new JButton("Personalizar viagem");
        botao_ver_populares = new JButton("Ver Populares");
        botao_ver_perfil = new JButton("Perfil");
        botao_sair = new JButton("SAIR");

        botao_sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*frame.dispose();
                System.exit(0);*/
                limpa_frame();
                menu_inicial();
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
                if(aluno_corrente.getViagem_escolhida_com_museu()!=null){
                    int confirmed = JOptionPane.showConfirmDialog(null,
                            "Ja tem a viagem com museu escolhida, caso prossiga sera anulada. Quer prosseguir?", "Exit Program Message Box",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmed == JOptionPane.YES_OPTION) {
                        limpa_frame();
                        aluno_corrente.setViagem_escolhida_com_museu(null);
                        botao_personalizar_viagem.removeActionListener(this::actionPerformed);
                        pagina_votar_viagem();
                    }
                }else{
                    limpa_frame();
                    botao_personalizar_viagem.removeActionListener(this::actionPerformed);
                    pagina_votar_viagem();
                }
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
        botao_ver_populares.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpa_frame();
                botao_ver_populares.removeActionListener(this::actionPerformed);
                ver_populares();
            }
        });

        painel_botao.add(botao_sair, BorderLayout.WEST);
        painel_botao.add(botao_ver_perfil, BorderLayout.EAST);

        painel_principal.add(label_titulo);
        painel_principal.add(label_aluno);
        painel_principal.add(botao_escolher_viagem_com_museu);
        painel_principal.add(botao_personalizar_viagem);
        painel_principal.add(botao_ver_populares);
        painel_principal.add(painel_botao);

        frame.setTitle("Pagina Inicial");
        frame.add(painel_principal);
        frame.setSize(new Dimension(250,300));
        frame.validate();
    }
    /**
     *
     * @param username username do aluno para poder fazer o registo
     * @param licenciado true caso seja licenciado e false caso seja mestrado
     * @return retorna o aluno corrente caso o registo se tenha feito com sucesso
     */
    private Aluno registar_aluno(String username, Boolean licenciado){
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

    /**
     * e apresentadoa o aluno as suas escolhas
     */
    private void ver_perfil(){
        JPanel painel_utilizador = new JPanel();
        JPanel painel_voto_viagem = new JPanel();
        JPanel painel_lista_ponto_interesse = new JPanel();
        JPanel painel_botao = new JPanel();
        JPanel painel_principal = new JPanel();

        Ponto_interesse[] lista_ponto_interesse = new Ponto_interesse[0];

        JLabel label_username, label_montante, label_locais, label_ponto_hot_evitar, label_viagem_com_museu;
        JList list_pontos_interesse;
        JButton botao_voltar = new JButton("VOLTAR");
        JButton botao_sair = new JButton("SAIR");
        JScrollPane listScroller;
        label_username = new JLabel(aluno_corrente.qual_tipo()+ ": " + aluno_corrente.getUsername());
        label_montante = new JLabel("Montante Maximo: " + aluno_corrente.getMontante_maximo());
        label_locais = new JLabel("Viagem: " + aluno_corrente.getVoto().getLocal1() + ",    " + aluno_corrente.getVoto().getLocal2() + ",    " + aluno_corrente.getVoto().getLocal3());
        if(aluno_corrente.getViagem_escolhida_com_museu()==null)
            label_viagem_com_museu = new JLabel("Ainda nao tem uma viagem com museu escolhida!");
        else
            label_viagem_com_museu = new JLabel("Com museu: " + aluno_corrente.getViagem_escolhida_com_museu());

        if(aluno_corrente.qual_tipo().equals("Aluno_licenciado"))
            label_ponto_hot_evitar = new JLabel("   HotSpot: "+ ((Aluno_licenciado)aluno_corrente).getHotspot());
        else
            label_ponto_hot_evitar = new JLabel("   Local Evitar: " + ((Aluno_mestrado)aluno_corrente).getLocal_evitar());

        //pegar nos pontos de interesse do aluno e por aqui
        if(aluno_corrente.getVoto().getLista_ponto_interesse() != null) {
            lista_ponto_interesse = aluno_corrente.getVoto().getLista_ponto_interesse().toArray(new Ponto_interesse[0]);
        }
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
                /*frame.dispose();
                System.exit(0);*/
                limpa_frame();
                menu_inicial();
            }
        });
        painel_utilizador.setLayout(new GridLayout(1,2));
        painel_utilizador.add(label_username);
        painel_utilizador.add(label_montante);

        painel_voto_viagem.setLayout(new GridLayout(3, 1));
        painel_voto_viagem.add(label_locais);
        painel_voto_viagem.add(label_ponto_hot_evitar);
        painel_voto_viagem.add(label_viagem_com_museu);

        /*painel_lista_ponto_interesse.setLayout(new GridLayout(1,1));
        painel_lista_ponto_interesse.add(list_pontos_interesse);*/

        painel_botao.setLayout(new BorderLayout());
        painel_botao.add(botao_sair, BorderLayout.WEST);
        painel_botao.add(botao_voltar, BorderLayout.EAST);

        painel_principal.setLayout(new FlowLayout());
        painel_principal.add(painel_utilizador);
        painel_principal.add(painel_voto_viagem);
        painel_principal.add(list_pontos_interesse);
        painel_principal.add(painel_botao);

        frame.setTitle("Ver Perfil");
        frame.add(painel_principal);
        System.out.println(frame.getSize().height + " : " + frame.getSize().width);
        frame.setSize(new Dimension(500,300));
        frame.validate();
    }

    /**
     * e dado ao aluno um lista de possiveis viagens que pode fazer
     * tendo por base a sua escolha de locais, pontos de interesse
     * e o montante
     */
    private void escolher_viagem_com_museu(){
        JPanel painel_principal = new JPanel(new FlowLayout());
        JPanel painel_botao = new JPanel(new BorderLayout());
        JPanel painel_escolhe_viagem = new JPanel(new GridLayout(2, 1));
        JLabel label_utilizador = new JLabel("Aluno: " + aluno_corrente.getUsername() + ", montante maximo:" + aluno_corrente.getMontante_maximo());
        JList lista_viagens_possiveis;
        JScrollPane listScroller_h;
        JTextArea texto_pontos_interesse=new JTextArea();

        //texto_pontos_interesse.setPreferredSize(new Dimension(250,80));
        JButton botao_voltar = new JButton("VOLTAR");
        JButton botao_sair = new JButton("SAIR");
        JButton botao_selecionar = new JButton("SELECIONAR");
        //ficheiro_txt.gerar_viagens_com_museu(aluno_corrente, aluno_corrente.getMontante_maximo());
        Aluno a = aluno_corrente;
        Viagem [] lista_viagens_com_museu = gerar_viagens_com_museu(a, a.getMontante_maximo()).toArray(new Viagem[0]);

        lista_viagens_possiveis = new JList(lista_viagens_com_museu);
        lista_viagens_possiveis.setLayoutOrientation(JList.VERTICAL);
        lista_viagens_possiveis.setVisibleRowCount(5);
        listScroller_h = new JScrollPane(lista_viagens_possiveis);
        lista_viagens_possiveis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista_viagens_possiveis.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                texto_pontos_interesse.setText(((Viagem)lista_viagens_possiveis.getSelectedValue()).imprime_lista_pontos_interesse());
                texto_pontos_interesse.setVisible(true);
                texto_pontos_interesse.validate();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
                texto_pontos_interesse.setText(null);
                texto_pontos_interesse.setVisible(false);
                texto_pontos_interesse.validate();
            }
        });
        lista_viagens_possiveis.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                texto_pontos_interesse.setText(((Viagem)lista_viagens_possiveis.getSelectedValue()).imprime_lista_pontos_interesse());
                texto_pontos_interesse.validate();
            }
        });
        botao_selecionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Tens a certeza?", "Janela de Confirmacao",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    aluno_corrente.votar_viagem_com_museu((Viagem)lista_viagens_possiveis.getSelectedValue());
                    ficheiro_txt.atualiza_corrente_viagem(aluno_corrente);
                    limpa_frame();
                    botao_selecionar.removeActionListener(this::actionPerformed);
                    pagina_inicial();
                }else
                    frame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

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
                /*frame.dispose();
                System.exit(0);*/
                limpa_frame();
                menu_inicial();
            }
        });
        painel_botao.add(botao_voltar, BorderLayout.WEST);
        painel_botao.add(botao_selecionar, BorderLayout.CENTER);
        painel_botao.add(botao_sair, BorderLayout.EAST);

        painel_escolhe_viagem.add(listScroller_h);
        painel_escolhe_viagem.add(texto_pontos_interesse);

        painel_principal.add(label_utilizador);
        painel_principal.add(painel_escolhe_viagem);
        painel_principal.add(painel_botao);
        texto_pontos_interesse.setVisible(false);
        frame.setTitle("Viagem Com Museu");
        frame.add(painel_principal);
        frame.setSize(new Dimension(700,300));
        frame.validate();
    }
    /**
     *
     * @param aluno_corrente, aluno que fez login com sucesso para verificar as suas opcoes
     * @param montante_maximo, montante maximo escolhido pelo aluno no registo
     * @return, e retornado
     */
    private ArrayList<Viagem> gerar_viagens_com_museu(Aluno aluno_corrente, double montante_maximo){
        List<Ponto_interesse> lista_pontos_totais = ficheiro_txt.devolve_lista_pontos_interesse();
        ArrayList<Viagem> lista_viagens_com_museu = new ArrayList<>();
        Double diferenca;
        diferenca = montante_maximo-aluno_corrente.getVoto().getCusto_viagem();
        System.out.println("No ficheiro: " + aluno_corrente.getVoto().imprime_lista_pontos_interesse() + " : " + diferenca);
        int i=0;
        while(i<lista_pontos_totais.size()){
            ArrayList<Viagem> lista_aux_viagens = new ArrayList<>();
            Viagem v = org.apache.commons.lang3.SerializationUtils.clone(aluno_corrente.getVoto());
            todas_as_combinacoes_v02(lista_aux_viagens, i, lista_pontos_totais, v, montante_maximo);
            /*for(Viagem item:lista_aux_viagens){
                if(lista_viagens_com_museu.contains(item))
                    lista_viagens_com_museu.remove(item);
            }*/
            lista_viagens_com_museu.addAll(org.apache.commons.lang3.SerializationUtils.clone(lista_aux_viagens));
            i++;
        }
        /*for(int j=0 ; j<lista_viagens_com_museu.size() ; j++){
            Viagem viagemAux = lista_viagens_com_museu.get(j);
            for(int k= j ; k<lista_viagens_com_museu.size() ; k++){
                Viagem viagemAux2 = lista_viagens_com_museu.get(k);
                if(viagemAux.getLista_ponto_interesse().equals(viagemAux2.getLista_ponto_interesse()))
                    lista_viagens_com_museu.remove(viagemAux2);
                if(viagemAux.getLista_ponto_interesse().size() == viagemAux2.getLista_ponto_interesse().size()){
                    int m = 0 ;
                    for(int l=0 ; l<viagemAux.getLista_ponto_interesse().size() ; l++){
                        if(viagemAux.getLista_ponto_interesse().get(l).getNome_local().equals(viagemAux.getLista_ponto_interesse().get(l).getNome_local()))
                            m++;
                    }
                    if(m == viagemAux.getLista_ponto_interesse().size())
                        lista_viagens_com_museu.remove(viagemAux2);
                }
            }
        }*/
        return lista_viagens_com_museu;
    }

    /**
     * todas as combinações que tiverem um museu e as suas preferencias
     * @param lista_aux_viagens, lista auxiliar que vai guardar as viagens geradas a cada iteracao
     * @param iterator, iterador que vai ate ao fim da lista de pontos de itneresse
     * @param lista_pontos, lista de pontos de interesse
     * @param v, viagem com as suas preferencias
     * @param montante, montante escolhido pelo utilizador para gastar numa viagem
     */
    private void todas_as_combinacoes_v02(ArrayList<Viagem> lista_aux_viagens, int iterator, List<Ponto_interesse> lista_pontos, Viagem v, double montante){
        if(iterator>=lista_pontos.size()){
            for(Viagem item:lista_aux_viagens){
                for(Ponto_interesse item_p:item.getLista_ponto_interesse()){
                    System.out.print(item_p.getNome_local() + "  ");
                }
            }
        }else{
            Viagem v1 = org.apache.commons.lang3.SerializationUtils.clone(v);
            v1.add_ponto_interesse(lista_pontos.get(iterator));
            //testar igualdades
           /* for(Viagem item:lista_aux_viagens){
                for(Ponto_interesse item_p1:item.getLista_ponto_interesse()){
                    for(Ponto_interesse item_p2:v1.getLista_ponto_interesse()){
                        if(item_p1.getNome_local().equals(item_p2.getNome_local()) && item_p1.getHorario().equals(item_p2.getHorario()))
                            return;
                    }
                }
            }*/
            //testar museu
            if(v1.getCusto_viagem()<=montante && v1.tem_museu())
                lista_aux_viagens.add(v1);
            todas_as_combinacoes_v02(lista_aux_viagens, iterator+1, lista_pontos, v1, montante);
        }
    }
    /**
     *
     * @param jlist, referencia da jlist do metodo votar_pontos_hot_not para lhe alterar os pontos
     *               de interesse contidos quando o licenciado escolhe um hospot
     * @param lista_pontos_interesse, lista de pontos de interessse total para lhe tirar o
     *                                ponto de interesse
     */
    private void atualiza_jlist_votar_pontos_hot_not(JList jlist, ArrayList<Ponto_interesse> lista_pontos_interesse, Ponto_interesse ponto_escolhido){
        ArrayList<Ponto_interesse> lista_pontos_interesse_deep_copy = org.apache.commons.lang3.SerializationUtils.clone(lista_pontos_interesse);

        for(int i=0 ; i<lista_pontos_interesse_deep_copy.size() ; i++){
            if(lista_pontos_interesse_deep_copy.get(i).getNome_local().equals(ponto_escolhido.getNome_local()) &&
                    lista_pontos_interesse_deep_copy.get(i).getHorario().equals(ponto_escolhido.getHorario()) &&
                    lista_pontos_interesse_deep_copy.get(i).getNome_local_ponto_interesse().equals(ponto_escolhido.getNome_local_ponto_interesse())){
                lista_pontos_interesse_deep_copy.remove(lista_pontos_interesse_deep_copy.get(i));
            }
        }
        Ponto_interesse [] lista_ponto_interesse = lista_pontos_interesse_deep_copy.toArray(new Ponto_interesse[0]);
        jlist.setListData(lista_ponto_interesse);
        jlist.validate();

    }

    /**
     * caso o aluno queira ver locais mais votados ou pontos de interesse mais votados
     * pode-se selecionar quais dos dois ver e se quer ver por ordem crescente ou
     * descrescente, por defeito é por ordem crescente
     */
    private void ver_populares(){
        JPanel paine_principal = new JPanel();
        JPanel painel_radio_local_ponto = new JPanel();
        JPanel painel_botao = new JPanel();
        //JPanel painel_ordenar = new JPanel();

        JRadioButton radio_botao_local = new JRadioButton("Local");
        JRadioButton radio_botao_ponto_interesse = new JRadioButton("Ponto de Interesse");
        JRadioButton radio_ordena_crescente = new JRadioButton("Crescente");
        JRadioButton radio_ordena_decrescente = new JRadioButton("Decrescente");
        JTextArea jtext_area_locais_pontos = new JTextArea();
        JButton botao_voltar, botao_sair;
        JLabel label_utilizador = new JLabel(aluno_corrente.qual_tipo() + " : " + aluno_corrente.getUsername());
        ficheiro_txt.atualiza_populares();
        botao_sair = new JButton("SAIR");
        botao_voltar = new JButton("VOLTAR");
        jtext_area_locais_pontos.setText("Escolha Local ou Ponto de Interesse");
        /*Ponto_interesse [] lista_ponto_interesse_finais = interesse_popular().toArray(new Ponto_interesse[0]);
        Local [] lista_locais_finais = devolve_local_popular().toArray(new Local[0]);
       */
        radio_ordena_crescente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String linha;
                //casos para botao ordena
                if(radio_ordena_decrescente.isSelected()){
                    radio_ordena_decrescente.setSelected(false);
                    radio_ordena_crescente.setSelected(true);
                }else
                    radio_ordena_crescente.setSelected(true);

                //casos para botao de local ou ponto de interesse
                if(radio_botao_ponto_interesse.isSelected()) {
                    linha = ficheiro_txt.getPontos_populares_crescente();
                }else{
                    linha = ficheiro_txt.getLocais_populares_crescente();
                }
                //String linha = ficheiro_txt.devolve_populares(false, true);
                jtext_area_locais_pontos.setText(linha);
                jtext_area_locais_pontos.validate();
            }

        });
        radio_ordena_decrescente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String linha;
                //casos para botao para ordenar
                if(radio_ordena_crescente.isSelected()){
                    radio_ordena_crescente.setSelected(false);
                    radio_ordena_decrescente.setSelected(true);
                }else
                    radio_ordena_decrescente.setSelected(true);

                //casos para botao de local ou ponto de interesse
                if(radio_botao_local.isSelected()) {
                    linha = ficheiro_txt.getLocais_populares_descrescente();
                }else{
                    linha = ficheiro_txt.getPontos_populares_descrescente();
                }
                //String linha = ficheiro_txt.devolve_populares(false, true);
                jtext_area_locais_pontos.setText(linha);
                jtext_area_locais_pontos.validate();
            }
        });
        radio_botao_local.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String linha;
                if(radio_botao_ponto_interesse.isSelected()) {
                    radio_botao_local.setSelected(true);
                    radio_botao_ponto_interesse.setSelected(false);
                }
                else
                    radio_botao_local.setSelected(true);
                System.out.println("depois de resolver radio");
                if(radio_ordena_crescente.isSelected()) {
                    linha = ficheiro_txt.getLocais_populares_crescente();
                }else{
                    linha = ficheiro_txt.getLocais_populares_descrescente();
                }
                jtext_area_locais_pontos.setText(linha);
                jtext_area_locais_pontos.validate();
            }
        });
        radio_botao_ponto_interesse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String linha;
                if(radio_botao_local.isSelected()) {
                    radio_botao_ponto_interesse.setSelected(true);
                    radio_botao_local.setSelected(false);
                }
                else
                    radio_botao_ponto_interesse.setSelected(true);
                if(radio_ordena_crescente.isSelected()) {
                    linha = ficheiro_txt.getPontos_populares_crescente();
                }else{
                    linha = ficheiro_txt.getPontos_populares_descrescente();
                }
                //String linha = ficheiro_txt.devolve_populares(false, true);
                System.out.println(linha);
                jtext_area_locais_pontos.setText(linha);
                jtext_area_locais_pontos.validate();
            }
        });
        botao_sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpa_frame();
                menu_inicial();
            }
        });
        botao_voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpa_frame();
                pagina_inicial();
            }
        });

        painel_radio_local_ponto.setLayout(new GridLayout(2,2));
        painel_radio_local_ponto.add(radio_botao_local);
        painel_radio_local_ponto.add(radio_botao_ponto_interesse);
        painel_radio_local_ponto.add(radio_ordena_crescente);
        painel_radio_local_ponto.add(radio_ordena_decrescente);

        painel_botao.setLayout(new BorderLayout());
        painel_botao.add(botao_voltar, BorderLayout.WEST);
        painel_botao.add(botao_sair, BorderLayout.EAST);

        paine_principal.setLayout(new FlowLayout());
        paine_principal.add(label_utilizador);
        paine_principal.add(painel_radio_local_ponto);
        paine_principal.add(jtext_area_locais_pontos);
        paine_principal.add(painel_botao);

        frame.setTitle("Ver populares");
        frame.add(paine_principal);
        frame.setSize(new Dimension(500,300));
        frame.validate();
    }
}