import java.util.Random;

public class Mapa {
    private String tipoTerritorio;
    private Soldado[][] tablero;
    private Random random;

    public Mapa(String tipoTerritorio) {
        this.tipoTerritorio = tipoTerritorio;
        this.tablero = new Soldado[10][10]; // Mapa 10x10
        this.random = new Random();
    }

    public boolean colocarSoldado(Soldado soldado) {
        int fila, columna;
        do {
            fila = random.nextInt(10);
            columna = random.nextInt(10);
        } while (tablero[fila][columna] != null); // Asegurar que la casilla esté vacía

        tablero[fila][columna] = soldado;
        soldado.mover(fila, columna);
        return true;
    }

    public void mostrarMapa() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == null) {
                    System.out.print("[   ] ");
                } else {
                    System.out.print("[" + tablero[i][j].getClass().getSimpleName().charAt(0) + " ] ");
                }
            }
            System.out.println();
        }
    }

    public String getTipoTerritorio() {
        return tipoTerritorio;
    }
}
