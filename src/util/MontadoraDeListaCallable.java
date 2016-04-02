/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.Ponto;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * @author joaofelipe
 */
public class MontadoraDeListaCallable {
    private List<Future<List<List<Ponto>>>> futures;
    private List<List<Ponto>> pontosMaisProximos;

    public MontadoraDeListaCallable(List<Future<List<List<Ponto>>>> futures) {
        this.futures = futures;
        this.pontosMaisProximos = new ArrayList<>();
    }

    public List<List<Ponto>> monta() throws InterruptedException, ExecutionException{
        for(Future<List<List<Ponto>>> futuro : futures){
            for(List<Ponto> ponto : futuro.get()){
                pontosMaisProximos.add(ponto);
            }
        }
        return pontosMaisProximos;
    }
}
