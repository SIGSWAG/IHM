package com.insa.thibault.ihm.view.fragment;

import android.databinding.DataBindingUtil;
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
import com.insa.thibault.ihm.databinding.FragmentDetailsRestaurantBinding;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Thibault on 10/12/2015.
 */
public class DetailsRestaurantFragment extends Fragment{


    public static String KEY_RESTAURANT = "key_restaurant";


    private FragmentDetailsRestaurantBinding binding;
    private Restaurant restaurant;





    public static DetailsRestaurantFragment newInstance(Bundle bundleArg, Restaurant restaurant){
        DetailsRestaurantFragment fragment = new DetailsRestaurantFragment();

        Bundle bundle = new Bundle();
        bundle.putAll(bundleArg);

        bundle.putParcelable(KEY_RESTAURANT, restaurant);

        fragment.setArguments(bundle);

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View v = inflater.inflate(R.layout.fragment_details_restaurant, container, false);
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_restaurant, container, false);

        View v = binding.getRoot();
        //ButterKnife.bind(this, v);

        if (getArguments() != null && getArguments().getParcelable(KEY_RESTAURANT) != null) { //we are in a new acti
            restaurant = getArguments().getParcelable(KEY_RESTAURANT);

            binding.setRestaurant(restaurant);

        }

        return v;
    }
}
