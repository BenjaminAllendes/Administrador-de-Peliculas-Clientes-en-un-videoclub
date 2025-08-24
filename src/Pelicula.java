public class Pelicula {

    private String id;
    private String title;
    private String genre;
    private int stock;


    public Pelicula(String id, String title, String genre, int stock){

        this.id = id;
        this.title = title;
        this.genre = genre;
        this.stock = stock;
    }

    //Metodos

    public boolean estaDisponible(){
        return stock>0;

    }

    public void reducirStock(){
        if (stock >0) stock--;

    }

    public void aumentarStock(){
        stock++;
    }

}
