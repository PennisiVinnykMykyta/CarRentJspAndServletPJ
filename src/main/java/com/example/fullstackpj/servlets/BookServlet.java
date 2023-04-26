package com.example.fullstackpj.servlets;

import com.example.fullstackpj.dao.BookDAO;
import com.example.fullstackpj.dao.CarDAO;
import com.example.fullstackpj.dao.UserDAO;
import com.example.fullstackpj.entities.Book;
import com.example.fullstackpj.entities.Car;
import com.example.fullstackpj.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet(name = "BookServlet", value = "/booking")
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAO bookDao = new BookDAO();

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAO bookDao = new BookDAO();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAO bookDao = new BookDAO();
        int id = Integer.parseInt(request.getParameter("user_id"));
        bookDao.deleteById(id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        switch (command){
            case "addUser":
                addBooking(request,response);
                break;
        }
    }

    private void addBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateForm = request.getParameter("StartDate");
        LocalDate startDate = null;
        startDate = LocalDate.parse(dateForm);


        dateForm = request.getParameter("EndDate");
        LocalDate endDate = null;
        endDate = LocalDate.parse(dateForm);


        int userId = Integer.parseInt(request.getParameter("userID"));
        int carId = Integer.parseInt(request.getParameter("carID"));

        //find the user and the car
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(userId);
        CarDAO carDAO = new CarDAO();
        Car car = carDAO.findById(carId);

        //create a booking with the correct user and the car
        BookDAO bookDao = new BookDAO();
        Book book = new Book(user,car,startDate,endDate);
        bookDao.saveOrUpdateBook(book);


    }
}
