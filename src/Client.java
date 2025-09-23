import java.util.ArrayList;

public class Client {
    private int id;
    private String name;
    private ArrayList<Rent> rentList;       // Arriendos activos
    private ArrayList<Rent> pastList;       // Arriendos pasados
    private ArrayList<Movie> recommended;   // Películas recomendadas

    // Constructor
    public Client(int id, String name) {
        this.id = id;
        this.name = name;
        this.rentList = new ArrayList<>();
        this.pastList = new ArrayList<>();
        this.recommended = new ArrayList<>();
    }

    // ----- ID -----
    public void setId(int id){
        if (id >= 0) {
            this.id = id;
        }
    }

    public int getId(){
        return id;
    }

    // ----- NAME -----
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

    // ----- RENT LIST -----

    public void addRent(Rent rent){
        rentList.add(rent);
    }

    // ----- PAST LIST -----


    public void addPastRent(Rent rent){
        pastList.add(rent);
    }

    // ----- RECOMMENDED -----

    public void addRecommended(Movie movie){
        recommended.add(movie);
    }
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
}
