/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.Ponto;
import java.util.ArrayList;
import java.util.List;
import threads.ProcessaPontosThread;

/**
 *
 * @author joaofelipe
 */
public class MontadoraDeLista {
    private List<ProcessaPontosThread> runnables;
    private List<List<Ponto>> pontosMaisProximos;

    public MontadoraDeLista(List<ProcessaPontosThread> runnables) {
        this.runnables = runnables;
        this.pontosMaisProximos = new ArrayList<>();
    }
    
    public List<List<Ponto>> monta(){
         new ArrayList<>();
        for(ProcessaPontosThread run : runnables){
           for(List<Ponto> ponto : run.getResultados()){
                pontosMaisProximos.add(ponto);
            }
        }
        return pontosMaisProximos;
    }
    
}
