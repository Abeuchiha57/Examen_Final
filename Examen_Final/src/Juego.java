import java.util.Scanner;

public class Juego {
    private final Tablero tablero;
    private boolean enJuego;

    public Juego() {
        tablero = new Tablero(10, 10, 10);
        enJuego = true;
    }

    public void iniciar() {
        tablero.inicializar();

        Scanner scanner = new Scanner(System.in);

        // Bucle principal del juego
        while (enJuego) {

        	tablero.mostrar();
            // Pedir al usuario que ingrese sus coordenadas
            System.out.println("Introduce coordenadas (ejemplo: A5) o marca con 'M A5':");
            String entrada = scanner.nextLine().toUpperCase();

            try {
                if (entrada.startsWith("M")) {
                    // Si la entrada comienza con "M", marcar la casilla
                    tablero.marcar(entrada.substring(2));
                } else {
                    // Sino, descubrir la casilla
                    tablero.descubrir(entrada);

                    // Verifica si el jugador ha ganado
                    if (tablero.victoria()) {
                        tablero.mostrar();
                        System.out.println("¡Felicidades, ganaste!");
                        enJuego = false; // Finalizar el juego
                    }
                }
            } catch (ExcepcionJuego e) {
                // Mostrar mensaje de cualquier excepción personalizada
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        // Crear e iniciar una nueva instancia del juego
        new Juego().iniciar();
    }
}
