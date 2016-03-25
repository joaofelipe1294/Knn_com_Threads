/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import beans.Ponto;
import java.util.ArrayList;
import java.util.Date;
import util.SeparadoraDeListas;
import java.util.List;
import threads.LeArquivoThread;
import threads.ProcessaPontosThread;
import util.MontadoraDeLista;

/**
 *
 * @author joaolopes
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int NUMERO_THREADS = 4;
        int kMaisProximos = 5;
        LeArquivoThread runnableTrain = new LeArquivoThread("150k/CCtrain");
        Thread threadTrain = new Thread(runnableTrain);
        LeArquivoThread runnableTest = new LeArquivoThread("150k/CCtest1");
        Thread threadTest = new Thread(runnableTest);
        System.out.println("Comecou a ler os arquivos");
        long tempo = new Date().getTime();
        threadTrain.start();
        threadTest.start();
        threadTrain.join();
        threadTest.join();        
        System.out.println("ARQUIVOS LIDOS ! tempo gasto : " + (new Date().getTime() - tempo));
        tempo = new Date().getTime();
        List<double[]> listaTrain = runnableTrain.getLista();
        List<double[]> listaTest = runnableTest.getLista();
        List<List<double[]>> listas = new SeparadoraDeListas(NUMERO_THREADS, listaTest).quebra();
        System.out.println("LISTA QUEBRADA ! tempo gasto :  " + (new Date().getTime() - tempo));
        tempo = new Date().getTime();
        List<ProcessaPontosThread> runnables = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for(int contador = 0 ; contador < NUMERO_THREADS ; contador++){
            ProcessaPontosThread run = new ProcessaPontosThread(listaTrain, listas.get(contador), kMaisProximos);
            runnables.add(run);
            Thread thread = new Thread(run);
            threads.add(thread);
        }
        threads.get(0).start();
        threads.get(1).start();
        threads.get(2).start();
        threads.get(3).start();
        threads.get(0).join();
        threads.get(1).join();
        threads.get(2).join();
        threads.get(3).join();
        System.out.println("Concluido processamento ponto a ponto ! tempo gasto : " + (new Date().getTime() - tempo));
        tempo = new Date().getTime();
        List<List<Ponto>> pontosMaisProximos = new MontadoraDeLista(runnables).monta();
        System.out.println("Lista montada : " + pontosMaisProximos.size());
        System.out.println("Remontada lita com os resultados ! tempo gasto : " + (new Date().getTime() - tempo));
        System.out.println("Concluido !!!");
    }
}
