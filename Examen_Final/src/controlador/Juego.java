package controlador;

import java.util.Scanner;

import modelo.Tablero;

public class Juego {
    private final Tablero tablero;
    private boolean enJuego;

    public Juego() {
        // Crear un tablero 10x10 con 10 minas
        tablero = new Tablero(10, 10, 10);
        enJuego = true;
    }

    public void iniciar() {
        // Inicializar el tablero
        tablero.inicializar();

        // Crear un lector para entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Bucle principal del juego
        while (enJuego) {
            // Mostrar el tablero actualizado
            tablero.mostrar();

            // Pedir al usuario que introduzca una acción
            System.out.println("Introduce coordenadas (ejemplo: A5) o marca con 'M A5':");
            String entrada = scanner.nextLine().toUpperCase();

            try {
                if (entrada.startsWith("M")) {
                    // Si la entrada comienza con "M", marcar la casilla
                    tablero.marcar(entrada.substring(2));
                } else {
                    // De lo contrario, descubrir la casilla
                    tablero.descubrir(entrada);

                    // Verificar si el jugador ha ganado
                    if (tablero.victoria()) {
                        tablero.mostrar();
                        System.out.println("¡Felicidades, ganaste!");
                        enJuego = false; // Finalizar el juego
                    }
                }
            } catch (ExcepcionJuego e) {
                // Mostrar el mensaje de cualquier excepción personalizada
                System.out.println(e.getMessage());
            }
        }

        scanner.close(); // Cerrar el lector de entrada
    }

    public static void main(String[] args) {
        // Crear e iniciar una nueva instancia del juego
        new Juego().iniciar();
    }
}
