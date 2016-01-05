package com.insa.thibault.ihm.tools;

import com.insa.thibault.ihm.business.Invitation;
import com.insa.thibault.ihm.business.Restaurant;
import com.insa.thibault.ihm.business.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Thibault on 31/12/2015.
 */
public class Tools {

    private static User currentUser;
    private static List<Restaurant> restaurantList;

    private static List<User> someUsers;
    static {
        User user1 = new User("Will", "Smith", "12h15");
        User user2 = new User("Damien", "Gallet", "13h14");
        User user3 = new User("Marc", "Javin", "11h45");
        User user4 = new User("Thibault", "Halepian", "13h45");
        User user5 = new User("Aurélien", "Coussat", "12h10");
        User user6 = new User("Hugo", "Delval", "12h35");
        User user7 = new User("Loïc", "Touzard", "13h30");
        User user8 = new User("Jonathan", "Taws", "12h45");

        someUsers = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8);
    }

    public static void init() {
        if(restaurantList == null) {
            restaurantList = new ArrayList<>();
            Restaurant restaurant = new Restaurant("Beurk", 12, 2, 30, "Brocoli", "20 Avenue Jean Capelle O, 69100 Villeurbanne, France");
            restaurantList.add(restaurant);
            restaurantList.add(new Restaurant("Prévert", 2, 5, 300, "tacos", "620 Eighth avenue New York, NY 10018"));
            restaurantList.add(new Restaurant("Snoop Dogg", 7, 1, 500, "frites", "620 Eighth avenue New York, NY 10018"));
            restaurant.setOpened(true);
            List<Invitation> invitations = new ArrayList<>();

            User sender = new User("Damien", "Gallet");
            User receiver = new User("Aziz", "SIGSWAG");

            invitations.add(new Invitation(sender, receiver, restaurant, 12, 45, 3, 12,Invitation.PENDING));
            invitations.add(new Invitation(sender, receiver, restaurant, 11, 30, 3, 12, Invitation.PENDING));

            User user1 = new User("Adam", "Rivault", null, null,
                    null, null,
                    null, null);
            user1.setAppUser(true);
            user1.setCurrentRestaurant(restaurant);

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

    public static User getCurrentUser() {
        init();
        return currentUser;
    }

    public static List<Restaurant> getRestaurants() {
        init();
        return restaurantList;
    }

    public static List<User> getSomeUsers(int numberOfUsers) {
        Collections.shuffle(someUsers);
        if(numberOfUsers >= someUsers.size() - 1) {
            return new ArrayList<>(someUsers);
        }
        return new ArrayList<>(someUsers.subList(0, numberOfUsers));
    }

}
