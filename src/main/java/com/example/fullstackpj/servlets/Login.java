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
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/login")
public class Login extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        //check the type of user that is trying to log in
        String guestMail = request.getParameter("email");
        String guestPassword = request.getParameter("password");

        UserDAO userDao = new UserDAO();
        User user = userDao.findByEmailAndPassword(guestMail,guestPassword);

        if (user != null) {                         //check if the user with given credentials is present in the database
            request.setAttribute("user", user);  //if the user exists prepare to send his info to the appropriate page
            List<Book> bookList = user.getBookings();
            request.setAttribute("bookingList", bookList);
            if (user.getType().equals(UserType.ADMIN)){
                request.getRequestDispatcher("adminHomepage.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("userHomepage.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("failedLogin.jsp").forward(request, response);
        }
    }
}
