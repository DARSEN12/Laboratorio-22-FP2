import java.util.*;

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
                case 0 -> soldado = new Espadachin(
                    nombre, 
                    random.nextInt(3) + 8, 
                    10, 
                    8,  
                    random.nextInt(10), 
                    random.nextInt(10)
                );
                case 1 -> soldado = new Arquero(
                    nombre, 
                    random.nextInt(3) + 3, 
                    7,  
                    3,  
                    random.nextInt(10), 
                    random.nextInt(10), 
                    random.nextInt(10) 
                );
                case 2 -> soldado = new Caballero(
                    nombre, 
                    random.nextInt(3) + 10, 
                    13, 
                    7,  
                    random.nextInt(10), 
                    random.nextInt(10), 
                    random.nextBoolean() 
                );
                case 3 -> soldado = new Lancero(
                    nombre, 
                    random.nextInt(3) + 5, 
                    5,  
                    10, 
                    random.nextInt(10), 
                    random.nextInt(10), 
                    random.nextDouble() * 2 + 1 
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
    
    public void mostrarSoldados() {
        System.out.println("Soldados del Ejército de " + nombreReino + ":");
        for (Soldado soldado : soldados) {
            System.out.println(soldado.getClass().getSimpleName() + ": " + soldado);
        }
    }
}
