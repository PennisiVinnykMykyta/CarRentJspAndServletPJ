package com.example.fullstackpj;

import com.example.fullstackpj.Util.CarUtil;
import com.example.fullstackpj.Entities.Car;
public class TestClass {
    public static void main(String[] args){
        CarUtil car = new CarUtil();

        car.printCar(2);
        car.removeCar(4);
        car.printCarList();



    }
}
