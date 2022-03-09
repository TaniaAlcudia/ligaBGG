package game;

public class Juego {

    private String nombre;
    private int numeroJugadores;

    public Juego(final String nombre, final int numeroJugadores) {

        this.nombre = nombre;
        this.numeroJugadores = numeroJugadores;

    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroJugadores() {
        return numeroJugadores;
    }

    @Override
    public String toString() {
        return nombre +" [" + numeroJugadores + " jugadores]";
    }
}
