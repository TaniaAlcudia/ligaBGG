package exception;

/**
 * Excepción que destaca que el archivo de jugadores no ha sido encontrado
 */
public class PlayerFileNotFoundException extends TournamentException{

    public PlayerFileNotFoundException(String message) {
        super(message);
    }
}
