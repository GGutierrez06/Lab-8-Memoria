package lab8memoria.modelo;

public class ListaEnlazada {

    private NodoPokemon primero;
    private NodoPokemon activo;
    private int tamanio;

    public ListaEnlazada() {
        primero = null;
        activo = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public int contar() {
        return tamanio;
    }

    public void insertar(Pokemon pokemon) {
        if (pokemon == null) {
            return;
        }
        NodoPokemon nuevo = new NodoPokemon(pokemon);
        if (primero == null) {
            primero = nuevo;
        } else {
            NodoPokemon actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamanio++;
        if (activo == null && !pokemon.estaDerrotado()) {
            activo = nuevo;
        }
    }

    public Pokemon buscar(String nombre) {
        NodoPokemon nodo = buscarNodo(nombre);
        if (nodo == null) {
            return null;
        }
        return nodo.getPokemon();
    }

    public boolean eliminar(String nombre) {
        if (primero == null || nombre == null) {
            return false;
        }
        if (primero.getPokemon().getNombre().equalsIgnoreCase(nombre)) {
            NodoPokemon eliminado = primero;
            primero = primero.getSiguiente();
            eliminado.setSiguiente(null);
            tamanio--;
            if (activo == eliminado) {
                activo = primerNodoDisponible();
            }
            return true;
        }
        NodoPokemon anterior = primero;
        while (anterior.getSiguiente() != null) {
            if (anterior.getSiguiente().getPokemon().getNombre().equalsIgnoreCase(nombre)) {
                NodoPokemon eliminado = anterior.getSiguiente();
                anterior.setSiguiente(eliminado.getSiguiente());
                eliminado.setSiguiente(null);
                tamanio--;
                if (activo == eliminado) {
                    activo = primerNodoDisponible();
                }
                return true;
            }
            anterior = anterior.getSiguiente();
        }
        return false;
    }

    public Pokemon obtenerPorIndice(int indice) {
        if (indice < 0 || indice >= tamanio) {
            return null;
        }
        NodoPokemon actual = primero;
        int contador = 0;
        while (contador < indice) {
            actual = actual.getSiguiente();
            contador++;
        }
        return actual.getPokemon();
    }

    public String recorrer() {
        String texto = "";
        NodoPokemon actual = primero;
        while (actual != null) {
            texto = texto + actual.getPokemon().toString();
            if (actual == activo) {
                texto = texto + " (ACTIVO)";
            }
            texto = texto + "\n";
            actual = actual.getSiguiente();
        }
        return texto;
    }

    public int contarDisponibles() {
        int disponibles = 0;
        NodoPokemon actual = primero;
        while (actual != null) {
            if (!actual.getPokemon().estaDerrotado()) {
                disponibles++;
            }
            actual = actual.getSiguiente();
        }
        return disponibles;
    }

    public Pokemon obtenerActivo() {
        if (activo == null) {
            return null;
        }
        return activo.getPokemon();
    }

    public boolean establecerActivo(String nombre) {
        NodoPokemon nodo = buscarNodo(nombre);
        if (nodo == null || nodo.getPokemon().estaDerrotado()) {
            return false;
        }
        activo = nodo;
        return true;
    }

    public Pokemon siguienteDisponible() {
        NodoPokemon actual = primero;
        while (actual != null) {
            if (actual != activo && !actual.getPokemon().estaDerrotado()) {
                return actual.getPokemon();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean cambiarAlSiguienteDisponible() {
        NodoPokemon actual = primero;
        while (actual != null) {
            if (actual != activo && !actual.getPokemon().estaDerrotado()) {
                activo = actual;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public boolean modificar(String nombre, int nivel, int hpMaximo) {
        NodoPokemon nodo = buscarNodo(nombre);
        if (nodo == null) {
            return false;
        }
        nodo.getPokemon().setNivel(nivel);
        nodo.getPokemon().setHpMaximo(hpMaximo);
        return true;
    }

    public boolean moverAlPrimerLugar(String nombre) {
        if (primero == null || nombre == null) {
            return false;
        }
        if (primero.getPokemon().getNombre().equalsIgnoreCase(nombre)) {
            return true;
        }
        NodoPokemon anterior = primero;
        while (anterior.getSiguiente() != null) {
            if (anterior.getSiguiente().getPokemon().getNombre().equalsIgnoreCase(nombre)) {
                NodoPokemon movido = anterior.getSiguiente();
                anterior.setSiguiente(movido.getSiguiente());
                movido.setSiguiente(primero);
                primero = movido;
                return true;
            }
            anterior = anterior.getSiguiente();
        }
        return false;
    }

    public void vaciar() {
        primero = null;
        activo = null;
        tamanio = 0;
    }

    private NodoPokemon buscarNodo(String nombre) {
        if (nombre == null) {
            return null;
        }
        NodoPokemon actual = primero;
        while (actual != null) {
            if (actual.getPokemon().getNombre().equalsIgnoreCase(nombre)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    private NodoPokemon primerNodoDisponible() {
        NodoPokemon actual = primero;
        while (actual != null) {
            if (!actual.getPokemon().estaDerrotado()) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public String toString() {
        return recorrer();
    }
}
