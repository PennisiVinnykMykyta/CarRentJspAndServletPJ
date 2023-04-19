package com.example.fullstackpj.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements  Serializable{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int id;

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
