class Arquero extends Soldado {
    private int numeroFlechas;

    public Arquero(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna, int numeroFlechas) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);
        this.numeroFlechas = numeroFlechas;
    }

    @Override
    public void accionEspecial() {
        if (numeroFlechas > 0) {
            System.out.println(this.nombre + " dispara una flecha!");
            numeroFlechas--;
        } else {
            System.out.println(this.nombre + " no tiene flechas para disparar!");
        }
    }
}
