abstract class Soldado {
    protected String nombre;
    protected int nivelVida;
    protected int ataque;
    protected int defensa;
    protected int fila;
    protected int columna;

    public Soldado(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna) {
        this.nombre = nombre;
        this.nivelVida = nivelVida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.fila = fila;
        this.columna = columna;
    }

    public abstract void accionEspecial();

    public void mover(int nuevaFila, int nuevaColumna) {
        this.fila = nuevaFila;
        this.columna = nuevaColumna;
    }

    public int atacar(Soldado enemigo) {
        return this.ataque - enemigo.defensa;
    }

    public void recibirDaño(int daño) {
        this.nivelVida -= daño;
    }

    public boolean estaVivo() {
        return this.nivelVida > 0;
    }
}
