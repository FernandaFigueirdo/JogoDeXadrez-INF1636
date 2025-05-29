package model;

import java.util.ArrayList;
import java.util.List;


//Classe que representa a peça Cavalo do xadrez, herda de Peca
class Cavalo extends Peca {

	 // Construtor: recebe a cor, linha e coluna onde o cavalo será colocado
    public Cavalo(Cor cor, int linha, int coluna) {
        super(cor, linha, coluna);
    }
    
    
    @Override
    public String getTipo() {
        return "Cavalo";
    }
    
    // Retorna a lista de movimentos possíveis do cavalo no tabuleiro
    @Override
    public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();

        // Adiciona os 8 movimentos possíveis em L do cavalo
        adicionarMovimento(tabuleiro, movimentos, getLinha() - 2, getColuna() - 1);

        adicionarMovimento(tabuleiro, movimentos, getLinha() - 2, getColuna() + 1);

        adicionarMovimento(tabuleiro, movimentos, getLinha() - 1, getColuna() - 2);

        adicionarMovimento(tabuleiro, movimentos, getLinha() - 1, getColuna() + 2);

        adicionarMovimento(tabuleiro, movimentos, getLinha() + 1, getColuna() - 2);

        adicionarMovimento(tabuleiro, movimentos, getLinha() + 1, getColuna() + 2);

        adicionarMovimento(tabuleiro, movimentos, getLinha() + 2, getColuna() - 1);

        adicionarMovimento(tabuleiro, movimentos, getLinha() + 2, getColuna() + 1);

        return movimentos;// Retorna a lista final de movimentos válidos
    }

    // Verifica se a posição está dentro do tabuleiro e se pode ser ocupada (livre ou inimigo)
    private void adicionarMovimento(Tabuleiro tabuleiro, List<Posicao> movimentos, int linha, int coluna) {
        if (tabuleiro.noTabuleiro(linha, coluna)) {
            Peca pecaDestino = tabuleiro.getPeca(linha, coluna);
            // Se a casa estiver vazia ou tiver uma peça inimiga, adiciona o movimento
            if (pecaDestino == null || outraCor(pecaDestino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }
    }

    // Retorna a representação textual da peça (CB para cavalo branco, CP para cavalo preto)
    @Override
    public String toString() {
        if (getCor() == Cor.BRANCO) {
            return "CB";
        } else {
            return "CP";
        }
    }
}