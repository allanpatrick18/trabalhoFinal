/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import static controller.main.ambiente;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import model.Estado;
import model.Fruta;
import model.No;
import static service.Agente.repreAmbiente;
import static service.Ambiente.espaco2D;

/**
 *
 * @author allan
 */
public class Algoritmo {

    Agente agente;
    Estado estadoFinal;
    Estado estadoInicial;
    No no;
    List<No> fronteiras = new ArrayList<>();
    List<No> conhecidos = new ArrayList<>();
    List<Integer> acoes = new ArrayList<>();
    String valorobj;
    List<No> arvore = new ArrayList<>();
    List<Integer> custototal = new ArrayList<>();
    Fruta[][] frutas;
     boolean flag = true;
    public double[][] ambi;

    public Algoritmo(Agente agente, Estado estadoInicial, Estado estadoFinal, Fruta[][] f, double[][] ambi) {
        this.agente = agente;
        this.estadoFinal = estadoFinal;
        this.estadoInicial = estadoInicial;
        this.frutas = f;
        this.ambi = ambi;
    }

    public boolean algoritomo5() {

       
        if (flag) {
            flag = false;
            loadinicail();
        }
        Estado estadoAtual = new Estado();
        estadoAtual = estadoInicial;
        valorobj = estadoFinal.getValor();
        agente.setRepreAmbiente();
        conhecidos = new ArrayList<>();
        no = new No();
        no.setPai(null);
        no.setCusto(0);
        setAcoes(agente.acoesPosiveis(estadoAtual));
        no.setPosicao(estadoInicial);
        No fronteira = null;
        arvore.add(no);
        arvore.add(null);
        fronteira = no;
        fronteira.setEnergia(300);
        int visitados = 0;
        int esplorados = 0;
        loadFrutas();
        while (true) {
            for (int i = 0; i < acoes.size(); i++) {
                No filhos = new No();
                filhos.setPosicao(agente.funcaoSucessora(fronteira.getPosicao(), acoes.get(i)));
                double cost = agente.custo(acoes.get(i));
                double heuristica = getValor(filhos.getPosicao());
                filhos.setFn(cost + heuristica);
                filhos.setEnergia(fronteira.getEnergia());
                visitados++;
                if (filhos.getPosicao().getValor().equals("O")) {
                    System.out.println("Visitados " + visitados + " Esplorados: " + esplorados);
             //     gg();
//                    while(fronteira.getPai()!=null){
//                        agente.marcarCaminho(fronteira.getPosicao());
//                        fronteira.getPosicao().printEstado();
//                        fronteira = fronteira.getPai();   
//                    }
                    return true;
                } else {
                    filhos.setPai(fronteira);
                    filhos.setEsplorado(false);
                    conhecidos.add(filhos);

                }
            }
            if (fronteira.getEnergia() > 0) {
                  double tmp = fronteira.getFn();
                  Estado tempespaco = fronteira.getPosicao();
                if (conhecidos.size() > 0) {
                    esplorados++;
                    List<Integer> l = new ArrayList<>();
                    ordenarbyFn(conhecidos);
                     if (conhecidos.size() > 1) {
                        for (int i = 0; i + 1 < conhecidos.size(); i++) {
                            if (conhecidos.get(i).getFn() == conhecidos.get(i + 1).getFn()) {
                                l.add(i);
                            } else {
                                break;
                            }
                        }
                    }
                    if (l.size() > 0) {
                        Random r = new Random();
                        Integer n = r.nextInt(l.size() + 1);
                        fronteira = conhecidos.get(n);
                    } else {
                        fronteira = conhecidos.get(0);
                    }     
              //     fronteira = conhecidos.get(0);
                   conhecidos.clear();
                    if(tmp< fronteira.getFn()){
                        setValor(tempespaco, fronteira.getFn());
                    }
                    fronteira.setEsplorado(true);
                    setAcoes(agente.acoesPosiveis(fronteira.getPosicao()));
                    Fruta fru = retornaFruta(fronteira.getPosicao());
                    int perdas =  100 + 40;
                    int total = fronteira.getEnergia() - perdas + comer(fru);
                    fronteira.setEnergia(total);
                }
            } else {
                return false;
            }
        }
    }

    public void ordenar(List<No> nos) {
        nos.sort(new Comparator<No>() {
            public int compare(No no1, No no2) {
                return Double.compare(no1.getCusto(), no2.getCusto());
            }
        });

    }

    public void ordenarbyFn(List<No> nos) {
        nos.sort(new Comparator<No>() {
            public int compare(No no1, No no2) {
                return Double.compare(no1.getFn(), no2.getFn());
            }
        });

    }

    public double calculaheuristica(Estado atual) {
        int y = abs(atual.getLine() - estadoFinal.getLine());
        int x = abs(atual.getColunm() - estadoFinal.getColunm());
        double t = Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
//        t = Math.floor(t);
     //       t = Math.round(t);
        return t;

    }

    public No insereNo(No filho, No fronteira) {

        return new No();

    }

    public List<Integer> getAcoes() {
        return acoes;
    }

    public void setAcoes(List<Integer> acoes) {
        this.acoes = acoes;
    }

    public boolean gg() {
        setValor(estadoInicial, 2000);
        No fronteira = new No();
        fronteira.setPosicao(estadoInicial);
        setAcoes(agente.acoesPosiveis(fronteira.getPosicao()));
        conhecidos = new ArrayList<>();
        while (true) {
            for (int i = 0; i < acoes.size(); i++) {
                No filhos = new No();
                filhos.setPosicao(agente.funcaoSucessora(fronteira.getPosicao(), acoes.get(i)));
                filhos.setFn(getValor(filhos.getPosicao()));
                if (filhos.getPosicao().getValor().equals("O")) {
                    return true;
                } else {
                    filhos.setPai(fronteira);
                    conhecidos.add(filhos);
                }
            }
            if (conhecidos.size() > 0) {
                ordenarbyFn(conhecidos);
                fronteira = conhecidos.get(0);
                conhecidos = new ArrayList<>();
                agente.marcarCaminho(fronteira.getPosicao());
                setAcoes(agente.acoesPosiveis(fronteira.getPosicao()));
            }
        }
    }

    public Fruta retornaFruta(Estado estado) {

        return frutas[estado.getLine()][estado.getColunm()];

    }

    public Integer comer(Fruta fruta) {
        //// madureza
        // 1 verde
        // 2 madura
        // 3 podre
        //// fibras, proteinas, lipideos
        // 1 pouca
        // 2 moderada
        // 3 alta

        if (fruta.getLipideos() == 3 || fruta.getLipideos() == 2) {
            if (fruta.getCarboidratos() == 1 || fruta.getCarboidratos() == 2) {
                if (fruta.getMadureza() == 1) {
                    return 140;
                }
                if (fruta.getMadureza() == 2) {
                    return 200;
                }
                if (fruta.getMadureza() == 3) {
                    return 30;
                }

            }
            if (fruta.getCarboidratos() == 1) {
                if (fruta.getMadureza() == 1) {
                    return 30;
                }
                if (fruta.getMadureza() == 2) {
                    return 140;
                }

            }

        }
        if (fruta.getLipideos() == 1) {
            if (fruta.getCarboidratos() == 3 || fruta.getCarboidratos() == 2) {
                if (fruta.getMadureza() == 1) {
                    return 30;
                }
                if (fruta.getMadureza() == 2) {
                    return 200;
                }

            } else {
                if (fruta.getProteinas() == 1 && fruta.getFibras() == 3 && fruta.getMadureza() != 3) {
                    return 140;
                }

            }
        }
        return 30;

    }

    public void printFrutas() {

        System.out.println("\n Frutas valores ");

        try {
            Fruta[][] matrix = frutas;

            int rows = matrix.length;
            int columns = matrix[0].length;
            String str = "|  ";

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    str += comer(matrix[i][j]).toString() + "  ";
                }

                System.out.println(str + "|");
                str = "|  ";
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    frutas[i][j] = new Fruta();

                }
            }

        } catch (Exception e) {
            System.out.println("Matrix is empty!!");
        }

    }

    public void loadinicail() {

        int rows = ambi.length;
        int columns = ambi[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                 Estado s = new Estado(rows,columns);
                ambi[i][j] = calculaheuristica(s);

            }
        }

    }

    public void loadFrutas() {

        Fruta[][] matrix = frutas;

        int rows = matrix.length;
        int columns = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                frutas[i][j] = new Fruta();

            }
        }
    }
    public void printCore() {

        double[][] matrix = ambi;

            int rows = matrix.length;
            int columns = matrix[0].length;
            String str = "|  ";

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    str += matrix[i][j] + "  ";
                }

                System.out.println(str + "|");
                str = "|  ";
            }


    }
    
  

    public void setValor(Estado state, double valor) {
        ambi[state.getLine()][state.getColunm()] = valor;
    }

    public double getValor(Estado state) {
        return ambi[state.getLine()][state.getColunm()];
    }

}
