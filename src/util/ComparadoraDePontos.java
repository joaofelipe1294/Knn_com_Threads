/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.Ponto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joaofelipe
 */
public class ComparadoraDePontos {
    private List<List<Ponto>> pontosMaisProximos;
    private List<Ponto> resultados;

    public ComparadoraDePontos(List<List<Ponto>> pontosMaisProximos) {
        this.pontosMaisProximos = pontosMaisProximos;
        this.resultados = new ArrayList<>();
    }
    
    public List<Ponto> compara(){    
        for(List<Ponto> pontos : pontosMaisProximos){
            int maiorAparicao = 0;
            int labelMaiorAparicao = 0;
            for(int indexInicial = 0 ; indexInicial < pontos.size() ; indexInicial++){
                Ponto pontoAtual = pontos.get(indexInicial);
                int contagem = 1;
                for(int contador = indexInicial ; contador < pontos.size() ; contador++){
                    if(pontoAtual.getLabel() == pontos.get(contador).getLabel()){
                        contagem++;
                    }
                }
                if(contagem > maiorAparicao){
                    maiorAparicao = contagem;
                    labelMaiorAparicao = pontoAtual.getLabel();
                }
            }
            Ponto ponto = new Ponto();
            ponto.setLabel(labelMaiorAparicao);
            this.resultados.add(ponto);
        }
        return resultados;
    }
}
