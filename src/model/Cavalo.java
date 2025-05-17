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


        adicionarMovimento(tabuleiro, movimentos, getLinha() - 2, getColuna() - 1);

        adicionarMovimento(tabuleiro, movimentos, getLinha() - 2, getColuna() + 1);

        adicionarMovimento(tabuleiro, movimentos, getLinha() - 1, getColuna() - 2);

        adicionarMovimento(tabuleiro, movimentos, getLinha() - 1, getColuna() + 2);

        adicionarMovimento(tabuleiro, movimentos, getLinha() + 1, getColuna() - 2);

        adicionarMovimento(tabuleiro, movimentos, getLinha() + 1, getColuna() + 2);

        adicionarMovimento(tabuleiro, movimentos, getLinha() + 2, getColuna() - 1);

        adicionarMovimento(tabuleiro, movimentos, getLinha() + 2, getColuna() + 1);

        return movimentos;
    }

    private void adicionarMovimento(Tabuleiro tabuleiro, List<Posicao> movimentos, int linha, int coluna) {
        if (tabuleiro.estaDentro(linha, coluna)) {
            Peca pecaDestino = tabuleiro.getPeca(linha, coluna);

            if (pecaDestino == null || outraCor(pecaDestino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }
    }

    @Override
    public String toString() {
        if (getCor() == Cor.BRANCO) {
            return "CB";
        } else {
            return "CP";
        }
    }
}