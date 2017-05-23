/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author allan
 */
public class No {
    public No pai;
    private Estado posicao;     
    private boolean esplorado;  
    private List<No> adjacentes;
    public  int custo =0;
    public  int energia;
    public  List<Fruta> frutas= new ArrayList<>();
    
    

    public No() {
        
        adjacentes = new ArrayList<>();
    }

    public No(boolean esplorado, List<No> adjacentes) {
        this.esplorado = esplorado;
        this.adjacentes = adjacentes;
    }

    public Estado getPosicao() {
        return posicao;
    }

    public void setPosicao(Estado posicao) {
        this.posicao = posicao;
    }

    public boolean isEsplorado() {
        return esplorado;
    }

    public void setEsplorado(boolean esplorado) {
        this.esplorado = esplorado;
    }

    public  List<No> getAdjacentes() {
        return adjacentes;
    }

    public  void setAdjacentes(List<No> adjacentes) {
        adjacentes = adjacentes;
    }
    
    public No addFilhos(No pai,List<No> nos){
        this.adjacentes = nos;
        return this.adjacentes.get(0);
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public double getCusto() {
        return custo;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public List<Fruta> getFrutas() {
        return frutas;
    }

    public void setFrutas(List<Fruta> frutas) {
        this.frutas = frutas;
    }

  
    
   
    
    
    
}
