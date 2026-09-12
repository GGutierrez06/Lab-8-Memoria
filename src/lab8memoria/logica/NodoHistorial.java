package lab8memoria.logica;

public class NodoHistorial {

    private int turno;
    private String texto;
    private NodoHistorial siguiente;

    public NodoHistorial(int turno, String texto) {
        this.turno = turno;
        this.texto = texto;
        this.siguiente = null;
    }

    public int getTurno() {
        return turno;
    }

    public String getTexto() {
        return texto;
    }

    public NodoHistorial getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoHistorial siguiente) {
        this.siguiente = siguiente;
    }
}
