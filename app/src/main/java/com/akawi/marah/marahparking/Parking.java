package com.akawi.marah.marahparking;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * Created by user on 10/26/2016.
 */
public class Parking {
    private String isShagira;// fadye aw la2
    private String is7enam;// bmsari aw la2
    private String adress;// el3inwan
    private String when;// sa3it el2i3lan
    private LatLng location;//mkanha gps


    public Parking(String isShagira, String is7enam, String adress,String when, LatLng location) {
        this.isShagira = isShagira;
        this.is7enam = is7enam;
        this.adress = adress;
        this.when = when;
        this.location = location;

    }

    public Parking() {
    }

    public String isShagira() {
        return isShagira;
    }

    public void setIsShagira(String shagira) {
        isShagira = shagira;
    }

    public String is7enam() {
        return is7enam;
    }

    public void setIs7enam(String is7enam) {
        this.is7enam = is7enam;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "isShagira=" + isShagira +
                ", is7enam=" + is7enam +
                ", adress='" + adress + '\'' +
                ", when=" + when +
                ", location=" + location +
                '}';
    }
}
