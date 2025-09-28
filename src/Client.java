import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Client {
    private int id;
    private String name;
    private ArrayList<Rent> rentList;       // Arriendos activos
    private ArrayList<Rent> pastList;       // Arriendos pasados


    // Constructor
    public Client(int id, String name) {
        this.id = id;
        this.name = name;
        this.rentList = new ArrayList<>();
        this.pastList = new ArrayList<>();
    }

    // ----- ID -----
    public void setId(int id){
        if (id >= 0) {
            this.id = id;
        }
    }

    // ----- NAME -----
    public int getId(){
        return id;
    }


    public void setName(String name){
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public void setName(String firstName, String lastName){
        if (firstName != null && lastName != null &&
                !firstName.isEmpty() && !lastName.isEmpty()) {
            this.name = firstName + " " + lastName;
        }
    }

    public String getName(){
        return name;
    }

    // ----- Lista de Arriendos Activos -----

    public void addRent(Rent rent){
        rentList.add(rent);
    }

    // ----- Lista de Arriendos pasados -----


    public void addPastRent(Rent rent){
        pastList.add(rent);
    }

    // ----- Recomendaciones -----


    public Set<Integer> getPastMovieIDs() {
        Set<Integer> pastMovieIDs = new HashSet<>();
        for (Rent rent : pastList) {
            pastMovieIDs.add(rent.getMovie().getID());
        }
        return pastMovieIDs;
    }

    public Set<String> getPastGenres() {
        Set<String> genres = new HashSet<>();
        for (Rent rent : pastList) {
            genres.add(rent.getMovie().getGenre());
        }
        return genres;
    }

    //Arriendos
    public void removeRent(Rent rent) {
        rentList.remove(rent);
    }
    public Rent findActiveRentByMovieID(int movieID) {
        for (Rent arriendo : this.rentList) {
            if (arriendo.getMovie().getID() == movieID) {
                return arriendo; // Lo encontramos, lo devolvemos
            }
        }
        return null; // No se encontró nada
    }
    public void mostrarArriendosActivos() {
        System.out.println("---------------------------------");
        System.out.println("Arriendos Activos para: " + this.getName());

        if (rentList.isEmpty()) {
            System.out.println("No tiene películas arrendadas actualmente.");
        } else {
            for (Rent arriendo : rentList) {
                System.out.println("- ID: " + arriendo.getMovie().getID() + " | Título: " + arriendo.getMovie().getTitle());
            }
        }
        System.out.println("---------------------------------");
    }
    //Obtener Arriendos activos
    public String getActiveRentsInfo() {
        if (rentList.isEmpty()) {
            return "No tiene arriendos activos.\n";
        }

        StringBuilder info = new StringBuilder();
        for (Rent rent : rentList) {
            info.append("- ").append(rent.getMovie().getTitle())
                    .append(" (Devolución: ").append(rent.getReturnDate()).append(")\n");
        }
        return info.toString();
    }
    //Obtener Arriendos Pasados
    public String getPastRentsInfo() {
        if (pastList.isEmpty()) {
            return "No tiene historial de arriendos.\n";
        }

        StringBuilder info = new StringBuilder();
        for (Rent rent : pastList) {
            info.append("- ").append(rent.getMovie().getTitle())
                    .append(" (Arrendado el: ").append(rent.getRentDate()).append(")\n");
        }
        return info.toString();
    }

    public boolean isVIP() {
        return false;
    } //Se utiliza para despues sobreescribirlo
}
