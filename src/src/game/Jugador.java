package game;

public class Jugador {

    private String nombre;

    public Jugador(final String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
