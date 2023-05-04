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
            case "customerHomepage" :
                showUserHomepage(request,response);
                break;
            case "adminHomepage" :
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
            case "bookList" :
                viewAdminBookList(request,response);
                break;
            case "addPage" : // the request to get the page for the creation of a new user
                doPut(request,response);
                break;
        }
    }

    private void viewAdminBookList(HttpServletRequest request, HttpServletResponse response) throws  ServletException,IOException {
        UserDAO userDAO = new UserDAO();
        Integer userID = Integer.parseInt(request.getParameter("userID"));
        User user = userDAO.findById(userID);
        request.setAttribute("user",user);
        List<Book> bookList = user.getBookings();
        request.setAttribute("bookList",bookList);
        request.getRequestDispatcher("bookListAdmin.jsp").forward(request,response);
    }

    protected  void showUserHomepage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Integer userID = Integer.parseInt(request.getParameter("userID"));
        UserDAO userDAO = new UserDAO();
        User user =  userDAO.findById(userID);
        List<Book> bookList = user.getBookings();
        request.setAttribute("bookList",bookList);
        request.setAttribute("user",user);
        request.getRequestDispatcher("userHomepage.jsp").forward(request,response);
    }

    protected void showAdminHomepage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userID = Integer.parseInt(request.getParameter("userID"));
        UserDAO userDAO = new UserDAO();
        User user =  userDAO.findById(userID);
        request.setAttribute("user",user);
        request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        Integer userID = Integer.parseInt(request.getParameter("userID"));
        User user = userDao.findById(userID);
        request.setAttribute("user",user);

        switch (request.getParameter("object")){
            case "user" :
                request.getRequestDispatcher("addUser.jsp").forward(request,response);
                break;
            case "car"  :
                request.getRequestDispatcher("addCar.jsp").forward(request,response);
                break;
            case "book" :
                request.getRequestDispatcher("addBook.jsp").forward(request,response);
                break;
            case "bookAdmin" :
                request.getRequestDispatcher("addBookAdmin.jsp").forward(request,response);
                break;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        Integer id = Integer.parseInt(request.getParameter("deleteID")); //get id to delete
        userDao.deleteById(id);
        viewUserList(request,response);
    }

    private void showUserProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userID = Integer.parseInt(request.getParameter("userID"));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(userID);
        request.setAttribute("user",user);
        if(request.getParameter("command").equalsIgnoreCase("ChangeProfileUser")){
            if(request.getParameter("object").equals("adminRequest")){
                Integer changeID = Integer.parseInt(request.getParameter("changeID"));
                User changeUser = userDAO.findById(changeID);
                request.setAttribute("changeUser",changeUser);
                request.getRequestDispatcher("changeProfileUser.jsp").forward(request,response);
            }
            request.getRequestDispatcher("changeProfile.jsp").forward(request,response);

        }else{
            request.getRequestDispatcher("showProfile.jsp").forward(request,response);
        }
    }

    private void showAdminProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("userID"));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(id);
        request.setAttribute("user",user);
        if(request.getParameter("command").equalsIgnoreCase("changeProfile")){
            request.getRequestDispatcher("changeProfileAdmin.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("showProfileAdmin.jsp").forward(request,response);
        }
    }

    protected  void addOrChangeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get user info from addUser.jsp
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String dateForm = request.getParameter("date");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = simpleDateFormat.parse(dateForm);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        User user;
        UserType userType;
        UserDAO userDAO = new UserDAO();
        Integer userID;
        userID = Integer.parseInt(request.getParameter("userID"));

        if(request.getParameter("request").equalsIgnoreCase("change")){

            //check if it is an admin operating on an element of a userList
            if(request.getParameter("object").equals("adminChange")){
                Integer changeID = Integer.parseInt(request.getParameter("changeID"));
                User changeUser = userDAO.findById(changeID);
                userType = changeUser.getType();
                user = new User(changeID,email,password,firstName,lastName,userType,date);
            }else{
                //Change User
                user = userDAO.findById(userID);
                //find the type of the user to change
                userType = user.getType();
                user = new User(userID,email,password,firstName,lastName,userType,date);
            }

        }else{
            //Add User
            String type = request.getParameter("type");

            //check what kind of user we are creating
            if (type.equalsIgnoreCase("admin")) {
                userType = UserType.ADMIN;
            }else{
                userType = UserType.CUSTOMER;
            }

            user = new User(email,password, firstName, lastName, userType, date);
        }

        //now we save or update our user
        userDAO.saveOrUpdateUser(user);

        //now we reset the user to the correct one to redirect to the right page
        user = userDAO.findById(userID);
        userType = user.getType();
        request.setAttribute("user",user);

        if(userType == UserType.ADMIN){
            //if it was admin go to adminHomepage
            request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);

        }else{
            //if it was customer got to userHomepage
            request.setAttribute("bookList",user.getBookings());
            request.getRequestDispatcher("userHomepage.jsp").forward(request,response);
        }
    }


    protected void viewUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userID = Integer.parseInt(request.getParameter("userID"));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(userID);
        request.setAttribute("user", user);
        List<User> userList = userDAO.findAll();
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("userList.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        switch (command){
            case "addOrChangeUser": //operation to add a user
                addOrChangeUser(request,response);
                break;
            case "changeProfileUser" : // the request to either show the user page or to modify it
                showUserProfile(request,response);
                break;
            case "changeProfile" : // the request to either show the admin page or to modify it
                showAdminProfile(request,response);
                break;
            case "delete" :
                doDelete(request,response);
                break;
        }
    }
}
