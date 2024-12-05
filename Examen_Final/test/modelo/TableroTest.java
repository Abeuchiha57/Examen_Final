package modelo;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableroTest {

    @Test
    void testInicializacionTablero() {
        Tablero tablero = new Tablero(5, 5, 3);
        tablero.inicializar();
        assertEquals(5, tablero.getFilas());
        assertEquals(5, tablero.getColumnas());
    }

    @Test
    void testColocarMinas() {
        Tablero tablero = new Tablero(5, 5, 3);
        tablero.inicializar();
        int minasColocadas = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (tablero.getCasilla(i, j).esMina()) {
                    minasColocadas++;
                }
            }
        }
        assertEquals(3, minasColocadas);
    }

    
}
