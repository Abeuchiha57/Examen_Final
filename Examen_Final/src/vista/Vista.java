package vista;

import modelo.Tablero;

import java.util.Scanner;

public class Vista {
    private final Scanner scanner;

    public Vista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarTablero(Tablero tablero) {
        System.out.print("  ");
        for (int i = 1; i <= tablero.getColumnas(); i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < tablero.getFilas(); i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < tablero.getColumnas(); j++) {
                System.out.print(tablero.getCasilla(i, j) + " ");
            }
            System.out.println();
        }
    }

    public String solicitarEntrada(String mensaje) {
        System.out.println(mensaje);
        return scanner.nextLine().toUpperCase();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
