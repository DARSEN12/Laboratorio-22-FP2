public class Arquero extends Soldado {
    private int flechasDisponibles;

    // Constructor con un mensaje de inicialización
    public Arquero(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna, int flechasDisponibles) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);
        this.flechasDisponibles = flechasDisponibles;
        System.out.println("El arquero " + nombre + " ha sido creado con " + flechasDisponibles + " flechas disponibles.");
    }

    @Override
    public void accionEspecial() {
        if (flechasDisponibles > 0) {
            // Mensaje más claro con la acción especial
            System.out.println(nombre + " dispara una flecha y aumenta su ataque en 5 puntos.");
            this.ataque += 5;
            flechasDisponibles--;
            System.out.println("Flechas restantes: " + flechasDisponibles); // Mensaje sobre las flechas restantes
        } else {
            // Mensaje claro sobre la falta de flechas
            System.out.println(nombre + " no tiene flechas disponibles para disparar.");
        }
    }
}
