package controlador;

import modelo.Tablero;
import modelo.excepciones.ExcepcionJuego;
import vista.Vista;

public class Juego {
	private final Tablero tablero;
	private final Vista vista;
	private boolean enJuego;

	public Juego() {
		// Crear el tablero y la vista
		tablero = new Tablero(10, 10, 10); // Tablero 10x10 con 10 minas
		vista = new Vista();
		enJuego = true;
	}

	public void iniciar() {
		tablero.inicializar();

		// Bucle principal del juego
		while (enJuego) {
			vista.mostrarTablero(tablero);

			String entrada = vista
					.solicitarEntrada("Introduce coordenadas (ejemplo: A5) o marca la casilla con 'M A5':");

			try {
				if (entrada.startsWith("M")) {
					tablero.marcar(entrada.substring(2));
				} else {
					tablero.descubrir(entrada);

					if (tablero.victoria()) {
						vista.mostrarTablero(tablero);
						vista.mostrarMensaje("¡Felicidades, ganaste!");
						enJuego = false;
					}
				}
			} catch (ExcepcionJuego e) {
				vista.mostrarMensaje(e.getMessage());
			}
		}

		vista.mostrarMensaje("¡Gracias por jugar!");
	}

	public static void main(String[] args) {
		new Juego().iniciar();
	}
}
