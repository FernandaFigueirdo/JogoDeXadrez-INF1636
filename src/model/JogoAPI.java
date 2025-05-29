package model;

import java.util.ArrayList;
import java.util.List;

//Classe que representa a API do jogo de xadrez, usando padrão Singleton
public class JogoAPI {
    private static JogoAPI instancia; // instância única do jogo (Singleton)

    private Tabuleiro tabuleiro; // representa o tabuleiro de xadrez
    private Peca pecaSelecionada; // armazena a peça atualmente selecionada
    private Cor jogadorAtual = Cor.BRANCO; // controla de quem é a vez

    private List<Peca> capturadasBrancas = new ArrayList<>(); // peças pretas capturadas
    private List<Peca> capturadasPretas = new ArrayList<>(); // peças brancas capturadas

    // Construtor privado para aplicar padrão Singleton
    private JogoAPI() {
        tabuleiro = new Tabuleiro();
        inicializarPecas(); //posiciona as peças no tabuleiro
    }

    // Método para obter a instância única do jogo
    public static JogoAPI getInstancia() {
        if (instancia == null) {
            instancia = new JogoAPI();
        }
        return instancia;
    }

    // Retorna o tabuleiro
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
    
 // Retorna os movimentos possíveis da peça selecionada
    public List<MovimentoDTO> getMovimentosPossiveisSelecionada() {
        if (pecaSelecionada == null) return null;

        List<Posicao> movimentos = pecaSelecionada.getMovimentosPossiveis(tabuleiro);
        List<MovimentoDTO> lista = new ArrayList<>();

        for (Posicao p : movimentos) {
            lista.add(new MovimentoDTO(p.getLinha(), p.getColuna()));
        }

        return lista;
    }


    // Retorna o jogador atual (quem deve jogar)
    public Cor getJogadorAtual() {
        return jogadorAtual;
    }

    // Alterna o jogador após um movimento válido
    public void alternarJogador() {
        jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
    }

    // Seleciona uma peça se ela pertence ao jogador atual
    public boolean selecionaPeca(int linha, int coluna) {
        Peca peca = tabuleiro.getPeca(linha, coluna);
        if (peca != null && peca.getCor() == jogadorAtual) {
            pecaSelecionada = peca;
            return true;
        } else {
            System.out.println("Peça inválida ou não é sua vez!");
            pecaSelecionada = null;
            return false;
        }
    }

    // Tenta mover a peça selecionada para a casa indicada
    public boolean selecionaCasa(int linha, int coluna) {
        if (pecaSelecionada == null) return false;

        List<Posicao> movimentos = pecaSelecionada.getMovimentosPossiveis(tabuleiro);
        Posicao destino = new Posicao(linha, coluna);
        if (movimentos.contains(destino)) {
            Peca alvo = tabuleiro.getPeca(linha, coluna);
            // Se capturar uma peça, armazena na lista de capturadas
            if (alvo != null) {
                if (alvo.getCor() == Cor.BRANCO)
                    capturadasBrancas.add(alvo);
                else
                    capturadasPretas.add(alvo);
            }

            // Executa o movimento da peça
            tabuleiro.removePeca(pecaSelecionada.getLinha(), pecaSelecionada.getColuna());
            tabuleiro.colocaPeca(pecaSelecionada, linha, coluna);
            
            // Troca o turno e limpa a peça selecionada
            alternarJogador();
            pecaSelecionada = null;
            return true;
        } 

        // Se o movimento não for válido, limpa a seleção
        pecaSelecionada = null;
        return false;
    }
    
    // Verifica se o jogador da cor especificada está em xeque
    public boolean estaEmXeque(Cor cor) {
        Posicao posRei = localizarRei(cor);

        if (posRei == null) return false; 

        // Percorre todas as peças do adversário e vê se alguma ameaça o rei
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
    
    // Busca a posição do rei de determinada cor
    private Posicao localizarRei(Cor cor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca peca = tabuleiro.getPeca(i, j);
                if (peca != null && peca.getTipo().equals("Rei") && peca.getCor() == cor) {
                    return new Posicao(i, j);
                }
            }
        }
        return null;
    }

    // Imprime o estado atual do tabuleiro e peças capturadas
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
    
    public String getTipoPeca(int linha, int coluna) {
        Peca peca = tabuleiro.getPeca(linha, coluna);
        return (peca != null) ? peca.getClass().getSimpleName().toUpperCase() : null;
    }

    public String getCorPeca(int linha, int coluna) {
        Peca peca = tabuleiro.getPeca(linha, coluna);
        return (peca != null) ? peca.getCor().name() : null;
    }

    // Inicializa todas as peças nas suas posições padrão no início do jogo
    private void inicializarPecas() {
    	  // Peças pretas (linhas 0 e 1)
        tabuleiro.colocaPeca(new Torre(Cor.PRETO, 0, 0), 0, 0);
        tabuleiro.colocaPeca(new Cavalo(Cor.PRETO, 0, 1), 0, 1);
        tabuleiro.colocaPeca(new Bispo(Cor.PRETO, 0, 2), 0, 2);
        tabuleiro.colocaPeca(new Rainha(Cor.PRETO, 0, 3), 0, 3);
        tabuleiro.colocaPeca(new Rei(Cor.PRETO, 0, 4), 0, 4);
        tabuleiro.colocaPeca(new Bispo(Cor.PRETO, 0, 5), 0, 5);
        tabuleiro.colocaPeca(new Cavalo(Cor.PRETO, 0, 6), 0, 6);
        tabuleiro.colocaPeca(new Torre(Cor.PRETO, 0, 7), 0, 7);
        for (int j = 0; j < 8; j++) {
            tabuleiro.colocaPeca(new Peao(Cor.PRETO, 1, j), 1, j);
        }

        // Peças brancas (linhas 6 e 7)
        for (int j = 0; j < 8; j++) {
            tabuleiro.colocaPeca(new Peao(Cor.BRANCO, 6, j), 6, j);
        }
        tabuleiro.colocaPeca(new Torre(Cor.BRANCO, 7, 0), 7, 0);
        tabuleiro.colocaPeca(new Cavalo(Cor.BRANCO, 7, 1), 7, 1);
        tabuleiro.colocaPeca(new Bispo(Cor.BRANCO, 7, 2), 7, 2);
        tabuleiro.colocaPeca(new Rainha(Cor.BRANCO, 7, 3), 7, 3);
        tabuleiro.colocaPeca(new Rei(Cor.BRANCO, 7, 4), 7, 4);
        tabuleiro.colocaPeca(new Bispo(Cor.BRANCO, 7, 5), 7, 5);
        tabuleiro.colocaPeca(new Cavalo(Cor.BRANCO, 7, 6), 7, 6);
        tabuleiro.colocaPeca(new Torre(Cor.BRANCO, 7, 7), 7, 7);
    }
}
