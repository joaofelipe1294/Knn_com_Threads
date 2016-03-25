/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author joaolopes
 */
public class LeArquivoThread implements Runnable{
    private String nomeArquivo;
    private List<double[]> lista;    
    
    public LeArquivoThread(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.lista = new ArrayList<>();
    }
    
    public List<double[]> getLista() {
        return lista;
    }
    
    @Override
    public synchronized void run() {
        try {
            Scanner scan = new Scanner(new FileReader(this.nomeArquivo));
            scan.nextLine();
            while(scan.hasNextLine()){
                String linha = scan.nextLine();
                String linhaQhebrada[] = linha.split(" ");
                double[] linhaDouble = new double[133];
                int contador = 0;
                for(String s : linhaQhebrada){
                    linhaDouble[contador] = Double.valueOf(s);
                    contador++;
                }
                this.lista.add(linhaDouble);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
