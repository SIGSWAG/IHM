package com.insa.thibault.ihm.business;

import java.util.Date;

/**
 * Created by loict on 15/12/2015.
 */
public class Invitation {

    private User sender;
    private User receiver;
    private Restaurant restaurant;
    private Date datetime;

    public Invitation(User sender, User receiver, Restaurant restaurant, Date datetime) {
        this.sender = sender;
        this.receiver = receiver;
        this.restaurant = restaurant;
        this.datetime = datetime;
    }


    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

}
