import java.util.*;

public class Ejercito {
    private String nombreReino;
    private ArrayList<Soldado> soldados;
    private boolean evolucionRealizada; 
    private static int contEjercitos=0;

    public Ejercito(String nombreReino) {
        contEjercitos++;
        this.nombreReino = nombreReino;
        this.soldados = new ArrayList<>();
    }

    public void generarSoldados(int cantidad) {
        Random random = new Random();
        for (int i = 0; i < cantidad; i++) {
            int tipo = random.nextInt(4); 
            Soldado soldado;
    
            switch (tipo) {
                case 0 -> soldado = new Espadachin(
                    "Espadachin " + i + " (" + nombreReino + ")", 
                    random.nextInt(3) + 8, 
                    10, 
                    8,  
                    random.nextInt(10), 
                    random.nextInt(10)
                );
                case 1 -> soldado = new Arquero(
                    "Arquero " + i + " (" + nombreReino + ")",
                    random.nextInt(3) + 3, 
                    7,  
                    3,  
                    random.nextInt(10), 
                    random.nextInt(10), 
                    random.nextInt(10) 
                );
                case 2 -> soldado = new Caballero(
                    "Caballero " + i + " (" + nombreReino + ")",
                    random.nextInt(3) + 10, 
                    13, 
                    7,  
                    random.nextInt(10), 
                    random.nextInt(10), 
                    random.nextBoolean() 
                );
                case 3 -> soldado = new Lancero(
                    "Lancero " + i + " (" + nombreReino + ")",
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

    public static int getContEjercitos() {
        return contEjercitos;
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

    public void generarUnidadesEspeciales() {
        Random random = new Random();
        int fila, columna;
        boolean posicionValida;
    
        do {
            fila = random.nextInt(10);  
            columna = random.nextInt(10);  
    
            posicionValida = true;
            for (Soldado s : soldados) {
                if (s.getFila() == fila && s.getColumna() == columna) {
                    posicionValida = false;
                    break;
                }
            }
        } while (!posicionValida);
    
        Soldado unidadEspecial;
        switch (nombreReino) {
            case "Inglaterra" -> unidadEspecial = new EspadachinReal(
                "Espadachin Real (" + nombreReino + ")", 
                10, 10, 8, fila, columna
            );
            case "Francia" -> unidadEspecial = new CaballeroFranco(
                "Caballero Franco (" + nombreReino + ")", 
                12, 13, 7, fila, columna
            );
            case "Castilla-Aragón" -> unidadEspecial = new EspadachinConquistador(
                "Espadachin Conquistador (" + nombreReino + ")", 
                11, 10, 9, fila, columna
            );
            case "Moros" -> unidadEspecial = new CaballeroMoro(
                "Caballero Moro (" + nombreReino + ")", 
                13, 14, 6, fila, columna
            );
            case "Sacro Imperio Romano-Germánico" -> unidadEspecial = new EspadachinTeutonico(
                "Espadachin Teutónico (" + nombreReino + ")", 
                12, 10, 10, fila, columna
            );
            default -> throw new IllegalArgumentException("Reino desconocido: " + nombreReino);
        }
    
        soldados.add(unidadEspecial);
    }
    
    public boolean isEvolucionRealizada() {
        return evolucionRealizada;
    }

    public void setEvolucionRealizada(boolean evolucionRealizada) {
        this.evolucionRealizada = evolucionRealizada;
    }

    public void intentarEvolucionar() {
        int contEjercitos=2;
        if (evolucionRealizada) {
            System.out.println("El ejército de " + nombreReino + " ya realizó su evolución.");
            return;
        }
    
        for (int i = 0; i < soldados.size(); i++) {
            Soldado soldado = soldados.get(i);
            if (soldados.get(i).getNumEjercito() == 1)
                contEjercitos=1;
            
            switch (nombreReino) {
                case "Inglaterra" -> {
                    if (soldado instanceof Espadachin) {
                        soldados.set(i, new EspadachinReal(
                            "Espadachin Real (" + nombreReino + ")", 
                            soldado.getNivelVida(), 
                            soldado.getAtaque(), 
                            soldado.getDefensa(), 
                            soldado.getFila(), 
                            soldado.getColumna()
                        ));
                        soldados.get(i).setNumEjercito(contEjercitos);
                        evolucionRealizada = true;
                        System.out.println("El ejército de Inglaterra ha evolucionado un Espadachin a Espadachin Real.");
                        return;
                    }
                }
                case "Francia" -> {
                    if (soldado instanceof Caballero) {
                        soldados.set(i, new CaballeroFranco(
                            "Caballero Franco (" + nombreReino + ")", 
                            soldado.getNivelVida(), 
                            soldado.getAtaque(), 
                            soldado.getDefensa(), 
                            soldado.getFila(), 
                            soldado.getColumna()
                        ));
                        soldados.get(i).setNumEjercito(contEjercitos);
                        evolucionRealizada = true;
                        System.out.println("El ejército de Francia ha evolucionado un Caballero a Caballero Franco.");
                        return;
                    }
                }
                case "Castilla-Aragón" -> {
                    if (soldado instanceof Espadachin) {
                        soldados.set(i, new EspadachinConquistador(
                            "Espadachin Conquistador (" + nombreReino + ")", 
                            soldado.getNivelVida(), 
                            soldado.getAtaque(), 
                            soldado.getDefensa(), 
                            soldado.getFila(), 
                            soldado.getColumna()
                        ));
                        soldados.get(i).setNumEjercito(contEjercitos);
                        evolucionRealizada = true;
                        System.out.println("El ejército de Castilla-Aragón ha evolucionado un Espadachin a Espadachin Conquistador.");
                        return;
                    }
                }
                case "Moros" -> {
                    if (soldado instanceof Caballero) {
                        soldados.set(i, new CaballeroMoro(
                            "Caballero Moro (" + nombreReino + ")", 
                            soldado.getNivelVida(), 
                            soldado.getAtaque(), 
                            soldado.getDefensa(), 
                            soldado.getFila(), 
                            soldado.getColumna()
                        ));
                        soldados.get(i).setNumEjercito(contEjercitos);
                        evolucionRealizada = true;
                        System.out.println("El ejército de Moros ha evolucionado un Caballero a Caballero Moro.");
                        return;
                    }
                }
                case "Sacro Imperio Romano-Germánico" -> {
                    if (soldado instanceof Espadachin) {
                        soldados.set(i, new EspadachinTeutonico(
                            "Espadachin Teutónico (" + nombreReino + ")", 
                            soldado.getNivelVida(), 
                            soldado.getAtaque(), 
                            soldado.getDefensa(), 
                            soldado.getFila(), 
                            soldado.getColumna()
                        ));
                        soldados.get(i).setNumEjercito(contEjercitos);
                        evolucionRealizada = true;
                        System.out.println("El ejército del Sacro Imperio ha evolucionado un Espadachin a Espadachin Teutónico.");
                        return;
                    }
                }
                default -> throw new IllegalArgumentException("Reino desconocido: " + nombreReino);
            }
        }
    
        System.out.println("No hay soldados elegibles para evolucionar en el ejército de " + nombreReino + ".");
    }
}
