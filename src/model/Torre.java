package model;

import java.util.ArrayList;
import java.util.List;

class Torre extends Peca {
    public Torre(Cor cor, int linha, int coluna) {
        super(cor, linha, coluna);
    }

    @Override
    public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();

        int linha, coluna;


        linha = getLinha() - 1;
        coluna = getColuna();
        while (tabuleiro.estaDentro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null) {
                movimentos.add(new Posicao(linha, coluna));
            } else {
                if (outraCor(destino)) {
                    movimentos.add(new Posicao(linha, coluna));
                }
                break;
            }
            linha--;
        }


        linha = getLinha() + 1;
        coluna = getColuna();
        while (tabuleiro.estaDentro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null) {
                movimentos.add(new Posicao(linha, coluna));
            } else {
                if (outraCor(destino)) {
                    movimentos.add(new Posicao(linha, coluna));
                }
                break;
            }
            linha++;
        }


        linha = getLinha();
        coluna = getColuna() - 1;
        while (tabuleiro.estaDentro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null) {
                movimentos.add(new Posicao(linha, coluna));
            } else {
                if (outraCor(destino)) {
                    movimentos.add(new Posicao(linha, coluna));
                }
                break;
            }
            coluna--;
        }


        linha = getLinha();
        coluna = getColuna() + 1;
        while (tabuleiro.estaDentro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null) {
                movimentos.add(new Posicao(linha, coluna));
            } else {
                if (outraCor(destino)) {
                    movimentos.add(new Posicao(linha, coluna));
                }
                break;
            }
            coluna++;
        }

        return movimentos;
    }

    @Override
    public String toString() {
        return getCor() == Cor.BRANCO ? "TB" : "TP";
    }
}