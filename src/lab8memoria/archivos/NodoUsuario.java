package lab8memoria.archivos;

public class NodoUsuario {
    private Usuario usuario;
    private NodoUsuario siguiente;

    public NodoUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.siguiente = null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public NodoUsuario getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoUsuario siguiente) {
        this.siguiente = siguiente;
    }
}
