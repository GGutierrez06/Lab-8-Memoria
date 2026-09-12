package lab8memoria.archivos;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import lab8memoria.modelo.*;

public class GestorArchivos {

    private static final int tNombre = 25;
    private static final int tPass = 25;
    private static final int tamañoU = (tNombre + tPass) * 2;

    private static final String UserFile = "datos/usuarios.pk";

    private static final int tTipo = 20;
    private static final int registroAtaque = (tNombre + tTipo) * 2 + 4;
    private static final int registroPokemon = (tNombre + tTipo) * 2 + 4 + 4 + registroAtaque * 4;
    private static final String ArchivoPokemones = "datos/pokemones.pk";

    private static final int MAX_EQUIPO = 6;
    private static final int registroEquipo = tNombre * 2 + 4 + (tNombre * 2) * MAX_EQUIPO;
    private static final String ArchivoEquipos = "datos/equipos.pk";
    private static final String ArchivoRivales = "datos/rivales.pk";

    private void escribirTexto(RandomAccessFile reg, String texto, int longitud) throws IOException {
        String t = texto;
        if (t.length() > longitud) {
            t = t.substring(0, longitud);
        }
        while (t.length() < longitud) {
            t = t + " ";
        }
        reg.writeChars(t);
    }

    private String leerTexto(RandomAccessFile reg, int longitud) throws IOException {
        String t = "";
        for (int i = 0; i < longitud; i++) {
            t = t + reg.readChar();
        }
        return t.trim();
    }

    public void guardarUsuarios(ListaUsuarios lista) throws IOException {
        File archivo = new File(UserFile);
        if (archivo.getParentFile() != null) {
            archivo.getParentFile().mkdirs();
        }
        if (archivo.exists()) {
            archivo.delete();
        }
        RandomAccessFile reg = new RandomAccessFile(archivo, "rw");
        int total = lista.contar();
        for (int i = 0; i < total; i++) {
            Usuario u = lista.obtenerPorIndice(i);
            escribirTexto(reg, u.getNombre(), tNombre);
            escribirTexto(reg, u.getPass(), tPass);
        }
        reg.close();
    }

    public ListaUsuarios cargarUsuarios() throws IOException {
        ListaUsuarios lista = new ListaUsuarios();
        File archivo = new File(UserFile);
        if (!archivo.exists()) {
            if (archivo.getParentFile() != null) {
                archivo.getParentFile().mkdirs();
            }
            archivo.createNewFile();
            return lista;
        }
        RandomAccessFile reg = new RandomAccessFile(archivo, "r");
        long totalRegistros = reg.length() / tamañoU;
        for (long i = 0; i < totalRegistros; i++) {
            String nombre = leerTexto(reg, tNombre);
            String pass = leerTexto(reg, tPass);
            lista.crear(nombre, pass);
        }
        reg.close();
        return lista;
    }

    private void escribirAtaque(RandomAccessFile reg, Ataque a) throws IOException {
        escribirTexto(reg, a.getNombre(), tNombre);
        escribirTexto(reg, a.getTipo(), tTipo);
        reg.writeInt(a.getPoder());
    }

    private Ataque leerAtaque(RandomAccessFile reg) throws IOException {
        String nombre = leerTexto(reg, tNombre);
        String tipo = leerTexto(reg, tTipo);
        int poder = reg.readInt();
        return new Ataque(nombre, tipo, poder);
    }

    private void escribirPokemon(RandomAccessFile reg, Pokemon poke) throws IOException {
        escribirTexto(reg, poke.getNombre(), tNombre);
        escribirTexto(reg, poke.getTipo(), tTipo);
        reg.writeInt(poke.getNivel());
        reg.writeInt(poke.getHpMaximo());

        for (int i = 0; i < 4; i++) {
            if (i < poke.cantidadAtaques()) {
                escribirAtaque(reg, poke.obtenerAtaque(i));
            } else {
                escribirAtaque(reg, new Ataque("", "", 0));
            }
        }
    }

    private Pokemon leerPokemon(RandomAccessFile reg) throws IOException {
        String nombre = leerTexto(reg, tNombre);
        String tipo = leerTexto(reg, tTipo);
        int nivel = reg.readInt();
        int hpMaximo = reg.readInt();

        Pokemon pokemon = new Pokemon(nombre, tipo, nivel, hpMaximo);

        for (int i = 0; i < 4; i++) {
            Ataque ataque = leerAtaque(reg);
            if (!ataque.getNombre().isEmpty()) {
                pokemon.agregarAtaque(ataque);
            }
        }
        return pokemon;
    }

    public void guardarCatalogo(ListaEnlazada lista) throws IOException {
        File archivo = new File(ArchivoPokemones);
        if (archivo.getParentFile() != null) {
            archivo.getParentFile().mkdirs();
        }
        if (archivo.exists()) {
            archivo.delete();
        }
        RandomAccessFile reg = new RandomAccessFile(archivo, "rw");
        int total = lista.contar();
        for (int i = 0; i < total; i++) {
            escribirPokemon(reg, lista.obtenerPorIndice(i));
        }
        reg.close();
    }

    public ListaEnlazada cargarCatalogo() throws IOException {
        ListaEnlazada lista = new ListaEnlazada();
        File archivo = new File(ArchivoPokemones);
        if (!archivo.exists()) {
            if (archivo.getParentFile() != null) {
                archivo.getParentFile().mkdirs();
            }
            archivo.createNewFile();
            return lista;
        }
        RandomAccessFile reg = new RandomAccessFile(archivo, "r");
        long totalRegistros = reg.length() / registroPokemon;
        for (long i = 0; i < totalRegistros; i++) {
            lista.insertar(leerPokemon(reg));
        }
        reg.close();
        return lista;
    }

    private Pokemon clonarPokemon(Pokemon original) {
        Pokemon copia = new Pokemon(original.getNombre(), original.getTipo(), original.getNivel(),
                original.getHpMaximo());
        for (int i = 0; i < original.cantidadAtaques(); i++) {
            copia.agregarAtaque(original.obtenerAtaque(i));
        }
        return copia;
    }

    private long buscarIndiceEquipo(RandomAccessFile reg, String nombre) throws IOException {
        long total = reg.length() / registroEquipo;
        for (long i = 0; i < total; i++) {
            reg.seek(i * registroEquipo);
            String nombreLeido = leerTexto(reg, tNombre);
            if (nombreLeido.equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public void guardarEquipo(Usuario usuario) throws IOException {
        File archivo = new File(ArchivoEquipos);
        if (archivo.getParentFile() != null) {
            archivo.getParentFile().mkdirs();
        }
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
        RandomAccessFile reg = new RandomAccessFile(archivo, "rw");
        long indice = buscarIndiceEquipo(reg, usuario.getNombre());
        if (indice == -1) {
            indice = reg.length() / registroEquipo;
        }
        reg.seek(indice * registroEquipo);

        Entrenador entrenador = usuario.getEntrenador();
        escribirTexto(reg, usuario.getNombre(), tNombre);
        int cantidad = 0;
        if (entrenador != null) {
            cantidad = entrenador.getEquipo().contar();
            if (cantidad > MAX_EQUIPO) {
                cantidad = MAX_EQUIPO;
            }
        }
        reg.writeInt(cantidad);
        for (int i = 0; i < MAX_EQUIPO; i++) {
            if (entrenador != null && i < cantidad) {
                Pokemon p = entrenador.getEquipo().obtenerPorIndice(i);
                escribirTexto(reg, p.getNombre(), tNombre);
            } else {
                escribirTexto(reg, "", tNombre);
            }
        }
        reg.close();
    }

    public void cargarEquipo(Usuario usuario) throws IOException {
        File archivo = new File(ArchivoEquipos);
        if (!archivo.exists()) {
            return;
        }
        RandomAccessFile reg = new RandomAccessFile(archivo, "r");
        long indice = buscarIndiceEquipo(reg, usuario.getNombre());
        if (indice == -1) {
            reg.close();
            return;
        }
        reg.seek(indice * registroEquipo);
        leerTexto(reg, tNombre);
        int cantidad = reg.readInt();
        String[] nombresPokemon = new String[MAX_EQUIPO];
        for (int i = 0; i < MAX_EQUIPO; i++) {
            nombresPokemon[i] = leerTexto(reg, tNombre);
        }
        reg.close();

        ListaEnlazada catalogo = cargarCatalogo();
        Entrenador entrenador = new Entrenador(usuario.getNombre());
        for (int i = 0; i < cantidad; i++) {
            Pokemon base = catalogo.buscar(nombresPokemon[i]);
            if (base != null) {
                entrenador.agregarPokemon(clonarPokemon(base));
            }
        }
        usuario.setEntrenador(entrenador);
    }

    public void guardarRivales(Entrenador[] rivales) throws IOException {
        File archivo = new File(ArchivoRivales);
        if (archivo.getParentFile() != null) {
            archivo.getParentFile().mkdirs();
        }
        if (archivo.exists()) {
            archivo.delete();
        }
        RandomAccessFile reg = new RandomAccessFile(archivo, "rw");
        for (int i = 0; i < rivales.length; i++) {
            Entrenador rival = rivales[i];
            escribirTexto(reg, rival.getNombre(), tNombre);
            int cantidad = rival.getEquipo().contar();
            if (cantidad > MAX_EQUIPO) {
                cantidad = MAX_EQUIPO;
            }
            reg.writeInt(cantidad);
            for (int j = 0; j < MAX_EQUIPO; j++) {
                if (j < cantidad) {
                    Pokemon p = rival.getEquipo().obtenerPorIndice(j);
                    escribirTexto(reg, p.getNombre(), tNombre);
                } else {
                    escribirTexto(reg, "", tNombre);
                }
            }
        }
        reg.close();
    }

    public Entrenador[] cargarRivales() throws IOException {
        File archivo = new File(ArchivoRivales);
        if (!archivo.exists()) {
            if (archivo.getParentFile() != null) {
                archivo.getParentFile().mkdirs();
            }
            archivo.createNewFile();
            return new Entrenador[0];
        }
        RandomAccessFile reg = new RandomAccessFile(archivo, "r");
        long totalRegistros = reg.length() / registroEquipo;
        ListaEnlazada catalogo = cargarCatalogo();
        Entrenador[] rivales = new Entrenador[(int) totalRegistros];
        for (int i = 0; i < totalRegistros; i++) {
            String nombreEntrenador = leerTexto(reg, tNombre);
            int cantidad = reg.readInt();
            String[] nombresPokemon = new String[MAX_EQUIPO];
            for (int j = 0; j < MAX_EQUIPO; j++) {
                nombresPokemon[j] = leerTexto(reg, tNombre);
            }
            Entrenador rival = new Entrenador(nombreEntrenador);
            for (int j = 0; j < cantidad; j++) {
                Pokemon base = catalogo.buscar(nombresPokemon[j]);
                if (base != null) {
                    rival.agregarPokemon(clonarPokemon(base));
                }
            }
            rivales[i] = rival;
        }
        reg.close();
        return rivales;
    }

    public Entrenador cargarRivalAleatorio() throws IOException {
        Entrenador[] rivales = cargarRivales();
        if (rivales.length == 0) {
            return null;
        }
        int indice = (int) (Math.random() * rivales.length);
        return rivales[indice];
    }

    public void crearPrecargados() throws IOException {
        File archivoPoke = new File(ArchivoPokemones);
        File archivoRival = new File(ArchivoRivales);
        if (archivoPoke.exists() && archivoPoke.length() > 0
                && archivoRival.exists() && archivoRival.length() > 0) {
            return;
        }

        ListaEnlazada catalogo = PreCarga.crearCatalogoBase();
        guardarCatalogo(catalogo);

        Entrenador[] rivales = PreCarga.crearRivalesBase(catalogo);
        guardarRivales(rivales);
    }
}
