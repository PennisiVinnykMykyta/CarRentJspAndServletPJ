package com.example.fullstackpj.dao;

import com.example.fullstackpj.entities.Car;
import com.example.fullstackpj.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CarDAO { //need to add the update method

    public void deleteById(int id) { //delete a car with a specific id
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Car car;
            car = session.find(Car.class, id);
            session.delete(car);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction == null) {
                e.printStackTrace();
            }
        }
    }

    public void saveOrUpdateCar(Car car) { //change state for a specific car
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(car);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction == null) {
                e.printStackTrace();
            }
        }
    }

    public List<Car> findAll() { //view all cars
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Car", Car.class).list();
        }
    }

    public Car findById(int id) { // get the specific car
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Car car;
            car = session.find(Car.class, id);
            return car;
        }
    }

    public List<Car> availableCars(List<Integer> bookedCars) {
        List<Car> carList = findAll();
        List<Car> availableCars = new ArrayList<>();

        for (Car car : carList) {
            if(!bookedCars.contains(car.getId())) {
                availableCars.add(car);
            }
        }
        return availableCars;

    }
}
