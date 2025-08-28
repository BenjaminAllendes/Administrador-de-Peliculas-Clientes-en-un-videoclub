import java.util.*;
import java.time.LocalDate;

public class Videoclub {
    private Map<Integer, Client> clients;
    private Map<Integer, Movie> movies;
    private ArrayList<Rent> rents;

    public Videoclub() {
        clients = new HashMap<>();
        movies = new HashMap<>();
        rents = new ArrayList<>();
    }

    public void addClient(Client c) {
        clients.put(c.getID(), c);
    }

    public Client findClientByID(int id) {
        return clients.get(id);
    }

    public void addMovie(Movie m) {
        movies.put(m.getID(), m);
    }

    public Movie findMovieByID(int id) {
        return movies.get(id);
    }

    public boolean rentMovie(int clientID, int movieID, int days) {
        Client c = findClientByID(clientID);
        Movie m = findMovieByID(movieID);

        if (c != null && m != null && m.estaDisponible()) {
            LocalDate today = LocalDate.now();
            LocalDate returnDate = today.plusDays(days);

            Rent r = new Rent(c, m, today, returnDate);
            rents.add(r);
            return true;
        }
        return false;
    }

    public boolean returnMovie(int clientID, int movieID) {
        Client c = findClientByID(clientID);
        Movie m = findMovieByID(movieID);

        if (c != null && m != null) {
            c.getPast_list().add(m.getTitle());
            c.getRent_list().removeIf(s -> s.contains(m.getTitle()));
            m.increaseStock();
            return true;
        }
        return false;
    }

    public void showClients() {
        for (Client c : clients.values()) {
            System.out.println("ID: " + c.getID() + " | Nombre: " + c.getName());
        }
    }

    public void showMovies() {
        for (Movie m : movies.values()) {
            System.out.println("ID: " + m.getID() +
                    " | Título: " + m.getTitle() +
                    " | Género: " + m.getGenre() +
                    " | Stock: " + (m.estaDisponible() ? m.getStock() : "Agotado"));
        }
    }

    public void showRents() {
        for (Rent r : rents) {
            r.showInfo();
        }
    }
}
