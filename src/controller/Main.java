package controller;
import java.util.Scanner;
import model.JogoAPI;

public class Main {
    public static void main(String[] args) {
        JogoAPI jogo = JogoAPI.getInstancia();
        Scanner sc = new Scanner(System.in);

        while (true) {
            jogo.imprimirTabuleiro();

            System.out.print("\nDigite a origem : ");
            String origem = sc.nextLine();
            int linhaOrigem = 8 - Character.getNumericValue(origem.charAt(1));
            int colunaOrigem = origem.charAt(0) - 'a';

            jogo.selecionaPeca(linhaOrigem, colunaOrigem);

            System.out.print("Digite o destino : ");
            String destino = sc.nextLine();
            int linhaDestino = 8 - Character.getNumericValue(destino.charAt(1));
            int colunaDestino = destino.charAt(0) - 'a';

            jogo.selecionaCasa(linhaDestino, colunaDestino);

            System.out.println("\n--- Movimento realizado ---\n");
        }
    }
}
