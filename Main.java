import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Mapa mapa = new Mapa(10, 10);

        String[] reinosSeleccionados = Reino.escogerReinos();

        System.out.println("Jugador 1 ha elegido: " + reinosSeleccionados[0]);

        System.out.println("------------------------------------------");

        System.out.println("Jugador 2 ha elegido: " + reinosSeleccionados[1]);

        System.out.println("------------------------------------------");

        Ejercito ejercito1 = new Ejercito(reinosSeleccionados[0]);
        ejercito1.generarSoldados(3);

        Ejercito ejercito2 = new Ejercito(reinosSeleccionados[1]);
        ejercito2.generarSoldados(3);

        ejercito1.mostrarSoldados();
        System.out.println("------------------------------------------");

        ejercito2.mostrarSoldados();
        System.out.println("------------------------------------------");

        String territorio = Territorio.generarTerritorio();
        System.out.println("El territorio actual es: " + territorio);

        ejercito1.aplicarBeneficios(territorio);
        ejercito2.aplicarBeneficios(territorio);

        for (Soldado soldado : ejercito1.getSoldados()) {
            mapa.colocarSoldado(soldado);
        }

        for (Soldado soldado : ejercito2.getSoldados()) {
            mapa.colocarSoldado(soldado);
        }

        JuegoDeBatalla juego = new JuegoDeBatalla(mapa, ejercito1, ejercito2);
        juego.iniciar();

        scanner.close();
    }
}
