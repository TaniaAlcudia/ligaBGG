package game;

import java.util.ArrayList;

public class Mesa {

    private ArrayList<Jugador> jugadores;
    private int maximo;

    public Mesa(int maximo) {
        this.jugadores = new ArrayList<>();
        this.maximo = maximo;
    }

    public boolean inscribir(final Jugador jugador) {
        if(this.llena() || jugadores.contains(jugador))
            return false;

        this.jugadores.add(jugador);
        return true;
    }

    public Jugador getJugador(int i) {
        return this.jugadores.get(i);
    }

    public boolean llena() {
        return this.jugadores.size() >= maximo;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
}
