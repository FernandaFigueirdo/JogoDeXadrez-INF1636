package model;

import java.util.ArrayList;
import java.util.List;

//Classe que representa a Torre no xadrez, herda da classe Peca
class Torre extends Peca {
	// Construtor: define a cor, linha e coluna iniciais da torre
    public Torre(Cor cor, int linha, int coluna) {
        super(cor, linha, coluna);
    }

    @Override
    public String getTipo() {
        return "Torre";
    }
    
    // Retorna os movimentos possíveis da torre
    @Override
    public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();

        int linha, coluna;

        // Movimento para cima (diminuindo a linha)
        linha = getLinha() - 1;
        coluna = getColuna();
        while (tabuleiro.noTabuleiro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null) {
                movimentos.add(new Posicao(linha, coluna)); // casa vazia
            } else {
                if (outraCor(destino)) {
                    movimentos.add(new Posicao(linha, coluna)); // pode capturar inimigo
                }
                break; // bloqueia se encontrar qualquer peça
            }
            linha--;
        }

        // Movimento para baixo (aumentando a linha)
        linha = getLinha() + 1;
        coluna = getColuna();
        while (tabuleiro.noTabuleiro(linha, coluna)) {
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

        // Movimento para a esquerda (diminuindo a coluna)
        linha = getLinha();
        coluna = getColuna() - 1;
        while (tabuleiro.noTabuleiro(linha, coluna)) {
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

        // Movimento para a direita (aumentando a coluna)
        linha = getLinha();
        coluna = getColuna() + 1;
        while (tabuleiro.noTabuleiro(linha, coluna)) {
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

        // Retorna a lista final com todos os movimentos válidos da torre
        return movimentos;
    }

    // Retorna a lista final com todos os movimentos válidos da torre
    @Override
    public String toString() {
        return getCor() == Cor.BRANCO ? "TB" : "TP";
    }
}