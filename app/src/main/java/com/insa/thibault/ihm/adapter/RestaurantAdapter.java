package com.insa.thibault.ihm.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.business.Restaurant;
import com.insa.thibault.ihm.business.User;
import com.insa.thibault.ihm.tools.Tools;
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
            viewHolder.plat = (TextView) convertView.findViewById(R.id.main_plat);
            viewHolder.info = (TextView) convertView.findViewById(R.id.info);
            viewHolder.layoutMeal = (LinearLayout) convertView.findViewById(R.id.layout_meal);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.img_restau);

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

        if(restaurant.isOpened()){

            viewHolder.layoutMeal.setVisibility(View.VISIBLE);
            viewHolder.plat.setText(restaurant.getPlat());
            viewHolder.info.setText(R.string.opened);
            viewHolder.info.setTextColor(getContext().getResources().getColor(R.color.green_invit));
        }else{
            viewHolder.layoutMeal.setVisibility(View.INVISIBLE);
            viewHolder.info.setText(R.string.closed);
            viewHolder.info.setTextColor(getContext().getResources().getColor(R.color.materialRed));

        }
        Bitmap imageRestaurant = Tools.getRestaurantBitmap(getContext(), restaurant);
        Resources r = getContext().getResources();
        int pixelsPour100DP = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, r.getDisplayMetrics());
        int widhtImage = getWidthImageCarree(pixelsPour100DP, imageRestaurant);
        int heightImage = getHeightImageCarree(pixelsPour100DP, imageRestaurant);
        Bitmap imageRestaurantCarree = Bitmap.createBitmap(imageRestaurant, widhtImage, heightImage, pixelsPour100DP, pixelsPour100DP);
        viewHolder.img.setImageBitmap(imageRestaurantCarree);

        return convertView;
    }

    private int getHeightImageCarree(int pixelsPour100DP, Bitmap imageRestaurant) {
        int res = imageRestaurant.getHeight()/2 - pixelsPour100DP;
        if(res < 0){
            return 0;
        }else{
            return res;
        }
    }

    private int getWidthImageCarree(int pixelsPour100DP, Bitmap imageRestaurant) {
        int res = imageRestaurant.getWidth()/2 - pixelsPour100DP;
        if(res < 0){
            return 0;
        }else{
            return res;
        }
    }

    static class ViewHolder {
        TextView name;
        TextView nbFriends;
        TextView nbNotifs;
        LinearLayout layoutMeal;
        TextView info;
        TextView plat;
        ImageView img;

        ImageButton buttonStar;
    }


}
