package recordmaestro.model;

/**
 * The Entity: one album obj = one record 
 * 
 * Fields follow Unit 13 (13.4): id, artist, title , price,
 * and whether it is instock, with a strentch goal of a stock. ;
 * 
 * Album
 */

public class Album {

    private int id; //for keeping track of albums
    private String title;
    private String artist;
    private double price;
    private boolean inStock= false;

    public Album(String title, String artist, double price, boolean inStock){
        this(0, title, artist, price, inStock);
    }

    public Album(int id, String title, String artist, double price, boolean inStock){
        setId(id);
        setTitle(title);
        setArtist(artist);
        setPrice(price);
        setInStock(inStock);
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative: " + id);
        }
        this.id = id;

    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.title = title.trim();


    }

    public String getArtist(){
        return artist;
    }

    public void setArtist(String artist){
        if (artist == null || artist.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.artist = artist.trim();

        this.artist = artist;

    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative: " + price);
        }

        this.price = price;

    }

    public boolean isInStock(){
        return inStock;
    }

    public void setInStock(boolean inStock){
        this.inStock = inStock;

    }

    @Override
    public String toString() {
        return String.format("#%-2d %-22s %-38s $%6.2f %s", id, title, artist, price,
         inStock ? "Is Stocked": "Is not Stocked");
    }
}
