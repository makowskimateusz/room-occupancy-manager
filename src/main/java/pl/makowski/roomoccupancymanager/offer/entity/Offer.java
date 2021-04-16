package pl.makowski.roomoccupancymanager.offer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Offer {

    private int id;
    private Double price;

    public Offer() {
    }

    public Offer(int id, Double price) {
        this.id = id;
        this.price = price;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
