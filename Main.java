import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Mapa mapa = new Mapa(10, 10);

        Ejercito ejercito1 = new Ejercito("Inglaterra");
        Ejercito ejercito2 = new Ejercito("Francia");
        ejercito1.generarSoldados(5);
        ejercito2.generarSoldados(5);

        for (Soldado soldado : ejercito1.getSoldados()) {
            mapa.colocarSoldado(soldado);
        }

        for (Soldado soldado : ejercito2.getSoldados()) {
            mapa.colocarSoldado(soldado);
        }

        JuegoDeBatalla juego = new JuegoDeBatalla(mapa, ejercito1, ejercito2);
        juego.iniciar();
    }
}
