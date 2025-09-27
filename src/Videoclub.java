import java.io.*;
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


    public String getRecommendedMoviesInfo(int clientID) throws RecursoNoEncontradoException {
        Client client = findClientByID(clientID);
        Set<String> pastGenres = client.getPastGenres();

        if (pastGenres.isEmpty()) {
            return "No hay recomendaciones disponibles, ya que no tiene historial de arriendos.";
        }

        Set<Integer> pastMovieIDs = client.getPastMovieIDs();

        StringBuilder recommendationsInfo = new StringBuilder();
        boolean foundRecommendation = false;

        for (Movie movie : movies.values()) {
            if (pastGenres.contains(movie.getGenre()) && !pastMovieIDs.contains(movie.getID()) && movie.estaDisponible()) {
                recommendationsInfo.append(" - ID: ").append(movie.getID()).append(" | ").append(movie.getTitle()).append(" (").append(movie.getGenre()).append(")\n");
                foundRecommendation = true;
            }
        }

        if (!foundRecommendation) {
            return "No hay recomendaciones que coincidan con su historial.";
        }

        return recommendationsInfo.toString();
    }


    //inasnjsajnasbjasbjasfbhafasaf
    public void saveData() {
        saveClientsToFile("clients.csv");
        saveMoviesToFile("movies.csv");
        saveRentsToFile("rents.csv");
    }

    private void saveClientsToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Client c : clients.values()) {
                pw.println(c.getId() + "," + c.getName());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar clientes: " + e.getMessage());
        }
    }

    private void saveMoviesToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Movie m : movies.values()) {
                pw.println(m.getID() + "," + m.getTitle() + "," + m.getGenre() + "," + m.getStock());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar películas: " + e.getMessage());
        }
    }

    private void saveRentsToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Rent r : rents) {
                pw.println(r.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar arriendos: " + e.getMessage());
        }
    }

    //jkasjsakasbhkkaskbhabhkshbksa

    //nkasbkasdbkasdjkaskjsad
    public void loadData() {
        loadClientsFromFile("clients.csv");
        loadMoviesFromFile("movies.csv");
        loadRentsFromFile("rents.csv");
    }

    private void loadClientsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                clients.put(id, new Client(id, name));
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar clientes (posible primera ejecución)");
        }
    }

    private void loadMoviesFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim(); // Elimina espacios al inicio y fin
                if (line.isEmpty()) continue; // Ignora líneas vacías

                String[] parts = line.split(",");
                if (parts.length < 4) continue; // Ignora líneas incompletas

                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String genre = parts[2];
                int stock = Integer.parseInt(parts[3]);

                Movie m = new Movie(id, title, genre, stock);
                movies.put(id, m);
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar películas (posible primera ejecución)");
        }
    }


    private void loadRentsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Rent r = Rent.fromCSV(line, this); // usa el método de la clase Rent
                    rents.add(r); // ya se agrega también al cliente dentro del constructor
                } catch (RecursoNoEncontradoException e) {
                    System.out.println("No se pudo cargar arriendo: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar arriendos (posible primera ejecución)");
        }
    }


    //anjasjsakka sk as

    public void generarReporteTXT(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("==== REPORTE VIDEOCLUB ====");
            pw.println("\nClientes:");
            pw.println(getClientsInfo());

            pw.println("\nPelículas:");
            pw.println(getMoviesInfo());

            pw.println("\nArriendos:");
            pw.println(getRentsInfo());
        } catch (IOException e) {
            System.out.println("Error al generar reporte: " + e.getMessage());
        }
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

