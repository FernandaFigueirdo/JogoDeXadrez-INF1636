package model;

import java.util.ArrayList;
import java.util.List;

class Peao extends Peca {

    public Peao(Cor cor, int linha, int coluna) {
        super(cor, linha, coluna);
    }

    @Override
    public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();

        int direcao = (getCor() == Cor.BRANCO) ? -1 : +1;

        int linhaAtual = getLinha();
        int colunaAtual = getColuna();

        int proximaLinha = linhaAtual + direcao;
        if (tabuleiro.estaDentro(proximaLinha, colunaAtual) &&
            tabuleiro.getPeca(proximaLinha, colunaAtual) == null) {

            movimentos.add(new Posicao(proximaLinha, colunaAtual));

            int linhaInicial = (getCor() == Cor.BRANCO) ? 6 : 1;
            int duasFrente = linhaAtual + 2 * direcao;
            if (linhaAtual == linhaInicial &&
                tabuleiro.estaDentro(duasFrente, colunaAtual) &&
                tabuleiro.getPeca(duasFrente, colunaAtual) == null) {

                movimentos.add(new Posicao(duasFrente, colunaAtual));
            }
        }

        int[] direcoesLaterais = {-1, +1};
        for (int deslocamentoColuna : direcoesLaterais) {
            int novaColuna = colunaAtual + deslocamentoColuna;

            if (tabuleiro.estaDentro(proximaLinha, novaColuna)) {
                Peca pecaAlvo = tabuleiro.getPeca(proximaLinha, novaColuna);

                if (pecaAlvo != null && outraCor(pecaAlvo)) {
                    movimentos.add(new Posicao(proximaLinha, novaColuna));
                }
            }
        }

        return movimentos;
    }

    @Override
    public String toString() {
        if (getCor() == Cor.BRANCO) {
            return "PB";
        } else {
            return "PP";
        }
    }
}