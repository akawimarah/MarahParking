package com.akawi.marah.marahparking;

import android.location.Location;

import java.util.Date;

/**
 * Created by user on 10/26/2016.
 */
public class Parking {
    private boolean isShagira;// fadye aw la2
    private boolean is7enam;// bmsari aw la2
    private String adress;// el3inwan
    private Date when;// sa3it el2i3lan
    private Location location;//mkanha gps

    public Parking(boolean isShagira, boolean is7enam, String adress, Date when, Location location) {
        this.isShagira = isShagira;
        this.is7enam = is7enam;
        this.adress = adress;
        this.when = when;
        this.location = location;
    }

    public Parking() {
    }

    public boolean isShagira() {
        return isShagira;
    }

    public void setShagira(boolean shagira) {
        isShagira = shagira;
    }

    public boolean is7enam() {
        return is7enam;
    }

    public void setIs7enam(boolean is7enam) {
        this.is7enam = is7enam;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
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
