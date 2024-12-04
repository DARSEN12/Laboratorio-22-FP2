public class EspadachinReal extends Espadachin {
    private int numeroCuchillos;
    private double tamanoCuchillos;
    private int nivelEvolucion;

    public EspadachinReal(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);
        this.numeroCuchillos = 5; // Inicial
        this.tamanoCuchillos = 1.0; // Inicial
        this.nivelEvolucion = 1;
    }

    public void lanzarCuchillo() {
        if (numeroCuchillos > 0) {
            System.out.println(nombre + " lanza un cuchillo de tamaño " + tamanoCuchillos + "!");
            numeroCuchillos--;
        } else {
            System.out.println(nombre + " no tiene más cuchillos.");
        }
    }

    public void evolucionar() {
        if (nivelEvolucion < 4) {
            nivelEvolucion++;
            numeroCuchillos += 2; // Incremento en cada evolución
            tamanoCuchillos += 0.5; // Incremento en cada evolución
            System.out.println(nombre + " ha evolucionado a nivel " + nivelEvolucion + "!");
        } else {
            System.out.println(nombre + " ya ha alcanzado el nivel máximo de evolución.");
        }
    }
}
