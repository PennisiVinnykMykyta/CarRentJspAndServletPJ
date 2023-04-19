package com.example.fullstackpj;

import com.example.fullstackpj.Util.*;
import com.example.fullstackpj.Entities.*;
public class TestClass {
    public static void main(String[] args){
        UserUtil user = new UserUtil();

        user.saveUser(new User (2));
        user.saveUser(new User (3));
        user.saveUser(new User (4));
        user.saveUser(new User (5));
        user.saveUser(new User (6));
        user.printUser(4);
        user.removeUser(5);
        user.removeUser(1);
        user.printUserList();




    }
}
