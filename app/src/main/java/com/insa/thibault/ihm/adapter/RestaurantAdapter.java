package com.insa.thibault.ihm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.business.Restaurant;
import com.insa.thibault.ihm.business.User;
import com.insa.thibault.ihm.utils.SnackbarUtils;

import java.util.List;

/**
 * Created by Thibault on 09/12/2015.
 */
public class RestaurantAdapter extends ArrayAdapter<Restaurant>{

    private List<Restaurant> restaurants;
    private User user;

    public RestaurantAdapter(Context context, List<Restaurant> restaurants, User utilisateur) {
        super(context, 0, restaurants);
        this.restaurants =  restaurants;
        this.user = utilisateur;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_restaurant, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.name = (TextView) convertView.findViewById(R.id.name_restaurant);
            viewHolder.nbFriends = (TextView) convertView.findViewById(R.id.nb_friends_eating);

            viewHolder.nbNotifs = (TextView) convertView.findViewById(R.id.nb_notifs);

            viewHolder.buttonStar = (ImageButton) convertView.findViewById(R.id.imageFavoris);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Restaurant restaurant = getItem(position);
        viewHolder.name.setText(restaurant.getName());
        viewHolder.nbFriends.setText(" " + restaurant.getNbFriends() + " ");
        viewHolder.nbNotifs.setText("" + restaurant.getNbInvitations());


        if(user.isRestaurantFavorite(restaurant)) {
            viewHolder.buttonStar.setImageResource(R.drawable.ic_star_black_24dp);
        }
        else {
            viewHolder.buttonStar.setImageResource(R.drawable.ic_star_border_black_24dp);
        }

        viewHolder.buttonStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.addOrRemove(restaurant)) {
                    viewHolder.buttonStar.setImageResource(R.drawable.ic_star_black_24dp);
                    SnackbarUtils.showFavoriteAdded(v, restaurant);
                } else {
                    viewHolder.buttonStar.setImageResource(R.drawable.ic_star_border_black_24dp);
                    SnackbarUtils.showFavoriteRemoved(v, restaurant);
                }
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView nbFriends;
        TextView nbNotifs;


        ImageButton buttonStar;
    }


}
