/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.*;


/**
 *
 * @author ginjo
 */
public class Viagem implements Serializable {
    protected Local local1;
    protected Local local2;
    protected Local local3;
    protected Aluno aluno;
    protected List<Ponto_interesse> lista_ponto_interesse;
    protected Double custo_total;
    protected Double custo_transporte;
    protected Double custo_viagem;
    
    Viagem(){
        this.local1 = null;
        this.local2 = null;
        this.local3 = null;
        this.aluno = null;
        this.custo_total = 0.0;
        this.custo_viagem = 0.0;
        this.custo_transporte = 0.0;
        this.lista_ponto_interesse = new ArrayList<>();
    }
    
    Viagem(Local local1, Local local2, Local local3){
        this.local1 = local1;
        this.local2 = local2;
        this.local3 = local3;
        this.aluno = null;
        this.custo_total = 0.0;
        this.custo_viagem = 0.0;
        this.custo_transporte = 0.0;

  //      System.out.println(calcula_distancia_coordenadas(n));
    }
    public Local getLocal1() {
        return local1;
    }

    public void votar_locais(Local local1, Local local2, Local local3){
        setLocal1(local1);
        setLocal2(local2);
        setLocal3(local3);
    }

    public void custo_transporte(){
        double custo=0;
        double preco_por_listro_combostivel = 15.13;
        double litros_por_kilometros = 26;

        double distancia1 = calcula_distancia_coordenadas(local1, local2);
        double distancia2 = calcula_distancia_coordenadas(local2, local3);
        double distancia_total = distancia1+distancia2;
        double litros_combostivel = distancia_total*litros_por_kilometros/100.0;

        custo = litros_combostivel*preco_por_listro_combostivel;
        this.custo_transporte = Math.ceil(custo);
    }
    private double calcula_distancia_coordenadas(Local l1, Local l2){
        double raio_terra = 6.371;
        double radianos_latitude_1 = Math.toRadians(l1.getLatitude());
        double radianos_latitude_2 = Math.toRadians(l2.getLatitude());
        double diferenca_latitude = (l2.getLatitude()-l1.getLatitude());
        double diferenca_longitude = (l2.getLongitude()-l1.getLongitude());
        double haversine = Math.sin(diferenca_latitude/2)*Math.sin(diferenca_latitude/2) +
                Math.cos(radianos_latitude_1)*Math.cos(radianos_latitude_2)*Math.sin(diferenca_longitude/2
                *Math.sin(diferenca_longitude/2));
        double c = 2*Math.atan2(Math.sqrt(haversine), Math.sqrt(1-haversine));
        return raio_terra*c;
    }
    public boolean tem_museu(){
        for(Ponto_interesse p:lista_ponto_interesse){
            if(p.qual_tipo().equals("Museu"))
                return true;
        }
        return false;
    }

    public void atualiza_pontuacao(){
        int aux=0;
        List<Ponto_interesse> lista_pontos_interesse=new List<Ponto_interesse>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Ponto_interesse> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Ponto_interesse ponto_interesse) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Ponto_interesse> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Ponto_interesse> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Ponto_interesse get(int index) {
                return null;
            }

            @Override
            public Ponto_interesse set(int index, Ponto_interesse element) {
                return null;
            }

            @Override
            public void add(int index, Ponto_interesse element) {

            }

            @Override
            public Ponto_interesse remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Ponto_interesse> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Ponto_interesse> listIterator(int index) {
                return null;
            }

            @Override
            public List<Ponto_interesse> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        /*this.local1.votar_pontuacao_voto();
        this.local2.votar_pontuacao_voto();
        this.local3.votar_pontuacao_voto();*/
        System.out.println("Em ficheiro: depois de votar os locais");
        while(aux<3) {
            if(aux==0)
                lista_pontos_interesse = this.local1.getLista_pontos_interesse();
            else if(aux==1)
                lista_pontos_interesse = this.local2.getLista_pontos_interesse();
            else if(aux==2)
                lista_pontos_interesse = this.local3.getLista_pontos_interesse();

            for (Ponto_interesse item : lista_pontos_interesse){
                item.add_pontuacao_utilizador();
            }
            aux++;
        }
    }

    public void add_ponto_interesse(Ponto_interesse p){
        for(Ponto_interesse item:lista_ponto_interesse){
            if(item.nome_local.equals(p.nome_local)){
                return;
            }
        }
        lista_ponto_interesse.add(p);
        p.add_pontuacao_utilizador();
        atualiza_custo_total();
    }
    
    public void setLocal1(Local local1) {
        this.local1 = local1;
        this.local1.votar_pontuacao_voto();
        atualiza_custo_total();
    }

    public Local getLocal2() {
        return local2;
    }

    public void setLocal2(Local local2) {
        this.local2 = local2;
        this.local2.votar_pontuacao_voto();
        atualiza_custo_total();
    }

    public Local getLocal3() {
        return local3;
    }

    public void setLocal3(Local local3) {
        this.local3 = local3;
        this.local2.votar_pontuacao_voto();
        atualiza_custo_total();
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Double getCusto_total() {
        return custo_total;
    }

    public void setCusto_total(Double custo_total) {
        this.custo_total = custo_total;
    }

    public Double getCusto_viagem() {
        return custo_viagem;
    }

    public void setCusto_viagem(Double custo_viagem) {
        this.custo_viagem = custo_viagem;
    }

    public List<Ponto_interesse> getLista_ponto_interesse() {
        return lista_ponto_interesse;
    }

    public void atualiza_custo_total(){
        Double montante=0.0;
        for (Ponto_interesse item : lista_ponto_interesse) {
            montante += item.getCusto();
        }
        setCusto_viagem(montante);
       // this.custo_transporte = custo_transporte();
        setCusto_total(this.custo_viagem+this.custo_transporte);
    }

    public void setLista_ponto_interesse(List<Ponto_interesse> lista_ponto_interesse) {
        this.lista_ponto_interesse = lista_ponto_interesse;
        atualiza_custo_total();
    }

    public String imprime_lista_pontos_interesse(){
        String linha_final="";
        if(lista_ponto_interesse.isEmpty())
            return null;
        for(Ponto_interesse item:lista_ponto_interesse){
            linha_final += item.toString() + "\n";
        }
        return linha_final;
    }

    public int getCusto_transporte() {
       // setCusto_transporte();
        return (int)Math.ceil(this.custo_transporte);
    }

    /*public void setCusto_transporte() {
        this.custo_transporte = custo_transporte();
    }*/

    @Override
    public String toString() {
        return "Viagem: " + local1.toString() + ", " + local2.toString()+ ", " + local3.toString()+ ", " +
                ", custo_total=" + custo_total +
                ", custo_transporte=" + custo_transporte +
                ", custo_viagem=" + custo_viagem +
                '}';
    }
}
