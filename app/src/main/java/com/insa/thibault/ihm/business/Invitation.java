package com.insa.thibault.ihm.business;

import java.util.Date;

/**
 * Created by loict on 15/12/2015.
 */
public class Invitation {

    private User sender;
    private User receiver;
    private Restaurant restaurant;
    private Date dateTime;
    private int nbFriends;
    private int nbPeople;

    public Invitation(User sender, User receiver, Restaurant restaurant, Date datetime, int nbFriends, int nbPeople) {
        this.sender = sender;
        this.receiver = receiver;
        this.restaurant = restaurant;
        this.dateTime = datetime;
        this.nbFriends = nbFriends;
        this.nbPeople = nbPeople;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getNbFriends() {
        return nbFriends;
    }

    public void setNbFriends(int nbFriends) {
        this.nbFriends = nbFriends;
    }

    public int getNbPeople() {
        return nbPeople;
    }

    public void setNbPeople(int nbPeople) {
        this.nbPeople = nbPeople;
    }
}
