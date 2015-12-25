package com.insa.thibault.ihm.business;

/**
 * Created by loict on 15/12/2015.
 */
public class Invitation {

    private User sender;
    private User receiver;
    private Restaurant restaurant;
    private int timeHour;
    private int timeMinutes;
    private int nbFriends;
    private int nbPeople;

    public Invitation(User sender, User receiver, Restaurant restaurant, int timeHour, int timeMinutes, int nbFriends, int nbPeople) {
        this.sender = sender;
        this.receiver = receiver;
        this.restaurant = restaurant;
        this.timeHour = timeHour;
        this.timeMinutes = timeMinutes;
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

    public int getTimeHour() {
        return timeHour;
    }

    public void setTimeHour(int timeHour) {
        this.timeHour = timeHour;
    }

    public int getTimeMinutes() {
        return timeMinutes;
    }

    public void setTimeMinutes(int timeMinutes) {
        this.timeMinutes = timeMinutes;
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
