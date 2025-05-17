package model;

import java.util.ArrayList;
import java.util.List;

class Rei extends Peca {
    public Rei(Cor cor, int linha, int coluna) {
        super(cor, linha, coluna);
    }

    @Override
    public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();

        int linha, coluna;

        linha = getLinha() - 1;
        coluna = getColuna();
        if (tabuleiro.estaDentro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null || outraCor(destino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }

        linha = getLinha() + 1;
        coluna = getColuna();
        if (tabuleiro.estaDentro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null || outraCor(destino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }

        linha = getLinha();
        coluna = getColuna() - 1;
        if (tabuleiro.estaDentro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null || outraCor(destino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }

        linha = getLinha();
        coluna = getColuna() + 1;
        if (tabuleiro.estaDentro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null || outraCor(destino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }

        linha = getLinha() - 1;
        coluna = getColuna() - 1;
        if (tabuleiro.estaDentro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null || outraCor(destino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }

        linha = getLinha() - 1;
        coluna = getColuna() + 1;
        if (tabuleiro.estaDentro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null || outraCor(destino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }

        linha = getLinha() + 1;
        coluna = getColuna() - 1;
        if (tabuleiro.estaDentro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null || outraCor(destino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }

        linha = getLinha() + 1;
        coluna = getColuna() + 1;
        if (tabuleiro.estaDentro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null || outraCor(destino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }

        return movimentos;
    }

    @Override
    public String toString() {
        return getCor() == Cor.BRANCO ? "rB" : "rP";
    }
}