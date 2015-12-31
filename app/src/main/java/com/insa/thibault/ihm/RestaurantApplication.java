package com.insa.thibault.ihm;

import android.app.Application;


import com.insa.thibault.ihm.config.AppComponent;
import com.insa.thibault.ihm.config.AppModule;
import com.insa.thibault.ihm.config.DaggerAppComponent;
import com.insa.thibault.ihm.config.RestaurantModule;

import java.io.IOException;

/**
 * Created by a607937 on 11/06/2015.
 */
public class RestaurantApplication extends Application {

    private AppComponent dependencyGraph;

    public AppComponent getAppComponent()
    {
        return dependencyGraph;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            dependencyGraph = DaggerAppComponent
                    .builder()
                    .appModule(new AppModule(this))
                    .restaurantModule(new RestaurantModule())
                    .build();
        } catch (IOException e) {
            // Should not happen
            throw new RuntimeException(e);
        }

    }
}
