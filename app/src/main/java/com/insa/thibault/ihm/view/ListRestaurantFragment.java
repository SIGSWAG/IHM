package com.insa.thibault.ihm.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.adapter.RestaurantAdapter;
import com.insa.thibault.ihm.business.Restaurant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Thibault on 09/12/2015.
 */
public class ListRestaurantFragment extends Fragment{


    @Bind(R.id.list_restaurants)
    protected ListView listRestaurants;

    private RestaurantAdapter restaurantAdapter;

    private List<Restaurant> restaurantList ;

    public ListRestaurantFragment newInstance(Bundle bundle){
        ListRestaurantFragment fragment = new ListRestaurantFragment();
        fragment.setArguments(bundle);

        return fragment;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_restaurants, container, false);

        ButterKnife.bind(this, v);

        restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant("Beurk"));
        restaurantList.add(new Restaurant("Prévert"));
        restaurantList.add(new Restaurant("Olivier"));

        restaurantAdapter = new RestaurantAdapter(this.getContext(), restaurantList);

        listRestaurants.setAdapter(restaurantAdapter);





        return v;



    }
}
