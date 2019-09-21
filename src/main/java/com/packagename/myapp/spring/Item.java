package com.packagename.myapp.spring;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name="item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name, category, sellerName, image, description;
    private double price;
    protected Item(){}

    public Item(String name, String category, String image, String description ,double price ,User user) {
        this.name = name;
        this.category = category;
        this.price=price;
        this.image = image;
        this.description=description;
        sellerName = user.getUserName();
    }

    public Item(String name, String category, String description ,double price,User user) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.description=description;
        sellerName = user.getUserName();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUser(@Autowired userRep u) {
        return u.findByUserName(sellerName);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUser(User user) {
        sellerName = user.getUserName();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
