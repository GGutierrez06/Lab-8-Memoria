package lab8memoria.modelo;

public class Pokemon {

    private String nombre;
    private String tipo;
    private int nivel;
    private int hp;
    private int hpMaximo;
    private Ataque[] ataques;
    private int cantidadAtaques;

    public Pokemon(String nombre, String tipo, int nivel, int hpMaximo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.hpMaximo = hpMaximo;
        this.hp = hpMaximo;
        this.ataques = new Ataque[4];
        this.cantidadAtaques = 0;
    }

    public boolean agregarAtaque(Ataque ataque) {
        if (ataque == null || cantidadAtaques >= 4) {
            return false;
        }
        ataques[cantidadAtaques] = ataque;
        cantidadAtaques++;
        return true;
    }

    public Ataque obtenerAtaque(int indice) {
        if (indice < 0 || indice >= cantidadAtaques) {
            return null;
        }
        return ataques[indice];
    }

    public int cantidadAtaques() {
        return cantidadAtaques;
    }

    public void recibirDanio(int danio) {
        if (danio <= 0) {
            return;
        }
        hp = hp - danio;
        if (hp < 0) {
            hp = 0;
        }
    }

    public void curar(int puntos) {
        if (puntos <= 0 || estaDerrotado()) {
            return;
        }
        hp = hp + puntos;
        if (hp > hpMaximo) {
            hp = hpMaximo;
        }
    }

    public void revivir() {
        if (!estaDerrotado()) {
            return;
        }
        hp = hpMaximo / 2;
        if (hp < 1) {
            hp = 1;
        }
    }

    public boolean estaDerrotado() {
        return hp <= 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        if (nivel > 0) {
            this.nivel = nivel;
        }
    }

    public int getHp() {
        return hp;
    }

    public int getHpMaximo() {
        return hpMaximo;
    }

    public void setHpMaximo(int hpMaximo) {
        if (hpMaximo <= 0) {
            return;
        }
        this.hpMaximo = hpMaximo;
        if (hp > hpMaximo) {
            hp = hpMaximo;
        }
    }

    public String toString() {
        String texto = nombre + " Nivel " + nivel + " " + tipo + " " + hp + "/" + hpMaximo;
        if (estaDerrotado()) {
            texto = texto + " DERROTADO";
        }
        return texto;
    }
}
