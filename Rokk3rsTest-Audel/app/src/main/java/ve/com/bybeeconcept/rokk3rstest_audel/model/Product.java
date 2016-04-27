package ve.com.bybeeconcept.rokk3rstest_audel.model;

import com.orm.SugarRecord;

/**
 * Created by audel on 2/25/16.
 */
public class Product extends SugarRecord{

    private long stock;
    private double price;
    private String name;
    private String description;

    public Product() { }

    public Product(long stock, double price, String name) {
        this.stock = stock;
        this.price = price;
        this.name = name;
    }

    public Product(long stock, double price, String name, String description) {
        this.stock = stock;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
