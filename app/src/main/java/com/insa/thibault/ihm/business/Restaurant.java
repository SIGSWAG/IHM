package com.insa.thibault.ihm.business;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Thibault on 09/12/2015.
 */
public class Restaurant implements Parcelable {

    private String name;
    private int nbFriends;
    private String plat;

    //Parcelable
    public static final Parcelable.Creator<Restaurant> CREATOR = new Parcelable.Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel source) {
            return new Restaurant(source);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };


    public Restaurant(String name) {
        this.name = name;
        this.nbFriends = 0;
        this.plat = "épinards";
    }

    public Restaurant(Parcel source) {
        this.name = source.readString();
        this.plat = source.readString();
        this.nbFriends = source.readInt();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbFriends() {
        return nbFriends;
    }

    public void setNbFriends(int nbFriends) {
        this.nbFriends = nbFriends;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(plat);
        dest.writeInt(nbFriends);
    }
}
