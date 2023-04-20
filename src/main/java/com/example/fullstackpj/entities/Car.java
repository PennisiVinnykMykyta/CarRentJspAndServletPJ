package com.example.fullstackpj.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Car implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "car_id")
    private int id;

    @Column(name = "booked")
    private boolean state;

    public boolean getState(){ return state; }

    public void setState(boolean state){ this.state = state;}

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
