package com.example.fullstackpj.Util;
import java.util.List;
import com.example.fullstackpj.Entities.User;
import com.example.fullstackpj.DAO.UserDAO;
public class UserUtil {

    private UserDAO userDao = new UserDAO();

    public void saveUser(User user){
        userDao.saveUser(user);
    }

    public void removeUser(int x){
        userDao.deleteUser(x);
    }

    public void printUserList(){
        List <User> userList = userDao.getUserList();
        userList.forEach(x -> System.out.println("The current user id is: " + x.getUserId()));
    }

    public void printUser(int x){
        User userToPrint = userDao.getUser(x);
        System.out.println("The selected user is: " + userToPrint.getUserId());
    }
}
