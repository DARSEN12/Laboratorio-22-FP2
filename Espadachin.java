public class Espadachin extends Soldado {
    private double longitudEspada;

    public Espadachin(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna, int numEjercito) {
        super(nombre, nivelVida, ataque, defensa, fila, columna, numEjercito);
    }

    @Override
    public void accionEspecial() {
        System.out.println(nombre + " crea un muro de escudos, aumentando su defensa en 3 puntos.");
        this.defensa += 3;
    }

    public double getLongitudEspada() {
        return longitudEspada;
    }

    public void setLongitudEspada(double longitudEspada) {
        this.longitudEspada = longitudEspada;
    }
}
