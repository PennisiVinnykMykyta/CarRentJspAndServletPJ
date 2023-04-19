package com.example.fullstackpj.DAO;
import java.util.List;
import com.example.fullstackpj.Util.HibernateUtil;
import com.example.fullstackpj.Entities.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.beans.Expression;

public class CarDAO {

    public void saveCar(Car car){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();;
            session.save(car);
            transaction.commit();
        }
        catch(Exception e){
            if(transaction != null ){
                //transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Car> getCarList(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return  session.createQuery("from Car",Car.class).list();
        }
    }
}
