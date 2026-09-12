package lab8memoria.archivos;

public class ListaUsuarios {
    private NodoUsuario nodo;
    private int cantidad;

    public ListaUsuarios() {
        nodo = null;
        cantidad = 0;
    }

    public boolean crear(String nombre, String pass) {
        if (buscar(nombre) != null) {
            return false;
        }
        Usuario nuevo = new Usuario(nombre, pass);
        NodoUsuario nUsuario = new NodoUsuario(nuevo);
        if (nodo == null) {
            nodo = nUsuario;
            cantidad++;
            return true;
        } else {
            NodoUsuario aux = nodo;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nUsuario);
            cantidad++;
            return true;
        }
    }

    public Usuario login(String nombre, String pass) {
        Usuario user = buscar(nombre);
        if (user == null) {
            return null;
        } else {
            if (user.getPass().equals(pass)) {
                return user;
            }
            return null;
        }
    }

    public Usuario buscar(String nombre) {
        NodoUsuario aux = nodo;
        while (aux != null) {
            if (aux.getUsuario().getNombre().equals(nombre)) {
                return aux.getUsuario();
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public int contar() {
        return cantidad;
    }

    public Usuario obtenerAleatorio() {
        if (cantidad == 0) {
            return null;
        }
        int indice = (int) (Math.random() * cantidad);
        NodoUsuario aux = nodo;
        int i = 0;
        while (i < indice) {
            aux = aux.getSiguiente();
            i++;
        }
        return aux.getUsuario();
    }

    public Usuario obtenerPorIndice(int indice) {
        if (indice < 0 || indice >= cantidad) {
            return null;
        }
        NodoUsuario actual = nodo;
        int i = 0;
        while (i < indice) {
            actual = actual.getSiguiente();
            i++;
        }
        return actual.getUsuario();
    }
}
