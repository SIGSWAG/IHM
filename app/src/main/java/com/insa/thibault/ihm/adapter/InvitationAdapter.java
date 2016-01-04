package com.insa.thibault.ihm.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.business.Invitation;
import com.insa.thibault.ihm.business.User;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Jonathan on 21/12/2015.
 */
public class InvitationAdapter extends ArrayAdapter<Invitation> {

    private List<Invitation> invitations;

    public InvitationAdapter(Context context, List<Invitation> invitations) {
        super(context, 0, invitations);
        this.invitations = invitations;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_invitation, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.inviterName = (TextView) convertView.findViewById(R.id.sender_full_name);
            viewHolder.inviterImage = (ImageView) convertView.findViewById(R.id.inviter_profile_image);
            viewHolder.location = (TextView) convertView.findViewById(R.id.restaurant_invite_name);
            viewHolder.nbFriends = (TextView) convertView.findViewById(R.id.invite_nb_friends_eating);
            viewHolder.time = (TextView) convertView.findViewById(R.id.invite_datetime);
            viewHolder.acceptInvite = (ImageButton) convertView.findViewById(R.id.accept_invite);
            viewHolder.declineInvite = (ImageButton) convertView.findViewById(R.id.decline_invite);
            viewHolder.acceptInvite.setFocusable(false);
            viewHolder.declineInvite.setFocusable(false);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Invitation currentInvitation = getItem(position);
        User sender = currentInvitation.getSender();
        viewHolder.inviterName.setText(sender.getFirstName() + " " + sender.getLastName());
        // TODO Handle profile image
        //viewHolder.inviterImage.setImageDrawable(sender.getImage());
        viewHolder.nbFriends.setText(currentInvitation.getNbFriends() + " amis y vont");
        viewHolder.location.setText(currentInvitation.getRestaurant().getName());
        String time = currentInvitation.getTimeHour() + "h";
        if(currentInvitation.getTimeMinutes() != 0) {
            time += currentInvitation.getTimeMinutes();
        }
        viewHolder.time.setText(time);
        // TODO Buttons
        viewHolder.acceptInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Vous avez accepter l'invitation ! ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        viewHolder.declineInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Vous avez décliné l'invitation, pas cool ! ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView inviterName;
        TextView nbFriends;
        TextView location;
        TextView time;
        ImageButton acceptInvite;
        ImageButton declineInvite;
        ImageView inviterImage;
    }
}
