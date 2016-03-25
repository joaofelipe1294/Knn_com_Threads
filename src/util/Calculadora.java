/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author joaolopes
 */
public class Calculadora {
    private String[] linhaTrain;
    private String[] linhaTest;

    public Calculadora(String[] linhaTrain, String[] linhaTest) {
        this.linhaTrain = linhaTrain;
        this.linhaTest = linhaTest;
    }
    
    public double calcDistancia(){
        double distancia = 0;
        for(int contador = 0; contador < linhaTest.length - 1 ; contador++ ){
            distancia += Math.pow(Double.parseDouble(linhaTrain[contador]) - Double.parseDouble(linhaTest[contador]) , 2);
        }
        return distancia;
    }
    
}
