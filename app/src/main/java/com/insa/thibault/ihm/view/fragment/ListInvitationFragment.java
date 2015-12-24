package com.insa.thibault.ihm.view.fragment;

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

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.adapter.InvitationAdapter;
import com.insa.thibault.ihm.business.Invitation;
import com.insa.thibault.ihm.business.Restaurant;
import com.insa.thibault.ihm.business.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * Created by Thibault on 09/12/2015.
 */
public class ListInvitationFragment extends Fragment implements AdapterView.OnItemClickListener {

    private OnInvitationSelectedListener mCallback;

    @BindString(R.string.title_invitations_fragment)
    protected String title;

    @Bind(R.id.list_my_meals)
    protected ListView myMealsList;

    @Bind(R.id.list_friends_meals)
    protected ListView invitationsList;

    private List<Invitation> invitations;

    private InvitationAdapter invitationAdapter;

    public static ListInvitationFragment newInstance(Bundle bundle){
        ListInvitationFragment fragment = new ListInvitationFragment();
        fragment.setArguments(bundle);

        return fragment;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnInvitationSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnInvitationSelectedListener");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_invitations, container, false);

        ButterKnife.bind(this, v);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);

        invitations = new ArrayList<>();

        User sender = new User("Damien", "Gallet");
        User receiver = new User("Aziz", "SIGSWAG");
        Restaurant restaurant = new Restaurant("Beurk", 12, 2, 30, "Brocoli");

        invitations.add(new Invitation(sender, receiver, restaurant, 12, 45, 3, 12));
        invitations.add(new Invitation(sender, receiver, restaurant, 11, 30, 3, 12));

        invitationAdapter = new InvitationAdapter(this.getContext(), invitations);

        invitationsList.setAdapter(invitationAdapter);
        invitationAdapter.notifyDataSetChanged();

        invitationsList.setOnItemClickListener(this);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("List", "Click");
        if(mCallback != null) {
            mCallback.onInvitationSelected(invitations.get(position));
        }
    }
}
