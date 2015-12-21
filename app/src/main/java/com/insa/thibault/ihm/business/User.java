package com.insa.thibault.ihm.business;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loict on 15/12/2015.
 */
public class User {

    private String firstName;
    private String lastName;
    private Restaurant currentRestaurant;
    private List<User> friends;
    private List<Invitation> receivedInvitations;
    private List<Invitation> sentInvitations;
    private List<Invitation> acceptedInvitations;
    private List<Restaurant> favoritesRestaurant;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentRestaurant = null;
        this.friends = new ArrayList<>();
        this.receivedInvitations = new ArrayList<>();
        this.sentInvitations = new ArrayList<>();
        this.acceptedInvitations = new ArrayList<>();
        this.favoritesRestaurant = new ArrayList<>();
    }

    public User(String firstName, String lastName, Restaurant currentRestaurant, List<User> friends,
                List<Invitation> receivedInvitations, List<Invitation> sentInvitations,
                List<Invitation> acceptedInvitations, List<Restaurant> favoritesRestaurant) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentRestaurant = currentRestaurant;
        this.friends = friends;
        this.receivedInvitations = receivedInvitations;
        this.sentInvitations = sentInvitations;
        this.acceptedInvitations = acceptedInvitations;
        this.favoritesRestaurant = favoritesRestaurant;
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

    public Restaurant getCurrentRestaurant() {
        return currentRestaurant;
    }

    public void setCurrentRestaurant(Restaurant currentRestaurant) {
        this.currentRestaurant = currentRestaurant;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User friend) {
        this.friends.add(friend);
    }

    public void removeFriend(User friend) {
        this.friends.remove(friend);
    }

    public List<Invitation> getReceivedInvitations() {
        return receivedInvitations;
    }

    public void setReceivedInvitations(List<Invitation> receivedInvitations) {
        this.receivedInvitations = receivedInvitations;
    }

    public void addReceivedInvitation(Invitation invitation) {
        this.receivedInvitations.add(invitation);
    }

    public void removeReceivedInvitation(Invitation invitation) {
        this.receivedInvitations.remove(invitation);
    }

    public List<Invitation> getSentInvitations() {
        return sentInvitations;
    }

    public void setSentInvitations(List<Invitation> sentInvitations) {
        this.sentInvitations = sentInvitations;
    }

    public void addSentInvitations(Invitation invitation) {
        this.sentInvitations.add(invitation);
    }

    public void removeSentInvitations(Invitation invitation) {
        this.sentInvitations.remove(invitation);
    }

    public List<Invitation> getAcceptedInvitations() {
        return acceptedInvitations;
    }

    public void setAcceptedInvitations(List<Invitation> acceptedInvitations) {
        this.acceptedInvitations = acceptedInvitations;
    }

    public void addAcceptedInvitation(Invitation invitation) {
        acceptedInvitations.add(invitation);
    }

    public void removeAcceptedInvitation(Invitation invitation) {
        acceptedInvitations.remove(invitation);
    }

    public List<Restaurant> getFavoritesRestaurant() {
        return favoritesRestaurant;
    }

    public void setFavoritesRestaurant(List<Restaurant> favoritesRestaurant) {
        this.favoritesRestaurant = favoritesRestaurant;
    }

    public void addFavoritesRestaurant(Restaurant favoriteRestaurant) {
        this.favoritesRestaurant.add(favoriteRestaurant);
    }

    public void removeFavoritesRestaurant(Restaurant favoriteRestaurant) {
        this.favoritesRestaurant.remove(favoriteRestaurant);
    }

    public boolean addOrRemove(Restaurant favoriteRestaurant) {
        if(this.favoritesRestaurant.indexOf(favoriteRestaurant) != -1){
            this.favoritesRestaurant.remove(favoriteRestaurant);
            return false;
        }else{
            this.favoritesRestaurant.add(favoriteRestaurant);
            return true;
        }
    }

}
