package model;

class Tabuleiro {
    private Casa[][] casas;

    public Tabuleiro() {
        casas = new Casa[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                casas[i][j] = new Casa(i, j);
            }
        }
    }
    
    public Casa getCasa(int linha, int coluna) {
    	return casas[linha][coluna];
    }
    
    public Peca getPeca(int linha, int coluna) {
    	return casas[linha][coluna].getPeca();
    }
 
    public void colocarPeca(Peca peca, int linha, int coluna) {
    	casas[linha][coluna].setPeca(peca);
    	peca.setPosicao(linha,coluna);
    }
    
    public void removerPeca( int linha, int coluna) {
    	casas[linha][coluna].removePeca();
    }
    
    public boolean estaDentro(int linha, int coluna) {
        return linha >= 0 && linha < 8 && coluna >= 0 && coluna < 8;
    }
}
