package com.insa.thibault.ihm.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.RestaurantApplication;
import com.insa.thibault.ihm.adapter.InvitationAdapter;
import com.insa.thibault.ihm.business.Invitation;
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
public class ListInvitationFragment extends Fragment implements AdapterView.OnItemClickListener {

    private OnInvitationSelectedListener mCallback;

    @BindString(R.string.title_invitations_fragment)
    protected String title;

    @Bind(R.id.list_friends_meals)
    protected ListView invitationsList;

    @Inject
    protected User currentUser;

    private List<Invitation> invitations;

    private InvitationAdapter invitationAdapter;

    @Bind(R.id.item_invitation_accepted)
    protected View myMeal;

    @Bind(R.id.invitations_text_hint)
    protected TextView invitationsTextHint;

    protected LinearLayout invitationAcceptedNormalLayout;

    protected TextView myMealTextHint;


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
        ((RestaurantApplication) getActivity().getApplication()).getAppComponent().inject(this);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_invitations, container, false);

        ButterKnife.bind(this, v);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);

        invitations = new ArrayList<>();
        invitations.addAll(currentUser.getReceivedInvitations());

        invitationAdapter = new InvitationAdapter(this.getContext(), invitations, this);
        invitationsList.setAdapter(invitationAdapter);
        invitationAdapter.notifyDataSetChanged();
        invitationsList.setOnItemClickListener(this);
        invitationsList.setEmptyView(invitationsTextHint);

        myMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Invitation acceptedInvitation = currentUser.getAcceptedInvitation();
                if(acceptedInvitation != null) {
                    mCallback.onInvitationSelected(currentUser.getAcceptedInvitation());
                }
            }
        });

        invitationAcceptedNormalLayout = (LinearLayout) myMeal.findViewById(R.id.invitation_accepted_normal);
        myMealTextHint = (TextView) myMeal.findViewById(R.id.my_meal_text_hint);

        Invitation acceptedInvitation = currentUser.getAcceptedInvitation();
        if(acceptedInvitation == null) {
            invitationAcceptedNormalLayout.setVisibility(View.GONE);
            myMealTextHint.setVisibility(View.VISIBLE);
        }
        else {
            setAcceptedInvitation(acceptedInvitation);
        }

        return v;
    }

    private void setAcceptedInvitation(Invitation acceptedInvitation) {
        invitationAcceptedNormalLayout.setVisibility(View.VISIBLE);
        myMealTextHint.setVisibility(View.GONE);

        TextView senderText = (TextView) myMeal.findViewById(R.id.sender_full_name);
        TextView restaurant = (TextView) myMeal.findViewById(R.id.restaurant_invite_name);
        TextView time = (TextView) myMeal.findViewById(R.id.invite_datetime);
        TextView nbFriendsEating = (TextView) myMeal.findViewById(R.id.invite_nb_friends_eating);
        ImageButton declineInvite = (ImageButton) myMeal.findViewById(R.id.decline_invite);

        User sender = acceptedInvitation.getSender();
        Restaurant acceptedRestaurant = acceptedInvitation.getRestaurant();
        String acceptedTime = String.format("%02dh%02d", acceptedInvitation.getTimeHour(), acceptedInvitation.getTimeMinutes());

        senderText.setText(sender.getFirstName() + " " + sender.getLastName());
        restaurant.setText(acceptedRestaurant.getName());
        time.setText(acceptedTime);
        nbFriendsEating.setText(acceptedInvitation.getNbFriends() + " amis y vont");

        declineInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                declineAcceptedInvitation();
            }
        });
    }

    private void declineAcceptedInvitation() {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle(R.string.sure);
        alertDialog.setMessage(getString(R.string.cancel_my_meal));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.confirm),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        currentUser.setAcceptedInvitation(null);
                        currentUser.setCurrentRestaurant(null);
                        invitationAcceptedNormalLayout.setVisibility(View.GONE);
                        myMealTextHint.setVisibility(View.VISIBLE);
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("List", "Click");
        if(mCallback != null) {
            mCallback.onInvitationSelected(invitations.get(position));
        }
    }

    public void acceptInvite(final Invitation invitation) {
        if(currentUser.getAcceptedInvitation() != null) {
            AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
            alertDialog.setTitle(R.string.sure);
            alertDialog.setMessage(getString(R.string.accept_invite));
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.confirm),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            invitations.remove(invitation);
                            currentUser.setAcceptedInvitation(invitation);
                            currentUser.removeReceivedInvitation(invitation);
                            setAcceptedInvitation(invitation);
                            invitationAdapter.notifyDataSetChanged();
                            Snackbar.make(getView(), "Vous avez accepté l'invitation ! ", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.cancel),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            alertDialog.show();
        }
        else {
            invitations.remove(invitation);
            currentUser.setAcceptedInvitation(invitation);
            currentUser.removeReceivedInvitation(invitation);
            setAcceptedInvitation(invitation);
            invitationAdapter.notifyDataSetChanged();
            Snackbar.make(getView(), "Vous avez accepté l'invitation ! ", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    public void declineInvite(Invitation invitation) {
        invitations.remove(invitation);
        currentUser.removeReceivedInvitation(invitation);
        invitationAdapter.notifyDataSetChanged();
    }
}
