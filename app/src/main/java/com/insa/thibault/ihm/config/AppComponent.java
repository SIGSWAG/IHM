package com.insa.thibault.ihm.config;


import com.insa.thibault.ihm.view.activity.DetailsRestaurantActivity;
import com.insa.thibault.ihm.view.fragment.DetailsRestaurantFragment;
import com.insa.thibault.ihm.view.fragment.FriendsFragment;
import com.insa.thibault.ihm.view.fragment.ListInvitationFragment;
import com.insa.thibault.ihm.view.fragment.ListRestaurantFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by a607937 on 15/06/2015.
 */
@Singleton
@Component(modules = {RestaurantModule.class, AppModule.class})
public interface AppComponent {

    void inject(FriendsFragment friendsFragment);

    void inject(ListRestaurantFragment listRestaurantFragment);

    void inject(ListInvitationFragment listInvitationFragment);

    void inject(DetailsRestaurantActivity detailsRestaurantActivity);

    void inject(DetailsRestaurantFragment detailsRestaurantFragment);
}