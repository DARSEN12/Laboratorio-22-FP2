class Caballero extends Soldado {
    private boolean montado;

    public Caballero(String nombre, int nivelVida, int ataque, int defensa, int fila, int columna) {
        super(nombre, nivelVida, ataque, defensa, fila, columna);
        this.montado = true; 
    }

    public void montar() {
        if (!montado) {
            this.montado = true;
            this.ataque += 3;
            System.out.println(this.nombre + " monta y se prepara para una envestida!");
        }
    }

    public void desmontar() {
        if (montado) {
            this.montado = false;
            this.ataque -= 3;
            System.out.println(this.nombre + " desmonta para un combate más cercano.");
        }
    }

    @Override
    public void accionEspecial() {
        if (montado) {
            System.out.println(this.nombre + " realiza una envestida poderosa!");
            this.ataque += 3;
        } else {
            System.out.println(this.nombre + " realiza un doble ataque!");
        }
    }
}
