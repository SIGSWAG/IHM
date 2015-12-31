package com.insa.thibault.ihm.business;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by loict on 15/12/2015.
 */
public class Invitation implements Parcelable{

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


    //Parcelable
    public static final Parcelable.Creator<Invitation> CREATOR = new Parcelable.Creator<Invitation>() {
        @Override
        public Invitation createFromParcel(Parcel source) {
            return new Invitation(source);
        }

        @Override
        public Invitation[] newArray(int size) {
            return new Invitation[size];
        }
    };




    public Invitation(Parcel source){
        sender = source.readParcelable(getClass().getClassLoader());
        receiver = source.readParcelable(getClass().getClassLoader());
        restaurant = source.readParcelable(getClass().getClassLoader());
        timeHour = source.readInt();
        timeMinutes = source.readInt();
        nbFriends = source.readInt();
        nbPeople = source.readInt();

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeParcelable(sender, 0);
        dest.writeParcelable(receiver, 0);
        dest.writeParcelable(restaurant, 0);
        dest.writeInt(timeHour);
        dest.writeInt(timeMinutes);
        dest.writeInt(nbFriends);
        dest.writeInt(nbPeople);


    }
}
