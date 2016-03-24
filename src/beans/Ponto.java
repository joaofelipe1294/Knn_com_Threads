/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import helpers.CalculadoraHelper;

/**
 *
 * @author joaolopes
 */
public class Ponto implements Comparable<Ponto>{
    private double distancia;
    private int label;

    public Ponto(String[] linhaTrain , String[] linhaTeste){
        this.distancia = new CalculadoraHelper(linhaTrain, linhaTeste).calcDistancia();
        this.label = Integer.parseInt(linhaTrain[linhaTrain.length - 1]);
    }
    
    public double getDistancia() {
        return distancia;
    }

    public int getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "Ponto{" + "distancia=" + distancia + ", label=" + label + '}';
    }

    @Override
    public int compareTo(Ponto ponto) {
        if (ponto.getDistancia() > this.distancia) return 1;
        else if (ponto.getDistancia() < this.distancia) return -1;
        else return 0; // igual
    }
    
    
    
}
