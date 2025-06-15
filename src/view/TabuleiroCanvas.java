package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JOptionPane;


import model.JogoAPI;
import model.MovimentoDTO;
import model.ResultadoJogo;
import view.ImagemPeca;

public class TabuleiroCanvas extends Canvas implements MouseListener {
    private static final int TAM_CASA = 80;
    private final JogoAPI jogo;
    private List<MovimentoDTO> movimentosPossiveis;

    public TabuleiroCanvas() {
        this.jogo = JogoAPI.getInstancia();
        this.addMouseListener(this);
        this.setSize(640, 640);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        desenharTabuleiro(g2);
        desenharPecas(g2);
        destacarMovimentos(g2);
    }

    private void desenharTabuleiro(Graphics2D g2) {
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                if ((linha + coluna) % 2 == 0) {
                    g2.setColor(new Color(240, 240, 240));
                } else {
                    g2.setColor(new Color(30, 30, 30));
                }
                g2.fillRect(coluna * TAM_CASA, linha * TAM_CASA, TAM_CASA, TAM_CASA);
            }
        }
    }

    private void desenharPecas(Graphics2D g2) {
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                BufferedImage imagem = ImagemPeca.getImagem(linha, coluna);
                if (imagem != null) {
                    g2.drawImage(imagem, coluna * TAM_CASA, linha * TAM_CASA, TAM_CASA, TAM_CASA, null);
                }
            }
        }
    }

    private void destacarMovimentos(Graphics2D g2) {
        if (movimentosPossiveis != null) {
            g2.setColor(new Color(255, 0, 0, 128));
            for (MovimentoDTO pos : movimentosPossiveis) {
                g2.fillRect(pos.coluna * TAM_CASA, pos.linha * TAM_CASA, TAM_CASA, TAM_CASA);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int coluna = e.getX() / TAM_CASA;
        int linha = e.getY() / TAM_CASA;

        if (movimentosPossiveis == null) {
            boolean sucesso = jogo.selecionaPeca(linha, coluna);
            if (sucesso) {
                movimentosPossiveis = jogo.getMovimentosPossiveisSelecionada();
            }
        } else {
        	boolean moveu = jogo.selecionaCasa(linha, coluna);
            movimentosPossiveis = null;
            if (moveu) {
                ResultadoJogo status = jogo.verificarFimDeJogo(); 
                if (status == ResultadoJogo.XEQUE_MATE_BRANCO) {
                    JOptionPane.showMessageDialog(this, "Xeque-mate! Vitória das peças BRANCAS!");
                } else if (status == ResultadoJogo.XEQUE_MATE_PRETO) {
                    JOptionPane.showMessageDialog(this, "Xeque-mate! Vitória das peças PRETAS!");
                } else if (status == ResultadoJogo.CONGELAMENTO) {
                    JOptionPane.showMessageDialog(this, "Empate por congelamento!");
                } else if (jogo.estaEmXeque(jogo.getTabuleiro(), jogo.getJogadorAtual())) {
                    JOptionPane.showMessageDialog(this, "Atenção! Seu rei está em xeque!");
                }
            }
        }

        repaint();
    }
    

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {} 
    

}
