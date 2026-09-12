package lab8memoria.logica;

public class ListaHistorial {

    private NodoHistorial cabeza;
    private NodoHistorial cola;

    public void agregar(int turno, String texto) {
        NodoHistorial nodo = new NodoHistorial(turno, texto);
        if (cabeza == null) {
            cabeza = nodo;
            cola = nodo;
            return;
        }
        cola.setSiguiente(nodo);
        cola = nodo;
    }

    public String recorrer() {
        StringBuilder resultado = new StringBuilder();
        NodoHistorial actual = cabeza;
        int turnoAnterior = -1;
        while (actual != null) {
            if (actual.getTurno() != turnoAnterior) {
                if (turnoAnterior != -1) {
                    resultado.append("\n");
                }
                resultado.append("Turno ").append(actual.getTurno()).append("\n");
                turnoAnterior = actual.getTurno();
            }
            resultado.append(actual.getTexto()).append("\n");
            actual = actual.getSiguiente();
        }
        return resultado.toString();
    }

    public int contar() {
        int total = 0;
        NodoHistorial actual = cabeza;
        while (actual != null) {
            total++;
            actual = actual.getSiguiente();
        }
        return total;
    }

    public void limpiar() {
        cabeza = null;
        cola = null;
    }
}
