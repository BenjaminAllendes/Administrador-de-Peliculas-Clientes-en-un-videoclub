import java.util.*;
import java.time.LocalDate;

public class Videoclub {
    private Map<Integer, Client> clients;  // Clientes registrados
    private Map<Integer, Movie> movies;    // Películas disponibles
    private ArrayList<Rent> rents;         // Arriendos activos

    // Constructor
    public Videoclub() {
        clients = new HashMap<>();
        movies = new HashMap<>();
        rents = new ArrayList<>();
    }

    // ----- CLIENTES -----
    public void addClient(Client c) {
        clients.put(c.getId(), c);
    }

    public Client findClientByID(int id) {
        return clients.get(id);
    }

    // ----- PELÍCULAS -----
    public void addMovie(Movie m) {
        movies.put(m.getID(), m);
    }

    public Movie findMovieByID(int id) {
        return movies.get(id);
    }

    // ----- ARRENDAR PELÍCULA -----
    public boolean rentMovie(int clientID, int movieID, int days) {
        Client c = findClientByID(clientID);
        Movie m = findMovieByID(movieID);

        if (c != null && m != null && m.estaDisponible()) {
            LocalDate today = LocalDate.now();
            LocalDate returnDate = today.plusDays(days);

            Rent r = new Rent(c, m, today, returnDate);
            rents.add(r);  // Agrega arriendo a lista global de arriendos
            return true;
        }
        return false;
    }

    // ----- DEVOLVER PELÍCULA -----
    public boolean returnMovie(int clientID, int movieID) {
        Client c = findClientByID(clientID);
        Movie m = findMovieByID(movieID);

        if (c != null && m != null) {

            // Buscar el arriendo activo correspondiente
            Rent rentToReturn = c.findActiveRentByMovieID(movieID) ;

            if (rentToReturn != null) {
                c.removeRent(rentToReturn);// Quitar del listado activo del cliente
                c.addPastRent(rentToReturn);           // Agregar al historial
                rents.remove(rentToReturn);            // Quitar de lista global de arriendos activos
                m.increaseStock();                     // Devolver stock de la película
                return true;
            }
        }
        return false;
    }

    // ----- MOSTRAR CLIENTES -----
    public void showClients() {
        for (Client c : clients.values()) {
            System.out.println("ID: " + c.getId() + " | Nombre: " + c.getName());
        }
    }

    // ----- MOSTRAR PELÍCULAS -----
    public void showMovies() {
        for (Movie m : movies.values()) {
            System.out.println("ID: " + m.getID() +
                    " | Título: " + m.getTitle() +
                    " | Género: " + m.getGenre() +
                    " | Stock: " + (m.estaDisponible() ? m.getStock() : "Agotado"));
        }
    }

    // ----- MOSTRAR ARRIENDOS ACTIVOS -----
    public void showRents() {
        for (Rent r : rents) {
            r.showInfo();
        }
    }
}
