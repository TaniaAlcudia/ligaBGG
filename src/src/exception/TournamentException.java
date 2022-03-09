package exception;

/**
 * Excepción general del programa, si hay algún problema
 * esta será la excepción que lance o alguna de sus hijas
 * para problemas más específicos.
 */
public class TournamentException extends Exception {
    public TournamentException(String message) {
        super(message);
    }
}
