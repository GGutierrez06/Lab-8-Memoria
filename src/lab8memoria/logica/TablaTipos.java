package lab8memoria.logica;

public class TablaTipos {

    public double multiplicador(String tipoAtaque, String tipoDefensor) {
        if (tipoAtaque.equalsIgnoreCase("Fuego") && tipoDefensor.equalsIgnoreCase("Planta")) {
            return 2.0;
        }
        if (tipoAtaque.equalsIgnoreCase("Fuego") && tipoDefensor.equalsIgnoreCase("Agua")) {
            return 0.5;
        }
        if (tipoAtaque.equalsIgnoreCase("Agua") && tipoDefensor.equalsIgnoreCase("Fuego")) {
            return 2.0;
        }
        if (tipoAtaque.equalsIgnoreCase("Agua") && tipoDefensor.equalsIgnoreCase("Planta")) {
            return 0.5;
        }
        if (tipoAtaque.equalsIgnoreCase("Planta") && tipoDefensor.equalsIgnoreCase("Agua")) {
            return 2.0;
        }
        if (tipoAtaque.equalsIgnoreCase("Planta") && tipoDefensor.equalsIgnoreCase("Fuego")) {
            return 0.5;
        }
        if (tipoAtaque.equalsIgnoreCase("Electrico") && tipoDefensor.equalsIgnoreCase("Agua")) {
            return 2.0;
        }
        if (tipoAtaque.equalsIgnoreCase("Electrico") && tipoDefensor.equalsIgnoreCase("Planta")) {
            return 0.5;
        }
        if (tipoAtaque.equalsIgnoreCase("Fantasma") && tipoDefensor.equalsIgnoreCase("Fantasma")) {
            return 2.0;
        }
        if (tipoAtaque.equalsIgnoreCase("Fantasma") && tipoDefensor.equalsIgnoreCase("Normal")) {
            return 0.0;
        }
        if (tipoAtaque.equalsIgnoreCase("Normal") && tipoDefensor.equalsIgnoreCase("Fantasma")) {
            return 0.0;
        }
        return 1.0;
    }
}
