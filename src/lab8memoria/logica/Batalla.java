package lab8memoria.logica;

import lab8memoria.modelo.Ataque;
import lab8memoria.modelo.Entrenador;
import lab8memoria.modelo.Objeto;
import lab8memoria.modelo.Pokemon;

public class Batalla {

    private Entrenador jugador;
    private Entrenador rival;
    private TablaTipos tablaTipos;
    private ListaHistorial historial;
    private int turno;
    private int danioInfligido;
    private int danioRecibido;
    private int caidosRival;
    private int caidosJugador;

    public Batalla(Entrenador jugador, Entrenador rival) {
        this.jugador = jugador;
        this.rival = rival;
        this.tablaTipos = new TablaTipos();
        this.historial = new ListaHistorial();
        this.turno = 1;
    }

    public String atacar(int indiceAtaque) {
        Pokemon atacante = jugador.getEquipo().obtenerActivo();
        Pokemon defensor = rival.getEquipo().obtenerActivo();
        if (atacante == null || defensor == null) {
            return "No hay Pokemon activos.";
        }
        Ataque ataque = atacante.obtenerAtaque(indiceAtaque);
        if (ataque == null) {
            return atacante.getNombre() + " no tiene ese ataque disponible.";
        }
        int danio = calcularDanio(atacante, defensor, ataque);
        defensor.recibirDanio(danio);
        danioInfligido = danioInfligido + danio;

        String texto = atacante.getNombre() + " utilizo " + ataque.getNombre() + ".\n"
                + defensor.getNombre() + " recibio " + danio + " puntos de danio.\n"
                + defensor.getNombre() + ": " + defensor.getHp() + "/" + defensor.getHpMaximo() + " HP";
        historial.agregar(turno, atacante.getNombre() + " ataco a " + defensor.getNombre() + ".");
        historial.agregar(turno, defensor.getNombre() + " perdio " + danio + " HP.");

        if (defensor.estaDerrotado()) {
            caidosRival++;
            historial.agregar(turno, defensor.getNombre() + " fue derrotado.");
        }
        return texto;
    }

    public String usarObjeto(int indiceObjeto) {
        Objeto objeto = jugador.getObjetos().obtenerPorIndice(indiceObjeto);
        if (objeto == null) {
            return "Ese objeto no esta disponible.";
        }
        Pokemon pokemon = jugador.getEquipo().obtenerActivo();
        if (pokemon == null) {
            return "No hay Pokemon activo.";
        }
        String resultado = jugador.getObjetos().usar(objeto, pokemon);
        historial.agregar(turno, jugador.getNombre() + " utilizo " + objeto.getNombre() + ".");
        historial.agregar(turno, resultado);
        return resultado;
    }

    public String turnoRival() {
        Pokemon atacante = rival.getEquipo().obtenerActivo();
        Pokemon defensor = jugador.getEquipo().obtenerActivo();
        if (atacante == null || atacante.estaDerrotado() || defensor == null || defensor.estaDerrotado()) {
            return "";
        }
        int cantidadAtaques = atacante.cantidadAtaques();
        if (cantidadAtaques <= 0) {
            return "";
        }
        int indice = (int) (Math.random() * cantidadAtaques);
        Ataque ataque = atacante.obtenerAtaque(indice);
        int danio = calcularDanio(atacante, defensor, ataque);
        defensor.recibirDanio(danio);
        danioRecibido = danioRecibido + danio;

        String texto = atacante.getNombre() + " utilizo " + ataque.getNombre() + ".\n"
                + defensor.getNombre() + " recibio " + danio + " puntos de danio.\n"
                + defensor.getNombre() + ": " + defensor.getHp() + "/" + defensor.getHpMaximo() + " HP";
        historial.agregar(turno, atacante.getNombre() + " ataco a " + defensor.getNombre() + ".");
        historial.agregar(turno, defensor.getNombre() + " perdio " + danio + " HP.");

        if (defensor.estaDerrotado()) {
            caidosJugador++;
            historial.agregar(turno, defensor.getNombre() + " fue derrotado.");
        }
        turno++;
        return texto;
    }

    public int calcularDanio(Pokemon atacante, Pokemon defensor, Ataque ataque) {
        double multiplicador = tablaTipos.multiplicador(ataque.getTipo(), defensor.getTipo());
        int base = (atacante.getNivel() + ataque.getPoder()) / 5;
        int danio = (int) (base * multiplicador);
        if (danio < 1) {
            danio = 1;
        }
        return danio;
    }

    public boolean hayDerrotado() {
        Pokemon activoJugador = jugador.getEquipo().obtenerActivo();
        Pokemon activoRival = rival.getEquipo().obtenerActivo();
        boolean jugadorCaido = activoJugador != null && activoJugador.estaDerrotado();
        boolean rivalCaido = activoRival != null && activoRival.estaDerrotado();
        return jugadorCaido || rivalCaido;
    }

    public String continuarConSiguiente() {
        String resultado = "";
        Pokemon activoJugador = jugador.getEquipo().obtenerActivo();
        if (activoJugador != null && activoJugador.estaDerrotado()) {
            Pokemon siguiente = jugador.getEquipo().siguienteDisponible();
            if (siguiente != null) {
                jugador.getEquipo().establecerActivo(siguiente.getNombre());
                resultado = resultado + "Continuas con " + siguiente.getNombre() + ". ";
            }
        }
        Pokemon activoRival = rival.getEquipo().obtenerActivo();
        if (activoRival != null && activoRival.estaDerrotado()) {
            Pokemon siguiente = rival.getEquipo().siguienteDisponible();
            if (siguiente != null) {
                rival.getEquipo().establecerActivo(siguiente.getNombre());
                resultado = resultado + "El rival continua con " + siguiente.getNombre() + ".";
            }
        }
        return resultado;
    }

    public boolean jugadorGano() {
        return !rival.tieneDisponibles();
    }

    public boolean jugadorPerdio() {
        return !jugador.tieneDisponibles();
    }

    public void reiniciar() {
        turno = 1;
        danioInfligido = 0;
        danioRecibido = 0;
        caidosRival = 0;
        caidosJugador = 0;
        historial.limpiar();
    }

    public String estadisticas() {
        return "Turnos jugados: " + turno + "\n"
                + "Danio hecho: " + danioInfligido + "\n"
                + "Danio recibido: " + danioRecibido + "\n"
                + "Pokemon vencidos del rival: " + caidosRival + "\n"
                + "Pokemon vencidos propios: " + caidosJugador;
    }

    public ListaHistorial getHistorial() {
        return historial;
    }

    public int getTurno() {
        return turno;
    }
}
