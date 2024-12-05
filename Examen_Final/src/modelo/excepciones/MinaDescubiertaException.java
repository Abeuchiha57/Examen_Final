package modelo.excepciones;

public class MinaDescubiertaException extends ExcepcionJuego {
    public MinaDescubiertaException() {
        super("¡Has perdido! Pisaste una mina.");
    }
}
