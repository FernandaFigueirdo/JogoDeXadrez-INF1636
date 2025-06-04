package model;

//Classe que representa o tabuleiro de xadrez (8x8 casas)
class Tabuleiro {
    private Casa[][] casas; // matriz de casas (cada casa pode conter uma peça ou estar vazia)

    // Construtor: inicializa todas as 64 casas do tabuleiro
    public Tabuleiro() {
        casas = new Casa[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                casas[i][j] = new Casa(i, j); // inicializa cada casa com sua linha e coluna
            }
        }
    }
    
    // Retorna a casa na posição especificada
    public Casa getCasa(int linha, int coluna) {
    	return casas[linha][coluna];
    }
    
    // Retorna a peça que está na casa especificada
    public Peca getPeca(int linha, int coluna) {
    	return casas[linha][coluna].getPeca();
    }
 
    // Coloca uma peça em uma casa específica do tabuleiro
    public void colocaPeca(Peca peca, int linha, int coluna) {
    	casas[linha][coluna].setPeca(peca);  // define a peça naquela casa
    	peca.setPosicao(linha,coluna); // atualiza a posição da peça também
    }
    
    // Remove qualquer peça da casa especificada
    public void removePeca( int linha, int coluna) {
    	casas[linha][coluna].removePeca();
    }
    
    // Verifica se uma posição está dentro dos limites válidos do tabuleiro (0 a 7)
    public boolean noTabuleiro(int linha, int coluna) {
        return linha >= 0 && linha < 8 && coluna >= 0 && coluna < 8;
    }
    
    public Tabuleiro clonar() {
        Tabuleiro copia = new Tabuleiro();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca p = this.getPeca(i, j);
                if (p != null) {
                    // Criar uma nova instância da mesma peça com a mesma cor e posição
                    Peca nova = null;
                    if (p instanceof Rei) {
                        nova = new Rei(p.getCor(), i, j);
                    } else if (p instanceof Rainha) {
                        nova = new Rainha(p.getCor(), i, j);
                    } else if (p instanceof Torre) {
                        nova = new Torre(p.getCor(), i, j);
                    } else if (p instanceof Bispo) {
                        nova = new Bispo(p.getCor(), i, j);
                    } else if (p instanceof Cavalo) {
                        nova = new Cavalo(p.getCor(), i, j);
                    } else if (p instanceof Peao) {
                        nova = new Peao(p.getCor(), i, j);
                    }

                    if (nova != null && p.jaMovimentou()) {
                        nova.moveu();
                    }

                    copia.colocaPeca(nova, i, j);
                }
            }
        }

        return copia;
    }

}
