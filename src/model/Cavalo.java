package model;

import java.util.ArrayList;
import java.util.List;

class Cavalo extends Peca {
    public Cavalo(Cor cor, int linha, int coluna) {
        super(cor, linha, coluna);
    }
    
    @Override
    public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
    List<Posicao> movimentos = new ArrayList<>();

    int[][] deslocamentos = {
        {-2, -1}, {-2, +1},
        {-1, -2}, {-1, +2},
        {+1, -2}, {+1, +2},
        {+2, -1}, {+2, +1}
    };
    
    for(int[] desloc : deslocamentos) {
    	int novaLinha = getLinha() + desloc[0];
    	int novaColuna = getColuna() + desloc[1];
    	   
    	
    	if (tabuleiro.estaDentro(novaLinha, novaColuna)) {
               Peca destino = tabuleiro.getPeca(novaLinha, novaColuna);

               if (destino == null || outraCor(destino)) {
                   movimentos.add(new Posicao(novaLinha, novaColuna));
               }
           }
    }
    
    return movimentos;
    }
    
    @Override
    public String toString() {
        return getCor() == Cor.BRANCO ? "CB" : "CP";
    }
    
}