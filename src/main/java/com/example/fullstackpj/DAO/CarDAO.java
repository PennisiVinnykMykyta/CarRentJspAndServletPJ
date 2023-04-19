package com.example.fullstackpj.DAO;
import java.util.List;
import com.example.fullstackpj.Util.HibernateUtil;
import com.example.fullstackpj.Entities.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.beans.Expression;

public class CarDAO {

    public void saveCar(Car car){ //save a specific car
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();;
            session.save(car);
            transaction.commit();
        }
        catch(Exception e){
            if(transaction == null ){
                e.printStackTrace();
            }
        }
    }

    public void deleteCar(int id){ //delete a car with a specific id
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Car car = new Car();
            car = session.find(Car.class,id);
            session.delete(car);
            session.flush();
            transaction.commit();
        }
        catch(Exception e){
            if(transaction == null ){
                e.printStackTrace();
            }
        }
    }

    public List<Car> getCarList(){ //view all of the cars
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return  session.createQuery("from Car",Car.class).list();
        }
    }

    public Car getCar(int id){ // get the specific car
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Car car = new Car();
            car = session.find(Car.class, id);
            return car;
        }
    }
}
