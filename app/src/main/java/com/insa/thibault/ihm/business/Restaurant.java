package com.insa.thibault.ihm.business;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Thibault on 09/12/2015.
 */
public class Restaurant implements Parcelable {

    private String name;
    private int nbFriends;
    private int nbInvitations;
    private String plat;
    private String menu;
    private int distanceMetres;
    private boolean opened;
    private String address;
    private int img;


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


    public Restaurant(String name, int nbFriends, int nbInvitations, int distanceMetres, String plat,String menu, String address, int img) {
        this.name = name;
        this.nbFriends = nbFriends;
        this.nbInvitations = nbInvitations;
        this.distanceMetres = distanceMetres;
        this.plat = plat;
        this.address = address;
        opened = false;
        this.menu = menu;
        this.img = img;

    }

    public Restaurant(Parcel source) {
        this.name = source.readString();
        this.plat = source.readString();
        this.nbFriends = source.readInt();
        this.nbInvitations = source.readInt();
        this.distanceMetres = source.readInt();
        boolean[] app = new boolean[1];
        source.readBooleanArray(app);
        opened =  app[0];
        address = source.readString();
        img = source.readInt();
        menu = source.readString();

    }

    public String getName() {
        return name;
    }

    public int getNbFriends() {
        return nbFriends;
    }

    public int getNbInvitations() {
        return nbInvitations;
    }

    public String getDistanceMetres() {
        return "~" + distanceMetres + "m";
    }

    public String getPlat() {
        return plat;
    }

    public void setDistanceMetres(int distanceMetres) {
        this.distanceMetres = distanceMetres;
    }

    public void setNbInvitations(int nbInvitations) {
        this.nbInvitations = nbInvitations;
    }

    public void setNbFriends(int nbFriends) {
        this.nbFriends = nbFriends;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(name);
        dest.writeString(plat);
        dest.writeInt(nbFriends);
        dest.writeInt(nbInvitations);
        dest.writeInt(distanceMetres);
        boolean[] open = new boolean[1];
        open[0]=opened;
        dest.writeBooleanArray(open);
        dest.writeString(address);
        dest.writeInt(img);
        dest.writeString(menu);

    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Restaurant) {
            Restaurant restaurant = (Restaurant) o;
            if(!name.equals(restaurant.name)) {
                return false;
            }
            if(!plat.equals(restaurant.plat)) {
                return false;
            }
            if(nbFriends != restaurant.nbFriends) {
                return false;
            }
            if(distanceMetres != restaurant.distanceMetres) {
                return false;
            }
            return true;
        }
        return false;
    }



}
