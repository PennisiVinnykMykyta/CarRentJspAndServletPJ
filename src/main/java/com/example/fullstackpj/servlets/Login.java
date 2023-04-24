package com.example.fullstackpj.servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


import com.example.fullstackpj.dao.UserDAO;
import com.example.fullstackpj.entities.User;

@WebServlet(name = "LoginServlet", value = "/login")
public class Login extends HttpServlet {

@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //check the type of user that is trying to log in
        String guestMail = request.getParameter("email");
        String guestPassword = request.getParameter("password");

        UserDAO userDao = new UserDAO();
        User user = userDao.findByEmailAndPassword(guestMail,guestPassword);

        if(user != null){                          //check if the user with given credentials is present in the database
            request.setAttribute("user",user);  //if the user exists prepare to send his info to the appropriate page
            if(user.getType().equals(0)){
                request.getRequestDispatcher("adminHomepage.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("userHomepage.jsp").forward(request, response);
            }
        }else{
            out.println("<html><body>");
            out.println("<h3>" + "Sorry but these credentials do not match any user in our database" + "</h3>");
            out.println("<a href=\"login.jsp\">Go back to login menu</a>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    public void destroy() {
    }
}
