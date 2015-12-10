package com.insa.thibault.ihm.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.insa.thibault.ihm.R;

import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * Created by Thibault on 09/12/2015.
 */
public class SettingsFragment extends Fragment{

    @BindString(R.string.title_settings_fragment)
    protected String title;


    public static SettingsFragment newInstance(Bundle bundle){
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(bundle);

        return fragment;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, v);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        return v;

    }
}
