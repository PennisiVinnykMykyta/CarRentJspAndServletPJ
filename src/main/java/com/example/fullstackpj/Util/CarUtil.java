package com.example.fullstackpj.Util;

import java.util.List;
import com.example.fullstackpj.Entities.Car;
import com.example.fullstackpj.DAO.CarDAO;
public class CarUtil {

    private CarDAO carDao = new CarDAO();

    public void saveCar(Car car){
        carDao.saveCar(car);
    }

    public void removeCar(int x){
        carDao.deleteCar(x);
    }

    public void printCarList(){
        List <Car> carList = carDao.getCarList();
        carList.forEach(x -> System.out.println("The current car id is: " + x.getCarId()));
    }

    public void printCar(int x){
        Car carToPrint = carDao.getCar(x);
        System.out.println("The selected car is: " + carToPrint.getCarId());
    }
}
