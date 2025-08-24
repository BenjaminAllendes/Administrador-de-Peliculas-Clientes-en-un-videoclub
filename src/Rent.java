import java.time.LocalDate;

public class Rent {
    private Client rentClient;
    private Pelicula movie;
    private LocalDate rentDate;
    private LocalDate returnDate;

    public Rent(Client rentClient, Pelicula movie, LocalDate rentDate, LocalDate returnDate) {
        this.rentClient = rentClient;
        this.movie = movie;
        this.rentDate = rentDate;
        this.returnDate = returnDate;

        rentClient.getRent_list().add(movie.getTitle()
                + " (Arriendo: " + rentDate
                + " / Devolución: " + returnDate + ")");
        movie.reducirStock();
    }

    public Client getRentClient() {
        return rentClient;
    }

    public Pelicula getMovie() {
        return movie;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }


    public void showInfo() {
        System.out.println("Cliente: " + rentClient.getName() +
                " | Película: " + movie.getTitle() +
                " | Fecha arriendo: " + rentDate +
                " | Fecha devolución: " + returnDate);
    }
}
