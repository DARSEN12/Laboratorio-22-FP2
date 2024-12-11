import java.util.*;

public class Batalla {

    public static Soldado enfrentar(Soldado soldado1, Soldado soldado2) {
        int sumaVida = soldado1.getNivelVida() + soldado2.getNivelVida();

        Random random = new Random();
        double resultado = random.nextDouble() * sumaVida; // Multiplicamos directamente por la suma de las vidas

        if (resultado < soldado1.getNivelVida()) {
            soldado1.nivelVida += 1;  // Incremento directo de la propiedad
            System.out.println(soldado1.getNombre() + " gana la batalla contra " + soldado2.getNombre());
            return soldado1;
        } else {
            soldado2.nivelVida += 1;  // Incremento directo de la propiedad
            System.out.println(soldado2.getNombre() + " gana la batalla contra " + soldado1.getNombre());
            return soldado2;
        }
    }

    public static boolean verificarReglasBatalla(Soldado soldado1, Soldado soldado2) {
        if (soldado1 instanceof Caballero) {
            if (soldado2 instanceof Arquero || soldado2 instanceof Espadachin) {
                soldado1.nivelVida += 1; // Incremento directo de la propiedad
            } else if (soldado2 instanceof Lancero) {
                soldado2.nivelVida += 1; // Incremento directo de la propiedad
            }
        } else if (soldado1 instanceof Arquero && soldado2 instanceof Lancero) {
            soldado1.nivelVida += 1; // Incremento directo de la propiedad
        } else if (soldado1 instanceof Espadachin && soldado2 instanceof Lancero) {
            soldado1.nivelVida += 1; // Incremento directo de la propiedad
        }
        return true;
    }
}
