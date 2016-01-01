package com.insa.thibault.ihm.view.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.adapter.InviteListener;
import com.insa.thibault.ihm.business.User;
import com.insa.thibault.ihm.view.fragment.FriendsFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FriendsActivity extends AppCompatActivity implements InviteListener, View.OnClickListener {

    public static String FRIENDS_LIST =  "friends_list";

    Map<String, User> invitedFriends;

    @Bind(R.id.validation_button)
    Button validation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        ButterKnife.bind(this);

        invitedFriends = new HashMap<>();

        validation.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FriendsFragment friendsFragment = FriendsFragment.newInstance(new Bundle(), true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, friendsFragment)//TODO use tags
                .commit();
    }

    @Override
    public void invite(User user) {
        invitedFriends.put(user.getFirstName()+user.getLastName(), user);
    }

    @Override
    public void cancelInvite(User user) {
        invitedFriends.remove(user.getFirstName()+user.getLastName());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        ArrayList<User> friends = new ArrayList();
        friends.addAll(invitedFriends.values());
        intent.putParcelableArrayListExtra(FRIENDS_LIST, friends);
        setResult(RESULT_OK, intent);
        finish();
    }
}
