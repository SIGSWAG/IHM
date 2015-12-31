package com.insa.thibault.ihm.config;

import android.content.Context;

import com.insa.thibault.ihm.RestaurantApplication;
import java.io.IOException;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by a607937 on 15/06/2015.
 */
@Module
public class AppModule {
    protected final RestaurantApplication application;

    public AppModule(RestaurantApplication application) throws IOException {
        this.application = application;




    }


    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

}
