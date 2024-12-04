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
            String nombre = "Soldado " + i + " (" + nombreReino + ")";
    
            switch (tipo) {
                case 0 -> soldado = new Espadachin(
                    nombre, 
                    random.nextInt(3) + 8, // Vida [8..10]
                    10, // Ataque
                    8,  // Defensa
                    random.nextInt(10), 
                    random.nextInt(10)
                );
                case 1 -> soldado = new Arquero(
                    nombre, 
                    random.nextInt(3) + 3, // Vida [3..5]
                    7,  // Ataque
                    3,  // Defensa
                    random.nextInt(10), 
                    random.nextInt(10), 
                    random.nextInt(10) // Número de flechas
                );
                case 2 -> soldado = new Caballero(
                    nombre, 
                    random.nextInt(3) + 10, // Vida [10..12]
                    13, // Ataque
                    7,  // Defensa
                    random.nextInt(10), 
                    random.nextInt(10), 
                    random.nextBoolean() // Montado o no
                );
                case 3 -> soldado = new Lancero(
                    nombre, 
                    random.nextInt(3) + 5, // Vida [5..8]
                    5,  // Ataque
                    10, // Defensa
                    random.nextInt(10), 
                    random.nextInt(10), 
                    random.nextDouble() * 2 + 1 // Longitud de lanza
                );
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

    public void aplicarBeneficios(String territorio) {
        boolean tieneBeneficio = switch (this.nombreReino) {
            case "Inglaterra" -> territorio.equals("Bosque");
            case "Francia" -> territorio.equals("Campo Abierto");
            case "Castilla-Aragón" -> territorio.equals("Montaña");
            case "Moros" -> territorio.equals("Desierto");
            case "Sacro Imperio Romano-Germánico" -> 
                territorio.equals("Bosque") || territorio.equals("Playa") || territorio.equals("Campo Abierto");
            default -> false;
        };
    
        if (tieneBeneficio) {
            for (Soldado soldado : soldados) {
                soldado.aumentarVida(1);
            }
            System.out.println("El ejército de " + nombreReino + " recibe beneficios por el territorio: " + territorio);
        }
    }
    
}
