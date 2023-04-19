package com.example.fullstackpj.util;
import java.util.List;
import com.example.fullstackpj.entities.Car;
import com.example.fullstackpj.dao.CarDAO;

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
        carList.forEach(x -> System.out.println("The current car id is: " + x.getId()));
    }

    public void printCar(int x){
        Car carToPrint = carDao.getCar(x);
        System.out.println("The selected car is: " + carToPrint.getId());
    }
}
