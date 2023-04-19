package com.example.fullstackpj.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Car implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "car_id")
    private int carId;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Car(){}

    public Car(int id){
        this.carId=id;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (carId != car.carId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return carId;
    }
}
