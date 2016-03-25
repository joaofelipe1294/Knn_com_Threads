/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import util.SeparadoraDeListas;
import java.util.List;
import threads.LeArquivoThread;
import threads.ProcessaPontosThread;

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
        LeArquivoThread runnableTest = new LeArquivoThread("150k/test600");
        Thread threadTest = new Thread(runnableTest);
        threadTrain.start();
        threadTest.start();
        threadTrain.join();
        threadTest.join();        
        System.out.println("ARQUIVOS LIDOS !");
        List<double[]> listaTrain = runnableTrain.getLista();
        List<double[]> listaTest = runnableTest.getLista();
        List<List<double[]>> listas = new SeparadoraDeListas(NUMERO_THREADS, listaTest).quebra();
        System.out.println("LISTA QUEBRADA | " + listas.size());
        List<Thread> threads = new ArrayList<>();
        for(int contador = 0 ; contador < NUMERO_THREADS ; contador++){
            ProcessaPontosThread run = new ProcessaPontosThread(listaTrain, listas.get(contador), kMaisProximos);
            Thread thread = new Thread(run);
            thread.start();
            //thread.join();
            threads.add(thread);
        }
        System.out.println("Concluido !!!");
    }
}
