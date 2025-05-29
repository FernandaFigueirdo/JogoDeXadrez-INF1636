package model;

import java.util.ArrayList;
import java.util.List;

//Classe que representa a peça Rainha no xadrez, herda de Peca
class Rainha extends Peca {
	
	 // Construtor: recebe a cor e a posição inicial da rainha
    public Rainha(Cor cor, int linha, int coluna) {
        super(cor, linha, coluna);
    }

    @Override
    public String getTipo() {
        return "Rainha";
    }
    
    // Retorna os movimentos possíveis da rainha
    @Override
    public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();

        int linha, coluna;

        // Movimento para cima (vertical)
        linha = getLinha() - 1;
        coluna = getColuna();
        while (tabuleiro.noTabuleiro(linha, coluna)) {
            Peca destino = tabuleiro.getPeca(linha, coluna);
            if (destino == null) {
                movimentos.add(new Posicao(linha, coluna));
            } else {
                if (outraCor(destino)) {
                    movimentos.add(new Posicao(linha, coluna));
                }
                break;// bloqueia o movimento após encontrar peça
            }
            linha--;
        }
        // Movimento para baixo (vertical)
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

        // Movimento para a esquerda (horizontal)
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

        // Movimento para a direita (horizontal)
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

        // Movimento diagonal: cima-esquerda
        linha = getLinha() - 1;
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
            linha--;
            coluna--;
        }

        // Movimento diagonal: cima-direita
        linha = getLinha() - 1;
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
            linha--;
            coluna++;
        }

        // Movimento diagonal: baixo-esquerda
        linha = getLinha() + 1;
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
            linha++;
            coluna--;
        }

        // Movimento diagonal: baixo-direita
        linha = getLinha() + 1;
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
            linha++;
            coluna++;
        }

        return movimentos; // retorna todos os movimentos válidos
    }

    // Representação textual da rainha: RB para branca, RP para preta
    @Override
    public String toString() {
        return getCor() == Cor.BRANCO ? "RB" : "RP";
    }
}