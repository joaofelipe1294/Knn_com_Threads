/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import beans.Ponto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joaolopes
 */
public class ProcessaPontosThread implements Runnable{
    //private List<String> listaTrain;
    //private List<String> listaTest;
    private List<double[]> listaTrain;
    private List<double[]> listaTest;
    private int kMaisProximos;
    
    public ProcessaPontosThread(List<double[]> listaTrain, List<double[]> listaTest , int kMaisProximos) {
        this.listaTrain = listaTrain;
        this.listaTest = listaTest;
        this.kMaisProximos = kMaisProximos;
    }

    /*public ProcessaPontosThread(List<String> listaTrain, List<String> listaTest, int kMaisProximos) {
        this.listaTrain = listaTrain;
        this.listaTest = listaTest;
        this.kMaisProximos = kMaisProximos;
    }*/
    
    
    
    /*@Override
    public void run() {
        System.out.println("COMECOU A RODAR !");
        int contador = 0;
        for (String linhaTeste : listaTest){
        //for(double[] linhaTeste : listaTest){
            List<Ponto> pontosMaisProximos = new ArrayList<>();
            long tempoInicial = new Date().getTime();
            contador++;
            for (String linhaTrain : listaTrain){   
            //for(double[] linhaTrain : listaTrain){
                Ponto ponto = new Ponto(linhaTrain.split(" ") , linhaTeste.split(" ")); 
                //Ponto ponto = new Ponto(linhaTrain, linhaTeste);
                if(pontosMaisProximos.size() < kMaisProximos){
                    pontosMaisProximos.add(ponto);
                    Collections.sort(pontosMaisProximos);
                }else if (pontosMaisProximos.get(0).getDistancia() > ponto.getDistancia()){
                    pontosMaisProximos.remove(0);
                    pontosMaisProximos.add(0, ponto);
                    Collections.sort(pontosMaisProximos);
                }
            }
            System.out.println("Linha : " + contador + "          |         tempo gasto : " + (new Date().getTime() - tempoInicial) + " ms"  + pontosMaisProximos);
            //for(Ponto ponto : pontosMaisProximos){
            //    System.out.println(ponto);
            //}
        }
    }*/
    
    @Override
    public void run() {
        System.out.println("COMECOU A RODAR !");
        int contador = 0;
        for(double[] linhaTeste : listaTest){
            List<Ponto> pontosMaisProximos = new ArrayList<>();
            long tempoInicial = new Date().getTime();
            contador++;
            for(double[] linhaTrain : listaTrain){
                Ponto ponto = new Ponto(linhaTrain, linhaTeste);
                if(pontosMaisProximos.size() < kMaisProximos){
                    pontosMaisProximos.add(ponto);
                    Collections.sort(pontosMaisProximos);
                }else if (pontosMaisProximos.get(0).getDistancia() > ponto.getDistancia()){
                    pontosMaisProximos.remove(0);
                    pontosMaisProximos.add(0, ponto);
                    Collections.sort(pontosMaisProximos);
                }
            }
            System.out.println("Linha : " + contador + "          |         tempo gasto : " + (new Date().getTime() - tempoInicial) + " ms"  + pontosMaisProximos);
        }
    }
    
}
