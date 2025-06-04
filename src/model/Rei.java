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
        
     // Verificar possibilidade de roque
        if (!this.jaMovimentou() && !JogoAPI.getInstancia().estaEmXeque(tabuleiro, this.getCor())) {
            linha = getLinha();

            // Roque curto
            Peca torreDireita = tabuleiro.getPeca(linha, 7);
            if (torreDireita instanceof Torre && !torreDireita.jaMovimentou()) {
                if (tabuleiro.getPeca(linha, 5) == null && tabuleiro.getPeca(linha, 6) == null) {
                    // Simula rei em f1 (coluna 5) e g1 (coluna 6)
                    boolean seguro = true;
                    for (int c : new int[] {5, 6}) {
                        Tabuleiro copia = tabuleiro.clonar(); 
                        copia.removePeca(getLinha(), getColuna());
                        copia.colocaPeca(new Rei(getCor(), linha, c), linha, c);
                        if (JogoAPI.getInstancia().estaEmXeque(copia, getCor())) {
                            seguro = false;
                            break;
                        }
                    }
                    if (seguro) movimentos.add(new Posicao(linha, 6));
                }
            }

            // Roque longo
            Peca torreEsquerda = tabuleiro.getPeca(linha, 0);
            if (torreEsquerda instanceof Torre && !torreEsquerda.jaMovimentou()) {
                if (tabuleiro.getPeca(linha, 1) == null &&
                    tabuleiro.getPeca(linha, 2) == null &&
                    tabuleiro.getPeca(linha, 3) == null) {
                    // Simula rei passando por d1 (col 3) e c1 (col 2)
                    boolean seguro = true;
                    for (int c : new int[] {3, 2}) {
                        Tabuleiro copia = tabuleiro.clonar();
                        copia.removePeca(getLinha(), getColuna());
                        copia.colocaPeca(new Rei(getCor(), linha, c), linha, c);
                        if (JogoAPI.getInstancia().estaEmXeque(copia, getCor())) {
                            seguro = false;
                            break;
                        }
                    }
                    if (seguro) movimentos.add(new Posicao(linha, 2));
                }
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