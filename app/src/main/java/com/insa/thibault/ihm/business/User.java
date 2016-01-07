package com.insa.thibault.ihm.business;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by loict on 15/12/2015.
 */
public class User implements Parcelable{

    private String firstName;
    private String lastName;
    private Restaurant currentRestaurant;
    private Map<String,User> friends;
    private List<Invitation> receivedInvitations;
    private List<Invitation> sentInvitations;
    private Invitation acceptedInvitation;
    private List<Restaurant> favoritesRestaurant;
    private String timeEating;
    boolean isAppUser;

    //Parcelable
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


    public User(Parcel source) {
        firstName = source.readString();
        lastName = source.readString();
        currentRestaurant = source.readParcelable(getClass().getClassLoader());
        friends = new HashMap<>();
        User[] users =  source.createTypedArray(User.CREATOR);

        for(User user : users){
            friends.put(user.getFirstName()+user.getLastName(), user);
        }

        receivedInvitations =  source.createTypedArrayList(Invitation.CREATOR);
        sentInvitations =  source.createTypedArrayList(Invitation.CREATOR);

        favoritesRestaurant = source.createTypedArrayList(Restaurant.CREATOR);
        boolean[] app = new boolean[1];
        source.readBooleanArray(app);
        isAppUser =  app[0];
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentRestaurant = null;
        this.friends = new HashMap<>();
        this.receivedInvitations = new ArrayList<>();
        this.sentInvitations = new ArrayList<>();
        this.favoritesRestaurant = new ArrayList<>();
    }

    public User(String firstName, String lastName, String timeEating) {
        this(firstName, lastName);
        this.timeEating = timeEating;
    }

    public User(String firstName, String lastName, Restaurant currentRestaurant, Map<String, User> friends,
                List<Invitation> receivedInvitations, List<Invitation> sentInvitations,
                Invitation acceptedInvitation, List<Restaurant> favoritesRestaurant) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentRestaurant = currentRestaurant;
        this.friends = friends;
        this.receivedInvitations = receivedInvitations;
        this.sentInvitations = sentInvitations;
        this.acceptedInvitation = acceptedInvitation;
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

    public String getTimeEating() {
        return timeEating;
    }


    public Map<String, User> getFriends() {
        return friends;
    }

    public void setFriends(Map<String,User> friends) {
        this.friends = friends;
    }

    public void addFriend(User friend) {
        this.friends.put(friend.getFirstName()+friend.getLastName(), friend);
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

    public Invitation getAcceptedInvitation() {
        return acceptedInvitation;
    }

    public void setAcceptedInvitation(Invitation acceptedInvitation) {
        this.acceptedInvitation = acceptedInvitation;
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
        if(favoritesRestaurant.indexOf(favoriteRestaurant) != -1){
            favoritesRestaurant.remove(favoriteRestaurant);
            return false;
        }
        else{
            favoritesRestaurant.add(favoriteRestaurant);
            return true;
        }
    }

    public boolean isAppUser() {
        return isAppUser;
    }

    public void setAppUser(boolean appUser) {
        isAppUser = appUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeParcelable(currentRestaurant,0);

        if(friends==null){
            friends = new HashMap<>();
        }
        if(receivedInvitations == null){
            receivedInvitations = new ArrayList<>();
        }
        if(sentInvitations == null){
            sentInvitations = new ArrayList<>();
        }

        if(favoritesRestaurant == null){
            favoritesRestaurant = new ArrayList<>();
        }

        dest.writeTypedArray( friends.values().toArray(new User[friends.size()]),0);
        dest.writeTypedList( receivedInvitations);
        dest.writeTypedList( sentInvitations);
        dest.writeTypedList( favoritesRestaurant);
        boolean[] userApp = new boolean[1];
        userApp[0]=isAppUser;
        dest.writeBooleanArray(userApp);
    }

    public boolean isRestaurantFavorite(Restaurant currentRestaurant) {
        for(Restaurant favoriteRestaurant : favoritesRestaurant) {
            if(favoriteRestaurant.equals(currentRestaurant)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEatingIn(Restaurant restaurant) {
        return restaurant!=null && currentRestaurant!=null && currentRestaurant.equals(restaurant);
    }

    public void setTimeEating(String timeEating) {
        this.timeEating = timeEating;
    }
}
