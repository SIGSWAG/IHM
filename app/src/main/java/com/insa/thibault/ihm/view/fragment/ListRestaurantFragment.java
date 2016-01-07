package com.insa.thibault.ihm.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.RestaurantApplication;
import com.insa.thibault.ihm.adapter.RestaurantAdapter;
import com.insa.thibault.ihm.business.Restaurant;
import com.insa.thibault.ihm.business.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;


/**
 * Created by Thibault on 09/12/2015.
 */
public class ListRestaurantFragment extends Fragment implements AdapterView.OnItemClickListener {

    private OnRestaurantSelectedListener mCallback;

    @BindString(R.string.title_List_restaurant_fragment)
    protected String title;

    @Bind(R.id.list_restaurants)
    protected ListView listRestaurants;

    private RestaurantAdapter restaurantAdapter;

    @Inject
    protected User user;

    @Inject
    protected List<Restaurant> restaurantList;


    private List<Restaurant> restaurants;


    public ListRestaurantFragment() {

    }

    public static ListRestaurantFragment newInstance(Bundle bundle){
        ListRestaurantFragment fragment = new ListRestaurantFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnRestaurantSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnRestaurantSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((RestaurantApplication) getActivity().getApplication()).getAppComponent().inject(this);

        View v = inflater.inflate(R.layout.fragment_list_restaurants, container, false);

        ButterKnife.bind(this, v);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);

        restaurants = new ArrayList<>();

        restaurants.addAll(user.getFavoritesRestaurant());

        for(Restaurant restaurant : restaurantList){

            if(!restaurants.contains(restaurant)){

                restaurants.add(restaurant);
            }
        }

        restaurantAdapter = new RestaurantAdapter(this.getContext(), restaurants, user);

        listRestaurants.setAdapter(restaurantAdapter);
        restaurantAdapter.notifyDataSetChanged();
        listRestaurants.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("List","Click");
        if(mCallback != null){

            mCallback.onRestaurantSelected(view, restaurants.get(position));
        }
    }


    @Override
    public void onResume(){
        super.onResume();
        if(restaurantAdapter!=null) {


            restaurants.clear();

            restaurants.addAll(user.getFavoritesRestaurant());

            for(Restaurant restaurant : restaurantList){

                if(!restaurants.contains(restaurant)){

                    restaurants.add(restaurant);
                }
            }
            restaurantAdapter.notifyDataSetChanged();
        }

    }

}
