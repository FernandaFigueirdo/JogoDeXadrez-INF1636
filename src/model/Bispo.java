package model;

import java.util.ArrayList;
import java.util.List;


//Classe que representa o Bispo no xadrez, herda da classe Peca
class Bispo extends Peca {

	 // Construtor: define a cor e a posição inicial do bispo
    public Bispo(Cor cor, int linha, int coluna) {
        super(cor, linha, coluna);
    }
    
    @Override
    public String getTipo() {
        return "Bispo";
    }
    
    // Retorna os movimentos possíveis do bispo
    @Override
    public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();

        // Diagonal superior direita
        int linha = getLinha() - 1;
        int coluna = getColuna() + 1;
        while (tabuleiro.noTabuleiro(linha, coluna)) {
            Peca pecaDestino = tabuleiro.getPeca(linha, coluna);

            if (pecaDestino == null) {
                movimentos.add(new Posicao(linha, coluna));// casa vazia
            } else {
                if (outraCor(pecaDestino)) {
                    movimentos.add(new Posicao(linha, coluna));// pode capturar
                }
                break; // bloqueia se encontrar qualquer peça
            }

            linha--;
            coluna++;
        }

        // Diagonal inferior esquerda 
        linha = getLinha() + 1;
        coluna = getColuna() - 1;
        while (tabuleiro.noTabuleiro(linha, coluna)) {
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

        // Diagonal superior esquerda 
        linha = getLinha() - 1;
        coluna = getColuna() - 1;
        while (tabuleiro.noTabuleiro(linha, coluna)) {
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

        // Diagonal inferior direita
        linha = getLinha() + 1;
        coluna = getColuna() + 1;
        while (tabuleiro.noTabuleiro(linha, coluna)) {
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

        // Retorna todos os movimentos possíveis do bispo
        return movimentos;
    }

    // Retorna a representação textual do bispo: BB = branco, BP = preto
    @Override
    public String toString() {

        if (getCor() == Cor.BRANCO) {
            return "BB";
        } else {
            return "BP";
        }
    }
}
