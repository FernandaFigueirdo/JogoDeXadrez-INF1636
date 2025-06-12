package observer;

public interface Observado {
	 void registrarObservador(Observador o);
	 void removerObservador(Observador o);
	 void notificarObservadores();
}
