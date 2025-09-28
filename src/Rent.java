import java.time.LocalDate;

public class Rent {
    private Client rentClient;      // Cliente que realiza el arriendo
    private Movie movie;            // Película que se está arrendando
    private LocalDate rentDate;     // Fecha de arriendo
    private LocalDate returnDate;   // Fecha de devolución

    // Constructor
    public Rent(Client rentClient, Movie movie, LocalDate rentDate, LocalDate returnDate) {
        this.rentClient = rentClient;
        this.movie = movie;
        this.rentDate = rentDate;
        this.returnDate = returnDate;

        // Agrega este arriendo al cliente como objeto Rent
        rentClient.addRent(this);

        // Disminuye el stock de la película
        movie.decreaseStock();
    }
    //Se utiliza para leer los datos de manera correcta desde el CSV para despues pasarlo a datos utilizables en el programa
    public static Rent fromCSV(String line, Videoclub vc) throws RecursoNoEncontradoException {
        String[] parts = line.split(",");
        int clientID = Integer.parseInt(parts[0]);
        int movieID = Integer.parseInt(parts[1]);
        LocalDate rentDate = LocalDate.parse(parts[2]);
        LocalDate returnDate = LocalDate.parse(parts[3]);

        Client c = vc.findClientByID(clientID);
        Movie m = vc.findMovieByID(movieID);

        return new Rent(c, m, rentDate, returnDate);
    }


    public String toCSV() {
        return rentClient.getId() + "," + movie.getID() + "," + rentDate + "," + returnDate;
    }


    // ----- GETTERS y SETTERS -----
    public Client getRentClient() {
        return rentClient;
    }

    public void setRentClient(Client rentClient) {
        this.rentClient = rentClient;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // Muestra información del arriendo
    public void showInfo() {
        System.out.println("Cliente: " + rentClient.getName() +
                " | Película: " + movie.getTitle() +
                " | Fecha arriendo: " + rentDate +
                " | Fecha devolución: " + returnDate);
    }
}
