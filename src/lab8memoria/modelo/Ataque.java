package lab8memoria.modelo;

public class Ataque {

    private String nombre;
    private String tipo;
    private int poder;

    public Ataque(String nombre, String tipo, int poder) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.poder = poder;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPoder() {
        return poder;
    }

    public String toString() {
        return nombre + " (" + tipo + ", poder " + poder + ")";
    }
}
