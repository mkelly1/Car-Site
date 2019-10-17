package com.example.demo;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long categoryId;

    private String cartype;

    public Category(){}

    public Category(String cartype){
        this.cartype = cartype;
    }

    public long getCategoryId() {
        return categoryId;
    }

    @OneToMany
    public List<Car> cars;

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }
}
