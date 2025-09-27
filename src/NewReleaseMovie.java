class NewReleaseMovie extends Movie{
    public NewReleaseMovie(int id, String title, String genre, int stock) {
        super(id, title, genre, stock);
    }

    @Override
    public String toString() {
        return super.getID() + " - " + super.getTitle() + " (ESTRENO)" +
                " | Género: " + super.getGenre() +
                " | Stock: " + super.getStock();
    }
}
