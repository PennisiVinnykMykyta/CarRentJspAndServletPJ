package com.example.fullstackpj.servlets;

import com.example.fullstackpj.dao.BookDAO;
import com.example.fullstackpj.dao.CarDAO;
import com.example.fullstackpj.dao.UserDAO;
import com.example.fullstackpj.entities.Book;
import com.example.fullstackpj.entities.Car;
import com.example.fullstackpj.entities.User;
import com.example.fullstackpj.entities.enums.UserType;

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
        switch (command) {
            case "bookList":
                viewBookList(request, response);
                break;
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAO bookDao = new BookDAO();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //delete the booking
        BookDAO bookDao = new BookDAO();
        int bookID = Integer.parseInt(request.getParameter("deleteID"));
        bookDao.deleteById(bookID);

        //set all of important info for user and their bookings
        int userID = Integer.parseInt(request.getParameter("userID"));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(userID);
        UserType userType = user.getType();
        request.setAttribute("user", user);
        List<Book> bookList = user.getBookings();
        request.setAttribute("bookList", bookList);

        //redirect the user to the correct page
        if (userType == UserType.ADMIN) {
            request.getRequestDispatcher("bookList.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("userHomepage.jsp").forward(request, response);
        }
    }

    protected void viewBookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = Integer.parseInt(request.getParameter("userID"));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(userID);
        request.setAttribute("user", user);
        BookDAO bookDAO = new BookDAO();
        List<Book> bookList = bookDAO.findAll();
        request.setAttribute("bookList", bookList);
        request.getRequestDispatcher("bookList.jsp").forward(request, response);
    }

    protected void addBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        LocalDate startDateLocal = LocalDate.parse(startDate);

        String endDate = request.getParameter("endDate");
        LocalDate endDateLocal = LocalDate.parse(endDate);

        int userID = Integer.parseInt(request.getParameter("userID"));
        int carID = Integer.parseInt(request.getParameter("carID"));

        //find the user and the car
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(userID);
        UserType userType = user.getType();

        CarDAO carDAO = new CarDAO();
        Car car = carDAO.findById(carID);
        Book book;

        //create a booking with the correct user and the car
        BookDAO bookDao = new BookDAO();
        if (request.getParameter("bookID").isEmpty() || request.getParameter("bookID") == null) {
            book = new Book(user, car, startDateLocal, endDateLocal);
        } else {
            Integer id = Integer.parseInt(request.getParameter("bookID"));
            book = new Book(id, user, car, startDateLocal, endDateLocal);
        }

        bookDao.saveOrUpdateBook(book);

        request.setAttribute("user", user);

        if (userType == UserType.CUSTOMER) {
            List<Book> bookList = user.getBookings();
            request.setAttribute("bookList",bookList);
            request.getRequestDispatcher("userHomepage.jsp").forward(request, response);

        } else {
            request.getRequestDispatcher("adminHomepage.jsp").forward(request, response);
        }
    }

    protected void showBookProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //HEre we look at the user type and forward them to the appropriate page
        int id = Integer.parseInt(request.getParameter("bookID"));
        int userID = Integer.parseInt(request.getParameter("userID"));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(userID);
        UserType userType = user.getType();
        request.setAttribute("user", user);

        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.findById(id);
        request.setAttribute("book", book);

        if (userType == UserType.CUSTOMER) {
            request.getRequestDispatcher("changeBook.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("changeBookAdmin.jsp").forward(request, response);
        }
    }

    protected void acceptBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("bookID"));
        Integer userID = Integer.parseInt(request.getParameter("userID"));

        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.findById(id);
        book.setValid(true);
        bookDAO.saveOrUpdateBook(book);
        List<Book> bookiList = bookDAO.findAll();
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(userID);


        request.setAttribute("user", user);
        request.setAttribute("bookList", bookiList);

        request.getRequestDispatcher("bookList.jsp").forward(request, response);

    }

    protected void checkBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //message to be printed in case of an error
        String msg = "";

        //set up all vital info
        UserDAO userDAO = new UserDAO();
        int userID = Integer.parseInt(request.getParameter("userID"));
        User user = userDAO.findById(userID);
        UserType userType = user.getType();
        request.setAttribute("user", user);
        Integer carID;
        BookDAO bookDAO = new BookDAO();


        //first we check if the method was called to add or to modify the booking
        if(request.getParameter("object").equalsIgnoreCase("add")){
            //make sure user has selected both dates
            if (request.getParameter("startDate").isEmpty() || request.getParameter("endDate").isEmpty()) {
                msg = "ERROR: Please select both dates!";
                request.setAttribute("message", msg);
                if (userType == UserType.ADMIN) {
                    request.getRequestDispatcher("addBookAdmin.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("addBook.jsp").forward(request, response);
                }
            }

            LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
            LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));

            //make sure start and end dates are matching
            if (startDate.isAfter(endDate)) {
                msg = "ERROR: Start date must be earlier then Drop-off date!";
                request.setAttribute("message", msg);
                if (userType == UserType.ADMIN) {
                    request.getRequestDispatcher("addBookAdmin.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("addBook.jsp").forward(request, response);
                }
            }
            //make sure the start date is not set before the current date
            if (startDate.isBefore(LocalDate.now().plusDays(2))) {
                msg = "ERROR: start date cannot be set before at least 2 days prior to the current date. First available date is: " + LocalDate.now().plusDays(2);
                request.setAttribute("message", msg);
                if (userType == UserType.ADMIN) {
                    request.getRequestDispatcher("addBookAdmin.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("addBook.jsp").forward(request, response);
                }
            }

            //first we get the conflicting bookings
            List<Book> bookList = bookDAO.conflictingBookings(startDate, endDate);
            CarDAO carDAO = new CarDAO();
            List<Integer> bookedCarsIDS = new ArrayList<>();
            //now we get the list of all unavailable cars
            for (Book book : bookList) {
                carID = book.getCar().getId();
                bookedCarsIDS.add(carID);
            }

            //and now we get the resulting list of the available cars
            List<Car> availableCars = carDAO.availableCars(bookedCarsIDS);
            request.setAttribute("carList", availableCars);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);

            if(userType == UserType.ADMIN){
                request.getRequestDispatcher("carBookList.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("carBookListUser.jsp").forward(request, response);
            }

        }else{
            //now we enter the branch where we look to modify our booking
            Integer bookID = Integer.parseInt(request.getParameter("bookID"));
            Book book = bookDAO.findById(bookID);
            request.setAttribute("book", book);

            //first we check if the booking can be modified at all
            if (LocalDate.now().isAfter(book.getStartDate().minusDays(2))) {
                msg = "ERROR: Cannot modify booking 2 days prior to your Starting date!";

                request.setAttribute("message", msg);
                if (userType == UserType.ADMIN) {
                    request.getRequestDispatcher("adminHomepage.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("userHomepage.jsp").forward(request, response);
                }
            }
            //make sure user has selected both dates
            if (request.getParameter("startDate").isEmpty() || request.getParameter("endDate").isEmpty()) {
                msg = "ERROR: Please select both dates!";
                request.setAttribute("message", msg);
                if (userType == UserType.ADMIN) {
                    request.getRequestDispatcher("changeBookAdmin.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("changeBook.jsp").forward(request, response);
                }
            }

            LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
            LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));

            //make sure start and end dates are matching
            if (startDate.isAfter(endDate)) {
                msg = "ERROR: Start date must be earlier then Drop-off date!";
                request.setAttribute("message", msg);
                if (userType == UserType.ADMIN) {
                    request.getRequestDispatcher("changeBookAdmin.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("changeBook.jsp").forward(request, response);
                }
            }
            //make sure the start date is not set before the current date
            if (startDate.isBefore(LocalDate.now().plusDays(2))) {
                msg = "ERROR: start date cannot be set before at least 2 days prior to the current date. First available date is: " + LocalDate.now().plusDays(2);
                request.setAttribute("message", msg);
                if (userType == UserType.ADMIN) {
                    request.getRequestDispatcher("changeBookAdmin.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("changeBook.jsp").forward(request, response);
                }
            }

            //once all controls have been passed
            ////////////////////////////////////
            //we ge the conflicting bookings
            List<Book> bookList = new ArrayList<>();
            bookList = bookDAO.conflictingBookings(startDate, endDate);
            CarDAO carDAO = new CarDAO();
            List<Integer> bookedCarsIDS = new ArrayList<>();

            //now we get the list of all unavailable cars excluding the one we've already booked
            for (Book bookCheck : bookList) {
                if (bookCheck.getId() != book.getId()) { //we make sure to NOT add our already booked car to the booked car list
                    carID = bookCheck.getCar().getId();
                    bookedCarsIDS.add(carID);
                }
            }

            List<Car> availableCars = carDAO.availableCars(bookedCarsIDS);
            request.setAttribute("carList", availableCars);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);

            if(userType == UserType.ADMIN){
                request.getRequestDispatcher("carBookList.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("carBookListUser.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        switch (command) {
            case "addOrChangeBookMethod":
                addBooking(request, response);
                break;
            case "delete":
                doDelete(request, response);
                break;
            case "addOrChangeBooking":
                showBookProfile(request, response);
                break;
            case "acceptBook":
                acceptBook(request, response);
                break;
            case "checkBook":
                checkBook(request, response);
                break;
        }
    }
}
