package lab8memoria.archivos;

import lab8memoria.modelo.Entrenador;

public class Usuario {
    private String nombre;
    private String pass;
    private Entrenador entrenador;

    public Usuario(String usuario, String pass) {
        this.nombre = usuario;
        this.pass = pass;
        this.entrenador = null;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPass() {
        return pass;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }
}