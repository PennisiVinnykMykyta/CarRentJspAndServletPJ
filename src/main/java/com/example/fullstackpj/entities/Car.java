package com.example.fullstackpj.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Car implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "car_id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Car(){}

    public Car(int id){
        this.id =id;
    }

}
