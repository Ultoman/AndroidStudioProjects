package edu.niu.cs.z1764108.candystore;

/**
 * Created by JAB on 4/18/2017.
 */

public class Candy {
    private int id;
    private String name;
    private double price;

    public Candy(int newId, String newName, double newPrice) {
        this.id = newId;
        this.name = newName;
        this.price = newPrice;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString()
    {
        return id + " " + name + " " + price;
    }

}
