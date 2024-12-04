public class Main {
    public static void main(String[] args) {
        // Crear mapa
        Mapa mapa = new Mapa("Bosque");

        // Crear ejércitos
        Ejercito ejercito1 = new Ejercito("Inglaterra");
        Ejercito ejercito2 = new Ejercito("Francia");

        // Generar soldados
        ejercito1.generarSoldados(10);
        ejercito2.generarSoldados(10);

        // Posicionar soldados en el mapa
        for (Soldado soldado : ejercito1.getSoldados()) {
            mapa.colocarSoldado(soldado);
        }
        for (Soldado soldado : ejercito2.getSoldados()) {
            mapa.colocarSoldado(soldado);
        }

        // Mostrar mapa
        mapa.mostrarMapa();
    }
}
