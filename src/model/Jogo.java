package model;

import java.util.ArrayList;
import java.util.List;

public class Jogo {
    private static Jogo instancia;

    private Tabuleiro tabuleiro;
    private Peca pecaSelecionada;
    private Cor jogadorAtual = Cor.BRANCO;

    private List<Peca> capturadasBrancas = new ArrayList<>();
    private List<Peca> capturadasPretas = new ArrayList<>();

    private Jogo() {
        tabuleiro = new Tabuleiro();
        inicializarPecas();
    }

    public static Jogo getInstancia() {
        if (instancia == null) {
            instancia = new Jogo();
        }
        return instancia;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
    }

    public void alternarJogador() {
        jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
    }

    public void selecionaPeca(int linha, int coluna) {
        Peca peca = tabuleiro.getPeca(linha, coluna);
        if (peca != null && peca.getCor() == jogadorAtual) {
            pecaSelecionada = peca;
        } else {
            System.out.println("Peça inválida ou não é sua vez!");
            pecaSelecionada = null;
        }
    }

    public void selecionaCasa(int linha, int coluna) {
        if (pecaSelecionada == null) return;

        List<Posicao> movimentos = pecaSelecionada.getMovimentosPossiveis(tabuleiro);
        Posicao destino = new Posicao(linha, coluna);
        if (movimentos.contains(destino)) {
            Peca alvo = tabuleiro.getPeca(linha, coluna);
            if (alvo != null) {
                if (alvo.getCor() == Cor.BRANCO)
                    capturadasBrancas.add(alvo);
                else
                    capturadasPretas.add(alvo);
            }

            tabuleiro.removerPeca(pecaSelecionada.getLinha(), pecaSelecionada.getColuna());
            tabuleiro.colocarPeca(pecaSelecionada, linha, coluna);
            alternarJogador();
        } else {
            System.out.println("Movimento inválido!");
        }

        pecaSelecionada = null;
    }
    
    public boolean estaEmXeque(Cor cor) {
        Posicao posRei = localizarRei(cor);

        if (posRei == null) return false; 

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca peca = tabuleiro.getPeca(i, j);
                if (peca != null && peca.getCor() != cor) {
                    List<Posicao> movimentos = peca.getMovimentosPossiveis(tabuleiro);
                    if (movimentos.contains(posRei)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    
    private Posicao localizarRei(Cor cor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca peca = tabuleiro.getPeca(i, j);
                if (peca instanceof Rei && peca.getCor() == cor) {
                    return new Posicao(i, j);
                }
            }
        }
        return null;
    }

    public void imprimirTabuleiro() {
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < 8; j++) {
                Casa casa = tabuleiro.getCasa(i, j);
                System.out.print(casa + " ");
            }
            System.out.println();
        }
        System.out.println("   a  b  c  d  e  f  g  h");

        System.out.print("Capturadas Brancas: ");
        for (Peca p : capturadasBrancas) System.out.print(p + " ");
        System.out.println();

        System.out.print("Capturadas Pretas: ");
        for (Peca p : capturadasPretas) System.out.print(p + " ");
        System.out.println();

        System.out.println("Vez de: " + (jogadorAtual == Cor.BRANCO ? "BRANCO" : "PRETO"));
        
        if (estaEmXeque(jogadorAtual)) {
            System.out.println("XEQUE no jogador " + jogadorAtual);
        }
    }

    private void inicializarPecas() {
        tabuleiro.colocarPeca(new Torre(Cor.PRETO, 0, 0), 0, 0);
        tabuleiro.colocarPeca(new Cavalo(Cor.PRETO, 0, 1), 0, 1);
        tabuleiro.colocarPeca(new Bispo(Cor.PRETO, 0, 2), 0, 2);
        tabuleiro.colocarPeca(new Rainha(Cor.PRETO, 0, 3), 0, 3);
        tabuleiro.colocarPeca(new Rei(Cor.PRETO, 0, 4), 0, 4);
        tabuleiro.colocarPeca(new Bispo(Cor.PRETO, 0, 5), 0, 5);
        tabuleiro.colocarPeca(new Cavalo(Cor.PRETO, 0, 6), 0, 6);
        tabuleiro.colocarPeca(new Torre(Cor.PRETO, 0, 7), 0, 7);
        for (int j = 0; j < 8; j++) {
            tabuleiro.colocarPeca(new Peao(Cor.PRETO, 1, j), 1, j);
        }

        for (int j = 0; j < 8; j++) {
            tabuleiro.colocarPeca(new Peao(Cor.BRANCO, 6, j), 6, j);
        }
        tabuleiro.colocarPeca(new Torre(Cor.BRANCO, 7, 0), 7, 0);
        tabuleiro.colocarPeca(new Cavalo(Cor.BRANCO, 7, 1), 7, 1);
        tabuleiro.colocarPeca(new Bispo(Cor.BRANCO, 7, 2), 7, 2);
        tabuleiro.colocarPeca(new Rainha(Cor.BRANCO, 7, 3), 7, 3);
        tabuleiro.colocarPeca(new Rei(Cor.BRANCO, 7, 4), 7, 4);
        tabuleiro.colocarPeca(new Bispo(Cor.BRANCO, 7, 5), 7, 5);
        tabuleiro.colocarPeca(new Cavalo(Cor.BRANCO, 7, 6), 7, 6);
        tabuleiro.colocarPeca(new Torre(Cor.BRANCO, 7, 7), 7, 7);
    }
}
