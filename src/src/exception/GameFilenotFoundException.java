package exception;

/**
 * Excepción que destaca que el archivo de juegos no ha sido encontrado
 */
public class GameFilenotFoundException extends TournamentException {
    public GameFilenotFoundException(String message) {
        super(message);
    }
}
