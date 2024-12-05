package modelo.excepciones;

public class CoordenadaInvalidaException extends ExcepcionJuego {
    public CoordenadaInvalidaException() {
        super("La coordenada ingresada es inválida. Asegúrate de que esté en el formato correcto (ejemplo: A5) y dentro de los límites del tablero.");
    }
}
