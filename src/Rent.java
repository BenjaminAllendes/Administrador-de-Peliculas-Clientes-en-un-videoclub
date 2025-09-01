import java.time.LocalDate; //Importa LocalDate para manejar fechas de arriendo y devolución

public class Rent {
    private Client rentClient; //Cliente que realiza el arriendo
    private Movie movie; //Película que está siendo arrendada
    private LocalDate rentDate; //Fecha en que se realiza el arriendo
    private LocalDate returnDate; //Fecha programada para devolver la película

    //Constructor: crea un nuevo arriendo
    public Rent(Client rentClient, Movie movie, LocalDate rentDate, LocalDate returnDate) {
        this.rentClient = rentClient;
        this.movie = movie;
        this.rentDate = rentDate;
        this.returnDate = returnDate;

        rentClient.getRent_list().add(movie.getTitle()
                + " (Arriendo: " + rentDate
                + " / Devolución: " + returnDate + ")");
        movie.decreaseStock();
    }
    // ----- Métodos SETTERS y GETTERS  -----
    public void setRentClient(Client rentClient){
        this.rentClient = rentClient;
    }

    public Client getRentClient() {
        return rentClient;
    }

    public void setMovie(Movie movie){
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setRentDate(LocalDate rentDate){
        this.rentDate = rentDate;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setReturnDate(LocalDate returnDate){
        this.returnDate = returnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    //Muestra información detallada del arriendo
    public void showInfo() {
        System.out.println("Cliente: " + rentClient.getName() +
                " | Película: " + movie.getTitle() +
                " | Fecha arriendo: " + rentDate +
                " | Fecha devolución: " + returnDate);
    }
}
