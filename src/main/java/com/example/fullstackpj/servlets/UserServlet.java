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
            case "adminProfile" :
                showAdminProfile(request,response);
                break;
            case "userList" :
                viewUserList(request,response);
                break;
        }
    }

    protected  void showUserHomepage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if(request.getParameter("adminID") == null || request.getParameter("adminID").isEmpty()){
            Integer id = Integer.parseInt(request.getParameter("id"));
            UserDAO userDAO = new UserDAO();
            User user =  userDAO.findById(id);
            request.setAttribute("user",user);
            request.setAttribute("bookingList",user.getBookings());
            request.getRequestDispatcher("userHomepage.jsp").forward(request,response);
        }else{
            showAdminHomepage(request,response);
        }
    }

    protected void showAdminHomepage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("adminID"));
        UserDAO userDAO = new UserDAO();
        User user =  userDAO.findById(id);
        List<User> userList = userDAO.findAll();
        request.setAttribute("user",user);
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        Integer id = Integer.parseInt(request.getParameter("adminID"));
        User user = userDao.findById(id);
        request.setAttribute("admin",user);

        if(request.getParameter("object").equalsIgnoreCase("user")){
            request.getRequestDispatcher("addUser.jsp").forward(request,response);
        } else if (request.getParameter("object").equalsIgnoreCase("car")) {
            request.getRequestDispatcher("addCar.jsp").forward(request,response);
        }

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        Integer id = Integer.parseInt(request.getParameter("deleteID")); //get id to delete
        userDao.deleteById(id);
        viewUserList(request,response);
    }

    private void showUserProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String adminID = request.getParameter("adminID");
        request.setAttribute("adminID",adminID);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(id);
        request.setAttribute("user",user);
        if(request.getParameter("command").equalsIgnoreCase("addUserView")){
            request.getRequestDispatcher("changeProfile.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("showProfile.jsp").forward(request,response);
        }
    }

    private void showAdminProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(id);
        request.setAttribute("user",user);
        if(request.getParameter("command").equalsIgnoreCase("addAdminView")){
            request.getRequestDispatcher("changeProfileAdmin.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("showProfileAdmin.jsp").forward(request,response);
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
        }else{
            userType = UserType.CUSTOMER;
        }

        //now we create the user and add it
        UserDAO userDao = new UserDAO();
        User user = null;
        String stringID = request.getParameter("id");

        if(stringID == null){
            user = new User(email,password, firstName, lastName, userType, date);
        }else{
            Integer id = Integer.parseInt(request.getParameter("id"));
            user = new User(id,email,password, firstName, lastName, userType, date);
        }
        userDao.saveOrUpdateUser(user);

        String adminCheck = request.getParameter("adminID"); //see if the request was done by admin or by the user

        if(adminCheck == null || adminCheck.isEmpty()){
            request.setAttribute("user",user);
            List<Book> bookList = user.getBookings();
            request.setAttribute("bookingList", bookList);
            request.getRequestDispatcher("userHomepage.jsp").forward(request,response);
        }else{
            Integer adminID = Integer.parseInt(request.getParameter("adminID"));
            User admin = userDao.findById(adminID);
            request.setAttribute("user",admin);
            request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);
        }

    }


    protected void viewUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("adminID"));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(id);
        request.setAttribute("admin", user);
        List<User> userList = userDAO.findAll();
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("userList.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        switch (command){
            case "addUser": //operation to add a user
                addUser(request,response);
                break;
            case "addUserView" : // the request to either show the user page or to modify it
                showUserProfile(request,response);
                break;
            case "addAdminView" : // the request to either show the admin page or to modify it
                showAdminProfile(request,response);
                break;
            case "delete" :
                doDelete(request,response);
                break;
            case "addPage" : // the request to get the page for the creation of a new user
                doPut(request,response);
                break;
            default:
                request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);
                break;
        }
    }
}
