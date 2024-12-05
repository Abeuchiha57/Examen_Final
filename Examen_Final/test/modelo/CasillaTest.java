package modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CasillaTest {

    @Test
    void testInicializacion() {
        Casilla casilla = new Casilla();
        assertFalse(casilla.esMina());
        assertFalse(casilla.estaDescubierta());
        assertFalse(casilla.esMina());
        assertEquals(0, casilla.getMinasAdyacentes());
    }

    @Test
    void testPonerMina() {
        Casilla casilla = new Casilla();
        casilla.ponerMina();
        assertTrue(casilla.esMina());
    }

    @Test
    void testDescubrir() {
        Casilla casilla = new Casilla();
        casilla.descubrir();
        assertTrue(casilla.estaDescubierta());
    }

    @Test
    void testMinasAdyacentes() {
        Casilla casilla = new Casilla();
        casilla.setMinasAdyacentes(3);
        assertEquals(3, casilla.getMinasAdyacentes());
    }
}

