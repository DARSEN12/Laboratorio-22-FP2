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
            int tipo = random.nextInt(4); 
            Soldado soldado;
            String nombre = "Soldado " + i + " (" + nombreReino + ")";

            switch (tipo) {
                case 0 -> soldado = new Espadachin(nombre, 9, 10, 8, random.nextInt(10), random.nextInt(10));
                case 1 -> soldado = new Arquero(nombre, 4, 7, 3, random.nextInt(10), random.nextInt(10), random.nextInt(10));
                case 2 -> soldado = new Caballero(nombre, 11, 13, 7, random.nextInt(10), random.nextInt(10), random.nextBoolean());
                case 3 -> soldado = new Lancero(nombre, 6, 5, 10, random.nextInt(10), random.nextInt(10), random.nextDouble() * 2);
                default -> throw new IllegalArgumentException("Tipo de soldado desconocido.");
            }

            soldados.add(soldado);
        }
    }

    public ArrayList<Soldado> getSoldados() {
        return soldados;
    }

    public String getNombreReino() {
        return nombreReino;
    }
}
