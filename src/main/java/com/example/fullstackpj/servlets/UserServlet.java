package com.example.fullstackpj.servlets;

import com.example.fullstackpj.dao.CarDAO;
import com.example.fullstackpj.dao.UserDAO;
import com.example.fullstackpj.entities.Car;
import com.example.fullstackpj.entities.User;
import com.example.fullstackpj.entities.enums.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@WebServlet(name = "UserServlet", value = "/user")

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        User user = (User) request.getAttribute("user");
        request.setAttribute("name", user.getFirstName());
        request.setAttribute("lastName", user.getLastName());
        request.setAttribute("user", user.getLastName());
        request.setAttribute("password", user.getPassword());
        request.setAttribute("birthDate", user.getBirthDate());

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        int id = (int) request.getAttribute("id");
        User user = userDao.findById(id);
        userDao.saveOrUpdateUser(user);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        int id = (int) request.getAttribute("id");
        userDao.deleteById(id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected  void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get user info from addUser.jsp
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dateForm = request.getParameter("date");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateForm);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String type = request.getParameter("type");
        UserType userType;
        if (type.equalsIgnoreCase("admin")) {
            userType = UserType.ADMIN;
        } else {
            userType = UserType.CUSTOMER;
        }

        //now we create the user and add it
        UserDAO userDao = new UserDAO();
        User user = new User(password, firstName, lastName, userType, date, null);
        userDao.saveOrUpdateUser(user);

    }

    protected  void addCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get user info from addUser.jsp
        String color = request.getParameter("color");
        String plateNumber = request.getParameter("plate");
        String model = request.getParameter("model");
        String brand = request.getParameter("brand");

        //now we create the new car and add it
        CarDAO carDao = new CarDAO();
        Car car = new Car(plateNumber,color,model,brand);
        carDao.saveOrUpdateCar(car);

    }


}
