package com.insa.thibault.ihm.business;

/**
 * Created by Thibault on 09/12/2015.
 */
public class Restaurant {

    private String name;
    private int nbFriends;
    private String plat;

    public Restaurant(String name) {
        this.name = name;
        this.nbFriends = 0;
        this.plat = "épinards";
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
}
