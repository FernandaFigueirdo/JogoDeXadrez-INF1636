package controller;

import view.FachadaView;

import javax.swing.*;
import java.awt.*;
import java.io.File;


public class MenuInicial {

    public static void mostrarMenu() {
        JFrame menu = new JFrame("Jogo de Xadrez - Menu Inicial");
        menu.setSize(300, 150);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLayout(new FlowLayout());

        JButton novaPartida = new JButton("Nova Partida");
        JButton carregarPartida = new JButton("Carregar Partida");

        carregarPartida.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int resultado = chooser.showOpenDialog(null);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                File arquivo = chooser.getSelectedFile();
                new FachadaView(arquivo);
                menu.dispose();
            }
        });

        novaPartida.addActionListener(e -> {
            new FachadaView();
            menu.dispose();
        });
        
        menu.add(novaPartida);
        menu.add(carregarPartida);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }
}
