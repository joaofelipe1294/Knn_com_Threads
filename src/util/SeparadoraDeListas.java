/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaofelipe
 */
public class SeparadoraDeListas {
    private int numeroThreads;
    //private List<List<String>> listas;
    //private List<String> listaTeste;
    private List<List<double[]>> listas;
    private List<double[]> listaTeste;
    
    public SeparadoraDeListas(int numeroThreads , List<double[]> listaTeste) {
        this.numeroThreads = numeroThreads;
        this.listaTeste = listaTeste;
        this.listas = new ArrayList<>();
    }

    /*public SeparadoraDeListas(int numeroThreads, List<String> listaTeste) {
        this.numeroThreads = numeroThreads;
        this.listaTeste = listaTeste;
        this.listas = new ArrayList<>();
    }*/
    
    

    public List<List<double[]>> quebra (){
        int contadorTeste = 0;
        for(int contadorListas = 0 ; contadorListas < numeroThreads ; contadorListas++){
            listas.add(new ArrayList<>());
            int indexLimite;
            if((contadorListas == (numeroThreads - 1) && (listaTeste.size() % numeroThreads) > 0)){
                indexLimite = listaTeste.size() - 1;
            }else{
                indexLimite = ((listaTeste.size() / numeroThreads) * (contadorListas + 1)) - 1;
            }
            while(contadorTeste <= indexLimite){
                listas.get(contadorListas).add(listaTeste.get(contadorTeste));
                contadorTeste++;
            }
        }   
        return listas;
    }
    
    /*public List<List<String>> quebra (){
        int contadorTeste = 0;
        for(int contadorListas = 0 ; contadorListas < numeroThreads ; contadorListas++){
            listas.add(new ArrayList<>());
            int indexLimite;
            if((contadorListas == (numeroThreads - 1) && (listaTeste.size() % numeroThreads) > 0)){
                indexLimite = listaTeste.size() - 1;
            }else{
                indexLimite = ((listaTeste.size() / numeroThreads) * (contadorListas + 1)) - 1;
            }
            while(contadorTeste <= indexLimite){
                listas.get(contadorListas).add(listaTeste.get(contadorTeste));
                contadorTeste++;
            }
        }   
        return listas;
    }*/
}
