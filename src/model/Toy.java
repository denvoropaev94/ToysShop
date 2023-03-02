package model;

public class Toy {
    private long id;
    private String title;
    private double price;
    private ToyType toyType;

    public Toy(long id, String title, double price, ToyType toyType) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.toyType = toyType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ToyType getToyType() {
        return toyType;
    }

    public void setToyType(ToyType toyType) {
        this.toyType = toyType;
    }
}
