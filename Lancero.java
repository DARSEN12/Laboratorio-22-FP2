class Lancero extends Soldado {
    private int longitudLanza;

    public Lancero(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna, int longitudLanza) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);
        this.longitudLanza = longitudLanza;
    }

    @Override
    public void accionEspecial() {
        System.out.println(this.nombre + " forma un schiltrom para mejorar su defensa!");
        this.defensa += 1;
    }
}
