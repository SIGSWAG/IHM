package com.insa.thibault.ihm.view.fragment;

import android.view.View;

import com.insa.thibault.ihm.business.Restaurant;

/**
 * Created by Thibault on 10/12/2015.
 */
public interface OnRestaurantSelectedListener {

    void onRestaurantSelected(View v, Restaurant restaurant);
}
