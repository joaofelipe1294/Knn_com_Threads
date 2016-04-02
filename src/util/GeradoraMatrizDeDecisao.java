/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.Ponto;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaolopes
 */
public class GeradoraMatrizDeDecisao {
    private List<double[]> listaTeste;
    private List<Ponto> resultados;
    private List<int[]> matriz;
    private double classificadosCorretamente;

    public GeradoraMatrizDeDecisao(List<double[]> listaTeste, List<Ponto> resultados) {
        this.listaTeste = listaTeste;
        this.resultados = resultados;
        this.matriz = new ArrayList<>();
        int vet0[] = new int[] {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0};
        this.matriz.add(vet0);
        int vet1[] = new int[] {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0};
        this.matriz.add(vet1);
        int vet2[] = new int[] {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0};
        this.matriz.add(vet2);
        int vet3[] = new int[] {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0};
        this.matriz.add(vet3);
        int vet4[] = new int[] {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0};
        this.matriz.add(vet4);
        int vet5[] = new int[] {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0};
        this.matriz.add(vet5);
        int vet6[] = new int[] {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0};
        this.matriz.add(vet6);
        int vet7[] = new int[] {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0};
        this.matriz.add(vet7);
        int vet8[] = new int[] {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0};
        this.matriz.add(vet8);
        int vet9[] = new int[] {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0};
        this.matriz.add(vet9);
    }
    
    public void gerarMatriz(){
        classificadosCorretamente = 0;
        for(int contador = 0 ; contador < resultados.size() ; contador++){
            /*double[] linha = listaTeste.get(contador);
            for(double valor : linha){
                System.out.print(" " + valor + " ");
            }
            System.out.println("");*/
            int labelKnn = resultados.get(contador).getLabel();
            double[] linhaTeste = listaTeste.get(contador);
            int labelTeste = (int) linhaTeste[linhaTeste.length - 1];
            //System.out.println("knn : " + labelKnn + "  teste : " + labelTeste);
            matriz.get(labelTeste)[labelKnn] += 1;
            if(labelKnn == labelTeste){
                classificadosCorretamente += 1;
            }
        }
        gerarArquivo();
    }
    
    private void gerarArquivo(){
        PrintStream arquivo;
        try {
            arquivo = new PrintStream("matriz.txt");
            arquivo.println("        +---------------------------------------+");
            arquivo.println("        | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
            arquivo.println("+-------+---------------------------------------+");
            arquivo.println("|   0   | " + matriz.get(0)[0] + "   " + matriz.get(0)[1] + "   " + matriz.get(0)[2] + "   " + matriz.get(0)[3] + "   " + matriz.get(0)[4] + "   " + matriz.get(0)[5] + "   " + matriz.get(0)[6] + "   " + matriz.get(0)[7] + "   " + matriz.get(0)[8] + "   " + matriz.get(0)[9] + "   " + "|" );
            arquivo.println("|   1   | " + matriz.get(1)[0] + "   " + matriz.get(1)[1] + "   " + matriz.get(1)[2] + "   " + matriz.get(1)[3] + "   " + matriz.get(1)[4] + "   " + matriz.get(1)[5] + "   " + matriz.get(1)[6] + "   " + matriz.get(1)[7] + "   " + matriz.get(1)[8] + "   " + matriz.get(1)[9] + "   " + "|" );
            arquivo.println("|   2   | " + matriz.get(2)[0] + "   " + matriz.get(2)[1] + "   " + matriz.get(2)[2] + "   " + matriz.get(2)[3] + "   " + matriz.get(2)[4] + "   " + matriz.get(2)[5] + "   " + matriz.get(2)[6] + "   " + matriz.get(2)[7] + "   " + matriz.get(2)[8] + "   " + matriz.get(2)[9] + "   " + "|" );
            arquivo.println("|   3   | " + matriz.get(3)[0] + "   " + matriz.get(3)[1] + "   " + matriz.get(3)[2] + "   " + matriz.get(3)[3] + "   " + matriz.get(3)[4] + "   " + matriz.get(3)[5] + "   " + matriz.get(3)[6] + "   " + matriz.get(3)[7] + "   " + matriz.get(3)[8] + "   " + matriz.get(3)[9] + "   " + "|" );
            arquivo.println("|   4   | " + matriz.get(4)[0] + "   " + matriz.get(4)[1] + "   " + matriz.get(4)[2] + "   " + matriz.get(4)[3] + "   " + matriz.get(4)[4] + "   " + matriz.get(4)[5] + "   " + matriz.get(4)[6] + "   " + matriz.get(4)[7] + "   " + matriz.get(4)[8] + "   " + matriz.get(4)[9] + "   " + "|" );
            arquivo.println("|   5   | " + matriz.get(5)[0] + "   " + matriz.get(5)[1] + "   " + matriz.get(5)[2] + "   " + matriz.get(5)[3] + "   " + matriz.get(5)[4] + "   " + matriz.get(5)[5] + "   " + matriz.get(5)[6] + "   " + matriz.get(5)[7] + "   " + matriz.get(5)[8] + "   " + matriz.get(5)[9] + "   " + "|" );
            arquivo.println("|   6   | " + matriz.get(6)[0] + "   " + matriz.get(6)[1] + "   " + matriz.get(6)[2] + "   " + matriz.get(6)[3] + "   " + matriz.get(6)[4] + "   " + matriz.get(6)[5] + "   " + matriz.get(6)[6] + "   " + matriz.get(6)[7] + "   " + matriz.get(6)[8] + "   " + matriz.get(6)[9] + "   " + "|" );
            arquivo.println("|   7   | " + matriz.get(7)[0] + "   " + matriz.get(7)[1] + "   " + matriz.get(7)[2] + "   " + matriz.get(7)[3] + "   " + matriz.get(7)[4] + "   " + matriz.get(7)[5] + "   " + matriz.get(7)[6] + "   " + matriz.get(7)[7] + "   " + matriz.get(7)[8] + "   " + matriz.get(7)[9] + "   " + "|" );
            arquivo.println("|   8   | " + matriz.get(8)[0] + "   " + matriz.get(8)[1] + "   " + matriz.get(8)[2] + "   " + matriz.get(8)[3] + "   " + matriz.get(8)[4] + "   " + matriz.get(8)[5] + "   " + matriz.get(8)[6] + "   " + matriz.get(8)[7] + "   " + matriz.get(8)[8] + "   " + matriz.get(8)[9] + "   " + "|" );
            arquivo.println("|   9   | " + matriz.get(9)[0] + "   " + matriz.get(9)[1] + "   " + matriz.get(9)[2] + "   " + matriz.get(9)[3] + "   " + matriz.get(9)[4] + "   " + matriz.get(9)[5] + "   " + matriz.get(9)[6] + "   " + matriz.get(9)[7] + "   " + matriz.get(9)[8] + "   " + matriz.get(9)[9] + "   " + "|" );
            arquivo.println("+------------------------------------------------+");
            arquivo.println("Taxa de reconhecimento : " + (classificadosCorretamente / listaTeste.size()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeradoraDeResultados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
