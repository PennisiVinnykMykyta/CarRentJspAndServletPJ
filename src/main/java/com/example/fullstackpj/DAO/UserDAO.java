package com.example.fullstackpj.DAO;
import java.util.List;

import com.example.fullstackpj.Util.HibernateUtil;
import com.example.fullstackpj.Entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class UserDAO {

    public void saveUser(User user){ // need to add the update method
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
        catch(Exception e){
            if(transaction == null ){
                e.printStackTrace();
            }
        }
    }

    public void deleteUser(int id){ //delete a user with a specific id
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            User user = new User();
            user = session.find(User.class,id);
            session.delete(user);
            session.flush();
            transaction.commit();
        }
        catch(Exception e){
            if(transaction == null ){
                e.printStackTrace();
            }
        }
    }

    public List<User> getUserList(){ //view all of the users
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return  session.createQuery("from User",User.class).list();
        }
    }

    public User getUser(int id){ // get a specific user
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            User user = new User();
            user = session.find(User.class, id);
            return user;
        }
    }

}
