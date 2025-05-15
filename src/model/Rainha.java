package model;

import java.util.ArrayList;
import java.util.List;

class Rainha extends Peca {
    public Rainha(Cor cor, int linha, int coluna) {
        super(cor, linha, coluna);
    }
    
    @Override
    public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
    List<Posicao> movimentos = new ArrayList<>();

    int[][] deslocamentos = {
    		 {-1, +1}, 
    		 {+1, -1},
    		 {-1, -1}, 
    		 {+1, +1},
    		 {+1, 0},
    		 {0, +1},
    		 {-1, 0},
    		 {0, -1}	 
    };
    
    for(int[] desloc : deslocamentos) {
    	int novaLinha = getLinha() + desloc[0];
    	int novaColuna = getColuna() + desloc[1];
    	   
    	
    	while (tabuleiro.estaDentro(novaLinha, novaColuna)) {
               Peca destino = tabuleiro.getPeca(novaLinha, novaColuna);

               if (destino == null) {
                   movimentos.add(new Posicao(novaLinha, novaColuna));
               } else {
            	   if (outraCor(destino)) {
                       movimentos.add(new Posicao(novaLinha, novaColuna));
                   }
                   break;
               }
               novaLinha += desloc[0];
               novaColuna += desloc[1];
         }
    }
    
    return movimentos;
    }
    
    @Override
    public String toString() {
        return getCor() == Cor.BRANCO ? "RB" : "RP";
    }
    
}
