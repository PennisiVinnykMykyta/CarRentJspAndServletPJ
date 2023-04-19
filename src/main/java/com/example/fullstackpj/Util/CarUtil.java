package com.example.fullstackpj.Util;

import java.util.List;
import com.example.fullstackpj.Entities.Car;
import com.example.fullstackpj.DAO.CarDAO;
public class CarUtil {

    private CarDAO carDao = new CarDAO();
    private Car car = new Car();

    public void saveCar(Car car){
        carDao.saveCar(car);
    }

    public void printCarList(){
        List <Car> carList = carDao.getCarList();
        carList.forEach(x -> System.out.println("The current car id is: " + x.getCarId()));
    }
}
