package com.insa.thibault.ihm.view.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.business.Restaurant;
import com.insa.thibault.ihm.business.User;


import com.insa.thibault.ihm.databinding.FragmentDetailsRestaurantBinding;
import com.insa.thibault.ihm.view.activity.FriendsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Thibault on 10/12/2015.
 */
public class DetailsRestaurantFragment extends Fragment implements View.OnClickListener {


    public static String KEY_RESTAURANT = "key_restaurant";
    private int FRIENDS_ACTIVITY = 0;

    private FragmentDetailsRestaurantBinding binding;
    private Restaurant restaurant;





    @Bind(R.id.button_invit)
    Button buttonInvit;



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
        ButterKnife.bind(this, v);

        if (getArguments() != null && getArguments().getParcelable(KEY_RESTAURANT) != null) { //we are in a new acti
            restaurant = getArguments().getParcelable(KEY_RESTAURANT);

            binding.setRestaurant(restaurant);

        }

        buttonInvit.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this.getActivity(), FriendsActivity.class);
        startActivityForResult(intent, FRIENDS_ACTIVITY);


        Snackbar.make(v, "Invitons des amis", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == FRIENDS_ACTIVITY) {

            if(resultCode == FriendsActivity.RESULT_OK){
                List<User> invitedFriends = data.getParcelableArrayListExtra(FriendsActivity.FRIENDS_LIST);

                Toast.makeText(getContext(), invitedFriends.get(0).getFirstName(), Toast.LENGTH_LONG).show();
            }

        }
    }
}
