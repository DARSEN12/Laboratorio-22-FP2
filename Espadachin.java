public class Espadachin extends Soldado {
    private double longitudEspada;

    public Espadachin(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna, double longitudEspada) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);
        this.longitudEspada = longitudEspada;
    }

    @Override
    public void accionEspecial() {
        System.out.println(nombre + " crea un muro de escudos, aumentando defensa en 3 puntos.");
        this.defensa += 3;
    }
}
