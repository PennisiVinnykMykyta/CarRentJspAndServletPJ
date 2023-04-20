package com.example.fullstackpj.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class Car implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "car_id")
    private int id;

    @Column(name = "number_plate")
    private String numberPlate;

    @Column(name = "color")
    private String color;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @OneToMany(mappedBy = "car")
    List<Book> bookings;

    public Car(int id, String numberPlate, String color, String model, String brand) {
        this.id = id;
        this.numberPlate = numberPlate;
        this.color = color;
        this.model = model;
        this.brand = brand;
    }

    public Car(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
