package com.example.fullstackpj.servlets;

import com.example.fullstackpj.dao.UserDAO;
import com.example.fullstackpj.entities.Book;
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

        String command = request.getParameter("command");

        switch (command){
            case "customer" :
                showUserHomepage(request,response);
                break;
            case "admin" :
                showAdminHomepage(request,response);
                break;
            case "profile" :
                showUserProfile(request,response);
                break;
        }
    }

    protected  void showUserHomepage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer id = Integer.parseInt(request.getParameter("id"));
        UserDAO userDAO = new UserDAO();
        User user =  userDAO.findById(id);
        request.setAttribute("user",user);
        request.setAttribute("bookingList",user.getBookings());
        request.getRequestDispatcher("userHomepage.jsp").forward(request,response);
    }

    protected void showAdminHomepage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        List<User> userList = userDAO.findAll();
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        int id = (int) request.getAttribute("id");
        User user = userDao.findById(id);
        userDao.saveOrUpdateUser(user);
        request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        int id = (int) request.getAttribute("id");
        userDao.deleteById(id);
        request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);
    }

    private void showUserProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(id);
        request.setAttribute("user",user);
        if(request.getParameter("command").equalsIgnoreCase("addUserView")){
            request.getRequestDispatcher("changeProfile.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("showProfile.jsp").forward(request,response);
        }
    }

    protected  void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get user info from addUser.jsp
        Integer id = Integer.parseInt(request.getParameter("id"));
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
        User user = null;
        if(id != null){
            user = new User(id,email,password, firstName, lastName, userType, date);
        }else{
            user = new User(email,password, firstName, lastName, userType, date);
        }
        userDao.saveOrUpdateUser(user);

        if(request.getParameter("type").equalsIgnoreCase("customer")){
            request.setAttribute("user",user); //here the id of a user to modify and id of a user who requested it is the same
            List<Book> bookList = user.getBookings();
            request.setAttribute("bookingList", bookList);
            request.getRequestDispatcher("userHomepage.jsp").forward(request,response);
        }else{
            //will need the id of the admin!!!
            request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        switch (command){
            case "addUser":
                addUser(request,response);
                break;
            case "addUserView" :
                showUserProfile(request,response);
            default:
                request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);
        }
    }
}
