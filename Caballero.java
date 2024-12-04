public class Caballero extends Soldado {
    private boolean montado;

    public Caballero(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna, boolean montado, int numEjercito) {
        super(nombre, nivelVida, ataque, defensa, fila, columna, numEjercito);
        this.montado = montado;
    }

    public void alternarMontura() {
        this.montado = !montado;
        System.out.println(nombre + (montado ? " monta su caballo." : " desmonta de su caballo."));
    }

    @Override
    public void accionEspecial() {
        if (montado) {
            System.out.println(nombre + " realiza una poderosa envestida.");
            this.ataque += 10;
        } else {
            System.out.println(nombre + " realiza un ataque doble.");
            this.ataque += 5;
        }
    }
}
