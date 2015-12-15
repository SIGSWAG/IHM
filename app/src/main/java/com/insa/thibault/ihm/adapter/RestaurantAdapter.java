package com.insa.thibault.ihm.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.business.Restaurant;

import java.util.List;

/**
 * Created by Thibault on 09/12/2015.
 */
public class RestaurantAdapter extends ArrayAdapter<Restaurant>{


    private List<Restaurant> restaurants;

    public RestaurantAdapter(Context context, List<Restaurant> restaurants) {
        super(context, 0, restaurants);
        this.restaurants =  restaurants;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_restaurant, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.name = (TextView) convertView.findViewById(R.id.name_restaurant);
            viewHolder.nbFriends = (TextView) convertView.findViewById(R.id.nb_friends_eating);
            viewHolder.distance = (TextView) convertView.findViewById(R.id.distance_restaurant);
            viewHolder.nbNotifs = (TextView) convertView.findViewById(R.id.nb_notifs);
            viewHolder.boutonManger = (Button) convertView.findViewById(R.id.boutonManger);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Restaurant currentRestaurant = getItem(position);
        viewHolder.name.setText(currentRestaurant.getName());
        viewHolder.nbFriends.setText(" " + currentRestaurant.getNbFriends() + " ");
        viewHolder.distance.setText(currentRestaurant.getDistanceMetres());
        viewHolder.nbNotifs.setText("" + currentRestaurant.getNbInvitations());
        viewHolder.boutonManger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Button ", "Click");
            }
        });

        return convertView;
    }


    static class ViewHolder {
        TextView name;
        TextView nbFriends;
        TextView nbNotifs;
        TextView distance;
        Button boutonManger;
    }


}
