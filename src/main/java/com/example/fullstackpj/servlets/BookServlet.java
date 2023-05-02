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
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "BookServlet", value = "/book")
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        switch (command){
            case "bookList":
                viewBookList(request,response);
                break;
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAO bookDao = new BookDAO();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAO bookDao = new BookDAO();
        int id = Integer.parseInt(request.getParameter("deleteID"));
        bookDao.deleteById(id);
        if(request.getParameter("adminID").isEmpty() || request.getParameter("adminID") == null){
            UserDAO userDAO = new UserDAO();
            int userID = Integer.parseInt(request.getParameter("userID"));
            User user = userDAO.findById(userID);
            request.setAttribute("user",user);
            request.setAttribute("bookingList",user.getBookings());
            request.getRequestDispatcher("userHomepage.jsp").forward(request,response);
        }else{
            viewBookList(request,response);
        }
    }

    private void viewBookList(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        int id = Integer.parseInt(request.getParameter("adminID"));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(id);
        request.setAttribute("admin", user);
        BookDAO bookDAO = new BookDAO();
        List<Book> bookList = bookDAO.findAll();
        request.setAttribute("bookList",bookList);
        request.getRequestDispatcher("bookList.jsp").forward(request,response);
    }

    private void addBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        LocalDate startDateLocal = LocalDate.parse(startDate);


        String endDate = request.getParameter("endDate");
        LocalDate endDateLocal = LocalDate.parse(endDate);


        int userId;
        int carId = Integer.parseInt(request.getParameter("carID"));

        if(request.getParameter("adminID").isEmpty() || request.getParameter("adminID") == null){
            userId = Integer.parseInt(request.getParameter("userID"));
        }else{
            userId = Integer.parseInt(request.getParameter("adminID"));
        }
        //find the user and the car
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(userId);
        CarDAO carDAO = new CarDAO();
        Car car = carDAO.findById(carId);

        //create a booking with the correct user and the car
        BookDAO bookDao = new BookDAO();
        Book book = new Book(user,car,startDateLocal,endDateLocal);
        bookDao.saveOrUpdateBook(book);

        request.setAttribute("user",user);

        if(request.getParameter("adminID").isEmpty() || request.getParameter("adminID") == null){
            request.getRequestDispatcher("userHomepage.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);
        }
    }

    protected void showBookProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer id = Integer.parseInt(request.getParameter("bookID"));
        Integer userID = Integer.parseInt(request.getParameter("userID"));

        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.findById(id);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(userID);

        request.setAttribute("book",book);
        request.setAttribute("user",user);
        request.getRequestDispatcher("changeBook.jsp").forward(request,response);
    }

    protected void acceptBook(HttpServletRequest request, HttpServletResponse response) throws  ServletException,IOException {
        Integer id = Integer.parseInt(request.getParameter("bookID"));
        Integer userID = Integer.parseInt(request.getParameter("adminID"));

        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.findById(id);
        book.setValid(true);
        bookDAO.saveOrUpdateBook(book);
        List<Book> bookiList = bookDAO.findAll();
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(userID);


        request.setAttribute("admin",user);
        request.setAttribute("bookList",bookiList);

        request.getRequestDispatcher("bookList.jsp").forward(request,response);

    }

    protected void checkBook(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        String msg = ""; //message to be printed in case of an error
        if(request.getParameter("startDate").isEmpty() || request.getParameter("endDate").isEmpty()){ //make sure user has selected the dates
            msg = "ERROR: Please select both dates!";
            request.setAttribute("message",msg);
            Integer id;
            String key = "";
            if (request.getParameter("adminID").isEmpty() || request.getParameter("adminID") == null) {
                id = Integer.parseInt(request.getParameter("userID"));
                key="user";
            } else {
                id = Integer.parseInt(request.getParameter("adminID"));
                key="admin";
            }
            User user = userDAO.findById(id);
            request.setAttribute(key, user);
            request.getRequestDispatcher("addBook.jsp").forward(request, response);
        }

        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));

        if(startDate.isAfter(endDate)) { //check if the start date and end date are matching
            msg = "ERROR: Start date must be earlier then Drop-off date!";
            request.setAttribute("message",msg);
            Integer id;
            String key = "";
            if (request.getParameter("adminID").isEmpty() || request.getParameter("adminID") == null) {
                id = Integer.parseInt(request.getParameter("userID"));
                key="user";
            } else {
                id = Integer.parseInt(request.getParameter("adminID"));
                key="admin";
            }
            User user = userDAO.findById(id);
            request.setAttribute(key, user);
            request.getRequestDispatcher("addBook.jsp").forward(request, response);
        }else if(startDate.isBefore(LocalDate.now().plusDays(2))){ //check that the start date is not set before the current date
            msg = "ERROR: start date cannot be set before at least 2 days prior to the current date. First available date is: " + LocalDate.now().plusDays(2);
            request.setAttribute("message",msg);
            Integer id;
            String key="";
            if (request.getParameter("adminID").isEmpty() || request.getParameter("adminID") == null) {
                id = Integer.parseInt(request.getParameter("userID"));
                key="user";
            } else {
                id = Integer.parseInt(request.getParameter("adminID"));
                key="admin";
            }
            User user = userDAO.findById(id);
            request.setAttribute(key, user);
            request.getRequestDispatcher("addBook.jsp").forward(request, response);
        }else{
            BookDAO bookDAO = new BookDAO();
            if(request.getParameter("object").equalsIgnoreCase("add")){
                //first we get the conflicting bookings
                List<Book> bookList = bookDAO.conflictingBookings(startDate,endDate);
                CarDAO carDAO = new CarDAO();
                List<Integer> bookedCarsIDS = new ArrayList<>();
                //now we get the list of all unavailable cars
                for(Book x : bookList){
                    int id = x.getCar().getId();
                    bookedCarsIDS.add(id);
                }
                //and now we get the resulting list of the available cars
                List<Car> availableCars = carDAO.availableCars(bookedCarsIDS);
                request.setAttribute("carList",availableCars);
                request.setAttribute("startDate",startDate);
                request.setAttribute("endDate",endDate);

                //now we see if it was the user or admin to request this page
                Integer id;
                String key ="";
                if (request.getParameter("adminID").isEmpty() || request.getParameter("adminID") == null) {
                    id = Integer.parseInt(request.getParameter("userID"));
                    key="user";
                } else {
                    id = Integer.parseInt(request.getParameter("adminID"));
                    key="admin";
                }
                User user = userDAO.findById(id);
                request.setAttribute(key, user);
                request.getRequestDispatcher("carBookList.jsp").forward(request, response);
            }else if(request.getParameter("object").equalsIgnoreCase("modify")){

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        switch (command){
            case "addBook":
                addBooking(request,response);
                break;
            case "delete":
                doDelete(request,response);
                break;
            case "addBookView" :
                showBookProfile(request,response);
                break;
            case "acceptBook" :
                acceptBook(request,response);
                break;
            case "checkBook" :
                checkBook(request,response);
                break;
        }
    }
}
