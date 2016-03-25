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
    private List<String> lista;
    
    public LeArquivoThread(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.lista = new ArrayList<>();
    }

    public List<String> getLista() {
        return lista;
    }
    
    @Override
    public synchronized void run() {
        try {
            Scanner scan = new Scanner(new FileReader(this.nomeArquivo));
            scan.nextLine();
            while(scan.hasNextLine()){
                this.lista.add(scan.nextLine());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
