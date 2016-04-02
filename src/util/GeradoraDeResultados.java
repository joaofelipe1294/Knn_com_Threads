/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.Ponto;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaofelipe
 */
public class GeradoraDeResultados {
    private List<Ponto> resultados;
    private List<double[]> listaTest;

    public GeradoraDeResultados(List<Ponto> resultados, List<double[]> listaTest) {
        this.resultados = resultados;
        this.listaTest = listaTest;
    }
    
    public void gerarArquivo(){
        PrintStream arquivo;
        try {
            arquivo = new PrintStream("respostas.txt");
            arquivo.println("+---------------------+");
            arquivo.println("|   KNN    |  TESTE   |");
            arquivo.println("+---------------------+");
            for(int contador = 0 ; contador < listaTest.size() ; contador++){
                int knn = resultados.get(contador).getLabel();
                double[] linhaTeste = listaTest.get(contador);
                int teste = (int) linhaTeste[linhaTeste.length - 1];
                arquivo.println("|     " + knn + "    |    " + teste + "     |");
            }
            arquivo.println("+---------------------+");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeradoraDeResultados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
