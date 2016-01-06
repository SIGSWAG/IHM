package com.insa.thibault.ihm.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.business.Invitation;
import com.insa.thibault.ihm.business.Restaurant;
import com.insa.thibault.ihm.business.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

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


    public static Bitmap getRestaurantBitmap(Context context, Restaurant restaurant){

        Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.restaurant_insa);

        return icon;

    }


    public static void init(Context context) {
        if(restaurantList == null) {
            restaurantList = new ArrayList<>();


            Restaurant restaurant = new Restaurant("Beurk", 12, 2, 30, "Brocoli", "20 Avenue Jean Capelle O, 69100 Villeurbanne, France");
            restaurant.setOpened(true);
            restaurantList.add(restaurant);
            restaurantList.add(new Restaurant("Le Prévert", 2, 5, 300, "Gras", "14 avenue Beurk, 69100 Villeurbanne, France"));
            restaurantList.add(new Restaurant("Snack du campus", 7, 1, 500, "Tacos", "18 avenue Einstein, 69100 Villeurbanne, France"));
            restaurantList.add(new Restaurant("Panorama", 0, 3, 200, "Frites - Omelette", "12 rue Sigmond, 69100 Villeurbanne, France"));
            restaurantList.add(new Restaurant("Ninkasi", 14, 0, 1500, "Quiche", "20 Avenue Jean Capelle O, 69100 Villeurbanne, France"));
            restaurantList.add(new Restaurant("Grand RU", 4, 3, 400, "Falafel - Petits pois", "14 avenue Beurk, 69100 Villeurbanne, France"));
            restaurantList.add(new Restaurant("Petit RU", 2, 6, 10, "Pizzas", "18 avenue Einstein, 69100 Villeurbanne, France"));
            restaurantList.add(new Restaurant("Auberge de la Doua", 10, 0, 400, "Purée - Courgettes", "18 rue de SIGSWAG, 69100 Villeurbanne, France"));
            restaurantList.add(new Restaurant("Mozaïque", 0, 1, 400, "Yaourt aux fruits", "620 avenue Mona Liza, NY 10018"));



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

    public static User getCurrentUser(Context context) {
        init(context);
        return currentUser;
    }

    public static List<Restaurant> getRestaurants(Context context) {
        init(context);
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
