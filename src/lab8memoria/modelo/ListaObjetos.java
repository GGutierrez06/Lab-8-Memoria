package lab8memoria.modelo;

public class ListaObjetos {

    private NodoObjeto cabeza;

    public void insertar(Objeto objeto) {
        NodoObjeto nodo = new NodoObjeto(objeto);
        if (cabeza == null) {
            cabeza = nodo;
            return;
        }
        NodoObjeto actual = cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nodo);
    }

    public Objeto buscar(String nombre) {
        NodoObjeto actual = cabeza;
        while (actual != null) {
            if (actual.getObjeto().getNombre().equalsIgnoreCase(nombre)) {
                return actual.getObjeto();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public int contar() {
        int total = 0;
        NodoObjeto actual = cabeza;
        while (actual != null) {
            total++;
            actual = actual.getSiguiente();
        }
        return total;
    }

    public String recorrer() {
        StringBuilder resultado = new StringBuilder();
        NodoObjeto actual = cabeza;
        while (actual != null) {
            Objeto objeto = actual.getObjeto();
            resultado.append(objeto.getNombre())
                    .append(" - ")
                    .append(objeto.getDescripcion())
                    .append(" (x")
                    .append(objeto.getCantidad())
                    .append(")\n");
            actual = actual.getSiguiente();
        }
        return resultado.toString();
    }

    public String usar(Objeto objeto, Pokemon pokemon) {
        if (objeto == null || pokemon == null) {
            return "Datos invalidos.";
        }
        if (objeto.getCantidad() <= 0) {
            return "No quedan " + objeto.getNombre() + ".";
        }
        String resultado;
        if (objeto.getNombre().equalsIgnoreCase("Revivir")) {
            if (!pokemon.estaDerrotado()) {
                return pokemon.getNombre() + " no necesita ser revivido.";
            }
            pokemon.revivir();
            resultado = pokemon.getNombre() + " fue revivido y recupero " + pokemon.getHp() + " HP.";
        } else {
            if (pokemon.estaDerrotado()) {
                return "No se puede usar " + objeto.getNombre() + " en un Pokemon derrotado.";
            }
            pokemon.curar(objeto.getValor());
            resultado = pokemon.getNombre() + " recupero HP usando " + objeto.getNombre()
                    + ". Ahora tiene " + pokemon.getHp() + "/" + pokemon.getHpMaximo() + " HP.";
        }
        objeto.decrementarCantidad();
        return resultado;
    }
}
