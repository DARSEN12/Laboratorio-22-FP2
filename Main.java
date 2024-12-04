import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicializar mapa
        final int FILAS = 10;
        final int COLUMNAS = 10;
        Mapa mapa = new Mapa(FILAS, COLUMNAS);
        System.out.println("Mapa inicializado con " + FILAS + " filas y " + COLUMNAS + " columnas.");

        // Seleccionar reinos
        String[] reinosSeleccionados = Reino.escogerReinos();
        System.out.println("Jugador 1 ha elegido el reino: " + reinosSeleccionados[0]);
        System.out.println("Jugador 2 ha elegido el reino: " + reinosSeleccionados[1]);

        // Inicializar ejércitos
        Ejercito ejercito1 = new Ejercito(reinosSeleccionados[0]);
        Ejercito ejercito2 = new Ejercito(reinosSeleccionados[1]);

        // Generar soldados para cada ejército
        generarSoldadosParaEjercito(ejercito1, mapa, 5);
        generarSoldadosParaEjercito(ejercito2, mapa, 5);

        // Mostrar estado inicial del mapa
        System.out.println("Estado inicial del mapa:");
        mapa.mostrarMapa(ejercito1, ejercito2);

        // Crear y ejecutar el juego de batalla
        JuegoDeBatalla juego = new JuegoDeBatalla(mapa, ejercito1, ejercito2);
        juego.iniciar();

        scanner.close();
    }

    /**
     * Genera soldados para el ejército y los coloca en el mapa.
     *
     * @param ejercito El ejército al que se le asignarán soldados.
     * @param mapa     El mapa donde se colocarán los soldados.
     * @param cantidad La cantidad de soldados a generar.
     */
    private static void generarSoldadosParaEjercito(Ejercito ejercito, Mapa mapa, int cantidad) {
        try {
            // Generar soldados para el ejército
            ejercito.generarSoldados(cantidad, mapa);

            // Colocar soldados en el mapa
            for (Soldado soldado : ejercito.getSoldados()) {
                if (!mapa.colocarSoldado(soldado)) {
                    throw new IllegalStateException("No se pudo colocar un soldado del ejército " + ejercito.getNombreReino() + " en el mapa.");
                }
            }
            System.out.println("Soldados generados para el ejército de " + ejercito.getNombreReino() + ".");
        } catch (IllegalStateException e) {
            System.err.println("Error al generar soldados para el ejército de " + ejercito.getNombreReino() + ": " + e.getMessage());
            System.exit(1);
        }
    }
}

