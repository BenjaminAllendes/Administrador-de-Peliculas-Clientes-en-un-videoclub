import java.util.*;
import java.time.LocalDate;

public class Videoclub {
    //Mapa que guarda a los clientes, donde la clave es el ID único del cliente
    private Map<Integer, Client> clients;
    //Mapa que guarda las películas, donde la clave es el ID único de la película
    private Map<Integer, Movie> movies;
    //Lista que almacena todos los arriendos activos
    private ArrayList<Rent> rents;

    //Constructor: inicializa los mapas y la lista de arriendos vacíos
    public Videoclub() {
        clients = new HashMap<>();
        movies = new HashMap<>();
        rents = new ArrayList<>();
    }

    // ----- Métodos SETTERS y GETTERS  -----
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
    //Método para arrendar una película
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
    //Método para devolver una película
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
    //Muestra todos los clientes registrados
    public void showClients() {
        for (Client c : clients.values()) {
            System.out.println("ID: " + c.getID() + " | Nombre: " + c.getName());
        }
    }
    //Muestra todas las películas registradas
    public void showMovies() {
        for (Movie m : movies.values()) {
            System.out.println("ID: " + m.getID() +
                    " | Título: " + m.getTitle() +
                    " | Género: " + m.getGenre() +
                    " | Stock: " + (m.estaDisponible() ? m.getStock() : "Agotado"));
        }
    }
    //Muestra todos los arriendos actuales con su información
    public void showRents() {
        for (Rent r : rents) {
            r.showInfo();
        }
    }
}
