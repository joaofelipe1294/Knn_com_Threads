/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import beans.Ponto;
import util.SeparadoraDeListas;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import threads.LeArquivoThread;
import threads.ProcessaPontosThread;

/**
 *
 * @author joaolopes
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int NUMERO_THREADS = 3;
        int kMaisProximos = 5;
        LeArquivoThread runnableTrain = new LeArquivoThread("150k/CCtrain");
        Thread threadTrain = new Thread(runnableTrain);
        LeArquivoThread runnableTest = new LeArquivoThread("150k/CCtest1");
        Thread threadTest = new Thread(runnableTest);
        threadTrain.start();
        threadTest.start();
        threadTrain.join();
        threadTest.join();        
        List<String> listaTrain = runnableTrain.getLista();
        List<String> listaTest = runnableTest.getLista();
        List<List<String>> listas = new SeparadoraDeListas(NUMERO_THREADS, listaTest).quebra();
        for(List<String> lista : listas){
            System.out.println(lista.size());
        }
    }
}
