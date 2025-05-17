package model;

import java.util.ArrayList;
import java.util.List;

class Bispo extends Peca {

    public Bispo(Cor cor, int linha, int coluna) {
        super(cor, linha, coluna);
    }

    @Override
    public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();

        int linha = getLinha() - 1;
        int coluna = getColuna() + 1;
        while (tabuleiro.estaDentro(linha, coluna)) {
            Peca pecaDestino = tabuleiro.getPeca(linha, coluna);

            if (pecaDestino == null) {
                movimentos.add(new Posicao(linha, coluna));
            } else {
                if (outraCor(pecaDestino)) {
                    movimentos.add(new Posicao(linha, coluna));
                }
                break; 
            }

            linha--;
            coluna++;
        }


        linha = getLinha() + 1;
        coluna = getColuna() - 1;
        while (tabuleiro.estaDentro(linha, coluna)) {
            Peca pecaDestino = tabuleiro.getPeca(linha, coluna);

            if (pecaDestino == null) {
                movimentos.add(new Posicao(linha, coluna));
            } else {
                if (outraCor(pecaDestino)) {
                    movimentos.add(new Posicao(linha, coluna));
                }
                break;
            }

            linha++;
            coluna--;
        }


        linha = getLinha() - 1;
        coluna = getColuna() - 1;
        while (tabuleiro.estaDentro(linha, coluna)) {
            Peca pecaDestino = tabuleiro.getPeca(linha, coluna);

            if (pecaDestino == null) {
                movimentos.add(new Posicao(linha, coluna));
            } else {
                if (outraCor(pecaDestino)) {
                    movimentos.add(new Posicao(linha, coluna));
                }
                break;
            }

            linha--;
            coluna--;
        }


        linha = getLinha() + 1;
        coluna = getColuna() + 1;
        while (tabuleiro.estaDentro(linha, coluna)) {
            Peca pecaDestino = tabuleiro.getPeca(linha, coluna);

            if (pecaDestino == null) {
                movimentos.add(new Posicao(linha, coluna));
            } else {
                if (outraCor(pecaDestino)) {
                    movimentos.add(new Posicao(linha, coluna));
                }
                break;
            }

            linha++;
            coluna++;
        }

        return movimentos;
    }

    @Override
    public String toString() {

        if (getCor() == Cor.BRANCO) {
            return "BB";
        } else {
            return "BP";
        }
    }
}
