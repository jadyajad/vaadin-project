package com.packagename.myapp.spring;

import com.vaadin.flow.templatemodel.AllowClientUpdates;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName, lastName, userName, password;
//    @OneToMany(mappedBy="userid", fetch=FetchType.EAGER)
//    private List<Item> selling= new LinkedList<>();
//    @OneToMany(mappedBy="user", fetch=FetchType.EAGER)
//    private List<Item> bought = new LinkedList<>();

    protected User(){}

    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
//        selling = new ArrayList<>();
//        bought = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public List<Item> getSelling(@Autowired ItemRep i) {
//        return i.findAllById(selling);
//    }
//
//    public void addSellings(Item I) {
//        selling.add(I.getId());
//    }
//
//    public List<Item> getBought(@Autowired ItemRep i) {
//        return i.findAllById(bought);
//    }
//
//    public void Buy(Item I) {
//        bought.add(I.getId());
//    }
}
