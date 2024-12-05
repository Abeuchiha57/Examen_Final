package modelo.excepciones;

public class CasillaYaDescubiertaException extends ExcepcionJuego {
    public CasillaYaDescubiertaException() {
        super("La casilla ya ha sido descubierta. Selecciona otra casilla.");
    }
}
