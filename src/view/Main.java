package view;

import java.awt.Frame;

public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame("Jogo de Xadrez");

        TabuleiroCanvas canvas = new TabuleiroCanvas();
        frame.add(canvas);
      
        frame.setSize(652, 675); 
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
