package com.insa.thibault.ihm.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.business.Invitation;
import com.insa.thibault.ihm.business.User;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by a607937 on 18/06/2015.
 */
public class RecyclerInvitationAdapter extends RecyclerView.Adapter<RecyclerInvitationAdapter.ViewHolder>
        /***/ {

    private List<Invitation> invitations;
    private WeakReference<OnItemClickListener> mCallBack;
    private User currentUser;
    private Context context;




    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerInvitationAdapter(Context context, List<Invitation> invitations, OnItemClickListener listener, User currentUser) {

        mCallBack = new WeakReference<OnItemClickListener>(listener);
        this.invitations = invitations;
        this.currentUser = currentUser;
        this.context = context;


    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerInvitationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_invitation, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //the current Forecast
        final Invitation currentInvitation = invitations.get(position);
        //we generate the color

        holder.friendName.setText(currentInvitation.getSender().getFirstName());
        holder.hour.setText(currentInvitation.getTimeHour()+":"+currentInvitation.getTimeMinutes());

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.get().acceptedInvitation(v, RecyclerInvitationAdapter.this, currentInvitation );
            }
        });

        holder.decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerInvitationAdapter.this.remove(currentInvitation);
                currentInvitation.setStatus(Invitation.DENIED);
                currentUser.removeReceivedInvitation(currentInvitation);
            }
        });
    }






    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return invitations.size();
    }

    public void remove(int position) {
        Invitation invitation = invitations.remove(position);
        notifyItemRemoved(position);
        currentUser.removeReceivedInvitation(invitation);
    }

    public void remove(Invitation invitation) {

        for(int i=0; i<invitations.size(); i++){
            Invitation curr = invitations.get(i);
            if(invitation ==  curr){
                this.remove(i);
            }
        }
    }


    public Invitation getItemAt(int position)
    {

        return invitations.get(position);
    }

    public void clear() {
        invitations.clear();
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position, Invitation invitation);
        void acceptedInvitation(View v, RecyclerInvitationAdapter onClickListener, Invitation currentInvitation);
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case

        @Bind(R.id.friend_name)
        protected TextView friendName;

        @Bind(R.id.hour)
        protected TextView hour;

        @Bind(R.id.accept_invite)
        protected ImageButton accept;

        @Bind(R.id.decline_invite)
        protected ImageButton decline;


        public ViewHolder(View v) {
            super(v);

            ButterKnife.bind(this, v);



            //v.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            OnItemClickListener listener = null;
            if ((listener = mCallBack.get()) != null)
            {
                mCallBack.get().onItemClick(v, getAdapterPosition(), invitations.get(getAdapterPosition()));
            }


        }
    }
}