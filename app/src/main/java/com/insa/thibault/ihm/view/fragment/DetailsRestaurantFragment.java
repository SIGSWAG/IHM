package com.insa.thibault.ihm.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.business.Restaurant;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Thibault on 10/12/2015.
 */
public class DetailsRestaurantFragment extends Fragment{


    public static String KEY_RESTAURANT = "key_restaurant";


    @Bind(R.id.restaurant_name)
    TextView name;

    @Bind(R.id.distance_restaurant)
    TextView distance;

    @Bind(R.id.nb_friends_eating)
    TextView nbFriendsEating;

    @Bind(R.id.nb_notifs)
    TextView nbNotifs;


    public static DetailsRestaurantFragment newInstance(Bundle bundleArg, Restaurant restaurant){
        DetailsRestaurantFragment fragment = new DetailsRestaurantFragment();

        Bundle bundle = new Bundle();
        bundle.putAll(bundleArg);

        //bundle.putSerializable(KEY_RESTAURANT, restaurant); //TODO implement parcelables
        bundle.putParcelable(KEY_RESTAURANT, restaurant);

        fragment.setArguments(bundle);

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_details_restaurant, container, false);

        ButterKnife.bind(this, v);

        if (getArguments() != null && getArguments().getParcelable(KEY_RESTAURANT) != null) { //we are in a new acti
            Restaurant restaurant = getArguments().getParcelable(KEY_RESTAURANT);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(restaurant.getName());
            name.setText(restaurant.getName());
            distance.setText(restaurant.getDistanceMetres());
            nbFriendsEating.setText(restaurant.getNbFriends());
            nbNotifs.setText(restaurant.getNbInvitations());
        }

        return v;
    }
}
