/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import beans.Ponto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import threads.LeArquivoCallable;
import threads.ProcessaPontosRunnable;
import util.ComparadoraDePontos;
import util.GeradoraDeResultados;
import util.GeradoraMatrizDeDecisao;
import util.MontadoraDeLista;
import util.SeparadoraDeListas;

/**
 *
 * @author joaofelipe
 */
public class MainCallable {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String arquivoTreino;
        String arquivoTeste;
        int kMaisProximos;
        int numeroThreads;
        if(args.length > 0){
            arquivoTreino = args[0];
            arquivoTeste = args[1];
            kMaisProximos = Integer.valueOf(args[2]);
            numeroThreads = Integer.valueOf(args[4]);
        }else{
            Scanner scan = new Scanner(System.in);
            System.out.print("Arquivo de treino : ");
            arquivoTreino = scan.next();
            System.out.print("Arquivo de teste : ");
            arquivoTeste = scan.next();
            System.out.print("K mais proximos : ");
            kMaisProximos = scan.nextInt();
            System.out.print("Numero de threads : ");
            numeroThreads = scan.nextInt();
            scan.close();
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        LeArquivoCallable callableTreino = new LeArquivoCallable(arquivoTreino);
        LeArquivoCallable callableTeste = new LeArquivoCallable(arquivoTeste);
        System.out.println("Comecou a ler os arquivos");
        long tempo = new Date().getTime();
        Future<List<double[]>> futuroTeste = executorService.submit(callableTeste);
        Future<List<double[]>> futuroTreino = executorService.submit(callableTreino);
        List<double[]> listaTrain = futuroTreino.get();
        List<double[]> listaTest = futuroTeste.get();
        System.out.println("ARQUIVOS LIDOS ! tempo gasto : " + (new Date().getTime() - tempo));
        tempo = new Date().getTime();
        List<List<double[]>> listas = new SeparadoraDeListas(numeroThreads, listaTest).quebra();
        System.out.println("LISTA QUEBRADA ! tempo gasto :  " + (new Date().getTime() - tempo));
        tempo = new Date().getTime();
        List<ProcessaPontosRunnable> runnables = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for(int contador = 0 ; contador < numeroThreads ; contador++){
            ProcessaPontosRunnable run = new ProcessaPontosRunnable(listaTrain, listas.get(contador), kMaisProximos);
            runnables.add(run);
            Thread thread = new Thread(run);
            threads.add(thread);
        }
        if(numeroThreads == 2){
            threads.get(0).start();
            threads.get(1).start();
            threads.get(0).join();
            threads.get(1).join();
        }else if (numeroThreads == 4){
            threads.get(0).start();
            threads.get(1).start();
            threads.get(2).start();
            threads.get(3).start();
            threads.get(0).join();
            threads.get(1).join();
            threads.get(2).join();
            threads.get(3).join();
        }
        System.out.println("Concluido processamento ponto a ponto ! tempo gasto : " + (new Date().getTime() - tempo));
        tempo = new Date().getTime();
        List<List<Ponto>> pontosMaisProximos = new MontadoraDeLista(runnables).monta();
        System.out.println("Remontada lita com os resultados ! tempo gasto : " + (new Date().getTime() - tempo));
        List<Ponto> resultados = new ComparadoraDePontos(pontosMaisProximos).compara();
        new GeradoraDeResultados(resultados, listaTest).gerarArquivo();
        new GeradoraMatrizDeDecisao(listaTest, resultados).gerarMatriz();
        executorService.shutdown();
        System.out.println("Concluido !!!");
    }
}
