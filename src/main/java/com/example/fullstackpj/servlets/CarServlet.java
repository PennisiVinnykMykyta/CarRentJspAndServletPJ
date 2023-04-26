package com.example.fullstackpj.servlets;

import com.example.fullstackpj.dao.CarDAO;
import com.example.fullstackpj.entities.Car;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarDAO carDao = new CarDAO();

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarDAO carDao = new CarDAO();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarDAO carDao = new CarDAO();
        int id = Integer.parseInt(request.getParameter("user_id"));
        carDao.deleteById(id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        switch (command){
            case "addCar":
                addCar(request,response);
                break;
        }
    }

    protected  void addCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get user info from addUser.jsp
        String color = request.getParameter("color");
        String plateNumber = request.getParameter("plate");
        String model = request.getParameter("model");
        String brand = request.getParameter("brand");

        //now we create the new car and add it
        CarDAO carDao = new CarDAO();
        Car car = new Car(plateNumber,color,model,brand);
        carDao.saveOrUpdateCar(car);

        request.getRequestDispatcher("adminHomepage.jsp").forward(request,response);

    }
}
