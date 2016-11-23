package com.akawi.marah.marahparking;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by user on 11/23/2016.
 */
public class Parking1 {
    private String isShagira;// fadye aw la2
    private String is7enam;// bmsari aw la2
    private String adress;// el3inwan
    private String when;// sa3it el2i3lan
    private LatLng location;//mkanha gps
    private int prioroty;//
    private String id;

    public Parking1(String adress, String isShagira, String is7enam, String when, LatLng location, int prioroty, String id) {
        this.adress = adress;
        this.isShagira = isShagira;
        this.is7enam = is7enam;
        this.when = when;
        this.location = location;
        this.prioroty = prioroty;
        this.id = id;
    }
    public Parking1(){}


    public String getIsShagira() {
        return isShagira;
    }

    public void setIsShagira(String isShagira) {
        this.isShagira = isShagira;
    }

    public String getIs7enam() {
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

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public int getPrioroty() {
        return prioroty;
    }

    public void setPrioroty(int prioroty) {
        this.prioroty = prioroty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Parking1{" +
                "isShagira='" + isShagira + '\'' +
                ", is7enam='" + is7enam + '\'' +
                ", adress='" + adress + '\'' +
                ", when='" + when + '\'' +
                ", location=" + location +
                ", prioroty=" + prioroty +
                ", id='" + id + '\'' +
                '}';
    }
}
