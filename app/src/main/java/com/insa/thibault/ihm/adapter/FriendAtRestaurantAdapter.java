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
import com.insa.thibault.ihm.business.Restaurant;
import com.insa.thibault.ihm.business.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jonathan on 02/01/2016.
 */
public class FriendAtRestaurantAdapter extends ArrayAdapter<User> {

    private List<User> friendsEating;
    private List<User> friendsInvited;

    public FriendAtRestaurantAdapter(Context context, List<User> friends) {
        super(context, 0, friends);
        friendsEating = friends.subList(0, friends.size()/2);
        friendsInvited = friends.subList(friends.size()/2, friends.size());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_restaurant_friend, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.friendName = (TextView) convertView.findViewById(R.id.invite_friend_name);
            viewHolder.inviteFriendProfileIcon = (ImageView) convertView.findViewById(R.id.invite_friend_profile_icon);
            viewHolder.inviteFriendClockIcon = (ImageView) convertView.findViewById(R.id.invite_friend_clock_icon);
            viewHolder.timeEating = (TextView) convertView.findViewById(R.id.invite_friend_datetime);
            viewHolder.invited = (TextView) convertView.findViewById(R.id.invite_friend_text);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final User friend = getItem(position);

        viewHolder.friendName.setText(friend.getFirstName() + " " + friend.getLastName());
        // TODO Handle profile image
        //viewHolder.inviteFriendProfileIcon.setImageDrawable(friend.getImage());

        if(isInFriendsEating(friend)) {
            viewHolder.invited.setVisibility(View.INVISIBLE);
            viewHolder.timeEating.setText(""+friend.getTimeEating());
        }
        else if(isInvited(friend)) {
            viewHolder.inviteFriendClockIcon.setVisibility(View.INVISIBLE);
            viewHolder.timeEating.setVisibility(View.INVISIBLE);
        }
        else {
            // TODO
        }

        return convertView;
    }

    private boolean isInFriendsEating(User user) {
        for(User friend : friendsEating) {
            if(friend.equals(user)) {
                return true;
            }
        }
        return false;
    }

    private boolean isInvited(User user) {
        for(User friend : friendsInvited) {
            if(friend.equals(user)) {
                return true;
            }
        }
        return false;
    }

    private static class ViewHolder {
        ImageView inviteFriendProfileIcon;
        TextView friendName;

        ImageView inviteFriendClockIcon;
        TextView timeEating;

        TextView invited;
    }

}
