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
import java.util.concurrent.Callable;

/**
 *
 * @author joaofelipe
 */
public class ProcessaPontosCallable implements Callable<List<List<Ponto>>>{
    private List<double[]> listaTest;
    private List<double[]> listaTrain;
    private int kMaisProximos;

    public ProcessaPontosCallable(List<double[]> listaTest, List<double[]> listaTrain, int kMaisProximos) {
        this.listaTest = listaTest;
        this.listaTrain = listaTrain;
        this.kMaisProximos = kMaisProximos;
    }
    
    @Override
    public List<List<Ponto>> call() throws Exception {
        List<List<Ponto>> resultados = new ArrayList<>();
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
        }
        return resultados;
    }
    
}
