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
import java.util.concurrent.Callable;

/**
 *
 * @author joaofelipe
 */
public class LeArquivoCallable implements Callable<List<double[]>>{
    private String nomeArquivo;
    
    public LeArquivoCallable(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
    
    @Override
    public List<double[]> call() throws Exception {
        System.out.println("Comecou a executar " + Thread.currentThread().getId());
        List<double[]> lista = new ArrayList<>();
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
                lista.add(linhaDouble);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Concluida thread " + Thread.currentThread().getId());
        return lista;
    }
    
    
    
}
