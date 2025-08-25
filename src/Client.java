import java.util.*;
import java.util.ArrayList;

public class Client {
    private int ID ;
    private String name ;
    private ArrayList<String> rent_list ;
    private ArrayList<String> past_list ;
    private ArrayList<String> recommended ;

    public Client(int ID, String name){
        this.ID = ID ;
        this.name = name ;
        rent_list = new ArrayList<>();
        past_list = new ArrayList<>();
        recommended = new ArrayList<>();
    }

    public void setID(){
        if (ID >= 0) {
            this.ID = ID ;
        }
    }

    public int getID(){
        return ID ;
    }

    public void setName(String name){
        if (name.equals(name.toString())) {
            this.name = name ;
        }
    }

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

    // editar el get recommended para que RECOMIENDE DEL MISMO GENERO
    public ArrayList<String>getRecommended(){
        return recommended ;
    }

}
