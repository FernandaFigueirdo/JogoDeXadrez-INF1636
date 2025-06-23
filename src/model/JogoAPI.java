package model;

import java.util.ArrayList;
import java.util.List;
import observer.Observado;
import observer.Observador;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;



//Classe que representa a API do jogo de xadrez, usando padrão Singleton
public class JogoAPI implements Observado {
	 private final List<Observador> observadores = new ArrayList<>();

	    @Override
	    public void registrarObservador(Observador o) {
	        observadores.add(o);
	    }

	    @Override
	    public void removerObservador(Observador o) {
	        observadores.remove(o);
	    }

	    @Override
	    public void notificarObservadores() {
	        for (Observador obs : observadores) {
	            obs.atualizar();
	        }
	    }

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
    
    public String getTipoPeca(int linha, int coluna) {
        Peca peca = tabuleiro.getPeca(linha, coluna);
        return (peca != null) ? peca.getClass().getSimpleName().toUpperCase() : null;
    }

    public String getCorPeca(int linha, int coluna) {
        Peca peca = tabuleiro.getPeca(linha, coluna);
        return (peca != null) ? peca.getCor().name() : null;
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
            
            pecaSelecionada.moveu();

            // Verifica se foi Roque (rei se movendo para col 6 ou 2)
            if (pecaSelecionada instanceof Rei) {
            	if (coluna == 6) { // Roque curto
            		Peca torre = tabuleiro.getPeca(linha, 7);
            		tabuleiro.removePeca(linha, 7);
            		tabuleiro.colocaPeca(torre, linha, 5);
            		torre.moveu();
            	}else if (coluna == 2) { // Roque longo
            		Peca torre = tabuleiro.getPeca(linha, 0);
            		tabuleiro.removePeca(linha, 0);
            		tabuleiro.colocaPeca(torre, linha, 3);
            		torre.moveu();
            	}
            }
            
            // Verifica se é peão e se chegou ao fim do tabuleiro
            if (pecaSelecionada instanceof Peao) {
                int fim = (pecaSelecionada.getCor() == Cor.BRANCO) ? 0 : 7;
                if (linha == fim) {
                    // Por enquanto, promoção automática para rainha
                    promocaoPeao(linha, coluna, "RAINHA");
                }
            }

            
            // Troca o turno e limpa a peça selecionada
            alternarJogador();
            pecaSelecionada = null;
            notificarObservadores();
            return true;
        } 

        // Se o movimento não for válido, limpa a seleção
        pecaSelecionada = null;
        return false;
    }
    
    // Verifica se o jogador da cor especificada está em xeque
    public boolean estaEmXeque(Tabuleiro tabuleiroSimulado, Cor cor) {
        Posicao posRei = localizarRei(tabuleiroSimulado, cor);

        if (posRei == null) return false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca peca = tabuleiroSimulado.getPeca(i, j);
                if (peca != null && peca.getCor() != cor) {
                	if (peca instanceof Rei) continue;
                    List<Posicao> movimentos = peca.getMovimentosPossiveis(tabuleiroSimulado);
                    if (movimentos.contains(posRei)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    
    public boolean estaEmXeque(Cor cor) {
        return estaEmXeque(this.tabuleiro, cor);
    }

    
    public boolean estaEmXequeMate(Cor cor) {
        if (!estaEmXeque(tabuleiro, cor)) {
            return false; // Se não está em xeque, não pode ser xeque-mate
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca p = tabuleiro.getPeca(i, j);
                if (p != null && p.getCor() == cor) {
                    List<Posicao> movimentos = p.getMovimentosPossiveis(tabuleiro);
                    if (movimentos == null || movimentos.isEmpty()) continue;

                    for (Posicao destino : movimentos) {
                        Tabuleiro simulado = tabuleiro.clonar();
                        Peca simulada = simulado.getPeca(i, j);
                        if (simulada == null) continue;

                        simulado.removePeca(i, j);
                        simulado.colocaPeca(simulada, destino.getLinha(), destino.getColuna());

                        if (!estaEmXeque(simulado, cor)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
    
    public boolean estaCongelado(Cor cor) {
        if (estaEmXeque(tabuleiro, cor)) {
            return false; // Não é congelamento se está em xeque (aí pode ser xeque-mate)
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca p = tabuleiro.getPeca(i, j);
                if (p != null && p.getCor() == cor) {
                    List<Posicao> movimentos = p.getMovimentosPossiveis(tabuleiro);
                    if (movimentos == null || movimentos.isEmpty()) continue;

                    for (Posicao destino : movimentos) {
                        Tabuleiro simulado = tabuleiro.clonar();
                        Peca simulada = simulado.getPeca(i, j);
                        if (simulada == null) continue;

                        simulado.removePeca(i, j);
                        simulado.colocaPeca(simulada, destino.getLinha(), destino.getColuna());

                        if (!estaEmXeque(simulado, cor)) {
                            return false; // Tem pelo menos uma jogada válida
                        }
                    }
                }
            }
        }

        return true; // Nenhuma jogada válida sem entrar em xeque → congelamento
    }




    
    public void promocaoPeao(int linha, int coluna, String tipoNovaPeca) {
        Peca peca = tabuleiro.getPeca(linha, coluna);

        if (peca instanceof Peao) {
            Cor cor = peca.getCor();
            switch (tipoNovaPeca.toUpperCase()) {
                case "RAINHA":
                    tabuleiro.colocaPeca(new Rainha(cor, linha, coluna),linha, coluna);
                    break;
                case "TORRE":
                    tabuleiro.colocaPeca(new Torre(cor, linha, coluna),linha, coluna);
                    break;
                case "BISPO":
                    tabuleiro.colocaPeca(new Bispo(cor, linha, coluna),linha, coluna);
                    break;
                case "CAVALO":
                    tabuleiro.colocaPeca(new Cavalo(cor, linha, coluna),linha, coluna);
                    break;
                default:
                    System.out.println("Tipo de peça inválido para promoção.");
            }
        }
    }

    
    // Busca a posição do rei de determinada cor
    private Posicao localizarRei(Tabuleiro tabuleiro, Cor cor) {
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
    
    public ResultadoJogo verificarFimDeJogo() {
        if (estaEmXequeMate(jogadorAtual)) {
            return (jogadorAtual == Cor.BRANCO) ? ResultadoJogo.XEQUE_MATE_PRETO : ResultadoJogo.XEQUE_MATE_BRANCO;
        }
        if (estaCongelado(jogadorAtual)) {
            return ResultadoJogo.CONGELAMENTO;
        }
        return ResultadoJogo.EM_ANDAMENTO;
    }

    
    public void reiniciarJogo() {
        tabuleiro = new Tabuleiro();
        inicializarPecas();
        capturadasBrancas.clear();
        capturadasPretas.clear();
        jogadorAtual = Cor.BRANCO;
        pecaSelecionada = null;
        notificarObservadores();
    }


    
    public void salvarEstadoComJFileChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos de Texto (*.txt)", "txt"));
        int resultado = chooser.showSaveDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivo = chooser.getSelectedFile();
            if (!arquivo.getName().toLowerCase().endsWith(".txt")) {
                arquivo = new File(arquivo.getAbsolutePath() + ".txt");
            }
            try (PrintWriter writer = new PrintWriter(arquivo)) {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        String tipo = getTipoPeca(i, j);
                        String cor = getCorPeca(i, j);
                        if (tipo != null && cor != null) {
                            writer.println(tipo + ";" + cor + ";" + i + ";" + j);
                        }
                    }
                }
                writer.println("TURNO;" + (jogadorAtual == Cor.BRANCO ? "BRANCO" : "PRETO"));
                JOptionPane.showMessageDialog(null, "Partida salva com sucesso!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar partida.");
                e.printStackTrace();
            }
        }
    }


    public void carregarEstadoComJFileChooser() {
        JFileChooser chooser = new JFileChooser();
        int resultado = chooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivo = chooser.getSelectedFile();
            tabuleiro = new Tabuleiro(); 
            pecaSelecionada = null;
            capturadasBrancas.clear();
            capturadasPretas.clear();

            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha;

                while ((linha = reader.readLine()) != null) {
                    if (linha.startsWith("TURNO")) {
                        String[] partes = linha.split(";");
                        jogadorAtual = partes[1].equals("BRANCO") ? Cor.BRANCO : Cor.PRETO;
                    } else {
                        String[] partes = linha.split(";");
                        String tipo = partes[0];
                        String cor = partes[1];
                        int l = Integer.parseInt(partes[2]);
                        int c = Integer.parseInt(partes[3]);

                        Peca nova = criarPeca(tipo, cor.charAt(0), l, c);
                        if (nova != null) {
                            tabuleiro.colocaPeca(nova, l, c);
                        } else {
                            System.out.println("Erro ao criar peça: " + tipo + " " + cor);
                        }
                    }
                }

                JOptionPane.showMessageDialog(null, "Partida carregada com sucesso!");
                notificarObservadores();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar partida.");
                e.printStackTrace();
            }
        }
    }

    
    public Peca criarPeca(String tipo, char corChar, int linha, int coluna) {
        Cor cor = (corChar == 'B' || corChar == 'b') ? Cor.BRANCO : Cor.PRETO;

        switch (tipo.toUpperCase()) {
            case "PEAO": return new Peao(cor, linha, coluna);
            case "TORRE": return new Torre(cor, linha, coluna);
            case "BISPO": return new Bispo(cor, linha, coluna);
            case "CAVALO": return new Cavalo(cor, linha, coluna);
            case "RAINHA": return new Rainha(cor, linha, coluna);
            case "REI": return new Rei(cor, linha, coluna);
            default: return null;
        }
    }
    
    public void carregarEstadoDeArquivo(File arquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            tabuleiro = new Tabuleiro();
            pecaSelecionada = null;
            capturadasBrancas.clear();
            capturadasPretas.clear();

            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("TURNO")) {
                    String[] partes = linha.split(";");
                    jogadorAtual = partes[1].equals("BRANCO") ? Cor.BRANCO : Cor.PRETO;
                } else {
                    String[] partes = linha.split(";");
                    String tipo = partes[0];
                    String cor = partes[1];
                    int l = Integer.parseInt(partes[2]);
                    int c = Integer.parseInt(partes[3]);

                    Peca nova = criarPeca(tipo, cor.charAt(0), l, c);
                    if (nova != null) {
                        tabuleiro.colocaPeca(nova, l, c);
                    }
                }
            }

            notificarObservadores();
            JOptionPane.showMessageDialog(null, "Partida carregada com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar partida.");
            e.printStackTrace();
        }
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
