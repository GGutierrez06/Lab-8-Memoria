package lab8memoria.modelo;

public class Objeto {

    private String nombre;
    private String descripcion;
    private int valor;
    private int cantidad;

    public Objeto(String nombre, String descripcion, int valor, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valor = valor;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getValor() {
        return valor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void decrementarCantidad() {
        if (cantidad > 0) {
            cantidad--;
        }
    }

    public void incrementarCantidad(int extra) {
        cantidad = cantidad + extra;
    }
}
