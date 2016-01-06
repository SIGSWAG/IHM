package com.insa.thibault.ihm.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.insa.thibault.ihm.R;
import com.insa.thibault.ihm.RestaurantApplication;
import com.insa.thibault.ihm.business.Restaurant;
import com.insa.thibault.ihm.business.User;
import com.insa.thibault.ihm.tools.Tools;
import com.insa.thibault.ihm.view.fragment.DetailsRestaurantFragment;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsRestaurantActivity extends AppCompatActivity {

    public static String KEY_RESTAURANT = "key_restaurant";

    @Inject
    protected User currentUser;

    private Restaurant restaurant;

    @Bind(R.id.backdrop)
    protected ImageView restaurantImage;

    @Bind(R.id.fab)
    protected FloatingActionButton fab;

    public static Intent newIntent(Context context, Restaurant restaurant){

        Intent intent = new Intent(context, DetailsRestaurantActivity.class);
        intent.putExtra(KEY_RESTAURANT, restaurant);

        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((RestaurantApplication)getApplication()).getAppComponent().inject(this);
        setContentView(R.layout.activity_details_restaurant);
        ButterKnife.bind(this);

        getWindow().getEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                    fab.hide();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                        fab.show();
                        getWindow().getEnterTransition().removeListener(this);

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        Intent intent = getIntent();

        restaurant = intent.getParcelableExtra(KEY_RESTAURANT);

        //restaurantImage.setTransitionName(getString(R.string.activity_image_trans));


        Picasso.with(this).
                load(restaurant.getImg())
                .into(restaurantImage);

        //restaurantImage.setImageBitmap(Tools.getRestaurantBitmap(this.getBaseContext(), restaurant));



        collapsingToolbar.setTitle(restaurant.getName());

        DetailsRestaurantFragment detailsRestaurantFragment = DetailsRestaurantFragment.newInstance(new Bundle(), restaurant);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailsRestaurantFragment)//TODO use tags
                .commit();

        fab.setOnClickListener(detailsRestaurantFragment);
        fab.show();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:

                onBackPressed();
                break;
            default:
                break;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    @Override
    public void onBackPressed(){

        fab.animate().alpha(0.0f);

        super.onBackPressed();
    }

}
