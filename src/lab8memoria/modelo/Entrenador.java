package lab8memoria.modelo;

public class Entrenador {

    private String nombre;
    private ListaEnlazada equipo;
    private ListaObjetos objetos;

    public Entrenador(String nombre) {
        this.nombre = nombre;
        this.equipo = new ListaEnlazada();
        this.objetos = new ListaObjetos();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaEnlazada getEquipo() {
        return equipo;
    }

    public ListaObjetos getObjetos() {
        return objetos;
    }

    public void agregarPokemon(Pokemon pokemon) {
        equipo.insertar(pokemon);
    }

    public Pokemon pokemonActivo() {
        return equipo.obtenerActivo();
    }

    public boolean cambiarPokemon(String nombre) {
        return equipo.establecerActivo(nombre);
    }

    public boolean continuarConSiguiente() {
        return equipo.cambiarAlSiguienteDisponible();
    }

    public boolean tieneDisponibles() {
        return equipo.contarDisponibles() > 0;
    }

    public int pokemonesDisponibles() {
        return equipo.contarDisponibles();
    }

    public String toString() {
        return nombre + " (" + equipo.contarDisponibles() + " de " + equipo.contar() + " disponibles)";
    }
}
