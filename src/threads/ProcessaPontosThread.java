/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import beans.Ponto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author joaolopes
 */
public class ProcessaPontosThread implements Runnable{
    private List<String> listaTrain;
    private List<String> listaTest;
    private int kMaisProximos;
    
    public ProcessaPontosThread(List<String> listaTrain, List<String> listaTest , int kMaisProximos) {
        this.listaTrain = listaTrain;
        this.listaTest = listaTest;
        this.kMaisProximos = kMaisProximos;
    }
    
    @Override
    public void run() {
        System.out.println("Comecou a rodar");
        for (String linhaTeste : listaTest){
            List<Ponto> pontosMaisProximos = new ArrayList<>();
            for(String linhaTrain : listaTrain){
                Ponto ponto = new Ponto(linhaTrain.split(" ") , linhaTeste.split(" ")); 
                if(pontosMaisProximos.size() < kMaisProximos){
                    pontosMaisProximos.add(ponto);
                }else if (pontosMaisProximos.get(0).getDistancia() > ponto.getDistancia()){
                    pontosMaisProximos.remove(0);
                    pontosMaisProximos.add(0, ponto);
                }
                Collections.sort(pontosMaisProximos);
            }
            System.out.println("----------------------------------");
            for(Ponto ponto : pontosMaisProximos){
                System.out.println(ponto);
            }
        }
    }
    
}
