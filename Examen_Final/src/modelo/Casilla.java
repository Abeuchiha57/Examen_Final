package modelo;

public class Casilla {
    private boolean mina;
    private boolean descubierta;
    private boolean marcada;
    private int minasAdyacentes;

    public Casilla() {
        mina = false;
        descubierta = false;
        marcada = false;
        minasAdyacentes = 0;
    }

    public boolean esMina() {
        return mina;
    }

    public void ponerMina() {
        mina = true;
    }

    public boolean estaDescubierta() {
        return descubierta;
    }

    public void descubrir() {
        descubierta = true;
    }

    public void marcar() {
        marcada = !marcada;
    }

    public void setMinasAdyacentes(int minas) {
        minasAdyacentes = minas;
    }

    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    @Override
    public String toString() {
        if (descubierta) {
            if (mina) {
                return "X"; // Mina descubierta
            } else if (minasAdyacentes == 0) {
                return "V"; // Espacio vacío seleccionado
            } else {
                return String.valueOf(minasAdyacentes); // Número de minas adyacentes
            }
        }
        return marcada ? "M" : "#"; // Casilla marcada o no descubierta
    }
}
