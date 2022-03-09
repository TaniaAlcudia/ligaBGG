import exception.TournamentException;
import game.Juego;
import game.Jugador;
import game.Mesa;
import io.LectorArchivos;

import java.util.ArrayList;

public class Torneo {

    private ArrayList<Jugador> jugadores;
    private ArrayList<Juego> juegos;
    private ArrayList<Mesa> mesas;

    private final int rondas;
    private int rondaActual;

    public Torneo(final int rondas) throws TournamentException {
        this.rondas = rondas;
        this.rondaActual = 0;

        System.out.println("Cargando jugadores..");
        this.jugadores = LectorArchivos.leerJugadoresDeArchivo();
        System.out.println("OK. \n");

        System.out.println("Cargando juegos..");
        this.juegos = LectorArchivos.leerJuegosDeArchivo();
        System.out.println("OK. \n");

        // Crear tres mesas con jugadores aleatorios sin repetir
        System.out.println("__________________ASIGNANDO MESAS___________________");
        this.crearMesas();
        this.rotarJugadoresMesas();
    }

    /**
     * Crear las mesas,
     */
    public void crearMesas() {
        this.mesas = new ArrayList<>();

        final ArrayList<Jugador> insertados = new ArrayList<>();

        final Mesa mesa1 = new Mesa(4);
        final Mesa mesa2 = new Mesa(5);
        final Mesa mesa3 = new Mesa(4);

        mesas.add(mesa1);
        mesas.add(mesa2);
        mesas.add(mesa3);

        jugadores.forEach(jugador -> {

            if(mesasLlenas()) {
                System.out.println("Mesas llenas.");
                return;
            }

            asignarMesaAleatoriamente(jugador);

        });

        System.out.println("Mesa 1: " + mesa1.getJugadores());
        System.out.println("Mesa 2: " + mesa2.getJugadores());
        System.out.println("Mesa 3: " + mesa3.getJugadores());


    }

    /**
     * Devuelve si las mesas están llenas
     * @return si las mesas están llenas.
     */
    public boolean mesasLlenas() {
        boolean llenas = true;
        for (Mesa mesa: mesas) {
            if(!mesa.llena()) {
                llenas = false;
            }
        }
        return llenas;
    }

    public void asignarMesaAleatoriamente(Jugador jugador) {

        int numeroMesa = (int) (Math.random() * mesas.size());
        Mesa mesa = mesas.get(numeroMesa);

        while(mesa.llena() || mesa.inscribir(jugador) == false){
            numeroMesa = (int) (Math.random() * mesas.size());
            mesa = mesas.get(numeroMesa);
        };
    }

    /**
     * Rotar jugadores en mesa
     */
    public void rotarJugadoresMesas() {

        // MESA 1
        // -------------------------------------
        // 1 DE MESA 1
        // 2 DE MESA 2
        // 1 DE MESA 3

        final Mesa mesa1 = mesas.get(0);
        final Mesa mesa2 = mesas.get(1);
        final Mesa mesa3 = mesas.get(2);

        final int aleatorio = (int)(Math.random() * mesa1.getJugadores().size());
        final Jugador jugadorMesa1 = mesa1.getJugador(aleatorio);


       /*
        int aleatorio2 = -1;
        do {
            aleatorio2 = (int)(Math.random() * mesa1.getJugadores().size());
        }  while(aleatorio == aleatorio2);
         final Jugador jug2 = mesa1.getJugador(aleatorio2);s
        */





        // MESA 2
        // -------------------------------------
        // 1 DE MESA 2
        // 2 DE MESA 1
        // 2 DE MESA 3


        // MESA 3
        // -------------------------------------
        // 1 DE MESA 3
        // 2 DE MESA 2
        // 1 DE MESA 1

    }


    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public ArrayList<Juego> getJuegos() {
        return juegos;
    }

    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("|                     TOURNAMENT-PRO 3000 BY TANIA & AKRCK02                         |");
        System.out.println("--------------------------------------------------------------------------------------");

        try {
            final Torneo torneo = new Torneo(8);

            /**
                System.out.println("______________________JUGADORES DISPONIBLES______________________");
                torneo.getJugadores().forEach(jugador -> {
                    System.out.println(jugador);
                });

                System.out.println("\n________________________JUEGOS DISPONIBLES________________________");
                torneo.getJuegos().forEach(juego -> {
                    System.out.println(juego);
                });
            */

        } catch (TournamentException e) {
            System.out.println("[ERROR]" + e.getMessage());
        }

    }

}

