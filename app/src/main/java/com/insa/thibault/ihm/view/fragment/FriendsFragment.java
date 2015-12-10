package com.insa.thibault.ihm.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.adapter.RestaurantAdapter;
import com.insa.thibault.ihm.business.Restaurant;
import com.insa.thibault.ihm.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * Created by Thibault on 09/12/2015.
 */
public class FriendsFragment extends Fragment {

    @BindString(R.string.title_friends_fragment)
    protected String title;


    public static FriendsFragment newInstance(Bundle bundle){
        FriendsFragment fragment = new FriendsFragment();
        fragment.setArguments(bundle);

        return fragment;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_friends, container, false);

        ButterKnife.bind(this, v);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);


        return v;

    }
}
