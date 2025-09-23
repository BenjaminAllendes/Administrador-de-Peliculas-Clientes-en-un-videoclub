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
    public ArrayList<Rent> getRentList(){
        return rentList;
    }

    public void addRent(Rent rent){
        rentList.add(rent);
    }

    // ----- PAST LIST -----
    public ArrayList<Rent> getPastList(){
        return pastList;
    }

    public void addPastRent(Rent rent){
        pastList.add(rent);
    }

    // ----- RECOMMENDED -----
    public ArrayList<Movie> getRecommended(){
        return recommended;
    }

    public void addRecommended(Movie movie){
        recommended.add(movie);
    }
}
