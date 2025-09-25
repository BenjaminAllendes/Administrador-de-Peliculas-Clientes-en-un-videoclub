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

    public Client findClientByID(int id) throws RecursoNoEncontradoException {
        Client c = clients.get(id);
        if (c == null) {
            throw new RecursoNoEncontradoException("No se encontró un cliente con el ID: " + id);
        }
        return c;
    }

    // ----- PELÍCULAS -----
    public void addMovie(Movie m) {
        movies.put(m.getID(), m);
    }

    public Movie findMovieByID(int id) throws RecursoNoEncontradoException  {
        Movie m = movies.get(id);
        if (m == null) {
            throw new RecursoNoEncontradoException("No se encontró una película con el ID: " + id);
        }
        return m;
    }

    // ----- ARRENDAR PELÍCULA -----
    public boolean rentMovie(int clientID, int movieID, int days) throws RecursoNoEncontradoException, PeliculaSinStockException {

        Client c = findClientByID(clientID);
        Movie m = findMovieByID(movieID);

        // 2. CAMBIA la lógica: si no está disponible, LANZA la excepción
        if (!m.estaDisponible()) {
            throw new PeliculaSinStockException(m.getTitle()); // Esta línea es la que faltaba
        }

        // Si el código llega hasta aquí, significa que hay stock y todo está bien.
        LocalDate today = LocalDate.now();
        LocalDate returnDate = today.plusDays(days);

        Rent r = new Rent(c, m, today, returnDate);
        rents.add(r);
        return true;
    }

    // ----- DEVOLVER PELÍCULA -----
    public boolean returnMovie(int clientID, int movieID) throws RecursoNoEncontradoException {
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
    // Nuevos metodos para el GUI
    public String getRentsInfo() {
        StringBuilder sb = new StringBuilder();
        for (Rent r : rents) {
            sb.append("Cliente: ").append(r.getRentClient().getName())
                    .append(" | Película: ").append(r.getMovie().getTitle())
                    .append(" | Fecha de devolución: ").append(r.getReturnDate()).append("\n");
        }
        return sb.toString();
    }

    public String getClientsInfo() {
        StringBuilder sb = new StringBuilder();
        for (Client c : clients.values()) { // Accedemos directamente al mapa
            sb.append("ID: ").append(c.getId())
                    .append(" | Nombre: ").append(c.getName()).append("\n");
        }
        return sb.toString();
    }

    public String getMoviesInfo() {
        StringBuilder sb = new StringBuilder();
        for (Movie m : movies.values()) { // Accedemos directamente al mapa
            sb.append("ID: ").append(m.getID())
                    .append(" | Título: ").append(m.getTitle())
                    .append(" | Género: ").append(m.getGenre())
                    .append(" | Stock: ").append(m.getStock()).append("\n");
        }
        return sb.toString();
    }
    public void removeClient(int id) throws RecursoNoEncontradoException {
        if (!clients.containsKey(id)) {
            throw new RecursoNoEncontradoException("No se puede eliminar: No existe cliente con ID " + id);
        }
        // Adicional: Lógica para manejar arriendos activos si es necesario
        clients.remove(id);
    }

    public void modifyClientName(int id, String newName) throws RecursoNoEncontradoException {
        Client clientToModify = findClientByID(id); // Reutilizamos el método de búsqueda
        clientToModify.setName(newName);
    }
    public void removeMovie(int id) throws RecursoNoEncontradoException {
        if (!movies.containsKey(id)) {
            throw new RecursoNoEncontradoException("No se puede eliminar: No existe película con ID " + id);
        }
        movies.remove(id);
    }

    public void modifyMovieTitle(int id, String newTitle) throws RecursoNoEncontradoException {
        Movie movieToModify = findMovieByID(id);
        movieToModify.setTitle(newTitle);
    }
    public Movie findMovieByTitle(String title) throws RecursoNoEncontradoException {
        for (Movie movie : movies.values()) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }
        throw new RecursoNoEncontradoException("No se encontró ninguna película con el título: " + title);
    }

    public Client findClientByRentedMovieID(int movieID) throws RecursoNoEncontradoException {
        // Primero, valida que la película exista
        findMovieByID(movieID);

        for (Client client : clients.values()) {
            if (client.findActiveRentByMovieID(movieID) != null) {
                return client;
            }
        }
        throw new RecursoNoEncontradoException("Ningún cliente tiene arrendada la película con ID: " + movieID);
    }

}

