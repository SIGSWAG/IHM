package com.insa.thibault.ihm.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.RestaurantApplication;
import com.insa.thibault.ihm.adapter.RecyclerInvitationAdapter;
import com.insa.thibault.ihm.adapter.FriendAtRestaurantAdapter;
import com.insa.thibault.ihm.business.Invitation;
import com.insa.thibault.ihm.business.Restaurant;
import com.insa.thibault.ihm.business.User;


import com.insa.thibault.ihm.databinding.FragmentDetailsRestaurantBinding;
import com.insa.thibault.ihm.utils.SnackbarUtils;
import com.insa.thibault.ihm.view.activity.FriendsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Thibault on 10/12/2015.
 */
public class DetailsRestaurantFragment extends Fragment implements View.OnClickListener, RecyclerInvitationAdapter.OnItemClickListener {

    public static String KEY_RESTAURANT = "key_restaurant";
    private int FRIENDS_ACTIVITY = 0;

    private FragmentDetailsRestaurantBinding binding;
    private Restaurant restaurant;
    private RecyclerInvitationAdapter recyclerInvitationAdapter;

    @Inject
    protected User user;

    @Bind(R.id.button_invit)
    Button buttonInvit;

    @Bind(R.id.list_invit)
    RecyclerView recyclerViewInvitations;

    @Bind(R.id.list_friends_eating)
    ListView listFriendsEating;

    @Bind(R.id.restaurant_favorite_icon)
    ImageButton buttonFavorite;

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
        ((RestaurantApplication)getActivity().getApplication()).getAppComponent().inject(this);

        //View v = inflater.inflate(R.layout.fragment_details_restaurant, container, false);
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_restaurant, container, false);

        View v = binding.getRoot();
        ButterKnife.bind(this, v);

        if (getArguments() != null && getArguments().getParcelable(KEY_RESTAURANT) != null) { //we are in a new acti
            restaurant = getArguments().getParcelable(KEY_RESTAURANT);
            binding.setRestaurant(restaurant);
        }

        List<Invitation> invitations = new ArrayList<>();
        for(Invitation invitation :  user.getReceivedInvitations()){
            if(invitation.getRestaurant().getName().compareTo(restaurant.getName()) == 0 && invitation.getStatus()==Invitation.PENDING){
                invitations.add(invitation);
            }
        }

        recyclerInvitationAdapter = new RecyclerInvitationAdapter(getActivity(), invitations, this, user);
        recyclerViewInvitations.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewInvitations.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                recyclerInvitationAdapter.getItemAt(pos).setStatus(Invitation.DENIED);
                recyclerInvitationAdapter.remove(pos);
                recyclerInvitationAdapter.notifyDataSetChanged();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewInvitations);

        recyclerViewInvitations.setAdapter(recyclerInvitationAdapter);
        recyclerInvitationAdapter.notifyDataSetChanged();

        buttonInvit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsRestaurantFragment.this.getActivity(), FriendsActivity.class);
                startActivityForResult(intent, FRIENDS_ACTIVITY);
                Snackbar.make(v, "Invitons des amis", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if(user.isRestaurantFavorite(restaurant)) {
            buttonFavorite.setImageResource(R.drawable.ic_star_black_24dp);
        }
        else {
            buttonFavorite.setImageResource(R.drawable.ic_star_border_black_24dp);
        }

        buttonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.addOrRemove(restaurant)) {
                    buttonFavorite.setImageResource(R.drawable.ic_star_black_24dp);
                    SnackbarUtils.showFavoriteAdded(v, restaurant);
                } else {
                    buttonFavorite.setImageResource(R.drawable.ic_star_border_black_24dp);
                    SnackbarUtils.showFavoriteRemoved(v, restaurant);
                }
            }
        });

        FriendAtRestaurantAdapter friendAtRestaurantAdapter =
                new FriendAtRestaurantAdapter(getContext(), null, restaurant);

        listFriendsEating.setAdapter(friendAtRestaurantAdapter);
        friendAtRestaurantAdapter.notifyDataSetChanged();

        return v;
    }

    @Override
    public void onClick(View v) {
        final View view = v;
        if (user.getCurrentRestaurant() != null ) {
            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
            alertDialog.setTitle("Etes vous sûr ?");
            alertDialog.setMessage("Vous avez indiqué vouloir manger dans un autre restaurant ou à une autre heure ce midi. Voulez vous vraiment" +
                    " annuler votre repas dans l'autre restaurant ?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Confirmer",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            user.setCurrentRestaurant(restaurant);
                            user.getAcceptedInvitations().clear();
                            user.addAcceptedInvitation(new Invitation(user, user, restaurant, 12, 45, 3, 12, Invitation.ACCEPTED));
                            Snackbar.make(view, "Votre repas a été enregistré", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            dialog.dismiss();
                    }
                                  });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Annuler",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                }
                              });

            alertDialog.show();
        }
        else if(user.getCurrentRestaurant() == null){
            user.setCurrentRestaurant(restaurant);

            user.addAcceptedInvitation(new Invitation(user, user, restaurant, 12, 45, 3, 12, Invitation.ACCEPTED));
            Snackbar.make(view, "Votre repas a été enregistré", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
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

    @Override
    public void onItemClick(View v, int position, Invitation invitation) {
        // TODO
    }
}
