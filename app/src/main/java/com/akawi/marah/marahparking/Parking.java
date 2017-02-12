package com.akawi.marah.marahparking;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;


/**
 * Created by user on 11/23/2016.
 */
public class Parking {
    private String isShagira;// Vacant or not
    private String is7enam;// payment or not
    private String adress;// address
    private Date when;// Date of announcement
    private LatLng location;//location

    private String id;//

    public Parking(String adress, String isShagira, String is7enam, Date when, LatLng location , String id) {
        this.adress = adress;
        this.isShagira = isShagira;
        this.is7enam = is7enam;
        this.when = when;
        this.location = location;
        this.id = id;
    }

    /**
     * Constructivism virtual function(dali bina2ye aiftradye)
     */
    public Parking()
    {

    }


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

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "isShagira='" + isShagira + '\'' +
                ", is7enam='" + is7enam + '\'' +
                ", adress='" + adress + '\'' +
                ", when='" + when + '\'' +
                ", location=" + location +
                ", id='" + id + '\'' +
                '}';
    }
}
