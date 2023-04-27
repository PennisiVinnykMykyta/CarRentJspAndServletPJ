package com.example.fullstackpj.dao;
import java.util.List;
import com.example.fullstackpj.util.HibernateUtil;
import com.example.fullstackpj.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class UserDAO {

    public void deleteById(int id){ //delete a user with a specific id
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            User user;
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

    public void saveOrUpdateUser(User user){ // save the user if not present else update it
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            session.flush();
            transaction.commit();
        }
        catch(Exception e){
            if(transaction == null ){
                e.printStackTrace();
            }
        }
    }

    public List<User> findAll(){ //view all users
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return  session.createQuery("SELECT s from User s",User.class).list();
        }catch(NoResultException exception){
            return null;
        }
    }

    public User findById(int id){ // get a specific user
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            User user;
            user = session.find(User.class, id);
            return user;
        }catch(NoResultException exception){
            return null;
        }
    }

    public User findByEmailAndPassword(String mail,String password){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String command = "select s from User s where s.password = :pippo AND s.email = :mail";
            Query query = session.createQuery(command);
            query.setParameter("pippo",password);
            query.setParameter("mail",mail);
            return (User) query.getSingleResult();
        }catch(NoResultException exception){
            return null;
        }
    }
}
