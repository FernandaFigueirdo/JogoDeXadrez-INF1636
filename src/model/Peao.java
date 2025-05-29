package model;

import java.util.ArrayList;
import java.util.List;

//Classe que representa o peão no xadrez, herda de Peca
class Peao extends Peca {

	 // Construtor: recebe cor, linha e coluna da posição inicial
    public Peao(Cor cor, int linha, int coluna) {
        super(cor, linha, coluna);
    }

    @Override
    public String getTipo() {
        return "Peao";
    }
    

    // Método que calcula e retorna os movimentos possíveis do peão
    @Override
    public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();

        // Define a direção do movimento de acordo com a cor da peça
        // Branco anda para cima (-1), preto para baixo (+1)
        int direcao = (getCor() == Cor.BRANCO) ? -1 : +1;

        int linhaAtual = getLinha();
        int colunaAtual = getColuna();

        // Calcula a próxima linha à frente
        int proximaLinha = linhaAtual + direcao;
        
        // Verifica se a casa à frente está vazia e dentro do tabuleiro
        if (tabuleiro.noTabuleiro(proximaLinha, colunaAtual) &&
            tabuleiro.getPeca(proximaLinha, colunaAtual) == null) {

        	// Adiciona o movimento de uma casa à frente
            movimentos.add(new Posicao(proximaLinha, colunaAtual));

            // Verifica se é o primeiro movimento do peão
            int linhaInicial = (getCor() == Cor.BRANCO) ? 6 : 1;
            int duasFrente = linhaAtual + 2 * direcao;
            // Se for o primeiro movimento, e ambas as casas à frente estiverem livres, permite andar duas casas
            if (linhaAtual == linhaInicial &&
                tabuleiro.noTabuleiro(duasFrente, colunaAtual) &&
                tabuleiro.getPeca(duasFrente, colunaAtual) == null) {

                movimentos.add(new Posicao(duasFrente, colunaAtual));
            }
        }

        // Verifica as diagonais para possível captura
        int[] direcoesLaterais = {-1, +1};
        for (int deslocamentoColuna : direcoesLaterais) {
            int novaColuna = colunaAtual + deslocamentoColuna;

            // Verifica se a diagonal está dentro dos limites
            if (tabuleiro.noTabuleiro(proximaLinha, novaColuna)) {
                Peca pecaAlvo = tabuleiro.getPeca(proximaLinha, novaColuna);

                // Se houver peça adversária na diagonal, adiciona o movimento de captura
                if (pecaAlvo != null && outraCor(pecaAlvo)) {
                    movimentos.add(new Posicao(proximaLinha, novaColuna));
                }
            }
        }

        // Retorna todos os movimentos possíveis do peão
        return movimentos;
    }

    // Retorna representação textual do peão (PB = Peão Branco, PP = Peão Preto)
    @Override
    public String toString() {
        if (getCor() == Cor.BRANCO) {
            return "PB";
        } else {
            return "PP";
        }
    }
}