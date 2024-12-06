import java.util.*;
public class JuegoDeBatalla {
    private final Ejercito ejercito1;
    private final Ejercito ejercito2;
    private final Mapa mapa;
    private final Scanner scanner;

    public JuegoDeBatalla(Mapa mapa, Ejercito ejercito1, Ejercito ejercito2) {
        this.mapa = mapa;
        this.ejercito1 = ejercito1;
        this.ejercito2 = ejercito2;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarPosicionesSoldados(Ejercito ejercitoOponente) {
        System.out.println("Posiciones de los soldados del ejército oponente:");
        for (Soldado soldado : ejercitoOponente.getSoldados()) {
            System.out.printf("%s está en la posición (%d, %d)%n",
                              soldado.getNombre(), soldado.getFila(), soldado.getColumna());
        }
    }
    
    public void iniciar() {
        System.out.println("¡Bienvenidos al campo de batalla!");
        boolean juegoActivo = true;
        boolean turnoJugador1 = true;
    
        while (juegoActivo) {
            mapa.mostrarMapa(ejercito1, ejercito2);
            System.out.println("\nTurno del jugador " + (turnoJugador1 ? "1" : "2"));
    
            Ejercito ejercitoActual = turnoJugador1 ? ejercito1 : ejercito2;
            Ejercito ejercitoOponente = turnoJugador1 ? ejercito2 : ejercito1;
    
            if (!ejercitoActual.isEvolucionRealizada()) {
                ejercitoActual.intentarEvolucionar();
            }
    
            System.out.println("\nSoldados disponibles:");
            for (int i = 0; i < ejercitoActual.getSoldados().size(); i++) {
                System.out.println((i + 1) + ". " + ejercitoActual.getSoldados().get(i));
            }
    
            System.out.print("Selecciona el soldado a mover (número): ");
            int seleccion = scanner.nextInt() - 1;
    
            if (seleccion < 0 || seleccion >= ejercitoActual.getSoldados().size()) {
                System.out.println("Selección inválida. Pierdes tu turno.");
                turnoJugador1 = !turnoJugador1;
                continue;
            }
    
            Soldado soldadoSeleccionado = ejercitoActual.getSoldados().get(seleccion);
    
            mostrarPosicionesSoldados(ejercitoOponente);
    
            System.out.print("Ingresa la nueva fila: ");
            int nuevaFila = scanner.nextInt();
            System.out.print("Ingresa la nueva columna: ");
            int nuevaColumna = scanner.nextInt();
    
            if (mapa.esMovimientoValido(soldadoSeleccionado, nuevaFila, nuevaColumna)) {
                Soldado oponente = mapa.obtenerSoldadoEnPosicion(nuevaFila, nuevaColumna);
                if (oponente != null) {
                    System.out.println("¡Batalla entre " + soldadoSeleccionado.getNombre() + " y " + oponente.getNombre() + "!");
                    Soldado ganador = Batalla.enfrentar(soldadoSeleccionado, oponente);
                    if (ganador == soldadoSeleccionado) {
                        mapa.limpiarPosicion(oponente.getFila(), oponente.getColumna());
                        ejercitoOponente.getSoldados().remove(oponente);
                    } else {
                        mapa.limpiarPosicion(soldadoSeleccionado.getFila(), soldadoSeleccionado.getColumna());
                        ejercitoActual.getSoldados().remove(soldadoSeleccionado);
                    }
                } else {
                    mapa.moverSoldado(soldadoSeleccionado, nuevaFila, nuevaColumna);
                }
            } else {
                System.out.println("Movimiento inválido. Pierdes tu turno.");
            }
    
            if (ejercito1.getSoldados().isEmpty()) {
                System.out.println("\n¡El jugador 2 ha ganado la batalla!");
                juegoActivo = false;
            } else if (ejercito2.getSoldados().isEmpty()) {
                System.out.println("\n¡El jugador 1 ha ganado la batalla!");
                juegoActivo = false;
            } else {
                turnoJugador1 = !turnoJugador1;
            }
        }
        System.out.println("Juego terminado.");
    }  
}
