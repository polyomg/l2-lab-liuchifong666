package poly.edu.model;

public class Item {
    private Integer id;
    private String name;
    private double price;
    private int qty;

    public Item() {}
    public Item(Integer id, String name, double price, int qty) {
        this.id = id; this.name = name; this.price = price; this.qty = qty;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }
}
