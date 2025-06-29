package controller;

import model.JogoAPI;
import observer.Observador;
import javax.swing.*;
import java.io.*;

public class Controller {
    private final JogoAPI jogo;

    public Controller() {
        this.jogo = JogoAPI.getInstancia();
    }

    public void registrarObservador(Observador obs) {
        jogo.registrarObservador(obs);
    }

    public void selecionarPeca(int linha, int coluna) {
        jogo.selecionaPeca(linha, coluna);
    }

    public void selecionarCasa(int linha, int coluna) {
        jogo.selecionaCasa(linha, coluna);
    }

    public JogoAPI getJogo() {
        return jogo;
    }
    
    public static void salvarPartida() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos de Texto (*.txt)", "txt"));
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File arquivo = chooser.getSelectedFile();
            if (!arquivo.getName().toLowerCase().endsWith(".txt")) {
                arquivo = new File(arquivo.getAbsolutePath() + ".txt");
            }

            String estado = JogoAPI.getInstancia().gerarEstadoTexto();
            try (PrintWriter out = new PrintWriter(arquivo)) {
                out.print(estado);
                JOptionPane.showMessageDialog(null, "Partida salva com sucesso!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar partida.");
                e.printStackTrace();
            }
        }
    }

    public static void carregarPartida() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File arquivo = chooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(arquivo));
                StringBuilder sb = new StringBuilder();
                String linha;
                while ((linha = reader.readLine()) != null) {
                    sb.append(linha).append("\n");
                }
                reader.close();
                JogoAPI.getInstancia().carregarEstadoDeTexto(sb.toString());
                JOptionPane.showMessageDialog(null, "Partida carregada com sucesso!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar partida.");
                e.printStackTrace();
            }
        }
    }
    
    public static void carregarPartidaDeArquivo(File arquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            StringBuilder sb = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                sb.append(linha).append("\n");
            }
            JogoAPI.getInstancia().carregarEstadoDeTexto(sb.toString());
            JOptionPane.showMessageDialog(null, "Partida carregada com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar partida.");
            e.printStackTrace();
        }
    }

}
