class Espadachin extends Soldado {
    private int longitudEspada;

    public Espadachin(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna, int longitudEspada) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);
        this.longitudEspada = longitudEspada;
    }

    @Override
    public void accionEspecial() {
        System.out.println(this.nombre + " crea un muro de escudos!");
        this.defensa += 2; 
    }
}
