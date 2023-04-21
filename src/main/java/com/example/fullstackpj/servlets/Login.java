package com.example.fullstackpj.servlets;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


import com.example.fullstackpj.dao.UserDAO;
import com.example.fullstackpj.entities.User;
import com.example.fullstackpj.entities.enums.UserType;

@WebServlet(name = "LoginServlet", value = "/login")
public class Login extends HttpServlet {
    private String message = "Sorry but these credentials do not match any user in our database";

    private UserDAO userDao;
    private User user;

@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        UserType userType;

        if(request.getParameter("type").equalsIgnoreCase("admin")){
            userType = UserType.ADMIN;
        }else{
            userType = UserType.CUSTOMER;
        }

        //check if the user with given credentials is present in database
        List<User> userList = userDao.findAll();
        boolean userIsPresent = false;
        for(User x : userList){
            if(x.getFirstName() == request.getParameter("firstName") && x.getLastName() == request.getParameter("lastName") && x.getPassword() == request.getParameter("password")){
                userIsPresent = true;
           }
        }


        if(userIsPresent){
            //direct user to the homepage
        }else{
            out.println("<html><body>");
            out.println("<h1>" + message + "</h1>");
            out.println("</body></html>");
        }
    }
    public void destroy() {
    }
}
