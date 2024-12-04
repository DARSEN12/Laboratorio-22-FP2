public class Mapa {
    private final int filas;
    private final int columnas;
    private final Soldado[][] mapa;

    public Mapa(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.mapa = new Soldado[filas][columnas];
    }

    public boolean colocarSoldado(Soldado soldado) {
        if (esPosicionValida(soldado.getFila(), soldado.getColumna()) && mapa[soldado.getFila()][soldado.getColumna()] == null) {
            mapa[soldado.getFila()][soldado.getColumna()] = soldado;
            return true;
        }
        return false;
    }

    public boolean moverSoldado(Soldado soldado, int nuevaFila, int nuevaColumna) {
        if (esMovimientoValido(soldado, nuevaFila, nuevaColumna)) {
            limpiarPosicion(soldado.getFila(), soldado.getColumna());
            soldado.mover(nuevaFila, nuevaColumna);
            mapa[nuevaFila][nuevaColumna] = soldado;
            return true;
        }
        return false;
    }

    public void limpiarPosicion(int fila, int columna) {
        if (esPosicionValida(fila, columna)) {
            mapa[fila][columna] = null;
        }
    }

    public boolean esMovimientoValido(Soldado soldado, int nuevaFila, int nuevaColumna) {
        return esPosicionValida(nuevaFila, nuevaColumna) && mapa[nuevaFila][nuevaColumna] == null;
    }

    public Soldado obtenerSoldadoEnPosicion(int fila, int columna) {
        return esPosicionValida(fila, columna) ? mapa[fila][columna] : null;
    }

    public boolean esPosicionValida(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    public void mostrarMapa(Ejercito ejercito1, Ejercito ejercito2) {
        System.out.println("Estado actual del mapa:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Soldado soldado = mapa[i][j];
                if (soldado == null) {
                    System.out.print("[   ] ");
                } else {
                    String inicialEjercito = ejercito1.getSoldados().contains(soldado) ? "1" : "2";
                    String tipoSoldado = soldado.getClass().getSimpleName().substring(0, 1); // Primera letra del tipo
                    System.out.print("[" + tipoSoldado + inicialEjercito + "] ");
                }
            }
            System.out.println();
        }
    }
}


