package com.example.fullstackpj;

import com.example.fullstackpj.Util.CarUtil;
import com.example.fullstackpj.Entities.Car;
public class TestClass {
    public static void main(String[] args){
        CarUtil car = new CarUtil();

        Car car1 = new Car(1);
        Car car2 = new Car(2);
        Car car5 = new Car(5);
        car.saveCar(car1);
        car.saveCar(car2);
        car.saveCar(car5);
        car.printCarList();

    }
}
