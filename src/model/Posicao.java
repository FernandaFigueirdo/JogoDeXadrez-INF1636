package model;

public class Posicao {
	public final int linha;
	public final int coluna;
	
	public Posicao(int linha, int coluna) {
		this.linha=linha;
		this.coluna=coluna;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Posicao)) return false;
        Posicao outra = (Posicao) obj;
        return this.linha == outra.linha && this.coluna == outra.coluna;
    }

    @Override
    public int hashCode() {
        return 31 * linha + coluna;
    }

    @Override
    public String toString() {
        return "(" + linha + "," + coluna + ")";
    }
}
