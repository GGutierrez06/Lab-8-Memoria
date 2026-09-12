package lab8memoria.archivos;

import lab8memoria.modelo.Ataque;
import lab8memoria.modelo.Entrenador;
import lab8memoria.modelo.ListaEnlazada;
import lab8memoria.modelo.Pokemon;

public class PreCarga {

    public static ListaEnlazada crearCatalogoBase() {
        ListaEnlazada catalogo = new ListaEnlazada();

        Pokemon charmander = new Pokemon("Charmander", "Fuego", 5, 35);
        charmander.agregarAtaque(new Ataque("Ascuas", "Fuego", 28));
        charmander.agregarAtaque(new Ataque("Arañazo", "Normal", 15));
        catalogo.insertar(charmander);

        Pokemon squirtle = new Pokemon("Squirtle", "Agua", 5, 36);
        squirtle.agregarAtaque(new Ataque("Pistola Agua", "Agua", 28));
        squirtle.agregarAtaque(new Ataque("Placaje", "Normal", 12));
        catalogo.insertar(squirtle);

        Pokemon bulbasaur = new Pokemon("Bulbasaur", "Planta", 5, 39);
        bulbasaur.agregarAtaque(new Ataque("Hoja Afilada", "Planta", 27));
        bulbasaur.agregarAtaque(new Ataque("Placaje", "Normal", 12));
        catalogo.insertar(bulbasaur);

        Pokemon pikachu = new Pokemon("Pikachu", "Electrico", 10, 38);
        pikachu.agregarAtaque(new Ataque("Impactrueno", "Electrico", 33));
        pikachu.agregarAtaque(new Ataque("Placaje", "Normal", 12));
        catalogo.insertar(pikachu);

        return catalogo;
    }

    public static Entrenador[] crearRivalesBase(ListaEnlazada catalogo) {
        Entrenador[] rivales = new Entrenador[10];
        rivales[0] = crearRival(catalogo, "Azul", "charmander", "squirtle");
        rivales[1] = crearRival(catalogo, "Plata", "charmander", "bulbasaur");
        rivales[2] = crearRival(catalogo, "Bruna", "charmander", "pikachu");
        rivales[3] = crearRival(catalogo, "Aura", "squirtle", "bulbasaur");
        rivales[4] = crearRival(catalogo, "Blasco", "squirtle", "pikachu");
        rivales[5] = crearRival(catalogo, "Bel", "bulbasaur", "pikachu");
        rivales[6] = crearRival(catalogo, "N", "charmander", "squirtle");
        rivales[7] = crearRival(catalogo, "Serena", "charmander", "bulbasaur");
        rivales[8] = crearRival(catalogo, "Cheren", "charmander", "pikachu");
        rivales[9] = crearRival(catalogo, "Xana", "squirtle", "bulbasaur");
        return rivales;
    }

    private static Entrenador crearRival(ListaEnlazada catalogo, String nombre, String... nombresPokemon) {
        Entrenador rival = new Entrenador(nombre);
        for (int i = 0; i < nombresPokemon.length; i++) {
            Pokemon base = catalogo.buscar(nombresPokemon[i]);
            if (base != null) {
                rival.agregarPokemon(clonarPokemon(base));
            }
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
