import java.util.Scanner;
import model.*;

public class Main {
    public static void main(String[] args) {
        Jogo jogo = Jogo.getInstancia();
        Scanner sc = new Scanner(System.in);

        while (true) {
            jogo.imprimirTabuleiro();

            System.out.print("\nDigite a origem (ex: e2): ");
            String origem = sc.nextLine();
            int linhaOrigem = 8 - Character.getNumericValue(origem.charAt(1));
            int colunaOrigem = origem.charAt(0) - 'a';

            jogo.selecionaPeca(linhaOrigem, colunaOrigem);

            System.out.print("Digite o destino (ex: e4): ");
            String destino = sc.nextLine();
            int linhaDestino = 8 - Character.getNumericValue(destino.charAt(1));
            int colunaDestino = destino.charAt(0) - 'a';

            jogo.selecionaCasa(linhaDestino, colunaDestino);

            System.out.println("\n--- Movimento realizado ---\n");
        }
    }
}
