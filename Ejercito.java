import java.util.*;

public class Ejercito {
    private String nombreReino;
    private ArrayList<Soldado> soldados;
    private boolean evolucionRealizada; 

    public Ejercito(String nombreReino) {
        this.nombreReino = nombreReino;
        this.soldados = new ArrayList<>();
    }

    public void generarUnidadesEspeciales(Mapa mapa) {
        if (mapa == null) {
            throw new IllegalArgumentException("El mapa no puede ser nulo.");
        }
    
        int fila = -1, columna = -1;
    
        // Obtener una posición válida utilizando un método auxiliar
        int[] posicionValida = obtenerPosicionValida(mapa);
        fila = posicionValida[0];
        columna = posicionValida[1];
    
        // Crear la unidad especial según el reino
        Soldado unidadEspecial = crearUnidadEspecial(fila, columna);
    
        // Colocar la unidad especial en el mapa y añadirla al ejército
        if (mapa.colocarSoldado(unidadEspecial)) {
            soldados.add(unidadEspecial);
            System.out.println("Unidad especial generada: " + unidadEspecial.getNombre() + 
                               " en fila=" + fila + ", columna=" + columna);
        } else {
            throw new IllegalStateException("No se pudo colocar la unidad especial en el mapa.");
        }
    }

    private int[] obtenerPosicionValida(Mapa mapa) {
        // Generar una lista de todas las posiciones posibles del mapa
        List<int[]> posiciones = new ArrayList<>();
        for (int i = 0; i < mapa.getFilas(); i++) {
            for (int j = 0; j < mapa.getColumnas(); j++) {
                posiciones.add(new int[]{i, j});
            }
        }
    
        // Filtrar posiciones válidas
        List<int[]> posicionesValidas = posiciones.stream()
            .filter(pos -> mapa.esPosicionValida(pos[0], pos[1]))
            .filter(pos -> mapa.obtenerSoldadoEnPosicion(pos[0], pos[1]) == null)
            .filter(pos -> soldados.stream()
                .noneMatch(s -> s.getFila() == pos[0] && s.getColumna() == pos[1]))
            .toList();
    
        // Verificar si hay posiciones válidas
        if (posicionesValidas.isEmpty()) {
            throw new IllegalStateException("No hay posiciones válidas disponibles en el mapa.");
        }
    
        // Seleccionar una posición válida al azar
        Random random = new Random();
        int[] posicionSeleccionada = posicionesValidas.get(random.nextInt(posicionesValidas.size()));
    
        System.out.println("Posición seleccionada: fila=" + posicionSeleccionada[0] + ", columna=" + posicionSeleccionada[1]);
    
        return posicionSeleccionada;
    }
    
    
    private Soldado crearUnidadEspecial(int fila, int columna) {
        return switch (nombreReino) {
            case "Inglaterra" -> new EspadachinReal(
                "Espadachin Real (" + nombreReino + ")", 12, 10, 8, fila, columna, this.hashCode()
            );
            case "Francia" -> new CaballeroFranco(
                "Caballero Franco (" + nombreReino + ")", 15, 13, 7, fila, columna, this.hashCode()
            );
            case "Castilla-Aragón" -> new EspadachinConquistador(
                "Espadachin Conquistador (" + nombreReino + ")", 14, 10, 9, fila, columna, this.hashCode()
            );
            case "Moros" -> new CaballeroMoro(
                "Caballero Moro (" + nombreReino + ")", 13, 14, 6, fila, columna, this.hashCode()
            );
            case "Sacro Imperio Romano-Germánico" -> new EspadachinTeutonico(
                "Espadachin Teutónico (" + nombreReino + ")", 13, 10, 10, fila, columna, this.hashCode()
            );
            default -> throw new IllegalArgumentException("Reino desconocido: " + nombreReino);
        };
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

    public void generarSoldados(int cantidad, Mapa mapa) {
        if (mapa == null) {
            throw new IllegalArgumentException("El mapa no puede ser nulo.");
        }
    
        List<int[]> posiciones = new ArrayList<>();
        for (int i = 0; i < mapa.getFilas(); i++) {
            for (int j = 0; j < mapa.getColumnas(); j++) {
                posiciones.add(new int[]{i, j});
            }
        }
    
        Random random = new Random();
    
        for (int i = 0; i < cantidad; i++) {
            // Filtrar posiciones válidas
            List<int[]> posicionesValidas = posiciones.stream()
                .filter(pos -> mapa.esPosicionValida(pos[0], pos[1]))
                .filter(pos -> mapa.obtenerSoldadoEnPosicion(pos[0], pos[1]) == null)
                .filter(pos -> soldados.stream()
                    .noneMatch(s -> s.getFila() == pos[0] && s.getColumna() == pos[1]))
                .toList();
    
            if (posicionesValidas.isEmpty()) {
                throw new IllegalStateException("No hay posiciones válidas disponibles en el mapa.");
            }
    
            int[] posicionSeleccionada = posicionesValidas.get(random.nextInt(posicionesValidas.size()));
            int fila = posicionSeleccionada[0];
            int columna = posicionSeleccionada[1];
    
            System.out.println("Generando soldado en posición: fila=" + fila + ", columna=" + columna);
    
            String nombre = "Soldado " + i + " (" + nombreReino + ")";
            Soldado soldado;
    
            int tipo = random.nextInt(4);
            switch (tipo) {
                case 0 -> soldado = new Espadachin(nombre, random.nextInt(3) + 8, 10, 8, fila, columna, this.hashCode());
                case 1 -> soldado = new Arquero(nombre, random.nextInt(3) + 3, 7, 3, fila, columna, random.nextInt(10), this.hashCode());
                case 2 -> soldado = new Caballero(nombre, random.nextInt(3) + 10, 13, 7, fila, columna, random.nextBoolean(), this.hashCode());
                case 3 -> soldado = new Lancero(nombre, random.nextInt(3) + 5, 5, 10, fila, columna, random.nextDouble() * 2 + 1, this.hashCode());
                default -> throw new IllegalArgumentException("Tipo de soldado desconocido.");
            }
    
            if (mapa.colocarSoldado(soldado)) {
                soldados.add(soldado);
            } else {
                throw new IllegalStateException("No se pudo colocar el soldado en el mapa.");
            }
        }
    }
    
    
    
    public boolean isEvolucionRealizada() {
        return evolucionRealizada;
    }

    public void setEvolucionRealizada(boolean evolucionRealizada) {
        this.evolucionRealizada = evolucionRealizada;
    }

    public void intentarEvolucionar() {
        if (evolucionRealizada) {
            System.out.println("El ejército de " + nombreReino + " ya realizó su evolución.");
            return;
        }
    
        for (int i = 0; i < soldados.size(); i++) {
            Soldado soldado = soldados.get(i);
    
            switch (nombreReino) {
                case "Inglaterra" -> {
                    if (soldado instanceof Espadachin) {
                        soldados.set(i, new EspadachinReal(
                            "Espadachin Real (" + nombreReino + ")",
                            soldado.getNivelVida(),
                            soldado.getAtaque(),
                            soldado.getDefensa(),
                            soldado.getFila(),
                            soldado.getColumna(),
                            this.hashCode() 
                        ));
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
                            soldado.getColumna(),
                            this.hashCode()
                        ));
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
                            soldado.getColumna(),
                            this.hashCode()
                        ));
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
                            soldado.getColumna(),
                            this.hashCode()
                        ));
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
                            soldado.getColumna(),
                            this.hashCode()
                        ));
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