package model;

import java.util.ArrayList;
import java.util.List;

//Classe que representa o Rei no xadrez, herda da classe Peca
class Rei extends Peca {
	// Construtor: define a cor, linha e coluna inicial do rei
    public Rei(Cor cor, int linha, int coluna) {
        super(cor, linha, coluna);
    }

    @Override
    public String getTipo() {
        return "Rei";
    }
    
    // Retorna uma lista com os movimentos possíveis do rei
    @Override
    public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();

        int linha, coluna;

        // Movimento para cima
        linha = getLinha() - 1;
        coluna = getColuna();
        if (tabuleiro.noTabuleiro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null || outraCor(destino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }

        // Movimento para baixo
        linha = getLinha() + 1;
        coluna = getColuna();
        if (tabuleiro.noTabuleiro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null || outraCor(destino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }

        // Movimento para a esquerda
        linha = getLinha();
        coluna = getColuna() - 1;
        if (tabuleiro.noTabuleiro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null || outraCor(destino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }

        // Movimento para a direita
        linha = getLinha();
        coluna = getColuna() + 1;
        if (tabuleiro.noTabuleiro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null || outraCor(destino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }

        // Movimento diagonal: cima-esquerda 
        linha = getLinha() - 1;
        coluna = getColuna() - 1;
        if (tabuleiro.noTabuleiro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null || outraCor(destino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }

        // Movimento diagonal: cima-direita
        linha = getLinha() - 1;
        coluna = getColuna() + 1;
        if (tabuleiro.noTabuleiro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null || outraCor(destino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }

        // Movimento diagonal: baixo-esquerda
        linha = getLinha() + 1;
        coluna = getColuna() - 1;
        if (tabuleiro.noTabuleiro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null || outraCor(destino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }
        // Movimento diagonal: baixo-direita
        linha = getLinha() + 1;
        coluna = getColuna() + 1;
        if (tabuleiro.noTabuleiro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null || outraCor(destino)) {
                movimentos.add(new Posicao(linha, coluna));
            }
        }

        // Retorna todos os movimentos possíveis válidos
        return movimentos;
    }

    // Representação textual da peça (rB para rei branco, rP para rei preto)
    @Override
    public String toString() {
        return getCor() == Cor.BRANCO ? "rB" : "rP";
    }
}