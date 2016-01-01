package com.insa.thibault.ihm.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.business.Invitation;
import com.insa.thibault.ihm.business.Restaurant;
import com.insa.thibault.ihm.business.User;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Jonathan on 21/12/2015.
 */
public class ContactAdapter extends ArrayAdapter<User> {

    private List<User> contacts;

    boolean inviteAdapter;

    private InviteListener listener;

    public ContactAdapter(Context context, List<User> invitations, boolean inviteAdapter, InviteListener listener) {
        super(context, 0, invitations);
        this.contacts = invitations;
        this.inviteAdapter = inviteAdapter;
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_friend, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.invite = (CheckBox) convertView.findViewById(R.id.invite_check);
            viewHolder.friendName = (TextView) convertView.findViewById(R.id.friend_name);
            viewHolder.location = (TextView) convertView.findViewById(R.id.restaurant_name);
            viewHolder.inviteInApp = (Button) convertView.findViewById(R.id.invite_in_app);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final User currentUser = getItem(position);
        Restaurant restaurant = currentUser.getCurrentRestaurant();
        viewHolder.invite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    listener.invite(currentUser);
                }else{
                    listener.cancelInvite(currentUser);
                }

            }
        });

        viewHolder.friendName.setText(currentUser.getFirstName()+" "+currentUser.getLastName());

        if(restaurant != null) {
            viewHolder.location.setText(restaurant.getName());
        }

        if(inviteAdapter && currentUser.isAppUser()){

            viewHolder.invite.setVisibility(View.VISIBLE);
            viewHolder.inviteInApp.setVisibility(View.GONE);
        }
        else if (currentUser.isAppUser()){
            viewHolder.inviteInApp.setVisibility(View.GONE);
            viewHolder.invite.setVisibility(View.GONE);

        }
        else{
            viewHolder.inviteInApp.setVisibility(View.VISIBLE);
            viewHolder.invite.setVisibility(View.GONE);
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView friendName;
        CheckBox invite;
        Button inviteInApp;
        TextView location;

    }

    public void setContacts(List<User> contacts) {
        this.contacts = contacts;
        super.clear();
        super.addAll(contacts);
    }
}
