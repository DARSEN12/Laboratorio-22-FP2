public class EspadachinReal extends Espadachin {
    private int numeroCuchillos;
    private double tamanoCuchillos;
    private int nivelEvolucion;

    public EspadachinReal(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);
        this.numeroCuchillos = 5; // Número inicial de cuchillos
        this.tamanoCuchillos = 1.0; // Tamaño inicial de cuchillos
        this.nivelEvolucion = 1; // Nivel de evolución inicial
    }

    // Método para lanzar cuchillos
    public void lanzarCuchillo() {
        if (numeroCuchillos > 0) {
            System.out.println(nombre + " lanza un cuchillo de tamaño " + tamanoCuchillos + "!");
            numeroCuchillos--;
        } else {
            System.out.println(nombre + " no tiene más cuchillos.");
        }
    }

    // Método para evolucionar al EspadachinReal
    public void evolucionar() {
        if (nivelEvolucion < 4) {
            nivelEvolucion++;
            numeroCuchillos += 2; // Aumenta el número de cuchillos al evolucionar
            tamanoCuchillos += 0.5; // Aumenta el tamaño de los cuchillos al evolucionar
            System.out.println(nombre + " ha evolucionado a nivel " + nivelEvolucion + "!");
            System.out.println("Ahora tiene " + numeroCuchillos + " cuchillos de tamaño " + tamanoCuchillos);
        } else {
            System.out.println(nombre + " ya ha alcanzado el nivel máximo de evolución.");
        }
    }
}

