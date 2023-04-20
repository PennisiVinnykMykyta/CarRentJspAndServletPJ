package com.example.fullstackpj.dao;
import java.util.List;
import com.example.fullstackpj.util.HibernateUtil;
import com.example.fullstackpj.entities.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CarDAO { //need to add the update method

    public void saveCar(Car car){ //save a specific car
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
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
            Car car;
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

    public void updateCarState(int id, boolean state){ //change state for a specific car
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Car car;
            car = session.get(Car.class,id);
            car.setState(state);
            session.update(car);
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
            Car car;
            car = session.find(Car.class, id);
            return car;
        }
    }
}
