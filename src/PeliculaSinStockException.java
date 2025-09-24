public class PeliculaSinStockException extends Exception {
    public PeliculaSinStockException(String tituloPelicula) {
        super("La película '" + tituloPelicula + "' no tiene stock disponible.");
    }
}
