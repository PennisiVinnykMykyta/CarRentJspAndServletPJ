package com.example.fullstackpj.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements  Serializable{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int id;

    @Column(name ="first_name")
    private String firstName;

    @Column(name ="last_name")
    private String lastName;

    @Column(name ="user_type")
    private String userType;

    public String getUserType(){ return userType;}

    public void setUserType(String type){ this.userType = type;}

    public String getFirstName(){ return firstName;}

    public void setFirstName(String name){ this.firstName = name;}

    public String getLastName(){ return lastName;}

    public void setLastName(String name){ this.lastName = name;}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(){}

    public User(int id){
        this.id =id;
    }

}
