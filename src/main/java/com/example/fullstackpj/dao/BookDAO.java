package com.example.fullstackpj.dao;
import java.util.List;
import com.example.fullstackpj.entities.Book;
import com.example.fullstackpj.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookDAO {
    public void deleteById(int id){ //delete a specific booking instance
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Book book;
            book = session.find(Book.class,id);
            session.delete(book);
            session.flush();
            transaction.commit();
        }catch(Exception e){
            if(transaction == null ){
                e.printStackTrace();
            }
        }
    }

    public void saveOrUpdateBook(Book book){ //change state for a specific book
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(book);
            session.flush();
            transaction.commit();
        }
        catch(Exception e){
            if(transaction == null ){
                e.printStackTrace();
            }
        }
    }

    public List<Book> findAll(){ //view all cars
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return  session.createQuery("from Book",Book.class).list();
        }
    }

    public Book findById(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Book book;
            book = session.find(Book.class, id);
            return book;
        }
    }
}
