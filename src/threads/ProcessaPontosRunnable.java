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
public class ProcessaPontosRunnable implements Runnable{
    private List<double[]> listaTrain;
    private List<double[]> listaTest;
    private int kMaisProximos;
    private List<List<Ponto>> resultados;
    
    public ProcessaPontosRunnable(List<double[]> listaTrain, List<double[]> listaTest , int kMaisProximos) {
        this.listaTrain = listaTrain;
        this.listaTest = listaTest;
        this.kMaisProximos = kMaisProximos;
        this.resultados = new ArrayList<>();
    }

    public List<List<Ponto>> getResultados() {
        return resultados;
    }
    
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
            resultados.add(pontosMaisProximos);
            //System.out.println("Linha : " + contador + "          |         tempo gasto : " + (new Date().getTime() - tempoInicial) + " ms"  + pontosMaisProximos);
        }
    }
    
}
