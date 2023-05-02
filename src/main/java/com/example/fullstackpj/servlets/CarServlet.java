package com.example.fullstackpj.servlets;

import com.example.fullstackpj.dao.CarDAO;
import com.example.fullstackpj.dao.UserDAO;
import com.example.fullstackpj.entities.Car;
import com.example.fullstackpj.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "CarServlet", value = "/car")
public class CarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        switch(command){
            case "carList" :
                viewCarList(request,response);
                break;
        }


    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarDAO carDao = new CarDAO();
        int id = Integer.parseInt(request.getParameter("deleteID")); //id to delete
        carDao.deleteById(id);
        viewCarList(request,response);
    }

    protected  void addCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get user info from addUser.jsp
        String color = request.getParameter("color");
        String plateNumber = request.getParameter("plate");
        String model = request.getParameter("model");
        String brand = request.getParameter("brand");

        //now we create the new car and add it
        CarDAO carDao = new CarDAO();
        Car car;
        String stringID = request.getParameter("carID");
        if(stringID == null || stringID.isEmpty()){
            car = new Car(plateNumber,color,model,brand);
        }else{
            Integer carID = Integer.parseInt(request.getParameter("carID"));
            car = new Car(carID,plateNumber,color,model,brand);
        }
        carDao.saveOrUpdateCar(car);

        Integer adminID = Integer.parseInt(request.getParameter("adminID"));
        UserDAO userDAO = new UserDAO();
        User admin = userDAO.findById(adminID);
        request.setAttribute("user",admin);
        request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);

    }



    protected void viewCarList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("adminID"));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(id);
        request.setAttribute("admin", user);
        CarDAO carDAO = new CarDAO();
        List<Car> carList = carDAO.findAll();
        request.setAttribute("carList",carList);
        request.getRequestDispatcher("carList.jsp").forward(request,response);
    }

    protected void showCarProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        Integer id = Integer.parseInt(request.getParameter("carID"));
        Integer adminID = Integer.parseInt(request.getParameter("adminID"));
        CarDAO carDAO = new CarDAO();
        Car car = carDAO.findById(id);
        request.setAttribute("car",car);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findById(adminID);
        request.setAttribute("admin",user);
        request.getRequestDispatcher("changeCar.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        switch (command){
            case "addCar":
                addCar(request,response);
                break;
            case "addCarPage" :
                doPut(request,response);
                break;
            case "delete" :
                doDelete(request,response);
                break;
            case "addCarView" :
                showCarProfile(request,response);
        }
    }
}
