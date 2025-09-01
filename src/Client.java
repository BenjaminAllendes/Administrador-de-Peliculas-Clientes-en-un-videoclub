import java.util.*;
import java.util.ArrayList;

public class Client {
    private int ID ;
    private String name ;
    private ArrayList<String> rent_list ;
    private ArrayList<String> past_list ;
    private ArrayList<String> recommended ;

    //Constructor: Inicializa el cliente con ID y un nombre
    public Client(int ID, String name){
        this.ID = ID ;
        this.name = name ;
        rent_list = new ArrayList<>();
        past_list = new ArrayList<>();
        recommended = new ArrayList<>();
    }
    //Setter para poblar el ID
    public void setID(int ID){
        if (ID >= 0) {
            this.ID = ID ;
        }
    }
    //Getter para obtener el ID
    public int getID(){
        return ID ;
    }
    // Setter para el nombre, validando que no esté vacío
    public void setName(String name){
        if (!name.isEmpty()) {
            this.name = name ;
        }
    }
    //Sobrecarga del método setName: permite establecer nombre y apellido juntos
    public void setName(String firstName, String lastName) {
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            this.name = firstName + " " + lastName ;
        }
    }
    // ----- Métodos SETTERS y GETTERS  -----
    public String getName(){
        return name ;
    }

    public void setRent_list(){
        rent_list = new ArrayList<>();
    }

    public ArrayList<String>getRent_list(){
        return rent_list ;
    }

    public void setPast_list(){
        past_list = new ArrayList<>();
    }

    public ArrayList<String>getPast_list(){
        return past_list ;
    }

    public void setRecommended(){
        recommended = new ArrayList<>();
    }


    public ArrayList<String>getRecommended(){
        return recommended ;
    }

}
