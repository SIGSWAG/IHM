package com.insa.thibault.ihm.config;

import android.content.Context;

import com.insa.thibault.ihm.business.Restaurant;
import com.insa.thibault.ihm.business.User;
import com.insa.thibault.ihm.tools.Tools;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by a607937 on 15/06/2015.
 */
@Module
public class RestaurantModule {

    @Provides
    @Singleton
    User provideUser(Context context) {
        return Tools.getCurrentUser(context);
    }

    @Provides
    @Singleton
    List<Restaurant> provideRestaurants(Context context) {
        return Tools.getRestaurants(context);
    }
}
