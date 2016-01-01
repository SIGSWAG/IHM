package com.insa.thibault.ihm.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.insa.thibault.ihm.business.Restaurant;

/**
 * Created by Jonathan on 01/01/2016.
 */
public class SnackbarUtils {

    public static void showSnackbarShort(View v, String message) {
        Snackbar.make(v, message, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    public static void showSnackbarLong(View v, String message) {
        Snackbar.make(v, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public static void showSnackbar(View v, String message, int length) {
        Snackbar.make(v, message, length)
                .setAction("Action", null).show();
    }

    public static void showFavoriteAdded(View v, Restaurant restaurant) {
        SnackbarUtils.showSnackbarShort(v, restaurant.getName() + " a été ajouté à vos favoris");
    }

    public static void showFavoriteRemoved(View v, Restaurant restaurant) {
        SnackbarUtils.showSnackbarShort(v, restaurant.getName() + " a été retiré de vos favoris");
    }
}
