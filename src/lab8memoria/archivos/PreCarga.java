package lab8memoria.archivos;

import lab8memoria.modelo.Ataque;
import lab8memoria.modelo.Entrenador;
import lab8memoria.modelo.ListaEnlazada;
import lab8memoria.modelo.Pokemon;

public class PreCarga {

    public static ListaEnlazada crearCatalogoBase() {
        ListaEnlazada catalogo = new ListaEnlazada();

        Pokemon flamitor = new Pokemon("Charizard", "Fuego", 8, 40);
        flamitor.agregarAtaque(new Ataque("Llamarada", "Fuego", 35));
        flamitor.agregarAtaque(new Ataque("Arañazo", "Normal", 15));
        catalogo.insertar(flamitor);

        Pokemon rapidash = new Pokemon("Rapidash", "Fuego", 10, 45);
        rapidash.agregarAtaque(new Ataque("Bola de Fuego", "Fuego", 30));
        rapidash.agregarAtaque(new Ataque("Placaje", "Normal", 12));
        catalogo.insertar(rapidash);

        Pokemon blastoise = new Pokemon("Blastoise", "Agua", 9, 42);
        blastoise.agregarAtaque(new Ataque("Hidrobomba", "Agua", 32));
        blastoise.agregarAtaque(new Ataque("Placaje", "Normal", 12));
        catalogo.insertar(blastoise);

        Pokemon poliwrath = new Pokemon("Poliwrath", "Agua", 11, 50);
        poliwrath.agregarAtaque(new Ataque("Chorro de Agua", "Agua", 28));
        poliwrath.agregarAtaque(new Ataque("Golpe Cuerpo", "Normal", 18));
        catalogo.insertar(poliwrath);

        Pokemon venusaur = new Pokemon("Venusaur", "Planta", 9, 43);
        venusaur.agregarAtaque(new Ataque("Latigo Cepa", "Planta", 30));
        venusaur.agregarAtaque(new Ataque("Placaje", "Normal", 12));
        catalogo.insertar(venusaur);

        Pokemon pikachu = new Pokemon("Pikachu", "Electrico", 10, 38);
        pikachu.agregarAtaque(new Ataque("Impactrueno", "Electrico", 33));
        pikachu.agregarAtaque(new Ataque("Placaje", "Normal", 12));
        catalogo.insertar(pikachu);

        Pokemon gengar = new Pokemon("Gengar", "Fantasma", 12, 36);
        gengar.agregarAtaque(new Ataque("Puno Sombra", "Fantasma", 34));
        gengar.agregarAtaque(new Ataque("Arañazo", "Normal", 15));
        catalogo.insertar(gengar);

        Pokemon rattata = new Pokemon("Rattata", "Normal", 7, 46);
        rattata.agregarAtaque(new Ataque("Golpe Cuerpo", "Normal", 20));
        rattata.agregarAtaque(new Ataque("Placaje", "Normal", 12));
        catalogo.insertar(rattata);

        return catalogo;
    }

    public static Entrenador[] crearRivalesBase(ListaEnlazada catalogo) {
        Entrenador[] rivales = new Entrenador[10];
        rivales[0] = crearRival(catalogo, "Azul", "Charizard", "rattata");
        rivales[1] = crearRival(catalogo, "Plata", "blastoise", "venusaur");
        rivales[2] = crearRival(catalogo, "Bruna", "pikachu", "gengar");
        rivales[3] = crearRival(catalogo, "Aura", "poliwrath", "rapidash");
        rivales[4] = crearRival(catalogo, "Blasco", "rattata", "blastoise");
        rivales[5] = crearRival(catalogo, "Bel", "venusaur", "pikachu");
        rivales[6] = crearRival(catalogo, "N", "gengar", "Charizard");
        rivales[7] = crearRival(catalogo, "Serena", "rapidash", "poliwrath");
        rivales[8] = crearRival(catalogo, "Cheren", "pikachu", "rattata");
        rivales[9] = crearRival(catalogo, "Xana", "gengar", "blastoise");
        return rivales;
    }

    private static Entrenador crearRival(ListaEnlazada catalogo, String nombre, String nombrePoke1, String nombrePoke2) {
        Entrenador rival = new Entrenador(nombre);
        Pokemon base1 = catalogo.buscar(nombrePoke1);
        if (base1 != null) {
            rival.agregarPokemon(clonarPokemon(base1));
        }
        Pokemon base2 = catalogo.buscar(nombrePoke2);
        if (base2 != null) {
            rival.agregarPokemon(clonarPokemon(base2));
        }
        return rival;
    }

    private static Pokemon clonarPokemon(Pokemon original) {
        Pokemon copia = new Pokemon(original.getNombre(), original.getTipo(), original.getNivel(),
                original.getHpMaximo());
        for (int i = 0; i < original.cantidadAtaques(); i++) {
            copia.agregarAtaque(original.obtenerAtaque(i));
        }
        return copia;
    }
}
