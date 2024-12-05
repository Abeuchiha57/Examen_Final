package modelo;

import modelo.excepciones.CoordenadaInvalidaException;
import modelo.excepciones.CasillaYaDescubiertaException;
import modelo.excepciones.ExcepcionJuego;
import modelo.excepciones.MinaDescubiertaException;

import java.util.Random;

//Atributos de la clase
public class Tablero {
	private final Casilla[][] casillas;
	private final int filas;
	private final int columnas;
	private final int minas;
	private int casillasSeguras;

	// Constructor
	public Tablero(int filas, int columnas, int minas) {
		this.filas = filas;
		this.columnas = columnas;
		this.minas = minas;
		this.casillasSeguras = filas * columnas - minas;
		casillas = new Casilla[filas][columnas];
	}

	// Metodos
	public void inicializar() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				casillas[i][j] = new Casilla();
			}
		}
		colocarMinas();
		calcularAdyacentes();
	}

	private void colocarMinas() {
		Random random = new Random();
		int colocadas = 0;

		while (colocadas < minas) {
			int fila = random.nextInt(filas);
			int columna = random.nextInt(columnas);

			if (!casillas[fila][columna].esMina()) {
				casillas[fila][columna].ponerMina();
				colocadas++;
			}
		}
	}

	private void calcularAdyacentes() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (!casillas[i][j].esMina()) {
					casillas[i][j].setMinasAdyacentes(contarMinasAdyacentes(i, j));
				}
			}
		}
	}

	private int contarMinasAdyacentes(int fila, int columna) {
		int minas = 0;
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				int nuevaFila = fila + x;
				int nuevaColumna = columna + y;
				if (dentroLimites(nuevaFila, nuevaColumna) && casillas[nuevaFila][nuevaColumna].esMina()) {
					minas++;
				}
			}
		}
		return minas;
	}

	private boolean dentroLimites(int fila, int columna) {
		return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
	}

	public void descubrir(String coordenada) throws ExcepcionJuego {
		int[] indices = convertirCoordenada(coordenada);
		int fila = indices[0];
		int columna = indices[1];

		if (casillas[fila][columna].estaDescubierta()) {
			throw new CasillaYaDescubiertaException();
		}

		casillas[fila][columna].descubrir();

		if (casillas[fila][columna].esMina()) {
			throw new MinaDescubiertaException();
		}

		casillasSeguras--;

		if (casillas[fila][columna].getMinasAdyacentes() == 0) {
			revelarAdyacentes(fila, columna);
		}
	}

	private void revelarAdyacentes(int fila, int columna) {
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				int nuevaFila = fila + x;
				int nuevaColumna = columna + y;
				if (dentroLimites(nuevaFila, nuevaColumna) && !casillas[nuevaFila][nuevaColumna].estaDescubierta()) {
					try {
						descubrir(convertirCoordenada(nuevaFila, nuevaColumna));
					} catch (ExcepcionJuego e) {
						// Ignorar excepciones durante la revelación automática
					}
				}
			}
		}
	}

	public void marcar(String coordenada) throws ExcepcionJuego {
		int[] indices = convertirCoordenada(coordenada);
		int fila = indices[0];
		int columna = indices[1];

		if (casillas[fila][columna].estaDescubierta()) {
			throw new CasillaYaDescubiertaException();
		}

		casillas[fila][columna].marcar();
	}

	public boolean victoria() {
		return casillasSeguras == 0;
	}

	// Getters y Setters
	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public Casilla getCasilla(int fila, int columna) {
		return casillas[fila][columna];
	}

	private int[] convertirCoordenada(String coordenada) throws ExcepcionJuego {
		if (coordenada.length() < 2) {
			throw new CoordenadaInvalidaException();
		}

		char filaChar = coordenada.charAt(0);
		int columna;
		try {
			columna = Integer.parseInt(coordenada.substring(1)) - 1;
		} catch (NumberFormatException e) {
			throw new CoordenadaInvalidaException();
		}

		int fila = filaChar - 'A';

		if (!dentroLimites(fila, columna)) {
			throw new CoordenadaInvalidaException();
		}

		return new int[] { fila, columna };
	}

	private String convertirCoordenada(int fila, int columna) {
		return (char) ('A' + fila) + "" + (columna + 1);
	}
}
