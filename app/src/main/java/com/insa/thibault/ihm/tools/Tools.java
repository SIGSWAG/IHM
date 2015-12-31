package com.insa.thibault.ihm.tools;

import com.insa.thibault.ihm.business.Invitation;
import com.insa.thibault.ihm.business.Restaurant;
import com.insa.thibault.ihm.business.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Thibault on 31/12/2015.
 */
public class Tools {

    private static User currentUser;
    private static List<Restaurant> restaurantList;

    public static void init(){

        if(restaurantList ==  null) {

            restaurantList = new ArrayList<>();
            Restaurant restaurant = new Restaurant("Beurk", 12, 2, 30, "Brocoli");
            restaurantList.add(restaurant);
            restaurantList.add(new Restaurant("Prévert", 2, 5, 300, "tacos"));
            restaurantList.add(new Restaurant("Snoop doog", 7, 1, 500, "frites"));


            List<Invitation> invitations = new ArrayList<>();

            User sender = new User("Damien", "Gallet");
            User receiver = new User("Aziz", "SIGSWAG");


            invitations.add(new Invitation(sender, receiver, restaurant, 12, 45, 3, 12,Invitation.PENDING));
            invitations.add(new Invitation(sender, receiver, restaurant, 11, 30, 3, 12, Invitation.PENDING));


            User user1 = new User("Adam", "Rivault", null, null,
                    null, null,
                    null, null);
            user1.setAppUser(true);
            User user2 = new User("Adelin", "Ricaud", null, null,
                    null, null,
                    null, null);
            user2.setAppUser(true);
            Map<String, User> friends = new HashMap<>();
            friends.put(user1.getFirstName() + user1.getLastName(), user1);
            friends.put(user2.getFirstName() + user2.getLastName(), user2);

            currentUser = new User("Thibault", "Halepian", null, friends,
                    invitations, new ArrayList<Invitation>(),
                    new ArrayList<Invitation>(), new ArrayList<Restaurant>());
        }

    }



    public static User getCurrentUser(){

        init();
        return currentUser;
    }


    public static List<Restaurant> getRestaurants(){

        init();
        return restaurantList;

    }




}
