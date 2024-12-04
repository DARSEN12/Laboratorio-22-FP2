import java.util.ArrayList;
import java.util.Random;

public class Ejercito {
    private String nombreReino;
    private ArrayList<Soldado> soldados;

    public Ejercito(String nombreReino) {
        this.nombreReino = nombreReino;
        this.soldados = new ArrayList<>();
    }

    public void generarSoldados(int cantidad) {
        Random random = new Random();
        for (int i = 0; i < cantidad; i++) {
            int tipo = random.nextInt(4); // 0: Espadachin, 1: Arquero, 2: Caballero, 3: Lancero
            Soldado soldado;
            String nombre = "Soldado" + i;

            switch (tipo) {
                case 0 -> soldado = new Espadachin(nombre, 9, 10, 8, -1, -1, random.nextDouble() * 1.5);
                case 1 -> soldado = new Arquero(nombre, 4, 7, 3, -1, -1, random.nextInt(10));
                case 2 -> soldado = new Caballero(nombre, 11, 13, 7, -1, -1, random.nextBoolean());
                case 3 -> soldado = new Lancero(nombre, 6, 5, 10, -1, -1, random.nextDouble() * 2);
                default -> throw new IllegalArgumentException("Tipo de soldado desconocido.");
            }

            soldados.add(soldado);
        }
    }

    public ArrayList<Soldado> getSoldados() {
        return soldados;
    }
}
