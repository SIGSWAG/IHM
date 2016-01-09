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
        Bitmap icon = null;
        switch (restaurantList.indexOf(restaurant)){
            case 0:
                icon = BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.restaurant_insa);
                break;
            case 1:
                icon = BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.restau1);
                break;
            case 2:
                icon = BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.restau2);
                break;
            case 3:
                icon = BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.restau3);
                break;
            case 4:
                icon = BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.restau4);
                break;
            case 5:
                icon = BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.restau5);
                break;
            case 6:
                icon = BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.restau6);
                break;
            case 7:
                icon = BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.restau7);
                break;
            case 8:
                icon = BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.restau8);
                break;
        }


        return icon;

    }


    public static void init(Context context) {
        if(restaurantList == null) {
            restaurantList = new ArrayList<>();

            restaurantList.add(new Restaurant("Beurk", 12, 2, 30,"Brocolis & steak haché", "Soupe à l'oignon \n Brocolis & steak haché \n Fruits", "20 Avenue Albert Einstein, 69100 Villeurbanne, France",R.drawable.restaurant_insa, 45.7814181, 4.8729667));
            restaurantList.add(new Restaurant("Le Prévert", 2, 5, 300,"Panini thon", "Salade \n Panini thon \n Eclair au café", "20 Avenue Albert Einstein, 69100 Villeurbanne, France",R.drawable.restau1, 45.7814155, 4.8709152));
            restaurantList.add(new Restaurant("Snack du campus", 7, 1, 500,"Tacos Kebab Maison", "Tacos Kebab Maison", "29 rue de la Doua, 69100 Villeurbanne, France",R.drawable.restau2, 45.7814155, 4.8709152));
            restaurantList.add(new Restaurant("Panorama", 0, 3, 200,"Frites - Omelette ou Kebab", "Frites - Omelette ou Kebab", "63 avenue Roger Salengro, 69100 Villeurbanne, France",R.drawable.restau3, 45.7814155, 4.8709152));
            restaurantList.add(new Restaurant("Ninkasi", 14, 0, 1500,"Purée de carottes", "Quiche Lorraine \n Purée de carottes", "2-4 rue Léon Fabre, 69100 Villeurbanne, France",R.drawable.restau4, 45.7814155, 4.8709152));
            restaurantList.add(new Restaurant("Grand RU", 4, 3, 400,"Falafel & Petits pois", "Jambon cru \n Falafel & Petits pois \n Tarte aux pommes", "3 avenue Albert Einstein, 69100 Villeurbanne, France",R.drawable.restau5, 45.7814155, 4.8709152));
            restaurantList.add(new Restaurant("Petit RU", 2, 6, 10,"Pizza au saumon", "Salade verte \n Pizza au saumon \n Fruits", "18 avenue Einstein, 69100 Villeurbanne, France",R.drawable.restau6, 45.7814155, 4.8709152));
            restaurantList.add(new Restaurant("Auberge de la Doua", 10, 0, 400,"Purée & Courgettes", "Carottes rapées \n Purée & Courgettes \n Yaourts", "26 rue Marguerite, 69100 Villeurbanne, France",R.drawable.restau7, 45.7814155, 4.8709152));
            restaurantList.add(new Restaurant("Mozaïque", 0, 1, 400,"Pates bolognaises", "Foie gras \n Pates bolognaises \n Yaourt aux fruits", "8 rue Léon Fabre, 69100 Villeurbanne, France",R.drawable.restau8, 45.7814155, 4.8709152));


            restaurantList.get(0).setOpened(true);
            restaurantList.get(1).setOpened(true);
            restaurantList.get(2).setOpened(true);
            restaurantList.get(3).setOpened(true);
            restaurantList.get(4).setOpened(true);

            List<Invitation> invitations = new ArrayList<>();
            User sender = new User("Damien", "Gallet");
            User receiver = new User("Aziz", "SIGSWAG");
            invitations.add(new Invitation(sender, receiver, restaurantList.get(0), 12, 45, 3, 12, Invitation.PENDING));
            invitations.add(new Invitation(sender, receiver, restaurantList.get(1), 11, 30, 8, 12, Invitation.PENDING));

            User user1 = new User("Adam", "Rivault", null, null,
                    null, null,
                    null, null);
            user1.setAppUser(true);
            user1.setCurrentRestaurant(restaurantList.get(0));

            User user2 = new User("Adelin", "Ricaud", null, null,
                    null, null,
                    null, null);
            user2.setAppUser(true);

            invitations.add(new Invitation(user2, receiver, restaurantList.get(3), 13, 0, 2, 10, Invitation.PENDING));

            Map<String, User> friends = new HashMap<>();
            friends.put(user1.getFirstName() + user1.getLastName(), user1);
            friends.put(user2.getFirstName() + user2.getLastName(), user2);

            currentUser = new User("Thibault", "Halepian", null, friends,
                    invitations, new ArrayList<Invitation>(),
                    null, new ArrayList<Restaurant>());
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
