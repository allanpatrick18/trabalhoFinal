package model;

import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author allan ### madureza # 1 verde # 2 madura # 3 podre ### fibras,
 * proteinas, lipideos # 1 pouca # 2 moderada # 3 alta
 */
public class Fruta {

    int madureza;
    int carboidratos;
    int fibras;
    int proteinas;
    int lipideos;

    public Fruta() {
        Random r = new Random();
        madureza = r.nextInt(3)+1;
        carboidratos = r.nextInt(3)+1;
        fibras = r.nextInt(3)+1;
        proteinas = r.nextInt(3)+1;
        lipideos = r.nextInt(3)+1;

    }

    public int comer(Fruta fruta) {
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

    public int getMadureza() {
        return madureza;
    }

    public void setMadureza(int madureza) {
        this.madureza = madureza;
    }

    public int getCarboidratos() {
        return carboidratos;
    }

    public void setCarboidratos(int carboidratos) {
        this.carboidratos = carboidratos;
    }

    public int getFibras() {
        return fibras;
    }

    public void setFibras(int fibras) {
        this.fibras = fibras;
    }

    public int getProteinas() {
        return proteinas;
    }

    public void setProteinas(int proteinas) {
        this.proteinas = proteinas;
    }

    public int getLipideos() {
        return lipideos;
    }

    public void setLipideos(int lipideos) {
        this.lipideos = lipideos;
    }

}
