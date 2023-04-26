package com.example.fullstackpj.servlets;

import com.example.fullstackpj.dao.UserDAO;
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
import java.util.Date;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/user")

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String command = request.getParameter("userType");

        switch (command){
            case "customer" :
                break;
            case "admin" :
                showUserList(request,response);
                break;
        }
    }

    protected void showUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        List<User> userList = userDAO.findAll();
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        int id = (int) request.getAttribute("id");
        User user = userDao.findById(id);
        userDao.saveOrUpdateUser(user);
        request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        int id = (int) request.getAttribute("id");
        userDao.deleteById(id);
        request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        switch (command){
            case "addUser":
                addUser(request,response);
                break;
            default:
                request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);
        }
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
        User user = new User(email,password, firstName, lastName, userType, date, null);
        userDao.saveOrUpdateUser(user);

        request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);

    }
}
