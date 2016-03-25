/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import beans.Ponto;
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
        List<List<String>> listas = new ArrayList<>();
        int contadorTeste = 0;
        for(int contadorListas = 0 ; contadorListas < NUMERO_THREADS ; contadorListas++){
            listas.add(new ArrayList<>());
            int indexLimite;
            if((contadorListas == (NUMERO_THREADS - 1) && (listaTest.size() % NUMERO_THREADS) > 0)){
                indexLimite = listaTest.size() - 1;
            }else{
                indexLimite = ((listaTest.size() / NUMERO_THREADS) * (contadorListas + 1)) - 1;
            }
            while(contadorTeste <= indexLimite){
                listas.get(contadorListas).add(listaTest.get(contadorTeste));
                contadorTeste++;
            }
        }
        
        for(List<String> lista : listas){
            System.out.println(lista.size());
        }
        
        
        /*for(int contadorListas = 0 ; contadorListas < listaTest.size() / NUMERO_THREADS ; contadorListas++){
            listas.add(new ArrayList<>());
            int limiteLoop = (NUMERO_THREADS * ( contadorListas)) - 1;
            for(int contadorListaTest = 0 ; contadorListaTest < limiteLoop ; contadorListaTest++){
                listas.get(contadorListas).add(listaTest.get(contadorListaTest));
            }
        }
        for(String s : listas.get(1)){
            System.out.println(s);
        }*/
        
        //List<String> test1 = new ArrayList<>();
        //ProcessaPontosThread run = new ProcessaPontosThread(listaTrain, test1 , kMaisProximos);
        //Thread t1 = new Thread(run);
        //t1.start();
        //t1.join();
        
    }
}
