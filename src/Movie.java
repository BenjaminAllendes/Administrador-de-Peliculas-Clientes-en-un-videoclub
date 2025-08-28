public class Movie {

    private int id;
    private String title;
    private String genre;
    private int stock;


    public Movie(int id, String title, String genre, int stock){

        this.id = id;
        this.title = title;
        this.genre = genre;
        this.stock = stock;
    }

    //Metodos

    public String getTitle(){
        return title ;
    }
    public void setTitle(String title){
        this.title = title ;
    }
    public String getGenre(){
        return genre ;
    }
    public void setGenre(String genre){
        this.genre = genre ;
    }
    public int getID(){
        return id ;
    }
    public void setID(int id){
        this.id = id ;
    }

    public int getStock(){
        return stock ;
    }

    public void setStock(int stock){
        this.stock = stock ;
    }

    public boolean estaDisponible(){
        return stock>0;

    }

    public void reducirStock(){
        if (stock >0) stock--;
    }

    public void aumentarStock(){
        stock++;
    }
    public void aumentarStock(int cantidad) {
        if (cantidad > 0) {
            this.stock += cantidad;
        }
    }

}
