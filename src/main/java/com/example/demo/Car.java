package com.example.demo;

import javax.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long carId;

    @ManyToOne
    private Category category;

    private String price;
    private String make;
    private String model;
    private String caryear;

    public Car(){}

    public Car(String price, String make, String model, String year){
        this.price = price;
        this.make = make;
        this.model = model;
        this.caryear = year;
    }

    public long getCarId() { return carId; }

    public void setCarId(long carId) { this.carId = carId; }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCaryear() {
        return caryear;
    }

    public void setCaryear(String caryear) {
        this.caryear = caryear;
    }
}
