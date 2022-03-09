package io;

import exception.GameFilenotFoundException;
import exception.PlayerFileNotFoundException;
import exception.TournamentException;
import game.Juego;
import game.Jugador;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;


/**
 * Clase que se encarga de leer de los archivos de jugadores y juegos
 */
public class LectorArchivos {

    public static String ARCHIVO_JUGADORES = "jugadores.txt";
    public static String ARCHIVO_JUEGOS = "juegos.txt";


    /**
     * Devuelve los jugadores del archivo de jugadores
     * @return una lista de jugadores
     * @throws PlayerFileNotFoundException si no se encuentra el archivo de jugadores
     */
    public static ArrayList<Jugador> leerJugadoresDeArchivo() throws TournamentException {
        final ArrayList<Jugador> jugadores = new ArrayList<>();
        URL datos = LectorArchivos.class.getResource("/" + ARCHIVO_JUGADORES);

        // Si no existe el archivo lanza la excepcion.
        if(datos == null){
            throw new PlayerFileNotFoundException("No se ha podido encontrar el archivo de jugadores o este está mal formado.");
        }

        // Lee del archivo
        try {
            final BufferedReader lector = new BufferedReader(new FileReader(datos.getPath()));

            // Por cada linea crea un jugador y lo añade al ArrayList
            String linea = lector.readLine();
            while(linea != null) {
                Jugador jugador = new Jugador(linea.trim());
                jugadores.add(jugador);
                linea = lector.readLine();
            }

            lector.close();
        } catch (FileNotFoundException e) {
            throw new PlayerFileNotFoundException("No se ha podido encontrar el archivo de jugadores o este está mal formado.");
        } catch (IOException e) {
            throw new TournamentException("Un error desconocido sucedió con el archivo de juagdores, no se puedo cerrar.");
        }

        return jugadores;
    }

    /**
     * Devuelve los juegos del archivo de juegos
     * @return una lista de juegos
     */
    public static ArrayList<Juego> leerJuegosDeArchivo() throws TournamentException {
        final ArrayList<Juego> juegos = new ArrayList<>();
        URL datos = LectorArchivos.class.getResource("/" + ARCHIVO_JUEGOS);

        // Si no existe el archivo lanza la excepcion.
        if(datos == null){
            throw new GameFilenotFoundException("No se ha podido encontrar el archivo de juegos o este está mal formado.");
        }

        // Lee del archivo
        try {
            final BufferedReader lector = new BufferedReader(new FileReader(datos.getPath()));
            int lineaActual = 0;

            // Por cada linea crea un juego y lo añade al ArrayList
            String linea = lector.readLine();;
            while(linea != null) {

                //Sumamos la linea
                lineaActual++;

                // Corta el string en ":" y busca el nombre del juego y sus jugadores
                final String[] corte = linea.split(":");

                // Si la linea no esta bien, se la salta
                if(corte.length < 2) {
                    linea = lector.readLine();
                    System.out.println("Datos de juego mal formados en la línea " + lineaActual + ". \nsaltando...");
                    continue;
                }

                // Si no intenta parsear el numero de jugadores
                // y si falla, se salta el juego
                final String nombreJuego = corte[0].trim();
                Integer numeroJugadores = 0 ;
                try {
                    numeroJugadores = Integer.parseInt(corte[1].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Datos de juego mal formados en la línea " + lineaActual + ". Juego : " + nombreJuego + ". \nsaltando...");
                    linea = lector.readLine();
                    continue;
                }

                final Juego juego = new Juego(nombreJuego,numeroJugadores);
                juegos.add(juego);
                linea = lector.readLine();
            }

            lector.close();
        } catch (FileNotFoundException e) {
            throw new GameFilenotFoundException("No se ha podido encontrar el archivo de juegos o este está mal formado.");
        } catch (IOException e) {
            throw new TournamentException("Un error desconocido sucedió con el archivo de juegos, no se puedo cerrar.");
        }
        return juegos;
    }
}
